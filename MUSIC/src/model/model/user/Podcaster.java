package model.model.user;


import model.model.audio.Podcast;

import java.util.List;

// پادکستر
public class Podcaster extends Artist {
    private List<Podcast> podcasts;

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    @Override
    public String toString() {
        return "Podcaster{" +
                "podcasts=" + podcasts +
                "} " + super.toString();
    }
}
