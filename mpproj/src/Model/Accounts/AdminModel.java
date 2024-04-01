package Model.Accounts;

import java.util.Date;

public class AdminModel extends AccountsModel {

    private AdminModel admin;
    private AdminModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        super(username , password , fullName , email , phoneNumber , birthDate);
    }

    public AdminModel getAdmin(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        if(admin == null){
            admin = new AdminModel(username , password , fullName , email , phoneNumber , birthDate);
        }

        return admin;
    }

    public AdminModel getAdmin(){
        return this.admin;
    }
}
