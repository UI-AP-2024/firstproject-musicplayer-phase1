package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import model.audio.Audio;
import model.database.Database;
import model.user.Admin;
import model.user.PremiumListener;
import model.user.Report;

public class AdminController {
    private static AdminController adminController;
    private AdminController(){

    }
    public static AdminController getAdminController(){
        if(adminController==null){
            adminController = new AdminController();
        }
        return adminController;
    }
    public String ShowAccountInfo(){
        String txt="Account info:"+
        "\nuser name : "+Admin.getAdmin().getUsername()+
        "\nFirst name : "+Admin.getAdmin().getName()+
        "\nemail address: "+Admin.getAdmin().getEmailAddress()+
        "\npassword : "+Admin.getAdmin().getPassword()+
        "\nbirth date : "+String.valueOf(Admin.getAdmin().getBirthDate());
        return txt;
    }

    public String popularAudios(){
        String txt = "Popular Audios\n";
        ArrayList <Audio> sorted = Database.getDatabase().getAllAudio()
                .stream()
                .sorted(Comparator.comparing(Audio -> Audio.getNumberOfLikes()))
                .collect(Collectors.toCollection(ArrayList::new));
            for(Audio audio : sorted){
                txt+="-"+audio.getAudioName()+"("+audio.getArtistName()+")\n"
                +"Likes : "+String.valueOf(audio.getNumberOfLikes())+"\n"
                +"Plays : "+String.valueOf(audio.getNumberOfPlays())+"\n\n";
            }
            return txt;
    }
    ///artists and audios 

    public String reports(){
        String txt = "All reports\n";
        if(Database.getDatabase().getAllReports().size()==0){
            return "no report has been recieved";
        }
        for(Report report : Database.getDatabase().getAllReports()){
            txt+="reported Artist : "+report.getReportedArtist()+"\n"+
            "reporting user : " + report.getReportingUser()+"\n"+
            "Description : "+ report.getDescription()+"\n";
        }
        return txt;
    }
    
}
