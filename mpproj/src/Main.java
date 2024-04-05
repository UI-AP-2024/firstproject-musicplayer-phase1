import model.DataBase.DataBaseModel;
import model.UserAccount.AdminModel;
import view.SelectUserView;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        AdminModel admin = AdminModel.getAdmin("ADMIN", "ADMIN0000", "unknown", "unknown@nothing.com", "09100000000", LocalDate.parse("1901-01-01"));
        DataBaseModel.getDataBase().getUsers().add(admin);
        SelectUserView.getSelectUserView().signUpLogInOut();
    }
}
