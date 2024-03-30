package model;

import java.util.ArrayList;

public class Database {
    private static  Database instance;
    private List<User> users;
    private List<Audio> audiofiles;
    private List<Report> reports;

    //*********************************************
   private Database(){
       users = new ArrayList<>();
       audiofiles = new ArrayList<>();
       reports = new ArrayList<>();

   }
    //*********************************************

public static Database getInstance(){
       if(instance = null){
           instance = new Database();
       }
       return instance;
}

}