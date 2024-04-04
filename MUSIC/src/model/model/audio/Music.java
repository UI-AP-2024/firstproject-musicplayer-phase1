package model.model.audio;


public class Music extends Audio {
    private String musicText;

    public String getMusicText() {
        return musicText;
    }

    public void setMusicText(String musicText) {
        this.musicText = musicText;
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicText='" + musicText + '\'' +
                "} " + super.toString();
    }
}
