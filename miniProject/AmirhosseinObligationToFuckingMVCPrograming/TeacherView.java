package AmirhosseinObligationToFuckingMVCPrograming;

import java.util.Scanner;

public class TeacherView {

    TeacherController teacherController;

    public void run(TeacherController teacherController){
        this.teacherController = teacherController;
        Scanner input = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println("Are you new teacher?\n[1]:[1]:Yes-->(Signup)\n[2]:No-->(Login)\n[3]:Go Back");
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

    }

    private void teacherPowers(){

    }

}
