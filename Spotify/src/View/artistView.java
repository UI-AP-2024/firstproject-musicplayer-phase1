package View;

import controller.Artist.artistController;
import model.Genre.Genre;

import java.util.Scanner;

public class artistView {
    private static artistView artistVi;
    private artistController artistCo = artistController.getArtistC();
    private artistView(){
    }

    public static artistView getArtistVi() {
        if (artistVi==null){
            artistVi = new artistView();
        }
        return artistVi;
    }

    public void artistCommands(){
        Scanner sc = new Scanner(System.in);
        String[] commands = sc.nextLine().split(" -");

        /// Logout command
        if (commands[0].equals("Logout")){
            System.out.println("you loged out now!");
            firstPage.getFirst().firstView();
        }
        ///AccountInfo command
        if (commands[0].equals("AccountInfo")){
            System.out.println(artistCo.AccountInfo());
            artistCommands();
        }

        /// Followers command
        if (commands[0].equals("Followers")){
            System.out.println(artistCo.showFollowers());
            artistCommands();
        }

        /// ViewsStatistics command
        if (commands[0].equals("ViewsStatistics")){
            System.out.println(artistCo.ViewsStatistics());
            artistCommands();
        }

        /// NewAlbum -[name]
        if (commands[0].equals("NewAlbum")){
            System.out.println(artistCo.createAlbum(commands[1]));
            artistCommands();
        }

        /// Publish -M|P -[title] -[genre] -[lyric|caption] -[link] -[cover] -[album ID ] command
        if (commands[0].equals("Publish")){
            if (commands[1].equals("M")){
                artistCo.shareMusic(commands[2], Genre.valueOf(commands[3]),commands[4],commands[5],commands[6],
                        Integer.parseInt(commands[7]));
            }
            if (commands[1].equals("P")){
                artistCo.sharePodcast(commands[2], Genre.valueOf(commands[3]),commands[4],commands[5],commands[6]);
            }
            System.out.println("Your audio file published;");
            artistCommands();
        }
    }
}
