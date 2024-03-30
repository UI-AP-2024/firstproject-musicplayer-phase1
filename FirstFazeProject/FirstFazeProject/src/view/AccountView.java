package view;

import java.util.Scanner;
public class AccountView {
    private static AccountView accountView;

    private AccountView() {
    }

    public static AccountView getAccountView() {
        if (accountView == null)
            accountView = new AccountView();
        return accountView;
    }

    Scanner inp = new Scanner(System.in);
}
