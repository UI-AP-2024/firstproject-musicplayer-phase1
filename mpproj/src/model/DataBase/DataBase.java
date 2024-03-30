package model.DataBase;

import model.Audio.Audio;
import model.Report;
import model.UserAcount.UserAcount;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<UserAcount> users;
    private ArrayList<Audio> audios;
    private ArrayList<Report> reports;
    private static DataBase dataBase;

    public static DataBase getDataBase() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    public ArrayList<UserAcount> getUsers() {
        return users;
    }

    public ArrayList<Audio> getAoudios() {
        return audios;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setUsers(ArrayList<UserAcount> users) {
        this.users = users;
    }

    public void setAoudios(ArrayList<Audio> audio) {
        this.audios = audio;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}
