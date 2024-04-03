package Controllers;
import Extra.RegexValidator;
import Models.Data.Database;
import Models.Genre;
import Models.User.*;
import Views.*;

import java.util.ArrayList;
import java.time.LocalDate;

public class AccountController {
    // Made this class follow the Singleton pattern cuz we only need one instance
    private Database database;
    private static AccountController accountController;

    public static AccountController getUserController()
    {
        if(accountController == null) accountController = new AccountController();
        return accountController;
    }
    private AccountController()
    {
        this.database = Database.getInstance();
    }

    private boolean checkName(String artistName)
    {
        for(User tmpUser : database.getUsers())
        {
            if(tmpUser instanceof Artist)
            {
                if(tmpUser.getName().equals(artistName)) return true;
            }
        }
        return false;
    }

    public String signUp(String userType, String userName, String password, String name, String email, String phoneNumber, LocalDate dateOfBirth, String bio)
    {
        for(User tmpUser : database.getUsers())
        {
            if(tmpUser.getUsername().equals(userName))
            {
                return "Username already taken";
            }
        }
        if(!RegexValidator.emailValidator(email)) return "Invalid email format";
        if(!RegexValidator.phoneValidator(phoneNumber)) return "Invalid phone number format";
        if(!RegexValidator.passwordValidator(password)) return "weak password pattern";
        String switchResult = "User added successfully";
        switch (userType)
        {
            case "L":
                ArrayList<Genre> genres = AccountView.getUserView().getGenres();
                database.addUser(new NormalListener(userName, password, name, email, phoneNumber, dateOfBirth, 50, null, genres));
                break;
            case "S":
                // since each Audio has an artistName property, artistNames must be unique
                if(checkName(name))
                {
                    switchResult = "An artist with the same name already exists";
                    break;
                }
                database.addUser(new Singer(userName, password, name, email, phoneNumber, dateOfBirth, 0, bio));
                break;
            case "P":
                // since each Audio has an artistName property, artistNames must be unique
                if(checkName(name))
                {
                    switchResult = "An artist with the same name already exists";
                    break;
                }
                database.addUser(new Podcaster(userName, password, name, email, phoneNumber, dateOfBirth, 0, bio));
                break;
            default:
                switchResult = "Invalid User Type";
                break;
        }
        return switchResult;
    }

    public String login(String userName, String password)
    {
        for(User tmpUser : database.getUsers())
        {
            if(tmpUser.getUsername().equals(userName))
            {
                if(tmpUser.getPassword().equals(password))
                {
                    database.setLogedInUser(tmpUser);
                    if(tmpUser instanceof NormalListener) new NormalListenerView().getInput();
                    else if(tmpUser instanceof PremiumListener) new PremiumListenerView().getInput();
                    else if(tmpUser instanceof Singer) new SingerView().getInput();
                    else if(tmpUser instanceof Podcaster) new PodcasterView().getInput();
                    else if(tmpUser instanceof Admin) new AdminView().getInput();
                }
                return "Wrong password";
            }
        }
        return "Wrong username";
    }

    public String showAccountInfo()
    {
        return database.getLogedInUser().toString();
    }
}
