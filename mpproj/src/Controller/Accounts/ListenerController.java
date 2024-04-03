package Controller.Accounts;

import Model.Accounts.AccountsModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Accounts.Artists.PodcasterModel;
import Model.Accounts.Artists.SingerModel;
import Model.Accounts.Listeners.FreeListenerModel;
import Model.Accounts.Listeners.ListenerModel;
import Model.Accounts.Listeners.PremiumListenerModel;
import Model.Accounts.Subscriptions;
import Model.Audios.*;
import Model.Database.Database;
import Model.Reports.ReportModel;

import java.util.*;

public class ListenerController {

    private ListenerModel user;

    private static ListenerController listenerController;

    private ListenerController(){}

    public static ListenerController getListenerController(){
        if(listenerController == null){
            listenerController = new ListenerController();
        }
        return listenerController;
    }
    public void loginListener(AccountsModel listener) {
        this.user = (ListenerModel) listener;
    }

    public StringBuilder showGenre(){
        StringBuilder message = new StringBuilder();
        message.append("\nGenres :\n");
        for (Genre genre : Genre.values()){
            message.append(genre.toString());
            message.append("\n\n");
        }

        return message;
    }

    public String addFavoriteGenres(String genres){
        ArrayList<Genre> favoriteGenre = new ArrayList<Genre>();
        String[] splitGenres = genres.split(",");
        for (String genre : splitGenres){
            favoriteGenre.add(Genre.valueOf(genre));
        }
        this.user.setFavoriteGenres(favoriteGenre);

        return "\nFavorite Genres added";
    }

    public String addNewFavoriteGenre(Genre genre){
        this.user.addFavoriteGenre(genre);
        return "\nNew Genre Added";
    }
    public String createPlaylist(String playListName){
        if (this.user instanceof FreeListenerModel listener){
            return freeListenerCreatePlaylist(listener , playListName);
        }

        if (this.user instanceof PremiumListenerModel listener){
            return premiumCreatePlaylist(listener , playListName);
        }

        return "\nsomething went wrong!";
    }

    private String freeListenerCreatePlaylist(FreeListenerModel listener , String playlistName){
            if(listener.getPlaylistCountLimit() <= listener.getPlaylistCount()){
                return "\nYou can not create more Playlist";
            }

            PlaylistModel playlist = new PlaylistModel(playlistName , listener.getUsername());
            listener.addPlaylist(playlist);
            listener.setPlaylistAudioCount(playlist);
            listener.setPlaylistCount(listener.getPlaylistCount() + 1);
            Database.getDatabase().addPlaylist(playlist);
            return "\nPlaylist created successfully";

    }

    private String premiumCreatePlaylist(PremiumListenerModel listener , String playlistName){
            PlaylistModel playlist = new PlaylistModel(playlistName , listener.getUsername());
            listener.addPlaylist(playlist);
            Database.getDatabase().addPlaylist(playlist);
            return "\nPlaylist created successfully";

    }

    public String addToPlaylist(String playlistName , int audioID){
        PlaylistModel  playlist = findPlaylistByName(playlistName);
        if (playlist == null){
            return "\nCould not find playlist";
        }

        AudioModel audio = findAudioByID(audioID);
        if (audio == null){
            return "\nCould not find playlist";
        }
        if (this.user instanceof FreeListenerModel listener){
            return freeAddToPlaylist(listener , playlist , audio);
        }

        if (this.user instanceof PremiumListenerModel){
            return premiumAddToPlaylist(playlist , audio);
        }

        return "\nSomething went wrong!";
    }

    private PlaylistModel findPlaylistByName(String playlistName){
        ArrayList<PlaylistModel> playlists = Database.getDatabase().getPlayLists();
        int playlistIndex = -1;
        for (PlaylistModel playlist : playlists){
            if(playlist.getPlayListName().equals(playlistName)){
                playlistIndex = playlists.indexOf(playlist);
                break;
            }
        }
        if(playlistIndex == -1){
            return null;
        }
        return playlists.get(playlistIndex);
    }

    private AudioModel findAudioByID(int audioID){
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        int audioIndex = -1;
        for (AudioModel audio : audios){
            if(audio.getID() == audioID){
                audioIndex = audios.indexOf(audio);
                break;
            }
        }
        if(audioIndex == -1){
            return null;
        }
        return audios.get(audioIndex);
    }
    private String premiumAddToPlaylist(PlaylistModel playlist , AudioModel audio){
        playlist.addAudio(audio);
        return "\nAudio added to playlist successfully";
    }

    private String freeAddToPlaylist(FreeListenerModel listener ,PlaylistModel playlist , AudioModel audio){
        if (listener.getPlaylistAudioCount(playlist) >= listener.getMaxAddToPlaylistLimit()){
            return "\nYou can not add more audio to playlist";
        }
        playlist.addAudio(audio);
        listener.addToPlaylistAudioCount(playlist);
        return "\nAudio added to playlist successfully";
    }
    public String playMusic(int audioID){
        AudioModel audio = findAudioByID(audioID);
        if(audio == null){
            return "\nCould not found audio";
        }
        this.user.addAudioPlayCount(audio);
        audio.setPlayCount(audio.getPlayCount() + 1);
        return "\n"+ audio.toString() +
                "\nGenre : "+ audio.getGenre() +
                "\nCover : "+ audio.getCover() +
                "\nRelease Time : "+ audio.getReleaseTime().toString();
    }

    public String showLyric(int audioID){
        AudioModel audio = findAudioByID(audioID);
        if (audio == null){
            return "\nCould not found audio";
        }
        if (audio instanceof MusicModel music){
            return music.getLyrics();
        }
        if (audio instanceof PodcastModel podcast){
            return podcast.getCaption();
        }
        return "\nSomething went wrong";
    }

    public String likeAudio(int audioID){
        AudioModel audio = findAudioByID(audioID);
        if (audio == null){
            return "\nCould not found audio";
        }
        audio.setLikeCount(audio.getLikeCount() + 1);
        this.user.addLikedAudio(audio);

        return "\nYou liked "+ audio.getAudioName() ;
    }

    public StringBuilder search(String search){
        StringBuilder message = new StringBuilder();

        ArrayList<ArtistModel> artists = findArtists();
        List<ArtistModel> searchArtist = artists.stream().filter(artist -> artist.getUsername().equals(search)).toList();
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();

        if(!(searchArtist.isEmpty())) {
            List<AudioModel> artistAudios = audios.stream().filter(audio -> audio.getArtistName().equals(search)).toList();
            message.append("\nArtists :\n");
            for (int i = 0; i < searchArtist.size(); i++) {
                message.append(i + 1);
                message.append(")\n");
                message.append(searchArtist.get(i).toString()).append("\n\n");
                message.append("\nArtist's audios :\n");
                for (int j = 0 ; j < artistAudios.size() ; j++) {
                    message.append(j + 1);
                    message.append(")\n");
                    message.append(artistAudios.get(j).toString());
                    message.append("\n\n");
                }
                message.append("\n\n");
            }
        }
        List<AudioModel> searchAudio = audios.stream().filter(audio -> audio.getAudioName().equals(search)).toList();

        if(!(searchAudio.isEmpty())) {
            message.append("\nAudios :\n");
            for (int i = 0; i < searchAudio.size(); i++) {
                message.append(i + 1);
                message.append(")\n");
                message.append(searchAudio.get(i).toString());
                message.append("\n\n");
            }
        }

        if(searchArtist.isEmpty() && searchAudio.isEmpty()){
            message.append("\nNothing match found ! ");
        }
        return message;
    }

    private ArrayList<ArtistModel> findArtists(){
        ArrayList<ArtistModel> artists = new ArrayList<ArtistModel>();
        for (AccountsModel account : Database.getDatabase().getAccounts()){
            if (account instanceof ArtistModel artist){
                artists.add(artist);
            }
        }
        return artists;
    }
    public StringBuilder sortByLike(){
        StringBuilder message = new StringBuilder();
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> sortedAudios = audios.stream().sorted(Comparator.comparing(AudioModel::getLikeCount).reversed()).toList();

        message.append("\nMost liked audios :\n");
        for (int i = 0 ; i < sortedAudios.size() ; i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(sortedAudios.get(i).toString());
            message.append("\n\n");

        }

        return message;

    }public StringBuilder sortByPlayCount(){
        StringBuilder message = new StringBuilder();
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> sortedAudios = audios.stream().sorted(Comparator.comparing(AudioModel::getPlayCount).reversed()).toList();

        message.append("\nMost played audios :\n");
        for (int i = 0 ; i < sortedAudios.size() ; i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(sortedAudios.get(i).toString());
            message.append("\n\n");

        }

        return message;
    }

    public StringBuilder filterByArtist(String artistName){
        StringBuilder message = new StringBuilder();

        if (findArtistByUsername(artistName) == null){
            message.append("\nArtist does not exist");
            return message;
        }
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> filteredAudios = audios.stream().filter(audio -> audio.getArtistName().equals(artistName)).toList();

        message.append("\nAudios of ").append(artistName).append("\n");

        for (int i = 0 ; i < filteredAudios.size(); i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(filteredAudios.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder filterByGenre(String genre){
        StringBuilder message = new StringBuilder();
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> filteredAudios = audios.stream().filter(audio -> audio.getGenre().equals(genre)).toList();

        message.append("\nAudios of genre : ").append(genre).append("\n");

        for (int i = 0 ; i < filteredAudios.size(); i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(filteredAudios.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder filterByDate(String date1 , String date2){
        String[] splitDate1 = date1.split("-");

        String[] splitDate2 = date2.split("-");
        int[] startDate = new int[3];
        int[] endDate = new int[3];
        for (int i = 0; i < splitDate1.length; i++) {
            startDate[i] = Integer.parseInt(splitDate1[i]);
            endDate[i] = Integer.parseInt(splitDate2[i]);
        }
        Date start = new Date((startDate[0] - 1900) , (startDate[1] - 1) , startDate[2]);
        Date end = new Date((endDate[0] - 1900) , (endDate[1] - 1) , endDate[2]);

        StringBuilder message = new StringBuilder();

        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> filteredAudios = audios.stream().filter(audio -> (audio.getReleaseTime().equals(start) || audio.getReleaseTime().after(start)) && (audio.getReleaseTime().equals(end) || audio.getReleaseTime().before(end))).toList();

        message.append("\nAudios between : ").append(start.toString()).append(" and ").append(end.toString()).append("\n");
        for (int i = 0 ; i < filteredAudios.size(); i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(filteredAudios.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder showFollowing(){
        StringBuilder message = new StringBuilder();

        ArrayList<ArtistModel> followings = this.user.getFollowingList();

        message.append("\nFollowing list :\n");
        for (ArtistModel following : followings){
            message.append(following.toString());
            message.append("\n\n");
        }

        return message;
    }

    public String reportArtists(String artistName , String description){
        AccountsModel artist = findArtistByUsername(artistName);
        ReportModel report = new ReportModel(this.user , artist , description);
        Database.getDatabase().addReport(report);
        return "\nReport sent ";
    }

    public StringBuilder showArtists(){
        StringBuilder message = new StringBuilder();
        ArrayList<ArtistModel> artists = findArtists();

        message.append("\nArtists :\n");
        for (int i = 0 ; i < artists.size() ; i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(artists.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder showArtistDetails(String username){
        StringBuilder message = new StringBuilder();
        ArtistModel artist = (ArtistModel) findArtistByUsername(username);
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();

        if(artist == null){
            return message.append("\nCould not find artist");
        }
        message.append("\nUsername : ").append(artist.getUsername()).append("\n");
        message.append("\nFull Name : ").append(artist.getFullName()).append("\n");
        message.append("\nEmail Address : ").append(artist.getEmail()).append("\n");
        message.append("\nBio : ").append(artist.getBio()).append("\n");
        message.append("\nBirthday : ").append(artist.getBirthday().toString()).append("\n");

        message.append("\nArtist's Audios");
        for (int i = 0 ; i < Math.min(audios.size() , 3); i++){
            if (audios.get(i).getArtistName().equals(username)){
                message.append(i + 1);
                message.append(")\n");
                message.append(audios.get(i).toString());
                message.append("\n\n");
            }
        }
        return message;
    }


    private AccountsModel findArtistByUsername(String username){
        for (AccountsModel artist : findArtists()){
            if (artist.getUsername().equals(username)){
                return artist;
            }
        }
        return null;
    }
    public String followArtist(String username){
        ArtistModel artist = (ArtistModel) findArtistByUsername(username);
        if (artist == null){
            return "\nCould not find artist";
        }

        artist.addFollower(this.user);
        artist.setFollowersCount(artist.getFollowersCount() + 1);
        this.user.addToFollowingList(artist);
        return "\nArtist "+ artist.getFullName() + "\nfollowed\n";
    }

    public StringBuilder showUserPlaylists(){
        StringBuilder message = new StringBuilder();
        ArrayList<PlaylistModel> playlists = this.user.getPlaylists();

        message.append("\nYour playlists :\n");

        for (int i = 1 ; i <= playlists.size() ; i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(playlists.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder showAllPlaylists(){
        StringBuilder message = new StringBuilder();
        ArrayList<PlaylistModel> playlists  = Database.getDatabase().getPlayLists();

        message.append("\nPlaylists :\n");
        for (int i = 0 ; i < playlists.size() ; i++){
            message.append(i + 1);
            message.append(")\n");
            message.append(playlists.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder showPlaylistDetail(String playlistName){
        StringBuilder message = new StringBuilder();
        PlaylistModel playlist = findPlaylistByName(playlistName);
        if (playlist == null){
            return message.append("\nCould not found playlist");
        }

        message.append(playlist.toString()).append("\n\n");
        message.append("\nAudios of playlist :\n");
        for (AudioModel audio : playlist.getAudioList()){
            message.append(audio.toString());
            message.append("\n\n");
        }

        return message;
    }

    public StringBuilder suggestAudio(){
        return getSuggestAudio(10);
    }

    public StringBuilder suggestAudio(int count){
        return getSuggestAudio(count);
    }

    private StringBuilder getSuggestAudio(int audioCount){
        StringBuilder message = new StringBuilder();
        int audioAdded = 0;
        ArrayList<AudioModel> suggestAudios = new ArrayList<AudioModel>();

        ArrayList<AudioModel> mostPlayAudios = findListenerMostPlayAudios(this.user.getAllAudiosPlayCount() , 2);
        if (mostPlayAudios != null){
            for (AudioModel audio : mostPlayAudios){
                    suggestAudios.add(audio);
                    audioAdded++;
            }
        }

        ArrayList<AudioModel> likedAudios = findRandomLikeAudios(this.user.getLikedAudios() , 2);

        if (likedAudios != null){
            for (AudioModel audio : likedAudios){
                    suggestAudios.add(audio);
                    audioAdded++;
            }
        }

        ArrayList<AudioModel> followedArtistAudios = findFollowedArtistAudios(this.user.getFollowingList());

        if(followedArtistAudios != null){
            for (AudioModel audio : followedArtistAudios){
                suggestAudios.add(audio);
                audioAdded++;
            }
        }

        List<AudioModel> favoriteGereAudios = findGenreAudios(this.user.getFavoriteGenres());

        for (int i = 0 ; i < favoriteGereAudios.size() ; i++){
            if (audioAdded == audioCount){
                break;
            }
            suggestAudios.add(favoriteGereAudios.get(i));
        }
        message.append("\nSuggest Audios :\n");
        for (AudioModel audio : suggestAudios){
            message.append(audio.toString());
            message.append("\n\n");
        }


        return message;
    }

    private ArrayList<AudioModel> findListenerMostPlayAudios(Map<AudioModel , Integer> map , int audioCount){
        if (map.isEmpty()){
            return null;
        }
        Map.Entry<AudioModel , Integer>[] mapArray = map.entrySet().toArray(new Map.Entry[map.size()]);
        for (int i = 0 ; i < mapArray.length ; i++){
            for (int j = i ; j < mapArray.length ; j++){
                if (mapArray[i].getValue() < mapArray[j].getValue()){
                    Map.Entry<AudioModel , Integer> temp = mapArray[i];
                    mapArray[i] = mapArray[j];
                    mapArray[j] = temp;
                }
            }
        }

        int count = Math.min(audioCount, map.size());
        ArrayList<AudioModel> mostPlayAudios = new ArrayList<AudioModel>();
        for (int i = 0 ; i < count ; i++){
            mostPlayAudios.add(mapArray[i].getKey());
        }
        return mostPlayAudios;
    }

    private ArrayList<AudioModel> findRandomLikeAudios(ArrayList<AudioModel> likedAudios , int audioCount){
        if (likedAudios.isEmpty()){
            return null;
        }
        ArrayList<AudioModel> randomLikedAudios = new ArrayList<AudioModel>();

        int count = Math.min(likedAudios.size() , audioCount);
        for (int i = 0 ; i < count ; i++){
            int index = (int) (Math.random() * likedAudios.size());
            randomLikedAudios.add(likedAudios.get(index));
        }
        return randomLikedAudios;
    }


    private ArrayList<AudioModel> findFollowedArtistAudios(ArrayList<ArtistModel> followings){
        if (followings.isEmpty()){
            return null;
        }
        ArrayList<AudioModel> audios = new ArrayList<AudioModel>();

        ArrayList<ArtistModel> randomArtists = new ArrayList<ArtistModel>();
        for (int i = 0; i < Math.min(2, followings.size()); i++) {
            int index = (int) (Math.random() * followings.size());
            randomArtists.add(followings.get(index));
        }

        for (ArtistModel artist : randomArtists){
            if (artist instanceof SingerModel singer){
                ArrayList<AudioModel> singerMusics = singer.getMusicList();
                if (!(singerMusics.isEmpty())){
                    audios.addAll(findMostLikeAndPlayedAudio(singerMusics));
                }
            }
            if (artist instanceof PodcasterModel podcaster){
                ArrayList<AudioModel> podcasterPodcasts = podcaster.getPodcastList();
                if (!(podcasterPodcasts.isEmpty())){
                    audios.addAll(findMostLikeAndPlayedAudio(podcasterPodcasts));
                }
            }
        }
        return audios;
    }

    private ArrayList<AudioModel> findMostLikeAndPlayedAudio(ArrayList<AudioModel> audios){
        ArrayList<AudioModel> foundAudios = new ArrayList<AudioModel>();
        List<AudioModel> sortedMostLikeAudio = audios.stream().sorted(Comparator.comparing(AudioModel::getLikeCount)).toList();
        List<AudioModel> sortedMostPlayAudio = audios.stream().sorted(Comparator.comparing(AudioModel::getPlayCount)).toList();

        if (!(sortedMostLikeAudio.isEmpty())){
            foundAudios.add(sortedMostLikeAudio.getFirst());
        }
        if (!(sortedMostPlayAudio.isEmpty())){
            foundAudios.add(sortedMostPlayAudio.getFirst());
        }

        return foundAudios;
    }


    private List<AudioModel> findGenreAudios(ArrayList<Genre> genres){
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> genresAudios = new ArrayList<AudioModel>();

        for (Genre genre : genres){
            List<AudioModel> findGenreAudios = audios.stream().filter(audio -> Genre.valueOf(audio.getGenre()).equals(genre)).toList();
            genresAudios.addAll(findGenreAudios);
        }

        return genresAudios.stream().sorted(Comparator.comparing(AudioModel::getLikeCount).reversed()).toList();
    }
    public StringBuilder showAccountInformation(){
        StringBuilder message = new StringBuilder();

        if (this.user instanceof PremiumListenerModel premium){
            checkRemainSubscriptionDays();
            if (premium.getPremiumDayRemain() >= 0){
                message.append("\nSubscription Day remain : ").append(premium.getPremiumDayRemain());
                message.append("\nSubscription Expiration : ").append(this.user.getSubscriptionExpiration()).append("\n");
            }
            else {
                premiumExpiration();
            }
        }
        message.append(this.user.toString());
        message.append("\nEmail : ").append(this.user.getEmail());
        message.append("\nPhone Number : ").append(this.user.getPhoneNumber());
        message.append("\nCredit : ").append(this.user.getCredit());
        message.append("\nBirthday").append(this.user.getBirthday()).append("\n");

        return message;
    }

    private void premiumExpiration(){

        String[] informations = new String[5];
        informations[0] = this.user.getUsername();
        informations[1] = this.user.getPassword();
        informations[2] = this.user.getFullName();
        informations[3] = this.user.getEmail();
        informations[4] = this.user.getPhoneNumber();
        int[] birthDate = this.user.getBirthDate();
        double credit = this.user.getCredit();

        Database database = Database.getDatabase();
        database.getAccounts().remove(this.user);
        FreeListenerModel tempUser = new FreeListenerModel(informations[0] , informations[1] , informations[2] , informations[3] , informations[4] , birthDate);
        tempUser.setCredit(credit);
        database.addAccount(tempUser);
        this.user = tempUser;

    }
    public String buyOrRenewalSubscription(String subscriptionType){
        if (this.user instanceof FreeListenerModel){
            switch (subscriptionType){
                case "_30_DAYS" : return buySubscription(Subscriptions._30_DAYS);

                case "_60_DAYS" : return buySubscription(Subscriptions._60_DAYS);

                case "_180_DAYS" : return buySubscription(Subscriptions._180_DAYS);
            }
        }

        if (this.user instanceof PremiumListenerModel){
            switch (subscriptionType){
                case "_30_DAYS" : return renewalSubscription(Subscriptions._30_DAYS);

                case "_60_DAYS" : return renewalSubscription(Subscriptions._60_DAYS);

                case "_180_DAYS" : return renewalSubscription(Subscriptions._180_DAYS);
            }

        }

        return "\nSomething went wrong!";
    }


    private String buySubscription(Subscriptions subscription){
        if(subscription.getPrice() > this.user.getCredit()){
            return "\nYour account credit is insufficient";
        }
        this.user.setCredit(this.user.getCredit() - subscription.getPrice());

        String[] informations = new String[5];
        informations[0] = this.user.getUsername();
        informations[1] = this.user.getPassword();
        informations[2] = this.user.getFullName();
        informations[3] = this.user.getEmail();
        informations[4] = this.user.getPhoneNumber();
        int[] birthDate = this.user.getBirthDate();
        double credit = this.user.getCredit();

        Database database = Database.getDatabase();
        database.getAccounts().remove(this.user);

        PremiumListenerModel tempUser = new PremiumListenerModel(informations[0] , informations[1] , informations[2] , informations[3] , informations[4] , birthDate);
        tempUser.setCredit(credit);
        tempUser.setPremiumDayRemain(subscription.getDays());

        Date expirationDate = new Date();
        long seconds = expirationDate.getTime();
        seconds += (long) subscription.getDays() * 86400000;
        expirationDate.setTime(seconds);
        tempUser.setSubscriptionExpiration(expirationDate);

        database.addAccount(tempUser);
        this.user = tempUser;
        return "\nSubscription Activated";
    }

    private String renewalSubscription(Subscriptions subscriptions){
        PremiumListenerModel tempUser = (PremiumListenerModel) this.user;
        Date expirationDate = tempUser.getSubscriptionExpiration();
        long seconds = expirationDate.getTime();
        seconds += (long) subscriptions.getDays() * 86400000;
        expirationDate.setTime(seconds);
        tempUser.setSubscriptionExpiration(expirationDate);
        tempUser.setPremiumDayRemain(tempUser.getPremiumDayRemain() + subscriptions.getDays());
        return "\nSubscription updated";
    }

    public void checkRemainSubscriptionDays(){
        PremiumListenerModel tempUser = (PremiumListenerModel) this.user;
        Date expirationDate = tempUser.getSubscriptionExpiration();
        long seconds = expirationDate.getTime();
        seconds -= (long) 86400000;
        expirationDate.setTime(seconds);
        tempUser.setSubscriptionExpiration(expirationDate);
        tempUser.setPremiumDayRemain(tempUser.getPremiumDayRemain() -1);
    }

    public String increaseCredit(double money){
        this.user.setCredit(this.user.getCredit() + money);
        return "\nCredit increased";
    }

    public String showSubscriptions(){
        return Subscriptions._30_DAYS.toString() + " : "+ Subscriptions._30_DAYS.getPrice() + "\n"+
                Subscriptions._60_DAYS.toString() + " : "+ Subscriptions._60_DAYS.getPrice() + "\n"+
                Subscriptions._180_DAYS.toString() + " : "+ Subscriptions._180_DAYS.getPrice();
    }
}

