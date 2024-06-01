package AmirhosseinObligationToFuckingMVCPrograming;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MainMVC {
    public static void main(String[] args) {
        MainModel.studentIdSetter();
        MainModel.courseIdSetter();
        //Beginning
        while (true){
            boolean Exit1 = false;
            System.out.println("Who are you?\n[1]:Admin\n[2]:Teacher\n[3]:Exit");
            Scanner input = new Scanner(System.in);
            String role;
            int intRole ; 
            try {
                role = input.next();
                intRole = Integer.parseInt(role); //bug fixed !
            }catch (Exception e){
                System.out.println("Invalid input!\n");
                continue;
            }
            switch (intRole){
                case 1:
                    AdminView adminView = new AdminView();
                    AdminModel adminModel = new AdminModel();
                    AdminController adminController = new AdminController(adminModel, adminView);
                    adminView.run(adminController);
                    //input.close();
                    break;
                case 2:
                    TeacherView teacherView = new TeacherView();
                    TeacherModel teacherModel = new TeacherModel();
                    TeacherController teacherController = new TeacherController(teacherModel, teacherView);
                    teacherView.run(teacherController);
                    //input.close();
                    break;
                case 3:
                    Exit1 = true;
                    //input.close();
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
