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
                        System.out.println("Are you NEW?\n[1]:Yes(Signup)\n[2]:No(Login)\n[3]:Go Back");
                        int AdminLoginOrSignup = input.nextInt();
                        switch (AdminLoginOrSignup){
                            case 1://Signing up Admin
                                String AdminName;
                                String AdminPassword;
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
                                break;
                            case 3://Go Back
                                Exit2 = true;
                                break;
                            default:
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
