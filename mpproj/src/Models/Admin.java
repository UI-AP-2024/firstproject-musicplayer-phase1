package Model;

public class Admin extends UserAccount {
    private static Admin admin;
    private Admin(String userName, String password, String firstAndLastName,
                  String email, String phoneNumber, int year, int month, int day) {
        super(userName, password, firstAndLastName, email, phoneNumber, year, month, day);
        DataBase.getDataBase().getUsersList().add(this);
    }

    public static Admin getAdmin(String userName, String password, String firstAndLastName,
                          String email, String phoneNumber, int year, int month, int day) {
        if (admin == null) {
            admin = new Admin(userName, password, firstAndLastName,
                    email, phoneNumber, year, month, day);
        }
        return admin;
    }
    public static Admin getAdmin(){
        return admin;
    }

    public static void setAdmin(Admin admin) {
        Admin.admin = admin;
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
