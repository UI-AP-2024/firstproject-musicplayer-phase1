package View;

import Controller.ArtistController;
import Controller.PodcasterController;
import Controller.SingerController;
import Model.ArtistModel;
import Model.Genre;

import java.util.Date;
import java.util.Scanner;

public class ArtistView {
    private ArtistController view=new ArtistController();
    private static ArtistView artistView;
    private ArtistView(){}
    public static ArtistView getArtistView(){
        if(artistView==null)
            artistView = new ArtistView();
        return artistView;
    }
    public void signUp(String artist,String userName,String password,String name,String email,String phoneNumber,Date birthDate,String bio){
        System.out.println(view.signUp(artist,userName, password, name, email, phoneNumber, birthDate, bio));
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
            String[] commands = command.split("-");
            if(commands[0].equals("Followers"))
                System.out.println(view.showFollowers());
            else if(commands[0].equals("CalculateEarnings"))
                System.out.println(view.showUserInfo());
            else if(commands[0].equals("ViewsStatistics"))
                System.out.println(view.showPlayedCount());
            else if(commands[0].equals("NewAlbum")){
                view.newAlbum(commands[1]);
                System.out.println("Done!");
            }
            else if(commands[0].equals("Publish")){
                if(commands[1].equals("M")){
                    if(commands[3].equals(Genre.Country.name())) {
                        view.publishMusic(commands[2], Genre.Country, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.Pop.name())) {
                        view.publishMusic(commands[2], Genre.Pop, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.HipHop.name())) {
                        view.publishMusic(commands[2], Genre.HipHop, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.History.name())) {
                        view.publishMusic(commands[2], Genre.History, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.Interview.name())) {
                        view.publishMusic(commands[2], Genre.Interview, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.Jazz.name())) {
                        view.publishMusic(commands[2], Genre.Jazz, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.Rock.name())) {
                        view.publishMusic(commands[2], Genre.Rock, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.Society.name())) {
                        view.publishMusic(commands[2], Genre.Society, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }
                    else if(commands[3].equals(Genre.TrueCrime.name())){
                        view.publishMusic(commands[2], Genre.TrueCrime, commands[4], commands[5], commands[6], Integer.parseInt(commands[7]));
                        System.out.println("Done!");
                    }

                }
                else {
                    view.publishPodcast(commands[2], Genre.TrueCrime, commands[4], commands[5], commands[6]);
                    System.out.println("Done!");
                }
            }
            else if(!commands[0].equals("Logout"))
                System.out.println("Wrong command!");
        }


    }
}
