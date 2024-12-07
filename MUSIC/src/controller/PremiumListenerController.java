package controller;

import model.enums.PremiumSubscriptionPackages;
import model.model.audio.Audio;
import model.model.user.PremiumListener;

public interface PremiumListenerController extends ListenerController {
    void addAudioPremiumListenerPlayList(final int playListId, final Audio audio) throws Exception;

    int newPremiumListenerPlayList(final String username, final String name) throws Exception;

    PremiumListener fetchPremiumListener(final String username) throws Exception;

    void renewalPremium(final String username, final PremiumSubscriptionPackages premiumSubscriptionPackages) throws Exception;
}
