package Models.User;

import java.util.Date;

public class Admin extends User {
    private static Admin instance;

    private Admin(String username, String password, String name, String email, String phoneNumber, Date dateOfBirth) {
        super(username, password, name, email, phoneNumber, dateOfBirth);
    }

    public static Admin getInstance()
    {
        return instance;
    }
    public static Admin getInstance(String username, String password, String name, String email, String phoneNumber, Date dateOfBirth) {
        if (instance == null) {
            instance = new Admin(username, password, name, email, phoneNumber, dateOfBirth);
        }
        return instance;
    }
}