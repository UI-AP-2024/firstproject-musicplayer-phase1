package Model;

public class Report {
    private User user;
    private ArtistModel reportedArtist;
    private String description;

    public Report(User user, ArtistModel reportedArtist, String description) {
        this.user = user;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public ArtistModel getReportedArtist() {
        return reportedArtist;
    }

    public String getDescription() {
        return description;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setReportedArtist(ArtistModel reportedArtist) {
        this.reportedArtist = reportedArtist;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
