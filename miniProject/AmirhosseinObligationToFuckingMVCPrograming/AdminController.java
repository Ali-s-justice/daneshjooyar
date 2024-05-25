package AmirhosseinObligationToFuckingMVCPrograming;

public class AdminController {

    private final AdminModel adminModel;
    private final AdminView adminView;

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

    public boolean getAdminLoginValidation(AdminView adminView, String adminLoginUsername, String adminLoginPassword ){
        if (adminView.equals(this.adminView)){
            return this.adminModel.adminLoginValidation(adminLoginUsername, adminLoginPassword);
        }else {
            return false;
        }
    }

    public boolean getStudentNameValidation(AdminView adminView, String studentName){
        if (adminView.equals(this.adminView)){
            return this.adminModel.StudentNameValidation(studentName);
        }else {
            return false;
        }
    }

    public boolean getStudentUsernameValidation(AdminView adminView, String username){
        if (adminView.equals(this.adminView)){
            return this.adminModel.StudentUsernameValidation(username);
        }else {
            return false;
        }
    }

    public void getStudentSignUp(AdminView adminView , String StudentName, String Username, String Password){
        if (adminView.equals(this.adminView)){
            this.adminModel.SignUpStudent(StudentName, Username, Password);
        }
    }

    public void getStudentIDSetter(AdminView adminView){
        if (adminView.equals(this.adminView)){
            this.adminModel.studentIDSetter();
        }
    }

    public boolean getNoStudentFound(AdminView adminView, String studentName){
        if (adminView.equals(this.adminView)){
            return this.adminModel.noStudentFound(studentName);
        }else {
            return true;
        }
    }

    public void getStudentAccountRemover(AdminView adminView, String studentName){
        if (adminView.equals(this.adminView)){
            this.adminModel.studentAccountRemover(studentName);
        }
    }
}
