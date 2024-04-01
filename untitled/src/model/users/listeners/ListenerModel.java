package model.users.listeners;

import model.audioRelated.AudioModel;
import model.audioRelated.Genre;
import model.audioRelated.PlayListModel;
import model.users.AccountUserModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ListenerModel extends AccountUserModel
{
    private double listenerCredit;
    private ArrayList <PlayListModel> playLists=new ArrayList<>();
    private Map <AudioModel,Long> playingAmount=new HashMap<>();
    private Calendar subscriptionExpiration;
    private ArrayList <Genre> favGenres=new ArrayList<>();
    public ListenerModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate);
        this.listenerCredit=50;
        this.subscriptionExpiration=null;
    }
    public void setListenerCredit(double listenerCredit)
    {
        this.listenerCredit=listenerCredit;
    }
    public void setPlayLists(ArrayList<PlayListModel> playLists)
    {
        this.playLists = playLists;
    }
    public void setPlayingAmount(Map<AudioModel, Long> playingAmount)
    {
        this.playingAmount = playingAmount;
    }
    public void setSubscriptionExpiration(Calendar subscriptionExpiration)
    {
        this.subscriptionExpiration = subscriptionExpiration;
    }
    public void setFavGenres(ArrayList<Genre> favGenres)
    {
        this.favGenres = favGenres;
    }
    public double getListenerCredit()
    {
        return this.listenerCredit;
    }
    public ArrayList<PlayListModel> getPlayLists()
    {
        return this.playLists;
    }
    public Map<AudioModel, Long> getPlayingAmount()
    {
        return this.playingAmount;
    }
    public ArrayList<Genre> getFavGenres()
    {
        return this.favGenres;
    }
    public Calendar getSubscriptionExpiration()
    {
        return this.subscriptionExpiration;
    }
    public String toString()
    {
        String string= super.toString()+"\nCredit: "+this.listenerCredit+"\nSubscription expiration date: ";
        if(this.subscriptionExpiration!=null)
            string=string+this.subscriptionExpiration.get(Calendar.YEAR)+"/"+this.subscriptionExpiration.get(Calendar.MONTH)+"/"+this.subscriptionExpiration.get(Calendar.DATE);
        else
            string=string+"Not a premium listener";
        StringBuilder theRestOfString=new StringBuilder("\nFavorite genres: ");
        for(Genre temp:this.favGenres)
            if(temp!=null)
                theRestOfString.append(temp).append(" ");
        theRestOfString.append("\nPlaylists:\n");
        for(PlayListModel temp:this.playLists)
            if(temp!=null)
                theRestOfString.append(temp).append("\n");
        theRestOfString.append("Playing amount:\n");
        for(Map.Entry <AudioModel,Long> temp:playingAmount.entrySet())
            if(temp!=null)
                theRestOfString.append(temp.getKey()).append(" : ").append(temp.getValue()).append("\n");
        return string+theRestOfString;
    }
}
