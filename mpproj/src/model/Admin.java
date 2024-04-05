package model;

import java.util.Date;

public class Admin extends UserAccount {
    private static Admin admin;

    @Override
    public String toString(){
        String result = "";
        result += super.toString();
        return result;
    }

    public static Admin getAdmin(String userName, String password, String name, String email, String phoneNumber, String dateOfBirth) {
        if (admin == null) {
            String[] tmp = dateOfBirth.split("-");
            Date birth = new Date(Integer.valueOf(tmp[0])-1900,Integer.valueOf(tmp[1])-1,Integer.valueOf(tmp[2]));
            admin = new Admin(userName, password, name, email, phoneNumber,birth);
            Database.getDataBase().getUserAccounts().add(admin);
        }
        return admin;
    }

    public static Admin getAdmin(){
        return admin;
    }

    private Admin(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth) {
        super(userName, password, name, email, phoneNumber, dateOfBirth);
    }
}
