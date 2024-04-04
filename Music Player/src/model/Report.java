package model;

import model.UserAccount.Artist.Artist;
import model.UserAccount.UserAccount;

public class Report
{
    private UserAccount reportingUser;
    private Artist reportedArtist;
    private String description;
    public Report(UserAccount reportingUser, Artist reportedArtist, String description) {
        this.reportingUser = reportingUser;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }
    public UserAccount getReportingUser() {
        return reportingUser;
    }
    public void setReportingUser(UserAccount reportingUser) {
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
    @Override
    public String toString()
    {
        return "RiportingUser: "+getReportingUser()+"\t"+"ReportedArtist: "+getReportedArtist()+"\t"+"Report: "+getDescription();
    }
}
