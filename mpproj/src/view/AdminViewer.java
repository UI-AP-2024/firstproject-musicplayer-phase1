package view;

import controller.AdminControler;

import java.util.Scanner;

public class AdminViewer {
    private static AdminViewer adminViewer;

    Scanner sc = new Scanner(System.in);
    private String answer;
    String[] answers;

    public AdminViewer() {
    }

    public static AdminViewer getAdminViewer() {
        if (adminViewer == null)
            adminViewer = new AdminViewer();
        return adminViewer;
    }

    public void getAnswer() {
        answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Logout"))
            logout();
         else if (answers[0].equals("Artists"))
            artists();
         else if (answers[0].equals("Audios"))
            audios();
         else if (answers[0].equals("Audio"))
            audioInfo();
        else if (answers[0].equals("AccountInfo"))
            artist();
        else if (answers[0].equals("Statistic"))
            artist();
        else if (answers[0].equals("Artist"))
            artist();
         else if (answers[0].equals("Reports"))
            reports();
         else {
            print("wrong command");
            getAnswer();
        }
    }
    protected
    public void audioInfo(){
        print(AdminControler.getAdminControler().lookAudioInfo(Integer.parseInt(answers[1])));
        getAnswer();
    }
    public void audios(){
        print(AdminControler.getAdminControler().lookAudios());
        getAnswer();
    }
    public void reports(){
        print(AdminControler.getAdminControler().lookReport());
        getAnswer();
    }
    public void artist(){
        print(AdminControler.getAdminControler().lookArtistInfo(answers[1]));
        getAnswer();
    }
    public void artists(){
        print(AdminControler.getAdminControler().lookArtists());
        getAnswer();
    }
    public void logout(){
        print(AdminControler.getAdminControler().logout());
        MainViewer.getMainViewer().getanswer();
    }
    private void print (Object object){
        System.out.println(object);
    }
}
