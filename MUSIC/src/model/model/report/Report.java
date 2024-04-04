package model.model.report;

import model.model.user.Artist;
import model.model.user.User;

// گزارش
public class Report {
    private User reportedBy;
    private Artist reportedArtist;
    private String description;

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedAt) {
        this.reportedBy = reportedAt;
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
    public String toString() {
        return "Report{" +
                "reportedAt=" + reportedBy +
                ", reportedArtist=" + reportedArtist +
                ", description='" + description + '\'' +
                '}';
    }
}
