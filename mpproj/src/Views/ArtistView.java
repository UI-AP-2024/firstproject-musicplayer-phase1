package view;

import Controller.AdminController;
import Controller.ArtistController;
import Controller.ListenerController;
import Model.*;

import java.util.ArrayList;
import java.util.Scanner;

import static view.View.answers;
import static view.View.firstPage;

public class ArtistView {
    private static ArtistView artistView;


    private ArtistView() {
    }

    public static ArtistView getArtistView() {
        if (artistView == null)
            artistView = new ArtistView();
        return artistView;
    }

    Scanner sc = new Scanner(System.in);


     public void artistPanel()
    {
        String[] inputs;
        do {
            System.out.println("Artist Panel");
            String input=sc.nextLine();
             inputs=input.split("-");
            switch (inputs[0].trim()) {
                case "Followers":
                    System.out.println(ArtistController.getArtistController().showFollowers());
                    break;
                case "ViewsStatistics":
                    System.out.println(ArtistController.getArtistController().showPlayCount());
                    break;
                case "CalculateEarnings":
                    System.out.println(ArtistController.getArtistController().showSalary());
                    break;
                case "NewAlbum":
                    if (ArtistController.getArtistController().getArtist() instanceof Singer) {
                        System.out.println(ArtistController.getArtistController().newAlbum(inputs[1].trim()));
                    } else {
                        System.out.println("Incorrect command(new Album is Only for Singer)");
                    }
                    break;
                case "Publish":
                    if (ArtistController.getArtistController().getArtist() instanceof Singer) {
                        System.out.println(ArtistController.getArtistController().publishMusic(inputs[1].trim(), inputs[2].trim(),
                                Genre.valueOf(inputs[3].trim().toUpperCase()), inputs[4].trim(), inputs[5].trim(), inputs[6].trim(), inputs[7].trim()));
                    }else{
                        System.out.println(ArtistController.getArtistController().publishPodcast(inputs[1].trim(), inputs[2].trim(),
                                Genre.valueOf(inputs[3].trim().toUpperCase()), inputs[4].trim(), inputs[5].trim(), inputs[6].trim()));
                    }
                    break;
                case "AccountInfo":
                    System.out.println(ArtistController.getArtistController().getArtist());
                    break;
                case "Help":
                    System.out.println("Commands:\nFollowers\n,ViewsStatistics\n,CalculateEarnings\n,NewAlbum\n,Publish\n" +
                            "Logout\n,AccountInfo\n");
                    break;
                default:
                    if (!(inputs[0].trim().equals("Logout"))) {
                        System.out.println("Incorrect command (Use <Help> for more Info!)");
                    }
            }
        }while (!(inputs[0].trim().equals("Logout")));
    }
    public void SignUp_S() {
        Singer singer = new Singer(answers[2].trim(), answers[3].trim(), answers[4].trim(), answers[5].trim()
                , answers[6].trim(), Integer.parseInt(answers[7].trim()), Integer.parseInt(answers[8].trim()), Integer.parseInt(answers[9].trim())
                , answers[10].trim());
        ArtistController.getArtistController().setArtist(singer);
        DataBase.getDataBase().getUsersList().add(singer);
        System.out.println("Registered Successfully!");
    }
    public void SignUp_P(){
        Podcaster podcaster=new Podcaster(answers[2].trim(),answers[3].trim(), answers[4].trim(), answers[5].trim()
                , answers[6].trim(), Integer.parseInt(answers[7].trim()), Integer.parseInt(answers[8].trim()), Integer.parseInt(answers[9].trim())
                , answers[10].trim() );
        ArtistController.getArtistController().setArtist(podcaster);
        DataBase.getDataBase().getUsersList().add(podcaster);
        System.out.println("Registered Successfully!");
    }
}
