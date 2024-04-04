package model;

import java.util.ArrayList;

public class Database {
    private final static Database data = new Database();

    private Database() {}

    public static Database getData() {
        return data;
    }
    private ArrayList<UserAccount> allUsers = new ArrayList<>();
    private ArrayList<Audio> allAudios = new ArrayList<>();
    private ArrayList<Report> reports = new ArrayList<>();


    public ArrayList<UserAccount> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers (ArrayList<UserAccount> allUsers){
        this.allUsers = allUsers;
    }

    public ArrayList<Audio> getAllAudios() {
        return allAudios;
    }

    public void setAllAudios(ArrayList<Audio> allAudios) {
        this.allAudios = allAudios;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}
