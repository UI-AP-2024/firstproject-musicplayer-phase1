package model.AccountUser;

import java.util.Date;

public class Admin extends AccountUser {
    private static Admin instance;

    //*********************************************
    private Admin(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(userName, password, fullName, email, phoneNumber, birthDate);
    }
    //*********************************************

    public static Admin getInstance() {
        if (instance == null) {
            instance = new Admin("admin", "admin123", "Admin User", "admin@example.com", "1234567890", new Date());
        }
        return instance;
    }
    @Override
    public String toString() {
        return "Admin{" +
                "username='" +
                getUserName() + '\'' +
                ", password='" +
                getPassword() + '\'' +
                ", fullName='" +
                getFullName() + '\'' +
                ", email='" +
                getEmail() + '\'' +
                ", phoneNumber='" +
                getPhoneNumber() + '\'' +
                ", birthDate="
                + getBirthDate() +
                '}';
    }
}



