package Models.Data;

import Models.Audio.Audio;
import Models.Report;
import Models.User.User;

import java.util.ArrayList;

public class Database {
    private static Database instance;
    private ArrayList<User> users;
    private ArrayList<Report> reports;
    private ArrayList<Audio> audios;
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }

    private static Database getInstance()
    {
        if(instance==null) instance = new Database();
        return instance;
    }

    public Database() {
        users = new ArrayList<User>();
        reports = new ArrayList<Report>();
        audios = new ArrayList<Audio>();
    }
}
