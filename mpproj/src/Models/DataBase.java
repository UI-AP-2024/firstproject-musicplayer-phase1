package Model;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<UserAccount> usersList ;
    private ArrayList<Audio> audioList ;
    private ArrayList<Report> reportsList ;

    private static DataBase dataBase;

    public static DataBase getDataBase(){
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    private DataBase() {
        usersList = new ArrayList<>();
        audioList = new ArrayList<>();
        reportsList = new ArrayList<>();
    }

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }

    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }

    public void setReportsList(ArrayList<Report> reportsList) {
        this.reportsList = reportsList;
    }

    public ArrayList<Report> getReportsList() {
        return reportsList;
    }

    public void setUsersList(ArrayList<UserAccount> usersList) {
        this.usersList = usersList;
    }

    public ArrayList<UserAccount> getUsersList() {
        return usersList;
    }


}
