package Controllers;

import Models.Audio.Audio;
import Models.Data.Database;
import Models.Report;
import Models.User.Admin;
import Models.User.Artist;
import Models.User.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AdminController {
    private static AdminController adminController;
    private Database database;
    private Admin adminModel;
    private AdminController()
    {
        database = Database.getInstance();
        adminModel = Admin.getInstance();
    }
    public AdminController getAdminController()
    {
        if(adminController == null) adminController = new AdminController();
        return adminController;
    }

    public ArrayList<Audio> showMostLiked()
    {
        ArrayList<Audio> result = database.getAudios();
        result = result.stream().sorted((aud1, aud2) -> aud1.getLikesCount() - aud2.getLikesCount())
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
    private Artist findArtistByName(String Name)
    {
        Artist selectedArtist = null;
        for(User tmpUser : database.getUsers()) if(tmpUser instanceof Artist)
        {
            Artist tmpArtist = (Artist) tmpUser;
            if(tmpArtist.getName().equals(Name)) selectedArtist = tmpArtist;
            break;
        }
        return selectedArtist;
    }
    public String showArtistInfo(String artistName)
    {
        Artist selectedArtist = findArtistByName(artistName);
        if(selectedArtist == null) return "No artist found";
        return selectedArtist.toString();
    }

    private Audio findAudioByName(String Name)
    {
        Audio selectedAudio = null;
        for(Audio tmpAudio : database.getAudios())
        {
            if(tmpAudio.getFileName().equals(Name)) selectedAudio = tmpAudio;
            break;
        }
        return selectedAudio;
    }
    public String showAudioInfo(String audioName)
    {
        Audio selectedAudio = findAudioByName(audioName);
        if(selectedAudio == null) return "No audio found";
        return selectedAudio.toString();
    }
    public ArrayList<Artist> showArtists()
    {
        ArrayList<Artist> result = new ArrayList<>();
        for(User tmpUser : database.getUsers())
        {
            if(tmpUser instanceof Artist) result.add((Artist)tmpUser);
        }
        return result;
    }

    public ArrayList<Report> showReports()
    {
        return database.getReports();
    }

    public String showAdminInfo()
    {
        return adminModel.toString();
    }
}
