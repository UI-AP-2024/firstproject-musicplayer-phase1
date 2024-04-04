package model.AccountUser;

import model.Database;

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
            instance = new Admin("admin", "admin123", "Admin User", "aAdmi23n@gmail.com", "09134567723", new Date());
        }
        Database.getInstance().getUsers().add(instance);
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



