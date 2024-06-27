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

    public boolean getPasswordNotValidate(TeacherView teacherView, String password, String username) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.PasswordNotValidate(password, username);
        } else {
            return false;
        }
    }

    public boolean getTeacherUsernameNotAvailable(TeacherView teacherView, String username) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.teacherUsernameNotAvailable(username);
        } else {
            return false;
        }
    }

    public boolean getTeacherPasswordIsWrong(TeacherView teacherView, String username, String password) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.teacherPasswordIsWrong(username, password);
        } else {
            return false;
        }
    }

    public boolean getNoStudentFoundById(TeacherView teacherView, String studentId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.noStudentFoundById(studentId);
        } else {
            return true;
        }
    }

    public String getThisTeacherIdGetter(TeacherView teacherView, String username){
        if (teacherView.equals(this.teacherView)){
            return this.teacherModel.thisTeacherIdGetter(username);
        }else {
            return null;
        }
    }

    public boolean getNoCourseFoundById(TeacherView teacherView, String courseId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.noCourseFoundById(courseId);
        } else {
            return true;
        }
    }

    public boolean getCourseIsNotForTeacher(TeacherView teacherView, String courseId, String teacherId){
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.courseIsNotForTeacher(courseId, teacherId);
        } else {
            return true;
        }
    }

    public boolean getStudentHasCourse(TeacherView teacherView, String studentId, String courseId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.studentHasCourse(studentId, courseId);
        } else {
            return false;
        }
    }

    public void getSetStudentScore(TeacherView teacherView, String studentId, String courseId, double score) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.setStudentScore(studentId, courseId, score);
        }
    }

    public boolean getDateHasValidPattern(TeacherView teacherView, String date) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.dateHasValidPattern(date);
        } else {
            return false;
        }
    }

    public boolean getHourHasValidPattern(TeacherView teacherView, String hour) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.hourHasValidPattern(hour);
        } else {
            return false;
        }
    }

    public void getSetExamDate(TeacherView teacherView, String courseId, String examDate, String examHour) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.setExamDate(courseId, examDate, examHour);
        }
    }

    public void getAddAssignment(TeacherView teacherView, String assignmentName, String isActive, String dateOfDeadline, String hourOfDeadline, String maker) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.addAssignment(assignmentName, isActive, dateOfDeadline, hourOfDeadline, maker);
        }
    }

    public boolean getNoAssignmentFoundById(TeacherView teacherView, String assignmentId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.noAssignmentFoundById(assignmentId);
        } else {
            return true;
        }
    }
    public String getAssignmentNameById(TeacherView teacherView, String assignmentId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.assignmentNameById(assignmentId);
        } else {
            return null;
        }
    }

    public void getRemoveAssignment(TeacherView teacherView, String assignmentId) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.removeAssignment(assignmentId);
        }
    }
    public boolean getAssignmentIsNotForTeacher(TeacherView teacherView, String assignmentId, String teacherId){
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.assignmentIsNotForTeacher(assignmentId, teacherId);
        }else {
            return true;
        }
    }

    public void getSetAssignmentForCourse(TeacherView teacherView, String assignmentId, String courseId) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.setAssignmentForCourse(assignmentId, courseId);
        }
    }

    public String getCourseNameById(TeacherView teacherView, String courseId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.courseNameById(courseId);
        } else {
            return null;
        }
    }

    public boolean getRemoveAssignmentFromCourse(TeacherView teacherView, String assignmentId, String courseId) {
        if (teacherView.equals(this.teacherView)) {
            return this.teacherModel.removeAssignmentFromCourse(assignmentId, courseId);
        }else {
            return false;
        }
    }

    public void getSetDeadline(TeacherView teacherView, String assignmentId, String dateOfDeadline, String hourOfDeadline) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.setDeadline(assignmentId, dateOfDeadline, hourOfDeadline);
        }
    }

    public void getSetAssignmentActivity(TeacherView teacherView, String assignmentId, String obligation) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.setAssignmentActivity(assignmentId, obligation);
        }
    }

    public void getSetCaptionForAssignment(TeacherView teacherView, String assignmentId, String caption) {
        if (teacherView.equals(this.teacherView)) {
            this.teacherModel.setCaptionForAssignment(assignmentId, caption);
        }
    }


}
