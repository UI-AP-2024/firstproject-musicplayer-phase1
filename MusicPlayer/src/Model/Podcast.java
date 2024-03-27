package Model;
public class Podcast extends AudioModel{
    private String caption;
    public Podcast(String audioName, String artistName, Genre genre, String audioLink, String cover, String caption) {
        super(audioName, artistName, genre, audioLink, cover);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
