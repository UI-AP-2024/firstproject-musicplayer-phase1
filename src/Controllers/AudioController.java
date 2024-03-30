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
    public ArrayList<Audio> searchAudios(String value)
    {
        ArrayList<Audio> audios = database.getAudios();
        ArrayList<Audio> result = audios.stream()
                .filter(aud -> aud.getArtistName().contains(value) || aud.getFileName().contains(value))
                .collect(Collectors.toCollection(ArrayList::new));
        return result;
    }
    public ArrayList<Audio> sortAudios(int type)
    {
        ArrayList<Audio> audios = database.getAudios();
        ArrayList<Audio> result = new ArrayList<>();
        switch (type)
        {
            case 1:
                result = audios.stream()
                        .sorted((aud1, aud2) -> aud1.getLikesCount() - aud2.getLikesCount())
                        .collect(Collectors.toCollection(ArrayList::new));
                break;
            case 2:
                result = audios.stream()
                        .sorted((aud1, aud2) -> aud1.getPlayCount() - aud2.getPlayCount())
                        .collect(Collectors.toCollection(ArrayList::new));
                break;
        }
        return result;
    }

    public ArrayList<Audio> filterAudios(String artistName, Genre genre, LocalDate startDate, LocalDate endDate)
    {
        ArrayList<Audio> result = database.getAudios();
        if(artistName != null)
        {
            result = result.stream()
                    .filter(aud -> aud.getArtistName().equals(artistName))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if(genre != null)
        {
            result = result.stream()
                    .filter(aud -> aud.getGenre().equals(genre))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if(startDate != null && endDate != null)
        {
            result = result.stream()
                    .filter(aud -> aud.getPublishDate().isBefore(endDate) && aud.getPublishDate().isAfter(startDate))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return result;
    }

    public ArrayList<Audio> suggestedAudios(int n)
    {
        return AIRecommendor.recommendor(n);
    }
}
