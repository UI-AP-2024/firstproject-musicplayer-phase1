import model.Admin;
import view.AccountView;
import view.AdminView;

public class Main {
    public static void main(String[] args) {
        Admin.getAdmin("admin11","admin11@pass","Alireza harandi","Alirezaharandi11@gmail.com","09231039186","1383-10-10");
        AccountView.getAccountView().showFirstMenu();

    }
}

/*

Signup -S -singer11 -singer11@pass -Ali jafari -ali233gmail.com -09140405053 -1383-10-9 -biosinger11
Signup -S -singer11 -singer11@pass -Ali jafari -ali233@gmail.com -091404050643 -1383-10-9 -biosinger11

Signup -S -singer11 -singer11@pass -Ali jafari -ali233@gmail.com -09140405053 -1383-10-9 -biosinger11
AccountInfo
NewAlbum -album1
AccountInfo
Publish -M -music1 -POP -lyric -sdhfskfskfgb.com -cover -1
Followers
ViewsStatistics
CalculateEarnings
AccountInfo
Logout


Signup -P -podcaster11 -podcaster11@pass -Ali jafari -ali23gmail.com -09140405053 -1383-10-9 -bioPodcaster11
Signup -P -podcaster11 -podcaster11@pass -Ali jafari -ali233@gmail.com -08140405053 -1383-10-9 -bioPodcaster11

Signup -P -podcaster11 -podcaster11@pass -Ali jafari -ali233@gmail.com -09140405053 -1383-10-9 -bioPodcaster11
AccountInfo
Publish -P -pod1 -TRUE_CRIME -lyric -sdhfskfskfgb.com -cover
Followers
ViewsStatistics
CalculateEarnings
AccountInfo
Logout


Signup -P -podcaster22 -podcaster11@pass -Reza jafari -ali233@gmail.com -09140405053 -1383-10-9 -bioPodcaster11
Publish -P -pod22 -TRUE_CRIME -lyric -sdhfskfskfgb.com -cover
Logout


Signup -L -listener11 -listener11@pass -Ali jafari -ali233@g@mail.com -09140405053 -1383-10-9
Signup -L -listener11 -listener11@pass -Ali jafari -ali233@gmail.com -091480405053 -1383-10-9
Signup -L -podcaster11 -listener11@pass -Ali jafari -ali233@gmail.com -09140405053 -1383-10-9

Signup -L -listener11 -listener11@pass -Ali jafari -ali233@gmail.com -09140405053 -1383-10-9
FavouriteGenres -POP,ROCK,JAZZ,SOCIETY,HISTORY
FavouriteGenres -POP,ROCK,JAZZ
Follow -podcaster11
Artists
Artists -singer11
Search -Ali jafari
Search -music1
Followings
Report -podcaster11 -exp
IncreaseCredit -20
AccountInfo
Like -2
Lyric -1
Play -1
NewPlaylist -playlist_1
Sort -L
Sort -P
Add -playlist_1 -2
Add -playlist_1 -1
Add -playlist_1 -2
NewPlaylist -playlist_2
ShowPlaylists
SelectPlaylist -playlist_1
ShowPremium
GetPremium -ONE_MONTH
Filter -A -Ali jafari
Filter -D -2024-4-5
Filter -G -POP
GetSuggestions
Logout


Login -admin11 -admin11@pass
Reports
Artist -singer11
Artists
Audio -2
Audios
Statistics
Logout
 */