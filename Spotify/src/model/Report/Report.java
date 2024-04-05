package model.Report;
import model.UserAccounts.*;
import model.UserAccounts.Artist.Artist;
public class Report {
    private userAccount reporterUser;
    private Artist reportingArtist;
    private String description;
    public Report(userAccount reporterUser,Artist reportingArtist,String description){
        this.reporterUser = reporterUser;
        this.reportingArtist = reportingArtist;
        this.description = description;
    }

    public userAccount getReporterUser() {
        return reporterUser;
    }

    public void setReporterUser(userAccount reporterUser) {
        this.reporterUser = reporterUser;
    }

    public Artist getReportingArtist() {
        return reportingArtist;
    }

    public void setReportingArtist(Artist reportingArtist) {
        this.reportingArtist = reportingArtist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString(){
        StringBuilder context = new StringBuilder();
        context.append("\nreporter user : ");
        context.append(reporterUser);
        context.append("\nreporting artist : ");
        context.append(reportingArtist);
        context.append("\nreport description : ");
        context.append(description);
        context.append("\n");
        return context.toString();
    }
}
