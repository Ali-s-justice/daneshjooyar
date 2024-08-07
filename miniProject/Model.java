import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.regex.*;
import java.security.SecureRandom;


public class Model {
    private static Model model = null;
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Admin> admins = new ArrayList<>();
    ArrayList<Student> students = new ArrayList<>();
    private Model(){

    }
    public static Model getModel(){
         if (model ==null){
             model = new Model();
             try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\student_num.txt"))) {
                 String code;
                 code = reader.readLine();
                 if (code==null){
                     try {
                         FileWriter writer = new FileWriter("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\student_num.txt");
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
         return model;
    }
    // Admin
    public boolean AdminNameValidation(String NewAdminName){//Admin Name Validation
        ArrayList<String> AllAdminsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\admins.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllAdminsName.add(Info[0]);
            }
            bufferedReader.close();
            return AllAdminsName.contains(NewAdminName);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean PasswordNotValidation(String NewPassword, String username){
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(NewPassword);

        if (NewPassword.contains(username)) {
            return true;
        }

        return !m.matches();
    }

    //Making Admin
    public void AdminMaker(String name, String Password){
        Admin NewAdmin = new Admin(name,Password);
        admins.add(NewAdmin);
    }

    //Saving Password
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

    //Admin Login
    public boolean AdminLoginValidation(String AdminLoginName, String Password){//Admin Login Validation
        ArrayList<String> AllAdminsName = new ArrayList<>();
        ArrayList<String> AllAdminsPassword = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\admins.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllAdminsName.add(Info[0]);
                AllAdminsPassword.add(Info[1]);
            }
            bufferedReader.close();
            return !(AllAdminsName.contains(AdminLoginName) && checkPassword(Password, AllAdminsPassword.get(AllAdminsName.indexOf(AdminLoginName))));

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Sign up a student
    public void SignUpStudent(String StudentName, String Username, String Password){
        Student NewStudent = new Student(StudentName,Username,Password);
        students.add(NewStudent);
    }

    //Signup Student Name validation --> return true if name is valid
    public boolean StudentNameValidation(String NewStudentName){
        ArrayList<String> AllStudentsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllStudentsName.add(Info[0]);
            }
            bufferedReader.close();
            return !AllStudentsName.contains(NewStudentName);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    //Signup Student Username Validation --> return true if username is unavailable
    public boolean StudentUsernameValidation(String Username){
        ArrayList<String> AllStudentsUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllStudentsUsername.add(Info[2]);
            }
            bufferedReader.close();
            return AllStudentsUsername.contains(Username);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    //Remove account of a student
    public void studentAccountRemover(String studentName){
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[0].equals(studentName))){
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            try {
                FileWriter writer = new FileWriter("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\students.txt");
                for (String s : allOfFile) {
                    writer.write(s + "\n");
                }
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean noStudentFound(String studentName){
        ArrayList<String> AllStudentsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("C:\\Users\\Asus\\Desktop\\Ap-Project\\daneshjooyar\\informations\\students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                AllStudentsName.add(Info[0]);
            }
            bufferedReader.close();
            return !AllStudentsName.contains(studentName);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

}
