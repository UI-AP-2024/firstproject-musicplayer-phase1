package controller;
import model.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountController {
    private static final AccountController accountController = new AccountController();
    private AccountController() {
    }

    public static AccountController getAccountController() {
        return accountController;
    }
    public int signUp(String answer){
        String[] answers = answer.split(" -");
        String[] dateInfo = answers[7].split("\\.");
        Date date = new Date(Integer.parseInt(dateInfo[0]),Integer.parseInt(dateInfo[1])-1,Integer.parseInt(dateInfo[2]));
        switch (answers[0]){
            case "Signup":
                for (UserAccount user : Database.getData().getAllUsers()){
                    if (Objects.equals(user.getUniqueUserName(), answers[2])){
                        return 0;
                    }
                }
                String passwordRegex ="(?=.*[a-z])(?=.*\\d)[a-z0-9A-Z]{10,16}";
                boolean result1 = Pattern.compile(passwordRegex).matcher(answers[3]).find();
                if (!result1)
                    return -1;
                String emailRegex ="\\w{8,50}@(gmail|yahoo|hotmail|aol)\\.com$";
                boolean result2 = Pattern.compile(emailRegex).matcher(answers[5]).find();
                if (!result2)
                    return -2;
                String phoneNumberRegex ="^([0][9]|[\\+][9][8][9])(([1][0-9])|([2][0-2])|([0][0-5])|([3][0])|([3][3])|([3][5-9])|([4][1]))(\\d){7}$";
//              can be replaced by this ->       ^(09|\+989)((1[0-9])|(2[0-2])|(0[0-5])|(30)|(33)|(3[5-9])|(41))(\d){7}$
                boolean result3 = Pattern.compile(phoneNumberRegex).matcher(answers[6]).find();
                if (!result3)
                    return -3;
                switch (answers[1]){
                    case "L":
                        Listener listenerPerson = new Free(answers[2],answers[3],answers[4],answers[5],answers[6],date);
                        addUserToDatabase(listenerPerson);
                        return 1;
                    case "S":
                        Singer singerPerson = new Singer(answers[2],answers[3],answers[4],answers[5],answers[6],date,answers[8]);
                        addUserToDatabase(singerPerson);
                        return 2;
                    case "P":
                        Podcaster podcastPerson = new Podcaster(answers[2],answers[3],answers[4],answers[5],answers[6],date,answers[8]);
                        addUserToDatabase(podcastPerson);
                        return 3;
                }
                break;
            case "Login":
                break;
        }

        return 5;
    }
    private void addUserToDatabase(UserAccount userAccount){
        ArrayList<UserAccount> backUp =Database.getData().getAllUsers();
        backUp.add(userAccount);
        Database.getData().setAllUsers(backUp);
//      adds a user account to user accounts list in database
    }
    public StringBuilder showGenres(){
        int counter = 1;
        StringBuilder result = new StringBuilder("The genres are : ");
        Genre[] genres = Genre.values();
        for (Genre genre : genres){
            result.append(counter).append("_").append(genre.name()).append(" ");
        }
        return result;
    }
    public void addFavoriteGenres(String answer){
        Listener person = (Listener) Database.getData().getAllUsers().getLast();
        String[] answers = answer.split(",");
        ArrayList<Genre> result = person.getFavoriteGenres();
        for (int i = 0 ; i < 4 ; i++) {
            for (Genre genre : Genre.values()) {
                if (genre.name().equals(answers[i])){
                    result.add(genre);
                    break;
                }
            }
        }
        person.setFavoriteGenres(result);
    }
}
