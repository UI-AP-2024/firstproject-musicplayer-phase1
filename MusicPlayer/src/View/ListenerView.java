package View;

import Controller.ListenerController;
import Model.Genre;

import java.util.*;
public class ListenerView {
     private ListenerController view;
     private static ListenerView listener;
     private ListenerView(){}
     public static ListenerView getListener(){
         if(listener==null)
             listener = new ListenerView();
         return listener;
     }
     public void signUp(){
         Scanner sin = new Scanner(System.in);
         String entry=sin.nextLine();
         String[] enter = entry.split("-");
         if(enter[0].equals("Signup")){
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
                     login();
                 }
                 else
                     System.out.println("Wrong command!");

         }
         else if(enter[0].equals("Login"))
             login();
         else
             System.out.println("Wrong command!");
     }
     public void login(){
         Scanner sin = new Scanner(System.in);
         String entry=sin.nextLine();
         String[] enter = entry.split("-");
         if(enter[0].equals("Login")){
             if(!view.logIn(enter[2],enter[3]).equals("Done!")) {
                 System.out.println(view.logIn(enter[2],enter[3]));
                 login();
             }
             System.out.println(view.logIn(enter[2],enter[3]));
             second();
         }

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
             else if(commands[0].equals(" GetPremium"))
                 System.out.println(view.buyOrExtendSubscription(Integer.parseInt(commands[1])));
             else
                 System.out.println("Wrong command!");
         }
     }
}

