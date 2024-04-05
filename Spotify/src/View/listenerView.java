package View;
import controller.Listener.listenerController;
import model.Genre.Genre;
import model.PremiumPackages.PremiumSubscriptionPackages;

import java.util.Date;
import java.util.Scanner;
import java.util.logging.Filter;

import static sun.misc.Version.print;

public class listenerView {
    private static listenerView listenerV;
    private listenerView(){
    }
    public static listenerView getListenerV() {
        if (listenerV==null){
            listenerV = new listenerView();
        }
        return listenerV;
    }
    private listenerController listenerCo = listenerController.getListenerC();

    public listenerController getListenerCo() {
        return listenerCo;
    }

    public void setListenerCo(listenerController listenerCo) {
        this.listenerCo = listenerCo;
    }

    public void showGenres(){
        StringBuilder context = new StringBuilder(" you can chosse maximum 4 geners of these geners ");
        for (Genre genre:Genre.values()){
            context.append(genre);
            context.append(",");
        }
        System.out.println(context);
        listenerCommands();
    }
    public void listenerCommands(){
        Scanner sc = new Scanner(System.in);
        String[] commands = sc.nextLine().split(" -");

        /// FavouriteGenres -[favourite genres separated with comma(,)]
        if (commands[0].equals("FavouriteGenres")){
            String[] genres =commands[1].split(",");
            listenerCo.chooseFourGenres(Genre.valueOf(genres[0]),
                    Genre.valueOf(genres[1]),Genre.valueOf(genres[2]),
                    Genre.valueOf(genres[3]));
            listenerCommands();
        }
        /// Logout command
        if (commands[0].equals("Logout")){
            System.out.println("you loged out now!");
            firstPage.getFirst().firstView();
        }

        /// AccountInfo
        if (commands[0].equals("AccountInfo")){
            System.out.println(listenerCo.AccountInfo());
            listenerCommands();
        }

        /// Artists command
        if (commands[0].equals("Artists")){
            System.out.println(listenerCo.artistsList());;
            listenerCommands();
        }

        /// Artist -[username] command
        if (commands[0].equals("Artist")){
            String res = listenerCo.artist(commands[1]);
            System.out.println(res);
            listenerCommands();
        }

        // Follow -[username] command
        if (commands[0].equals("Follow")){
            System.out.println(listenerCo.followArtist(commands[1]));
            listenerCommands();
        }

        //  Search -[artist name OR audio’s title] command
        if (commands[0].equals("Search")){
            System.out.println(listenerCo.Search(commands[1]));
            listenerCommands();
        }

        // Sort -L|P command
        if (commands[0].equals("Sort")){
            System.out.println(listenerCo.Sort(commands[1]));
        }

        // Filter -A|G|D -[filter by] command
        if (commands[0].equals("Filter")){
            if (commands[1].equals("A")){
                System.out.println(listenerCo.filter((String) commands[2],null));
            }
            if (commands[1].equals("G")){
                System.out.println(listenerCo.filter(Genre.valueOf(commands[2]),null));
            }
            if (commands[1].equals("D")){
                Date date1 = new Date(Integer.parseInt(commands[2].split("/")[0]),
                        Integer.parseInt(commands[2].split("/")[1]),
                        Integer.parseInt(commands[2].split("/")[2]));
                Date date2 = new Date(Integer.parseInt(commands[3].split("/")[0]),
                        Integer.parseInt(commands[3].split("/")[1]),
                        Integer.parseInt(commands[3].split("/")[2]));
                System.out.println(listenerCo.filter(date1,date2));
            }
            listenerCommands();
        }


        /// Add -[playlist’s name] -[audio’s ID] commad
        if (commands[0].equals("Add")){
            System.out.println(listenerCo.addAudioToPlaylist(commands[1],Integer.parseInt(commands[2])));
            listenerCommands();
        }


        /// . ShowPlaylists command
        if (commands[0].equals("ShowPlaylists")){
            System.out.println(listenerCo.showPlaylists());
            listenerCommands();
        }

        ///  SelectPlaylist -[playlist’s name]
        if (commands[0].equals("SelectPlaylist")){
            System.out.println(listenerCo.selectPlaylist(commands[1]));
            listenerCommands();
        }

        /// Play -[audio’s ID] command
        if (commands[0].equals("Play")){
            System.out.println(listenerCo.playAudio(Integer.parseInt(commands[1])));
            listenerCommands();
        }

        // Like -[audio’s ID] command
        if (commands[0].equals("Like")){
            System.out.println(listenerCo.likeAudio(Integer.parseInt(commands[1])));
            listenerCommands();
        }

        /// Lyric -[audio’s ID] command
        if (commands[0].equals("Lyric")){
            System.out.println(listenerCo.lyricAudio(Integer.parseInt(commands[1])));
            listenerCommands();
        }

        ///  NewPlaylist -[playlist’s name] command
        if (commands[0].equals("NewPlaylist")){
            System.out.println(listenerCo.createPlaylist(commands[1]));
            listenerCommands();
        }

        /// Followings command
        if (commands[0].equals("Followings")){
            System.out.println(listenerCo.showFollowings());
            listenerCommands();
        }

        /// Report -[artist’s username] -[explanation] command
        if (commands[0].equals("Report")){
            System.out.println(listenerCo.reportArtist(commands[1],commands[2]));
            listenerCommands();
        }

        /// IncreaseCredit -[value] command
        if (commands[0].equals("IncreaseCredit")){
            System.out.println(listenerCo.increaseCredit(Double.parseDouble(commands[1])));
            listenerCommands();
        }

        /// GetPremium -[package]
        if (commands[0].equals("GetPremium")){
            System.out.println(listenerCo.getPremium(PremiumSubscriptionPackages.valueOf(commands[1])));
            listenerCommands();
        }

    }
}
