package AmirhosseinObligationToFuckingMVCPrograming;

import java.util.Scanner;

public class TeacherView {

    TeacherController teacherController;

    public void run(TeacherController teacherController){
        this.teacherController = teacherController;
        Scanner input = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("Are you new teacher?\n[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
            String task = input.next();
            switch (task) {
                case "1":
                    teacherNameValidation();
                    break;
                case "2":
                    teacherLogin();
                    break;
                case "3":
                    Exit = true;
                    break;
                default:
                    System.out.println("Invalid input!");
            }
        } while (!Exit);
    }

    private void teacherNameValidation(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("Enter your name:\n[1]:Go back");
            String teacherName = input.next();
            if (teacherName.equals("1")){
                break;
            }
            if (teacherController.getTeacherNameValidation(this ,teacherName)){
                System.out.println("You are already signup!\nLogin please!");
                teacherLogin();
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
            System.out.println("Enter your username:\n[1]:Go back");
            String teacherUsername = input.next();
            if (teacherUsername.equals("1")){
                return true;
            }
            if (teacherController.getTeacherUsernameValidation(this, teacherUsername)){
                System.out.println("Your username is already exist!\n");
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
        while (true){
            String teacherPassword;
            while (true){//Password
                System.out.println("Enter your password:\n[1]:Go back");
                teacherPassword = input.next();
                if (teacherPassword.equals("1")){
                    return true;
                }
                if (teacherController.getTeacherPasswordNotValidation(this, teacherPassword, teacherUsername)){
                    System.out.println("The password does not follow the correct pattern\n");
                    continue;
                }
                if (!teacherController.getTeacherSignup(this,teacherName, teacherUsername, teacherPassword)){
                    System.out.println("Something went wrong!\n");
                }else {
                    System.out.println("Signup successfully!\n");
                    teacherPowers();
                    return false;
                }
            }
        }
    }

    private void teacherLogin(){
        String teacherLoginUserName;
        String teacherLoginPassword;
        Scanner input = new Scanner(System.in);
        boolean Exit;
        while (true){
            Exit = false;
            System.out.println("Enter your name:\n[1]:Go back");
            teacherLoginUserName = input.next();
            if (teacherLoginUserName.equals("1")){
                Exit = true;
                break;
            }
            System.out.println("Enter your password:\n[1]:Go back");
            teacherLoginPassword = input.next();
            if (teacherLoginPassword.equals("1")){
                continue;
            }
            if (teacherController.getTeacherLoginValidation(this, teacherLoginUserName, teacherLoginPassword)){
                System.out.println("You are not signup OR your password is incorrect\n");
                continue;
            }
            break;
        }
        if (!Exit){
            System.out.println("You successfully logged in!\n");
            teacherPowers();
        }
    }

    private void teacherPowers(){
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
                    //getStudentName();
                    break;
                case 2://Removing Student
                    //removeStudent();
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

}
