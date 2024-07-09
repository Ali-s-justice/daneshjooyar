package MVC_PROJECT;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentModel {

    public String[] obligationSplitter(String inputString) {
        return inputString.split("//");
    }

    public String[] getObligationRemover(String[] splitInputString) {
        ArrayList<String> temp = new ArrayList<>(List.of(splitInputString));
        if (temp.size() == 1) {
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

    public String studentIdByUsername(String username) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[2].equals(username)) {
                    return Info[1];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String studentUsernameByID(String ID) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(ID)) {
                    return Info[2];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String deleteAccount(String studentId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)) {
                    Info[2] = "$";
                    Info[3] = "$";
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            writer(allOfFile, "daneshjooyar/informations/students.txt");
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "500";//successful
    }

    public ArrayList<String> worseScoreAndCourse(String studentId) {
        HashMap<String, String> studentCourseScore = new HashMap<>();
        studentCourseScore.put(" ", "0");
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
                    studentCourseScore.put(Info[0], map.get(studentId));
                }
            }
            bufferedReader.close();
            if (studentCourseScore.size() > 1){
                studentCourseScore.remove(" ");
                double minValue = Integer.MAX_VALUE;
                String minKey = null;

                for (Map.Entry<String, String > entry : studentCourseScore.entrySet()) {
                    if (Double.parseDouble(entry.getValue()) < minValue) {
                        minValue = Double.parseDouble(entry.getValue());
                        minKey = entry.getKey();
                    }
                }
                ArrayList<String> returnValue = new ArrayList<>();
                returnValue.add(String.valueOf(minValue));
                returnValue.add(courseNameById(minKey));
                return returnValue;
            }else {
                ArrayList<String> returnValue = new ArrayList<>();
                returnValue.add("-");
                returnValue.add("-");
                return returnValue;
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public ArrayList<String> bestScoreAndCourse(String studentId) {
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

            if (studentCourseScore.size() > 1){
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
                if (Objects.equals(maxKey, " ")) {
                    returnValue.add(maxKey);
                } else {
                    returnValue.add(courseNameById(maxKey));
                }
                return returnValue;
            }else {
                ArrayList<String> returnValue = new ArrayList<>();
                returnValue.add("-");
                returnValue.add("-");
                return returnValue;
            }
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
                    if (dateSituation(examDateAndTime) == 1) {
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

    public boolean datePassed(String dateAndTime) {
        String dateTimePattern = "yyyy/MM/dd,HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
        try {
            Date dateTime = sdf.parse(dateAndTime);
            Date currentDateTime = new Date();
            return dateTime.before(currentDateTime);
        } catch (ParseException e) {
            System.out.println("Error parsing the date and time: " + e.getMessage());
        }
        return true;
    }

    public int dateSituation(String dateAndTime) {//1: date is in future 0: date is now -1: date is before
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

    public String assignmentDeadlineGetter(String assignmentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    return Info[3];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "0000/00/00,00:00";
    }

    public boolean assignmentIsTrue(String assignmentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    return Boolean.parseBoolean(Info[1]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean assignmentIsFalse(String assignmentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    return !(Boolean.parseBoolean(Info[1]));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
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

    public String getCourseExamDateAndTime(String courseId) {
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

    public boolean studentAlreadySignedUp(String studentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)) {
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
                if (Info[1].equals(studentId)) {
                    Info[2] = username;
                    Info[3] = hashPassword(password);
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            writer(allOfFile, "daneshjooyar/informations/students.txt");
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> studentChangedAssignmentDeadlineNameGetter(String studentId) {
        Set<String> allAssignmentId = allChangedDeadlineIdGetter();
        Set<String> studentAssignment = studentTrueAndHasTimeAssignmentId(studentId);
        Set<String> retained = new HashSet<>(studentAssignment);
        retained.retainAll(allAssignmentId);
        ArrayList<String> retainedId = new ArrayList<>(retained);
        ArrayList<String> retainedNames = new ArrayList<>();
        for (String s : retainedId) {
            retainedNames.add(assignmentNameGetter(s));
        }
        if (retainedNames.isEmpty()){
            retainedNames.add("404");
        }
        return retainedNames;
    }

    public Set<String> allChangedDeadlineIdGetter() {
        Set<String> allAssignmentId = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment_change_deadline.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allAssignmentId.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allAssignmentId;
    }

    public String studentNameById(String studentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)) {
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

    public synchronized void writer(ArrayList<String> allOfFile, String address) {
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

    public String studentLoginWay(String loginInput) {
        if (doWithID(loginInput)) {
            return "loginWithID";
        } else {
            return "loginWithUsername";
        }
    }

    public String studentInfoWay(String way) {
        if (doWithID(way)) {
            return "ID";
        } else {
            return "username";
        }
    }

    public boolean doWithID(String loginInput) {
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

    public boolean passwordIsWrongForId(String studentId, String password) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)) {
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

    public void changeUsername(String studentId, String newUsername) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)) {
                    Info[2] = newUsername;
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/students.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(String studentId, String newPassword) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(studentId)) {
                    Info[3] = hashPassword(newPassword);
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                } else {
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

    public int doneAssignmentNum(String studentId) {
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

//    public static ArrayList<String> stringToArrayList(String str) {
//        str = str.substring(1, str.length() - 1);
//        String[] items = str.split(", ");
//        return new ArrayList<>(Arrays.asList(items));
//    }

    public String newsHappenLinkGetterFromFile() {
        String returnValue = " ";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/linkes.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            line = bufferedReader.readLine();
            bufferedReader.close();
            return line;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }

    public LinkedHashMap<String, String> newsHappenLinkGetter() {
        String url = "https://www.sbu.ac.ir/";
        LinkedHashMap<String, String> announcementMap = new LinkedHashMap<>();
        LinkedHashMap<String, String> clearedAnnouncement = new LinkedHashMap<>();

        try {
            Document document = Jsoup.connect(url).get();
            Elements announcements = document.select("section.sp-announcement.pt-5.pb-5");
            for (Element announcement : announcements) {
                Elements links = announcement.select("a");
                for (Element link : links) {
                    String title = link.text();
                    String href = link.attr("abs:href");
                    announcementMap.put(title, href);
                }
            }

            for (String key : announcementMap.keySet()) {
                if (key.length() > 17) {
                    clearedAnnouncement.put(key, announcementMap.get(key));
                }
            }

        } catch (IOException e) {
            System.out.println("Error");
        }
        return clearedAnnouncement;
    }

    public ArrayList<String> newsForYouAssignmentNameGetter(String studentId) {
        Set<String> studentTrueAndHasTimeAssignmentId = studentTrueAndHasTimeAssignmentId(studentId);
        ArrayList<String> assignmentId = new ArrayList<>(studentTrueAndHasTimeAssignmentId);
        ArrayList<String> under24HourAssignmentName = new ArrayList<>();
        for (int i = 0; i < assignmentId.size(); i++) {
            try {
                FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] Info = line.split("//");
                    if (assignmentId.contains(Info[0])) {
                        if (under24HourRemain(Info[3])) {
                            under24HourAssignmentName.add(Info[2]);
                        }
                    }
                }
                bufferedReader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (under24HourAssignmentName.isEmpty()){
            under24HourAssignmentName.add("404");
        }
        Set<String> stringSet = new HashSet<>(under24HourAssignmentName);
        return new ArrayList<>(stringSet);
    }

    public boolean under24HourRemain(String inputDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(inputDateTime, formatter);
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(now, dateTime);
        return duration.toHours() < 24 && duration.toMillis() > 0;
    }

    public ArrayList<Long> dateOfEndGetter(String studentId) {
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
        if (maxDateTime == null) {
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

    public ArrayList<String> assignmentAllDeadlineGetter(Set<String> assignmentId) {
        ArrayList<String> allAssignmentDeadline = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (assignmentId.contains(Info[0])) {
                    allAssignmentDeadline.add(Info[3]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allAssignmentDeadline;
    }

    public ArrayList<String> allExamDateGetter(ArrayList<String> courseId) {
        ArrayList<String> allExamDate = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (courseId.contains(Info[0])) {
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

    public void jobIdSetter() {
        try (BufferedReader reader = new BufferedReader(new FileReader("daneshjooyar/informations/job_num.txt"))) {
            String code;
            code = reader.readLine();
            if (code == null) {
                try {
                    FileWriter writer = new FileWriter("daneshjooyar/informations/job_num.txt");
                    writer.write("2000");
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Map<String, String> studentSaraJobGetter(String studentId) {
        Map<String, String> returnValue = studentJobGetter(studentId);
        if (returnValue.size() >= 4) {
            int count = 0;
            Iterator<Map.Entry<String, String>> iterator = returnValue.entrySet().iterator();
            while (iterator.hasNext()) {
                if (count >= 3) {
                    iterator.next();
                    iterator.remove();
                } else {
                    count++;
                }
            }
        }
        return returnValue;
    }

    public Map<String, String> studentJobGetter(String studentId) {
        Set<String> allJobId = studentJobSetMaker(studentId);
        Set<String> notDoneJob = notDoneJobFinder(allJobId);
        ArrayList<String> notDoneJobList = new ArrayList<>(notDoneJob);
        return jobCaptionMapGetter(notDoneJobList);
    }

    public Map<String, String> jobCaptionMapGetter(ArrayList<String> allJobId) {
        Map<String, String> returnValue = new HashMap<>();
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            return null;
        }
        for (String s : allJobId) {
            try {
                if (allOfFile.contains("//" + s + "//")) {
                    for (int j = 0; j < allOfFile.size(); j++) {
                        if (allOfFile.get(j).equals("//" + s + "//")) {
                            ArrayList<String> caption = new ArrayList<>();
                            j++;
                            while (j < allOfFile.size() && captionJobIdPatternChecker(allOfFile.get(j))) {
                                caption.add(allOfFile.get(j));
                                j++;
                            }
                            String title = caption.getFirst();
                            StringBuilder total = new StringBuilder();
                            for (int k = 1; k < caption.size(); k++) {
                                total.append(caption.get(k));
                                total.append("\n");
                            }
                            String description = total.substring(0, total.length() - 1);
                            returnValue.put(title, description);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return returnValue;
    }

    public Set<String> notDoneJobFinder(Set<String> allJobId) {
        Set<String> notDoneJob = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (allJobId.contains(Info[0])) {
                    if (Info[1].equals("false")) {
                        notDoneJob.add(Info[0]);
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return notDoneJob;
    }

    public boolean captionJobIdPatternChecker(String input) {

        String pattern = "//\\d{4}//";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return !m.find();

    }

    public Set<String> studentJobSetMaker(String studentId) {
        Set<String> returnValue = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(studentId)) {
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    returnValue = set;
                    break;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }

    public ArrayList<String> doneAssignmentNameGetter(String studentId) {
        Set<String> doneAssignmentIdSet = doneAssignmentSetMaker(studentId);
        ArrayList<String> doneAssignmentIdArray = new ArrayList<>(doneAssignmentIdSet);
        ArrayList<String> returnValue = new ArrayList<>();
        for (String s : doneAssignmentIdArray) {
            returnValue.add(assignmentNameGetter(s));
        }
        return returnValue;
    }

    public String assignmentNameGetter(String assignmentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    return Info[2];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Set<String> doneAssignmentSetMaker(String studentId) {
        Set<String> returnValue = new HashSet<>();
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
                    returnValue = set;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return returnValue;
    }

    public ArrayList<String> newsBirthdayGetter() {
        ArrayList<String> names = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_birthday.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (isBirthdayToday(Info[1])) {
                    names.add(studentNameById(Info[0]));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (names.isEmpty()){
            names.add("404");
        }
        return names;
    }

    public boolean isBirthdayToday(String birthdayStr) {
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return today.getMonth() == birthday.getMonth() && today.getDayOfMonth() == birthday.getDayOfMonth();
    }

    public ArrayList<ArrayList<String>> jobNotDonePage(String studentId) {
        Set<String> allJobId = studentJobSetMaker(studentId);
        Set<String> notDoneJob = notDoneJobFinder(allJobId);
        ArrayList<String> notDoneJobList = new ArrayList<>(notDoneJob);
        return jobCaptionAndDeadlineArrayGetter(notDoneJobList);
    }

    public ArrayList<ArrayList<String>> jobCaptionAndDeadlineArrayGetter(ArrayList<String> allJobId) {
        ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            return null;
        }
        for (String s : allJobId) {
            try {
                if (allOfFile.contains("//" + s + "//")) {
                    for (int j = 0; j < allOfFile.size(); j++) {
                        if (allOfFile.get(j).equals("//" + s + "//")) {
                            ArrayList<String> caption = new ArrayList<>();
                            j++;
                            while (j < allOfFile.size() && captionJobIdPatternChecker(allOfFile.get(j))) {
                                caption.add(allOfFile.get(j));
                                j++;
                            }
                            ArrayList<String> temp = getStrings(caption);
                            temp.add(jobDeadlineGetter(s));
                            temp.add(s);
                            String time = temp.get(2);
                            String[] times = time.split("//");
                            temp.set(2, times[0]);
                            temp.add(3, times[1]);
                            temp.add(4, times[2]);
                            returnValue.add(temp);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (returnValue.isEmpty()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("404");
            for (int i = 0; i < 4; i++) {
                temp.add(" ");
            }
            returnValue.add(temp);
        }
        return returnValue;
    }

    public String jobDeadlineGetter(String jobId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (jobId.equals(Info[0])) {
                    return leftTime(Info[2]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public String leftTime(String deadline) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd,HH:mm");
        LocalDateTime inputDate = LocalDateTime.parse(deadline, formatter);
        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isAfter(inputDate)) {
            return "0//0//0";
        } else {
            long days = ChronoUnit.DAYS.between(currentDate, inputDate);
            long hours = ChronoUnit.HOURS.between(currentDate, inputDate) % 24;
            long minutes = ChronoUnit.MINUTES.between(currentDate, inputDate) % 60;

            return days + "//" + hours + "//" + minutes;
        }
    }

    public String addJob(String deadline, String studentId, String title, String caption) {
        String jobId = jobIdMaker();
        jobFileAdder(jobId, deadline);
        addJobToStudent(studentId, jobId);
        setCaptionForJob(jobId, title, caption);
        return "successful";
    }

    public void setCaptionForJob(String jobId, String title, String caption) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/job_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            ArrayList<String> newAllOfFile = new ArrayList<>();
            if (allOfFile.contains("//" + jobId + "//")) {
                for (int i = 0; i < allOfFile.size(); i++) {
                    if (allOfFile.get(i).equals("//" + jobId + "//")) {
                        newAllOfFile.add(allOfFile.get(i));
                        newAllOfFile.add(title);
                        newAllOfFile.add(caption);
                        while (i < allOfFile.size() - 1 && captionJobIdPatternChecker(allOfFile.get(i + 1))) {
                            i++;
                        }
                    } else {
                        newAllOfFile.add(allOfFile.get(i));
                    }
                }
            } else {
                newAllOfFile = allOfFile;
                newAllOfFile.add("//" + jobId + "//");
                newAllOfFile.add(title);
                newAllOfFile.add(caption);
            }
            writer(newAllOfFile, "daneshjooyar/informations/job_caption.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void addJobToStudent(String studentId, String jobId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(studentId)) {
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    set.add(jobId);
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
                set.add(jobId);
                String last = studentId + "//" + set;
                allOfFile.add(last);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/student_job.txt");
    }

    public void jobFileAdder(String jobId, String deadline) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            allOfFile.add(jobId + "//" + "false" + "//" + deadline);
            writer(allOfFile, "daneshjooyar/informations/job.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String jobIdMaker() {
        String jobId = "";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job_num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int code = Integer.parseInt(line);
            code++;
            jobId = String.valueOf(code);
            bufferedReader.close();
            try {//writing new code
                FileWriter writer = new FileWriter("daneshjooyar/informations/job_num.txt");
                writer.write(jobId);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jobId;
    }

    public String setJobDone(String jobId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(jobId)) {
                    Info[1] = "true";
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/job.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "successful";
    }

    public void deleteJobFromJobTxt(String jobId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!Info[0].equals(jobId)) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/job.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteJobFromStudentJobTxt(String studentId, String jobId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(studentId)) {
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    set.remove(jobId);
                    String last = Info[0] + "//" + set;
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/student_job.txt");
    }

    public void deleteJobFromJobCaptionTxt(String jobId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            return;
        }
        ArrayList<String> newAllOfFile = new ArrayList<>();
        try {
            if (allOfFile.contains("//" + jobId + "//")) {
                for (int j = 0; j < allOfFile.size(); j++) {
                    if (allOfFile.get(j).equals("//" + jobId + "//")) {
                        do {
                            j++;
                        } while (j < allOfFile.size() && captionJobIdPatternChecker(allOfFile.get(j)));
                    } else {
                        newAllOfFile.add(allOfFile.get(j));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(newAllOfFile, "daneshjooyar/informations/job_caption.txt");
    }

    public String deleteJob(String studentId, String jobId) {
        deleteJobFromJobTxt(jobId);
        deleteJobFromStudentJobTxt(studentId, jobId);
//        deleteJobFromJobCaptionTxt(jobId);
        return "successful";
    }

    public String editJob(String deadline, String title, String caption, String jobId) {
        editJobFile(jobId, deadline);
        setCaptionForJob(jobId, title, caption);
        return "successful";
    }

    public void editJobFile(String jobId, String deadline) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(jobId)) {
                    Info[2] = deadline;
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/job.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<ArrayList<String>> doneJob(String studentId) {
        Set<String> allJobId = studentJobSetMaker(studentId);
        Set<String> doneJob = doneJobFinder(allJobId);
        ArrayList<String> doneJobList = new ArrayList<>(doneJob);
        ArrayList<ArrayList<String>> returnValue = jobTitleAndCaptionArrayGetter(doneJobList);
        if (returnValue.isEmpty()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("404");
            temp.add(" ");
            returnValue.add(temp);
        }
        return returnValue;
    }

    public Set<String> doneJobFinder(Set<String> allJobId) {
        Set<String> doneJob = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (allJobId.contains(Info[0])) {
                    if (Info[1].equals("true")) {
                        doneJob.add(Info[0]);
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return doneJob;
    }

    public ArrayList<ArrayList<String>> jobTitleAndCaptionArrayGetter(ArrayList<String> allJobId) {
        ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/job_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            return null;
        }
        for (String s : allJobId) {
            try {
                if (allOfFile.contains("//" + s + "//")) {
                    for (int j = 0; j < allOfFile.size(); j++) {
                        if (allOfFile.get(j).equals("//" + s + "//")) {
                            ArrayList<String> caption = new ArrayList<>();
                            j++;
                            while (j < allOfFile.size() && captionJobIdPatternChecker(allOfFile.get(j))) {
                                caption.add(allOfFile.get(j));
                                j++;
                            }
                            ArrayList<String> temp = getStrings(caption);
                            returnValue.add(temp);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return returnValue;
    }

    private static ArrayList<String> getStrings(ArrayList<String> caption) {
        String title = caption.getFirst();
        StringBuilder total = new StringBuilder();
        for (int k = 1; k < caption.size(); k++) {
            total.append(caption.get(k));
            total.append("\n");
        }
        String description = total.substring(0, total.length() - 1);
        ArrayList<String> temp = new ArrayList<>();
        temp.add(title);
        temp.add(description);
        return temp;
    }

    private ArrayList<String> allStudentCourseId(String studentId){
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
                if (map.containsKey(studentId)) {
                    allCourseId.add(Info[0]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allCourseId;
    }

    private String courseTeacherNameById(String courseId){
        String teacherId = "";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(courseId)){
                    teacherId = Info[3];
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return teacherNameById(teacherId);
    }

    private String teacherNameById(String teacherId){
        String teacherName = "-";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[3].equals(teacherId)){
                    return Info[0];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return teacherName;
    }

    private String getExamDate(String courseId){
        String examDate = "-";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    return Info[1];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return examDate;
    }

    private String studentScoreGetter(String studentId, String courseId){
        String score = "-";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    if (!map.get(studentId).equals("null")){
                        return map.get(studentId);
                    }else {
                        return score;
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return score;
    }

    private int activeAssignmentNum(String courseId){
        int assignmentNum = 0;
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    ArrayList<String> assignmentId = new ArrayList<>(set);
                    for (String s : assignmentId) {
                        if (assignmentIsTrue(s)) {
                            assignmentNum++;
                        }
                    }
                    return assignmentNum;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return assignmentNum;
    }

    private String dayOfWeekGetter(String symbol){
        return switch (symbol) {
            case "sh" -> "شنبه";
            case "y" -> "یکشنبه";
            case "d" -> "دوشنبه";
            case "se" -> "سه شنبه";
            case "c" -> "چهارشنبه";
            case "p" -> "پنجشنبه";
            case "j" -> "جمعه";
            default -> "-";
        };
    }

    private ArrayList<ArrayList<String>> stringToArrayOfArrayList(String string){
        ArrayList<ArrayList<String>> returnArraylist = new ArrayList<>();
        for (String innerListString : string.substring(1, string.length() - 1).split("], ")) {
            String[] elements = innerListString.substring(1).split(", ");
            ArrayList<String> innerList = new ArrayList<>(Arrays.asList(elements));
            returnArraylist.add(innerList);
        }
        return returnArraylist;
    }

    private String courseDateHappenGetter(String courseId){
        StringBuilder returnValue = new StringBuilder();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_date.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    ArrayList<ArrayList<String>> allDays = stringToArrayOfArrayList(Info[1].substring(0, Info[1].length()-1));
                    for (ArrayList<String> allDay : allDays) {
                        String day = dayOfWeekGetter(allDay.getFirst());
                        returnValue.append(day).append(" ساعت ").append(allDay.getLast()).append(" و ");
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (returnValue.isEmpty()){
            return "-";
        }
        return returnValue.substring(0,returnValue.length()-3);
    }

    public ArrayList<ArrayList<String>> classes(String studentId){
        ArrayList<String> allCourseId = allStudentCourseId(studentId);
        ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
        for (String string : allCourseId) {
            ArrayList<String> allCourseInfo = new ArrayList<>();
            String courseName = courseNameById(string);
            String teacherName = courseTeacherNameById(string);
            int courseCredit = getCourseCredit(string);
            String examDate = getExamDate(string);
            String score = studentScoreGetter(studentId, string);
            int activeAssignmentNum = activeAssignmentNum(string);
            String courseDateHappen = courseDateHappenGetter(string);
            allCourseInfo.add(courseName);
            allCourseInfo.add(teacherName);
            allCourseInfo.add(String.valueOf(courseCredit));
            allCourseInfo.add(examDate);
            allCourseInfo.add(score);
            allCourseInfo.add(String.valueOf(activeAssignmentNum));
            allCourseInfo.add(courseDateHappen);
            allCourseInfo.add(string);//course ID
            returnValue.add(allCourseInfo);
        }
        if (returnValue.isEmpty()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("404");
            for (int i = 0; i < 7; i++) {
                temp.add(" ");
            }
            returnValue.add(temp);
        }
        return returnValue;
    }

    public boolean noCourseFoundById(String courseId) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addCourse(String studentId, String courseId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
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
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            if (isSet) {
                writer(allOfFile, "daneshjooyar/informations/student_course_score.txt");
            } else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(studentId, "null");
                allOfFile.add(courseId + "//" + hashMap);
                writer(allOfFile, "daneshjooyar/informations/student_course_score.txt");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean studentHasCourse(String studentId, String courseId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    if (map.containsKey(studentId)) {
                        return true;
                    }
                }
            }
            bufferedReader.close();
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public String deleteCourse(String studentId, String courseId){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    map.remove(studentId);
                    if (!map.isEmpty()){
                        Info[1] = map.toString();
                        String last = Info[0] + "//" + Info[1];
                        allOfFile.add(last);
                    }
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/student_course_score.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "successful";
    }

    public ArrayList<ArrayList<String>> weekPlanner(String studentId){
        ArrayList<String> allCourseId = allStudentCourseId(studentId);
        ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
        for (String string : allCourseId) {
            ArrayList<ArrayList<String>> temp;
            temp = weekPlannerHappenGetter(string);
            returnValue.addAll(temp);
        }
        if (returnValue.isEmpty()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("404");
            temp.add(" ");
            temp.add(" ");
            returnValue.add(temp);
        }
        return returnValue;
    }

    private ArrayList<ArrayList<String>> weekPlannerHappenGetter(String courseId){
        ArrayList<ArrayList<String>> allDays = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_date.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)){
                    allDays = stringToArrayOfArrayList(Info[1].substring(0, Info[1].length()-1));
                    for (ArrayList<String> allDay : allDays) {
                        String day = dayOfWeekGetter(allDay.getFirst());
                        allDay.set(0,day);
                        allDay.add(1, courseNameById(courseId));
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allDays;
    }

    public ArrayList<ArrayList<String>> notDoneAssignmentPage(String studentId, String date, String sortWay){
        Set<String> trueAndHasTime = studentTrueAndDateDeadlineAssignmentId(studentId, date);
        ArrayList<String> allAssignmentId = new ArrayList<>(trueAndHasTime);
        ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
        for (String s : allAssignmentId) {
            ArrayList<String> temp = new ArrayList<>();
            String assignmentName = assignmentNameGetter(s);
            double avrEstimateTime = avrEstimateTimeFinder(s);
            String teacherCaption = assignmentCaptionGetter(s);
            String studentCaption = studentCaptionGetter(s, studentId);
            String assignmentDeadline = assignmentDeadlineGetter(s);
            String leftTime = leftTime(assignmentDeadline);
            String[] leftTimes = leftTime.split("//");
            temp.add(assignmentName);
            temp.add(String.valueOf(avrEstimateTime));
            temp.add(teacherCaption);
            temp.add(studentCaption);
            temp.add(leftTimes[0]);
            temp.add(leftTimes[1]);
            temp.add(leftTimes[2]);
            temp.add(s);//assignment id
            if (Integer.parseInt(leftTimes[0]) == 0 && Integer.parseInt(leftTimes[1]) == 0 && Integer.parseInt(leftTimes[2]) == 0){
                continue;
            }
            returnValue.add(temp);
        }
        if (sortWay.equals("1")){//estimate
            returnValue = sortArrayByEstimate(returnValue);
        }else if (sortWay.equals("0")){//deadline
            returnValue = sortArrayByDeadline(returnValue);
        }
        if (returnValue.isEmpty()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("404");
            for (int i = 0; i < 7; i++) {
                temp.add(" ");
            }
            returnValue.add(temp);
        }
        return returnValue;
    }

    public ArrayList<ArrayList<String>> sortArrayByDeadline(ArrayList<ArrayList<String>> arrayList){
        for (int i = arrayList.size()-1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (Integer.parseInt(arrayList.get(j).get(4)) > Integer.parseInt(arrayList.get(j + 1).get(4))){
                    ArrayList<String> temp = arrayList.get(j);
                    arrayList.set(j, arrayList.get(j + 1));
                    arrayList.set((j+1), temp);
                } else if (Integer.parseInt(arrayList.get(j).get(4)) == Integer.parseInt(arrayList.get(j + 1).get(4))) {
                    if (Integer.parseInt(arrayList.get(j).get(5)) > Integer.parseInt(arrayList.get(j + 1).get(5))){
                        ArrayList<String> temp = arrayList.get(j);
                        arrayList.set(j, arrayList.get(j + 1));
                        arrayList.set((j+1), temp);
                    } else if (Integer.parseInt(arrayList.get(j).get(5)) == Integer.parseInt(arrayList.get(j + 1).get(5))) {
                        if (Integer.parseInt(arrayList.get(j).get(6)) > Integer.parseInt(arrayList.get(j + 1).get(6))){
                            ArrayList<String> temp = arrayList.get(j);
                            arrayList.set(j, arrayList.get(j + 1));
                            arrayList.set((j+1), temp);
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> sortArrayByEstimate(ArrayList<ArrayList<String>> arrayList){
        for (int i = arrayList.size()-1; i > 0 ; i--) {
            for (int j = 0; j < i; j++) {
                if (Double.parseDouble(arrayList.get(j).get(1)) > Double.parseDouble(arrayList.get(j + 1).get(1))){
                    ArrayList<String> temp = arrayList.get(j);
                    arrayList.set(j, arrayList.get(j + 1));
                    arrayList.set((j+1), temp);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<ArrayList<String>> doneAssignmentPage(String studentId){
        Set<String> falseAndNoTime = studentFalseAndPassedDeadlineAssignmentId(studentId);
        ArrayList<String> allAssignmentId = new ArrayList<>(falseAndNoTime);
        ArrayList<ArrayList<String>> returnValue = new ArrayList<>();
        for (String s : allAssignmentId) {
            ArrayList<String> temp = new ArrayList<>();
            String assignmentName = assignmentNameGetter(s);
            double avrEstimateTime = avrEstimateTimeFinder(s);
            String teacherCaption = assignmentCaptionGetter(s);
            String studentCaption = studentCaptionGetter(s, studentId);
            temp.add(assignmentName);
            temp.add(String.valueOf(avrEstimateTime));
            temp.add(teacherCaption);
            temp.add(studentCaption);
            temp.add(s);//assignment id
            returnValue.add(temp);
        }
        if (returnValue.isEmpty()){
            ArrayList<String> temp = new ArrayList<>();
            temp.add("404");
            for (int i = 0; i < 4; i++) {
                temp.add(" ");
            }
            returnValue.add(temp);
        }
        return returnValue;
    }

    public String studentCaptionGetter(String assignmentId, String studentId){
        ArrayList<String> returnValue = new ArrayList<>();
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            if (allOfFile.contains("//" + assignmentId + "-" + studentId + "//")) {
                for (int i = 0; i < allOfFile.size(); i++) {
                    if (allOfFile.get(i).equals("//" + assignmentId + "-" + studentId + "//")) {
                        i++;
                        do {
                            returnValue.add(allOfFile.get(i));
                            i++;
                        }while (i < allOfFile.size() - 1 && captionAssignmentIdPatternChecker(allOfFile.get(i + 1)));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!returnValue.isEmpty()){
            StringBuilder value = new StringBuilder();
            for (String s: returnValue){
                value.append(s);
                value.append("$");
            }
            return value.substring(0, value.length()-1);
        }else {
            return "-";
        }
    }

    public void setCaptionForAssignmentByStudent(String assignmentId, String studentId, String caption) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            ArrayList<String> newAllOfFile = new ArrayList<>();
            if (allOfFile.contains("//" + assignmentId + "-" + studentId + "//")) {
                for (int i = 0; i < allOfFile.size(); i++) {
                    if (allOfFile.get(i).equals("//" + assignmentId + "-" + studentId + "//")) {
                        newAllOfFile.add(allOfFile.get(i));
                        newAllOfFile.add(caption);
                        while (i < allOfFile.size() - 1 && captionAssignmentIdPatternChecker(allOfFile.get(i + 1))) {
                            i++;
                        }
                    } else {
                        newAllOfFile.add(allOfFile.get(i));
                    }
                }
            } else {
                newAllOfFile = allOfFile;
                newAllOfFile.add("//" + assignmentId + "-" + studentId + "//");
                newAllOfFile.add(caption);
            }
            writer(newAllOfFile, "daneshjooyar/informations/student_caption.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String assignmentCaptionGetter(String assignmentId){
        ArrayList<String> returnValue = new ArrayList<>();
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            if (allOfFile.contains("//" + assignmentId + "//")) {
                for (int i = 0; i < allOfFile.size(); i++) {
                    if (allOfFile.get(i).equals("//" + assignmentId + "//")) {
                        i++;
                        while (i < allOfFile.size() - 1 && captionAssignmentIdPatternChecker(allOfFile.get(i + 1))) {
                            returnValue.add(allOfFile.get(i));
                            i++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!returnValue.isEmpty()){
            StringBuilder value = new StringBuilder();
            for (String s: returnValue){
                value.append(s);
                value.append("\n");
            }
            return value.substring(0, value.length()-1);
        }else {
            return "-";
        }
    }

    public boolean captionAssignmentIdPatternChecker(String input) {

        String pattern = "//\\d{5}//";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return !m.find();

    }


    public double avrEstimateTimeFinder(String assignmentId){
        ArrayList<Double> allEstimateTime = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/estimate_time.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    HashMap<String, String> map = mapMaker(Info);
                    ArrayList<String> valuesList = new ArrayList<>(map.values());
                    for (String s: valuesList){
                        allEstimateTime.add(Double.parseDouble(s));
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        double sum = 0;
        for (Double d: allEstimateTime){
            sum += d;
        }
        return (sum / allEstimateTime.size());
    }

    private static HashMap<String, String> mapMaker(String[] Info) {
        String mapAsString = Info[1].replaceAll("[{}\\s]", "");
        HashMap<String, String> map = new HashMap<>();
        String[] keyValuePairs = mapAsString.split(",");
        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=");
            map.put(entry[0], entry[1]);
        }
        return map;
    }

    public boolean dateIsOtherDate(String date1, String date2) {//1: date is in future 0: date is now -1: date is before
        return date1.equals(date2);
    }

    public Set<String> studentTrueAndDateDeadlineAssignmentId(String studentId, String date) {
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
                        assignmentDeadline = assignmentDeadline.substring(0, assignmentDeadline.length()-6);
                        if (dateIsOtherDate(assignmentDeadline, date)) {
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

    public double estimateTimeGetter(String studentId, String assignmentId){
        double estimateTime = 0;
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/estimate_time.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    HashMap<String, String> map = mapMaker(Info);
                    if (map.containsKey(studentId)){
                        estimateTime = Double.parseDouble(map.get(studentId));
                    }
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return estimateTime;
    }

    public ArrayList<String> beforeSetAssignment(String studentId, String assignmentId){
        String caption = studentCaptionGetter(assignmentId, studentId);
        double estimateTime = estimateTimeGetter(studentId, assignmentId);
        ArrayList<String> returnValue = new ArrayList<>();
        returnValue.add(caption);
        returnValue.add(String.valueOf(estimateTime));
        return returnValue;
    }

    public void setEstimateTimeByStudent(String studentId, String assignmentId, String estimateTime){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/estimate_time.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    HashMap<String, String> map = mapMaker(Info);
                    if (map.containsKey(studentId)){
                        map.replace(studentId, estimateTime);
                    }else {
                        map.put(studentId, estimateTime);
                    }
                    String last = Info[0] + "//" + map;
                    allOfFile.add(last);
                }else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/estimate_time.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public String setAssignment(String studentId, String assignmentId, String caption, String estimateTime){
        setCaptionForAssignmentByStudent(assignmentId, studentId, caption);
        setEstimateTimeByStudent(studentId, assignmentId, estimateTime);
        return "500";
    }

    public Set<String> studentFalseAndPassedDeadlineAssignmentId(String studentId) {
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
                    ArrayList<String> falseAssignment = new ArrayList<>();
                    for (String s : assignmentId) {
                        if (assignmentIsFalse(s)) {
                            falseAssignment.add(s);
                        }
                    }
                    for (String s : falseAssignment) {
                        String assignmentDeadline = assignmentDeadlineGetter(s);
                        if (datePassed(assignmentDeadline)) {
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






}
