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
            if(enter[0].equals("Signup") && enter[1]!=null && enter[2]!=null && enter[3]!=null && enter[4]!=null && enter[5]!=null && enter[6]!=null && enter[7]!=null){
                if(enter[1].equals("L"))
                    ListenerView.getListener().signUp(enter[2], enter[3], enter[4], enter[5], enter[6], new Date(enter[7]));
                else if(enter[8]!=null)
                    ArtistView.getArtistView().signUp(enter[1],enter[2], enter[3], enter[4], enter[5], enter[6], new Date(enter[7]),enter[8]);
            }
            else if(enter[0].equals("Login") && enter[1]!=null && enter[2]!=null){
                for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
                    if(Database.getDatabase().getUsers().get(i).getUsername().equals(enter[1])){
                        User user = Database.getDatabase().getUsers().get(i);
                        if(user.getClass().equals(AdminModel.class))
                            AdminView.getAdmin().login(enter[1],enter[2]);
                        else if(user.getClass().equals(ListenerModel.class))
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