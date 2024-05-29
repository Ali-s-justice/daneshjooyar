package AmirhosseinObligationToFuckingMVCPrograming;

public class TeacherController {

    private final TeacherModel teacherModel;
    private final TeacherView teacherView;

    public TeacherController(TeacherModel teacherModel, TeacherView teacherView) {
        this.teacherModel = teacherModel;
        this.teacherView = teacherView;
    }

    public boolean getTeacherNameValidation(TeacherView teacherView ,String teacherName){
        if (teacherView.equals(this.teacherView)){
            return teacherModel.teacherNameValidation(teacherName);
        }else {
            return true;
        }
    }

    public boolean getTeacherUsernameValidation(TeacherView teacherView, String teacherUsername){
        if (teacherView.equals(this.teacherView)){
            return teacherModel.teacherUsernameValidation(teacherUsername);
        }else {
            return true;
        }
    }

    public boolean getTeacherPasswordNotValidation(TeacherView teacherView, String password, String username ){
        if (teacherView.equals(this.teacherView)){
            return teacherModel.teacherPasswordNotValidation(password, username);
        }else {
            return true;
        }
    }

    public boolean getTeacherSignup(TeacherView teacherView, String teacherName, String teacherUsername, String teacherPassword){
        if (teacherView.equals(this.teacherView)){
            return teacherModel.teacherSignup(teacherName, teacherUsername, teacherPassword);
        }else {
            return false;
        }
    }



    public boolean getTeacherLoginValidation(TeacherView teacherView, String teacherLoginUsername, String teacherLoginPassword){
        if (teacherView.equals(this.teacherView)){
            return this.teacherModel.teacherLoginValidation(teacherLoginUsername, teacherLoginPassword);
        }else {
            return false;
        }
    }
}
