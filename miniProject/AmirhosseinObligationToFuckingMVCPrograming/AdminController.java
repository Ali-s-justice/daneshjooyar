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

    public boolean getTeacherUsernameValidation(AdminView adminView, String teacherUsername){
        if (adminView.equals(this.adminView)){
            return adminModel.teacherUsernameValidation(teacherUsername);
        }else {
            return true;
        }
    }

    public boolean getTeacherSignup(AdminView adminView, String teacherName, String teacherUsername, String teacherPassword){
        if (adminView.equals(this.adminView)){
            return adminModel.teacherSignup(teacherName, teacherUsername, teacherPassword);
        }else {
            return false;
        }
    }

    public boolean getNoTeacherFound(AdminView adminView, String teacherUsername){
        if (adminView.equals(this.adminView)){
            return this.adminModel.noTeacherFound(teacherUsername);
        }else {
            return true;
        }
    }

    public void getTeacherAccountRemover(AdminView adminView, String teacherUsername){
        if (adminView.equals(this.adminView)){
            this.adminModel.teacherAccountRemover(teacherUsername);
        }
    }

    public void getAddCourse(AdminView adminView, String courseName, int credit){
        if (adminView.equals(this.adminView)){
            this.adminModel.addCourse(courseName, credit);
        }
    }

    public void getSetCourseTeacher(AdminView adminView,String courseId, String teacherUsername){
        if (adminView.equals(this.adminView)){
            this.adminModel.setCourseTeacher(courseId, teacherUsername);
        }
    }

    public boolean getCourseIdChecker(AdminView adminView, String courseId){
        if (adminView.equals(this.adminView)){
            return this.adminModel.courseIdChecker(courseId);
        }else {
            return false;
        }
    }

    public String getCourseNameById(AdminView adminView, String courseId){
        if (adminView.equals(this.adminView)){
            return this.adminModel.courseNameById(courseId);
        }else {
            return null;
        }
    }

    public void getRemoveCourse(AdminView adminView, String courseId){
        if (adminView.equals(this.adminView)){
            this.adminModel.removeCourse(courseId);
        }else {
            System.out.println("Something goes wrong!\n");
        }
    }

}
