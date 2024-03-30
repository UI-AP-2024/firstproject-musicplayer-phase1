package Controllers;
import Extra.RegexValidator;
import Models.Data.Database;
import Models.Genre;
import Models.User.*;

import java.util.ArrayList;
import java.time.LocalDate;

public class UserController {
    // Made this class follow the Singleton pattern cuz we only need one instance
    private Database database;
    private static UserController userController;

    public static UserController getUserController()
    {
        if(userController == null) userController = new UserController();
        return userController;
    }
    private UserController()
    {
        this.database = Database.getInstance();
    }

    public String signUp(char userType, String userName, String password, String name, String email, String phoneNumber, LocalDate dateOfBirth, String bio)
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
            case 'L':
                ArrayList<Genre> genres = userView.getGenres();
                database.addUser(new NormalListener(userName, password, name, email, phoneNumber, dateOfBirth, 50, null, genres));
                break;
            case 'S':
                database.addUser(new Singer(userName, password, name, email, phoneNumber, dateOfBirth, 0, bio));
                break;
            case 'P':
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
                    return "Loged in successfully";
                }
                return "Wrong Password";
            }
        }
        return "Invalid username";
    }
}
