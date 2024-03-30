package model;

public class Report {
    private UserAccount reporterUser;
    private Artist reportedArtist;
    private String caption;

    public Report(UserAccount reporterUser, Artist reportedArtist, String caption) {
        this.reporterUser = reporterUser;
        this.reportedArtist = reportedArtist;
        this.caption = caption;
    }

    public UserAccount getReporterUser() {
        return reporterUser;
    }

    public void setReporterUser(UserAccount reporterUser) {
        this.reporterUser = reporterUser;
    }

    public Artist getReportedArtist() {
        return reportedArtist;
    }

    public void setReportedArtist(Artist reportedArtist) {
        this.reportedArtist = reportedArtist;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
