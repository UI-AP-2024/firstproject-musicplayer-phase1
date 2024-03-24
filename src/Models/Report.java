package Models;

import Models.User.Artist;
import Models.User.User;

public class Report {
    private User reportingUser;
    private Artist reportedArtist;
    private String description;

    public Report(User reportingUser, Artist reportedArtist, String description) {
        this.reportingUser = reportingUser;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }
    public User getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(User reportingUser) {
        this.reportingUser = reportingUser;
    }

    public Artist getReportedArtist() {
        return reportedArtist;
    }

    public void setReportedArtist(Artist reportedArtist) {
        this.reportedArtist = reportedArtist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
