import Model.AdminModel;
import Model.Genre;
import Model.User;

import java.util.Date;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);

        showFirstMenu();
    }
    public static void showFirstMenu(){
        System.out.println("1) NewAdmin\n2) exit");
        Scanner sin = new Scanner(System.in);
        int enter = sin.nextInt();
        if(enter==1){
            sin.nextLine();
            String username = sin.nextLine();
            String password = sin.nextLine();
            String fullName = sin.nextLine();
            String email = sin.nextLine();
            String phoneNumber = sin.nextLine();
            String birth = sin.nextLine();
            Date birthDate = new Date(birth);
            User users = AdminModel.getAdmin(username, password, fullName, email, phoneNumber, birthDate);
            System.out.println("Done!");
        }
        else
            showFirstMenu();
    }

}