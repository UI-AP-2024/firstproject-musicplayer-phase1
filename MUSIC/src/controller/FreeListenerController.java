package controller;

import model.enums.PremiumSubscriptionPackages;
import model.model.audio.Audio;
import model.model.user.FreeListener;

public interface FreeListenerController extends ListenerController {

    void register(final FreeListener listener) throws Exception;

    void addAudioFreeListenerPlayList(final int playListId, final Audio audio) throws Exception;

    int newFreeListenerPlayList(final String username, final String name) throws Exception;

    FreeListener fetchFreeListener(final String username) throws Exception;

    void buyPremium(final String username, final PremiumSubscriptionPackages premiumSubscriptionPackages) throws Exception;

}
