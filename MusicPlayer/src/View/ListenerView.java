package View;

import Controller.ListenerController;
import Model.Genre;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.util.*;
public class ListenerView {
     private ListenerController view = new ListenerController();
     private static ListenerView listener;
     private ListenerView(){}
     public static ListenerView getListener(){
         if(listener==null)
             listener = new ListenerView();
         return listener;
     }
     public void signUp(String userName,String password,String name,String email,String phoneNumber,Date birthDate){
         Scanner sin = new Scanner(System.in);
         if(view.signUp(userName, password, name, email, phoneNumber, birthDate).equals("Done!")) {
             System.out.println("Done!");
             String entry = sin.nextLine();
             String[] enters = entry.split("[-,]");
             if (enters[0].equals("FavouriteGenres")) {

                 for (int i = 0; i < enters.length - 1; i++) {
                     if (enters[i+1].equals(Genre.Country.name()))
                         view.selectGenres(Genre.Country);
                     else if (enters[i+1].equals(Genre.Pop.name()))
                         view.selectGenres(Genre.Pop);
                     else if (enters[i+1].equals(Genre.HipHop.name()))
                         view.selectGenres(Genre.HipHop);
                     else if (enters[i+1].equals(Genre.History.name()))
                         view.selectGenres(Genre.History);
                     else if (enters[i+1].equals(Genre.Interview.name()))
                         view.selectGenres(Genre.Interview);
                     else if (enters[i+1].equals(Genre.Jazz.name()))
                         view.selectGenres(Genre.Jazz);
                     else if (enters[i+1].equals(Genre.Rock.name()))
                         view.selectGenres(Genre.Rock);
                     else if (enters[i+1].equals(Genre.Society.name()))
                         view.selectGenres(Genre.Society);
                     else
                         view.selectGenres(Genre.TrueCrime);
                 }
                 System.out.println("Done!");
             } else {
                 System.out.println("Wrong command!");
                 signUp(userName, password, name, email, phoneNumber, birthDate);
             }
         }
         else
             System.out.println(view.signUp(userName, password, name, email, phoneNumber, birthDate));

     }
     public void login(String userName,String password){

         if(view.logIn(userName, password).equals("Done!")) {
           System.out.println(view.logIn(userName, password));
           second();
         }
         else
             System.out.println(view.logIn(userName, password));
     }
     public void second(){
         Scanner sin = new Scanner(System.in);
         String command="";
         while ((!command.equals("Logout"))) {
             command = sin.nextLine();
             String[] commands = command.split("[-+]");
             if(commands[0].equals("GetSuggestions"))
                 System.out.println(view.getSuggestions(Integer.parseInt(commands[1])));
             else if(commands[0].equals("Artists"))
                 System.out.println(view.showArtistList());
             else if(commands[0].equals("Artist"))
                 System.out.println(view.showArtist(commands[1]));
             else if(commands[0].equals("Follow"))
                 view.followArtist(commands[1]);
             else if(commands[0].equals("Search"))
                 System.out.println(view.searchAudio(commands[1]));
             else if(commands[0].equals("Sort")){
                 if(commands[1].equals("L"))
                     view.sortingAudioFilesBasedOnLikes();
                 else
                     view.sortingAudioFilesBasedOnPlayCount();
             }
             else if(commands[0].equals("Filter")){
                 if(commands[1].equals("A"))
                     System.out.println(view.filterAudioFilesBasedOnArtist(commands[2]));
                 else if(commands[1].equals("G")){
                     if(commands[2].equals(Genre.Country.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.Country));
                     else if(commands[2].equals(Genre.Pop.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.Pop));
                     else if(commands[2].equals(Genre.HipHop.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.HipHop));
                     else if(commands[2].equals(Genre.History.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.History));
                     else if(commands[2].equals(Genre.Interview.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.Interview));
                     else if(commands[2].equals(Genre.Jazz.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.Jazz));
                     else if(commands[2].equals(Genre.Rock.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.Rock));
                     else if(commands[2].equals(Genre.Society.name()))
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.Society));
                     else
                         System.out.println(view.filterAudioFilesBasedOnGenres(Genre.TrueCrime));
                 }
                 else
                     if(commands[3]!=null)
                         System.out.println(view.filterAudioFilesBasedAmongDate(new Date(commands[2]),new Date(commands[3])));
                     else
                         System.out.println(view.filterAudioFilesBasedOnDate(new Date(commands[2])));
             }
             else if(commands[0].equals("Add"))
                 System.out.println(view.addMusicToPlaylist(commands[1],Integer.parseInt(commands[2])));
             else if(commands[0].equals("ShowPlaylists"))
                 System.out.println(view.showPlaylists());
             else if(commands[0].equals("SelectPlaylist"))
                 System.out.println(view.selectPlaylist(commands[1]));
             else if(commands[0].equals("Play"))
                 System.out.println(view.playAudio(Integer.parseInt(commands[1])));
             else if(commands[0].equals("Like")){
                 view.likeAudio(Integer.parseInt(commands[1]));
                 System.out.println("Done!");
             }
             else if(commands[0].equals("Lyric"))
                 System.out.println(view.showLyric(Integer.parseInt(commands[1])));
             else if(commands[0].equals("NewPlaylist"))
                 System.out.println(view.makeNewPlaylist(commands[1],"playlist"));
             else if(commands[0].equals("Followings"))
                 System.out.println(view.showFollowing());
             else if(commands[0].equals("Report")){
                 view.ArtistReport(commands[1],commands[2]);
                 System.out.println("Done!");
             }
             else if(commands[0].equals("IncreaseCredit")){
                 view.increaseCredit(Integer.parseInt(commands[1]));
                 System.out.println("Done!");
             }
             else if(commands[0].equals("GetPremium"))
                 System.out.println(view.buyOrExtendSubscription(Integer.parseInt(commands[1])));
             else if(!commands[0].equals("Logout"))
                 System.out.println("Wrong command!");
         }
     }
}

