package view.test;

import java.text.ParseException;
import controller.ArtistController;

public class ArtistView {
    private static ArtistView artistView;

    private ArtistView() {

    }

    public static ArtistView getArtistView() {
        if (artistView == null) {
            artistView = new ArtistView();
        }
        return artistView;
    }

    public void ArtistView(String[] spltCmd) throws ParseException {
        String txt;
        switch (spltCmd[0]) {
            case "AccountInfo":
                txt = ArtistController.getArtistController().ShowAccountInfo();
                System.out.println(txt);

                break;
            case "Followers":
                txt = ArtistController.getArtistController().showFollowers();
                System.out.println(txt);
                break;
            case "ViewsStatistics":
                txt = ArtistController.getArtistController().showViewsStatics();
                System.out.println(txt);

                break;
            case "CalculateEarnings":
                ArtistController.getArtistController().calculateIncome();

                break;
            case "NewAlbum":
                txt = ArtistController.getArtistController().createNewAlbum(spltCmd[1]);
                System.out.println(txt);

                break;
            case "Publish":
                if (spltCmd[1].equals("P")) {
                    txt = ArtistController.getArtistController().publishAudio(spltCmd[1], spltCmd[2], spltCmd[3],
                            spltCmd[4], spltCmd[5], spltCmd[6], 0L);
                    System.out.println(txt);
                }
                if (spltCmd[1].equals("M")) {
                    txt = ArtistController.getArtistController().publishAudio(spltCmd[1], spltCmd[2], spltCmd[3],
                            spltCmd[4], spltCmd[5], spltCmd[6], Long.parseLong(spltCmd[7]));
                    System.out.println(txt);
                }

                break;
            case "Logout":
                MainView.getMainView().mainView();

                break;

            default:
                System.out.println("not a valid command or you dont have access to this command");
                break;
        }
    }
}