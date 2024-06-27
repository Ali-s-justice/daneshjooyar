package MVC_PROJECT;

public class StudentController {

    StudentView studentView;
    StudentModel studentModel;

    StudentController(StudentView studentView, StudentModel studentModel){
        this.studentView = studentView;
        this.studentModel = studentModel;
    }

    public String[] getObligationSplitter(StudentView studentView, String inputString){
        if (studentView.equals(this.studentView)){
            return this.studentModel.obligationSplitter(inputString);
        }else {
            return null;
        }
    }

    public boolean getNoStudentFoundById(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)){
            return this.studentModel.noStudentFoundById(studentId);
        }else {
            return true;
        }
    }

    public boolean getNoStudentFoundByUsername(StudentView studentView, String username){
        if (studentView.equals(this.studentView)){
            return this.studentModel.noStudentFoundByUsername(username);
        }else {
            return true;
        }
    }

    public String[] getObligationRemover(StudentView studentView, String[] splitInputString){
        if (studentView.equals(this.studentView)){
            return this.studentModel.getObligationRemover(splitInputString);
        }else {
            return null;
        }
    }

    public boolean getStudentUsernameNotValid(StudentView studentView, String username){
        if (studentView.equals(this.studentView)){
            return this.studentModel.studentUsernameNotValid(username);
        }else {
            return true;
        }
    }

    public boolean getStudentAlreadySignedUp(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)){
            return this.studentModel.studentAlreadySignedUp(studentId);
        }else {
            return true;
        }
    }

    public boolean getStudentNotSignedUp(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)){
            return !this.studentModel.studentAlreadySignedUp(studentId);
        }else {
            return true;
        }
    }

    public void getSignupStudent(StudentView studentView, String studentId, String username, String password){
        if (studentView.equals(this.studentView)){
            this.studentModel.signupStudent(studentId, username, password);
        }
    }

    public String getReturnName(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)){
            return this.studentModel.returnName(studentId);
        }else {
            return null;
        }
    }

    public String getStudentLoginWay(StudentView studentView, String loginInput){
        if (studentView.equals(this.studentView)){
            return this.studentModel.studentLoginWay(loginInput);
        }else {
            return null;
        }
    }

    public boolean getPasswordIsWrongForId(StudentView studentView, String studentId, String password){
        if (studentView.equals(this.studentView)){
            return this.studentModel.passwordIsWrongForId(studentId, password);
        }else {
            return true;
        }
    }

    public String getStudentIdByUsername(StudentView studentView, String username){
        if (studentView.equals(this.studentView)){
            return this.studentModel.studentIdByUsername(username);
        }else {
            return null;
        }
    }

    public String getStudentUsernameByID(StudentView studentView, String ID){
        if (studentView.equals(this.studentView)){
            return this.studentModel.studentUsernameByID(ID);
        }else {
            return null;
        }
    }

    public void getChangeUsername(StudentView studentView, String studentId, String newUsername){
        if (studentView.equals(this.studentView)){
            this.studentModel.changeUsername(studentId, newUsername);
        }
    }

    public void getChangePassword(StudentView studentView, String studentId, String newPassword){
        if (studentView.equals(this.studentView)){
            this.studentModel.changePassword(studentId, newPassword);
        }
    }

    public String getStudentInfoWay(StudentView studentView, String way){
        if (studentView.equals(this.studentView)){
            return this.studentModel.studentInfoWay(way);
        }else {
            return null;
        }
    }

    public int getAllCreditGetter(StudentView studentView, String studentID){
        if (studentView.equals(this.studentView)){
            return studentModel.AllCreditGetter(studentID);
        }else {
            return -1;
        }
    }

    public double getPrintAverage(StudentView studentView, String studentID, String kind){
        if (studentView.equals(this.studentView)){
            return studentModel.printAverage(studentID, kind);
        }else {
            return -1;
        }
    }

    public String getDeleteAccount(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)){
            return this.studentModel.deleteAccount(studentId);
        }else {
            return null;
        }
    }


}
