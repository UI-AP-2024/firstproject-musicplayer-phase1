package View;
import controller.Listener.listenerController;
import controller.Artist.artistController;
import controller.userLogin;

import java.util.Date;
import java.util.Scanner;

public class firstPage {
    private static firstPage first;

    private firstPage(){
    }
    public static firstPage getFirst(){
        if (first==null){
            first = new firstPage();
        }
        return first;
    }
    public void firstView(){
        System.out.println("You can now sign up or login");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] commands = input.split(" -");

        /// register
        if (commands[0].equals("Signup")){
            String[] dati = commands[7].split("/");
            Date date=new Date(Integer.parseInt(dati[0]),Integer.parseInt(dati[1]),Integer.parseInt(dati[2]));
            int ok=3;
            if (commands[1].equals("L")){
                ok=listenerController.getListenerC().registerListener(commands[2],commands[3],
                        commands[4],commands[5],commands[6],date);
                if (ok==3){
                    System.out.println("Registering was successful!!!");
                    listenerView.getListenerV().showGenres();
                }
            }
            else if (commands[1].equals("P") || commands[1].equals("S")){
                ok=artistController.getArtistC().registerArtist(commands[2],commands[1],
                        commands[3],
                        commands[4],commands[5],commands[6],date,commands[8]);
                if (ok==3){
                    //todo;
                    System.out.println("Registering was successful!!!");
                }
            }
            switch (ok){
                case 0:
                    System.out.println("this user name has already exist!");
                    firstView();
                case 1:
                    System.out.println("phone number is not true!");
                    firstView();
                case 2:
                    System.out.println("email is not true");
                    firstView();
            }
        };


        // login
        if (commands[0].equals("Login")){
            String log = userLogin.getUser().checkType(commands[1],commands[2]);
            switch (log){
                case "Listener":
                    System.out.println("welcome");
                    listenerView.getListenerV().listenerCommands();
                case "Artist":
                    System.out.println("welcome");
                case "Admin":
                    System.out.println("welcome");
                    adminView.getAdminVi().adminCommands();
                case "0":
                    System.out.println("user not found");
                    firstView();

            }
        }
    }
}
