package AmirhosseinObligationToFuckingMVCPrograming;
import java.util.Scanner;

public class AdminView {

    AdminController adminController;

    public void run(AdminController adminController){
        this.adminController = adminController;
        this.adminController.getStudentIDSetter(this);
        while (true){
            boolean Exit = false;
            System.out.println("Are you new admin?\n[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
            Scanner input = new Scanner(System.in);
            int AdminLoginOrSignup = 0;
            try {
                AdminLoginOrSignup = input.nextInt();
            }catch (Exception exception){
                System.out.println("Invalid input!\n");
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
        }
    }

    private void adminLogin(){
        String adminLoginUserName;
        String adminLoginPassword;
        Scanner input = new Scanner(System.in);
        boolean Exit;
        while (true){
            Exit = false;
            System.out.println("Enter your name:\n[1]:Go back");
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

    private void adminPowers(){
        boolean Exit;
        Scanner input = new Scanner(System.in);
        while (true){
            Exit = false;
            String ALL_OBLIGATIONS = """
                                            What do you want to to?
                                            
                                            STUDENT:
                                            [1]:Signing up student
                                            [2]:Deleting account of a student
                                            [3]:
                                            [4]:Removing course of a student
                                            [5]:Adding Course to a student
                                            [6]:Printing number of course units of a student
                                            [7]:Printing current average of a student
                                            [8]:Printing total average of a student
                                            [9]:Printing courses of a student
                                            [10]:Setting Score for a student
                                            [11]:Removing student from a course
                                            [12]:Adding student to a course
                                            
                                            TEACHER:
                                            [13]:Adding teacher
                                            [14]:Deleting teacher
                                            
                                            COURSE:
                                            [15]:Removing a course
                                            [16]:Adding a course
                                            [17]:Setting exam day for a course
                                            
                                            ASSIGNMENT:
                                            [18]:Adding assignment
                                            [19]:Changing deadline of an assignment
                                            [20]:Deactivating assignment
                                            
                                            PROJECT:
                                            [21]:Deactivating project
                                            [22]:Adding project
                                            
                                            [23]:Log out!
                                            """;
            System.out.println(ALL_OBLIGATIONS.substring(0,ALL_OBLIGATIONS.length()-1));
            int Operation;
            try {
                Operation = input.nextInt();
            }catch (Exception e){
                System.out.println("Something went wrong!\n");
                continue;
            }
            switch (Operation){
                case 1://Signing up a student
                    getStudentName();
                    break;
                case 2://Removing Student
                    removeStudent();
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
            System.out.println("Enter your SBU validation code:\n[1]:Go back");
            Scanner input = new Scanner(System.in);
            String choice = input.next();
            if (choice.length() < 2 && Integer.parseInt(choice) == 1){
                break;
            }
            if (choice.equals(ValidationCode)){
                adminSignUp();
                break;
            }else {
                System.out.println("Unable to validate your information!\n");
            }
        }
    }

    private void adminSignUp(){
        while (true){
            boolean Exit1 = false;
            String adminUserName;
            Scanner input = new Scanner(System.in);
            while (true){
                System.out.println("Enter your username:\n[1]:Go back");
                adminUserName = input.next();
                if (adminUserName.length() < 2 && Integer.parseInt(adminUserName) == 1){
                    Exit1 = true;
                    break;
                }
                if (adminController.getAdminNameValidation(this,adminUserName)){
                    System.out.println("Your username is unavailable!\n");
                    continue;
                }
                break;
            }
            if (Exit1){
                break;
            }
            boolean Exit2 = false;
            String adminPassword;
            while (true){//Password
                System.out.println("Enter your password:\n[1]:Go back");
                adminPassword = input.next();
                if (adminPassword.equals("1")){
                    Exit2 = true;
                    break;
                }
                if (adminController.getAdminPasswordNotValidation(this,adminPassword, adminUserName)){
                    System.out.println("The password does not follow the correct pattern\n");
                    continue;
                }
                break;
            }
            if (Exit2){
                continue;
            }
            if (adminController.getSaveAdmin(this, adminUserName, adminPassword)){
                System.out.println("Signup successfully!\n");
                adminPowers();
            }else {
                System.out.println("Something went wrong!\n");
            }
            break;
        }
    }
}
