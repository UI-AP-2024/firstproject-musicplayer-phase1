package model;

public class Free extends Listener{
    private static final int maxAddSongToPlaylist = 10;
    private static final int maxPlaylistMade = 5;
    public int getMaxAddSongToPlaylist(){
        return maxAddSongToPlaylist;
    }

    public static int getMaxPlaylistMade() {
        return maxPlaylistMade;
    }
}
