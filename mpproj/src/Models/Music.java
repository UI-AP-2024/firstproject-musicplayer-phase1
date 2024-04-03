package Model;

public class Music extends Audio {
    private String musicText;

    public Music(String audioName, String artistName
            , Genre genre, String audioLink, String cover, int year, int month, int day,
                 String musicText) {
        super( audioName, artistName, genre, audioLink, cover, year, month, day);
        this.musicText = musicText;
    }

    public String getMusicText() {
        return musicText;
    }

    public void setMusicText(String musicText) {
        this.musicText = musicText;
    }
@Override
    public String toString() {
        return super.toString() + "Music Text:" + getMusicText()+"\n";
    }
}
