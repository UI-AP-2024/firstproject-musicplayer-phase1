package controller;

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
    public void setListener(Listener listener){
        this.listener = listener;
    }

    
    
}
