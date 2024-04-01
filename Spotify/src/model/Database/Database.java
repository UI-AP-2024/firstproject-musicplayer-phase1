package model.Database;
import model.UserAccounts.userAccount;
import model.Audio.Audio;
import model.Report.Report;

import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<userAccount> allUsersList;
    private ArrayList<Audio> allAudiosList;
    private ArrayList<Report> reportsList;

    private Database(){
        this.allUsersList = new ArrayList<userAccount>();
        this.allAudiosList = new ArrayList<Audio>();
        this.reportsList = new ArrayList<Report>();
    }

    public static Database getDatabase() {
        if (database==null){
            database = new Database();
        }
        return database;
    }

    public ArrayList<userAccount> getAllUsersList() {
        return allUsersList;
    }

    public void setAllUsersList(ArrayList<userAccount> allUsersList) {
        this.allUsersList = allUsersList;
    }

    public ArrayList<Audio> getAllAudiosList() {
        return allAudiosList;
    }

    public void setAllAudiosList(ArrayList<Audio> allAudiosList) {
        this.allAudiosList = allAudiosList;
    }

    public ArrayList<Report> getReportsList() {
        return reportsList;
    }

    public void setReportsList(ArrayList<Report> reportsList) {
        this.reportsList = reportsList;
    }
}
