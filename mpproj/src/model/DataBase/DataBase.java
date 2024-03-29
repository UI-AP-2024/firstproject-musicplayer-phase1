package model.DataBase;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<Users> users;
    private ArrayList<Aoudios> aoudios;
    private ArrayList<Reports> reports;
    private static DataBase dataBase;

    public static DataBase getDataBase() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    public ArrayList<Aoudios> getAoudios() {
        return aoudios;
    }

    public ArrayList<Reports> getReports() {
        return reports;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public void setAoudios(ArrayList<Aoudios> aoudios) {
        this.aoudios = aoudios;
    }

    public void setReports(ArrayList<Reports> reports) {
        this.reports = reports;
    }
}
