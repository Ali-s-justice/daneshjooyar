package AmirhosseinObligationToFuckingMVCPrograming;
import java.util.InputMismatchException;
import java.util.Scanner;

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
            }
            switch (AdminLoginOrSignup){
                case 1:
                    adminSignUpValidation();
                    break;
                case 2:
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
                System.out.println("Unable to validate your information !\n");
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
                    System.out.println("Your name is unavailable!\n");
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
                if (adminUserName.length() < 2 && Integer.parseInt(adminPassword) == 1){
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
                break;
            }
            if (adminController.getSaveAdmin(this, adminUserName, adminPassword)){
                System.out.println("Signup successfully!\n");
            }else {
                System.out.println("Something went wrong!\n");
            }
            break;
        }
    }
}
