package controller.Listener;

import model.Audio.Audio;
import model.Audio.Music;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre.Genre;
import model.Playlist.Playlist;
import model.PremiumPackages.PremiumSubscriptionPackages;
import model.Report.Report;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.Artist.Singer;
import model.UserAccounts.Listener.Free;
import model.UserAccounts.Listener.Listener;
import model.UserAccounts.Listener.Premium;
import model.UserAccounts.userAccount;
import sun.font.TrueTypeFont;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class listenerController {
    private static listenerController listenerC;
    private Listener listenerM;

    private listenerController() {
    }

    public static listenerController getListenerC() {
        if (listenerC == null) {
            listenerC = new listenerController();
        }
        return listenerC;
    }


    public Listener getListenerM() {
        return listenerM;
    }

    public void setListenerM(Listener listenerM) {
        this.listenerM = listenerM;
    }


    //// Register free listener
    public int registerListener(String userId, String password, String fullName, String email, String phoneNumber, Date birthday) {
        for (userAccount userAccount : Database.getDatabase().getAllUsersList()) {
            if (userAccount.getUserId().equals(userId)) {
                return 0; //this user name has already exist!
            }
        }
        Pattern phoneRegex = Pattern.compile("^09[01239][0-9]{8}$");
        if (!phoneRegex.matcher(phoneNumber).matches()) {
            return 1; //phone number is not true!
        }
        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+.[a-zA-Z]{2,}$");
        if (!emailRegex.matcher(email).matches()) {
            return 2; //email is not true
        }
        Free tempListener = new Free(userId, password, fullName, email, phoneNumber, birthday);
        listenerM = tempListener;
        Database.getDatabase().getAllUsersList().add(listenerM);
        return 3; //Registering was successful!!!
    }

    //Show Genres to  registered listener;

    //choose Genres to listener:
    public void chooseFourGenres(Genre genre1, Genre genre2, Genre genre3, Genre genre4) {
        getListenerM().getFavouriteGenres().add(genre1);
        getListenerM().getFavouriteGenres().add(genre2);
        getListenerM().getFavouriteGenres().add(genre3);
        getListenerM().getFavouriteGenres().add(genre4);
    }

    /// Login free listener
    public void loginListener(userAccount userAccount) {

        if (userAccount instanceof Free) {
            listenerM = (Free) userAccount;
        } else if (userAccount instanceof Premium) {
            listenerM = (Premium) userAccount;
        }
    }


    /// Create playlist
    public String createPlaylist(String name) {
        if (listenerM instanceof Free) {
            if (listenerM.getPlaylists().size() >= 3) {
                return "Error! you can not create mort than 3 playlist; because you are a free listener";
            }
        }
        Playlist playlist = new Playlist(name, listenerM.getUserId(), new ArrayList<Audio>());
        listenerM.getPlaylists().add(playlist);
        return "Your playlist was created";
    }

    // Add audio to playlist

    public String addAudioToPlaylist(String name, int audioId) {
        for (Playlist playlist : listenerM.getPlaylists()) {
            if (playlist.getPlayListName().equals(name)) {
                for (Audio audio : Database.getDatabase().getAllAudiosList()) {
                    if (audio.getAudioId() == audioId) {
                        for (Audio audio1 : playlist.getAudioList()) {
                            if (audio.equals(audio1)) {
                                return "you added this audio to playlist before!";
                            }
                        }
                        playlist.getAudioList().add(audio);
                        return "audio added to playlist successfully";
                    }
                }
                return "audio not found!";
            }
        }
        return "playlist not found!";
    }

    // Play the audio
    public String playAudio(int audioId) {
        for (Audio audio : Database.getDatabase().getAllAudiosList()) {
            if (audio.getAudioId() == audioId) {
                if (audio instanceof Podcast) {
                    audio.getArtist().setIncome(audio.getArtist().getIncome() + .5);
                } else if (audio instanceof Music) {
                    audio.getArtist().setIncome(audio.getArtist().getIncome() + .4);
                }
                if (listenerM.getPlayFiles().containsKey(audio)) {
                    listenerM.getPlayFiles().put(audio, listenerM.getPlayFiles().get(audio) + 1);
                } else {
                    listenerM.getPlayFiles().putIfAbsent(audio, 0);
                }
                return audio.toString();
            }
        }
        return "not found";
    }

    /// like audio
    public String likeAudio(int audioId) {
        for (Audio audio : Database.getDatabase().getAllAudiosList()) {
            if (audio.getAudioId() == audioId) {
                audio.setNumberOfLikes(audio.getNumberOfLikes() + 1);
                return "You liked this audio";
            }
        }
        return "Audio not found!";
    }

    /// show lyric
    public String lyricAudio(int audioId) {
        for (Audio audio : Database.getDatabase().getAllAudiosList()) {
            if (audio.getAudioId() == audioId) {
                if (audio instanceof Music) {
                    return ((Music) audio).getLyric();
                } else if (audio instanceof Podcast) {
                    return ((Podcast) audio).getCaption();
                }
            }
        }
        return "not found";
    }

    /// follow artist
    public String followArtist(String userId) {
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user.getUserId().equals(userId)) {
                if (user instanceof Artist) {
                    ((Artist) user).getFollowersList().add(listenerM);
                    listenerM.getFollowings().add((Artist) user);
                    return "following was successful.";
                }
            }
        }
        return "user not found or not an artist";
    }

    //// show followings
    public String showFollowings() {
        StringBuilder context = new StringBuilder();
        if (listenerM.getFollowings().isEmpty()) {
            return "There is no following for you!";
        }
        for (Artist artist : listenerM.getFollowings()) {
            context.append(artist.toString());
            context.append("\n");
        }
        return context.toString();
    }

    /// report artist

    public String reportArtist(String userId, String explanation) {
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user instanceof Artist && user.getUserId().equals(userId)) {
                Database.getDatabase().getReportsList().add(new Report(listenerM, (Artist) user, explanation));
                return "report was send";
            }
        }
        return "user not found!";
    }

    /// increase credit
    public String increaseCredit(double value) {
        listenerM.setCredit(listenerM.getCredit() + value);
        String res="your credit upgraded to : ";
        res= res+ listenerM.getCredit();
        return res;
    }

    /// buy premium account:

    public String getPremium(PremiumSubscriptionPackages type) {
        if (listenerM.getCredit() >= type.getValue()) {
            Premium premium = (Premium) listenerM;
            if (type == PremiumSubscriptionPackages.THIRTYDAYS) {
                premium.setLeftOverDays(30);
            } else if (type == PremiumSubscriptionPackages.SIXTYDAYS) {
                premium.setLeftOverDays(60);
            } else if (type == PremiumSubscriptionPackages.ONEHUNDREDEIGHTYDAYS) {
                premium.setLeftOverDays(100);
            }
            Database.getDatabase().getAllUsersList().remove(listenerM);
            listenerM = premium;
            Database.getDatabase().getAllUsersList().add(listenerM);
            return "You now are a premium listener;";
        }
        return "Your credit is not enough!";
    }


    //// show playlists
    public String showPlaylists() {
        StringBuilder context = new StringBuilder();
        for (Playlist playlist : listenerM.getPlaylists()) {
            context.append(playlist.toString());
            context.append("\n");
        }
        return context.toString();
    }

    /// select playlist
    public String selectPlaylist(String name) {
        StringBuilder context = new StringBuilder();
        for (Playlist playlist : listenerM.getPlaylists()) {
            if (playlist.getPlayListName().equals(name)) {
                context.append(playlist.toString());
                return context.toString();
            }
        }
        return "Not found";
    }

    /// show all artist
    public String artistsList() {
        StringBuilder context = new StringBuilder();
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user instanceof Artist) {
                context.append(((Artist) user).toString());
                context.append("\n");
            }
        }
        return context.toString();
    }

    /// show one artis
    public String artist(String userId) {
        StringBuilder context = new StringBuilder();
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user instanceof Artist) {
                if (user.getUserId().equals(userId)) {
                    context.append(((Artist) user).toString());
                    return context.toString();
                }
            }
        }
        return "Artist not found";
    }


    /// GetSuggestion
    public String getSuggestion(int n) {
        Audio[] suggestions = new Audio[n];
        int i = 0;
        Map<Audio, Integer> map = new HashMap<>();
        map = listenerM.getPlayFiles().entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())
        ).collect(LinkedHashMap::new, (map1, entry) -> map1.put(entry.getKey(), entry.getValue()), Map::putAll);
        for (Audio audio : map.keySet()) {
            if (i >= n - 1) break;
            for (Artist artist : listenerM.getFollowings()) {
                if (artist.equals(audio.getArtist())) {
                    suggestions[i++] = audio;
                }
            }
        }
        if (i < n - 1) {
            for (Audio audio : map.keySet()) {
                if (i >= n - 1) break;
                suggestions[i++] = audio;
            }
        }
        if (i < n - 1) {
            for (Audio audio : Database.getDatabase().getAllAudiosList()) {
                if (i >= n - 1) break;
                for (Artist artist : listenerM.getFollowings()) {
                    if (artist.equals(audio.getArtist())) {
                        suggestions[i++] = audio;
                    }
                }
            }
        }
        StringBuilder context = new StringBuilder();
        return context.toString();
    }

    /// Sort
    public String Sort(String by) {
        StringBuilder context = new StringBuilder();
        if (by.equals("L")) {
            List<Audio> sortedList = Database.getDatabase().getAllAudiosList().stream().sorted(Comparator.comparingInt(Audio::getNumberOfLikes).reversed()).collect(Collectors.toList());
            ;
            for (Audio audio : sortedList) {
                context.append(audio.toString());
            }
        } else if (by.equals("P")) {
            List<Audio> sortedList = Database.getDatabase().getAllAudiosList().stream().sorted(Comparator.comparingInt(Audio::getNumberOfPlays).reversed()).collect(Collectors.toList());
            ;
            for (Audio audio : sortedList) {
                context.append(audio.toString());
            }
        }
        return context.toString();
    }

    /// Search
    public String Search(String name) {
        StringBuilder context = new StringBuilder();
        List<Audio> searchList1 = Database.getDatabase().getAllAudiosList().stream().filter(obj -> obj.getFileName().equals(name)).collect(Collectors.toList());
        List<Artist> searchList2 = new ArrayList<>();
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user instanceof Artist) {
                searchList2.add((Artist) user);
            }
        }
        searchList2 = searchList2.stream().filter(obj -> obj.getUserId().equals(name)).collect(Collectors.toList());
        for (Artist artist : searchList2) {
            context.append(artist.toString());
        }
        for (Audio audio : searchList1) {
            context.append(audio.toString());
        }
        return context.toString();
    }


    public String filter(Object first, Object last) {
        StringBuilder context = new StringBuilder();
        List<Audio> audioList = Database.getDatabase().getAllAudiosList();
        if (last != null) {
            audioList = audioList.stream().filter(obj -> obj.getDateOfRelease().after((Date) first)
                    && obj.getDateOfRelease().before((Date) last)).collect(Collectors.toList());
        } else if (first instanceof String) {
            audioList = audioList.stream().filter(obj -> obj.getArtistName().equals((String) first)
            ).collect(Collectors.toList());
        } else if (first instanceof Genre) {
            audioList = audioList.stream().filter(obj -> obj.getGenre().equals((Genre) first)
            ).collect(Collectors.toList());
        }
        for (Audio audio : audioList) {
            context.append(audio.toString());
            context.append("\n");
        }
        return context.toString();
    }


    ///AccountInfo
    public String AccountInfo() {
        if (listenerM instanceof Premium){
            ((Premium) listenerM).setLeftOverDays(((Premium)listenerM).getLeftOverDays()-1);
            if (((Premium)listenerM).getLeftOverDays()==0){
                Listener free = (Free) listenerM;
                Database.getDatabase().getAllUsersList().remove(listenerM);
                listenerM=free;
            }
        }
        return listenerM.toString();
    }


}
