package Model.Reports;

import Model.Accounts.AccountsModel;

public class ReportModel {
    private final AccountsModel sender;
    private final AccountsModel reportedArtist;
    private final String description;


    public ReportModel(AccountsModel sender , AccountsModel reportedArtist , String description){
        this.sender = sender;
        this.reportedArtist = reportedArtist;
        this.description = description;
    }

    public AccountsModel getSender(){
        return this.sender;
    }

    public AccountsModel getReportedArtist(){
        return this.reportedArtist;
    }

    public String getDescription(){
        return this.description;
    }

    @Override
    public String toString(){
        return "Sender : "+ this.sender.getUsername() +
                "\nReported Artist : "+ this.reportedArtist.getUsername() +
                "\nDescription : "+ this.description;
    }
}
