import java.util.Scanner;

public class CLISimulation {
    public static void main(String[] args) {
        while (true){
            boolean Exit1 = false;
            System.out.println("Who are you?\n[1]:Admin\n[2]:Teacher\n[3]:Exit");
            Scanner input = new Scanner(System.in);
            int Personality = input.nextInt();
            //Working on Admin:
            switch (Personality){
                case 1://Admin
                    while (true){
                        boolean Exit2 = false;
                        System.out.println("Are you new admin?\n[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
                        int AdminLoginOrSignup = input.nextInt();
                        switch (AdminLoginOrSignup){
                            case 1://Signing up Admin
                                String AdminName;
                                String AdminPassword;
                                String ValidationCode = "SBU_ADMIN";
                                String ValidationCodeChecker;
                                while (true){
                                    System.out.println("Enter your SBU validation code:");
                                    ValidationCodeChecker = input.next();
                                    if (!ValidationCode.equals(ValidationCodeChecker)){
                                        System.out.println("Unable to validate your information !\n");
                                        continue;
                                    }
                                    break;
                                }
                                while (true){
                                    System.out.println("Enter your name:");
                                    AdminName = input.next();
                                    if (Model.getModel().AdminNameValidation(AdminName)){
                                        System.out.println("Your name is unavailable!\n");
                                        continue;
                                    }
                                    break;
                                }
                                while (true){//Password
                                    System.out.println("Enter your password:");
                                    AdminPassword = input.next();
                                    if (Model.getModel().PasswordNotValidation(AdminPassword, AdminName)){
                                        System.out.println("The password does not follow the correct pattern\n");
                                        continue;
                                    }
                                    break;
                                }
                                Model.getModel().AdminMaker(AdminName,AdminPassword);
                                break;
                            case 2://logging Admin
                                String AdminLoginName;
                                String AdminLoginPassword;
                                while (true){
                                    System.out.println("Enter your name:");
                                    AdminLoginName = input.next();
                                    System.out.println("Enter your password:");
                                    AdminLoginPassword = input.next();
                                    if (Model.getModel().AdminLoginValidation(AdminLoginName,AdminLoginPassword)){
                                        System.out.println("You are not signup OR your password is incorrect\n");
                                        continue;
                                    }
                                    break;
                                }
                                System.out.println("You successfully logged in!\n");
                                boolean Exit3 = false;
                                while (true){
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
                                            
                                            [23]:Go back
                                            """;
                                    System.out.println(ALL_OBLIGATIONS.substring(1,ALL_OBLIGATIONS.length()-1));
                                    int Operation = input.nextInt();
                                    boolean goBack = false;
                                    switch (Operation){
                                        case 1://Signing up a student
                                            String StudentSignUpName;
                                            while (true){
                                                System.out.println("Enter student name:\n[1]:Go back");
                                                StudentSignUpName = input.next();
                                                if (StudentSignUpName.equals("1")){
                                                    goBack = true;
                                                    break;
                                                }
                                                if (!Model.getModel().StudentNameValidation(StudentSignUpName)){
                                                    System.out.println("Student named: " + StudentSignUpName + " is already signed up !");
                                                    continue;
                                                }
                                                break;
                                            }
                                            if (goBack){
                                                break;
                                            }
                                            String StudentSignUpUsername;
                                            while (true){
                                                System.out.println("Enter student username:");
                                                StudentSignUpUsername = input.next();
                                                if (Model.getModel().StudentUsernameValidation(StudentSignUpUsername)){
                                                    System.out.println("Username is already token!\n");
                                                    continue;
                                                }
                                                break;
                                            }
                                            String StudentSignUpPassword;
                                            while (true){
                                                System.out.println("Enter student password:");
                                                StudentSignUpPassword = input.next();
                                                if (Model.getModel().PasswordNotValidation(StudentSignUpPassword, StudentSignUpUsername)){
                                                    System.out.println("The password does not follow the correct pattern\n");
                                                    continue;
                                                }
                                                break;
                                            }
                                            Model.getModel().SignUpStudent(StudentSignUpName, StudentSignUpUsername, StudentSignUpPassword);
                                            System.out.println("Signup has been successful!\n");
                                            break;
                                        case 2:
                                            System.out.println("Enter the name of student you want to remove:");
                                            String removingStudentName = input.next();
                                            if (Model.getModel().noStudentFound(removingStudentName)){
                                                System.out.println("There is no student named: " + removingStudentName);
                                            }else {
                                                System.out.println("Are you sure you want to remove student named: " + removingStudentName + " ? \n[1]:Continue\n[2]:Go back");
                                                int lastRemoveChoice = input.nextInt();
                                                switch (lastRemoveChoice){
                                                    case 1:
                                                        System.out.println("Removing Student...\n");
                                                        Model.getModel().studentAccountRemover(removingStudentName);
                                                        System.out.println("Student named: " + removingStudentName + " has successfully removed !\n");
                                                        break;
                                                    case 2:
                                                        goBack = true;
                                                        break;
                                                    default:
                                                        System.out.println("Invalid input\n");
                                                }
                                            }
                                            break;
                                        case 23://
                                            Exit3 = true;
                                            break;
                                        default:
                                            System.out.println("Invalid input!\n");
                                            break;
                                    }
                                    if (goBack){
                                        goBack = false;
                                        continue;
                                    }
                                    if (Exit3){
                                        break;
                                    }
                                }

                                break;
                            case 3://Go Back
                                Exit2 = true;
                                break;
                            default:
                                System.out.println("Invalid input!\n");
                        }
                        if (Exit2){
                            break;
                        }
                    }
                    break;
                case 2://Teacher
                    break;
                case 3://Exit
                    Exit1 = true;
                    break;
                default:
                    System.out.println("Invalid Input!\n");
            }
            if (Exit1){
                break;
            }
        }
    }
}
