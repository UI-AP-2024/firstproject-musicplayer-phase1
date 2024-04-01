package View;
import Controller.AdminController;
import Model.User;

import java.util.Date;
import java.util.Scanner;
public class AdminView {
    private  AdminController view = new AdminController();
    private static AdminView admin;
    private AdminView(){
    }
    public static AdminView getAdmin(){
        if(admin==null)
            admin = new AdminView();

        return admin;
    }
    public void login(String userName,String password){

        if (view.enterUserPanel(userName,password)) {
            System.out.println("Correct!");
            second();
        } else {
            System.out.println("username or password is wrong!");
            System.out.println("Try Again!");
        }

    }
    private void second(){
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
            else if(commands[0].equals("AccountInfo"))
                System.out.println(view.showUserInfo());
            else if(!commands[0].equals("Logout"))
                System.out.println("Wrong command!");
        }
        //go to main menu
    }
}
