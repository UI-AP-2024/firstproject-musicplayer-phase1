package controller;

import model.DataBase.DataBaseModel;
import model.ReportModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;


public class ListenerController {
    private ListenerModel listener;
    public ListenerModel getListener() {
        return listener;
    }
    public void setListener(ListenerModel listener) {
        this.listener = listener;
    }

    private static ListenerController listenerController;

    public static ListenerController getListenerController() {
        if (listenerController == null)
                listenerController = new ListenerController();
        return listenerController;
    }
    public String sinUp(String accountType,String username, String password, String name, String email, String phoneNumber, String birthDate) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), username)) return "This username is already in use. Try another one!";
        return "";
    }
    public String report(String artistUsername, String description) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), artistUsername)) {
                ReportModel report = new ReportModel(listener,(ArtistModel)userAccount, description);
                DataBaseModel.getDataBase().getReports().add(report);
                return "The report was successfully registered";
            }
        return "Artist not found";
    }

}
