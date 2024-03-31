package model.user;

import java.util.Date;

public class Admin extends User{

    public Admin(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate) {
        super(password, firstName, lastName, emailAddress, phoneNumber, birthDate);
        
    }

    private static Admin admin;
    public static Admin getAdmin(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
         Date birthDate){
        if(admin==null){
            admin = new Admin(password, firstName, lastName, emailAddress, phoneNumber, birthDate);
            return admin;
        }
        else{
            return admin;
        }

    }
    public static Admin getAdmin(){
        return admin;
    }


    
    
}
