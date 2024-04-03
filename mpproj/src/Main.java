import Controller.ListenerController;
import Model.Admin;
import Model.DataBase;
import Model.Listener;
import view.AdminView;
import view.ListenerView;
import view.View;

public class Main {
    public static void main(String[] args) {
        Admin.getAdmin("Amir","1234","AmirAliGoli",
                "amir@gmail.com","09133214546",1379,3,5);
        View.firstPage();

    }
}
