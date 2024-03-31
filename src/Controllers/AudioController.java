package Controllers;

import Extra.AIRecommendor;
import Models.Audio.Audio;
import Models.Data.Database;
import Models.Genre;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class AudioController {
    // Made this class follow the Singleton pattern cuz we only need one instance
    private Database database;
    private static AudioController audioController;
    public AudioController getAudioController()
    {
        if(audioController == null) audioController = new AudioController();
        return audioController;
    }
    private AudioController()
    {
         database = Database.getInstance();
    }

}
