package view;

import controller.ListenerController;

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
    }
    
}

