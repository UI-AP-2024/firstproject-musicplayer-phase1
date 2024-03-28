package Model;

import java.util.*;

public class ListenerModel extends User{

    private double credit;
    private ArrayList<Playlists> playlists;
    private Map<AudioModel, Integer> playCount;
    public Date subscriptionExpirDate;
    private Genre favoriteGenres;
    public ListenerModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        super(username,password,fullName,email,phoneNumber,birthDate);
        playCount = new HashMap<>();
    }
}
