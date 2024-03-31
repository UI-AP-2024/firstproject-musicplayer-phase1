package Controllers;

import Extra.AIRecommendor;
import Models.Audio.Audio;
import Models.Data.Database;
import Models.Genre;
import Models.PremiumPlan;
import Models.PremiumPlan;
import Models.User.Listener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public abstract class ListenerController {
    private Database database;

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
    private Listener listenerModel;

    public Listener getListenerModel() {
        return listenerModel;
    }

    public void setListenerModel(Listener listenerModel) {
        this.listenerModel = listenerModel;
    }
    public ListenerController()
    {
        this.database = Database.getInstance();
        listenerModel = (Listener) this.database.getLogedInUser();
    }
    public void addCredit(double value) {
        listenerModel.setCredit(listenerModel.getCredit() + value);
        database.updateUser(this.listenerModel);
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
        return AIRecommendor.recommendor(n, this.listenerModel);
    }

    public String playAudio(String audioName)
    {
        for(Audio audio : database.getAudios())
        {
            if(audio.getFileName().equals(audioName)) return audio.getAudioLink();
        }
        return "Audio file not found";
    }

    public abstract String makeNewPlaylist(String name);
    public abstract String addToPlaylist(String playistName, Audio audio);
    public abstract String purchasePremium(PremiumPlan plan);


}
