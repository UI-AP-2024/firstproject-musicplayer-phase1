package Controllers;

import Models.Audio.Podcast;
import Models.User.Podcaster;

import java.util.ArrayList;

public class PodcasterController extends ArtistController{

    public void publishPodcast(Podcast podcast)
    {
        Podcaster podcasterModel = (Podcaster) this.getArtistModel();
        ArrayList<Podcast> podcasts = podcasterModel.getPodcasts();
        podcasts.add(podcast);
    }

    public double calculateIncome()
    {
        int playedCount = getPlayedCount();
        this.getArtistModel().setIncome(0.5 * playedCount);
        return this.getArtistModel().getIncome();
    }
}
