package Model.Accounts.Artists;

import Model.Audios.AlbumModel;
import Model.Audios.MusicModel;

import java.util.ArrayList;
import java.util.Date;

public class SingerModel extends ArtistModel {
    private ArrayList<MusicModel> musicList;

    private ArrayList<AlbumModel> albumList;

    public SingerModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate , String bio) {
        super(username, password, fullName, email, phoneNumber, birthDate , bio);

        this.musicList = new ArrayList<MusicModel>();
        this.albumList = new ArrayList<AlbumModel>();
    }


    public ArrayList<MusicModel> getMusicList(){
        return this.musicList;
    }

    public void AddMusic(MusicModel music){
        this.musicList.add(music);
    }

    public ArrayList<AlbumModel> getAlbumList(){
        return this.albumList;
    }

    public void addAlbum(AlbumModel album){
        this.albumList.add(album);
    }
}
