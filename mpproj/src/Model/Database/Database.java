package Model.Database;

import Model.Accounts.AccountsModel;
import Model.Accounts.AdminModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Accounts.Listeners.ListenerModel;
import Model.Audios.AudioModel;
import Model.Audios.PlaylistModel;
import Model.Reports.ReportModel;
import Model.Reports.ReportModel;

import java.util.ArrayList;

public class Database {
    private ArrayList<AccountsModel> accounts;
    private ArrayList<AudioModel> audios;
    private ArrayList<PlaylistModel> playLists;
    private ArrayList<ReportModel> reports;

    private static Database database;

    private Database(){
        this.accounts = new ArrayList<AccountsModel>();
        this.audios = new ArrayList<AudioModel>();
        this.playLists = new ArrayList<PlaylistModel>();
        this.reports = new ArrayList<ReportModel>();
    }

    public static Database getDatabase(){
        if(database == null){
            database = new Database();
        }

        return database;
    }

    public ArrayList<AccountsModel> getAccounts(){
        return this.accounts;
    }
    
    public void addAccount(AccountsModel artist){
        this.accounts.add(artist);
    }

    public ArrayList<AudioModel> getAudios(){
        return this.audios;
    }
    
    public void addAudio(AudioModel audio){
        this.audios.add(audio);
    }

    public ArrayList<PlaylistModel> getPlayLists(){
        return this.playLists;
    }

    public void addPlaylist(PlaylistModel playlist){
        this.playLists.add(playlist);
    }

    public ArrayList<ReportModel> getReports(){
        return this.reports;
    }

    public void addReport(ReportModel report){
        this.reports.add(report);
    }
}
