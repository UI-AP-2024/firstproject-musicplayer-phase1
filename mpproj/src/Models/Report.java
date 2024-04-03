package Model;

public class
Report {
    private UserAccount userReporter;
    private Artist reportedArtist;
    private String description;

    public Report(UserAccount userReporter, Artist reportedArtist, String description) {
        this.description = description;
        this.userReporter = userReporter;
        this.reportedArtist = reportedArtist;
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

    public UserAccount getUserReporter() {
        return userReporter;
    }

    public void setUserReporter(UserAccount userReporter) {
        this.userReporter = userReporter;
    }

    public String toString() {
        return "\nReporter User: " + getUserReporter().getUserName() + "\nReported Artist: " +
                getReportedArtist().getUserName() + "\nDescription: " + getDescription()+"\n\n";
    }
}
