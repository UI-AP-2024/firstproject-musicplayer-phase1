package view;

import Controller.ListenerController;
import Model.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static view.View.answers;
import static view.View.firstPage;

public class ListenerView {
    private static ListenerView listenerView;

    private ListenerView() {
    }
    public static ListenerView getlistenerView() {
        if (listenerView == null)
            listenerView = new ListenerView();
        return listenerView;
    }
    Scanner sc = new Scanner(System.in);

    public void SignUp_L() {
        System.out.println("1.Rock, 2.Pop, 3.Jazz, 4.Hiphop, 5.Country\n," +
                "6.TrueCrime, 7.Society, 8.Interview, 9.History");
        FreeListener listener=new FreeListener(answers[2].trim(),answers[3].trim(),answers[4].trim(),answers[5].trim()
                ,answers[6].trim(),Integer.parseInt(answers[7].trim()),Integer.parseInt(answers[8].trim()),Integer.parseInt(answers[9].trim())
                ,50,new ArrayList<>());
            ListenerController.getListenerController().setListener(listener);
        System.out.println("Choose atmost your  4 favorite genres");
        String anser=sc.nextLine();
        String[] ansers=anser.split("-");
        if(ansers[0].trim().equals("FavouriteGenres")) {
            System.out.println(ListenerController.getListenerController().addFavoriteGenres(ansers[1].toUpperCase().trim(),listener));
            ListenerController.getListenerController().setListener(listener);
            DataBase.getDataBase().getUsersList().add(listener);
            System.out.println("Registered Successfully!");
        }else{
            System.out.println("Incorrect command!");
            SignUp_L();
        }

    }
    public void listenerPanel() {
        String[] inputs;
        do {
            System.out.println("Listener Panel");
            String input = sc.nextLine();
            inputs = input.split("-");
            switch (inputs[0].trim()) {
                case "Artists":
                    System.out.println(ListenerController.getListenerController().seeArtists());
                    break;
                case "Artist":
                    System.out.println(ListenerController.getListenerController().seeArtistsAudios(inputs[1].trim()));
                    break;
                case "Follow":
                    System.out.println(ListenerController.getListenerController().followArtist(inputs[1].trim()));
                    break;
                case "Search":
                    System.out.println(ListenerController.getListenerController().searchAudio(inputs[1].trim()));
                    break;
                case "Sort":
                    System.out.println(ListenerController.getListenerController().sortAudios(inputs[1].trim()));
                    break;
                case "Filter":
                    if(inputs[1].trim().equals("D")) {
                        filterAudios_D( inputs[2].trim(), inputs[3].trim(), inputs[4].trim());
                    }else if(inputs[1].trim().equals("A")||inputs[1].trim().equals("G")){
                        filterAudios_A_G(inputs[1].trim(),inputs[2].trim());
                    }else{
                    System.out.println("Incorrect sortKind!");
                    }
                    break;
                case "Add":
                    if(ListenerController.getListenerController().getListener() instanceof PremiumListener){
                        System.out.println(ListenerController.getListenerController().addAudio_P(inputs[1].trim(),inputs[2].trim()));
                    }else{
                        System.out.println(ListenerController.getListenerController().addAudio_F(inputs[1].trim(),inputs[2].trim()));
                    }
                    break;
                case "ShowPlaylists":
                    System.out.println(ListenerController.getListenerController().showPlaylists());
                    break;
                case  "SelectPlaylist":
                    System.out.println(ListenerController.getListenerController().showAudiosOfPlaylist(inputs[1].trim()));
                    break;
                case  "Play":
                    System.out.println(ListenerController.getListenerController().playAudio(inputs[1].trim()));
                    break;
                case  "Like":
                    System.out.println(ListenerController.getListenerController().likeAudio(inputs[1].trim()));
                    break;
                case  "Lyric":
                    System.out.println(ListenerController.getListenerController().showLyrics(inputs[1].trim()));
                    break;
                case "NewPlaylist":
                    if(ListenerController.getListenerController().getListener() instanceof FreeListener){
                        System.out.println(ListenerController.getListenerController().makePlaylist_F(inputs[1].trim()));
                    }else{
                        System.out.println(ListenerController.getListenerController().makePlaylist_P(inputs[1].trim()));
                    }
                    break;
                case "Followings":
                    System.out.println(ListenerController.getListenerController().showFollowings());
                    break;
                case "Report":
                    System.out.println(ListenerController.getListenerController().reportArtist(inputs[1].trim(),inputs[2].trim()));
                    break;
                case "IncreaseCredit":
                    System.out.println(ListenerController.getListenerController().addCredit(Long.parseLong(inputs[1].trim())));
                    break;
                case "GetPremium":
                    System.out.println(ListenerController.getListenerController().getPremium(PremiumPack.valueOf(inputs[1].trim())));
                    break;
                case "AccountInfo":
                    ListenerController.getListenerController().updateRemainDays();
                    System.out.println(ListenerController.getListenerController().getListener());
                    break;
                case "PremiumPacks":
                    System.out.println("ONEMONTH\nTWOMONTH\nSIXMONTH\n");
                    break;
                case "Help":
                    System.out.println("Commands:\nGetSuggestions,Artists,Artist,Follow,Search\nSort," +
                            "Filter,Add,ShowPlaylists,SelectPlaylist,Play\nLike,Lyric,NewPlaylist,Followings" +
                            "Report,IncreaseCredit,GetPremium,AccountInfo\nLogout,PremiumPacks\n");
                    break;
                default:
                    String ans = inputs[0];
                    String[] ansrs = ans.split("[+]");
                    if(ansrs[0].trim().equals("GetSuggestions")) {
                        if (ansrs.length == 2) {
                            System.out.println(ListenerController.getListenerController().seeRecommendedAudios(ansrs[1].trim()));
                        }else{
                            System.out.println(ListenerController.getListenerController().seeRecommendedAudios("10"));
                        }
                    }
                    else if (!(ansrs[0].trim().equals("Logout"))){
                        System.out.println("Incorrect command (Use <Help> for more Info!)");
                    }
            }

        } while (!(inputs[0].trim().equals("Logout")));
    }
    public void filterAudios_A_G(String filterKind,String name) {
        switch (filterKind) {
            case "A":
                System.out.println(ListenerController.getListenerController().findAudioByArtist(name));
                break;
            case "G":
                System.out.println(ListenerController.getListenerController().findAudioByGenre(name));
                break;
        }

    }
    public void filterAudios_D (String Year, String month, String day){
        System.out.println(ListenerController.getListenerController().findAudioByDate(Year, month, day));
    }

}
