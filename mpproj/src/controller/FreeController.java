package controller;

import model.audio.PlayList;
import model.user.FreeListener;

public class FreeController extends ListenerController {
    private static FreeController freeController;
    private FreeController(){
        super();
    }
    public static FreeController getFreeController(){
        if(freeController==null){
            freeController = new FreeController();
        }
        return freeController;
    }
    private FreeListener freeListener;
    public FreeListener getFreeListener(){
        return freeListener;
    }
    public void setFreeListener(FreeListener freeListener){//in login
        this.freeListener = freeListener;
    }
     
    @Override
    public String createNewPlaylist(String name){
        System.out.println("k");
        if(getFreeListener().getListOfPlayLists().size()<FreeListener.getPlayListLimit()){
            getFreeListener().addToListOfPlayLists(new PlayList(name, getFreeListener().getUsername()));
            return "your playlist created";
        }
        return "you cant create more playlists if you want , get a premium account";
    }
}
