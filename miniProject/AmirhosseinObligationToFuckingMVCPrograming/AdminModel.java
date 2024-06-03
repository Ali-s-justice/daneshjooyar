package AmirhosseinObligationToFuckingMVCPrograming;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
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

    public boolean noStudentFoundById(String studentId){
        ArrayList<String> allStudentId = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allStudentId.add(Info[1]);
            }
            bufferedReader.close();
            return !allStudentId.contains(studentId);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean noCourseFoundById(String courseId){
        ArrayList<String> allStudentId = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allStudentId.add(Info[1]);
            }
            bufferedReader.close();
            return !allStudentId.contains(courseId);

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void setStudentCourse(String studentId, String courseId){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    map.put(studentId, "null");
                    Info[1] = map.toString();
                    String last = Info[0] + "//" + Info[1];
                    allOfFile.add(last);
                    isSet = true;
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            if (isSet){
                courseWriter(allOfFile);
            }else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(studentId, "null");
                allOfFile.add(courseId + "//" + hashMap.toString());
                courseWriter(allOfFile);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setStudentScore(String studentId, String courseId, double score){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    map.replace(studentId, String.valueOf(score));
                    Info[1] = map.toString();
                    String last = Info[0] + "//" + Info[1];
                    allOfFile.add(last);
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            courseWriter(allOfFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public int getCourseCredit(String courseId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(courseId)){
                    return Integer.parseInt(Info[2]);
                }
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public double printAverage(String studentId, String kind){
        if (kind.equals("current")){
            double sum = 0.0;
            int courseCredit = 0;
            try {
                FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] Info = line.split("//");
                    int credit = getCourseCredit(Info[0]);
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    if (map.containsKey(studentId) && !map.get(studentId).equals("null")){
                        courseCredit += credit;
                        String score = map.get(studentId);
                        sum += (Double.parseDouble(score) * credit) ;
                    }
                }
                bufferedReader.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if (courseCredit == 0){
                return -1;
            }else {
                return (sum/courseCredit);
            }
        } else if (kind.equals("total")) {
            double sum = 0.0;
            int courseCredit = 0;
            try {
                FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] Info = line.split("//");
                    int credit = getCourseCredit(Info[0]);
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    if (map.containsKey(studentId)){
                        courseCredit += credit;
                        String score = map.get(studentId);
                        if (!score.equals("null")){
                            sum += (Double.parseDouble(score) * credit);
                        }
                    }
                }
                bufferedReader.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            if (courseCredit == 0){
                return -1;
            }else {
                return (sum/courseCredit);
            }
        }else {
            return -1;
        }
    }

    public String printAllCourse(String studentId){
        ArrayList<String> allCourseId = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                HashMap<String, String> map = new HashMap<>();
                String[] keyValuePairs = mapAsString.split(",");
                for (String pair : keyValuePairs) {
                    String[] entry = pair.split("=");
                    map.put(entry[0], entry[1]);
                }
                if (map.containsKey(studentId)){
                    allCourseId.add(Info[0]);
                }
            }
            bufferedReader.close();
            if (allCourseId.isEmpty()){
                return "null";
            }else {
                StringBuilder out = new StringBuilder();
                out.append("Course name : Course ID\n");
                for (String s : allCourseId) {
                    out.append(courseNameById(s));
                    out.append(" :\t");
                    out.append(s);
                    out.append("\n");
                }
                return out.substring(0,out.length()-1);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "null";
    }

    public int printAllCredit(String studentId){
        int courseCredit = 0;
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                int credit = getCourseCredit(Info[0]);
                String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                HashMap<String, String> map = new HashMap<>();
                String[] keyValuePairs = mapAsString.split(",");
                for (String pair : keyValuePairs) {
                    String[] entry = pair.split("=");
                    map.put(entry[0], entry[1]);
                }
                if (map.containsKey(studentId)){
                    courseCredit += credit;
                }
            }
            bufferedReader.close();
            return courseCredit;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public boolean dateHasValidPattern(String date){
        String pattern = "^(\\d{4})/(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])$"; // Pattern for yyyy/mm/dd format
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(date);
        return m.find();
    }

    public boolean hourHasValidPattern(String hour){
        String pattern = "^(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; // Pattern for hh:mm format
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(hour);
        return m.find();
    }

    public void addAssignment(String assignmentName, String isActive, String dateOfDeadline, String hourOfDeadline){
        String assignmentId = "";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment_num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int code = Integer.parseInt(line);
            code++;
            assignmentId = String.valueOf(code);
            bufferedReader.close();
            try {//writing new code
                FileWriter writer = new FileWriter("daneshjooyar/informations/assignment_num.txt");
                writer.write(assignmentId);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            String last = assignmentId + "//" + isActive + "//" + assignmentName + "//" + dateOfDeadline + "," + hourOfDeadline + "\n" ;
            allOfFile.add(last);
            assignmentWriter(allOfFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean noAssignmentFoundById(String assignmentId){
        ArrayList<String> allAssignmentId = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allAssignmentId.add(Info[0]);
            }
            bufferedReader.close();
            return !allAssignmentId.contains(assignmentId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void setAssignmentForCourse(String assignmentId, String courseId){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    set.add(assignmentId);
                    String last = Info[0] + "//" + set.toString();
                    allOfFile.add(last);
                    isSet = true;
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            if (!isSet){
                Set<String> set = new HashSet<>();
                set.add(assignmentId);
                String last = courseId + "//" + set.toString();
                allOfFile.add(last);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/course_assignment.txt");
    }

    public void writer(ArrayList<String> allOfFile, String address){
        try {
            FileWriter writer = new FileWriter(address);
            for (String s : allOfFile) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void assignmentWriter(ArrayList<String> allOfFile){
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/assignment.txt");
            for (String s : allOfFile) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setExamDate(String courseId, String examDate, String examHour){
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    Info[1] = examDate;
                    Info[2] = examHour;
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2];
                    allOfFile.add(last);
                    isSet = true;
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            if (!isSet){
                allOfFile.add(courseId + "//" + examDate + "//" + examHour);
            }
            examWriter(allOfFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void examWriter(ArrayList<String> allOfFile){
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/exam.txt");
            for (String s : allOfFile) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeStudentCourse(String studentId, String courseId){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    map.remove(studentId);
                    Info[1] = map.toString();
                    String last = Info[0] + "//" + Info[1];
                    allOfFile.add(last);
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            courseWriter(allOfFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean studentHasCourse(String studentId, String courseId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    if (map.containsKey(studentId)){
                        return true;
                    }
                }
            }
            bufferedReader.close();
            return false;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void courseWriter(ArrayList<String> allOfFile){
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/student_course_score.txt");
            for (String s : allOfFile) {
                writer.write(s + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
