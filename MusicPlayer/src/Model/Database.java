package Model;

import java.util.ArrayList;

public class Database {
    private static Database database;
    private ArrayList<User> users;
    private ArrayList<AudioModel> audios;
    private ArrayList<Report> reports;
    private ArrayList<ArtistModel> artist ;
    private ArrayList<SingerModel> singer;
    private ArrayList<PodcasterModel> podcaster;
    private Database(){
        this.users = new ArrayList<>();
        this.audios = new ArrayList<>();
        this.reports = new ArrayList<>();
        this.artist = new ArrayList<>();
        this.singer = new ArrayList<>();
        this.podcaster = new ArrayList<>();
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

    public ArrayList<ArtistModel> getArtists() {
        return artist;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public void setPlaylists(ArrayList<ArtistModel> artist) {
        this.artist = Database.this.artist;
    }

    public ArrayList<ArtistModel> getArtist() {
        return artist;
    }

    public ArrayList<SingerModel> getSinger() {
        return singer;
    }

    public ArrayList<PodcasterModel> getPodcaster() {
        return podcaster;
    }
}
