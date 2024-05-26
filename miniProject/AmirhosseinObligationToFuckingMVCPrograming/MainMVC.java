package AmirhosseinObligationToFuckingMVCPrograming;

import java.util.Scanner;

public class MainMVC {
    public static void main(String[] args) {
        while (true){
            boolean Exit1 = false;
            System.out.println("Who are you?\n[1]:Admin\n[2]:Teacher\n[3]:Exit");
            Scanner input = new Scanner(System.in);
            int role;
            try {
                role = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid input!\n");
                continue;
            }
            switch (role){
                case 1:
                    AdminView adminView = new AdminView();
                    AdminModel adminModel = new AdminModel();
                    AdminController adminController = new AdminController(adminModel, adminView);
                    adminView.run(adminController);
                    break;
                case 2:
                    TeacherView teacherView = new TeacherView();
                    TeacherModel teacherModel = new TeacherModel();
                    TeacherController teacherController = new TeacherController(teacherModel, teacherView);
                    teacherView.run(teacherController);
                    break;
                case 3:
                    Exit1 = true;
                    break;
                default:
                    System.out.println("Invalid input!\n");
                    break;
            }
            if (Exit1){
                System.out.println("Exiting system!\n");
                break;
            }
        }
    }
}
