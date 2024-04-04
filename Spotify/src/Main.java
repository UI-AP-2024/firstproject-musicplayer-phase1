import model.Database.Database;
import model.UserAccounts.Admin.Admin;
import View.*;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Database.getDatabase().getAllUsersList().add(Admin.getAdmin("Admin","1234","first admin","test@gmail.com","09905854478",
                new Date(2005,4,13)));

        firstPage.getFirst();

    }
}