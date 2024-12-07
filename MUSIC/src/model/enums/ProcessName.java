package model.enums;

import controller.command.process.AccountInfoProcess;
import controller.command.process.ExitProcess;
import controller.command.process.FavouriteGenresProcess;
import controller.command.process.Process;
import controller.command.process.admin.*;
import controller.command.process.artist.FollowersProcess;
import controller.command.process.artist.NewAlbumProcess;
import controller.command.process.library.*;
import controller.command.process.listener.AddPlayListProcess;
import controller.command.process.listener.FollowProcess;
import controller.command.process.listener.GetSuggestionsProcess;
import controller.command.process.listener.NewPlayListProcess;
import controller.command.process.signup.*;

import java.util.Arrays;
import java.util.Optional;

public enum ProcessName {

    SIGNUP("Signup", new SignupProcess(), true),
    SIGNUP_LISTENER("SignupListener", new SignupListenerProcess(), false),
    SIGNUP_SINGER("SignupSinger", new SignupSingerProcess(), false),
   SIGNUP_PODCASTER("SignupPodcaster" , new SignupPodcasterProcess(),false),
    LOGIN("Login", new LoginProcess(), true),
    LOGOUT("Logout", new LogoutProcess(), true),
    ACCOUNT_INFO("AccountInfo", new AccountInfoProcess(), true),
    FAVOURITE_GENRES("FavouriteGenres", new FavouriteGenresProcess(), true),
    EXIT("exit", new ExitProcess(), true),

    // Listener
    GET_SUGGESTIONS("GetSuggestions", new GetSuggestionsProcess(), true),
    FOLLOW("Follow", new FollowProcess(), true),
    NEW_PLAY_LIST("NewPlayList", new NewPlayListProcess(), true),
    ADD_PLAY_LIST("AddPlayList", new AddPlayListProcess(), true),

    // Library
    SHOW_PLAY_LIST("ShowPlaylists", new ShowPlaylistsProcess(), true),
    LIKE_AUDIO("Like", new LikeAudioProcess(), true),
    LYRIC_MUSIC("Lyric", new LyricMusicProcess(), true),
    FOLLOWINGS("Followings", new FollowingsProcess(), true),
    REPORT("Report", new ReportProcess(), true),
    BUY_PREMIUM("GetPremium", new BuyPremiumProcess(), true),
    RENEWAL_PREMIUM("IncreaseCredit", new RenewalPremiumProcess(), true),

    // Admin
    STATISTICS("Statistics", new StatisticsProcess(), true),
    AUDIOS("Audios", new AudiosProcess(), true),
    AUDIO("Audio", new AudioProcess(), true),
    ARTISTS("Artists", new ArtistsProcess(), true),
    ARTIST("Artist", new ArtistProcess(), true),
    REPORTS("Reports", new ReportsProcess(), true),

    // Artist
    FOLLOWERS("Followers", new FollowersProcess(), true),
    NEW_ALBUM("NewAlbum", new NewAlbumProcess(), true),

    //
    ;
    public final String name;
    public final Process process;
    private final boolean publicCommand;

    ProcessName(final String name, final Process process, final boolean publicCommand) {
        this.name = name;
        this.process = process;
        this.publicCommand = publicCommand;
    }

    public static Process getProcess(final String processName) {
        try {
            final Optional<ProcessName> processNameOptional = Arrays.stream(values()).filter(item -> item.name.equals(processName) && item.publicCommand).findFirst();
            if (processNameOptional.isPresent()) {
                return processNameOptional.get().process;
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
