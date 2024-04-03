package Controller;

import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class ListenerController {
    private static ListenerController listenerController;
    private Listener listener;
    public PremiumListener premiumListener;
    ;

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    private ListenerController() {
    }

    static int playlistcount = 0;
    static int audioCount = 0;

    public static ListenerController getListenerController() {
        if (listenerController == null)
            listenerController = new ListenerController();
        return listenerController;
    }


    public String addFavoriteGenres(String genres, Listener listener) {
        int genreCount=0;
        String[] inpts=genres.split(",");
        for (int i = 0; i < inpts.length; i++) {
            if(genreCount<4) {
                listener.getFavoriteGenres().add(Genre.valueOf(inpts[i].trim()));
                genreCount++;
            }else {
                 return "First 4 genres added to your favorite genres!";
            }
        }
        return "Genres Added!";
    }
    public String seeRecommendedAudios(String recommendNum) {
        int i = 1;
        boolean isAdd;
        int suggustedCount = 0;
        StringBuilder sb = new StringBuilder();
            for (Audio audio : DataBase.getDataBase().getAudioList()) {
                isAdd=false;
                    for (Genre genre : listener.getFavoriteGenres()) {
                        if (genre.equals(audio.getGenre())) {
                            sb.append(i).append("=>\nAudio ID:").append(audio.getId()).append("\nName: ").append(audio.getAudioName())
                                    .append("\nArtist:").append(audio.getArtistName()).append("\nGenre: ").append(audio.getGenre()).append("\n\n");
                            i++;
                            suggustedCount++;
                            isAdd = true;
                        }
                    }
                if (!isAdd) {
                    for (Artist artist : listener.getFollowingsArtist()) {
                        if (artist.getUserName().equals(audio.getArtistName())) {
                            sb.append(i).append("=>\nAudio ID:").append(audio.getId()).append("\nName: ").append(audio.getAudioName())
                                    .append("\nArtist:").append(audio.getArtistName()).append("\nGenre: ").append(audio.getGenre()).append("\n\n");
                            i++;
                            suggustedCount++;
                            isAdd=true;
                        }
                    }
                }if(!isAdd){
                    for (Map.Entry<Audio,Integer> e:sortPlayMap()){
                        if(e.getKey()==audio){
                            sb.append(i).append("=>\nAudio ID:").append(audio.getId()).append("\nName: ").append(audio.getAudioName())
                                    .append("\nArtist:").append(audio.getArtistName()).append("\nGenre: ").append(audio.getGenre()).append("\n\n");
                            i++;
                            isAdd=true;
                            suggustedCount++;
                        }
                    }
                }if(!isAdd){
                    for (Audio likedAudio:listener.getLikedAudios()){
                        if(likedAudio.getId().equals(audio.getId())){
                            sb.append(i).append("=>\nAudio ID:").append(audio.getId()).append("\nName: ").append(audio.getAudioName())
                                    .append("\nArtist:").append(audio.getArtistName()).append("\nGenre: ").append(audio.getGenre()).append("\n\n");
                            i++;
                            suggustedCount++;
                        }
                    }
                }
                if(suggustedCount==Integer.parseInt(recommendNum))
                    break;
            }
        return String.valueOf(sb);
        }

    public String makePlaylist_P(String name) {
        Playlist playlist = new Playlist(name, listener.getUserName());
        listener.getPlaylists().add(playlist);
        return "Playlist "+name+" added to your playlists";
    }

    public String makePlaylist_F(String name) {
        if(!checkRepeatedPlaylist(name)) {
            if (playlistcount < FreeListener.getMaxPlaylists()) {
                Playlist playlist = new Playlist(name, listener.getUserName());
                listener.getPlaylists().add(playlist);
                playlistcount++;
                return "Playlist " + name + " added to your playlists";
            }
            return "You have limit for add a new playlist";
        }
        return "You have a playlist with the same name!";
    }
    public boolean checkRepeatedPlaylist(String playlistName){
        for (Playlist plylist:listener.getPlaylists()){
            if(plylist.getPlaylistName().equals(playlistName)){
                return true;
            }
        }
        return false;
    }
    public String searchAudio(String name) {
        StringBuilder audios = new StringBuilder();
        boolean isFound=false;
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (name.equals(audio.getAudioName()) ) {
                audios.append(audio);
                isFound=true;
            }if (name.equals(audio.getArtistName())){
                for (UserAccount user : DataBase.getDataBase().getUsersList()) {
                    if (user.getUserName().equals(audio.getArtistName())) {
                        audios.append("Atrist Info : \n").append(user);
                        return String.valueOf(audios);
                    }
                }
            }
        }
        if(isFound) {
            return String.valueOf(audios);
        }else{
            return "not found!";
        }
    }

    public String addAudio_P(String playlistName, String audioID) {
        for (Playlist playlist : listener.getPlaylists()) {
            if (playlist.getPlaylistName().equals(playlistName)) {
                for (Audio audio : DataBase.getDataBase().getAudioList()) {
                    if (audio.getId().equals(audioID)) {
                        if (playlist.getAudioList().contains(audio)) {
                            return "This audio is already exists in this playlist!";
                        } else {
                            playlist.getAudioList().add(audio);
                            return audioID+" Added to your playlist";
                        }
                    }
                }
            }
        }
        return audioID+" is not found!";
    }

    public String addAudio_F(String playlistName, String audioID) {
        if (audioCount < FreeListener.getMaxAudio()) {
            audioCount++;
            return (addAudio_P(playlistName, audioID));
        }
        return "You have Limit for adding Audio";
    }


    public String playAudio(String audioID) {
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getId().equals(audioID)) {
                updatePlayMap(audio);
                audio.setPlaycounter(audio.getPlaycounter()+1);
                ArtistController.getArtistController().calculateArtistSalary(audio,audio.getArtistName());
                return String.valueOf(audio);
            }
        }
        return "Audio not found!";
    }
    public void updatePlayMap(Audio audio){
        if(listener.getPlayMap().containsKey(audio)){
            int value=listener.getPlayMap().get(audio)+1;
            listener.getPlayMap().put(audio,value);
        }else{
            listener.getPlayMap().put(audio,1);
        }
    }
    public Map.Entry<Audio, Integer>[] sortPlayMap(){
        Map.Entry<Audio,Integer> []arrMap=listener.getPlayMap().entrySet().toArray(new Map.Entry[listener.getPlayMap().size()]);
        for (int i = 0; i < listener.getPlayMap().size()-1; i++) {
            for (int j = 0; j < listener.getPlayMap().size()-i-1; j++) {
                if(arrMap[j].getValue()<arrMap[j+1].getValue()){
                    Map.Entry<Audio,Integer> temp = arrMap[j];
                    arrMap[j] = arrMap[j + 1];
                    arrMap[j + 1] = temp;
                }
            }
        }
        return arrMap;
    }
    public String likeAudio(String audioID) {
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getId().equals(audioID)) {
                if(!checkSecondLike(audio)) {
                    audio.setLikeConter(audio.getLikeConter() + 1);
                    audio.getLikers().add(listener);
                    listener.getLikedAudios().add(audio);
                    return audioID + " Liked!";
                }else {
                    return "You have liked this audio before!";
                }
            }
        }
        return audioID + " Not Found!";
    }
    public boolean checkSecondLike(Audio audio){
        for(Listener liker:audio.getLikers()){
            if(liker.getUserName().equals(listener.getUserName())){
                return true;
            }
        }
        return false;
    }

    public String sortAudios(String sortKind) {
        StringBuilder sorted = new StringBuilder();
        int i=1;
        ArrayList<Audio> sortedAudios = DataBase.getDataBase().getAudioList();
        switch (sortKind) {
            case "L":
                sorted.append("Sorted Base On Likes:\n");
                sortedAudios.sort(Comparator.comparing(Audio::getLikeConter));
                Collections.reverse(sortedAudios);
                break;
            case "P":
                sorted.append("Sorted Base On Plays:\n");
                sortedAudios.sort(Comparator.comparing(Audio::getPlaycounter));
                Collections.reverse(sortedAudios);
                break;
        }
        for (Audio audio : sortedAudios) {
            sorted.append("\nAudio ").append(i).append(".\nName: ").append(audio.getAudioName())
                    .append("\nArtist: ")
                    .append(audio.getArtistName()).append("\nID: ").append(audio.getId()).append("\nLikes: ")
                    .append(audio.getLikeConter()).append("\nPlays :").append(audio.getPlaycounter()).append("\n");
            i++;
        }
        return String.valueOf(sorted);
    }
    public String findAudioByArtist(String atrtistName) {
        int i=1;
        StringBuilder audios = new StringBuilder();
        audios.append("*Audios By ").append(atrtistName).append(":\n");
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getArtistName().equals(atrtistName)) {
                audios.append(i).append(".\n").append(audio).append("\n");
                i++;
            }
        }
        return String.valueOf(audios);
    }

    public String findAudioByGenre(String genreName) {
        StringBuilder audios = new StringBuilder();
        int i=1;
        audios.append("*Audios in Genre ").append(genreName).append(":\n");
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getGenre() == Genre.valueOf(genreName)) {
                audios.append(i).append(".\n").append(audio).append("\n");
                i++;
            }
        }
        return String.valueOf(audios);
    }

    public String findAudioByDate(String Year,String month,String day) {
        StringBuilder audios = new StringBuilder();
        int i=1;
        String date=String.format("%s-%s-%s",Year,month,day);
        audios.append("*Audios in this Date ").append(":\n\n");
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getReleaseDate().toString().equals(date)) {
                audios.append(i).append("=>\n").append(audio).append("\n");
                i++;
            }
        }
        return String.valueOf(audios);
    }

    public String getPremium(PremiumPack pack) {
        if (pack.getCash() < listener.getAccountCredit()) {
            if (listener instanceof FreeListener) {
                premiumListener = new PremiumListener(listener.getUserName(), listener.getPassword(),
                        listener.getFirstAndLastName(), listener.getEmail(), listener.getPhoneNumber(), listener.getBirthDate().getYear()
                        , listener.getBirthDate().getMonthValue(), listener.getBirthDate().getDayOfMonth(), listener.getAccountCredit() - pack.getCash(),
                        LocalDate.now().plusDays(pack.getDays()), listener.getFavoriteGenres(), pack.getDays());
                DataBase.getDataBase().getUsersList().remove(listener);
                DataBase.getDataBase().getUsersList().add(premiumListener);
                listener=premiumListener;

            } else {
                listener.setAccountCredit(listener.getAccountCredit() - pack.getCash());
                ((PremiumListener)listener).setRemainDays(((PremiumListener)listener).getRemainDays() + pack.getDays());
                listener.setFinishTime(listener.getFinishTime().plusDays(pack.getDays()));
            }
            return pack+" Pack applyed for you successfully!";
        }
        return "Your Balance in not enough for this pack";
    }
    public void updateRemainDays() {
        if (listener instanceof PremiumListener) {
            listener.setFinishTime(listener.getFinishTime().minusDays(1));
            ((PremiumListener) listener).setRemainDays(((PremiumListener) listener).getRemainDays() - 1);
            if(((PremiumListener) listener).getRemainDays()==0){
               FreeListener freeListener=new FreeListener(listener.getUserName(), listener.getPassword(),
                       listener.getFirstAndLastName(), listener.getEmail(), listener.getPhoneNumber(), listener.getBirthDate().getYear()
                       , listener.getBirthDate().getMonthValue(), listener.getBirthDate().getDayOfMonth(),listener.getAccountCredit()
               ,listener.getFavoriteGenres());
               setListener(freeListener);
                DataBase.getDataBase().getUsersList().remove(listener);
                DataBase.getDataBase().getUsersList().add(freeListener);
            }
        }
    }

    public String seeArtists() {
        StringBuilder artists = new StringBuilder();
        int i=1;
        for (UserAccount user : DataBase.getDataBase().getUsersList()) {
            if(user instanceof Artist) {
                artists.append("Artist ").append(i).append("=>\n").append("UserName: "+user.getUserName()).append("\n")
                        .append("Name: "+user.getFirstAndLastName()).append("\n");
                if(user instanceof Singer) {
                    int j=1;
                    artists.append("Albums Name:\n");
                    for (Album album : ((Singer) user).getAlbumsList()) {
                        artists.append(j).append("-").append(album.getAlbumName()).append("\n");
                        j++;
                    }
                    artists.append("\n");
                }else{
                    int j=1;
                    artists.append("Podcasts Name:\n");
                    for (Podcast podcast : ((Podcaster) user).getPodcastsList()) {
                        artists.append(j).append("-").append(podcast.getAudioName()).append("\n");
                        j++;
                    }
                    artists.append("\n");
                }
                i++;
            }
        }
        return String.valueOf(artists);
    }

    public String seeArtistsAudios(String username) {
        StringBuilder audios = new StringBuilder();
        for (UserAccount user : DataBase.getDataBase().getUsersList()) {
            if (user.getUserName().equals(username)) {
                audios.append("Atrist Info : \n").append(user);
                if (user instanceof Singer) {
                    for (Album album : ((Singer) user).getAlbumsList()) {
                        audios.append("\nAlbum ").append(album.getAlbumName()).append("=>\n");
                        audios.append(showMusics(album));
                    }
                } else {
                    int i=1;
                    for (Podcast podcast : ((Podcaster) user).getPodcastsList()) {
                        audios.append("\nPodcast ").append(i).append(".\n").append(podcast).append("\n");
                        i++;
                    }
                }
            }
        }
        return String.valueOf(audios);
    }

    public String showMusics(Album album) {
        StringBuilder musics = new StringBuilder();
        int i = 1;
        for (Music music : album.getMusicsList()) {
            musics.append("\nMusic ").append(i).append(".\n").append(music);
            i++;
        }
        return String.valueOf(musics);
    }

    public String followArtist(String artistName) {
        for (UserAccount user : DataBase.getDataBase().getUsersList()) {
            if (user.getUserName().equals(artistName)) {
                ((Artist)user).getFollowersList().add(listener);
                listener.getFollowingsArtist().add((Artist) user);
                return "You followed " + artistName + " sucsesfully";
            }
        }
        return "Artist not found!";

    }

    public String showPlaylists() {
        StringBuilder playlists = new StringBuilder();
        for (Playlist playlist : listener.getPlaylists()) {
            playlists.append(playlist).append("\n");
        }
        return String.valueOf(playlists);
    }

    public String showAudiosOfPlaylist(String playlistName) {
        int i=1;
        StringBuilder playlists = new StringBuilder();
        for (Playlist playlist : listener.getPlaylists()) {
            if (playlist.getPlaylistName().equals(playlistName)) {
                playlists.append("*Playlist Name: ").append(playlist.getPlaylistName()).append("\n");
                for (Audio audio : playlist.getAudioList()) {
                    playlists.append("Audio").append(i).append("\n").append(audio).append("\n");
                    i++;
                }
            }
        }
        return String.valueOf(playlists);
    }

    public String showLyrics(String audioID) {
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getId().equals(audioID)) {
                if (audio instanceof Music) {
                    return "Lyric:\n" + ((Music) audio).getMusicText();
                } else {
                    return "Podcast doesn't have lyric!\n" ;
                }
            }
        }
        return audioID+" Not Found!";
    }
    public String showFollowings(){
        int i=1;
        StringBuilder followinglist=new StringBuilder();
        for (Artist artist:listener.getFollowingsArtist()){
            followinglist.append("Artist").append(i).append("=>\n").append(artist).append("\n");
            i++;
        }
        return String.valueOf(followinglist);
    }
    public String reportArtist(String artistUsername,String explanation){
        Artist repoertedArtist = null;
        for (UserAccount user:DataBase.getDataBase().getUsersList()){
            if(artistUsername.equals(user.getUserName())){
                repoertedArtist=(Artist) user;
            }
        }
        Report report=new Report(listener,repoertedArtist,explanation);
        DataBase.getDataBase().getReportsList().add(report);
        return "Report Completed!";
    }
    public String addCredit(long value){
        listener.setAccountCredit(listener.getAccountCredit()+value);
        return "Credit Added to your Account";
    }

}