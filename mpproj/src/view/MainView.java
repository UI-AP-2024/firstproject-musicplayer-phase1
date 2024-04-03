package view;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import controller.ArtistController;
import controller.AudioController;
import controller.ListenerController;
import controller.SingerController;
import controller.UserController;
import model.user.Admin;
import model.user.Artist;
import model.user.Listener;
import model.user.Podcaster;
import model.user.PremiumListener;
import model.user.PremuimAcc;
import model.user.Singer;
import model.user.User;

public class MainView {
    private static MainView mainView;
    private MainView(){

    }
    public static MainView getMainView(){
        if(mainView==null){
            mainView = new MainView();
        }
        return mainView;
    }
    public void mainView() throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date dateOfbirth = formatter.parse("12/5/2000");
        Admin admin = Admin.getAdmin("Pkjhgyt4321#","isAdmin","AdminName","emailAdmin@gmail.com","09876543215",dateOfbirth);
        Scanner sc = new Scanner(System.in);
        String command;
        String txt;
        //Signup -L|S|P -[username] -[password] -[name] -[email] -[phone number]
//-[birth date] -[bio ]
        // String check ="Signup-L-mary-Maryamdar84#-MaryamDar-maryamdar1384@gmail.com-09390555104-10/5/2005-muytrggv";
        // String [] spltCmd = command.split("-");
        // txt = UserController.getUserController().signupNewUser(spltCmd[1],spltCmd[2],spltCmd[3],spltCmd[4],spltCmd[5],spltCmd[6],spltCmd[7],spltCmd[8]);
        //         System.out.println(txt);
        // boolean a =true;
        int a =10;
        String type=null;
        while (true) {
            command = sc.nextLine();
            String [] spltCmd = command.split("-");
            if(spltCmd[0].equals("Signup")){
                type = spltCmd[1];
            }
            switch (spltCmd[0]) {
                case "Signup":
                txt = UserController.getUserController().signupNewUser(spltCmd[1],spltCmd[2],spltCmd[3],spltCmd[4],spltCmd[5],spltCmd[6],spltCmd[7],spltCmd[8]);
                System.out.println(txt);
                    break;
                case "FavouriteGenres":
                if(type.equals("L")){
                    String [] genres = spltCmd[1].split(",");
                    ArrayList<String> genre= new ArrayList<>();
                    for(String string : genres){
                        genre.add(string);
                    }
                    //
                    ListenerController.getListenerController().getFavoriteGenres(genre);
                    ArrayList t = ListenerController.getListenerController().getListener().getFavoriteGenres();
                    System.out.println(t);
                }
                else System.out.println("only listeners can choose FavouriteGenres!");
                    
                    break;
                case "Login":
                String charachter = UserController.getUserController().loginUser(spltCmd[1],spltCmd[2]);
                if(charachter.equals("L")){
                    ListenerView.getListenerView().ListenerView();
                }
                if(charachter.equals("A")){
                    System.out.println("you've loged in successfully");
                    ArtistView.getArtistView().ArtistView();
                }
                if(charachter.equals("null")){
                    System.out.println("null");
                }
                else{
                    System.out.println("not a valid username or password try again");
                }
                    break;
                case "Logout":
                    
                    break;
            
                
                default:
                    break;
            }
            a--;
        }
    }
}
