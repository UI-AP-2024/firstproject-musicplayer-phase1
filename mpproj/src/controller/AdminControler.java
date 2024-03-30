package controller;

import model.Database.Database;
import model.Report;

import java.util.ArrayList;

public class AdminControler {
    public String lookReport(){
        StringBuilder result=new StringBuilder("reports :\n");
        ArrayList<Report> reports= Database.getDatabase().getReports();
        for(Report report:reports){
            if(report.getReportedArtist().getUsername().equals(artist.getUsername())){
                result.append(" reporter name : ").append(report.getReportingUser().getUsername()).append(" exeplenation: ").append(report.getDescription()).append("\n");
            }
        }
        return String.valueOf(result);
    }
}
