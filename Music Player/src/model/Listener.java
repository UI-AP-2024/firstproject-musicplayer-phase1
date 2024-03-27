package model;

import java.time.LocalDate;

public class Listener extends UserAccount
{
    public Listener(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord);
    }
}
