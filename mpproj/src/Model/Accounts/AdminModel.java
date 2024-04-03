package Model.Accounts;

import Model.Database.Database;

import java.util.Date;

public class AdminModel extends AccountsModel {

    private static AdminModel admin;
    private AdminModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        super(username , password , fullName , email , phoneNumber , birthDate);

    }

    public static AdminModel getAdmin(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        if(admin == null){
            admin = new AdminModel(username , password , fullName , email , phoneNumber , birthDate);
            Database.getDatabase().addAccount(admin);
        }
        return admin;
    }

    public AdminModel getAdmin(){
        return admin;
    }
}
