package Model;

import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<User> users;
    private ArrayList<AudioModel> audios;
    private ArrayList<Report> reports;

    private Database(){
        this.users = new ArrayList<>();
        this.audios = new ArrayList<>();
        this.reports = new ArrayList<>();

    }
    public static Database getDatabase(){
        if(database==null)
            database = new Database();
        return database;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<AudioModel> getAudios() {
        return audios;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setAudios(ArrayList<AudioModel> audios) {
        this.audios = audios;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }


    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

}
