package AmirhosseinObligationToFuckingMVCPrograming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeacherModel {

    //return true if teacher name is not available
    public boolean teacherNameValidation(String teacherName){
        ArrayList<String> allTeachersName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allTeachersName.add(Info[0]);
            }
            bufferedReader.close();
            return allTeachersName.contains(teacherName);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //return true if username is not available
    public boolean teacherUsernameValidation(String teacherUsername){
        ArrayList<String> allTeachersUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allTeachersUsername.add(Info[1]);
            }
            bufferedReader.close();
            return allTeachersUsername.contains(teacherUsername);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean teacherPasswordNotValidation(String password, String username){
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(password);

        if (password.contains(username)) {
            return true;
        }
        return !m.matches();
    }

    public boolean teacherSignup(String teacherName, String teacherUsername, String teacherPassword){
        try {
            FileWriter AdminFileWriter = new FileWriter("informations/teachers.txt", true);
            AdminFileWriter.write(teacherName + "//" + teacherUsername + "//" + hashPassword(teacherPassword) + "\n");
            AdminFileWriter.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String hashPassword(String password) {//Password to hash
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] salt = getSalt();
            md.update(salt);

            byte[] hashedPassword = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hashedPassword);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] getSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }





    public boolean teacherLoginValidation(String TeacherLoginName, String Password){//Admin Login Validation
        ArrayList<String> AllTeachersName = new ArrayList<>();
        ArrayList<String> AllTeachersPassword = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllTeachersName.add(Info[1]);
                AllTeachersPassword.add(Info[2]);
            }
            bufferedReader.close();
            return !(AllTeachersName.contains(TeacherLoginName) && checkPassword(Password, AllTeachersPassword.get(AllTeachersName.indexOf(TeacherLoginName))));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean checkPassword(String password, String storedPassword) {
        try {
            String[] parts = storedPassword.split(":");
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] storedHash = Base64.getDecoder().decode(parts[1]);

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);

            byte[] hashedPassword = md.digest(password.getBytes());

            return MessageDigest.isEqual(storedHash, hashedPassword);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
