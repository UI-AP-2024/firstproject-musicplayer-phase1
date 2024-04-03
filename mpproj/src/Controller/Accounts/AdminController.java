package Controller.Accounts;

import Model.Accounts.AccountsModel;
import Model.Accounts.AdminModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Audios.AudioModel;
import Model.Database.Database;
import Model.Reports.ReportModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AdminController {

    private AdminModel admin;

    private static AdminController adminController;

    private AdminController(){}

    public static AdminController getAdminController(){
        if(adminController == null){
            adminController = new AdminController();
        }
        return adminController;
    }


    public void loginAdmin(AccountsModel admin){
        this.admin = (AdminModel) admin;
    }


    public StringBuilder showMostPopularAudiosInfo(){
        StringBuilder message = new StringBuilder();
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();
        List<AudioModel> sortedAudio = audios.stream().sorted(Comparator.comparing(AudioModel::getLikeCount).reversed()).toList();

        message.append("\nMost popular Audios :\n");
        for (int i = 0 ; i < sortedAudio.size() ; i++){
            message.append(i);
            message.append(")\n");
            message.append(sortedAudio.get(i).toString());
            message.append("\n\n");
        }

        return message;
    }


    public StringBuilder showAudiosInfo(){
        StringBuilder message = new StringBuilder();
        ArrayList<AudioModel> audios = Database.getDatabase().getAudios();

        message.append("\nAudios Information :\n");
        for (AudioModel audio : audios){
            message.append(audio.toString());
            message.append("\n\n");
        }

        return message;
    }

    public String showAudioDetail(int audioID){
        AudioModel audio = findAudioByID(audioID);
        if(audio == null){
            return "\nCould not found audio";
        }
        return "\n"+ audio.toString() +
                "\nGenre : "+ audio.getGenre() +
                "\nCover : "+ audio.getCover() +
                "\nRelease Time : "+ audio.getReleaseTime().toString();
    }

    private AudioModel findAudioByID(int audioID){
        Database database = Database.getDatabase();
        ArrayList<AudioModel> audios = database.getAudios();

        int audioIndex = -1;
        for (AudioModel audio : audios){
            if(audio.getID() == audioID){
                audioIndex = audios.indexOf(audio);
                break;
            }
        }
        if(audioIndex == -1){
            return null;
        }

        return audios.get(audioIndex);
    }

    public StringBuilder showArtists(){
        StringBuilder message = new StringBuilder();
        ArrayList<ArtistModel> artists = findArtists();

        message.append("\nArtists Information :\n");
        for (ArtistModel artist : artists){
            message.append(artist.toString());
            message.append("\n\n");
        }
        return message;
    }

    private ArrayList<ArtistModel> findArtists(){
        ArrayList<ArtistModel> artists = new ArrayList<ArtistModel>();
        for (AccountsModel account : Database.getDatabase().getAccounts()){
            if (account instanceof ArtistModel artist){
                artists.add(artist);
            }
        }
        return artists;
    }
    public StringBuilder showArtistDetail(String artistUsername){
        StringBuilder message = new StringBuilder();
        ArtistModel artist = (ArtistModel) findArtistByUsername(artistUsername);

        if (artist == null){
            return message.append("\nCould not find artist");
        }
        message.append("\nArtist Information :\n");
        message.append(artist.toString()).append("\n");
        message.append("Email : ").append(artist.getEmail()).append("\n");
        message.append("Phone Number : ").append(artist.getPhoneNumber()).append("\n");
        message.append("Income : ").append(ArtistsController.getArtistsController().calculateIncome()).append("\n");
        message.append("Bio : ").append(artist.getBio()).append("\n");
        message.append("Birthday : ").append(artist.getBirthday()).append("\n");
        message.append("Follower count : ").append(artist.getFollowersCount()).append("\n");
        message.append("\n\n");

        return message;
    }

    private AccountsModel findArtistByUsername(String username){
        for (AccountsModel artist : findArtists()){
            if (artist.getUsername().equals(username)){
                return artist;
            }
        }
        return null;
    }

    public StringBuilder showReports(){
        StringBuilder message = new StringBuilder();
        ArrayList<ReportModel> reports = Database.getDatabase().getReports();

        message.append("\nReports : ");
        for (ReportModel report : reports){
            message.append(report.toString());
            message.append("\n\n");
        }

        return message;
    }

    public String showReportDetail(String senderName){
        ReportModel report = findReportBySenderName(senderName);
        if(report == null){
            return "\nCould not find Report";
        }

        return "\n"+ report.toString() + "Description : "+ report.getDescription();
    }

    private ReportModel findReportBySenderName(String senderName){
        ArrayList<ReportModel> reports = Database.getDatabase().getReports();

        for (ReportModel report : reports){
            if(report.getSender().getUsername().equals(senderName)){
                return report;
            }
        }
        return null;
    }

    public String showAccountInfo(){
        return "\n"+ this.admin.toString() +
                "\nEmail : "+ this.admin.getEmail() +
                "\nPhone Number : "+ this.admin.getPhoneNumber() +
                "\nBirthday : "+ this.admin.getBirthday();
    }
}
