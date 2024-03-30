package Controller;
import Model.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistController extends User{
    private ArtistModel model;
    public ArtistController(String username, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(username, password, fullName, email, phoneNumber, birthDate);
    }

    public String signUp(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(username)){
                return "This Username already used!";
            }
        }
        Pattern emailPattern = Pattern.compile(".*@.*(\\.com|\\.ir|\\.org)$");
        Matcher matcherEmail = emailPattern.matcher(email);
        Pattern phonepattern = Pattern.compile("^\\d{8,11}$");
        Matcher matcherPhone = phonepattern.matcher(phoneNumber);
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}$");
        Matcher matcherPassword = passwordPattern.matcher(password);
        if(!matcherEmail.matches())
            return "The email is not valid!";
        else if(!matcherPhone.matches())
            return "The phone number is not valid!";
        else if(!matcherPassword.matches())
            return "Weak password! Pleas use better password.";

        return "Done!";
    }

    public String logIn(String username, String password){
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(username) && Database.getDatabase().getUsers().get(i).getPassword().equals(password)){
                return "Done!";
            }
        }
        return "Username or password is wrong!";
    }

    public String showFollowers(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getFollowers().size(); i++)
            show.append("Follower "+i+": "+ model.getFollowers().get(i)+"\n");
        return show.toString();
    }
    public String showPlayedCount(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getArtistName().equals(model.getFullName())){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio name: "+audio.getAudioName()+" Play count: "+audio.getPlayedCount()+"\n");
            }
        }
        return show.toString();
    }
    public void CalculateEarnings(){
        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getArtistName().equals(model.getFullName())){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                model.setPlayCount(model.getPlayCount()+audio.getPlayedCount());
            }
        }
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getFullName().equals(model.getFullName())){
                User user = Database.getDatabase().getUsers().get(i);
                if(user.getClass().equals(PodcasterModel.class))
                    model.setSalary(model.getPlayCount()*0.5);
                else
                    model.setSalary(model.getPlayCount()*0.4);

                break;
            }
        }
    }
    
}
