package model;

import java.util.ArrayList;

public class Album {
    private long id;
    private String name;
    private String singerName;
    private ArrayList<Music> musicList;
    private static int countId=0;

    public static int getCountId() {
        return countId;
    }

    public static void setCountId(int countId) {
        Album.countId = countId;
    }

    public Album(long id, String name, String singerName/*, ArrayList<Music> musicList*/) {
        this.id = id;
        this.name = name;
        this.singerName = singerName;
//        this.musicList = musicList;
        this.musicList = new ArrayList<>();
    }

    public String toString(){
        String result = "";
        result += "Album Id: "+getId()+"\tAlbum Name: "+getName()+"\tSinger Name: "+getSingerName();
        String result2 = "";
        for ( Music music : getMusicList()){
            result2 += music+"\n";
        }
        if ( result2.equals(""))
            result += "\nMusics: empty";
        else
            result += "\nMusics:\n "+result2;
        return result;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSingerName() {
        return singerName;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }
}
