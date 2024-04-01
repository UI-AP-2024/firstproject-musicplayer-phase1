package view;

import controller.ListenerControler;

import java.util.Date;
import java.util.Scanner;

public class MainViewer {
    private static MainViewer mainViewer;

    public MainViewer() {
    }

    public static MainViewer getMainViewer() {
        if(mainViewer==null)
            mainViewer=new MainViewer();
        return mainViewer;
    }

    Scanner sc=new Scanner(System.in);
    public void signup(){
        String answer=sc.nextLine();
        String[]answers=answer.split("-");
        String[]dateStr=answers[7].split("/");
        Date date=new Date(Integer.parseInt(dateStr[0]),Integer.parseInt(dateStr[1]),Integer.parseInt(dateStr[2]));
        if(answers[1].equals("L")) {
            print(ListenerControler.getListenerControler().signUpListener(answers[2], answers[3], answers[4], answers[5], answers[6], date));
            String favGenre=sc.nextLine();
            String[] favgenres=favGenre.split(",");
            print(ListenerControler.getListenerControler().chooseFavoriteGenre(favgenres[0],favgenres[1],favgenres[2],favgenres[3]));
            ListenerViewer.getListenerViewer();
        }

    }
    private void print(Object object){
        System.out.println(object);
    }
}
            //answers[0]= username, String pasword, String name, String email, String phoneNum, String birthDate
