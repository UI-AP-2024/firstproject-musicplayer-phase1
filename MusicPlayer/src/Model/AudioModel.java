package Model;

import java.util.Date;

abstract public class AudioModel {
    private static int ID;
    private String audioName;
    private String artistName;
    private int playedCount;
    private int likeCount;
    private Date date;
    private Genre genre;
    private String audioLink;
    private String cover;
}
