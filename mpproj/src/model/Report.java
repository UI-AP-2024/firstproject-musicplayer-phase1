package model;

public class Report {
    private UserAccount user;
    private Artist artist;
    private String description;

    public String toString(){
        String result = "";
        result += "Reporting user: "+getUser().getUserName()+"\tReported artist: "+getArtist().getUserName()+"\nDescription: "+getDescription();
    }

    public Report(UserAccount user, Artist artist, String description) {
        this.user = user;
        this.artist = artist;
        this.description = description;
    }

    public UserAccount getUser() {
        return user;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
