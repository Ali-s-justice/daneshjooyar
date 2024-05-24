package AmirhosseinObligationToFuckingMVCPrograming;

import java.io.FileWriter;

public class AdminController {

    AdminModel adminModel;
    AdminView adminView;

    AdminController(AdminModel adminModel, AdminView adminView){
        this.adminModel = adminModel;
        this.adminView = adminView;
    }
    public boolean getAdminNameValidation(AdminView adminView, String username){
        if (adminView.equals(this.adminView)){
            return this.adminModel.adminNameValidation(username);
        }else {
            return false;
        }
    }
    public boolean getAdminPasswordNotValidation(AdminView adminView, String password,String username){
        if (adminView.equals(this.adminView)){
            return this.adminModel.PasswordNotValidation(password, username);
        }else {
            return false;
        }
    }

    public boolean getSaveAdmin(AdminView adminView, String userName , String password){
        if (adminView.equals(this.adminView)){
            return this.adminModel.saveAdmin(userName, password);
        }else {
            return false;
        }
    }
}
