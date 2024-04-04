package controller;

import model.model.audio.Podcast;

public class PodcasterController extends Controller {


    public void savePodcast(final Podcast podcast) {
        database.savePodcast(podcast);
    }


}
