package AmirhosseinObligationToFuckingMVCPrograming;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminView {

    AdminController adminController;

    public void run(AdminController adminController){
        this.adminController = adminController;
        while (true){
            boolean Exit = false;
            System.out.println("Are you new admin?\n[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
            Scanner input = new Scanner(System.in);
            int AdminLoginOrSignup = 0;
            try {
                AdminLoginOrSignup = input.nextInt();
            }catch (Exception exception){
                System.out.println("Invalid input!\n");
                continue;
            }
            switch (AdminLoginOrSignup){
                case 1:
                    adminSignUpValidation();
                    Exit = true;
                    break;
                case 2:
                    adminLogin();
                    Exit  =true;
                    break;
                case 3:
                    Exit = true;
                    break;
                default:
                    System.out.println("Invalid input!\n");
            }
            if (Exit){
                break;
            }
            input.close();
        }
    }

    private void adminPowers(){
        boolean Exit;
        Scanner input = new Scanner(System.in);
        while (true){
            Exit = false;
            String ALL_OBLIGATIONS = """
                                            What do you want to to?
                                            
                                            STUDENT:
                                            [1]:Signing up student~
                                            [2]:Deleting account of a student~
                                            [3]:Adding a student to a Course~
                                            [4]:Removing a student from a course~
                                            [5]:Setting Score for a student~
                                            [6]:Printing current average of a student~
                                            [7]:Printing total average of a student~
                                            [8]:Printing courses of a student~
                                            [9]:Printing course credit of a student~
                                            
                                            TEACHER:
                                            [10]:Adding teacher~
                                            [11]:Deleting teacher~
                                            
                                            COURSE:
                                            [12]:Adding a course~
                                            [13]:Removing a course~
                                            [14]:Set teacher for a course~
                                            [15]:Setting exam day for a course~
                                            
                                            ASSIGNMENT:
                                            [16]:Adding assignment
                                            [19]:Setting deadline for an assignment
                                            [20]:Deactivating assignment
                                            [21]:Activating assignment
                                            
                                            PROJECT:
                                            [21]:Deactivating project
                                            [22]:Adding project
                                            
                                            [23]:Log out!
                                            """;
            System.out.println(ALL_OBLIGATIONS.substring(0,ALL_OBLIGATIONS.length()-1));
            int inOperation;
            String Operation;
            try {
                Operation = input.next();
                inOperation = Integer.parseInt(Operation);
            }catch (Exception e){
                System.out.println("Something went wrong!\n");
                continue;
            }
            switch (inOperation){
                case 1://Signing up a student
                    getStudentName();
                    break;
                case 2://Removing Student
                    removeStudent();
                    break;
                case 3://Set Course for Student
                    if (getStudentId("set")){
                        System.out.println("Course has been set successfully!\n");
                    }
                    break;
                case 4://remove Student from a course
                    if (getStudentId("remove")){
                        System.out.println("Student has been removed successfully!\n");
                    }
                    break;
                case 5://set score for student
                    if (getStudentId("setScore")){
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
                    teacherNameValidation();
                    break;
                case 11://removing teacher
                    removeTeacher();
                    break;
                case 12://add course
                    if (getCourseName()){
                        System.out.println("Course has been created!\n");
                    }else {
                        System.out.println("No course create!\n");
                    }
                    break;
                case 13://removing a course
                    removeCourse();
                    break;
                case 14://set teacher for a course
                    setTeacher();
                    break;
                case 15://set exam date
                    courseIdGetterExam();
                    break;
                case 16://add assignment

                    break;
                case 23://
                    Exit = true;
                    break;
                default:
                    System.out.println("Invalid input!\n");
                    break;
            }
            if (Exit){
                break;
            }
        }
    }

    private void courseIdGetterExam(){
        while (true){
            System.out.println("Enter course ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")){
                break;
            }
            if (adminController.getNoCourseFoundById(this, courseId)){
                System.out.println("There is no course with ID " + courseId + "\n");
                continue;
            }
            if (getExamDate(courseId)){
                break;
            }
        }
    }

    private boolean getExamDate(String courseId){
        while (true){
            System.out.println("Enter exam date in pattern YYYY/MM/DD\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String examDate = input.next();
            if (examDate.equals("1")){
                return false;
            }
            String pattern = "^(\\d{4})/(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])$"; // Pattern for yyyy/mm/dd format
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(examDate);
            if (m.find()) {
                if (getExamHour(courseId, examDate)){
                    return true;
                }
            } else {
                System.out.println("The input does not match the yyyy/mm/dd format!\n");
            }
        }
    }

    private boolean getExamHour(String courseId, String examDate){
        while (true){
            System.out.println("Enter exam hour in format HH:MM\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String examHour = input.next();
            if (examHour.equals("1")){
                return false;
            }
            String pattern = "^(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; // Pattern for hh:mm format
            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(examHour);
            if (m.find()) {
                adminController.getSetExamDate(this, courseId, examDate, examHour);
                return true;
            } else {
                System.out.println("The input does not match the hh:mm format!\n");
            }
        }
    }

    private void studentIdGetterPrintCredit(){
        while (true){
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")){
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)){
                System.out.println("There is no student with ID " + studentId );
                continue;
            }
            if (adminController.getPrintAllCredit(this, studentId) == -1){
                System.out.println("Something went wrong!\n");
                continue;
            }else {
                System.out.println("This student has " + adminController.getPrintAllCredit(this, studentId) + "credit!\n");
            }
            break;
        }
    }

    private void studentIdGetterPrintCourse(){
        while (true){
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")){
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)){
                System.out.println("There is no student with ID " + studentId );
                continue;
            }
            String courses = adminController.getPrintAllCourse(this, studentId);
            if (courses.equals("null")){
                System.out.println("This student has no course!\n");
            }else {
                System.out.println(courses + "\n");
            }
            break;
        }
    }

    private void printAverage(String kind){
        String studentId;
        while (true){
            System.out.println("Enter student ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            studentId = input.next();
            if (studentId.equals("1")){
                break;
            }
            if (adminController.getNoStudentFoundById(this, studentId)){
                System.out.println("There is no student with ID " + studentId + "\n");
                continue;
            }
            double average = adminController.getPrintAverage(this, studentId, kind);
            if (average == -1){
                System.out.println("This student has no course!\n");
                continue;
            }
            System.out.println(average);
            break;
        }
    }

    private boolean getStudentId(String obligation){
        while (true){
            System.out.println("Enter ID of student\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String studentId = input.next();
            if (studentId.equals("1")){
                return false;
            }
            if (adminController.getNoStudentFoundById(this, studentId)){
                System.out.println("No student with ID " + studentId + "\n");
                continue;
            }
            if (getCourseIdSetStudent(studentId, obligation)){
                return true;
            }
        }
    }

    private boolean getCourseIdSetStudent(String studentId, String obligation){
        while (true){
            System.out.println("Enter course ID\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")){
                return false;
            }
            if (adminController.getNoCourseFoundById(this, courseId)){
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
                        System.out.println("Student does not has this course!\n");
                        return false;
                    }
                }
                case "setScore" -> {
                    if (adminController.getStudentHasCourse(this, studentId, courseId)) {
                        if (!setStudentScore(studentId, courseId)){
                            continue;
                        }
                    } else {
                        System.out.println("Student does not has this course!\n");
                        return false;
                    }
                }
            }
            return true;
        }
    }

    private void setStudentCourse(String studentId, String courseId){
        adminController.getSetStudentCourse(this, studentId, courseId);
    }

    private void removeStudentCourse(String studentId, String courseId){
        adminController.getRemoveStudentCourse(this, studentId, courseId);
    }

    private boolean setStudentScore(String studentId, String courseId){
        while (true){
            System.out.println("Enter Score(In double form --> 00.00)\n[-1]:Go back");
            Scanner input = new Scanner(System.in);
            double score;
            try {
                score = input.nextDouble();
            }catch (Exception e){
                System.out.println(e.getMessage());
                continue;
            }
            if(score == -1){
                return false;
            }
            if (score<00.00 || score >20.00){
                System.out.println("this score is not in true range!\nEnter a number between 0 and 20\n");
                continue;
            }
            adminController.getSetStudentScore(this, studentId, courseId, score);
            return true;
        }
    }

    private void removeCourse(){
        while (true){
            System.out.println("Enter the ID of course you want to remove:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseId = input.next();
            if (courseId.equals("1")){
                break;
            }
            if (!adminController.getCourseIdChecker(this, courseId)){
                System.out.println("No course found with ID " + courseId);
                continue;
            }
            String courseName = adminController.getCourseNameById(this, courseId);
            System.out.println("Are you sure you want to remove course " + courseName + " with ID " + courseId + " ?\n[1]:Continue\n[2]:Go back");
            String lastChance = input.next();
            if (lastChance.equals("2")){
                break;
            }else if (lastChance.equals("1")){
                adminController.getRemoveCourse(this, courseId);
                System.out.println("Course name " + courseName + " and ID " + courseId + " has successfully removed\n");
                break;
            }else {
                System.out.println("Invalid input!\n");
            }
        }
    }

    private void setTeacher(){
        while (true){
            String courseId = getCourseId();
            if (courseId == null){
                break;
            }
            if (adminController.getCourseIdChecker(this, courseId)){
                if (setCourseTeacher(courseId)){
                    break;
                }
            }else {
                System.out.println("No course with ID: " + courseId );
            }
        }
    }

    private boolean setCourseTeacher(String courseId){
        while (true){
            System.out.println("Enter teacher username:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String teacherUsername = input.next();
            if (teacherUsername.equals("1")){
                return false;
            }
            if (adminController.getNoTeacherFound(this, teacherUsername)){
                System.out.println("No teacher with username: " + teacherUsername + " found!\n");
                continue;
            }else {
                adminController.getSetCourseTeacher(this, courseId, teacherUsername);
                System.out.println("teacher with username: " + teacherUsername + " successfully been set as " + courseId + " teacher!\n");
            }
            return true;
        }
    }

    private String getCourseId(){
        System.out.println("Enter course ID:\n[1]:go back");
        Scanner input = new Scanner(System.in);
        String courseId = input.next();
        if (courseId.equals("1")){
            return null;
        }
        return courseId;
    }

    private boolean getCourseName(){
        while (true){
            System.out.println("Enter name of course you want to add:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String courseName = input.next();
            if (courseName.equals("1")){
                return false;
            }
            if (getCourseCredit(courseName)){
                return true;
            }
        }
    }

    private boolean getCourseCredit(String courseName){
        while (true){
            System.out.println("What is this course credit:\n[-1]:Go back");
            Scanner input = new Scanner(System.in);
            int creditNum;
            try {
                creditNum = input.nextInt();
                input.nextLine();
            }catch (Exception e){
                System.out.println("Invalid input!\n");
                continue;
            }
            if (creditNum == -1){
                return false;
            }
            adminController.getAddCourse(this, courseName, creditNum);
            return true;
        }
    }

    private void adminLogin(){
        String adminLoginUserName;
        String adminLoginPassword;
        Scanner input = new Scanner(System.in);
        boolean Exit;
        while (true){
            Exit = false;
            System.out.println("Enter your username:\n[1]:Go back");
            adminLoginUserName = input.next();
            if (adminLoginUserName.equals("1")){
                Exit = true;
                break;
            }
            System.out.println("Enter your password:\n[1]:Go back");
            adminLoginPassword = input.next();
            if (adminLoginPassword.equals("1")){
                continue;
            }
            if (adminController.getAdminLoginValidation(this, adminLoginUserName, adminLoginPassword)){
                System.out.println("You are not signup OR your password is incorrect\n");
                continue;
            }
            break;
        }
        if (!Exit){
            System.out.println("You successfully logged in!\n");
            adminPowers();
        }
    }

    private void removeTeacher(){
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the username of teacher you want to remove:\n[1]:Go back");
            String removingTeacherUsername = input.next();
            if (removingTeacherUsername.equals("1")){
                break;
            }
            if (adminController.getNoTeacherFound(this, removingTeacherUsername)){
                System.out.println("There is no teacher with username: " + removingTeacherUsername);
                continue;
            }else {
                int lastRemoveChoice;
                while (true){
                    System.out.println("Are you sure you want to remove teacher with username: " + removingTeacherUsername + " ? \n[1]:Continue\n[2]:Go back");
                    try {
                        lastRemoveChoice = input.nextInt();
                    }catch (Exception exception){
                        System.out.println("Invalid input!\n");
                        continue;
                    }
                    break;
                }
                switch (lastRemoveChoice){
                    case 1:
                        System.out.println("Removing teacher...\n");
                        adminController.getTeacherAccountRemover(this, removingTeacherUsername);
                        System.out.println("Teacher named: " + removingTeacherUsername + " has successfully removed!\n");
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

    private void teacherNameValidation(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Enter teacher name:\n[1]:Go back");
            String teacherName = input.next();
            if (teacherName.equals("1")){
                break;
            }
            if (teacherUsernameGetter(teacherName)){
                continue;
            }else {
                break;
            }
        }
    }

    private boolean teacherUsernameGetter(String teacherName){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Enter teacher username:\n[1]:Go back");
            String teacherUsername = input.next();
            if (teacherUsername.equals("1")){
                return true;
            }
            if (adminController.getTeacherUsernameValidation(this, teacherUsername)){
                System.out.println("This username is already exist!\n");
                continue;
            }
            if (teacherPasswordGetter(teacherName, teacherUsername)){
                continue;
            }else {
                return false;
            }
        }
    }

    private boolean teacherPasswordGetter(String teacherName, String teacherUsername){
        Scanner input = new Scanner(System.in);
        String teacherPassword;
        while (true){//Password
            System.out.println("Enter your password:\n[1]:Go back");
            teacherPassword = input.next();
            if (teacherPassword.equals("1")){
                return true;
            }
            if (adminController.getAdminPasswordNotValidation(this, teacherPassword, teacherUsername)){
                System.out.println("The password does not follow the correct pattern\n");
                continue;
            }
            if (!adminController.getTeacherSignup(this,teacherName, teacherUsername, teacherPassword)){
                System.out.println("Something went wrong!\n");
            }else {
                System.out.println("Signup successfully!\n");
                return false;
            }
        }
    }

    private void removeStudent(){
        while (true){
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the name of student you want to remove:\n[1]:Go back");
            String removingStudentName = input.next();
            if (removingStudentName.equals("1")){
                break;
            }
            if (adminController.getNoStudentFound(this, removingStudentName)){
                System.out.println("There is no student named: " + removingStudentName);
                continue;
            }else {
                int lastRemoveChoice;
                while (true){
                    System.out.println("Are you sure you want to remove student named: " + removingStudentName + " ? \n[1]:Continue\n[2]:Go back");
                    try {
                        lastRemoveChoice = input.nextInt();
                    }catch (Exception exception){
                        System.out.println("Invalid input!\n");
                        continue;
                    }
                    break;
                }
                switch (lastRemoveChoice){
                    case 1:
                        System.out.println("Removing Student...\n");
                        adminController.getStudentAccountRemover(this, removingStudentName);
                        System.out.println("Student named: " + removingStudentName + " has successfully removed!\n");
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

    private void getStudentName(){
        String StudentSignUpName;
        Scanner input = new Scanner(System.in);
        boolean DoubleName = true;//FOR AMIR HOSSEIN LOVE!
        while (true){
            System.out.println("Enter student name:\n[1]:Go back");
            StudentSignUpName = input.next();
            if (StudentSignUpName.equals("1")){
                return;
            }
            if (DoubleName && !adminController.getStudentNameValidation(this, StudentSignUpName)){
                System.out.println("Student named: " + StudentSignUpName + " is already signed up!\n[1]:Go back\n[2]:Signup anyway");
                String choice = input.next();
                if (choice.equals("1")){
                    continue;
                } else if (choice.equals("2")) {
                    DoubleName = false;
                }else {
                    System.out.println("Invalid input!\n");
                }
                continue;
            }
            if (!getStudentUsername(StudentSignUpName)){
                continue;
            }
            break;
        }
    }

    private boolean getStudentUsername(String studentName){
        Scanner input = new Scanner(System.in);
        String StudentSignUpUsername;
        while (true){
            System.out.println("Enter student username:\n[1]:Go back");
            StudentSignUpUsername = input.next();
            if (StudentSignUpUsername.equals("1")){
                return false;
            }
            if (adminController.getStudentUsernameValidation(this, StudentSignUpUsername)){
                System.out.println("Username is already token!\n");
                continue;
            }
            if (!getStudentPassword(studentName,StudentSignUpUsername)){
                continue;
            }
            break;
        }
        return true;
    }
    private boolean getStudentPassword(String StudentName, String StudentSignUpUsername){
        Scanner input = new Scanner(System.in);
        String StudentSignUpPassword;
        while (true){
            System.out.println("Enter student password:\n[1]:Go back");
            StudentSignUpPassword = input.next();
            if (StudentSignUpPassword.equals("1")){
                return false;
            }
            if (adminController.getAdminPasswordNotValidation(this, StudentSignUpPassword, StudentSignUpUsername)){
                System.out.println("The password does not follow the correct pattern\n");
                continue;
            }
            break;
        }
        signUpStudent(StudentName, StudentSignUpUsername, StudentSignUpPassword);
        return true;
    }

    private void signUpStudent(String StudentName, String Username, String Password){
        this.adminController.getStudentSignUp(this, StudentName, Username, Password);
        System.out.println("Student named: " + StudentName + " successfully signed up!");
    }

    private void adminSignUpValidation(){
        while (true){
            String ValidationCode = "SBU_ADMIN";
            System.out.println("Enter your SBU validation code:\n[1]:Exit");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            
            if (choice.equals("1")){ //if input is "a" -> Exception! -> handled*
                break;
            }
            
            else if (choice.equals(ValidationCode)){ 
                adminSignUp();
                break;
            }else {
                System.out.println("Unable to validate your information!\n");
            }
            
        }
    }

    private void adminSignUp(){
        number1:
        while (true){
            boolean Exit1 = false;
            String adminUserName;
            Scanner input = new Scanner(System.in);
            number2:
            while (true){
                System.out.println("Enter your username:\n[1]:Exit");
                adminUserName = input.next(); 
                if (adminUserName.equals("1")){ // change condition of if -> exception handeling
                    break number1;
                    // Exit1 = true;
                    // break;
                }
                
                else if (adminController.getAdminNameValidation(this,adminUserName)){ //unreachable methode ? (AdminValidation)?
                    System.out.println("Your username is unavailable!\n");
                    continue;
                }
                break;
            }
            // if (Exit1){
            //     break;
            // }
            
            boolean Exit2 = false;
            String adminPassword;
      
            number3:
            while (true){//Password
                input.nextLine();
                System.out.println("Enter your password:\n[1]:Go back");
                adminPassword = input.nextLine();
                if (adminPassword.equals("1")){
                    continue number1;
                    // Exit2 = true;
                    // continue;
                }
                if (adminController.getAdminPasswordNotValidation(this,adminPassword, adminUserName)){
                    System.out.println("The password does not follow the correct pattern\n");
                    continue;
                }
                break;
            }
            // if (Exit2){
            //     continue;
            // }
            if (adminController.getSaveAdmin(this, adminUserName, adminPassword)){
                System.out.println("Signup successfully!\n");
                adminPowers();
            }else {
                System.out.println("Something went wrong!\n");
            }
            input.close();
            break;
        }
    }
}
