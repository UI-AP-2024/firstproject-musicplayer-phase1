package view;

import controller.Admin2;
import controller.Listener2;
import controller.UserAccount2;
import model.Audio.Audio;
import model.Database;
import model.Genre;
import model.Playlist;
import model.UserAccount.Listener.Listener;
import model.UserAccount.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class view
{
    Scanner sc = new Scanner(System.in);
    String answer;
    UserAccount2 userAccount2;
    public void firstMenu()
    {
        System.out.println("1.Log in \n 2.Sign up");
        String answer = sc.nextLine();
        if(answer.equals("1"))
        {
            System.out.println("Please enter your username and password");
            if(userAccount2.findUser(sc.nextLine(),sc.nextLine()))
            {
                //menu ha
            }
            else
            {
                System.out.println("Your user name and password is not valid");
                firstMenu();
            }
        }
        if (answer.equals("2"))
        {
            System.out.println("Please enter your username,password,name,email,phone number,date of birth");
            String regexEmail = "[a-zA-Z0-9.-]@(gmail|yahoo)\\.com$";
            String regexPhoneNumber = "0[0-9]{11,12}";
            Pattern pattern1 = Pattern.compile(regexEmail);
            Pattern pattern2 = Pattern.compile(regexPhoneNumber);
            String email,phoneNumber,userName;
            userAccount2.getUserAccount2().newUserAccount(userName=sc.nextLine(),sc.nextLine(),sc.nextLine(),email=sc.nextLine(),phoneNumber=sc.nextLine(), LocalDate.from(LocalDate.parse(sc.nextLine())));
            Matcher matcher1 = pattern1.matcher(email);
            boolean bool1 = matcher1.matches();
            Matcher matcher2 = pattern2.matcher(phoneNumber);
            boolean bool2 = matcher2.matches();
            if(bool1==false)
            {
                System.out.println("The structure of the email entered is not correct , please enter correct email");
                email = sc.nextLine();
            }
            if(bool2==false)
            {
                System.out.println("The structure of the phone number entered is not correct , please enter correct phone number");
                phoneNumber = sc.nextLine();
            }
            for(int i=0;i<Database.getDatabase().getUserAccounts().size();i++)
            {
                if(userName.equals(Database.getDatabase().getUserAccounts().get(i).getUserName()))
                {
                    System.out.println("Your username already exists.Please choose another username");
                }

            }
            System.out.println("Create account completed successfully");
            System.out.println("1.Confirm");
            sc.nextLine();
            firstMenu();
        }
    }
    Genre genre;
    Listener listener;
    Listener2 listener2;
    Playlist playlist;
    Audio audio;
    public void secondMenu()
    {
        System.out.println("Please enter your user type \n 1.Listener 2.Artist");
        answer = sc.nextLine();
        if(answer.equals("1"))
        {
            System.out.println(" Please choose up to four of your favorite genres");
            System.out.println(Arrays.toString(Genre.values()));
            //genres
            listener.setAccountCredit(50);
            System.out.println("Please enter your playlist name");
            playlist = new Playlist(sc.nextLine());
            System.out.println("Pl");
            playlist.getPlayList().add(audio);//
            System.out.println("enter a string for find audio name and artist name");
            System.out.println(listener2.searching(sc.nextLine()));

        }
    }
}
