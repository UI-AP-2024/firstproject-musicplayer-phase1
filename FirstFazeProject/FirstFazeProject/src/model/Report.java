package model;

public class Report {
    private UserAccount reporterUser;
    private Artist reportedArtist;
    private String explanation;

    public Report(UserAccount reporterUser, Artist reportedArtist, String explanation) {
        this.reporterUser = reporterUser;
        this.reportedArtist = reportedArtist;
        this.explanation = explanation;
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
        return explanation;
    }

    public void setCaption(String explanation) {
        this.explanation = explanation;
    }

    @Override
    public String toString(){
        String result = null;
        result += "Reporter user : "+this.reporterUser+"\r\n"+"Reported Artist : "+this.reportedArtist+"\r\n"
                +"Explanation : " +this.explanation;
        return result;
    }
}
