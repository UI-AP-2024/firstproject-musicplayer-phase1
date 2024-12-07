package Model.Accounts.Artists;

import Model.Audios.AudioModel;
import Model.Audios.PodcastModel;

import java.util.ArrayList;
import java.util.Date;

public class PodcasterModel extends ArtistModel {
    private ArrayList<AudioModel> podcastList;


    public PodcasterModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate , String bio) {
        super(username, password, fullName, email, phoneNumber, birthDate , bio);

        this.podcastList = new ArrayList<AudioModel>();
    }

    public ArrayList<AudioModel> getPodcastList(){
        return this.podcastList;
    }

    public void addPodcast(PodcastModel podcast){
        this.podcastList.add(podcast);
    }
}
