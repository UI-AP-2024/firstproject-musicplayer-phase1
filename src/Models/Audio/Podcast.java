package Models.Audio;
import Models.Genre;

import java.time.LocalDate;

public class Podcast extends Audio {
    private String caption;

    public Podcast(int id, String fileName, String artistName, int playCount, int likesCount, LocalDate publishDate,
                   Genre genre, String audioLink, String cover, String caption) {
        super(id, fileName, artistName, playCount, likesCount, publishDate, genre, audioLink, cover);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
