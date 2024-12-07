package controller;

import model.database.Database;

public class Controller {
    protected final Database database;

    public Controller() {
        database = Database.getDatabase();
    }
}
