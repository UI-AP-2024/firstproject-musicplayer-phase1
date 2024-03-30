package View;
import Controller.AdminController;
import Model.User;

import java.util.Date;
import java.util.Scanner;
public class AdminView extends User{
    private AdminController view;
    private AdminView(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        super(username,password,fullName,email,phoneNumber,birthDate);
    }

    public void Login(){

        Scanner sin = new Scanner(System.in);
        String entry=sin.nextLine();
        String[] enter = entry.split("-");
        if(enter[0].equals("Login")) {
            if (view.enterUserPanel(enter[1], enter[2])) {
                System.out.println("Correct!");
                second();
            } else {
                System.out.println("username or password is wrong!");
                System.out.println("Try Again!");
                Login();
            }
        }
    }
    private void second(){
        System.out.println("1) Statistics: Most Poplar Songs\n2) Audios: Audios Information\n3) Artists: Artists Information\n4) Reports\n5) UserInfo\n6) log out");
        Scanner sin = new Scanner(System.in);
       String command="";
        while ((!command.equals("Logout"))) {
            command = sin.nextLine();
            String[] commands = command.split("-");
            if(commands[0].equals("Statistics"))
                System.out.println(view.showPopularSongs());
            else if(commands[0].equals("Audios"))
                System.out.println(view.showAudioInfo());
            else if(commands[0].equals("Audio"))
                System.out.println(view.showOneAudioInfo(Integer.parseInt(commands[1])));
            else if(commands[0].equals("Artists"))
                System.out.println(view.showArtistInfo());
            else if(commands[0].equals("Artist"))
                System.out.println(view.showOneArtistInfo(commands[1]));
            else if(commands[0].equals("Reports"))
                System.out.println(view.showReports());
        }
        Login();
    }
}
