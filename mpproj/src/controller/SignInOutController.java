package controller;

import model.DataBase.DataBaseModel;
import model.UserAccount.AdminModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Artist.PodcasterModel;
import model.UserAccount.Artist.SingerModel;
import model.UserAccount.Listener.FreeListenerModel;
import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Pattern;

public class SignInOutController {
    private UserAccountModel user;
    public UserAccountModel getUserAccount() {
        return user;
    }
    public void setUserAccount(UserAccountModel userAccount) {
        this.user = userAccount;
    }

    private static SignInOutController userAccountController;

    public static SignInOutController getUserAccountController() {
        if (userAccountController == null)
            userAccountController = new SignInOutController();
        return userAccountController;
    }
    public String sinUpListener(String username, String password, String name, String email, String phoneNumber, String birthDate) {
        FreeListenerModel newListener;
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), username) || Objects.equals(userAccount.getEmail(), email))
                return "This username or email is already in use. Try again!";
        Pattern phoneNumberRegex = Pattern.compile("^09[01239][0-9]{8}$");
        if (!phoneNumberRegex.matcher(phoneNumber).matches())
            return "Phone number format is false";
        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9+_.\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
        if (!emailRegex.matcher(email).matches())
            return "Email format is false";
        LocalDate b = LocalDate.parse(birthDate);
        newListener = new FreeListenerModel(username, password, name, email, phoneNumber, b);
        DataBaseModel.getDataBase().getUsers().add(newListener);
        ListenerController.getListenerController().setListener(newListener);
        user = newListener;

        return "Now you can choose 4 favorite genres that you like!";
    }
    public String sinUpPodcaster(String username, String password, String name, String email, String phoneNumber, String birthDate, String bio) {
        PodcasterModel newPodcaster;
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), username) || Objects.equals(userAccount.getEmail(), email))
                return "This username or email is already in use. Try again!";
        Pattern phoneNumberRegex = Pattern.compile("^09[01239][0-9]{8}$");
        if (!phoneNumberRegex.matcher(phoneNumber).matches())
            return "Phone number format is false";
        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9+_.\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
        if (!emailRegex.matcher(email).matches())
            return "Email format is false";
        LocalDate b = LocalDate.parse(birthDate);
        newPodcaster = new PodcasterModel(username, password, name, email, phoneNumber, b, bio);
        DataBaseModel.getDataBase().getUsers().add(newPodcaster);
        return "Now you can login!";
    }
    public String sinUpSinger(String username, String password, String name, String email, String phoneNumber, String birthDate, String bio) {
        SingerModel newSinger;
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), username) || Objects.equals(userAccount.getEmail(), email))
                return "This username or email is already in use. Try again!";
        Pattern phoneNumberRegex = Pattern.compile("^09[01239][0-9]{8}$");
        if (!phoneNumberRegex.matcher(phoneNumber).matches())
            return "Phone number format is false";
        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9+_.\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
        if (!emailRegex.matcher(email).matches())
            return "Email format is false";
        LocalDate b = LocalDate.parse(birthDate);
        newSinger = new SingerModel(username, password, name, email, phoneNumber, b, bio);
        DataBaseModel.getDataBase().getUsers().add(newSinger);
        return "Now you can login!";
    }
    public String login(String username, String password) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), username)) {
                if (Objects.equals(userAccount.getPassword(), password)){
                    if (userAccount instanceof ArtistModel){
                        ArtistController.getArtistController().setArtist((ArtistModel) userAccount);
                        user = (ArtistModel)userAccount;
                    }
                    if (userAccount instanceof ListenerModel) {
                        ListenerController.getListenerController().setListener((ListenerModel) userAccount);
                        user = (ListenerModel)userAccount;
                    }
                    if (userAccount instanceof AdminModel){
                        AdminController.getAdminController().setAdmin((AdminModel) userAccount);
                        user = (AdminModel)userAccount;
                    }
                    return "You are logged in now";
                }
                return "Password is incorrect";
            }
        return "username not found";
    }
    public void logout() {
        user = null;
    }
}
