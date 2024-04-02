package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Collectors;

import model.audio.Audio;
import model.audio.Genre;
import model.database.Database;
import model.user.Listener;
import model.user.User;

public class AudioController {
    private static AudioController audioController;
    private AudioController(){

    }
    public static AudioController audioController(){
        if(audioController==null){
            audioController = new AudioController();
        }
        return audioController;
    }
    // private Audio audio;
    // public Audio getAudio(){
    //     return audio;
    // }
    // public void setAudio(Audio audio){//in login
    //     this.audio = audio;
    // }

    public String searchAudio(String key){
        String txt="results matching your keyword\n";
        for(Audio audio : Database.getDatabase().getAllAudio()){
            if((audio.getAudioName().equals(key))|| audio.getArtistName().equals(key)){
                txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n";
            }
        }
        return txt;
    }

    public String sortAudio(String base){
        String txt = "Sorted Audios according to ";
        if(base.equals("P")){
            txt+="number of plays\n";
            ArrayList <Audio> sorted = Database.getDatabase().getAllAudio()
                .stream()
                .sorted(Comparator.comparing(Audio -> Audio.getNumberOfPlays()))
                .collect(Collectors.toCollection(ArrayList::new));
            for(Audio audio : sorted){
                txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n";
            }
        }
        if(base.equals("L")){
            txt+="number of likes\n";
            ArrayList <Audio> sorted = Database.getDatabase().getAllAudio()
                .stream()
                .sorted(Comparator.comparing(Audio -> Audio.getNumberOfLikes()))
                .collect(Collectors.toCollection(ArrayList::new));
            for(Audio audio : sorted){
                txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n";
            }
        }
        return txt;
    }

    public String filterAudio(String base, String key) throws ParseException{
        if(base.equals("A")){
            return filterByArtist(key);
        }
        if(base.equals("G")){
            return filterByGenres(key);
        }
        if(base.equals("D")){
            return filterByDate(key);
        }
        else{
            return "please enter \n'A' if you want audios to filter by artist name\n'G' if you want audios to filter by genres\n'D' if you want audios to filter by release date\n";
        }
    }

    private String filterByArtist(String key){
        String txt = " Audios filtered by artist\n";
        ArrayList <Audio> filtered = Database.getDatabase().getAllAudio()
            .stream()
            .filter(Audio -> Audio.getArtistName().equals(key))
            .collect(Collectors.toCollection(ArrayList::new));
            if(filtered.size()==0){
                return "this artist may not be valid or no audio was published by the Artist, enter another name!";
            }
        for(Audio audio : filtered){
            txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n";
           
        }
        return txt;
    }
    private String filterByGenres(String key){
        String txt = " Audios filtered by artist\n";
        ArrayList <Audio> filtered = Database.getDatabase().getAllAudio()
            .stream()
            .filter(Audio -> Audio.getGenre().equals(Genre.valueOf(key)))
            .collect(Collectors.toCollection(ArrayList::new));
        if(filtered.size()==0){
            return "no audio was published with this genre,  enter another one!";
        }
        for(Audio audio : filtered){
            txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n";
           
        }
        return txt;
    }
    private String filterByDate(String key)throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date = formatter.parse(key);
        String txt = " Audios filtered by artist\n";
        ArrayList <Audio> filtered = Database.getDatabase().getAllAudio()
            .stream()
            .filter(Audio -> Audio.getReleaseDate().equals(date))
            .collect(Collectors.toCollection(ArrayList::new));
        if(filtered.size()==0){
            return "no audio was published on this day enter another date!";
        }
        for(Audio audio : filtered){
            txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n";
           
        }
        return txt;
    }


}
