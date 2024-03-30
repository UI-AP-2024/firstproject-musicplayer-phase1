package Controllers;

import Models.Audio.Audio;
import Models.Data.Database;
import Models.PremiumPlan;
import Models.PremiumPlan;
import Models.User.Listener;
public abstract class ListenerController {
    private Database database;

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
    private Listener listenerModel;

    public Listener getListenerModel() {
        return listenerModel;
    }

    public void setListenerModel(Listener listenerModel) {
        this.listenerModel = listenerModel;
    }
    public ListenerController()
    {
        this.database = Database.getInstance();
        listenerModel = (Listener) this.database.getLogedInUser();
    }
    public void addCredit(double value) {
        listenerModel.setCredit(listenerModel.getCredit() + value);
        database.updateUser(this.listenerModel);
    }

    public abstract String makeNewPlaylist(String name);
    public abstract String addToPlaylist(String playistName, Audio audio);
    public abstract String purchasePremium(PremiumPlan plan);
}
