package MVC_PROJECT;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainModel {

    public static void studentIdSetter(){
        //Student Id Setter
        try (BufferedReader reader = new BufferedReader(new FileReader("daneshjooyar/informations/student_num.txt"))) {
            String code;
            code = reader.readLine();
            if (code==null){
                try {
                    FileWriter writer = new FileWriter("daneshjooyar/informations/student_num.txt");
                    writer.write("402243000");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void courseIdSetter(){
        //Course Id Setter
        try (BufferedReader reader = new BufferedReader(new FileReader("daneshjooyar/informations/course_num.txt"))) {
            String code;
            code = reader.readLine();
            if (code==null){
                try {
                    FileWriter writer = new FileWriter("daneshjooyar/informations/course_num.txt");
                    writer.write("1000");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void assignmentIdSetter(){
        try (BufferedReader reader = new BufferedReader(new FileReader("daneshjooyar/informations/assignment_num.txt"))) {
            String code;
            code = reader.readLine();
            if (code==null){
                try {
                    FileWriter writer = new FileWriter("daneshjooyar/informations/assignment_num.txt");
                    writer.write("10000");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void teacherIdSetter(){
        try (BufferedReader reader = new BufferedReader(new FileReader("daneshjooyar/informations/teacher_num.txt"))) {
            String code;
            code = reader.readLine();
            if (code==null){
                try {
                    FileWriter writer = new FileWriter("daneshjooyar/informations/teacher_num.txt");
                    writer.write("1000");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
