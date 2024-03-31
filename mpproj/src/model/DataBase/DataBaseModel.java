package model.DataBase;

import model.Audio.AudioModel;
import model.ReportModel;
import model.UserAccount.UserAccountModel;

import java.util.ArrayList;
import java.util.Map;

public class DataBaseModel {

    private ArrayList<UserAccountModel> users;
    private ArrayList<AudioModel> audios;
    private ArrayList<ReportModel> reports;
    private static DataBaseModel dataBase;

    public DataBaseModel() {
        this.users = new ArrayList<>();
        this.audios = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    public static DataBaseModel getDataBase() {
        if (dataBase == null)
            dataBase = new DataBaseModel();
        return dataBase;
    }
    public ArrayList<UserAccountModel> getUsers() {
        return users;
    }

    public ArrayList<AudioModel> getAudios() {
        return audios;
    }

    public ArrayList<ReportModel> getReports() {
        return reports;
    }

    public void setUsers(ArrayList<UserAccountModel> users) {
        this.users = users;
    }

    public void setAudios(ArrayList<AudioModel> audio) {
        this.audios = audio;
    }

    public void setReports(ArrayList<ReportModel> reports) {
        this.reports = reports;
    }
}
