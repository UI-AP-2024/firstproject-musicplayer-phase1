package controller.Admin;

import model.Audio.Audio;
import model.Database.Database;
import model.Report.Report;
import model.UserAccounts.Admin.Admin;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.Listener.Free;
import model.UserAccounts.Listener.Premium;
import model.UserAccounts.userAccount;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class adminController {
    private static adminController adminC;
    private Admin adminM;

    private adminController() {
    }

    public static adminController getAdminC() {
        if (adminC == null) {
            adminC = new adminController();
        }
        return adminC;
    }

    public Admin getAdminM() {
        return adminM;
    }

    public void setAdminM(Admin adminM) {
        this.adminM = adminM;
    }


    /// Login admin
    public void loginAdmin(userAccount userAccount) {

        adminM = (Admin) userAccount;
    }

    //// favorites Statistics
    public String Statistics() {
        StringBuilder context = new StringBuilder();

        List<Audio> sortedList = Database.getDatabase().getAllAudiosList().stream().sorted(Comparator.comparingInt(Audio::getNumberOfLikes).reversed()).collect(Collectors.toList());
        ;
        for (Audio audio : sortedList) {
            context.append(audio.toString());
        }
        return context.toString();
    }

    /// Show audios
    public String showAudios() {
        StringBuilder context = new StringBuilder();
        for (Audio audio : Database.getDatabase().getAllAudiosList()) {
            context.append(audio.toString());
            context.append("\n");
        }
        return context.toString();
    }

    // Show one select audio
    public String showSelectAudio(int audioId) {
        StringBuilder context = new StringBuilder();
        for (Audio audio : Database.getDatabase().getAllAudiosList()) {
            if (audio.getAudioId() == audioId) {
                context.append(audio.toString());
            }
        }
        return "audio not found!";
    }

    /// Show Artists
    public String showArtists() {
        StringBuilder context = new StringBuilder();
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user instanceof Artist) {
                context.append(((Artist) user).toString());
                context.append("\n");
            }
        }
        return context.toString();
    }

    /// Show one select Artist
    public String showSelectArtist(String userId) {
        StringBuilder context = new StringBuilder();
        for (userAccount user : Database.getDatabase().getAllUsersList()) {
            if (user instanceof Artist) {
                if (user.getUserId().equals(userId)) {
                    context.append(((Artist) user).toString());
                    return context.toString();
                }
            }
        }
        return "artist not found!";
    }

    //Show Reports
    public String showReports() {
        StringBuilder context = new StringBuilder();
        for (Report report : Database.getDatabase().getReportsList()) {
            context.append(report.toString());
            context.append("\n");
        }
        return context.toString();
    }

    ///AccountInfo
    public String AccountInfo() {
        return adminM.toString();
    }
}
