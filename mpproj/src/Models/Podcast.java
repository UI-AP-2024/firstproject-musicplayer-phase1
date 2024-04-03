package Model;

public class Podcast extends Audio {
    private String caption;

    public Podcast(String audioName, String artistName
            , Genre genre, String audioLink, String cover, int year, int month, int day,
                   String caption) {
        super( audioName, artistName, genre, audioLink, cover, year, month, day);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    @Override
    public String toString(){
        return super.toString()+"Caption: "+getCaption()+"\n\n";
    }
}
