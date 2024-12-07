package model.model.audio;

import java.util.List;

public class PlayList {
    private int id;
    private String name;
    private String createdBy;
    private List<Audio> audioFiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Audio> getAudioFiles() {
        return audioFiles;
    }

    public void setAudioFiles(List<Audio> audioFiles) {
        this.audioFiles = audioFiles;
    }

    @Override
    public String toString() {
        return "PlayList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt='" + createdBy + '\'' +
                ", audioFiles=" + audioFiles +
                '}';
    }
}
