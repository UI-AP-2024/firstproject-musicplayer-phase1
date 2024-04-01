package Controllers;

import Models.Album;
import Models.Audio.Audio;
import Models.Data.Database;
import Models.User.Artist;
import Models.User.Listener;
import Models.User.User;

import javax.xml.crypto.Data;
import java.util.Map;

public abstract class ArtistController {
    // the class is abstract, hence it can't follow the singleton pattern
    // because in singleton pattern we need to make an object of the class in constructor
    public ArtistController()
    {
        database = Database.getInstance();
        artistModel = (Artist) database.getLogedInUser();
    }
    private Database database;

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    private Artist artistModel;

    public Artist getArtistModel() {
        return artistModel;
    }

    public void setArtistModel(Artist artistModel) {
        this.artistModel = artistModel;
    }

    public abstract double calculateIncome();
    public static int getPlayedCount(Artist artist)
    {
        Database tmpDatabase = Database.getInstance();
        int playedCount = 0;
        for(User tmpUser : tmpDatabase.getUsers())
        {
            if(tmpUser instanceof Listener)
            {
                Listener tmpListener = (Listener) tmpUser;
                for(Map.Entry tmpEntry : tmpListener.getAudiosPlayed().entrySet())
                {
                    Audio tmpAudio = (Audio)tmpEntry.getKey();
                    if(tmpAudio.getArtistName().equals(artist.getName()))
                    {
                        playedCount += (Integer) tmpEntry.getValue();
                    }
                }
            }
        }
        return playedCount;
    }
}
