package MVC_PROJECT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdminView {

    AdminController adminController;
    String thisAdminUsername;

    public void run(AdminController adminController) {
        this.adminController = adminController;
        while (true) {
            thisAdminUsername = "";
            boolean Exit = false;
            System.out.println("Are you new admin?\n[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
            Scanner input = new Scanner(System.in);
            int AdminLoginOrSignup;
            try {
                AdminLoginOrSignup = input.nextInt();
            } catch (Exception exception) {
                System.out.println("Invalid input!\n");
                continue;
            }
            switch (AdminLoginOrSignup) {
                case 1:
                    adminSignupProcess();
                    break;
                case 2:
                    adminLoginUsernameGetter();
                    break;
                case 3:
                    Exit = true;
                    break;
                default:
                    System.out.println("Invalid input!\n");
                    continue;
            }
            if (Exit) {
                break;
            }
        }
    }

    private void adminPowers() {
        boolean Exit;
        Scanner input = new Scanner(System.in);
        while (true) {
            Exit = false;
            String ALL_OBLIGATIONS = """
                    What do you want to to?

                    STUDENT:
                    [1]:Signing up student~
                    [2]:Removing student~
                    [3]:Adding a student to a Course~
                    [4]:Removing a student from a course~
                    [5]:Setting Score for a student~
                    [6]:Printing current average of a student~
                    [7]:Printing total average of a student~
                    [8]:Printing courses of a student~
                    [9]:Printing course credit of a student~

                    TEACHER:
                    [10]:Adding teacher~
                    [11]:Removing teacher~

                    COURSE:
                    [12]:Adding a course~
                    [13]:Removing a course~
                    [14]:Set teacher for a course~
                    [15]:Setting exam day for a course~

                    ASSIGNMENT:
                    [16]:Adding assignment~
                    [17]:Removing assignment~
                    [18]:Set assignment for a course~
                    [19]:Remove assignment from a course~
                    [20]:Setting deadline for an assignment~
                    [21]:Activating assignment~
                    [22]:Deactivating assignment~
                    [23]:Set caption for an assignment

                    [24]:Log out!
                    """;
            System.out.println(ALL_OBLIGATIONS.substring(0, ALL_OBLIGATIONS.length() - 1));
            int inOperation;
            String Operation;
            try {
                Operation = input.next();
                inOperation = Integer.parseInt(Operation);
            } catch (Exception e) {
                System.out.println("Invalid input!\n");
                continue;
            }
            input.nextLine();
            switch (inOperation) {
                case 1://Signing up a student
                    getStudentName();
                    break;
                case 2://Removing Student
                    removeStudent();
                    break;
                case 3://Set Course for Student
                    if (getStudentId("set")) {
                        System.out.println("Course has been set successfully!\n");
                    }
                    break;
                case 4://remove Student from a course
                    if (getStudentId("remove")) {
                        System.out.println("Student has been removed successfully!\n");
                    }
                    break;
                case 5://set score for student
                    if (getStudentId("setScore")) {
                        System.out.println("Score has been set successfully!\n");
                    }
                    break;
                case 6://printing Current Average
                    printAverage("current");
                    break;
                case 7://printing total average
                    printAverage("total");
                    break;
                case 8://printing course
                    studentIdGetterPrintCourse();
                    break;
                case 9://print course credit
                    studentIdGetterPrintCredit();
                    break;
                case 10://Adding teacher
                    teacherNameGetter();
                    break;
                case 11://removing teacher
                    removeTeacher();
                    break;
                case 12://add course
                    if (getCourseName()) {
                        System.out.println("Course has been created!\n");
                    } else {
                        System.out.println("No course create!\n");
                    }
                    break;
                case 13://removing a course
                    removeCourse();
                    break;
                case 14://set teacher for a course
                    setTeacherForCourse();
                    break;
                case 15://set exam date
                    courseIdGetterExam();
                    break;
                case 16://add assignment
                    assignmentNameGetter();
                    break;
                case 17://remove assignment
                    assignmentIdGetterRemoveAssignment();
                    break;
                case 18://set assignment for a course
                    assignmentIdGetterSetCourse();
                    break;
                case 19://remove assignment from course
                    assignmentIdGetterRemoveFromCourse();
                    break;
                case 20://set deadline for assignment
                    assignmentIdGetterSetDeadline();
                    break;
                case 21://activating assignment
                    assignmentIdGetterSetActivity("activate");
                    break;
                case 22://deactivating assignment
                    assignmentIdGetterSetActivity("deactivate");
                    break;
                case 23://Set caption for assignment
                    getAssignmentId();
                    break;
                case 24://
                    Exit = true;
                    break;
                default:
                    System.out.println("Invalid input!\n");
                    break;
            }
            if (Exit) {
                break;
            }
        }
    }

    private void assignmentIdGetterRemoveFromCourse() {
        while (true) {
            System.out.println("Enter ID of assignment\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (adminController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment with ID " + assignmentId + " found!\n");
                continue;
            }
            if (courseIdGetterRemoveAssignmentFromCourse(assignmentId)) {
                break;
            }
        }
    }

    private boolean courseIdGetterRemoveAssignmentFromCourse(String assignmentId) {
        while (true) {
            System.out.println("Enter course ID you want to remove assignment\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")) {
                return false;
            }
            if (adminController.getNoCourseFoundById(this, courseId)) {
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            String assignmentName = adminController.getAssignmentNameById(this, assignmentId);
            String courseName = adminController.getCourseNameById(this, courseId);
            boolean hasAssignment = adminController.getRemoveAssignmentFromCourse(this, assignmentId, courseId);
            if (hasAssignment){
                System.out.println("Assignment name " + assignmentName + " successfully removed from course " + courseName + "!\n");
                return true;
            }else {
                System.out.println("Course name " + courseName + " doesn't have assignment name " + assignmentName + "\n");
            }
        }
    }

    private void assignmentIdGetterRemoveAssignment() {
        while (true) {
            System.out.println("Enter assignment ID you want to remove\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (adminController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found with id " + assignmentId + "\n");
                continue;
            }
            String assignmentName = adminController.getAssignmentNameById(this, assignmentId);
            System.out.println("Are you sure you want to remove assignment " + assignmentName + " with ID " + assignmentId + " ?\n[1]:Continue\n[2]:Exit");
            String lastChance = input.next();
            if (lastChance.equals("2")) {
                break;
            } else if (lastChance.equals("1")) {
                adminController.getRemoveAssignment(this, assignmentId);
                System.out.println("Assignment name " + assignmentName + " with ID " + assignmentId + " has successfully removed\n");
                break;
            } else {
                System.out.println("Invalid input!\n");
            }
        }
    }

    private void getAssignmentId() {
        while (true) {
            System.out.println("Enter assignment ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (adminController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("There is no assignment with ID " + assignmentId + "\n");
                continue;
            }
            if (setAssignmentCaption(assignmentId)) {
                break;
            }
        }
    }

    private boolean setAssignmentCaption(String assignmentId) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your caption --> (press Enter twice to end)\n[1]:Go back");
        StringBuilder paragraphBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).isEmpty()) {
            paragraphBuilder.append(line).append("\n");
        }
        String paragraph = paragraphBuilder.toString();
        if (paragraph.equals("1")) {
            return false;
        }
        adminController.getSetCaptionForAssignment(this, assignmentId, paragraph);
        System.out.println("The caption has been set successfully!\n");
        return true;
    }


    private void assignmentIdGetterSetActivity(String obligation) {
        while (true) {
            System.out.println("Enter assignment ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (adminController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found by ID " + assignmentId);
                continue;
            }
            adminController.getSetAssignmentActivity(this, assignmentId, obligation);
            if (obligation.equals("activate")) {
                System.out.println("Assignment with ID " + assignmentId + " is activated!\n");
            } else {
                System.out.println("Assignment with ID " + assignmentId + " is deactivated!\n");
            }
            break;
        }
    }

    private void assignmentIdGetterSetDeadline() {
        while (true) {
            System.out.println("Enter assignment ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (adminController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found by ID " + assignmentId + "\n");
                continue;
            }
            if (assignmentDateOfDeadlineGetter(assignmentId)) {
                break;
            }
        }
    }

    private boolean assignmentDateOfDeadlineGetter(String assignmentId) {
        while (true) {
            System.out.println("Enter date of deadline in pattern YYYY/MM/DD\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String dateOfDeadline = input.next();
            if (dateOfDeadline.equals("1")) {
                return false;
            }
            if (adminController.getDateHasValidPattern(this, dateOfDeadline)) {
                if (assignmentHourOfDeadlineGetter(assignmentId, dateOfDeadline)) {
                    return true;
                }
            } else {
                System.out.println("The date doesn't have correct pattern!\n");
            }
        }
    }

    private boolean assignmentHourOfDeadlineGetter(String assignmentId, String dateOfDeadline) {
        while (true) {
            System.out.println("Enter hour of deadline in pattern HH:MM\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String hourOfDeadline = input.next();
            if (hourOfDeadline.equals("1")) {
                return false;
            }
            if (adminController.getHourHasValidPattern(this, hourOfDeadline)) {
                adminController.getSetDeadline(this, assignmentId, dateOfDeadline, hourOfDeadline);
                System.out.println("Deadline set successfully!\n");
                return true;
            } else {
                System.out.println("The hour doesn't have correct pattern!\n");
            }
        }
    }

    private void assignmentIdGetterSetCourse() {
        while (true) {
            System.out.println("Enter assignment ID you want to set\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (adminController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found with id " + assignmentId + "\n");
                continue;
            }
            if (courseIdGetterSetAssignment(assignmentId)) {
                break;
            }
        }
    }

    private boolean courseIdGetterSetAssignment(String assignmentId) {
        while (true) {
            System.out.println("Enter course ID you want to set assignment for\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")) {
                return false;
            }
            if (adminController.getNoCourseFoundById(this, courseId)) {
                System.out.println("No course found with ID " + courseId + "\n");
                continue;
            }
            adminController.getSetAssignmentForCourse(this, assignmentId, courseId);
            System.out.println("Assignment set successfully!\n");
            return true;
        }
    }

    private void assignmentNameGetter() {
        while (true) {
            System.out.println("Enter assignment name\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentName = input.nextLine();
            if (assignmentName.equals("1")) {
                break;
            } else {
                if (assignmentIsActiveGetter(assignmentName)) {
                    break;
                }
            }
        }
    }

    private boolean assignmentIsActiveGetter(String assignmentName) {
        while (true) {
            System.out.println("Is this assignment active?\n[0]:No\n[1]:Yes\n[2]:Go back");
            Scanner input = new Scanner(System.in);
            String isActive = input.next();
            switch (isActive) {
                case "2" -> {
                    return false;
                }
                case "1" -> isActive = "true";
                case "0" -> isActive = "false";
                default -> {
                    System.out.println("Invalid input!\n");
                    continue;
                }
            }
            if (assignmentDateOfDeadlineGetter(assignmentName, isActive)) {
                return true;
            }
        }
    }

    private boolean assignmentDateOfDeadlineGetter(String assignmentName, String isActive) {
        while (true) {
            System.out.println("Enter date of deadline in pattern YYYY/MM/DD\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String dateOfDeadline = input.next();
            if (dateOfDeadline.equals("1")) {
                return false;
            }
            if (adminController.getDateHasValidPattern(this, dateOfDeadline)) {
                if (assignmentHourOfDeadlineGetter(assignmentName, isActive, dateOfDeadline)) {
                    return true;
                }
            } else {
                System.out.println("The date doesn't have correct pattern!\n");
            }
        }
    }

    private boolean assignmentHourOfDeadlineGetter(String assignmentName, String isActive, String dateOfDeadline) {
        while (true) {
            System.out.println("Enter hour of deadline in pattern HH:MM\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String hourOfDeadline = input.next();
            if (hourOfDeadline.equals("1")) {
                return false;
            }
            if (adminController.getHourHasValidPattern(this, hourOfDeadline)) {
                if (estimateTimeGetter(assignmentName, isActive, dateOfDeadline, hourOfDeadline)){
                    return true;
                }else {
                    continue;
                }
            } else {
                System.out.println("The hour doesn't have correct pattern!\n");
            }
        }
    }

    private boolean estimateTimeGetter(String assignmentName, String isActive, String dateOfDeadline, String hourOfDeadline){
        while (true){
            System.out.println("Enter a double for estimate time\n[-1]:Go back");
            Scanner input = new Scanner(System.in);
            double estimateTime = 0;
            try {
                estimateTime = input.nextDouble();
            }catch (Exception e){
                System.out.println("Invalid input!\n");
                continue;
            }
            if (estimateTime == -1){
                return false;
            }
            if (estimateTime < 0){
                System.out.println("Invalid input!\n");
                continue;
            }
            String assignmentId = adminController.getAddAssignment(this, assignmentName, isActive, dateOfDeadline, hourOfDeadline, thisAdminUsername);
            adminController.getEstimateTimeSetter(this, assignmentId, this.thisAdminUsername, estimateTime);
            System.out.println("Assignment add successfully!\n");
            return true;
        }
    }

    private void courseIdGetterExam() {
        while (true) {
            System.out.println("Enter course ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")) {
                break;
            }
            if (adminController.getNoCourseFoundById(this, courseId)) {
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            if (getExamDate(courseId)) {
                break;
            }
        }
    }

    private boolean getExamDate(String courseId) {
        while (true) {
            System.out.println("Enter exam date in pattern YYYY/MM/DD\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String examDate = input.next();
            if (examDate.equals("1")) {
                return false;
            }

            if (adminController.getDateHasValidPattern(this, examDate)) {
                if (getExamHour(courseId, examDate)) {
                    return true;
                }
            } else {
                System.out.println("The input does not match the yyyy/mm/dd format!\n");
            }
        }
    }

    private boolean getExamHour(String courseId, String examDate) {
        while (true) {
            System.out.println("Enter exam hour in format HH:MM\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String examHour = input.next();
            if (examHour.equals("1")) {
                return false;
            }

            if (adminController.getHourHasValidPattern(this, examHour)) {
                adminController.getSetExamDate(this, courseId, examDate, examHour);
                System.out.println("Exam has set successfully!\n");
                return true;
            } else {
                System.out.println("The input does not match the hh:mm format!\n");
            }
        }
    }

    private void studentIdGetterPrintCredit() {
        while (true) {
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")) {
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)) {
                System.out.println("There is no student with ID " + studentId + "\n");
                continue;
            }
            if (adminController.getPrintAllCredit(this, studentId) == -1) {
                System.out.println("Something went wrong!\n");
                continue;
            } else {
                System.out.println("This student has " + adminController.getPrintAllCredit(this, studentId) + " credit!\n");
            }
            break;
        }
    }

    private void studentIdGetterPrintCourse() {
        while (true) {
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")) {
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)) {
                System.out.println("There is no student with ID " + studentId);
                continue;
            }
            String courses = adminController.getPrintAllCourse(this, studentId);
            if (courses.equals("null")) {
                System.out.println("This student has no course!\n");
            } else {
                System.out.println(courses + "\n");
            }
            break;
        }
    }

    private void printAverage(String kind) {
        String studentId;
        while (true) {
            System.out.println("Enter student ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            studentId = input.next();
            if (studentId.equals("1")) {
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)) {
                System.out.println("There is no student with ID " + studentId + "\n");
                continue;
            }
            double average = adminController.getPrintAverage(this, studentId, kind);
            if (average == -1) {
                System.out.println("This student has no course!\n");
                continue;
            }
            System.out.println(average + "\n");
            break;
        }
    }

    private boolean getStudentId(String obligation) {
        while (true) {
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")) {
                return false;
            }
            if (adminController.getNoStudentFoundById(this, studentId)) {
                System.out.println("No student with ID " + studentId + " found\n");
                continue;
            }
            if (getCourseIdSetStudentScore(studentId, obligation)) {
                return true;
            }
        }
    }

    private boolean getCourseIdSetStudentScore(String studentId, String obligation) {
        while (true) {
            System.out.println("Enter course ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")) {
                return false;
            }
            if (adminController.getNoCourseFoundById(this, courseId)) {
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            switch (obligation) {
                case "set" -> {
                    if (!adminController.getStudentHasCourse(this, studentId, courseId)) {
                        setStudentCourse(studentId, courseId);
                    } else {
                        System.out.println("Student already has this course!\n");
                        return false;
                    }
                }
                case "remove" -> {
                    if (adminController.getStudentHasCourse(this, studentId, courseId)) {
                        removeStudentCourse(studentId, courseId);
                    } else {
                        System.out.println("Student does not have this course!\n");
                        return false;
                    }
                }
                case "setScore" -> {
                    if (adminController.getStudentHasCourse(this, studentId, courseId)) {
                        if (!setStudentScore(studentId, courseId)) {
                            continue;
                        }
                    } else {
                        System.out.println("Student does not have this course!\n");
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private void setStudentCourse(String studentId, String courseId) {
        adminController.getSetStudentCourse(this, studentId, courseId);
    }

    private void removeStudentCourse(String studentId, String courseId) {
        adminController.getRemoveStudentCourse(this, studentId, courseId);
    }

    private boolean setStudentScore(String studentId, String courseId) {
        while (true) {
            System.out.println("Enter Score(In double form --> 00.00)\n[-1]:Go back");
            Scanner input = new Scanner(System.in);
            double score;
            try {
                score = input.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input!\n");
                continue;
            }
            if (score == -1) {
                return false;
            }
            if (score < 00.00 || score > 20.00) {
                System.out.println("this score is not in true range!\nEnter a number between 0 and 20\n");
                continue;
            }
            adminController.getSetStudentScore(this, studentId, courseId, score);
            return true;
        }
    }

    private void removeCourse() {
        while (true) {
            System.out.println("Enter the ID of course you want to remove:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")) {
                break;
            }
            if (adminController.getNoCourseFoundById(this, courseId)) {
                System.out.println("No course found with ID " + courseId);
                continue;
            }
            String courseName = adminController.getCourseNameById(this, courseId);
            System.out.println("Are you sure you want to remove course " + courseName + " with ID " + courseId + " ?\n[1]:Continue\n[2]:Go back");
            String lastChance = input.next();
            if (lastChance.equals("2")) {
                break;
            } else if (lastChance.equals("1")) {
                adminController.getRemoveCourse(this, courseId);
                System.out.println("Course name " + courseName + " with ID " + courseId + " has successfully removed\n");
                break;
            } else {
                System.out.println("Invalid input!\n");
            }
        }
    }

    private void setTeacherForCourse() {
        while (true) {
            String courseId = getCourseId();
            if (courseId == null) {
                break;
            }
            if (adminController.getCourseIdChecker(this, courseId)) {
                if (setCourseTeacher(courseId)) {
                    break;
                }
            } else {
                System.out.println("No course with ID: " + courseId);
            }
        }
    }

    private boolean setCourseTeacher(String courseId) {
        while (true) {
            System.out.println("Enter teacher ID:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String teacherId = input.next();
            if (teacherId.equals("1")) {
                return false;
            }
            if (adminController.getNoTeacherFoundById(this, teacherId)) {
                System.out.println("No teacher with ID " + teacherId + " found!\n");
                continue;
            } else {
                adminController.getSetCourseTeacher(this, courseId, teacherId);
                System.out.println("teacher with username: " + teacherId + " successfully been set as " + courseId + " teacher!\n");
            }
            return true;
        }
    }

    private String getCourseId() {
        System.out.println("Enter course ID:\n[1]:go back");
        Scanner input = new Scanner(System.in);
        String courseId = input.next();
        if (courseId.equals("1")) {
            return null;
        }
        return courseId;
    }

    private boolean getCourseName() {
        while (true) {
            System.out.println("Enter name of course you want to add:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseName = input.next();
            if (courseName.equals("1")) {
                return false;
            }
            if (getCourseCredit(courseName)) {
                return true;
            }
        }
    }

    private boolean getCourseCredit(String courseName) {
        while (true) {
            System.out.println("What is this course credit:\n[-1]:Go back");
            Scanner input = new Scanner(System.in);
            int creditNum;
            try {
                creditNum = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input!\n");
                continue;
            }
            if (creditNum == -1) {
                return false;
            }
            String courseId = adminController.getAddCourse(this, courseName, creditNum);
            if (!getDaysNumForCourse(courseId)){
                adminController.getRemoveCourse(this, courseId);
                continue;
            }
            return true;
        }
    }

    public boolean getDaysNumForCourse(String courseId){
        while (true){
            System.out.println("Enter an integer for how many classes are going to happen in a week\n[0]:Go back");
            Scanner input = new Scanner(System.in);
            String dayNum = input.next();
            if (dayNum.equals("0")){
                return false;
            }
            int num;
            try {
                num = Integer.parseInt(dayNum);
            }catch (Exception e){
                System.out.println("Invalid input!\n");
                continue;
            }
            if (getTimeOfCourse(num, courseId)){
                return true;
            }
        }
    }

    public boolean getTimeOfCourse(int num, String courseId){
        ArrayList<ArrayList<String>> arrayList = new ArrayList<>();
        int i = 0;
        while (i < num){
            System.out.println("Enter " + (i+1) + " time of class:\nPattern must be like: TEXT=HH:MM\nTEXT is one of ( \"sh\", \"y\", \"d\", \"se\", \"c\", \"p\", \"j\" ) for every day of week\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseTime = input.next();
            if (courseTime.equals("1")){
                return false;
            }
            if (!adminController.getCourseDatePatternChecker(this, courseTime)){
                System.out.println("Invalid input!\n");
                continue;
            }
            String[] Info = courseTime.split("=");
            ArrayList<String> temp = new ArrayList<>();
            temp.add(Info[0]);
            temp.add(Info[1]);
            arrayList.add(temp);
            i++;
        }
        adminController.getSetDateForCourse(this, courseId, arrayList);
        return true;
    }

    private void adminLoginUsernameGetter() {
        while (true) {
            System.out.println("Enter your username:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String username = input.next();
            if (username.equals("1")) {
                break;
            }
            if (!adminController.getAdminUsernameNotAvailable(this, username)) {//username has not been initialized
                System.out.println("Username " + username + " is not signed up!\n");
                continue;
            }
            if (adminLoginPasswordGetter(username)) {
                break;
            }
        }
    }

    private boolean adminLoginPasswordGetter(String username) {
        while (true) {
            System.out.println("Enter your password:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String password = input.next();
            if (password.equals("1")) {
                return false;
            }
            if (adminController.getAdminPasswordIsWrong(this, username, password)) {
                System.out.println("Your password is wrong!\n");
                continue;
            }
            System.out.println("You successfully logged in!\n");
            thisAdminUsername = username;
            adminPowers();
            return true;
        }
    }

    private void removeTeacher() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the ID of teacher you want to remove:\n[1]:Go back");
            String removingTeacherId = input.next();
            if (removingTeacherId.equals("1")) {
                break;
            }
            if (adminController.getNoTeacherFoundById(this, removingTeacherId)) {
                System.out.println("There is no teacher with ID " + removingTeacherId + "\n");
                continue;
            } else {
                int lastRemoveChoice;
                while (true) {
                    System.out.println("Are you sure you want to remove teacher with ID " + removingTeacherId + " ? \n[1]:Continue\n[2]:Go back");
                    try {
                        lastRemoveChoice = input.nextInt();
                    } catch (Exception exception) {
                        System.out.println("Invalid input!\n");
                        continue;
                    }
                    break;
                }
                switch (lastRemoveChoice) {
                    case 1:
                        System.out.println("Removing teacher...\n");
                        adminController.getTeacherAccountRemover(this, removingTeacherId);
                        System.out.println("Teacher with ID " + removingTeacherId + " has successfully removed!\n");
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid input\n");
                }
            }
            break;
        }
    }

    private void teacherNameGetter() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter teacher name:\n[1]:Go back");
            String teacherName = input.next();
            if (teacherName.equals("1")) {
                break;
            }
            if (teacherUsernameGetter(teacherName)) {
                break;
            }
        }
    }

    private boolean teacherUsernameGetter(String teacherName) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter teacher username:\n[1]:Go back");
            String teacherUsername = input.next();
            if (teacherUsername.equals("1")) {
                return false;
            }
            if (adminController.getTeacherUsernameValidation(this, teacherUsername)) {
                System.out.println("This username is already exist!\n");
                continue;
            }
            if (teacherPasswordGetter(teacherName, teacherUsername)) {
                return true;
            }
        }
    }

    private boolean teacherPasswordGetter(String teacherName, String teacherUsername) {
        Scanner input = new Scanner(System.in);
        String teacherPassword;
        while (true) {//Password
            System.out.println("Enter teacher password:\n[1]:Go back");
            teacherPassword = input.next();
            if (teacherPassword.equals("1")) {
                return false;
            }
            if (adminController.getPasswordNotValidate(this, teacherPassword, teacherUsername)) {
                System.out.println("The password does not follow the correct pattern\n1:The password should not contains username\n2:The password should be at least 8 character\n3:The password must contain at least one lowercase letter, one uppercase letter and one number\n");
                continue;
            }
            if (!adminController.getTeacherSignup(this, teacherName, teacherUsername, teacherPassword)) {
                System.out.println("Something went wrong!\n");
            } else {
                System.out.println("Signup successfully!\n");
                return true;
            }
        }
    }

    private void removeStudent() {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the ID of student you want to remove:\n[1]:Go back");
            String studentId = input.next();
            if (studentId.equals("1")) {
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)) {
                System.out.println("There is no student with ID " + studentId);
                continue;
            } else {
                int lastRemoveChoice;
                while (true) {
                    System.out.println("Are you sure you want to remove student with ID " + studentId + " ? \n[1]:Continue\n[2]:Go back");
                    try {
                        lastRemoveChoice = input.nextInt();
                    } catch (Exception exception) {
                        System.out.println("Invalid input!\n");
                        continue;
                    }
                    break;
                }
                switch (lastRemoveChoice) {
                    case 1:
                        System.out.println("Removing Student...\n");
                        adminController.getStudentAccountRemover(this, studentId);
                        System.out.println("Student named: " + studentId + " has successfully removed!\n");
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Invalid input\n");
                }
            }
            break;
        }
    }

    private void getStudentName() {
        String studentName;
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter student name:\n[1]:Go back");
            studentName = input.nextLine();
            if (studentName.equals("1")) {
                return;
            }
            if (!adminController.getNoStudentFound(this, studentName)) {
                System.out.println("Student named: " + studentName + " is already signed up!\n[1]:Go back\n[2]:Signup anyway");
                String choice = input.next();
                if (choice.equals("1")) {
                    continue;
                } else if (choice.equals("2")) {
                    signUpStudent(studentName);
                    break;
                } else {
                    System.out.println("Invalid input!\n");
                }
                continue;
            }
            signUpStudent(studentName);
            break;
        }
    }

    private void signUpStudent(String StudentName) {
        String birthday = studentBirthdayGetter();
        if (birthday.equals("exit")){
            System.out.println("no student generate!\n");
            return;
        }
        String studentId = this.adminController.getStudentSignUp(this, StudentName, "$", "$");
        adminController.getSaveStudentBirthday(this, birthday, studentId);
        System.out.println("Student named: " + StudentName + " successfully signed up!\n");
    }

    private String studentBirthdayGetter(){
        while (true){
            System.out.println("Enter student birthday in pattern like (YYYY/MM/DD)\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String birthday = input.next();
            if (birthday.equals("1")){
                return "exit";
            }
            if (adminController.getDateHasValidPattern(this, birthday)){
                return birthday;
            }else {
                System.out.println("date " + birthday + " doesn't have true pattern!");
//                continue;
            }
        }
    }

    private void adminSignupProcess() {
        while (true) {
            String ValidationCode = "SBU_ADMIN";
            System.out.println("Enter your SBU validation code:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            if (choice.equals("1")) {
                break;
            } else if (choice.equals(ValidationCode)) {
                if (adminSignupUsernameGetter()) {
                    break;
                }
            } else {
                System.out.println("Unable to validate your information!\n");
            }
        }
    }

    private boolean adminSignupUsernameGetter() {
        while (true) {
            System.out.println("Enter your username\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String username = input.next();
            if (username.equals("1")) {
                return false;
            }
            if (adminController.getAdminUsernameNotAvailable(this, username)) {
                System.out.println("Username " + username + " is already token!\n");
                continue;
            }
            if (adminSignupPasswordGetter(username)) {
                return true;
            }
        }
    }

    private boolean adminSignupPasswordGetter(String username) {
        while (true) {
            System.out.println("Enter your password\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String password = input.next();
            if (password.equals("1")) {
                return false;
            }
            if (adminController.getPasswordNotValidate(this, password, username)) {
                System.out.println("The password does not follow the correct pattern\n1:The password should not contains username\n2:The password should be at least 8 character\n3:The password must contain at least one lowercase letter, one uppercase letter and one number\n");
                continue;
            }
            if (adminController.getSaveAdmin(this, username, password)) {
                System.out.println("Signup successfully!\n");
                thisAdminUsername = username;
                adminPowers();
            } else {
                System.out.println("Something went wrong!\n");
            }
        }
    }


}
