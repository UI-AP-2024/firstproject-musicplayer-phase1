package model;

import java.time.LocalDate;

public class Singer extends Artist{
    public Singer(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord, String biography) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord, biography);
    }
}
