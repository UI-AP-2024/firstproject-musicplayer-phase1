package Model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Audio {
    private String Id;
    private String audioName;
    private String artistName;
    private int playcounter;
    private int likeConter;
    private LocalDate releaseDate;
    private Genre genre;
    private String audioLink;
    private String cover;
    static int id=0;
    private ArrayList <Listener> likers;

    Audio(String audioName, String artistName
            , Genre genre, String audioLink, String cover, int year, int month, int day) {
        this.audioLink = audioLink;
        this.audioName = audioName;
        String audio="Aud0";
        String playlistID = String.format("0");
        int Id = Integer.valueOf(playlistID) + id;
        id++;
        this.Id=audio+Id;
        this.cover = cover;
        this.artistName = artistName;
        this.genre = genre;
        this.releaseDate = LocalDate.of(year, month, day);
        DataBase.getDataBase().getAudioList().add(this);
        likers=new ArrayList<>();
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getPlaycounter() {
        return playcounter;
    }

    public void setPlaycounter(int playcounter) {
        this.playcounter = playcounter;
    }

    public int getLikeConter() {
        return likeConter;
    }

    public void setLikeConter(int likeConter) {
        this.likeConter = likeConter;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public static void setId(int id) {
        Audio.id = id;
    }

    public ArrayList<Listener> getLikers() {
        return likers;
    }

    public void setLikers(ArrayList<Listener> likers) {
        this.likers = likers;
    }

    @Override
    public String toString(){
        return "ID:"+getId()+"\nAudioName: "+getAudioName()+"\nArtistName: "+getArtistName()+
            "\nPlays: "+getPlaycounter()+"\nLikes: "+getLikeConter()+
                "\nReleaseDate: "+getReleaseDate()+"\nGenre: "+getGenre()+"\nLink: "+getAudioLink()+"\nCover:"+getCover()
                +
                "\n";
    }
}
