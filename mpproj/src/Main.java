
import Model.Accounts.AdminModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Audios.MusicModel;
import Model.Database.Database;
import View.MainView;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        int[] date = new int[3]; date[0] = 2000; date[1] = 1 ; date[2] = 1;
        AdminModel.getAdmin("Admin" , "Admin1234" , "Admin" , "admin@gmail.com" , "09111111111" , date);
        System.out.println(MainView.getMainView().mainMenu(""));
        
    }
}