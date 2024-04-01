package controller;

import model.Database;
import model.users.AccountUserModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;
import model.users.listeners.ListenerModel;

import java.util.regex.Pattern;

public class Controller
{
    private static Controller controller;
    private AccountUserModel accModel;
    private Controller(){}
    public static Controller getController()
    {
        if(controller==null)
            controller=new Controller();
        return controller;
    }
    public void setAccModel(AccountUserModel accModel)
    {
        this.accModel = accModel;
    }
    public AccountUserModel getAccModel()
    {
        return this.accModel;
    }
    public String makeNewAccount(String userName,String email, String phoneNumber, String birthDate)
    {
        for(AccountUserModel temp: Database.getDatabase().getAllUsers())
            if(temp!=null && userName.compareTo(temp.getUserName())==0)
                return "This username already exists";
        String numRegex="^09\\d{9}$";
        String emailRegex="^[^(\\.|\\W)](?=\\d*[a-zA-Z])([a-zA-Z0-9]\\.?){4,28}[^(\\.|\\W)]@gmail.com$";
        String dateRegex="^\\d{4}/([1][0-2]|[1-9]|[0][1-9])/([1-2][0-9]|30|[0-9]|0[0-9])$";
        Pattern numPattern=Pattern.compile(numRegex);
        Pattern emailPattern=Pattern.compile(emailRegex);
        Pattern datePattern=Pattern.compile(dateRegex);
        if(!numPattern.matcher(phoneNumber).matches())
            return "phoneNumber isn't valid";
        else if(!emailPattern.matcher(email).matches())
            return "email isn't valid";
        else if(!datePattern.matcher(birthDate).matches())
            return "birth date isn't valid";
        return "Signed up successfully";
    }
}
