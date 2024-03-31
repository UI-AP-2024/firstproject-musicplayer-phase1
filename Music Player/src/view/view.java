package view;

import controller.Admin2;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class view
{
    Admin2 admin2;
    public void firstMenu()
    {
        System.out.println("Welcome to my music player project");
        System.out.println("Please enter your username,password,name,email,phone number,date of birth");
        String regexEmail = "[a-zA-Z0-9.-]\\@(gmail|yahoo)\\.com$";
        String regexPhoneNumber = "0[0-9]{11,12}";
        Scanner sc = new Scanner(System.in);
        Pattern pattern1 = Pattern.compile(regexEmail);
        Pattern pattern2 = Pattern.compile(regexPhoneNumber);
        String email,phoneNumber;
        admin2.getAdmin2().newAdmin(sc.nextLine(),sc.nextLine(),sc.nextLine(),email=sc.nextLine(),phoneNumber=sc.nextLine(), LocalDate.from(LocalDate.parse(sc.nextLine())));
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
        System.out.println("Making admin's account completed successfully");
        System.out.println("1.Confirm");
        sc.nextLine();
    }
}
