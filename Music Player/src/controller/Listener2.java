package controller;
import model.Audio.Audio;
import model.Audio.Audio;
import model.Database;
import model.Playlist;
import model.Report;
import model.UserAccount.Artist.Artist;
import model.UserAccount.Listener.*;
import model.UserAccount.Listener.Listener;
import model.UserAccount.UserAccount;
import controller.Playlist2;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Listener2 extends UserAccount2
{
    private Listener listener;

    public Listener newListener(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord)
    {
        listener = newListener(email,name,phoneNumber,dateOfBirth,userName,passWord);
        Database.getDatabase().getUserAccounts().add(listener);
        return listener;
    }
    public Listener getListener() {
        return listener;
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public void makePlaylist(String name)
    {
        Playlist playlist = new Playlist(name);
        listener.getPlaylists().add(playlist);
    }
    private Audio audio ;
    private Playlist playlist;
    public void addAudioToPlaylist(String audioName)
    {
        audio.setName(audioName);
        playlist.getPlayList().add(audio);
    }
    private String status;
    public Audio getAudio() {
        return audio;
    }
    public void setAudio(Audio audio) {
        this.audio = audio;
    }
    public Playlist getPlaylist() {
        return playlist;
    }
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void likeAudio(String like)
    {
        if(like=="like")
        {
            int i = audio.getLikesCount();
            i++;
            audio.setLikesCount(i);
        }
    }
    private Artist artist;
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    private ArrayList<String> artists = new ArrayList<String>();
    private ArrayList<String> following = new ArrayList<String>();
    public ArrayList<String> getArtists() {
        return artists;
    }
    public void setArtists(ArrayList<String> artists) {
        this.artists = artists;
    }
    public ArrayList<String> getFollowing() {
        return following;
    }
    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }
    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public ArrayList showFollowing(String follow, String artistName)
    {
        if(follow.equals("follow"))
        {
            if(artist.getName() == artistName)
            {
                artists.add(artistName);
                artist.getFollowers().add(userAccount);
                for(int i=0; i<artists.size();i++)
                {
                    following.add(artists.get(i));
                }
                return following;
            }
        }
        return null;
    }
    private Report report;
    public Report getReport() {
        return report;
    }
    public void setReport(Report report) {
        this.report = report;
    }
    public void description(String description, String artistName)
    {
        report.setDescription(description);
        report.getReportingUser().setName(userAccount.getName());
        report.getReportedArtist().setName(artistName);
        Database.getDatabase().getReports().add(report);
    }
    private ArrayList<Artist> artistArrayList = new ArrayList<Artist>();
    public ArrayList<Artist> getArtistArrayList() {
        return artistArrayList;
    }
    public void setArtistArrayList(ArrayList<Artist> artistArrayList) {
        this.artistArrayList = artistArrayList;
    }
    public ArrayList<Artist> artistsList(Artist artist)
    {
        artistArrayList.add(artist);
        return artistArrayList;
    }
    private Playlist2 playlist2;
    public Playlist2 getPlaylist2() {
        return playlist2;
    }
    public void setPlaylist2(Playlist2 playlist2) {
        this.playlist2 = playlist2;
    }
    public ArrayList<Playlist> showPlaylist()
    {
        return playlist2.showPlaylist();
    }
    protected abstract void buyOrRenew(int money);
}
