package controller;

import model.Audio.Audio;
import model.Playlist;

import java.util.List;

public class Listener {

    private List<String> likeAudis;

    public void addAudio(Playlist playlist, String audio) {
        playlist.addAudio(audio);
    }


    //*********
    public static Playlist createPlaylist(int id, String name, String userName, boolean isPremium) {
        if (!isPremium) {
            if (id > 3) {
                System.out.println("Error: Maximum number of playlists reached for a normal user.");
                return null;
            }
        }

        return new Playlist(id, name, userName);
    }

    public void addAudioToPlaylist(Playlist playlist, String audio, boolean isPremiumUser) {
        if (!isPremiumUser && playlist.getAudios().size() >= 10) {
            System.out.println("Error: Maximum number of audio files reached for a normal user.");
            return;
        }

        playlist.addAudio(audio);
    }

    //*********
    public void playAudio(Audio Audi, String audio) {
        System.out.println(
                "uniqueId=" + Audi.getUniqeId() +
                        ", title='" + Audi.getTitle() + '\'' +
                        ", artist='" + Audi.getArtist() + '\'' +
                        ", playCount=" + Audi.getPlayCount() +
                        ", likes=" + Audi.getLikes() +
                        ", releaseDate=" + Audi.getReleaseDate() +
                        ", genre=" + Audi.getGenre() +
                        ", audioLink='" + Audi.getAudioLink() + '\'' +
                        ", cover='" + Audi.getCover() + '\'' +
                        '}'
        );
    }

    public void likeAudis(String audio) {
        likeAudis.add(audio);
    }


    //*********
}
