package MVC_PROJECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class StudentView {
    StudentController studentController;

    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }

    // @Override
    // public void run() {
    // synchronized (lock) {
    // System.out.println("Handling client: " + clientSocket);
    //
    // try (BufferedReader in = new BufferedReader(new
    // InputStreamReader(clientSocket.getInputStream()));
    // PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
    //
    // // Read the String message from the client
    // String clientMessage = in.readLine();
    // String[] splitInputString = studentController.getObligationSplitter(this,
    // clientMessage);
    // String[] restString = studentController.getObligationRemover(this,
    // splitInputString);
    //
    // Thread.sleep(1000);
    //
    // //responding to front --> splitInputString[0] = obligation & restString =
    // rest of input
    // String response = allObligation(splitInputString[0], restString);
    // out.println("Server received: " + clientMessage);
    //
    // clientSocket.close();
    // System.out.println("Connection closed: " + clientSocket);
    // } catch (IOException | InterruptedException e) {
    // System.out.println("Exception!");
    // } finally {
    // lock.notify();
    // }
    // }
    // }


    public void RUN() {
        while (true) {
            System.out.println("Enter front message:\n[1]:BREAK!");
            Scanner input = new Scanner(System.in);
            String inputString = input.nextLine();
            if (inputString.equals("1")) {
                break;
            }
            String[] splitInputString = studentController.getObligationSplitter(this, inputString);
            String response = allObligation(splitInputString);
            System.out.println(response);
        }
    }
    //Testing Project

    public String allObligation(String[] command) {

        studentController.necessaryThing(this);

        String obligation = command[0];
        String[] restString = studentController.getObligationRemover(this, command);
        if (restString == null) {
            return ":}\n";
        }
        switch (obligation) {
            case "signup"://signup student
                return signup(restString);
            case "login":
                String loginWay = studentController.getStudentLoginWay(this, restString[0]);
                if (loginWay.equals("loginWithID")) {
                    return loginWithId(restString, true);
                } else {
                    return loginWithUsername(restString);
                }
            case "changeUsername":
                return chaneUsername(restString);
            case "changePassword":
                return changePassword(restString);
            case "userInfo":
                String way = studentController.getStudentInfoWay(this, restString[0]);
                if (!way.equals("ID")) {
                    restString[0] = studentController.getStudentIdByUsername(this, restString[0]);
                }
                return userInfo(restString);
            case "deleteAccount":
                return deleteAccount(restString);
            case "sara":
                return sara(restString);
            case "newsForYou":
                return newsForYou(restString);
            case "newsHappen":
                return newsHappen();
            case "newsBirthday":
                return newsBirthday();
            case "jobNotDone":
                return jobNotDone(restString);
            case "addJob":
                return addJob(restString);
            case "setJobDone":
                return setJobDone(restString);
            case "deleteJob":
                return deleteJob(restString);
            case "editJob":
                return editJob(restString);
            case "doneJob":
                return doneJob(restString);
            case "classes":
                return classes(restString);
            case "addCourse":
                return addCourse(restString);
            case "deleteCourse":
                return deleteCourse(restString);
            case "weekPlanner":
                return weekPlanner(restString);
            case "assignmentToday":
                return assignmentToday(restString);
            case null:
                return "error";
            default:
                return "noObligation";
        }
    }

    private String assignmentToday(String[] restString){
        //restString = studentId - todayDate(2025/12/04)
        return studentController.getNotDoneAssignmentPage(this, restString[0], restString[1]).toString();
    }

    private String weekPlanner(String[] restString){
        //restString = studentId
        return studentController.getWeekPlanner(this, restString[0]).toString();
    }

    private String deleteCourse(String[] restString){
        //restString = studentId - courseId
        return studentController.getDeleteCourse(this, restString[0], restString[1]);
    }

    private String addCourse(String[] restString){
        //restString = studentId - courseId
        if (studentController.getNoCourseFoundById(this, restString[1])){
            return "noCourseFound";
        }
        if (studentController.getStudentHasCourse(this, restString[0], restString[1])) {
            return "alreadySignedUp";
        }
        studentController.getAddCourse(this, restString[0], restString[1]);
        return "successful";
    }

    private String classes(String[] restString){
        // restString = studentId
        return studentController.getClasses(this, restString[0]).toString();
    }

    private String doneJob(String[] restString) {
        //restString : studentId
        return studentController.getDoneJob(this, restString[0]).toString();
    }

    private String editJob(String[] restString) {
        //rest String: Deadline(YYYY/MM/DD,HH:MM)/ title / caption / jobId
        return studentController.getEditJob(this, restString[0], restString[1], restString[2], restString[3]);
    }

    private String deleteJob(String[] restString) {
        //restString = studentId // jobId
        return studentController.getDeleteJob(this, restString[0], restString[1]);
    }

    private String setJobDone(String[] restString) {
        //restString = jobId
        return studentController.getSetJobDone(this, restString[0]);
    }

    private String addJob(String[] restString) {
        //rest String: Deadline(YYYY/MM/DD,HH:MM)/ studentId / title / caption
        return studentController.getAddJob(this, restString[0], restString[1], restString[2], restString[3]);
    }

    private String jobNotDone(String[] restString) {
        //restString = studentId
        return studentController.getJobNotDonePage(this, restString[0]).toString();
    }

    private String newsBirthday() {
        return studentController.getNewsBirthdayGetter(this).toString();
    }

    private String newsHappen() {
        return studentController.newsHappenLinkGetter(this).toString();
    }

    private String newsForYou(String[] restString) {
        return studentController.getNewsForYouAssignmentNameGetter(this, restString[0]).toString() + "//" + studentController.getStudentChangedAssignmentDeadlineNameGetter(this, restString[0]).toString();
    }

    private String sara(String[] restString) {
        // restString : studentId
        ArrayList<String> bestScoreAndCourse = studentController.getBestScoreAndCourse(this, restString[0]);
        String bestScore = bestScoreAndCourse.getFirst();
        String bestCourse = bestScoreAndCourse.getLast();
        ArrayList<String> worseScoreAndCourse = studentController.getWorseScoreAndCourse(this, restString[0]);
        String worseScore = worseScoreAndCourse.getFirst();
        String worseCourse = worseScoreAndCourse.getLast();
        int examNum = studentController.getAllExamNum(this, restString[0]);
        int assignmentNum = studentController.getAllAssignmentNum(this, restString[0]);
        int doneAssignmentNum = studentController.getDoneAssignmentNum(this, restString[0]);
        ArrayList<Long> dateOfEnd = studentController.getDateOfEndGetter(this, restString[0]);
        long day = dateOfEnd.getFirst();
        long hour = dateOfEnd.get(1);
        long minute = dateOfEnd.getLast();
        Map<String, String> studentJob = studentController.getStudentSaraJobGetter(this, restString[0]);
        ArrayList<String> doneAssignmentName = studentController.getDoneAssignmentNameGetter(this, restString[0]);
        return bestScore + "//" + bestCourse + "//" + worseScore + "//" + worseCourse + "//" + examNum + "//" + assignmentNum + "//" + doneAssignmentNum + "//" + day + "//" + hour + "//" + minute + "//" + studentJob.toString() + "//" + doneAssignmentName.toString();
    }

    private String deleteAccount(String[] restString) {
        // restString: studentID
        return studentController.getDeleteAccount(this, restString[0]);
    }

    private String userInfo(String[] restString) {
        // rest String : studentId
        if (studentController.getNoStudentFoundById(this, restString[0])) {
            return "noStudentFoundInServer";
        }
        String username = studentController.getStudentUsernameByID(this, restString[0]);
        String name = studentController.getStudentNameById(this, restString[0]);
        String studentID = restString[0];
        String termInfo = "بهار 1403-1402";
        int creditNum = studentController.getAllCreditGetter(this, restString[0]);
        if (creditNum == -1) {
            creditNum = 0;
        }
        double allAverage = studentController.getPrintAverage(this, restString[0], "total");
        if (allAverage == -1) {
            allAverage = 0.00;
        }
        return username + "//" + name + "//" + studentID + "//" + termInfo + "//" + creditNum + "//" + allAverage;
    }

    private String changePassword(String[] restString) {
        // restString = studentId//newPassword//oldPassword
        if (studentController.getNoStudentFoundById(this, restString[0])) {
            return "noStudentFoundInServer";
        }
        if (studentController.getPasswordIsWrongForId(this, restString[0], restString[2])) {
            return "passwordIsWrong";
        }
        studentController.getChangePassword(this, restString[0], restString[1]);
        return "successful";
    }

    private String chaneUsername(String[] restString) {
        // restString = lastUsername//newUsername//password
        if (studentController.getNoStudentFoundByUsername(this, restString[0])) {
            return "noStudentFoundInServer";
        }
        if (studentController.getStudentUsernameNotValid(this, restString[1])) {
            return "usernameExist";
        }
        String studentId = studentController.getStudentIdByUsername(this, restString[0]);
        if (studentController.getPasswordIsWrongForId(this, studentId, restString[2])) {
            return "passwordIsWrong";
        }
        studentController.getChangeUsername(this, studentId, restString[1]);
        return "successful";
    }

    private String loginWithId(String[] restString, boolean needCheck) {
        // restString = studentId//password
        if (needCheck && studentController.getNoStudentFoundById(this, restString[0])) {
            return "noStudentFoundInServer";
        }
        if (studentController.getStudentNotSignedUp(this, restString[0])) {
            return "notSignedUp";
        }
        if (studentController.getPasswordIsWrongForId(this, restString[0], restString[1])) {
            return "passwordIsWrong";
        }
        String studentName = studentController.getStudentNameById(this, restString[0]);
        return "successful//" + studentName;
    }

    private String loginWithUsername(String[] restString) {
        // restString = username//password
        if (studentController.getNoStudentFoundByUsername(this, restString[0])) {
            return "noStudentFoundInServer";
        }
        restString[0] = studentController.getStudentIdByUsername(this, restString[0]);
        return loginWithId(restString, false);
    }

    private String signup(String[] restString) {
        // restString = studentId//username//password
        if (studentController.getNoStudentFoundById(this, restString[0])) {
            return "noStudentFoundInServer";
        }
        if (studentController.getStudentAlreadySignedUp(this, restString[0])) {
            return "alreadySignedUp";
        }
        if (studentController.getStudentUsernameNotValid(this, restString[1])) {
            return "usernameExist";
        }
        studentController.getSignupStudent(this, restString[0], restString[1], restString[2]);
        String studentName = studentController.getStudentNameById(this, restString[0]);
        return "successful//" + studentName;
    }


}
