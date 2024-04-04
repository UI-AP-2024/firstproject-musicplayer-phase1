package model.dto;

import model.model.audio.Music;
import model.model.audio.Podcast;

import java.util.List;

public class AudioPopularDto {
    private final List<Music> music;
    private final List<Podcast> podcasts;

    public AudioPopularDto(final List<Music> music, final List<Podcast> podcasts) {
        this.music = music;
        this.podcasts = podcasts;
    }

    public List<Music> getMusic() {
        return music;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }
}
