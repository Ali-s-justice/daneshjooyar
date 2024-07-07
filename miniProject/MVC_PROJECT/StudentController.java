package MVC_PROJECT;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentController {

    StudentView studentView;
    StudentModel studentModel;

    public StudentController(StudentView studentView, StudentModel studentModel) {
        this.studentView = studentView;
        this.studentModel = studentModel;
    }

    public String[] getObligationSplitter(StudentView studentView, String inputString) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.obligationSplitter(inputString);
        } else {
            return null;
        }
    }

    public boolean getNoStudentFoundById(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.noStudentFoundById(studentId);
        } else {
            return true;
        }
    }

    public boolean getNoStudentFoundByUsername(StudentView studentView, String username) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.noStudentFoundByUsername(username);
        } else {
            return true;
        }
    }

    public String[] getObligationRemover(StudentView studentView, String[] splitInputString) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.getObligationRemover(splitInputString);
        } else {
            return null;
        }
    }

    public boolean getStudentUsernameNotValid(StudentView studentView, String username) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentUsernameNotValid(username);
        } else {
            return true;
        }
    }

    public boolean getStudentAlreadySignedUp(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentAlreadySignedUp(studentId);
        } else {
            return true;
        }
    }

    public boolean getStudentNotSignedUp(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return !this.studentModel.studentAlreadySignedUp(studentId);
        } else {
            return true;
        }
    }

    public void getSignupStudent(StudentView studentView, String studentId, String username, String password) {
        if (studentView.equals(this.studentView)) {
            this.studentModel.signupStudent(studentId, username, password);
        }
    }

    public String getStudentNameById(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentNameById(studentId);
        } else {
            return null;
        }
    }

    public String getStudentLoginWay(StudentView studentView, String loginInput) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentLoginWay(loginInput);
        } else {
            return null;
        }
    }

    public boolean getPasswordIsWrongForId(StudentView studentView, String studentId, String password) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.passwordIsWrongForId(studentId, password);
        } else {
            return true;
        }
    }

    public String getStudentIdByUsername(StudentView studentView, String username) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentIdByUsername(username);
        } else {
            return null;
        }
    }

    public String getStudentUsernameByID(StudentView studentView, String ID) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentUsernameByID(ID);
        } else {
            return null;
        }
    }

    public void getChangeUsername(StudentView studentView, String studentId, String newUsername) {
        if (studentView.equals(this.studentView)) {
            this.studentModel.changeUsername(studentId, newUsername);
        }
    }

    public void getChangePassword(StudentView studentView, String studentId, String newPassword) {
        if (studentView.equals(this.studentView)) {
            this.studentModel.changePassword(studentId, newPassword);
        }
    }

    public String getStudentInfoWay(StudentView studentView, String way) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentInfoWay(way);
        } else {
            return null;
        }
    }

    public int getAllCreditGetter(StudentView studentView, String studentID) {
        if (studentView.equals(this.studentView)) {
            return studentModel.AllCreditGetter(studentID);
        } else {
            return -1;
        }
    }

    public double getPrintAverage(StudentView studentView, String studentID, String kind) {
        if (studentView.equals(this.studentView)) {
            return studentModel.printAverage(studentID, kind);
        } else {
            return -1;
        }
    }

    public String getDeleteAccount(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.deleteAccount(studentId);
        } else {
            return null;
        }
    }

    public ArrayList<String> getBestScoreAndCourse(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.bestScoreAndCourse(studentId);
        } else {
            return null;
        }
    }

    public ArrayList<String> getWorseScoreAndCourse(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.worseScoreAndCourse(studentId);
        } else {
            return null;
        }
    }

    public int getAllExamNum(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.allExamNum(studentId);
        } else {
            return 0;
        }
    }

    public int getAllAssignmentNum(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.allAssignmentNum(studentId);
        } else {
            return 0;
        }
    }

    public void getSetAssignmentDone(StudentView studentView, String assignmentId, String studentId) {
        if (studentView.equals(this.studentView)) {
            this.studentModel.setAssignmentDone(assignmentId, studentId);
        }
    }

    public int getDoneAssignmentNum(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.doneAssignmentNum(studentId);
        } else {
            return 0;
        }
    }

    public ArrayList<Long> getDateOfEndGetter(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.dateOfEndGetter(studentId);
        } else {
            return null;
        }
    }

    public Map<String, String> getStudentSaraJobGetter(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentSaraJobGetter(studentId);
        } else {
            return null;
        }
    }

    public ArrayList<String> getDoneAssignmentNameGetter(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.doneAssignmentNameGetter(studentId);
        } else {
            return null;
        }
    }


    public void necessaryThing(StudentView studentView) {
        this.studentModel.jobIdSetter();
    }

    public ArrayList<String> getNewsForYouAssignmentNameGetter(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.newsForYouAssignmentNameGetter(studentId);
        } else {
            return null;
        }
    }

    public LinkedHashMap<String, String> newsHappenLinkGetter(StudentView studentView) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.newsHappenLinkGetter();
        } else {
            return null;
        }
    }

    public ArrayList<String> getNewsBirthdayGetter(StudentView studentView) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.newsBirthdayGetter();
        } else {
            return null;
        }
    }

    public ArrayList<String> getStudentChangedAssignmentDeadlineNameGetter(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentChangedAssignmentDeadlineNameGetter(studentId);
        } else {
            return null;
        }
    }

    public ArrayList<ArrayList<String>> getJobNotDonePage(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.jobNotDonePage(studentId);
        } else {
            return null;
        }
    }

    public String getAddJob(StudentView studentView, String deadline, String studentId, String title, String caption) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.addJob(deadline, studentId, title, caption);
        } else {
            return null;
        }
    }

    public String getSetJobDone(StudentView studentView, String jobId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.setJobDone(jobId);
        } else {
            return null;
        }
    }

    public String getDeleteJob(StudentView studentView, String studentId, String jobId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.deleteJob(studentId, jobId);
        } else {
            return null;
        }
    }

    public String getEditJob(StudentView studentView, String deadline, String title, String caption, String jobId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.editJob(deadline, title, caption, jobId);
        } else {
            return null;
        }
    }

    public ArrayList<ArrayList<String>> getDoneJob(StudentView studentView, String studentId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.doneJob(studentId);
        } else {
            return null;
        }
    }

    public ArrayList<ArrayList<String>> getClasses(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)) {
            return this.studentModel.classes(studentId);
        } else {
            return null;
        }
    }
    public boolean getNoCourseFoundById(StudentView studentView, String courseId){
        if (studentView.equals(this.studentView)) {
            return this.studentModel.noCourseFoundById(courseId);
        } else {
            return true;
        }
    }

    public boolean getStudentHasCourse(StudentView studentView, String studentId, String courseId) {
        if (studentView.equals(this.studentView)) {
            return this.studentModel.studentHasCourse(studentId, courseId);
        } else {
            return false;
        }
    }

    public void getAddCourse(StudentView studentView, String studentId, String courseId) {
        if (studentView.equals(this.studentView)) {
            this.studentModel.addCourse(studentId, courseId);
        }
    }

    public String getDeleteCourse(StudentView studentView, String studentId, String courseId){
        if (studentView.equals(this.studentView)) {
            return this.studentModel.deleteCourse(studentId, courseId);
        } else {
            return null;
        }
    }

    public ArrayList<ArrayList<String>> getWeekPlanner(StudentView studentView, String studentId){
        if (studentView.equals(this.studentView)) {
            return this.studentModel.weekPlanner(studentId);
        } else {
            return null;
        }
    }

    public ArrayList<ArrayList<String>> getNotDoneAssignmentPage(StudentView studentView, String studentId, String date){
        if (studentView.equals(this.studentView)) {
            return this.studentModel.notDoneAssignmentPage(studentId, date);
        } else {
            return null;
        }
    }


}
