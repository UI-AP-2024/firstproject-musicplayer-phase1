package Model;

import java.util.Date;

public class AdminModel extends User{
    private static AdminModel admin;
    private AdminModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        super(username,password,fullName,email,phoneNumber,birthDate);
    }
    public static AdminModel getAdmin(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        if(admin==null)
            admin = new AdminModel(username,password,fullName,email,phoneNumber,birthDate);
        return admin;
    }
    public static AdminModel getAdmin(){
        return admin;
    }
}
