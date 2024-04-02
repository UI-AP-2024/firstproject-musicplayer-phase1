package Controller;
import Model.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController {
    private ListenerModel model;
    private int count=0;

    public String signUp(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        for (User user : Database.getDatabase().getUsers())
            if(user.getUsername().equals(username))
                return "This Username already used!";

        Pattern emailPattern = Pattern.compile(".*@.*(\\.com|\\.ir|\\.org)$");
        Matcher matcherEmail = emailPattern.matcher(email);
        Pattern phonepattern = Pattern.compile("^\\d{8,11}$");
        Matcher matcherPhone = phonepattern.matcher(phoneNumber);
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}$");
        Matcher matcherPassword = passwordPattern.matcher(password);
        if(!matcherEmail.matches())
            return "The email is not valid!";
        else if(!matcherPhone.matches())
            return "The phone number is not valid!";
        else if(!matcherPassword.matches())
            return "Weak password! Pleas use better password.";

        model = new ListenerModel(username, password, fullName, email, phoneNumber, birthDate);
        model.setCredit(50);
        return "Done!";
    }
    public String logIn(String username, String password){
        for (User user : Database.getDatabase().getUsers())
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(user instanceof PremiumModel)
                    model = (PremiumModel)user;
                else
                    model = (ListenerModel) user;

                return "Done!";
            }

        return "Username or password is wrong!";
    }
    public void selectGenres(Genre genre){

        if( count < 4 ){
            model.getFavoriteGenres()[count] = genre;
            count++;
        }
    }
    public String makeNewPlaylist(String playlistName,String userName){

        if(model instanceof PremiumModel) {
            model.getPlaylists().add(new Playlists(playlistName,userName));
            return "Playlist made successfully";
        }
        else if(model.playlistCount < FreeModel.maxPlaylist){
            model.getPlaylists().add(new Playlists(playlistName,userName));
            model.playlistCount++;
            return "Playlist made successfully";
        }

        return "Sorry! You can't creat more than 3 playlists with freeAccount.";
    }
    public String addMusicToPlaylist(String playlistName,int ID){
        if(model instanceof PremiumModel){
            searchAndAdd(playlistName,ID);
            return "Audio added to playlist successfully.";
        }
        else if(model.musicCount < FreeModel.maxAudio){
            searchAndAdd(playlistName,ID);
            model.musicCount++;
            return "Audio added to playlist successfully.";
        }
        return "Sorry! You can't add more than 10 Songs to the playlist with freeAccount.";
    }
    private void searchAndAdd(String playlistName,int ID){
        for (Playlists playlists : model.getPlaylists())
            if(playlists.getPlaylistName().equals(playlistName))
                for (AudioModel audio : Database.getDatabase().getAudios())
                    if(audio.getIDCount()==ID)
                        playlists.getAudio().add(audio);
    }
    public String playAudio(int ID){
        String show = "";
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getIDCount()==ID){
                show = "Audio: " + audio.getAudioName() + "\tArtis: " + audio.getArtistName() + "\tLikes: " + audio.getLikeCount() +"\nGenre: " + audio.getGenre() + "\tRelease Date: " + audio.getReleaseDate() + "\tID: " + audio.getIDCount();
                audio.setPlayedCount(audio.getPlayedCount() + 1);
                model.Count++;
                model.getPlayCount().put(audio,model.Count);
            }

        return show;
    }
    public void likeAudio(int ID){
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getIDCount()==ID){
                audio.setLikeCount(audio.getLikeCount() + 1);
                break;
            }
    }
    public String showLyric(int ID){
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getIDCount()==ID)
                if(audio.getClass().equals(Music.class))
                    return ((Music) audio).getLyrics();

        return "Audio Not Found!";
    }
    public String searchAudio(String name){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getAudioName().equals(name) || audio.getArtistName().equals(name))
                show.append("Audio name: " + audio.getAudioName() + "\tArtist: " + audio.getArtistName() + "\t Genre: " + audio.getGenre() + "\n");

        return show.toString();
    }
    public void sortingAudioFilesBasedOnLikes(){

        for (int i = 0; i < Database.getDatabase().getAudios().size() - 1; i++) {
            int likeBefore = Database.getDatabase().getAudios().get(i).getLikeCount();
            for (int j = i + 1; j < Database.getDatabase().getAudios().size(); j++) {
                int likeAfter = Database.getDatabase().getAudios().get(j).getLikeCount();
                if(likeAfter > likeBefore){
                    int temp = likeAfter;
                    likeAfter = likeBefore;
                    likeBefore = temp;
                }
            }

        }

    }
    public void sortingAudioFilesBasedOnPlayCount(){

        for (int i = 0; i < Database.getDatabase().getAudios().size() - 1; i++) {
            int playCountBefore = Database.getDatabase().getAudios().get(i).getPlayedCount();
            for (int j = i + 1; j < Database.getDatabase().getAudios().size(); j++) {
                int playCountAfter = Database.getDatabase().getAudios().get(j).getPlayedCount();
                if(playCountAfter > playCountBefore){
                    int temp = playCountAfter;
                    playCountAfter = playCountBefore;
                    playCountBefore = temp;
                }
            }

        }

    }
    public String filterAudioFilesBasedOnArtist(String artistName){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios()) {
            if(audio.getArtistName().equals(artistName))
                show.append("Audio: "+audio.getAudioName() + "\tID: "+audio.getIDCount()+"\n");
        }
        return show.toString();
    }
    public String filterAudioFilesBasedOnGenres(Genre genre){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios()) {
            if(audio.getGenre().equals(genre))
                show.append("Audio: "+audio.getAudioName() +"\tArtist: "+audio.getArtistName() +"\tID: "+audio.getIDCount()+"\n");
        }
        return show.toString();
    }
    public String filterAudioFilesBasedOnDate(Date releaseDate){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getDate().getYear()==releaseDate.getYear()){
                show.append("Audio: "+audio.getAudioName() + "\tArtist: "+audio.getArtistName()+"\tID: "+audio.getIDCount()+"\n");
        }
        return show.toString();
    }
    public String filterAudioFilesBasedAmongDate(Date releaseDate1,Date releaseDate2){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios()) {
            if(releaseDate1.getYear() <= audio.getDate().getYear() && audio.getDate().getYear()<=releaseDate2.getYear())
                show.append("Audio: "+audio.getAudioName() + "\tArtist: "+audio.getArtistName()+"\tID: "+audio.getIDCount()+"\n");
        }
        return show.toString();
    }
    public void ArtistReport(String userName,String description){
        for (User user : Database.getDatabase().getUsers()) {
            if(user.getUsername().equals(userName)){
                ArtistModel artist =(ArtistModel) user;
                Database.getDatabase().getReports().add(new Report(model,artist,description));
                break;
            }
        }
    }
    public String showFollowing(){
        StringBuilder show = new StringBuilder();
        for (ArtistModel artist : model.getFallowingArtist())
            show.append("Artist: "+ artist.getFullName()+"\n");

        return show.toString();
    }
    public String showArtistList() {
        StringBuilder show = new StringBuilder();
        for (User user : Database.getDatabase().getUsers())
            if (user instanceof SingerModel || user instanceof PodcasterModel)
                show.append("Artist Name: ").append(user.getFullName()).append("\n");

        return show.toString();
    }
    public String showArtist(String userName){
        StringBuilder show = new StringBuilder();
        for (User user : Database.getDatabase().getUsers()) {
            if(user.getUsername().equals(userName)){
                ArtistModel artist = (ArtistModel) user;
                show.append("Artist Name: ").append(artist.getFullName()).append("\n");
                if(user instanceof SingerModel){
                    show.append("Albums Name:\n");
                    for (Album album : ((SingerModel) artist).getAlbums())
                        show.append(album.getAlbumName()).append("\n");
                }
                else{
                    show.append("Podcasts Name:\n");
                    for (Podcast podcast : ((PodcasterModel) artist).getPodcasts())
                        show.append(podcast.getAudioName()).append("\n");
                }
                break;
            }
        }
        return show.toString();
    }
    public void followArtist(String userName){
        Database database = Database.getDatabase();
        for (User user : database.getUsers()) {
            if (user.getUsername().equals(userName) && user instanceof ArtistModel) {
                ArtistModel artist = (ArtistModel) user;
                model.getFallowingArtist().add(artist);
                artist.getFollowers().add(model);
                break;
            }
        }
    }
    public String showPlaylists(){
        StringBuilder show = new StringBuilder();
        for (Playlists playlists : model.getPlaylists()) {
            show.append("Playlist name: ").append(playlists.getPlaylistName()).append("\n");
        }
        return show.toString();
    }
    public String selectPlaylist(String playlistName){
        StringBuilder show = new StringBuilder();
        for (Playlists playlists : model.getPlaylists()){
            if(playlists.getPlaylistName().equals(playlistName)){
                for (AudioModel audio :  playlists.getAudio()) {
                    show.append("Audio name: "+audio.getAudioName() + "\tArtist Name: " +audio.getArtistName()+"\n");
                }
            }
        }
        return show.toString();
    }
    public String getSuggestions(int n){
        StringBuilder show = new StringBuilder();
        int count=0;

        for (AudioModel audio : Database.getDatabase().getAudios()) {
            if(count<n) {
                if (audio.getGenre().equals(model.getFavoriteGenres()[0]) || audio.getGenre().equals(model.getFavoriteGenres()[1]) || audio.getGenre().equals(model.getFavoriteGenres()[2]) || audio.getGenre().equals(model.getFavoriteGenres()[3])) {
                    show.append("Audio name: " + audio.getAudioName() + "\tArtist name: " + audio.getArtistName() + "\tGenre: " + audio.getGenre() + "\n");
                    count++;
                }
            }
        }


        return show.toString();
    }
    public String showUserInfo(){
        return "Name: "+model.getFullName() + "\tUsername: " + model.getUsername() + "\tPhone number: " + model.getPhoneNumber() + "\nEmail: "+ model.getEmail() + "\tBorn: " + model.getBirthDate() ;
    }
    public String buyOrExtendSubscription(int day){
        if(model.getClass().equals(ListenerModel.class)){

            return check(day) + model.getClass();
        }
        else if(model.getClass().equals(PremiumModel.class)){
            ((PremiumModel) model).setRemainingSubscriptionDay(((PremiumModel)model).getRemainingSubscriptionDay()+day);
            return check(day);
        }
        return null;
    }
    private String check(int day){
        if(day==30) {
            if(model.getCredit()-PremiumSubscription.DAYS30.getSubPrize() < 0)
                return "Your credit is low!";
            PremiumModel per = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            per.setCredit(model.getCredit());
            per.setPlaylists(model.getPlaylists());
            per.setFavoriteGenres(model.getFavoriteGenres());
            model=per;
            per= (PremiumModel)model;
            ((PremiumModel) model).setRemainingSubscriptionDay(day);

            model.setCredit(model.getCredit() - PremiumSubscription.DAYS30.getSubPrize());
        }
        else if(day==60) {
            if(model.getCredit()-PremiumSubscription.DAYS60.getSubPrize() < 0)
                return "Your credit is low!";
            PremiumModel per = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            per.setCredit(model.getCredit());
            per.setPlaylists(model.getPlaylists());
            per.setFavoriteGenres(model.getFavoriteGenres());
            model=per;
            per= (PremiumModel)model;
            ((PremiumModel) model).setRemainingSubscriptionDay(day);

            model.setCredit(model.getCredit() - PremiumSubscription.DAYS60.getSubPrize());
        }
        else {
            if(model.getCredit()-PremiumSubscription.DAYS180.getSubPrize() < 0)
                return "Your credit is low!";
            PremiumModel per = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            per.setCredit(model.getCredit());
            per.setPlaylists(model.getPlaylists());
            per.setFavoriteGenres(model.getFavoriteGenres());
            model=per;
            per= (PremiumModel)model;
            ((PremiumModel) model).setRemainingSubscriptionDay(day);

            model.setCredit(model.getCredit() - PremiumSubscription.DAYS180.getSubPrize());
        }
        return "Done!";
    }
    public void increaseCredit(int value){
        model.setCredit(model.getCredit()+value);
    }
}
