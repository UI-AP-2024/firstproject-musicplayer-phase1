package controller;

import model.Album;
import model.Audio.Audio;
import model.Audio.Music;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre;
import model.UserAccount.Podcaster;
import model.UserAccount.RegularListener;
import model.UserAccount.Singer;
import model.UserAccount.User;

import java.util.ArrayList;
import java.util.Date;

public class SingerControler extends ArtistControler {
    private static SingerControler singerControler;
    public SingerControler() {
    }

    public static SingerControler getSingerControler() {
        if(singerControler==null)
            singerControler=new SingerControler();
        return singerControler;
    }



}
