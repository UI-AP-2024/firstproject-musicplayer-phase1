package model;

import java.util.ArrayList;

public class Podcaster extends Artist{
    private ArrayList<Podcast> podcasts = new ArrayList<>();

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
