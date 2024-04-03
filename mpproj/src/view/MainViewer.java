package view;

import controller.AdminControler;
import controller.ArtistControler;
import controller.ListenerControler;

import java.util.Date;
import java.util.Scanner;

public class MainViewer {
    private static MainViewer mainViewer;
    Scanner sc = new Scanner(System.in);
    private String answer;
    String[] answers;

    public MainViewer() {
    }

    public static MainViewer getMainViewer() {
        if(mainViewer==null)
            mainViewer=new MainViewer();
        return mainViewer;
    }
    public void getanswer() {
        answer = sc.nextLine();
        answers = answer.split("-");
        if(answers[0].equals("Signup")){
            signup();
        } else if (answers[0].equals("Login")) {
            login();
        }else{
            print("wrong command");
            getanswer();
        }
    }
    public void signup(){
        String error1="use valid phone number";
        String error2="use harder password";
        String error3="use valid email";
        String massage;
        String[]dateStr;

        if(answers[1].equals("L")||answers[1].equals("P")||answers[1].equals("S"))
             dateStr = answers[7].split("/");
        else
            dateStr=answers[6].split("/");
        Date date = new Date(Integer.parseInt(dateStr[0]), Integer.parseInt(dateStr[1]), Integer.parseInt(dateStr[2]));
        if(answers[1].equals("L")) {
            massage=ListenerControler.getListenerControler().signUpListener(answers[2], answers[3], answers[4], answers[5], answers[6],date);
            print(massage);
            if(massage.equals("error : this user name already exist .")||massage.equals(error1) || massage.equals(error2) || massage.equals(error3 ))
                getanswer();
            String favGenre=sc.nextLine();
            String[] favgenres=favGenre.split(",");
            if(favgenres.length==4)
            print(ListenerControler.getListenerControler().chooseFavoriteGenre(favgenres[0],favgenres[1],favgenres[2],favgenres[3]));
            else if(favgenres.length==3)
                print(ListenerControler.getListenerControler().chooseFavoriteGenre(favgenres[0],favgenres[1],favgenres[2],""));
            else if(favgenres.length==2)
                print(ListenerControler.getListenerControler().chooseFavoriteGenre(favgenres[0],favgenres[1],"",""));
            else if(favgenres.length==1)
                print(ListenerControler.getListenerControler().chooseFavoriteGenre(favgenres[0],"","",""));
            ListenerViewer.getListenerViewer().getAnswer();
        }else if(answers[1].equals("P")){
            massage=ArtistControler.getArtistControler().signUpArtist(answers[2], answers[3], answers[4], answers[5], answers[6], date,answers[8],"P");
            print(massage);
            if(massage.equals("error : this user name already exist .")||massage.equals(error1) || massage.equals(error2) || massage.equals(error3 ))
                getanswer();
            ArtistViewer.getArtistViewer().getAnswer();
        }else if(answers[1].equals("S")){
            massage=ArtistControler.getArtistControler().signUpArtist(answers[2], answers[3], answers[4], answers[5], answers[6], date,answers[8],"S");
            print(massage);
            if(massage.equals("error : this user name already exist .")||massage.equals(error1) || massage.equals(error2) || massage.equals(error3 ) )
                getanswer();
            ArtistViewer.getArtistViewer().getAnswer();
        }else{
            String[]dateStr1=answers[6].split("/");
            Date date1=new Date(Integer.parseInt(dateStr1[0]),Integer.parseInt(dateStr1[1]),Integer.parseInt(dateStr1[2]));
            massage=AdminControler.getAdminControler().signUpAdmin(answers[1], answers[2], answers[3], answers[4], answers[5], date);
            print(massage);
            if(massage.equals("error : this user name already exist .") ||massage.equals(error1) || massage.equals(error2) || massage.equals(error3 ))
                getanswer();
            AdminViewer.getAdminViewer().getAnswer();
        }
    }
    public void login(){
        String message;
        if(answers[1].equals("L")){
            message=ListenerControler.getListenerControler().login(answers[2],answers[3]);
            print(message);
            if(message.equals("error : user name or password is wrong"))
                getanswer();
            ListenerViewer.getListenerViewer().getAnswer();
        } else if (answers[1].equals("S")) {
            message=ArtistControler.getArtistControler().login(answers[2],answers[3]);
            print(message);
            if(message.equals("error : user name or password is wrong"))
                getanswer();
            ArtistViewer.getArtistViewer().getAnswer();
        }else if (answers[1].equals("P")) {
            message=ArtistControler.getArtistControler().login(answers[2], answers[3]);
            print(message);
            if(message.equals("error : user name or password is wrong"))
                getanswer();
            PodcasterViewer.getPodcasterViewer().getAnswer();
        }else{
            message=AdminControler.getAdminControler().login(answers[1],answers[2]);
            print(message);
            if(message.equals("error : user name or password is wrong"))
                getanswer();
            AdminViewer.getAdminViewer().getAnswer();
        }
    }
    private void print(Object object){
        System.out.println(object);
    }
}