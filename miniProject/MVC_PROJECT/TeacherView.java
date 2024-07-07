package MVC_PROJECT;
import java.util.Scanner;

public class TeacherView {

    TeacherController teacherController;
    String thisTeacherId;

    public void run(TeacherController teacherController) {
        this.teacherController = teacherController;
        Scanner input = new Scanner(System.in);
        boolean Exit = false;
        do {
            thisTeacherId = null;
            System.out.println("Are you new teacher?\n[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
            String task = input.next();
            switch (task) {
                case "1":
                    teacherNameGetter();
                    break;
                case "2":
                    teacherLoginUsernameGetter();
                    break;
                case "3":
                    Exit = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        } while (!Exit);
    }

    private void teacherNameGetter() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your name:\n[1]:Go back");
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
            System.out.println("Enter your username:\n[1]:Go back");
            String teacherUsername = input.next();
            if (teacherUsername.equals("1")) {
                return false;
            }
            if (teacherController.getTeacherUsernameValidation(this, teacherUsername)) {
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
            System.out.println("Enter your password:\n[1]:Go back");
            teacherPassword = input.next();
            if (teacherPassword.equals("1")) {
                return false;
            }
            if (teacherController.getPasswordNotValidate(this, teacherPassword, teacherUsername)) {
                System.out.println("The password does not follow the correct pattern\n1:The password should not contains username\n2:The password should be at least 8 character\n3:The password must contain at least one lowercase letter, one uppercase letter and one number\n");
                continue;
            }
            if (!teacherController.getTeacherSignup(this, teacherName, teacherUsername, teacherPassword)) {
                System.out.println("Something went wrong!\n");
            } else {
                System.out.println("Signup successfully!\n");
                thisTeacherId = teacherController.getThisTeacherIdGetter(this, teacherUsername);
                teacherPowers();
                return true;
            }
        }
    }

    private void teacherLoginUsernameGetter() {
        while (true) {
            System.out.println("Enter your username:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String username = input.next();
            if (username.equals("1")) {
                break;
            }
            if (!teacherController.getTeacherUsernameNotAvailable(this, username)) {//username has not been initialized
                System.out.println("Username " + username + " is not signed up!\n");
                continue;
            }
            if (teacherLoginPasswordGetter(username)) {
                break;
            }
        }
    }

    private boolean teacherLoginPasswordGetter(String username) {
        while (true) {
            System.out.println("Enter your password:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String password = input.next();
            if (password.equals("1")) {
                return false;
            }
            if (teacherController.getTeacherPasswordIsWrong(this, username, password)) {
                System.out.println("Your password is wrong!\n");
                continue;
            }
            System.out.println("You successfully logged in!\n");
            thisTeacherId = teacherController.getThisTeacherIdGetter(this, username);
            teacherPowers();
            return true;
        }
    }

    private void teacherPowers() {
        boolean Exit;
        Scanner input = new Scanner(System.in);
        while (true) {
            Exit = false;
            String ALL_OBLIGATIONS = """
                    What do you want to to?
                    
                    STUDENT:
                    [1]:Setting Score for a student~
                   
                    COURSE:
                    [2]:Setting exam day for a course~
                    
                    ASSIGNMENT:
                    [3]:Adding assignment~
                    [4]:Removing assignment~
                    [5]:Set assignment for a course~
                    [6]:Remove assignment from a course~
                    [7]:Setting deadline for an assignment~
                    [8]:Activating assignment~
                    [9]:Deactivating assignment~
                    [10]:Set caption for an assignment~
                    
                    [11]:Log out!
                    """;
            System.out.println(ALL_OBLIGATIONS.substring(0, ALL_OBLIGATIONS.length() - 1));
            int operation;
            try {
                operation = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input!\n");
                continue;
            }
            switch (operation) {
                case 1://Setting score for a student
                    if (getStudentId()) {
                        System.out.println("Score has been set successfully!\n");
                    }
                    break;
                case 2://setting exam
                    courseIdGetterExam();
                    break;
                case 3://add assignment
                    assignmentNameGetter();
                    break;
                case 4://remove assignment
                    assignmentIdGetterRemoveAssignment();
                    break;
                case 5://set Assignment for course
                    assignmentIdGetterSetCourse();
                    break;
                case 6://remove assignment from course
                    assignmentIdGetterRemoveFromCourse();
                    break;
                case 7://set deadline for assignment
                    assignmentIdGetterSetDeadline();
                    break;
                case 8://activate assignment
                    assignmentIdGetterSetActivity("activate");
                    break;
                case 9://deactivate assignment
                    assignmentIdGetterSetActivity("deactivate");
                    break;
                case 10://set caption for assignment
                    getAssignmentId();
                    break;
                case 11://
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

    private void getAssignmentId() {
        while (true) {
            System.out.println("Enter assignment ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentId = input.next();
            if (assignmentId.equals("1")) {
                break;
            }
            if (teacherController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("There is no assignment with ID " + assignmentId + "\n");
                continue;
            }
            if (teacherController.getAssignmentIsNotForTeacher(this, assignmentId, thisTeacherId)) {
                System.out.println("Sorry you didn't write assignment with ID " + assignmentId + "\n");
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
        teacherController.getSetCaptionForAssignment(this, assignmentId, paragraph);
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
            if (teacherController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found by ID " + assignmentId);
                continue;
            }
            if (teacherController.getAssignmentIsNotForTeacher(this, assignmentId, thisTeacherId)) {
                System.out.println("Sorry you didn't write assignment with ID " + assignmentId + "\n");
                continue;
            }
            teacherController.getSetAssignmentActivity(this, assignmentId, obligation);
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
            if (teacherController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found by ID " + assignmentId + "\n");
                continue;
            }
            if (teacherController.getAssignmentIsNotForTeacher(this, assignmentId, thisTeacherId)) {
                System.out.println("Sorry you didn't write assignment with ID " + assignmentId + "\n");
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
            if (teacherController.getDateHasValidPattern(this, dateOfDeadline)) {
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
            if (teacherController.getHourHasValidPattern(this, hourOfDeadline)) {
                teacherController.getSetDeadline(this, assignmentId, dateOfDeadline, hourOfDeadline);
                System.out.println("Deadline set successfully!\n");
                return true;
            } else {
                System.out.println("The hour doesn't have correct pattern!\n");
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
            if (teacherController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment with ID " + assignmentId + " found!\n");
                continue;
            }
            if (teacherController.getAssignmentIsNotForTeacher(this, assignmentId, thisTeacherId)) {
                System.out.println("Sorry you didn't write assignment with ID " + assignmentId + "\n");
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
            if (teacherController.getNoCourseFoundById(this, courseId)) {
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            if (teacherController.getCourseIsNotForTeacher(this, courseId, thisTeacherId)) {
                System.out.println("Sorry this is not your course!\n");
                continue;
            }
            String assignmentName = teacherController.getAssignmentNameById(this, assignmentId);
            String courseName = teacherController.getCourseNameById(this, courseId);
            boolean hasAssignment = teacherController.getRemoveAssignmentFromCourse(this, assignmentId, courseId);
            if (hasAssignment) {
                System.out.println("Assignment name " + assignmentName + " successfully removed from course " + courseName + "!\n");
                return true;
            } else {
                System.out.println("Course name " + courseName + " doesn't have assignment name " + assignmentName + "\n");
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
            if (teacherController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found with id " + assignmentId + "\n");
                continue;
            }
            if (teacherController.getAssignmentIsNotForTeacher(this, assignmentId, thisTeacherId)) {
                System.out.println("Sorry you didn't write assignment with ID " + assignmentId + "\n");
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
            if (teacherController.getNoCourseFoundById(this, courseId)) {
                System.out.println("No course found with ID " + courseId + "\n");
                continue;
            }
            if (teacherController.getCourseIsNotForTeacher(this, courseId, thisTeacherId)) {
                System.out.println("Sorry this is not your course!\n");
                continue;
            }
            teacherController.getSetAssignmentForCourse(this, assignmentId, courseId);
            System.out.println("Assignment set successfully!\n");
            return true;
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
            if (teacherController.getNoAssignmentFoundById(this, assignmentId)) {
                System.out.println("No assignment found with id " + assignmentId + "\n");
                continue;
            }
            if (teacherController.getAssignmentIsNotForTeacher(this, assignmentId, thisTeacherId)) {
                System.out.println("Sorry you didn't write assignment with ID " + assignmentId + "\n");
                continue;
            }
            String assignmentName = teacherController.getAssignmentNameById(this, assignmentId);
            System.out.println("Are you sure you want to remove assignment " + assignmentName + " with ID " + assignmentId + " ?\n[1]:Continue\n[2]:Exit");
            String lastChance = input.next();
            if (lastChance.equals("2")) {
                break;
            } else if (lastChance.equals("1")) {
                teacherController.getRemoveAssignment(this, assignmentId);
                System.out.println("Assignment name " + assignmentName + " with ID " + assignmentId + " has successfully removed\n");
                break;
            } else {
                System.out.println("Invalid input!\n");
            }
        }
    }

    private void assignmentNameGetter() {
        while (true) {
            System.out.println("Enter assignment name\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String assignmentName = input.next();
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
            if (teacherController.getDateHasValidPattern(this, dateOfDeadline)) {
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
            if (teacherController.getHourHasValidPattern(this, hourOfDeadline)) {
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
            String assignmentId = teacherController.getAddAssignment(this, assignmentName, isActive, dateOfDeadline, hourOfDeadline, thisTeacherId);
            teacherController.getEstimateTimeSetter(this, assignmentId, thisTeacherId, estimateTime);
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
            if (teacherController.getNoCourseFoundById(this, courseId)) {
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            if (teacherController.getCourseIsNotForTeacher(this, courseId, thisTeacherId)) {
                System.out.println("Sorry this is not your course!\n");
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

            if (teacherController.getDateHasValidPattern(this, examDate)) {
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

            if (teacherController.getHourHasValidPattern(this, examHour)) {
                teacherController.getSetExamDate(this, courseId, examDate, examHour);
                System.out.println("Exam has set successfully!\n");
                return true;
            } else {
                System.out.println("The input does not match the hh:mm format!\n");
            }
        }
    }


    private boolean getStudentId() {
        while (true) {
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")) {
                return false;
            }
            if (teacherController.getNoStudentFoundById(this, studentId)) {
                System.out.println("No student with ID " + studentId + " found\n");
                continue;
            }
            if (getCourseIdSetStudentScore(studentId)) {
                return true;
            }
        }
    }

    private boolean getCourseIdSetStudentScore(String studentId) {
        while (true) {
            System.out.println("Enter course ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")) {
                return false;
            }
            if (teacherController.getNoCourseFoundById(this, courseId)) {
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            if (teacherController.getCourseIsNotForTeacher(this, courseId, thisTeacherId)) {
                System.out.println("Sorry this is not your course!\n");
                continue;
            }
            if (teacherController.getStudentHasCourse(this, studentId, courseId)) {
                if (!setStudentScore(studentId, courseId)) {
                    continue;
                }
            } else {
                System.out.println("Student does not have this course!\n");
                return false;
            }
            return true;
        }
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
            teacherController.getSetStudentScore(this, studentId, courseId, score);
            return true;
        }
    }


}
