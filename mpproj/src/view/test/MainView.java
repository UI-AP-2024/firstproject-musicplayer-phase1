package view.test;
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
import model.database.Database;
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
        Database.getDatabase().addToAllUsers(admin);
        Scanner sc = new Scanner(System.in);
        String command;
        String txt;
        String character="out";
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
                if(spltCmd.length==9){
                    txt = UserController.getUserController().signupNewUser(spltCmd[1],spltCmd[2],spltCmd[3],spltCmd[4],spltCmd[5],spltCmd[6],spltCmd[7],spltCmd[8]);
                    System.out.println(txt);
                }
                if(spltCmd.length==8){
                    txt = UserController.getUserController().signupNewUser(spltCmd[1],spltCmd[2],spltCmd[3],spltCmd[4],spltCmd[5],spltCmd[6],spltCmd[7],null);
                    System.out.println(txt);
                }
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
                
                character = UserController.getUserController().loginUser(spltCmd[1],spltCmd[2]);
                if(character.equals("null")){
                    System.out.println("not a valid username or password try again");
                }
                else{
                    System.out.println("you've loged in successfully");
                }
                    break;
                case "Logout":
                character="out";
                System.out.println("you've logged out");
                    
                    break;
            
                
                default:
                if(character.equals("out")||character.equals("null")){
                    System.out.println("you dont have access to this command");
                }
                if(character.equals("A")){
                    ArtistView.getArtistView().ArtistView(spltCmd);
                }
                if(character.equals("L")){
                    ListenerView.getListenerView().ListenerView(spltCmd);
                }
                if(character.equals("Admin")){
                    AdminView.getAdminView().AdminView(spltCmd);
                }
                    break;
            }
            a--;
            System.out.println("\n");
        }
    }
}
