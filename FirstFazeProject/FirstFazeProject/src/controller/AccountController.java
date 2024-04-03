package controller;
import model.*;
import view.AccountView;

import java.util.*;
import java.util.regex.Pattern;

public class AccountController {
    private static final AccountController accountController = new AccountController();

    private AccountController() {
    }

    public static AccountController getAccountController() {
        return accountController;
    }

    public int signUp(String answer) {
        String[] answers = answer.split(" -");
        Date date = null;
        if (answers.length > 3) {
            String[] dateInfo = answers[7].split("\\.");
            date = new Date(Integer.parseInt(dateInfo[0]), Integer.parseInt(dateInfo[1]), Integer.parseInt(dateInfo[2]));
        }
        switch (answers[0]) {
            case "Signup":
                for (UserAccount user : Database.getData().getAllUsers()) {
                    if (Objects.equals(user.getUniqueUserName(), answers[2])) {
                        return 0;
                    }
                }
                String passwordRegex = "(?=.*[a-z])(?=.*\\d)[a-z0-9A-Z]{10,16}";
                boolean result1 = Pattern.compile(passwordRegex).matcher(answers[3]).find();
                if (!result1)
                    return -1;
                String emailRegex = "\\w{8,50}@(gmail|yahoo|hotmail|aol)\\.com$";
                boolean result2 = Pattern.compile(emailRegex).matcher(answers[5]).find();
                if (!result2)
                    return -2;
                String phoneNumberRegex = "^([0][9]|[\\+][9][8][9])(([1][0-9])|([2][0-2])|([0][0-5])|([3][0])|([3][3])|([3][5-9])|([4][1]))(\\d){7}$";
//              can be replaced by this  ->  ^(09|\+989)((1[0-9])|(2[0-2])|(0[0-5])|(30)|(33)|(3[5-9])|(41))(\d){7}$
                boolean result3 = Pattern.compile(phoneNumberRegex).matcher(answers[6]).find();
                if (!result3)
                    return -3;
                switch (answers[1]) {
                    case "L":
                        Listener listenerPerson = new Free(answers[2], answers[3], answers[4], answers[5], answers[6], date);
                        addUserToDatabase(listenerPerson);
                        AccountView.getAccountView().showGenresMenu();
                        return 1;
                    case "S":
                        Singer singerPerson = new Singer(answers[2], answers[3], answers[4], answers[5], answers[6], date, answers[8]);
                        addUserToDatabase(singerPerson);
                        return 2;
                    case "P":
                        Podcaster podcastPerson = new Podcaster(answers[2], answers[3], answers[4], answers[5], answers[6], date, answers[8]);
                        addUserToDatabase(podcastPerson);
                        return 3;
                }
            case "Login":
                for (UserAccount userAccount : Database.getData().getAllUsers()) {
                    if (Objects.equals(userAccount.getUniqueUserName(), answers[1])) {
                        if (Objects.equals(userAccount.getPassword(), answers[2])) {
                            return 4;
                        }
                    }
                }
                return 5;
        }
        return 6;
    }

    private void addUserToDatabase(UserAccount userAccount) {
        ArrayList<UserAccount> backUp = Database.getData().getAllUsers();
        backUp.add(userAccount);
        Database.getData().setAllUsers(backUp);
    }

    public StringBuilder showGenres() {
        int counter = 1;
        StringBuilder result = new StringBuilder("The genres are : ");
        Genre[] genres = Genre.values();
        for (Genre genre : genres) {
            result.append(counter++).append("_").append(genre.name()).append(" ");
        }
        return result;
    }

    public void addFavoriteGenres(String answer) {
        Listener person = (Listener) Database.getData().getAllUsers().getLast();
        String[] answers = answer.split(",");
        ArrayList<Genre> result = person.getFavoriteGenres();
        for (int i = 0; i < 4; i++) {
            for (Genre genre : Genre.values()) {
                if (genre.name().equals(answers[i])) {
                    result.add(genre);
                    break;
                }
            }
        }
        person.setFavoriteGenres(result);
    }

    public void loginPanel(String answer) {
        String[] answers = answer.split(" -");
        for (UserAccount userAccount : Database.getData().getAllUsers()) {
            if (Objects.equals(userAccount.getUniqueUserName(), answers[1])) {
                if (Objects.equals(userAccount.getPassword(), answers[2])) {
                    if (userAccount instanceof Listener) {
                        String type = "Listener";
                        AccountView.getAccountView().successfullyLogin(userAccount, type);
                    } else if (userAccount instanceof Admin) {
                        String type = "Admin";
                        AccountView.getAccountView().successfullyLogin(userAccount, type);
                    } else if (userAccount instanceof Singer || userAccount instanceof Podcaster) {
                        String type = "Artist";
                        AccountView.getAccountView().successfullyLogin(userAccount, type);
                    }
                }
            }
        }
    }

    public void loginListenerPanelOrders(UserAccount user, String answer) {
        String[] answers = answer.split(" -");
        switch (answers[0]) {
            case "Logout":
                AccountView.getAccountView().showMainMenu();
                break;
            case "AccountInfo":
                AccountView.getAccountView().showResult(accountInfo(user));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "GetSuggestions":
                if (user instanceof Listener) {
                    AccountView.getAccountView().showResult(getSuggestions((Listener) user));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                } else {
                    AccountView.getAccountView().showResult(new StringBuilder("Your account is not the listener type"));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                }
                break;
            case "Artists":
                int counter = 1;
                StringBuilder result = new StringBuilder("The artists are : \r\n");
                for (UserAccount user1 : Database.getData().getAllUsers()) {
                    if (user1 instanceof Artist) {
                        result.append(counter++).append("_").append(user1.getFullName()).append("\r\n");
                    }
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Artist":
                counter = 1;
                result = new StringBuilder(" ");
                for (UserAccount userAccount : Database.getData().getAllUsers()) {
                    if (userAccount instanceof Artist) {
                        if (Objects.equals(answers[1], userAccount.getUniqueUserName())) {
                            result.append(accountInfo(userAccount));
                            for (Audio audio : Database.getData().getAllAudios()) {
                                if (Objects.equals(audio.getArtistName(), userAccount.getUniqueUserName())) {
                                    result.append(counter++).append(audio.getAudioName()).append(" ");
                                }
                            }
                            AccountView.getAccountView().showResult(result);
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        }
                    }
                    AccountView.getAccountView().showResult(new StringBuilder("The artist was not found"));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                }
                break;
            case "Follow":
                for (UserAccount userAccount : Database.getData().getAllUsers()) {
                    if (userAccount instanceof Artist) {
                        if (Objects.equals(answers[1], userAccount.getUniqueUserName())) {
                            ArrayList<UserAccount> backUp = ((Artist) userAccount).getFollowers();
                            backUp.add(user);
                            ((Artist) userAccount).setFollowers(backUp);
                            AccountView.getAccountView().showResult(new StringBuilder("The artist was followed successfully"));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        }
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("The artist was not found"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Search":
                counter = 1;
                result = new StringBuilder("Artists you might be searching for : \r\n");
                for (UserAccount userAccount : Database.getData().getAllUsers()) {
                    if (userAccount instanceof Artist) {
                        if (userAccount.getUniqueUserName().contains(answers[1])) {
                            result.append(counter++).append("_").append(userAccount.getUniqueUserName()).append(" ");
                        }
                    }
                }
                counter = 1;
                result.append("\r\n").append("Audios you might be searching for : \r\n");
                for (Audio audio : Database.getData().getAllAudios()) {
                    if (audio.getAudioName().contains(answers[1])) {
                        result.append(counter++).append("_").append(audio.getAudioName()).append(" ");
                    }
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Sort":
                counter = 1;
                ArrayList<Audio> audioArrayList = Database.getData().getAllAudios();
                ;
                if (Objects.equals(answers[1], "L")) {
                    result = new StringBuilder("The sorted list of audios buy number of likes : \r\n");
                    for (int i = 0; i < audioArrayList.size() - 1; i++) {
                        for (int j = i + 1; j < audioArrayList.size(); j++) {
                            if (audioArrayList.get(i).getLikes() < audioArrayList.get(j).getLikes()) {
                                Audio temp = audioArrayList.get(i);
                                audioArrayList.remove(i);
                                audioArrayList.add(i, audioArrayList.get(j - 1));
                                audioArrayList.remove(j);
                                audioArrayList.add(j, temp);
                            }
                        }
                    }
                    for (Audio audio : audioArrayList) {
                        result.append(counter++).append("_").append(audio.getAudioName()).append("(").append(audio.getLikes()).append(")").append("\r\n");
                    }
                    AccountView.getAccountView().showResult(result);
                    AccountView.getAccountView().showListenerLoginPanel(user);
                } else if (Objects.equals(answers[1], "P")) {
                    result = new StringBuilder("The sorted list of audios buy number of plays : \r\n");
                    for (int i = 0; i < audioArrayList.size() - 1; i++) {
                        for (int j = i + 1; j < audioArrayList.size(); j++) {
                            if (audioArrayList.get(i).getTimesPlayed() < audioArrayList.get(j).getTimesPlayed()) {
                                Audio temp = audioArrayList.get(i);
                                audioArrayList.remove(i);
                                audioArrayList.add(i, audioArrayList.get(j - 1));
                                audioArrayList.remove(j);
                                audioArrayList.add(j, temp);
                            }
                        }
                    }
                    for (Audio audio : audioArrayList) {
                        result.append(counter++).append("_").append(audio.getAudioName()).append("(").append(audio.getTimesPlayed()).append(")").append("\r\n");
                    }
                    AccountView.getAccountView().showResult(result);
                    AccountView.getAccountView().showListenerLoginPanel(user);
                }
                break;
            case "Filter":
                counter = 1;
                result = new StringBuilder("The songs you were searching for by your filter : \r\n");
                switch (answers[1]) {
                    case "A":
                        for (UserAccount userAccount : Database.getData().getAllUsers()) {
                            for (Audio audio : Database.getData().getAllAudios()) {
                                if (audio.getAudioName().contains(answers[2])) {
                                    result.append(counter++).append("_").append(audio.getAudioName()).append(" ");
                                }
                            }
                        }
                        AccountView.getAccountView().showResult(result);
                        AccountView.getAccountView().showListenerLoginPanel(user);
                        break;
                    case "G":
                        for (Audio audio : Database.getData().getAllAudios()) {
                            if (audio.getGenre().name().equals(answers[2])) {
                                result.append(counter++).append("_").append(audio.getAudioName()).append(" ");
                            }
                        }
                        AccountView.getAccountView().showResult(result);
                        AccountView.getAccountView().showListenerLoginPanel(user);
                        break;
                    case "D":
                        String[] dateInfo1 = answers[2].split("\\.");
                        String[] dateInfo2 = answers[3].split("\\.");
                        Date date1 = new Date(Integer.parseInt(dateInfo1[0]), Integer.parseInt(dateInfo1[1]), Integer.parseInt(dateInfo1[2]));
                        Date date2 = new Date(Integer.parseInt(dateInfo1[0]), Integer.parseInt(dateInfo1[1]), Integer.parseInt(dateInfo1[2]));
                        for (Audio audio : Database.getData().getAllAudios()) {
                            if (date1.compareTo(audio.getReleaseTime()) <= 0 && date2.compareTo(audio.getReleaseTime()) >= 0) {
                                result.append(counter++).append("_").append(audio.getAudioName()).append(" ");
                            }
                        }
                        AccountView.getAccountView().showResult(result);
                        AccountView.getAccountView().showListenerLoginPanel(user);
                        break;
                }
            case "Add":
                if (user instanceof Free) {
                    for (Playlist playlist : ((Free) user).getPlaylists()) {
                        if (Objects.equals(playlist.getPlayListName(), answers[1])) {
                            if (playlist.getAudioList().size() >= ((Free) user).getMaxAddSongToPlaylist()) {
                                AccountView.getAccountView().showResult(new StringBuilder("Max number of audios have been added before to this list"));
                                AccountView.getAccountView().showListenerLoginPanel(user);
                            }
                            ArrayList<Audio> backUp = playlist.getAudioList();
                            for (Audio audio : Database.getData().getAllAudios()) {
                                if (audio.getUniqueId() == Integer.parseInt(answers[2])) {
                                    backUp.add(audio);
                                    break;
                                }
                            }
                            playlist.setAudioList(backUp);
                            break;
                        }
                    }
                    AccountView.getAccountView().showResult(new StringBuilder("The audio was added successfully"));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                    break;
                } else if (user instanceof Premium) {
                    for (Playlist playlist : ((Premium) user).getPlaylists()) {
                        if (Objects.equals(playlist.getPlayListName(), answers[1])) {
                            ArrayList<Audio> backUp = playlist.getAudioList();
                            for (Audio audio : Database.getData().getAllAudios()) {
                                if (audio.getUniqueId() == Integer.parseInt(answers[2])) {
                                    backUp.add(audio);
                                    break;
                                }
                            }
                            playlist.setAudioList(backUp);
                            AccountView.getAccountView().showResult(new StringBuilder("The audio was added successfully"));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                            break;
                        }
                    }
                    AccountView.getAccountView().showResult(new StringBuilder("An issue was found please try again and be more specific"));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                    break;
                }
                break;
            case "ShowPlaylists":
                counter = 1;
                result = new StringBuilder("The play lists names and ids are : \r\n");
                if (user instanceof Free) {
                    for (Playlist playlist : ((Free) user).getPlaylists()) {
                        result.append(counter++).append("_").append(playlist.getPlayListName()).append("(").append(playlist.getId()).append(") ");
                    }
                    AccountView.getAccountView().showResult(new StringBuilder(result));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                } else if (user instanceof Premium) {
                    for (Playlist playlist : ((Premium) user).getPlaylists()) {
                        result.append(counter++).append("_").append(playlist.getPlayListName()).append("(").append(playlist.getId()).append(") ");
                    }
                    AccountView.getAccountView().showResult(new StringBuilder(result));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                }
                AccountView.getAccountView().showResult(new StringBuilder("An issue was found please try again and be more specific"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "SelectPlaylist":
                if (user instanceof Free) {
                    for (Playlist playlist : ((Free) user).getPlaylists()) {
                        if (Objects.equals(playlist.getPlayListName(), answers[1])) {
                            AccountView.getAccountView().showResult(new StringBuilder("The play list was selected successfully"));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        }
                    }
                } else if (user instanceof Premium) {
                    for (Playlist playlist : ((Premium) user).getPlaylists()) {
                        if (Objects.equals(playlist.getPlayListName(), answers[1])) {
                            AccountView.getAccountView().showResult(new StringBuilder("The play list was selected successfully"));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        }
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("An issue was found please try again and be more specific"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Play":
                for (Audio audio : Database.getData().getAllAudios()) {
                    if (audio.getUniqueId() == Integer.parseInt(answers[1])) {
                        audio.setTimesPlayed((audio.getTimesPlayed() + 1));
                        Map<Audio, Integer> backUp = ((Listener) user).getAudioTimesPlayed();
                        for (Audio audio1 : backUp.keySet()) {
                            if (audio1 == audio) {
                                backUp.put(audio1, (backUp.get(audio1) + 1));
                                break;
                            }
                        }
                        ((Listener) user).setAudioTimesPlayed(backUp);
                        AccountView.getAccountView().showResult(new StringBuilder("The music was played successfully"));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("The audio was not found be more specific"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Like":
                for (Audio audio : Database.getData().getAllAudios()) {
                    if (audio.getUniqueId() == Integer.parseInt(answers[1])) {
                        AccountView.getAccountView().showResult(new StringBuilder("The music was liked successfully"));
                        audio.setLikes((audio.getLikes() + 1));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("The audio was not found be more specific"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Lyric":
                for (Audio audio : Database.getData().getAllAudios()) {
                    if (audio.getUniqueId() == Integer.parseInt(answers[1])) {
                        if (audio instanceof Music) {
                            AccountView.getAccountView().showResult(new StringBuilder("The music lyrics : \r\n").append(((Music) audio).getMusicLyrics()));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        } else if (audio instanceof Podcast) {
                            AccountView.getAccountView().showResult(new StringBuilder("The podcast caption : \r\n").append(((Podcast) audio).getCaption()));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        }
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("The audio was not found be more specific"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "NewPlaylist":
                if (user instanceof Free) {
                    if (((Listener) user).getPlaylists().size() >= ((Free) user).getMaxPlaylistMade()) {
                        AccountView.getAccountView().showResult(new StringBuilder("You have already made the maximum number of lists"));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    } else {
                        Playlist newPlaylist = new Playlist(answers[1], user.getUniqueUserName());
                        ArrayList<Playlist> backUp = ((Free) user).getPlaylists();
                        backUp.add(newPlaylist);
                        ((Free) user).setPlaylists(backUp);
                        AccountView.getAccountView().showResult(new StringBuilder("The playlist was successfully made"));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    }
                } else if (user instanceof Premium) {
                    Playlist newPlaylist = new Playlist(answers[1], user.getUniqueUserName());
                    ArrayList<Playlist> backUp = ((Premium) user).getPlaylists();
                    backUp.add(newPlaylist);
                    ((Premium) user).setPlaylists(backUp);
                    AccountView.getAccountView().showResult(new StringBuilder("The playlist was successfully made"));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                }
                AccountView.getAccountView().showResult(new StringBuilder("The playlist can't be made"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Followings":
                counter = 1;
                result = new StringBuilder("The followings are : \r\n");
                for (UserAccount userAccount : Database.getData().getAllUsers()) {
                    if (userAccount instanceof Artist) {
                        for (UserAccount userAccount1 : ((Artist) userAccount).getFollowers()) {
                            if (userAccount1 == user) {
                                result.append(counter++).append("_").append(userAccount.getUniqueUserName()).append(" ");
                            }
                            break;
                        }
                    }
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "Report":
                for (UserAccount userAccount : Database.getData().getAllUsers()) {
                    if (userAccount instanceof Artist) {
                        if (Objects.equals(userAccount.getUniqueUserName(), answers[1])) {
                            Report report = new Report(user, (Artist) userAccount, answers[2]);
                            ArrayList<Report> backUp = Database.getData().getReports();
                            backUp.add(report);
                            Database.getData().setReports(backUp);
                            AccountView.getAccountView().showResult(new StringBuilder("The report was recorded successfully"));
                            AccountView.getAccountView().showListenerLoginPanel(user);
                        }
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("The report's recording failed"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "IncreaseCredit":
                ((Listener) user).setAccountCredit(((Listener) user).getAccountCredit() + Double.parseDouble(answers[1]));
                AccountView.getAccountView().showResult(new StringBuilder("Your account credit was increased"));
                AccountView.getAccountView().showListenerLoginPanel(user);
                break;
            case "GetPremium":
                switch (answers[1]) {
                    case "ThirtyDay", "OneEightyDay", "SixtyDay":
                        accountShareCheck(user, answers);
                        break;
                }
            default:
                AccountView.getAccountView().showResult(new StringBuilder("Your command is unable to run"));
                AccountView.getAccountView().showListenerLoginPanel(user);
        }
    }

    public void accountShareCheck(UserAccount user, String[] answers) {
        if (user instanceof Free) {
            for (PremiumShare premiumShare : PremiumShare.values()) {
                if (premiumShare.name().equals(answers[1])) {
                    if (((Listener) user).getAccountCredit() >= premiumShare.getValue()) {
                        freeToPremium((Free) user, premiumShare);
                        AccountView.getAccountView().showResult(new StringBuilder("Your account is now premium"));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    }
                    AccountView.getAccountView().showResult(new StringBuilder("Your credit is not enough"));
                    AccountView.getAccountView().showListenerLoginPanel(user);
                }
            }
        } else if (user instanceof Premium) {
            for (PremiumShare premiumShare : PremiumShare.values()) {
                if (premiumShare.name().equals(answers[1])) {
                    if (((Listener) user).getAccountCredit() >= premiumShare.getValue()) {
                        updatePremiumShare((Premium) user, premiumShare);
                        AccountView.getAccountView().showResult(new StringBuilder("Your account is now premium"));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    } else {
                        AccountView.getAccountView().showResult(new StringBuilder("Your credit is not enough"));
                        AccountView.getAccountView().showListenerLoginPanel(user);
                    }
                }
            }
        }
    }

    public void freeToPremium(Free person1, PremiumShare premiumShare) {
        Premium person2 = new Premium(person1.getUniqueUserName(), person1.getPassword(), person1.getFullName(), person1.getEmail(), person1.getPhoneNumber(), person1.getBirthDate());
        person2.setShareDaysLeft(premiumShare.getDays());
        person2.setPlaylists(person1.getPlaylists());
        person2.setAudioTimesPlayed(person1.getAudioTimesPlayed());
        person2.setFavoriteGenres(person1.getFavoriteGenres());
        person2.setAccountCredit(person1.getAccountCredit() - premiumShare.getValue());
        ArrayList<UserAccount> backUp = Database.getData().getAllUsers();
        backUp.remove(person1);
        backUp.add(person2);
        Database.getData().setAllUsers(backUp);
    }

    public void updatePremiumShare(Premium person, PremiumShare premiumShare) {
        person.setShareDaysLeft(person.getShareDaysLeft() + premiumShare.getDays());
        person.setAccountCredit(person.getAccountCredit() - premiumShare.getValue());
    }

    public StringBuilder accountInfo(UserAccount user) {
        StringBuilder result = new StringBuilder("Account's info :\r\n");
        result.append("User Name : ").append(user.getUniqueUserName()).append("\r\n");
        result.append("Full Name : ").append(user.getFullName()).append("\r\n");
        result.append("E-Mail : ").append(user.getEmail()).append("\r\n");
        result.append("Birth Date : ").append(user.getBirthDate().getYear()).append("/").append(user.getBirthDate().getMonth()).append("/").append(user.getBirthDate().getDate()).append("\r\n");
        result.append("Phone Number : ").append(user.getPhoneNumber()).append("\r\n");
        AccountView.getAccountView().showResult(result);
        return result;
    }

    public StringBuilder getSuggestions(Listener person) {
        int counter = 1;
        StringBuilder result = new StringBuilder("Audios you might like : ");
        for (Audio audio : Database.getData().getAllAudios()) {
            for (Genre genre1 : person.getFavoriteGenres()) {
                if (genre1 == audio.getGenre() && counter <= 10) {
                    result.append(counter++).append("_").append(audio.getAudioName()).append(" ");
                }
            }
        }
        return result;
    }

    public void loginAdminPanelOrders(Admin admin, String answer) {
        String[] answers = answer.split(" -");
        StringBuilder result;
        int counter;
        switch (answers[0]) {
            case "Logout":
                AccountView.getAccountView().showMainMenu();
                break;
            case "AccountInfo":
                AccountView.getAccountView().showResult(accountInfo(admin));
                AccountView.getAccountView().showAdminLoginPanel(admin);
                break;
            case "Statistics":
                ArrayList<Audio> audios1 = Database.getData().getAllAudios();
                ArrayList<Audio> audios2 = new ArrayList<>();
                for (int i = 0 ; i < audios1.size() ; i++){
                    Audio backUp = audios1.getFirst() ;
                    for (Audio audio : Database.getData().getAllAudios()){
                        if (backUp.getLikes() < audio.getLikes()){
                            backUp = audio;
                        }
                    }
                    audios1.remove(backUp);
                    audios2.add(backUp);
                }
                counter = 1;
                result = new StringBuilder("The list of audios by like ranking : \r\n");
                for (Audio audio : audios2){
                    result.append(counter++).append("_").append(audio.getAudioName()).append("(").append(audio.getLikes()).append(")\r\n");
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showAdminLoginPanel(admin);
                break;
            case "Audios":
                result = new StringBuilder("The list of the audios, organized by adding time : \r\n");
                counter=1;
                for (Audio audio : Database.getData().getAllAudios()){
                    result.append(counter++).append("_").append(audio.getAudioName()).append("(").append(audio.getUniqueId()).append(")\r\n");
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showAdminLoginPanel(admin);
                break;
            case "Audio" :
                result = new StringBuilder("The audio's information : \r\n");
                for (Audio audio : Database.getData().getAllAudios()){
                    if (audio.getUniqueId() == Integer.parseInt(answers[1])){
                        result.append("Audio's name : ").append(audio.getAudioName()).append("\r\n");
                        result.append("Audio's likes : ").append(audio.getLikes()).append("\r\n");
                        result.append("Audio's genre : ").append(audio.getGenre().name()).append("\r\n");
                        result.append("Audio's times played : ").append(audio.getTimesPlayed()).append("\r\n");
                        result.append("Audio's artist's name : ").append(audio.getArtistName()).append("\r\n");
                        result.append("Audio's name : ").append(audio.getReleaseTime().toString()).append("\r\n");
                    }
                    AccountView.getAccountView().showResult(result);
                    AccountView.getAccountView().showAdminLoginPanel(admin);
                }
                AccountView.getAccountView().showResult(new StringBuilder("The id was not found"));
                AccountView.getAccountView().showAdminLoginPanel(admin);
            case "Artists":
                result = new StringBuilder("The list of the Artists, organized by adding time : \r\n");
                counter = 1;
                for (UserAccount user : Database.getData().getAllUsers()){
                    if (user instanceof Artist) {
                        result.append(counter++).append("_").append(user.getUniqueUserName()).append("\r\n");
                    }
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showAdminLoginPanel(admin);
                break;
            case "Artist":
                for (UserAccount userAccount : Database.getData().getAllUsers()){
                    if (Objects.equals(userAccount.getUniqueUserName(), answers[1])){
                        AccountView.getAccountView().showResult(accountInfo(userAccount));
                        AccountView.getAccountView().showAdminLoginPanel(admin);
                    }
                }
                AccountView.getAccountView().showResult(new StringBuilder("The username was not found"));
                AccountView.getAccountView().showAdminLoginPanel(admin);
                break;
            case "Reports":
                result = new StringBuilder("All reports recorded till now : \r\n");
                counter =1;
                for (Report report : Database.getData().getReports()){
                    result.append(counter++).append(report.getReporterUser()).append("--->").append(report.getReportedArtist())
                            .append("\r\n").append(report.getCaption()).append("\r\n");
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showAdminLoginPanel(admin);
            default:
                AccountView.getAccountView().showResult(new StringBuilder("The order is not able to be run please try again"));
                AccountView.getAccountView().showAdminLoginPanel(admin);
        }
    }

    public void loginArtistPanelOrders(Artist artist, String answer){
        String[] answers = answer.split(" -");
        StringBuilder result;
        int counter;
        switch (answers[0]){
            case "Followers":
                result = new StringBuilder("The artist's followers : ");
                counter = 1 ;
                for (UserAccount userAccount : artist.getFollowers()){
                    result.append(counter++).append("_").append(userAccount.getUniqueUserName()).append("\r\n");
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showArtistLoginPanel(artist);
                break;
            case "ViewsStatistics":
                result = new StringBuilder("The artist's audios and their plays count : \r\n");
                counter = 1;
                for (Audio audio : Database.getData().getAllAudios()){
                    if (Objects.equals(audio.getArtistName(), artist.getUniqueUserName())){
                        result.append(counter++).append("_").append(audio.getAudioName()).append("(")
                                .append(audio.getTimesPlayed()).append(")\r\n");
                    }
                }
                AccountView.getAccountView().showResult(result);
                AccountView.getAccountView().showArtistLoginPanel(artist);
            case "CalculateEarnings":
                result = new StringBuilder("The income of the artist is : ");
                if (artist instanceof Singer){
                    result.append(artist.audiosTimesPlayed());
                    AccountView.getAccountView().showArtistLoginPanel(artist);
                }else if ( artist instanceof Podcaster){
                    result.append(artist.audiosTimesPlayed());
                    AccountView.getAccountView().showArtistLoginPanel(artist);
                }
                break;
            case "NewAlbum" :

        }
    }
}