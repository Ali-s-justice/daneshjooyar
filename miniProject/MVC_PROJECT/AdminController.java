package MVC_PROJECT;

import java.util.ArrayList;
import java.util.HashMap;

public class AdminController {

    private final AdminModel adminModel;
    private final AdminView adminView;

    AdminController(AdminModel adminModel, AdminView adminView) {
        this.adminModel = adminModel;
        this.adminView = adminView;
    }

    public boolean getAdminUsernameNotAvailable(AdminView adminView, String username) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.adminUsernameNotAvailable(username);
        } else {
            return false;
        }
    }

    public boolean getPasswordNotValidate(AdminView adminView, String password, String username) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.PasswordNotValidate(password, username);
        } else {
            return false;
        }
    }

    public boolean getSaveAdmin(AdminView adminView, String userName, String password) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.saveAdmin(userName, password);
        } else {
            return false;
        }
    }

    public boolean getAdminPasswordIsWrong(AdminView adminView, String username, String password) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.adminPasswordIsWrong(username, password);
        } else {
            return false;
        }
    }

    public String getStudentSignUp(AdminView adminView, String StudentName, String Username, String Password) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.SignUpStudent(StudentName, Username, Password);
        }else {
            return null;
        }
    }

    public boolean getNoStudentFound(AdminView adminView, String studentName) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.noStudentFound(studentName);
        } else {
            return true;
        }
    }

    public boolean getNoStudentFoundById(AdminView adminView, String studentId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.noStudentFoundById(studentId);
        } else {
            return true;
        }
    }

    public boolean getNoCourseFoundById(AdminView adminView, String courseId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.noCourseFoundById(courseId);
        } else {
            return true;
        }
    }

    public void getStudentAccountRemover(AdminView adminView, String studentId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.studentAccountRemover(studentId);
        }
    }

    public boolean getTeacherUsernameValidation(AdminView adminView, String teacherUsername) {
        if (adminView.equals(this.adminView)) {
            return adminModel.teacherUsernameValidation(teacherUsername);
        } else {
            return true;
        }
    }

    public boolean getTeacherSignup(AdminView adminView, String teacherName, String teacherUsername, String teacherPassword) {
        if (adminView.equals(this.adminView)) {
            return adminModel.teacherSignup(teacherName, teacherUsername, teacherPassword);
        } else {
            return false;
        }
    }

    public boolean getNoTeacherFoundById(AdminView adminView, String teacherId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.noTeacherFoundById(teacherId);
        } else {
            return true;
        }
    }

    public void getTeacherAccountRemover(AdminView adminView, String teacherId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.teacherAccountRemover(teacherId);
        }
    }

    public String getAddCourse(AdminView adminView, String courseName, int credit) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.addCourse(courseName, credit);
        }else {
            return null;
        }
    }

    public void getSetCourseTeacher(AdminView adminView, String courseId, String teacherId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setCourseTeacher(courseId, teacherId);
        }
    }

    public boolean getCourseIdChecker(AdminView adminView, String courseId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.courseIdChecker(courseId);
        } else {
            return false;
        }
    }

    public String getCourseNameById(AdminView adminView, String courseId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.courseNameById(courseId);
        } else {
            return null;
        }
    }

    public void getRemoveCourse(AdminView adminView, String courseId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.removeCourse(courseId);
        } else {
            System.out.println("Something goes wrong!\n");
        }
    }

    public void getSetStudentCourse(AdminView adminView, String studentId, String courseId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setStudentCourse(studentId, courseId);
        }
    }

    public void getRemoveStudentCourse(AdminView adminView, String studentId, String courseId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.removeStudentCourse(studentId, courseId);
        }
    }

    public boolean getStudentHasCourse(AdminView adminView, String studentId, String courseId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.studentHasCourse(studentId, courseId);
        } else {
            return false;
        }
    }

    public void getSetStudentScore(AdminView adminView, String studentId, String courseId, double score) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setStudentScore(studentId, courseId, score);
        }
    }

    public double getPrintAverage(AdminView adminView, String studentId, String kind) {
        if (adminView.equals(this.adminView)) {
            return adminModel.printAverage(studentId, kind);
        } else {
            return -1;
        }
    }

    public String getPrintAllCourse(AdminView adminView, String studentId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.printAllCourse(studentId);
        } else {
            return "null";
        }
    }

    public int getPrintAllCredit(AdminView adminView, String studentId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.printAllCredit(studentId);
        } else {
            return -1;
        }
    }

    public void getSetExamDate(AdminView adminView, String courseId, String examDate, String examHour) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setExamDate(courseId, examDate, examHour);
        }
    }

    public boolean getDateHasValidPattern(AdminView adminView, String date) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.dateHasValidPattern(date);
        } else {
            return false;
        }
    }

    public boolean getHourHasValidPattern(AdminView adminView, String hour) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.hourHasValidPattern(hour);
        } else {
            return false;
        }
    }

    public String getAddAssignment(AdminView adminView, String assignmentName, String isActive, String dateOfDeadline, String hourOfDeadline, String maker) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.addAssignment(assignmentName, isActive, dateOfDeadline, hourOfDeadline, maker);
        }else {
            return null;
        }
    }

    public boolean getNoAssignmentFoundById(AdminView adminView, String assignmentId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.noAssignmentFoundById(assignmentId);
        } else {
            return true;
        }
    }

    public void getSetAssignmentForCourse(AdminView adminView, String assignmentId, String courseId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setAssignmentForCourse(assignmentId, courseId);
        }
    }

    public void getSetDeadline(AdminView adminView, String assignmentId, String dateOfDeadline, String hourOfDeadline) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setDeadline(assignmentId, dateOfDeadline, hourOfDeadline);
        }
    }

    public void getSetAssignmentActivity(AdminView adminView, String assignmentId, String obligation) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setAssignmentActivity(assignmentId, obligation);
        }
    }

    public void getSetCaptionForAssignment(AdminView adminView, String assignmentId, String caption) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.setCaptionForAssignment(assignmentId, caption);
        }
    }

    public String getAssignmentNameById(AdminView adminView, String assignmentId) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.assignmentNameById(assignmentId);
        } else {
            return null;
        }
    }

    public void getRemoveAssignment(AdminView adminView, String assignmentId) {
        if (adminView.equals(this.adminView)) {
            this.adminModel.removeAssignment(assignmentId);
        }
    }

    public boolean getRemoveAssignmentFromCourse(AdminView adminView, String assignmentId, String courseId) {
        if (adminView.equals(this.adminView)) {
           return this.adminModel.removeAssignmentFromCourse(assignmentId, courseId);
        }else {
            return false;
        }
    }

    public void getSaveStudentBirthday(AdminView adminView, String birthday, String studentId){
        if (adminView.equals(this.adminView)) {
            this.adminModel.saveStudentBirthday(birthday, studentId);
        }
    }

    public boolean getCourseDatePatternChecker(AdminView adminView, String input) {
        if (adminView.equals(this.adminView)) {
            return this.adminModel.courseDatePatternChecker(input);
        }else {
            return false;
        }
    }

    public void getSetDateForCourse(AdminView adminView, String courseId, ArrayList<ArrayList<String>> arrayList){
        if (adminView.equals(this.adminView)) {
            this.adminModel.setDateForCourse(courseId, arrayList);
        }
    }

    public void getEstimateTimeSetter(AdminView adminView, String assignmentId, String id, double estimateTime){
        if (adminView.equals(this.adminView)) {
            this.adminModel.estimateTimeSetter(assignmentId, id, estimateTime);
        }
    }


}
