package model;

import java.time.LocalDate;

public class Premium extends Listener{
    public Premium(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord);
    }
}
