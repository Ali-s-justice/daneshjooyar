package AmirhosseinObligationToFuckingMVCPrograming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class StudentModel {

    //Signup Student Username Validation --> return true if username is unavailable
    public boolean StudentUsernameValidation(String Username) {
        ArrayList<String> AllStudentsUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllStudentsUsername.add(Info[2]);
            }
            bufferedReader.close();
            return AllStudentsUsername.contains(Username);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
