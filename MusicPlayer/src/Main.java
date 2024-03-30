import Model.AdminModel;
import Model.Genre;
import Model.User;
import View.AdminView;
import View.ArtistView;
import View.ListenerView;

import java.util.Date;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        AdminModel.getAdmin("sina83m","12df34jj","sina M","sina@gamil.com","0913234",new Date(11/9/2004));
        System.out.println("1)Admin\n2)Listener\n3)Artist\n4)Exit");
        int n = sin.nextInt();
        while (n!=4) {
            switch (n) {
                case 1:
                    AdminView.getAdmin().Login();
                    break;
                case 2:
                    ListenerView.getListener().signUp();
                    break;
                case 3:
                    ArtistView.getArtistView().signUp();
                    break;
            }
        }
    }


}