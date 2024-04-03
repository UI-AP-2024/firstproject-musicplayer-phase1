package controller;

import model.Audio.Audio;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre;
import model.UserAccount.Podcaster;
import model.UserAccount.RegularListener;
import model.UserAccount.User;

import java.util.ArrayList;
import java.util.Date;

public class PodcasterControler extends ArtistControler{
    private static PodcasterControler podcasterControler;
    public PodcasterControler() {
    }

    public static PodcasterControler getPodcaster() {
        if(podcasterControler==null)
            podcasterControler=new PodcasterControler();
        return podcasterControler;
    }


}
