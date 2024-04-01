import Model.*;
import View.AdminView;
import View.ArtistView;
import View.ListenerView;

import java.util.Date;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        AdminModel.getAdmin("sina83m","12df34jj","sina M","sina@gamil.com","0913234",new Date(11/9/2004));
        String entry = "";
        while (!entry.equals("End")){
            entry=sin.nextLine();
            String[] enter = entry.split("-");
            if(enter[0].equals("Signup") && enter[1]!=null && enter[2]!=null){
                if(enter[1].equals("L"))
                    ListenerView.getListener().signUp(enter[2], enter[3], enter[4], enter[5], enter[6], new Date(enter[7]));
                else{
                    ArtistView.getArtistView().signUp(enter[1], enter[2], enter[3], enter[4], enter[5], enter[6], new Date(enter[7]), enter[8]);
                }
            }
            else if(enter[0].equals("Login") && enter[1]!=null && enter[2]!=null){
                for (User user : Database.getDatabase().getUsers()) {
                    if(user.getUsername().equals(enter[1])){
                        if(user instanceof AdminModel)
                            AdminView.getAdmin().login(enter[1],enter[2]);
                        else if(user instanceof ListenerModel)
                            ListenerView.getListener().login(enter[1],enter[2]);
                        else
                            ArtistView.getArtistView().login(enter[1],enter[2]);

                        break;
                    }
                }
            }
            else if(!enter[0].equals("End"))
                System.out.println("Wrong command!");
        }
    }


}