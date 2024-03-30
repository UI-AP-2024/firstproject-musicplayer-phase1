package Controllers;

import Models.Album;
import Models.Audio.Audio;
import Models.Data.Database;
import Models.User.Artist;

import javax.xml.crypto.Data;

public abstract class ArtistController {
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

    public ArtistController()
    {
        database = Database.getInstance();
        artistModel = (Artist) database.getLogedInUser();
    }
    public abstract double calculateIncome();

}
