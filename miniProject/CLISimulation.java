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
                        System.out.println("Are you NEW Admin?\n[1]:Yes(Signup)\n[2]:No(Login)\n[3]:Go Back");
                        int AdminLoginOrSignup = input.nextInt();
                        switch (AdminLoginOrSignup){
                            case 1://Signing up Admin
                                String AdminName;
                                String AdminPassword;
                                String ValidationCode = "SBU_ADMIN";
                                String ValidationCodeChecker;
                                while (true){
                                    System.out.println("Enter Your SBU validation code:");
                                    ValidationCodeChecker = input.next();
                                    if (!ValidationCode.equals(ValidationCodeChecker)){
                                        System.out.println("Unable to validate your information!");
                                        continue;
                                    }
                                    break;
                                }
                                while (true){
                                    System.out.println("Enter Your Name:");
                                    AdminName = input.next();
                                    if (!Model.getModel().AdminNameValidation(AdminName)){
                                        System.out.println("Your Name is Unavailable!");
                                        continue;
                                    }
                                    break;
                                }
                                while (true){//Password
                                    System.out.println("Enter Your Password:");
                                    AdminPassword = input.next();
                                    if (!Model.getModel().AdminPasswordValidation(AdminPassword,AdminName)){
                                        System.out.println("The password does not follow the correct pattern");
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
                                    System.out.println("Enter Your Name:");
                                    AdminLoginName = input.next();
                                    System.out.println("Enter Your Password:");
                                    AdminLoginPassword = input.next();
                                    if (!Model.getModel().AdminLoginValidation(AdminLoginName,AdminLoginPassword)){
                                        System.out.println("You are not signup OR your password is incorrect");
                                        continue;
                                    }
                                    break;
                                }
                                System.out.println("You successfully Logged in!");
                                boolean Exit3 = false;
                                while (true){
                                    System.out.println("""
                                            What do you want to to?
                                            
                                            STUDENT:
                                            [1]:Signing up student
                                            [2]:Deleting account of a student
                                            [3]:Logging out a Student
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
                                            Adding teacher
                                            
                                            COURSE:
                                            Removing a course
                                            Adding a course
                                            []:Setting exam day for a course
                                            
                                            ASSIGNMENT:
                                            Adding assignment
                                            Changing deadline of an assignment
                                            Deactivating assignment
                                            
                                            PROJECT:
                                            Deactivating project
                                            Adding project
                                            
                                            [100]EXIT
                                            """);
                                    int Operation = input.nextInt();
                                    switch (Operation){
                                        case 1://Signing up a student
                                            break;
                                        case 100:
                                            Exit3 = true;
                                            break;
                                        default:
                                            System.out.println("Invalid input!");
                                            break;
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
                                System.out.println("You entered wrong value!");
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
                    System.out.println("Invalid Input!");
            }
            if (Exit1){
                break;
            }
        }
    }
}
