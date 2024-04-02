package Controller;
import Model.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistController {
    private ArtistModel model;

    public ArtistController() {
    }

    public String signUp(String artKind, String username, String password, String fullName, String email, String phoneNumber, Date birthDate, String bio){
        for (User user : Database.getDatabase().getUsers())
            if(user.getUsername().equals(username))
                return "This Username already used!";

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

        if(artKind.equals("S"))
            model = new SingerModel(username,password,fullName,email,phoneNumber,birthDate,bio);
        else
            model = new PodcasterModel(username,password,fullName,email,phoneNumber,birthDate,bio);

        return "Done!";
    }

    public String logIn(String username, String password){
        for (User user : Database.getDatabase().getUsers()) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                model =(ArtistModel) user;
                return "Done!";
            }
        }
        return "Username or password is wrong!";
    }

    public String showFollowers(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getFollowers().size(); i++)
            show.append("Follower "+(i+1)+": "+ model.getFollowers().get(i).getFullName()+"\n");
        return show.toString();
    }
    public String showPlayedCount(){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getArtistName().equals(model.getFullName()))
                show.append("Audio name: "+audio.getAudioName()+"\tPlay count: "+audio.getPlayedCount()+"\n");

        return show.toString();
    }
    public void CalculateEarnings(){
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getArtistName().equals(model.getFullName())){
                model.setPlayCount(model.getPlayCount()+audio.getPlayedCount());
                audio.setPlayedCount(0);
            }
        for (User user : Database.getDatabase().getUsers())
            if(user.getFullName().equals(model.getFullName())){
                if(user instanceof PodcasterModel)
                    model.setSalary(model.getPlayCount()*0.5);
                else
                    model.setSalary(model.getPlayCount()*0.4);

                break;
            }
    }
    public String showUserInfo(){
        CalculateEarnings();
        return "Name: "+model.getFullName() + "\tSalary: "+model.getSalary()+"\tUsername: " + model.getUsername() + "\tPhone number: " + model.getPhoneNumber() + "\nEmail: "+ model.getEmail() + "\tBorn: " + model.getBirthDate() ;
    }
    public void newAlbum(String albumName){
       ((SingerModel)model).getAlbums().add(new Album(albumName, model.getFullName()));
    }
    public void publishMusic(String audioName, Genre genre,String lyric,String link,String cover,int albumID){
        for (Album album : ((SingerModel)model).getAlbums())
            if(album.getIDCount()==albumID){
                Music music = new Music(audioName, model.getFullName(),genre,link,cover,lyric);
                album.getMusics().add(music);
                break;
            }
    }
    public void publishPodcast(String audioName, Genre genre, String caption, String link, String cover){
        Podcast podcast = new Podcast(audioName,model.getFullName(),genre,link,cover,caption);
        ((PodcasterModel)model).getPodcasts().add(podcast);
    }
}
