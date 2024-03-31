package model;
import model.AcountUser.AccountUser;
import model.AcountUser.Artist.Artist;

public class Report {
    private static Report instance;
    private AccountUser reportingUser;
    private Artist reportedArtist;
    private String description;

    //*********************************************
    public Report(AccountUser reportingUser, Artist reportedArtist, String description) {
        this.reportingUser = reportingUser;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }

    public Report() {
    }
    //*********************************************

    public static Report getInstance() {
        if (instance == null) {
            instance = new Report();
        }
        return instance;
    }

    public AccountUser getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(AccountUser reportingUser) {
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
    //*********************************************
    @Override
    public String toString() {
        return "Report{" +
                "reportingUser=" + reportingUser +
                ", reportedArtist=" + reportedArtist +
                ", description='" + description + '\'' +
                '}';
    }
}