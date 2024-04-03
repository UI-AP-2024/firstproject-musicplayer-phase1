package Models;

import Models.User.Artist;
import Models.User.Listener;
import Models.User.User;

public class Report {
    private Listener reportingUser;
    private Artist reportedArtist;
    private String description;

    public Report(Listener reportingUser, Artist reportedArtist, String description) {
        this.reportingUser = reportingUser;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }
    public Listener getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(Listener reportingUser) {
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
