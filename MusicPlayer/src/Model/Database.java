package Model;

public class Database {
    private static Database database;
    private Database(){
    }
    public static Database getDatabase(){
        if(database==null)
            database = new Database();
        return database;
    }
}
