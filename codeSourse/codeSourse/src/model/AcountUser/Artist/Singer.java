package model.AcountUser.Artist;
import model.AcountUser.AccountUser;
import model.AcountUser.Artist.Artist;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Singer extends Artist {
    private List<String> albums;

    //*********************************************
    public Singer(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate, double income, String biography, List<AccountUser> followers, List<String> albums) {
        super(userName, password, fullName, email, phoneNumber,birthDate,income,biography,followers);
        this.albums = new ArrayList<>();
    }
    //*********************************************

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }
    //*********************************************
    @Override
    public String toString() {
        return "Artist{" +
                "userName='" + getUserName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", albums=" + albums +
                '}';
    }
}