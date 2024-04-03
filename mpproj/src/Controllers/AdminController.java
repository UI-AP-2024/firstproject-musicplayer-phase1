package Controller;

import Model.*;

public class AdminController {
    private static AdminController adminController;

    private AdminController() {
    }

    public static AdminController getAdminController() {
        if (adminController == null)
            adminController = new AdminController();
        return adminController;
    }

    public boolean checkAdmin(String username, String password) {
        if (Admin.getAdmin().getUserName().equals(username) &&
                Admin.getAdmin().getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public String showStatistics() {
        StringBuilder audios = new StringBuilder();
        int i = 1;
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getLikeConter() > 0) {
                audios.append("\nAudio ").append(i).append("=>\nName: ").append(audio.getAudioName()).append("\nArtist: ")
                        .append(audio.getArtistName()).append("\nID: ").append(audio.getId()).append("\nLikes: ")
                        .append(audio.getLikeConter()).append("\nPlays :").append(audio.getPlaycounter()).append("\n");
                i++;
            }
        }
        return String.valueOf(audios);
    }

    public String showAudio(String audioID) {
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getId().equals(audioID)) {
                return audio.toString();
            }
        }
        return audioID+" Not Found!";
    }
    public String showAudiosList() {
        StringBuilder audios = new StringBuilder();
        int i = 1;
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
                audios.append("\nAudio ").append(i).append("=>\nName: ").append(audio.getAudioName()).append("\nArtist: ")
                        .append(audio.getArtistName()).append("\nID: ").append(audio.getId()).append("\nLikes: ")
                        .append(audio.getLikeConter()).append("\nPlays :").append(audio.getPlaycounter()).append("\n");
                i++;
            }
        return String.valueOf(audios);
    }

    public StringBuilder showReports(){
        StringBuilder reports = new StringBuilder();
        int i=1;
        for (Report report: DataBase.getDataBase().getReportsList()) {
            reports.append("Report ").append(i).append(report);
            i++;
        }
        return reports;
    }
}
