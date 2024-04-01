package Controllers;

import Models.Audio.Podcast;
import Models.User.Podcaster;

import java.util.ArrayList;

public class PodcasterController extends ArtistController{
    private static  PodcasterController podcasterController;
    private PodcasterController()
    {
        super();
    }
    public PodcasterController getPodcasterController()
    {
        if(podcasterController == null) podcasterController = new PodcasterController();
        return podcasterController;
    }
    public void addPodcast(Podcast podcast)
    {
        Podcaster podcasterModel = (Podcaster) this.getArtistModel();
        ArrayList<Podcast> podcasts = podcasterModel.getPodcasts();
        podcasts.add(podcast);
    }

    public double calculateIncome()
    {
        int playedCount = ArtistController.getPlayedCount(this.getArtistModel());
        this.getArtistModel().setIncome(0.5 * playedCount);
        return this.getArtistModel().getIncome();
    }
}
