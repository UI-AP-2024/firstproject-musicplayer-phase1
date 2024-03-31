package view;

import java.util.ArrayList;

import controller.ListenerController;
import controller.UserController;
import model.audio.Genre;
import model.user.Listener;
import model.user.User;

public class DebugView {

    private static DebugView debugView;
    private DebugView(){

    }
    public static DebugView getDebugView(){
        if(debugView==null){
            debugView = new DebugView();
        }
        return debugView;
    }

    public void firstMenu(){
        System.out.println("1)sign up\n2)log in");
        System.out.println("enter :\n'L' if you are listener\n'S' if you are Singer\n'P' if you are poscaster");
        System.out.println("enter your [username] -[password] -[First name] -[Last name]-[email] -[phone number]\r\n" + //
                        "-[birthdate in year month day format] with a space in between");
        String txt = ListenerController.getListenerController().signupNewListener("Maryam","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        System.out.println(txt);
        txt = ListenerController.getListenerController().signupNewListener("Maryam","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        System.out.println(txt);
        txt = ListenerController.getListenerController().signupNewListener("nazanin","Maryamdar840", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        System.out.println(txt);
        txt = ListenerController.getListenerController().signupNewListener("nazanin","Maryamdar84!", "Maryam", "Dar", "maryamdar13849gmail.com","09390555104",2005,5,10 );
        System.out.println(txt);
        txt = ListenerController.getListenerController().signupNewListener("nazanin","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        System.out.println(txt);
        txt=Genre.allGenres();
        System.out.println(txt+"\nenter your favourite genres (at most four), each one in one line");//has next line sc.nextline;
        ArrayList<String> genres = new ArrayList<>();
        genres.add("POP");
        genres.add("HIPHOP");
        genres.add("SOCIETY");
        ListenerController.getListenerController().getFavoriteGenres(genres);
        String a =String.valueOf(ListenerController.getListenerController().getListener().getFavoriteGenres().get(0));
        System.out.println(a);
        User tmp = UserController.getUserController().findUser("Maryap", "Maryamdar84!");
        if(tmp == null){
            System.out.println("not a valid username or password try again");
        }
        if(tmp instanceof Listener){
            System.out.println("Is listener");
        }
        tmp = UserController.getUserController().findUser("Maryam", "Maryamdar84!");
        if(tmp == null){
            System.out.println("not a valid username or password try again");
        }
        if(tmp instanceof Listener){
            System.out.println("Is listener");
            txt = ListenerController.getListenerController().ShowAccountInfo();
            System.out.println(txt);
        }

    }
    
}

