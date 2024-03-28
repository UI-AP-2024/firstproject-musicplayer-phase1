package model;

public class Report
{
    private String reportingUser;
    private String reportedArtist;
    private String description;
    public Report(String reportingUser, String reportedArtist, String description)
    {
        this.reportingUser = reportingUser;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }
    public String getReportingUser() {
        return reportingUser;
    }
    public void setReportingUser(String reportingUser) {
        this.reportingUser = reportingUser;
    }
    public String getReportedArtist() {
        return reportedArtist;
    }
    public void setReportedArtist(String reportedArtist) {
        this.reportedArtist = reportedArtist;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
