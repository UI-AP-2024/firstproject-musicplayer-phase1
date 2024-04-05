package View;

import controller.Admin.adminController;

import java.util.Scanner;

public class adminView {
    private static adminView adminVi;
    private adminController adminCo = adminController.getAdminC();
    private adminView(){
    }

    public static adminView getAdminVi() {
        if (adminVi==null){
            adminVi = new adminView();
        }
        return adminVi;
    }

    public adminController getAdminCo() {
        return adminCo;
    }

    public void setAdminCo(adminController adminCo) {
        this.adminCo = adminCo;
    }

    public void adminCommands(){
        Scanner sc= new Scanner(System.in);
        String[] commands = sc.nextLine().split(" -");

        ///Logout command
        if (commands[0].equals("Logout")){
            System.out.println("You loged out now!");
            firstPage.getFirst().firstView();
        }

        /// AccountInfo
        if (commands[0].equals("AccountInfo")){
            System.out.println(adminCo.AccountInfo());
            adminCommands();
        }

        /// .Statistics command
        if (commands[0].equals("Statistics")){
            System.out.println(adminCo.Statistics());
            adminCommands();
        }

        /// Audios command
        if (commands[0].equals("Audios")){
            System.out.println(adminCo.showAudios());
            adminCommands();
        }

        ///  Audio -[audio’s ID] command
        if (commands[0].equals("Audio")){
            System.out.println(adminCo.showSelectAudio(Integer.parseInt(commands[1])));
            adminCommands();
        }

        ///  Artists command
        if (commands[0].equals("Artists")){
            System.out.println(adminCo.showArtists());
            adminCommands();
        }


        /// Artist -[username] command
        if (commands[0].equals("Artist")){
            System.out.println(adminCo.showSelectArtist(commands[1]));
            adminCommands();
        }

        /// Reports command
        if (commands[0].equals("Reports")){
            System.out.println(adminCo.showReports());
        }

    }
}
