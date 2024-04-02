package controller;

import model.Database;
import model.report.Report;

public class AdminController
{
    private static AdminController adminController;
    private AdminController(){}
    public static AdminController getAdminController()
    {
        if(adminController==null)
            adminController=new AdminController();
        return adminController;
    }
    public String getReports()
    {
        StringBuilder answer=new StringBuilder();
        for(Report temp: Database.getDatabase().getReports())
            if(temp!=null)
                answer.append(temp).append("\n");
        return answer.toString();
    }
}
