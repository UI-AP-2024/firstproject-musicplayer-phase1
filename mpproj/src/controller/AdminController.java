package controller;

import model.Audio.AudioModel;
import model.DataBase.DataBaseModel;
import model.ReportModel;
import model.UserAccount.AdminModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.UserAccountModel;

import java.util.ArrayList;
import java.util.Objects;

public class AdminController {
    private AdminModel admin;
    public AdminModel getAdmin() {
        return admin;
    }
    public void setAdmin(AdminModel admin) {
        this.admin = admin;
    }

    private static AdminController adminController;

    public static AdminController getAdminController() {
        if (adminController == null)
            adminController = new AdminController();
        return adminController;
    }
    public StringBuilder showArtists() {
        StringBuilder str = new StringBuilder();
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (userAccount instanceof ArtistModel)
                str.append("\nName : ").append(userAccount.getName())
                        .append(", username : ").append(userAccount.getUsername());
        return str;
    }
    public String showArtistInfo(String username) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (userAccount instanceof ArtistModel)
                if (Objects.equals(userAccount.getUsername(), username))
                    return userAccount.toString();
        return "Username not found";
    }
    public StringBuilder showAudios() {
        StringBuilder str = new StringBuilder();
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            str.append("Name : ").append(audio.getAudioTitle())
                    .append(", id : ").append(audio.getId());
        return str;
    }
    public String showAudioInfo(int id) {
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            if (audio.getId() == id)
                return audio.toString();
        return "ID not found";
    }
    public StringBuilder showReports() {
        StringBuilder str = new StringBuilder();
        for (ReportModel report : DataBaseModel.getDataBase().getReports())
            str.append(report.toString());
        return str;
    }
    public StringBuilder showPopularAudios() {
        StringBuilder str = new StringBuilder();
        ArrayList<AudioModel> audios = DataBaseModel.getDataBase().getAudios();
        for (int i = 0; i < audios.size(); i++) {
            for (int j = i + 1; j < audios.size(); j++) {
                if (audios.get(i).getLikesCount() > audios.get(j).getLikesCount()) {
                    AudioModel tmp = audios.get(i);
                    audios.set(i, audios.get(j));
                    audios.set(j, tmp);
                }
            }
        }
        for (AudioModel audio : audios)
            str.append(audio.toString());
        return str;
    }
    public String showAccountInfo() {
        return admin.toString();
    }
}
