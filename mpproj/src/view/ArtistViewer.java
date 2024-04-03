package view;

import controller.ArtistControler;
import controller.SingerControler;

import java.util.Scanner;

public class ArtistViewer {
    private static ArtistViewer artistViewer;

    public ArtistViewer() {
    }

    public static ArtistViewer getArtistViewer() {
        if (artistViewer == null)
            artistViewer = new ArtistViewer();
        return artistViewer;
    }

    Scanner sc = new Scanner(System.in);
    private String answer;
    String[] answers;

    public void getAnswer() {
        answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Logout")) {
            logout();
        } else if (answers[0].equals("Followers")) {
            followers();
        } else if (answers[0].equals("ViewStatistics")) {
            viewStatic();
        } else if (answers[0].equals("CalculationEarnings")) {
            calculationEarning();
        } else if (answers[0].equals("NewAlbum")) {
            newAlbum();
        } else if (answers[0].equals("Publish")) {
            publish();
        } else if (answers[0].equals("AccountInfo")) {
            accountInfo();
        }else{
            print("wrong command");
            getAnswer();
        }
    }
    public void followers(){
        print(ArtistControler.getArtistControler().showFollowers());
        getAnswer();
    }
    public void newAlbum(){
        print(ArtistControler.getArtistControler().newAlbum(answers[1]));
        getAnswer();
    }
    public void calculationEarning(){
        print(ArtistControler.getArtistControler().calculateEarning());
        getAnswer();
    }
    public void accountInfo(){
        print(ArtistControler.getArtistControler().artistInfo());
        getAnswer();
    }
    public void viewStatic(){
        print(ArtistControler.getArtistControler().viewStatic());
        getAnswer();
    }
    public void publish(){
        if(answers[1].equals("M"))
        print(ArtistControler.getArtistControler().publishMusic(answers[2],answers[3],answers[4],answers[5],answers[6], Integer.parseInt(answers[7])));
        else if(answers[1].equals("P"))
            print(ArtistControler.getArtistControler().publishPodcast(answers[2],answers[3],answers[4],answers[5],answers[6]));
        getAnswer();
    }
    public void logout(){
        print(ArtistControler.getArtistControler().logout());
        MainViewer.getMainViewer().getanswer();
    }
    private void print(Object object){
        System.out.println(object);
    }
}
