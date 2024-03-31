package model;

import model.UserAccount.Artist.Artist;
import model.UserAccount.UserAccount;

public class Report {
    private UserAccount reporterUser;
    private Artist reportedArtist;
    private String reportDescription;

    public Report(UserAccount reporterUser, Artist reportedArtist, String reportDescription) {
        this.reporterUser = reporterUser;
        this.reportedArtist = reportedArtist;
        this.reportDescription = reportDescription;
    }

    public UserAccount getReporterUser() {
        return reporterUser;
    }

    public Artist getReportedArtist() {
        return reportedArtist;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public void setReporterUser(UserAccount reporterUser) {
        this.reporterUser = reporterUser;
    }

    public void setReportedArtist(Artist reportedArtist) {
        this.reportedArtist = reportedArtist;
    }

    public void setReportDescription(String reportDescription) {
        this.reportDescription = reportDescription;
    }
}
