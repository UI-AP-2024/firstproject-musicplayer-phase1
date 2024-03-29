package controller;
import model.Audio.*;
import model.Database.Database;
import model.UserAccount.*;
import model.*;

import java.util.ArrayList;
import java.util.Date;

public class ListenerControler {
    Listener listenerr;

    public Listener getListenerr() {
        return listenerr;
    }

    public void setListenerr(Listener listenerr) {
        this.listenerr = listenerr;
    }

    public String signUpListener(String username, String pasword, String name, String email, String phoneNum, Date birthDate){
        ArrayList<User>users=Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username)){
                return "error : this user name already exist .";
            }
        }
        Listener listener=new Listener(username,pasword,name,email,phoneNum,birthDate,50);
        setListenerr(listener);
        //add to data base
        return showgenre()+"\n"+"listener accaount successfully created .";
    }
    String showgenre(){
        return Genre.Country+","+Genre.Pop+","+Genre.HipHop+","+Genre.History+","+Genre.Interview+","+Genre.Jazz+","+Genre.TrueCrime+","+Genre.Society+","+Genre.Rock;
    }
    public String login(String username,String password){
        ArrayList<User>users=Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                setListenerr((Listener) user);
                return "login successful .";
            }
        }
        return "error : user name or password is wrong";
    }
    public String logout(){
        ArrayList<User>users=Database.getDatabase().getUsers();
            //??
        return "logout successfull";
    }
    public String makePlaylist(String playlistName) {
        for (Playlist playlist : listenerr.getPlaylists()) {
            if (playlist.getName() == playlistName) {
                return "this play list already exist.";
            }
        }
        if (!(listenerr instanceof PrimiumListener)) {
            if (listenerr.getPlaylistcounter() >= 3) {
                return "you reached maximum limit .";
            }
        }
        listenerr.setPlaylistcounter(listenerr.getPlaylistcounter(), 1);
        listenerr.getPlaylists().add(new Playlist(generateIdPlaylist(), playlistName, listenerr.getFullName()));
        return "play list added .";
    }
    private int generateIdPlaylist(){
        return listenerr.getPlaylistcounter()+ Playlist.getIdcounter();
    }
}
