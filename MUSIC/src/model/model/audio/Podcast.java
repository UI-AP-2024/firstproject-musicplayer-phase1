package model.model.audio;

// پادکست
public class Podcast extends Audio {
    private String caption;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "caption='" + caption + '\'' +
                "} " + super.toString();
    }
}
