package view;

import controller.ArtistController;
import controller.Controller;
import controller.ListenerController;

import java.util.Scanner;

public class View
{
    Scanner input=new Scanner(System.in);
    private static View view;
    private View(){}
    public static View getView()
    {
        if(view==null)
            view=new View();
        return view;
    }
    public void showFirstMenu()
    {
        while(true)
        {
            String order=input.nextLine();
            String[] orders=order.split(" -");
            if(orders[0].compareTo("Signup")==0)
            {
                if(orders[1].compareTo("L")==0)
                {
                    System.out.println(ListenerController.getListenerController().makeNewListener(orders[2],orders[3],orders[4],orders[5],orders[6],orders[7]));
                    System.out.println("Choose maximum 4 favorite genres from genres below:\n"+ListenerController.getListenerController().getGenres());
                    String favInputGenre=input.nextLine();
                    String[] inputSplit=favInputGenre.split("-");
                    String[] genres=inputSplit[1].split(",");
                    int counter=0;
                    while(counter<=4 && counter<genres.length)
                    {
                        if(!ListenerController.getListenerController().addFavGenres(genres[counter]))
                            break;
                        counter++;
                    }
                }
                else
                    System.out.println(ArtistController.getArtistController().makeNewArtist(orders[2],orders[3],orders[4],orders[5],orders[6],orders[7],orders[8],orders[1]));
            }
            else if(orders[0].compareTo("Login")==0)
            {
                System.out.println(Controller.getController().logIn(orders[1],orders[2]));
                if(Controller.getController().getAccType().compareTo("L")==0)
                    showListenerPanel();
                /////kxnvkabvkbvufebv
                break;
            }
            else if (orders[0].compareTo("Logout")==0)
            {
                if(Controller.getController().getAccType().compareTo("L")==0)
                    ListenerController.getListenerController().setListener(null);
                else
                    ArtistController.getArtistController().setArtist(null);
                break;
            }
            else if(orders[0].compareTo("AccountInfo")==0)
            {
                if(Controller.getController().getAccModel()!=null)
                    System.out.println(Controller.getController().getAccModel().toString());
                else
                    System.out.println("not logged in");
            }
            else
                System.out.println("wrong order\ntry again");
        }
    }
    public void showListenerPanel()
    {
        while(true)
        {
            String order=input.nextLine();
            String[] orders=order.split(" -");
            if(orders[0].compareTo("Add")==0)
                System.out.println(ListenerController.getListenerController().addToPlayList(orders[1],orders[2]));
            else if(orders[0].compareTo("Artists")==0)
                System.out.println(ListenerController.getListenerController().showArtists());
            else if(orders[0].compareTo("Artist")==0)
                System.out.println(ListenerController.getListenerController().showArtist(orders[1]));
            else if(orders[0].compareTo("Follow")==0)
                System.out.println(ListenerController.getListenerController().followArtist(orders[1]));
            else if(orders[0].compareTo("Search")==0)
                System.out.println(ListenerController.getListenerController().search(orders[1]));
            else if(orders[0].compareTo("Sort")==0)
                System.out.println(ListenerController.getListenerController().sort(orders[1]));
            else if(orders[0].compareTo("Filter")==0)
                System.out.println(ListenerController.getListenerController().doFilter(orders[1],orders[2]));
            else if (orders[0].compareTo("Logout")==0)
            {
                ListenerController.getListenerController().setListener(null);
                break;
            }
            else if(orders[0].compareTo("AccountInfo")==0)
                System.out.println(Controller.getController().getAccModel().toString());
            else
                System.out.println("wrong order\ntry again");
        }
    }
}
