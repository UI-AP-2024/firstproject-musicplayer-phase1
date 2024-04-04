package controller.command.process.listener;

import controller.FreeListenerController;
import controller.ListenerControllerImp;
import controller.PremiumListenerController;
import controller.command.process.Process;
import controller.command.process.signup.LoginProcess;
import model.database.Database;
import model.exception.ProcessException;
import model.model.audio.Audio;
import model.model.audio.PlayList;
import model.validation.Validation;

public class AddPlayListProcess implements Process {

    private final FreeListenerController freeListenerController;
    private final PremiumListenerController premiumListenerController;
    private final Database database;

    public AddPlayListProcess() {
        freeListenerController = new ListenerControllerImp();
        premiumListenerController = new ListenerControllerImp();
        database = Database.getDatabase();
    }

    @Override
    public String exec(final String[] commands) throws ProcessException {

        if (commands.length != 3) {
            throw new ProcessException(this, "Invalid param");
        }

        Validation.checkLogin(this);

        final int playListId;
        final int audioId;
        try {
            playListId = Integer.parseInt(getValue(commands[1]));
            audioId = Integer.parseInt(getValue(commands[2]));
        } catch (Exception e) {
            throw new ProcessException(this, "Invalid param");
        }

        final Audio audio = database.fetchAudioById(audioId);
        if (audio == null) {
            throw new ProcessException(this, "Not found audio by id: " + audioId);
        }

        try {
            freeListenerController.fetchFreeListener(LoginProcess.user.getUsername());
            final PlayList playList = database.fetchFreeListenerPlayListById(playListId);
            if (playList == null) {
                throw new ProcessException(this, "Not found play list by id: " + playListId);
            }
            freeListenerController.addAudioFreeListenerPlayList(playListId, audio);

            return "Successfully added free listener play list";

        } catch (Exception e) {
            try {
                premiumListenerController.fetchPremiumListener(LoginProcess.user.getUsername());
                final PlayList playList = database.fetchPremiumListenerPlayListById(playListId);
                if (playList == null) {
                    throw new ProcessException(this, "Not found play list by id: " + playListId);
                }
                premiumListenerController.addAudioPremiumListenerPlayList(playListId, audio);

                return "Successfully added premium listener play list";

            } catch (Exception ignored) {
            }
        }

        throw new ProcessException(this, "Fatal Error");

    }
}
