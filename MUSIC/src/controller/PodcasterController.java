package controller;

import model.model.audio.Podcast;
import model.model.user.Podcaster;
import model.model.user.Singer;

public class PodcasterController extends Controller {


    public void savePodcast(final Podcast podcast) {
        database.savePodcast(podcast);
    }
    public void savePodcaster(final Podcaster podcaster) throws Exception {
        if (database.isExistsUsername(podcaster.getUsername())) {
            throw new Exception("Username is exists");
        }
        database.savePodcaster(podcaster);
    }


}
