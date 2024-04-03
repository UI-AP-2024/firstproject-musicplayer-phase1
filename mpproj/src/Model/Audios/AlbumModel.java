package Model.Audios;

import java.util.ArrayList;

public class AlbumModel {
    private final int ID;
    private String name;
    private String artistName;
    private ArrayList<AudioModel> songsList;

    private static int albumCount = 1;
    public AlbumModel(String name , String artistName){
        this.ID = albumCount++;
        this.name = name;
        this.artistName = artistName;
        this.songsList = new ArrayList<AudioModel>();
    }

    public int getID(){
        return this.ID;
    }

    public String getName(){
        return this.name;
    }


    public void setName(String name){
        this.name = name;
    }
    public String getArtistName(){
        return this.artistName;
    }

    public ArrayList<AudioModel> getSongsList(){
        return this.songsList;
    }

    public void addSongToAlbum(AudioModel audio){
        this.songsList.add(audio);
    }
}
