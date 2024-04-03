import model.*;
import view.AccountView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        Date date1 = new Date(date.getYear(), date.getMonthValue(),date.getDayOfMonth()+45);
        System.out.println(date1.getYear());
        System.out.println(date1.getMonth());
        System.out.println(date1.getDate());
        AccountView.getAccountView().showFirstMenu();
    }
}
//Signup -L -masih -masihMMM83 -msaihroughai -masihmasih@gmail.com -09135111562 -2005.1.8. -ahfbkginvunbhdnjkj
//Rock,Pop,Society,History
//Login -masih -masihMMM83
//Follow -username

//Timer timer = new Timer();
//TimerTask task = new TimerTask() {
//    public void run() {
//        System.out.println("Task is running...");
//    }
//};
//timer.schedule(task, 5000);