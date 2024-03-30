package model;
import model.Audio.Audio;
import model.UserAccount.UserAccount;

import java.util.ArrayList;

public class Playlist
{
    private StringBuilder id;
    private int i = 1;
    private String name;
    private UserAccount userAccount;
    private String nameOfUserAccount;
    private ArrayList<Audio> playList = new ArrayList<Audio>();
    public Playlist(String name)
    {
        this.name = name;
        this.nameOfUserAccount = userAccount.getName();
        id = new StringBuilder(this.nameOfUserAccount);
        id.append(" Liked Songs");
        id.append(i++);
    }

    public StringBuilder getId() {
        return id;
    }

    public void setId(StringBuilder id) {
        this.id = id;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getNameOfUserAccount() {
        return nameOfUserAccount;
    }

    public void setNameOfUserAccount(String nameOfUserAccount) {
        this.nameOfUserAccount = nameOfUserAccount;
    }

    public ArrayList<Audio> getPlayList() {
        return playList;
    }

    public void setPlayList(ArrayList<Audio> playList) {
        this.playList = playList;
    }
    public String toString()
    {
        return "PlayList ID: "+getId()+"\t"+"PlayList name: "+getName();
    }
}
