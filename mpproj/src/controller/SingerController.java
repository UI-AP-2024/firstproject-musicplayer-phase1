package controller;

import model.audio.Album;
import model.audio.Music;
import model.user.Singer;
import model.user.User;

public class SingerController {

    private static SingerController singerController;

    private SingerController() {

    }

    public static SingerController getSingerController() {
        if (singerController == null) {
            singerController = new SingerController();
        }
        return singerController;
    }

    private static Singer singer;

    public static Singer getSinger() {
        return singer;
    }

    public static void setSinger(Singer singer) {
        SingerController.singer = singer;
    }

    public String showFollowers() {
        String txt = "All Followers\n";
        for (User user : getSinger().getFollowers()) {
            txt += "-" + user.getUsername() + "\n";
        }
        return txt;
    }

    public void loginSinger(Singer singer) {
        setSinger(singer);
    }

    public String ShowSingerInfo() {
        String txt = "Singer info:" +
                "\nuser name : " + getSinger().getUsername() +
                "\nName : " + getSinger().getName() +
                "\nFollowers : " + String.valueOf(getSinger().getFollowers().size()) +
                "\nBiographi : " + getSinger().getBiographi() + "\n\n";
        if (getSinger().getAlbumList().size() == 0) {
            txt += "No album found!!";
            return txt;
        }
        for (Album album : getSinger().getAlbumList()) {
            txt += album.getAlbumName() + "\n";
            for (Music music : album.getMusicList()) {
                txt += "-" + music.getAudioName() + "(id:" + String.valueOf(music.getId()) + ")\n";
            }
        }

        return txt;
    }

    public String showViewsStatics() {
        String txt = "Albums\n";
        if (getSinger().getAlbumList().size() == 0) {
            txt += "No album found!!";
            return txt;
        }
        for (Album album : getSinger().getAlbumList()) {
            txt += album.getAlbumName() + "(id:" + String.valueOf(album.getId()) + ")\n";
            if (album.getMusicList().size() == 0) {
                txt += "no music is published in this album yet";
                return txt;
            }
            for (Music music : album.getMusicList()) {
                txt += "-" + music.getAudioName() + "(" + String.valueOf(music.getNumberOfPlays()) + ")\n";
            }
        }

        return txt;
    }

    public String createNewAlbum(String albumName) {
        getSinger().addToAlbumList(new Album(albumName, getSinger().getName()));
        return "your album has been created succesfully";
    }

}
