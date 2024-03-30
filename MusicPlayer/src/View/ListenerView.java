package View;

import Controller.ListenerController;
import Model.Genre;

import java.util.*;
public class ListenerView {
     private ListenerController view;
     public void signUp(){
         Scanner sin = new Scanner(System.in);
         String entry=sin.nextLine();
         String[] enter = entry.split("-");
         if(enter[0].equals("Signup")){
             if(enter[1].equals("L")) {
                 view.signUp(enter[2], enter[3], enter[4], enter[5], enter[6], new Date(enter[7]));
                 entry = sin.nextLine();
                 String[] enters = entry.split("-");
                 if(enters[0].equals("FavouriteGenres")){
                     String[] command = enters[1].split(",");
                     for (int i = 0; i < command.length; i++) {
                         if(command[i].equals(Genre.Country.name()))
                             view.selectGenres(Genre.Country);
                         else if(command[i].equals(Genre.Pop.name()))
                             view.selectGenres(Genre.Pop);
                         else if(command[i].equals(Genre.HipHop.name()))
                             view.selectGenres(Genre.HipHop);
                         else if(command[i].equals(Genre.History.name()))
                             view.selectGenres(Genre.History);
                         else if(command[i].equals(Genre.Interview.name()))
                             view.selectGenres(Genre.Interview);
                         else if(command[i].equals(Genre.Jazz.name()))
                             view.selectGenres(Genre.Jazz);
                         else if(command[i].equals(Genre.Rock.name()))
                             view.selectGenres(Genre.Rock);
                         else if(command[i].equals(Genre.Society.name()))
                             view.selectGenres(Genre.Society);
                         else
                             view.selectGenres(Genre.TrueCrime);
                     }
                 }
             }
             else{

             }

         }
         else if(enter[0].equals("Login"))
             ;
         else
             System.out.println("Wrong command!");
     }
}
