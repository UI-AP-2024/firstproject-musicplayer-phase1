package controller;

import model.Audio.Audio;
import model.Database.Database;
import model.Report;
import model.UserAccount.*;

import java.util.ArrayList;
import java.util.Date;

public class AdminControler {//todo admin controler
    Admin admin;
    private static AdminControler adminControler;

    public AdminControler() {
    }

    public static AdminControler getAdminControler() {
        if (adminControler == null)
            adminControler=new AdminControler();
        return adminControler;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public String signUpArtist(String username, String pasword, String name, String email, String phoneNum, Date birthDate, String bio, String type){
        ArrayList<User> users= Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username)){
                return "error : this user name already exist .";
            }
        }
        admin = Admin.getAdmin(username, pasword, name, email, phoneNum, birthDate);
        setAdmin(admin);
        Database.getDatabase().getUsers().add((Admin)admin);
        //todo:add to database
        admin.setIsLogin(true);
        return  "artist accaount successfully created .";
    }
    public String login(String username,String password){
        ArrayList<User>users=Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(admin.getIsLogin()==true)
                    return "you are in already in your profile .";
                setAdmin((Admin) user);
                admin.setIsLogin(true);
                return "login successful .";
            }
        }
        return "error : user name or password is wrong";
    }
    public String logout(){
        ArrayList<User>users=Database.getDatabase().getUsers();
        admin.setIsLogin(false);
        return "logout successfull";
    }
    public String lookReport(){
        StringBuilder result=new StringBuilder("reports :\n");
        ArrayList<Report> reports= Database.getDatabase().getReports();
        for(Report report:reports) {
            result.append(" artist name : ").append(report.getReportedArtist()).append(" reporter name : ").append(report.getReportingUser().getUsername()).append(" exeplenation: ").append(report.getDescription()).append("\n");
        }
        return String.valueOf(result);
    }
    public String lookArtists(){
        StringBuilder result=new StringBuilder("artists :\n");
        for(User user:Database.getDatabase().getUsers()){
            if(user instanceof Artist){
                result.append(user.getUsername()).append(", ");
            }
        }
        return String.valueOf(result);
    }
    public String lookAudios(){
        StringBuilder result=new StringBuilder("audios :\n");
        for(Audio audio:Database.getDatabase().getAudios()) {
            result.append(audio.getTitle()).append(", ");
        }
        return String.valueOf(result);
    }
}
