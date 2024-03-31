package controller;

import java.util.Date;

import model.database.Database;
import model.user.Listener;

public class ListenerController {
    private static ListenerController listenerController;
    private ListenerController(){

    }
    public static ListenerController getListenerController(){
        if(listenerController==null){
            listenerController = new ListenerController();
        }
        return listenerController;
    }
    private Listener listener;
    public Listener getListener(){
        return listener;
    }
    public void setListener(Listener listener){//in login
        this.listener = listener;
    }

    public void signupNewListener(String password,String username, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit){
        Listener tmp = new Listener(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, accountCredit);
        Database.getDatabase().addToAllUsers(tmp);
    }
    
}
