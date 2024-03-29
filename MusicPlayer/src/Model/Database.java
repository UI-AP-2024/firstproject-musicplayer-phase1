package Model;

import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<User> users;
    private ArrayList<AudioModel> audios;
    private ArrayList<Report> reports;
    private ArrayList<Playlists> playlists ;
    private Database(){
        this.users = new ArrayList<>();
        this.audios = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.playlists = new ArrayList<>();
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

    public ArrayList<Playlists> getPlaylists() {
        return playlists;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public void setPlaylists(ArrayList<Playlists> playlists) {
        this.playlists = playlists;
    }
}
