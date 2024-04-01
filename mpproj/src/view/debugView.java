package view;

import java.text.ParseException;
import java.util.ArrayList;

import controller.ArtistController;
import controller.ListenerController;
import controller.PodcasterController;
import controller.SingerController;
import controller.UserController;
import model.audio.Genre;
import model.user.Artist;
import model.user.Listener;
import model.user.Podcaster;
import model.user.PremiumListener;
import model.user.PremuimAcc;
import model.user.Singer;
import model.user.User;

public class DebugView {

    private static DebugView debugView;
    private DebugView(){

    }
    public static DebugView getDebugView(){
        if(debugView==null){
            debugView = new DebugView();
        }
        return debugView;
    }

    public void firstMenu() throws ParseException{
        // System.out.println("1)sign up\n2)log in");
        // System.out.println("enter :\n'L' if you are listener\n'S' if you are Singer\n'P' if you are poscaster");
        // System.out.println("enter your [username] -[password] -[First name] -[Last name]-[email] -[phone number]\r\n" + //
        //                 "-[birthdate in year month day format] with a space in between");
        // String txt = ListenerController.getListenerController().signupNewListener("Maryam","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        // System.out.println(txt);
        // txt = ListenerController.getListenerController().signupNewListener("Maryam","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        // System.out.println(txt);
        // txt = ListenerController.getListenerController().signupNewListener("nazanin","Maryamdar840", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        // System.out.println(txt);
        // txt = ListenerController.getListenerController().signupNewListener("nazanin","Maryamdar84!", "Maryam", "Dar", "maryamdar13849gmail.com","09390555104",2005,5,10 );
        // System.out.println(txt);
        // txt = ListenerController.getListenerController().signupNewListener("nazanin","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 );
        // System.out.println(txt);
        // txt=Genre.allGenres();
        // System.out.println(txt+"\nenter your favourite genres (at most four), each one in one line");//has next line sc.nextline;
        // ArrayList<String> genres = new ArrayList<>();
        // genres.add("POP");
        // genres.add("HIPHOP");
        // genres.add("SOCIETY");
        // ListenerController.getListenerController().getFavoriteGenres(genres);
        // String a =String.valueOf(ListenerController.getListenerController().getListener().getFavoriteGenres().get(0));
        // System.out.println(a);
        // User tmp = UserController.getUserController().findUser("Maryap", "Maryamdar84!");
        // if(tmp == null){
        //     System.out.println("not a valid username or password try again");
        // }
        // if(tmp instanceof Listener){
        //     System.out.println("Is listener");
        // }
        // tmp = UserController.getUserController().findUser("Maryam", "Maryamdar84!");
        // if(tmp == null){
        //     System.out.println("not a valid username or password try again");
        // }
        // if(tmp instanceof Listener){
        //     System.out.println("Is listener");
        //     ListenerController.getListenerController().loginListener((Listener)tmp);
        //     txt = ListenerController.getListenerController().ShowAccountInfo();
        //     System.out.println(txt);
        //     System.out.println("get your account premium\na)30 days(5$)\nb)60 days(9$)\nc)180 days(14$)");
        //     ListenerController.getListenerController().getPremium(30,5);
        //     ListenerController.getListenerController().getPremium(30, 5);
        //     Listener x = ListenerController.getListenerController().getListener();
        //     System.out.println(x.getAccountCredit());
        //     System.out.println(x.getPremiumExpirationDate());
        //     PremiumListener px = (PremiumListener)x; 
        //     System.out.println(px.getRemainingDaysOfPremium());
            
        // }
        // System.out.println("1)sign up\n2)log in");
        // System.out.println("enter :\n'L' if you are listener\n'S' if you are Singer\n'P' if you are poscaster");
        // System.out.println("enter your [username] -[password] -[First name] -[Last name]-[email] -[phone number]\r\n" + //
        //                 "-[birthdate in year month day format] with a space in between and your biographi");
        // String bio = "this is me maryam dar please supportme thanks";
        // txt = SingerController.getSingerController().signupNewSinger("Maryao","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 ,bio);
        // System.out.println(txt);
        // txt = SingerController.getSingerController().signupNewSinger("Maryao","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 ,bio);
        // System.out.println(txt);
        // txt = SingerController.getSingerController().signupNewSinger("Maryaop","Maryamdar840", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 ,bio);
        // System.out.println(txt);
        // txt = SingerController.getSingerController().signupNewSinger("Maryaop","Maryamdar84#", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 ,bio);
        // System.out.println(txt);

        // tmp = UserController.getUserController().findUser("Maryao", "Maryamdar84!");
        // if(tmp == null){
        //     System.out.println("not a valid username or password try again");
        // }
        // if(tmp instanceof Singer){
        //     System.out.println("Is Singer");
        //     SingerController.getSingerController().loginSinger((Singer)tmp);
        //     txt = SingerController.getSingerController().ShowAccountInfo();
        //     System.out.println(txt);
        // }

        // System.out.println("1)sign up\n2)log in");
        // System.out.println("enter :\n'L' if you are listener\n'S' if you are Singer\n'P' if you are poscaster");
        // System.out.println("enter your [username] -[password] -[First name] -[Last name]-[email] -[phone number]\r\n" + //
        //                 "-[birthdate in year month day format] with a space in between and your biographi");
        // bio = "this is me maryam dar please supportme thanks";
        // txt = PodcasterController.getPodcasterController().signupNewPodcaster("Maryaoi","Maryamdar84!", "Maryam", "Dar", "maryamdar1384@gmail.com","09390555104",2005,5,10 ,bio);
        // System.out.println(txt);

        // // tmp = UserController.getUserController().findUser("Maryao", "Maryamdar84!");
        // // tmp = UserController.getUserController().findUser("Maryam", "Maryamdar84!");
        // tmp = UserController.getUserController().findUser("Maryaoi", "Maryamdar84!");
        // // tmp = UserController.getUserController().findUser("Mary", "Maryamdar84!");
        // if(tmp == null){
        //     System.out.println("not a valid username or password try again");
        // }
        // if(tmp instanceof Listener){
        //     System.out.println("Is listener");
        //     ListenerController.getListenerController().loginListener((Listener)tmp);

        // }
        // if(tmp instanceof Singer){
        //     System.out.println("Is Singer");
        //     SingerController.getSingerController().loginSinger((Singer)tmp);

        // }
        // if(tmp instanceof Podcaster){
        //     System.out.println("Is podcaster");
        //     PodcasterController.getPodcasterController().loginPodcaster((Podcaster)tmp);
        //     txt = PodcasterController.getPodcasterController().ShowAccountInfo();
        //     System.out.println(txt);

        // }

        System.out.println("1)sign up\n2)log in");
        System.out.println("enter :\n'L' if you are listener\n'S' if you are Singer\n'P' if you are poscaster");
        System.out.println("enter your [username] -[password] -[First name] -[Last name]-[email] -[phone number]\r\n" + //
                        "-[birthdate in year month day format] with a space in between and your biographi");
        String bio = "this is me maryam dar please supportme thanks";
        String bion = null;
        String txt = UserController.getUserController().signupNewUser("Maryaol","Maryamdar84#", "MaryamDar", "maryamdar1384@gmail.com","09390555104","10-5-2005",'S',bio);
        System.out.println(txt);
        txt = UserController.getUserController().signupNewUser("Maryaoh","Maryamdar84#", "MaryamDar", "maryamdar1384@gmail.com","09390555104","10-5-2005",'L',bion);
        System.out.println(txt);
        txt = UserController.getUserController().signupNewUser("Maryaom","Maryamdar84#", "MaryamDar", "maryamdar1384@gmail.com","09390555104","10-5-2005",'P',bion);
        System.out.println(txt);

        User tmp = UserController.getUserController().findUser("Maryaol", "Maryamdar84#");
        User tmpn = UserController.getUserController().findUser("Maryaoh", "Maryamdar84#");
        if(tmp instanceof Singer){
            System.out.println("Is Singer");
            SingerController.getSingerController().loginSinger((Singer)tmp);
            txt = SingerController.getSingerController().ShowAccountInfo();
            System.out.println(txt);
        }
        if(tmpn instanceof Listener){
            System.out.println("Is listener");
            ListenerController.getListenerController().loginListener((Listener)tmpn);
            txt = ListenerController.getListenerController().ShowAccountInfo();
            System.out.println(txt);
            System.out.println("get your account premium\na)30 days(5$)\nb)60 days(9$)\nc)180 days(14$)");
            ListenerController.getListenerController().getPremium(30,5);
            ListenerController.getListenerController().getPremium(30, 5);
            Listener x = ListenerController.getListenerController().getListener();
            System.out.println(x.getAccountCredit());
            System.out.println(x.getPremiumExpirationDate());
            PremiumListener px = (PremiumListener)x; 
            System.out.println(px.getRemainingDaysOfPremium());
            
        }
        txt=ListenerController.getListenerController().showAllArtists();
        System.out.println(txt);
        txt=ListenerController.getListenerController().showArtistInfo("Maryaol");
        System.out.println(txt);

        txt = UserController.getUserController().signupNewUser("Maryaof","Maryamdar84#", "MaryamDar", "maryamdar1384@gmail.com","09390555104","10-5-2005",'P',bio);
        System.out.println(txt);

        txt=ListenerController.getListenerController().showAllArtists();
        System.out.println(txt);
        txt=ListenerController.getListenerController().showArtistInfo("Maryaof");
        System.out.println(txt);

        // tmp = UserController.getUserController().findUser("Maryaof", "Maryamdar84#");
        txt = ListenerController.getListenerController().followArtist("Maryaol");
        System.out.println(txt);
        txt = ListenerController.getListenerController().increaseAccountCredit(67);
        System.out.println(txt);
        txt = ListenerController.getListenerController().ShowAccountInfo();
        System.out.println(txt);
        int packag=30;

        switch (packag) {
            case 30:
            ListenerController.getListenerController().getPremium(30,PremuimAcc.ONE_MONTH.getMoney());
                break;
        
            default:
                break;
        }

        txt = ListenerController.getListenerController().ShowAccountInfo();
        System.out.println(txt);
        txt = ListenerController.getListenerController().ShowFollowings();
        System.out.println(txt);
        txt = ListenerController.getListenerController().reportArtist("Maryaol", "bad song");
        System.out.println(txt);

        tmp = UserController.getUserController().findUser("Maryaol", "Maryamdar84#");
        // SingerController.getSingerController().loginSinger((Singer)tmp);
        // txt = SingerController.getSingerController().showFollowers();
        // System.out.println(txt);
        // txt = SingerController.getSingerController().showViewsStatics();
        // System.out.println(txt);
        ArtistController.getArtistController().loginArtist((Artist)tmp);
        txt = ArtistController.getArtistController().showFollowers();
        System.out.println(txt);
        txt = ArtistController.getArtistController().showViewsStatics();
        System.out.println(txt);

        txt = ArtistController.getArtistController().createNewAlbum("mine");
        System.out.println(txt);
        long id = SingerController.getSingerController().getSinger().getAlbumList().get(0).getId();
        System.out.println(id);
        txt = ArtistController.getArtistController().publishAudio("M", "the first","POP" , "this is lyrics","htpps://jdjfsnfjksk" , "kjhdkjhc.png", 1);
        System.out.println(txt);
        txt = ArtistController.getArtistController().showViewsStatics();
        System.out.println(txt);
        
        
        tmp = UserController.getUserController().findUser("Maryaof", "Maryamdar84#");
        ArtistController.getArtistController().loginArtist((Podcaster)tmp);
        txt = ArtistController.getArtistController().ShowAccountInfo();
        System.out.println(txt);
        txt = ArtistController.getArtistController().showFollowers();
        System.out.println(txt);
        txt = ArtistController.getArtistController().showViewsStatics();
        System.out.println(txt);
        txt = ArtistController.getArtistController().publishAudio("P", "the first podcast","POP" , "this is Caption","htpps://pdjfsnfjksk" , "kjhdojhc.png", 0);
        System.out.println(txt);
        txt = ArtistController.getArtistController().showViewsStatics();
        System.out.println(txt);

        tmp = UserController.getUserController().findUser("Maryaoh", "Maryamdar84#");
        ListenerController.getListenerController().loginListener((Listener)tmp);
        txt = ListenerController.getListenerController().showAllArtists();
        System.out.println(txt);
        txt = ListenerController.getListenerController().followArtist("Maryaof");
        System.out.println(txt);
        txt = ListenerController.getListenerController().ShowFollowings();
        System.out.println(txt);

        tmp = UserController.getUserController().findUser("Maryaof", "Maryamdar84#");
        ArtistController.getArtistController().loginArtist((Podcaster)tmp);
        txt = ArtistController.getArtistController().showFollowers();
        System.out.println(txt);

        


        



        

        //to loop singer 




        
        
    }
    
}

