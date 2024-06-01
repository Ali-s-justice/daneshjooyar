package AmirhosseinObligationToFuckingMVCPrograming;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminModel {

    public boolean adminNameValidation(String NewAdminName){//Admin Name Validation
        ArrayList<String> AllAdminsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/admins.txt");
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

    public boolean PasswordNotValidation(String password, String username){
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(password);

        if (password.contains(username)) {
            return true;
        }
        return !m.matches();
    }

    public boolean saveAdmin(String userName, String password){
        try {
            FileWriter AdminFileWriter = new FileWriter("daneshjooyar/informations/admins.txt", true);
            String HashPassword = hashPassword(password);
            AdminFileWriter.write(userName + "//" + HashPassword + "\n");
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

    //Admin Login
    public boolean adminLoginValidation(String AdminLoginName, String Password){//Admin Login Validation
        ArrayList<String> AllAdminsName = new ArrayList<>();
        ArrayList<String> AllAdminsPassword = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/admins.txt");
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

    //Signup Student Name validation --> return true if name is valid
    public boolean StudentNameValidation(String studentName){
        ArrayList<String> AllStudentsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
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

    //Signup Student Username Validation --> return true if username is unavailable
    public boolean StudentUsernameValidation(String Username){
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

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void SignUpStudent(String StudentName, String Username, String Password){
        String student_code = "";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int code = Integer.parseInt(line);
            code++;
            student_code = String.valueOf(code);
            bufferedReader.close();
            try {//writing new code
                FileWriter writer = new FileWriter("daneshjooyar/informations/student_num.txt");
                writer.write(student_code);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/students.txt",true);
            writer.write(StudentName + "//" + student_code + "//" + Username + "//" + hashPassword(Password) + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean noStudentFound(String studentName){
        ArrayList<String> AllStudentsName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
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

    //Remove account of a student
    public void studentAccountRemover(String studentName){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
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
                FileWriter writer = new FileWriter("daneshjooyar/informations/students.txt");
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

    //return true if username is not available
    public boolean teacherUsernameValidation(String teacherUsername){
        ArrayList<String> allTeachersUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
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

    public boolean teacherSignup(String teacherName, String teacherUsername, String teacherPassword){
        try {
            FileWriter AdminFileWriter = new FileWriter("daneshjooyar/informations/teachers.txt", true);
            AdminFileWriter.write(teacherName + "//" + teacherUsername + "//" + hashPassword(teacherPassword) + "\n");
            AdminFileWriter.close();
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean noTeacherFound(String teacherUsername){
        ArrayList<String> allTeachersUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allTeachersUsername.add(Info[1]);
            }
            bufferedReader.close();
            return !allTeachersUsername.contains(teacherUsername);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void teacherAccountRemover(String teacherUsername){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[1].equals(teacherUsername))){
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            try {
                FileWriter writer = new FileWriter("daneshjooyar/informations/teachers.txt");
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

    public void addCourse(String courseName, int credit){
        String courseId = "";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int code = Integer.parseInt(line);
            code++;
            courseId = String.valueOf(code);
            bufferedReader.close();
            try {//writing new code
                FileWriter writer = new FileWriter("daneshjooyar/informations/course_num.txt");
                writer.write(courseId);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/course.txt",true);
            writer.write(courseName + "//" + courseId + "//" + credit + "//" + "null" + "//" + "null" + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setCourseTeacher(String courseId, String teacherUsername){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[1].equals(courseId))){
                    allOfFile.add(line);
                }else {
                    Info[3] = teacherUsername;
                    StringBuilder newString = new StringBuilder();
                    for (String s : Info) {
                        newString.append(s);
                        newString.append("//");
                    }
                    newString = new StringBuilder(newString.substring(0, newString.length() - 2));
                    allOfFile.add(newString.toString());
                }
            }
            bufferedReader.close();
            try {
                FileWriter writer = new FileWriter("daneshjooyar/informations/course.txt");
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

    public boolean courseIdChecker(String courseId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> allId = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allId.add(Info[1]);
            }
            return allId.contains(courseId);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String courseNameById(String courseId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> allId = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(courseId)){
                    return Info[0];
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public void removeCourse(String courseId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[1].equals(courseId))){
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            try {
                FileWriter writer = new FileWriter("daneshjooyar/informations/course.txt");
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

}
