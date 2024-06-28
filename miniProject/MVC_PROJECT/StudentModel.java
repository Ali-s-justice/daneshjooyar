package MVC_PROJECT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StudentModel {

    public String[] obligationSplitter(String inputString){
        return inputString.split("//");
    }

    public String[] getObligationRemover(String[] splitInputString){
        ArrayList<String> temp = new ArrayList<>(List.of(splitInputString));
        if (temp.size() == 1){
            return null;
        }
        temp.removeFirst();
        return temp.toArray(new String[0]);
    }

    public boolean noStudentFoundById(String studentId) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean noStudentFoundByUsername(String username) {
        ArrayList<String> allStudentUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allStudentUsername.add(Info[2]);
            }
            bufferedReader.close();
            return !allStudentUsername.contains(username);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String studentIdByUsername(String username){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[2].equals(username)){
                    return Info[1];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String studentUsernameByID(String ID){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(ID)){
                    return Info[2];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String deleteAccount(String studentId){
        ArrayList<String> allOfFile = new ArrayList<>();
        boolean successful = false;
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    Info[2] = "$";
                    Info[3] = "$";
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                    successful = true;
                }else {
                    allOfFile.add(line);
                }
            }
            writer(allOfFile, "daneshjooyar/informations/students.txt");
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (successful){
            return "successful";
        }else {
            return "unSuccessful";
        }
    }

    public ArrayList<String> worseScoreAndCourse(String studentId){
        HashMap<String, Double> studentCourseScore = new HashMap<>();
        studentCourseScore.put(" ", 0.00);
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
                if (map.containsKey(studentId) && !map.get(studentId).equals("null")) {
                    studentCourseScore.put(Info[0], Double.parseDouble(map.get(studentId)));
                }
            }
            bufferedReader.close();

            double minValue = Integer.MAX_VALUE;
            String minKey = null;

            for (Map.Entry<String, Double> entry : studentCourseScore.entrySet()) {
                if (entry.getValue() < minValue) {
                    minValue = entry.getValue();
                    minKey = entry.getKey();
                }
            }
            ArrayList<String> returnValue = new ArrayList<>();
            returnValue.add(String.valueOf(minValue));
            if (Objects.equals(minKey, " ")){
                returnValue.add(minKey);
            }else {
                returnValue.add(courseNameById(minKey));
            }
            return returnValue;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<String> bestScoreAndCourse(String studentId){
        HashMap<String, Double> studentCourseScore = new HashMap<>();
        studentCourseScore.put(" ", 0.00);
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
                if (map.containsKey(studentId) && !map.get(studentId).equals("null")) {
                    studentCourseScore.put(Info[0], Double.parseDouble(map.get(studentId)));
                }
            }
            bufferedReader.close();

            double maxValue = Integer.MIN_VALUE;
            String maxKey = null;

            for (Map.Entry<String, Double> entry : studentCourseScore.entrySet()) {
                if (entry.getValue() > maxValue) {
                    maxValue = entry.getValue();
                    maxKey = entry.getKey();
                }
            }
            ArrayList<String> returnValue = new ArrayList<>();
            returnValue.add(String.valueOf(maxValue));
            if (Objects.equals(maxKey, " ")){
                returnValue.add(maxKey);
            }else {
                returnValue.add(courseNameById(maxKey));
            }
            return returnValue;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    public String courseNameById(String courseId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(courseId)) {
                    return Info[0];
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public int allExamNum(String studentId) {//use same for exam num
        int examNum = 0;
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
                if (map.containsKey(studentId)) {
                    String examDateAndTime = getCourseExamDateAndTime(Info[0]);
                    if (dateSituation(examDateAndTime) == 1){
                        examNum++;
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return examNum;
    }

    public int allAssignmentNum(String studentId) {//use same for exam num
        int assignmentNum = 0;
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
                if (map.containsKey(studentId)) {
                    Set<String> allCourseAssignmentId = allCourseAssignmentId(Info[0]);
                    ArrayList<String> assignmentId = new ArrayList<>(allCourseAssignmentId);
                    ArrayList<String> trueAssignment = new ArrayList<>();
                    for (String s : assignmentId) {
                        if (assignmentIsTrue(s)) {
                            trueAssignment.add(s);
                        }
                    }
                    for (String s : trueAssignment) {
                        String assignmentDeadline = assignmentDeadlineGetter(s);
                        if (dateSituation(assignmentDeadline) == 1) {
                            assignmentNum++;
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return assignmentNum;
    }

    public int dateSituation(String dateAndTime){//1: date is in future 0: date is now -1: date is before
        String dateTimePattern = "yyyy/MM/dd,HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
        try {
            Date dateTime = sdf.parse(dateAndTime);
            Date currentDateTime = new Date();
            // Compare the parsed date and time with the current date and time
            if (dateTime.after(currentDateTime)) {
                return 1;
            } else if (dateTime.before(currentDateTime)) {
                return -1;
            } else {
                return 0;
            }
        } catch (ParseException e) {
            System.out.println("Error parsing the date and time: " + e.getMessage());
        }
        return -2;
    }

    public String assignmentDeadlineGetter(String assignmentId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)){
                    return Info[3];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0000/00/00,00:00";
    }

    public boolean assignmentIsTrue(String assignmentId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)){
                    return Boolean.parseBoolean(Info[1]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Set<String> allCourseAssignmentId(String courseId) {
        Set<String> set = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return set;
    }

    public String getCourseExamDateAndTime(String courseId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    return Info[1] + "," + Info[2];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0000/00/00";
    }

    //Signup Student Username Validation --> return true if username is unavailable
    public boolean studentUsernameNotValid(String Username) {
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

    public boolean studentAlreadySignedUp(String studentId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    return !Info[2].equals("$");
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void signupStudent(String studentId, String username, String password) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    Info[2] = username;
                    Info[3] = hashPassword(password);
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                }else {
                    allOfFile.add(line);
                }
            }
            writer(allOfFile, "daneshjooyar/informations/students.txt");
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String returnName(String studentId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    return Info[0];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int AllCreditGetter(String studentId) {
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
                if (map.containsKey(studentId)) {
                    courseCredit += credit;
                }
            }
            bufferedReader.close();
            return courseCredit;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public double printAverage(String studentId, String kind) {
        if (kind.equals("current")) {
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
                    if (map.containsKey(studentId) && !map.get(studentId).equals("null")) {
                        courseCredit += credit;
                        String score = map.get(studentId);
                        sum += (Double.parseDouble(score) * credit);
                    }
                }
                bufferedReader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (courseCredit == 0) {
                return -1;
            } else {
                return (sum / courseCredit);
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
                    if (map.containsKey(studentId)) {
                        courseCredit += credit;
                        String score = map.get(studentId);
                        if (!score.equals("null")) {
                            sum += (Double.parseDouble(score) * credit);
                        }
                    }
                }
                bufferedReader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (courseCredit == 0) {
                return -1;
            } else {
                return (sum / courseCredit);
            }
        } else {
            return -1;
        }
    }

    public int getCourseCredit(String courseId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(courseId)) {
                    return Integer.parseInt(Info[2]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
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

    public void writer(ArrayList<String> allOfFile, String address) {
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

    public String studentLoginWay(String loginInput){
        if (doWithID(loginInput)){
            return "loginWithID";
        }else {
            return "loginWithUsername";
        }
    }

    public String studentInfoWay(String way){
        if (doWithID(way)){
            return "ID";
        }else {
            return "username";
        }
    }

    public boolean doWithID(String loginInput){
        return loginInput.matches("\\d{9}");
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

    public boolean passwordIsWrongForId(String studentId, String password){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    String storedPassword = Info[3];
                    return !checkPassword(password, storedPassword);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void changeUsername(String studentId, String newUsername){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    Info[2] = newUsername;
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/students.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(String studentId, String newPassword){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)){
                    Info[3] = hashPassword(newPassword);
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/students.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setAssignmentDone(String assignmentId, String studentId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/done_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(studentId)) {
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    set.add(assignmentId);
                    String last = Info[0] + "//" + set;
                    allOfFile.add(last);
                    isSet = true;
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            if (!isSet) {
                Set<String> set = new HashSet<>();
                set.add(assignmentId);
                String last = studentId + "//" + set;
                allOfFile.add(last);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/done_assignment.txt");
    }

    public int doneAssignmentNum(String studentId){
        int doneAssignmentNum = 0;
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/done_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(studentId)) {
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    doneAssignmentNum = set.size();
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return doneAssignmentNum;
    }

    public ArrayList<Long> dateOfEndGetter(String studentId){
        Set<String> studentTrueAndHasTimeAssignmentId = studentTrueAndHasTimeAssignmentId(studentId);
        ArrayList<String> allAssignmentDeadline = assignmentAllDeadlineGetter(studentTrueAndHasTimeAssignmentId);
        ArrayList<String> studentCourseId = studentCourseId(studentId);
        ArrayList<String> allExamDate = allExamDateGetter(studentCourseId);
        ArrayList<String> allDate = new ArrayList<>();
        allDate.addAll(allAssignmentDeadline);
        allDate.addAll(allExamDate);

        LocalDateTime maxDateTime = null;

        for (String dateString : allDate) {
            LocalDateTime dateTime = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm"));
            if (maxDateTime == null || dateTime.isAfter(maxDateTime)) {
                maxDateTime = dateTime;
            }
        }
        if (maxDateTime == null){
            ArrayList<Long> returnValue = new ArrayList<>();
            returnValue.add(0L);
            returnValue.add(0L);
            returnValue.add(0L);
            return returnValue;
        }


        String inputDateTimeString = maxDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm"));
        LocalDateTime inputDateTime = LocalDateTime.parse(inputDateTimeString, DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm"));

        LocalDateTime currentDateTime = LocalDateTime.now();
        return getLongs(inputDateTime, currentDateTime);
    }

    private static ArrayList<Long> getLongs(LocalDateTime inputDateTime, LocalDateTime currentDateTime) {
        LocalDateTime maxRemainingTime = (inputDateTime.isAfter(currentDateTime)) ? inputDateTime : currentDateTime;

        long days = currentDateTime.until(maxRemainingTime, ChronoUnit.DAYS);
        long hours = currentDateTime.until(maxRemainingTime, ChronoUnit.HOURS) % 24;
        long minutes = currentDateTime.until(maxRemainingTime, ChronoUnit.MINUTES) % 60;

        ArrayList<Long> returnValue = new ArrayList<>();
        returnValue.add(days);
        returnValue.add(hours);
        returnValue.add(minutes);
        return returnValue;
    }

    public ArrayList<String> assignmentAllDeadlineGetter(Set<String> assignmentId){
        ArrayList<String> allAssignmentDeadline = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (assignmentId.contains(Info[0])){
                    allAssignmentDeadline.add(Info[3]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allAssignmentDeadline;
    }

    public ArrayList<String> allExamDateGetter(ArrayList<String> courseId){
        ArrayList<String> allExamDate = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (courseId.contains(Info[0])){
                    allExamDate.add(Info[1] + "," + Info[2]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allExamDate;
    }

    public Set<String> studentTrueAndHasTimeAssignmentId(String studentId) {
        Set<String> allAssignmentId = new HashSet<>();
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
                if (map.containsKey(studentId)) {
                    Set<String> allCourseAssignmentId = allCourseAssignmentId(Info[0]);
                    ArrayList<String> assignmentId = new ArrayList<>(allCourseAssignmentId);
                    ArrayList<String> trueAssignment = new ArrayList<>();
                    for (String s : assignmentId) {
                        if (assignmentIsTrue(s)) {
                            trueAssignment.add(s);
                        }
                    }
                    for (String s : trueAssignment) {
                        String assignmentDeadline = assignmentDeadlineGetter(s);
                        if (dateSituation(assignmentDeadline) == 1) {
                            allAssignmentId.add(s);
                        }
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allAssignmentId;
    }

    public ArrayList<String> studentCourseId(String studentId) {
        ArrayList<String> studentCourseId = new ArrayList<>();
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
                if (map.containsKey(studentId)) {
                    studentCourseId.add(Info[0]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return studentCourseId;
    }

}
