package view;

import controller.ListenerControler;
import model.SubscriptionPlan;

import java.util.Date;
import java.util.Scanner;

public class ListenerViewer {
    private  static ListenerViewer listenerViewer;

    public ListenerViewer() {
    }

    public static ListenerViewer getListenerViewer() {
        if (listenerViewer == null)
            listenerViewer=new ListenerViewer();
        return listenerViewer;
    }

    Scanner sc=new Scanner(System.in);
    private String answer;
    String[]answers;
    public void getAnswer() {
        answer = sc.nextLine();
        answers = answer.split("-");
        if (answers[0].equals("Logout"))
            logOut(answer);
        else if (answers[0].equals("Follow"))
            follow();
        else if (answers[0].equals("AccountInfo"))
            accountInfo(answer);
        else if (answers[0].equals("GetSuggestion"))
            getSuggestion(answer);
        else if (answers[0].equals("Artist"))
            artist();
        else if (answers[0].equals("Artists"))
            artists();
        else if (answers[0].equals("Sort")) {
            if (answers[1].equals("L"))
                sortLike();
            else if (answers[1].equals("P"))
                sortPlay();
        }else if (answers[0].equals("Search"))
            search();
        else if (answers[0].equals("Filter"))
            filter();
        else if (answers[0].equals("Add"))
            add();
        else if (answers[0].equals("ShowPlaylists"))
            showPlaylists();
        else if (answers[0].equals("SelectPlaylist"))
            selectPlaylists();
        else if (answers[0].equals("Play"))
            play();
        else if (answers[0].equals("Like"))
            like();
        else if (answers[0].equals("Lyric"))
            lyric();
        else if (answers[0].equals("NewPlaylist"))
            newPlaylist();
        else if (answers[0].equals("Followings"))
                followings();
        else if (answers[0].equals("Report"))
            reports();
        else if (answers[0].equals("IncreaseCredit"))
            increaseCredit();
        else if (answers[0].equals("GetPremium"))
            getpremium();

        else{
            print("wrong command");
            getAnswer();
        }
    }
    public void follow(){
        print(ListenerControler.getListenerControler().followArtist(answers[1]));
        getAnswer();
    }
    public void filter(){
        if(answers[1].equals("A"))
            print(ListenerControler.getListenerControler().filterArtist(answers[2]));
        else if(answers[1].equals("G"))
            print(ListenerControler.getListenerControler().filterbyGenre(answers[2]));
        else if (answers[1].equals("D")) {
            String[] dateStr1 = answers[2].split("/");
            Date startDate = new Date(Integer.parseInt(dateStr1[0]), Integer.parseInt(dateStr1[1]), Integer.parseInt(dateStr1[2]));
            if(answers.length==4) {
                String[] dateStr2 = answers[3].split("/");
                Date endDate = new Date(Integer.parseInt(dateStr2[0]), Integer.parseInt(dateStr2[1]), Integer.parseInt(dateStr2[2]));
                print(ListenerControler.getListenerControler().filterDate(startDate, endDate));
            } else if (answers.length==3) {
                print(ListenerControler.getListenerControler().filterDate(startDate));
            }
        }
        getAnswer();
    }
    public void search(){
        print(ListenerControler.getListenerControler().searchByAudio(answers[1]));
        print(ListenerControler.getListenerControler().searchByArtistName(answers[1]));
        getAnswer();
    }
    public void sortLike(){
        print(ListenerControler.getListenerControler().sortLikes());
        getAnswer();
    }
    public void sortPlay(){
        print(ListenerControler.getListenerControler().sortPlay());
        getAnswer();
    }

    public void getpremium(){
        print(ListenerControler.getListenerControler().buySub(SubscriptionPlan.valueOf(answers[1])));
        getAnswer();
    }
    public void increaseCredit(){
        print(ListenerControler.getListenerControler().increaseCredit(Integer.parseInt(answers[1])));
        getAnswer();
    }
    public void reports(){
        print(ListenerControler.getListenerControler().reportArtist(answers[1],answers[2]));
        getAnswer();
    }
    public void followings(){
        print(ListenerControler.getListenerControler().showFollowing());
        getAnswer();
    }
    public void logOut(String str){
        String[]results=answer.split("-");
        print(ListenerControler.getListenerControler().logout());
        MainViewer.getMainViewer().getanswer();
    }
    public void accountInfo(String str){
        String[] result=answer.split("-");
        print(ListenerControler.getListenerControler().userInfo());
        getAnswer();
    }
    public void getSuggestion(String str){
        String[] result=answer.split("-");
        print(ListenerControler.getListenerControler().suggestAudio(Integer.parseInt(result[1])));
        getAnswer();
    }
    public void  artist(){
        print(ListenerControler.getListenerControler().showArtist(answers[1]));
        getAnswer();
    }
    public void artists(){
        print(ListenerControler.getListenerControler().showArtists());
        getAnswer();
    }
    public void add(){
        print(ListenerControler.getListenerControler().AddAudio(answers[1], Integer.parseInt(answers[2])));
        getAnswer();
    }
    public void showPlaylists(){
        print(ListenerControler.getListenerControler().showmyPlaylist());
        getAnswer();
    }
    public void selectPlaylists(){
        print(ListenerControler.getListenerControler().myListInfo(answers[1]));
        getAnswer();
    }
    public void  play(){
        print(ListenerControler.getListenerControler().playAudio(Integer.parseInt(answers[1])));
        getAnswer();
    }

    public void like(){
        print(ListenerControler.getListenerControler().likeAudio(Integer.parseInt(answers[1])));
        getAnswer();
    }

    public void lyric(){
        print(ListenerControler.getListenerControler().lyric(Integer.parseInt(answers[1])));
        getAnswer();
    }
    public void newPlaylist(){
        print(ListenerControler.getListenerControler().makePlaylist(answers[1]));
        getAnswer();
    }


    private void print(Object object){
        System.out.println(object);
    }


}
