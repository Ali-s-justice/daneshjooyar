package MVC_PROJECT;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminModel {

    public boolean adminUsernameNotAvailable(String username) {//Admin Name Validation
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
            return AllAdminsName.contains(username);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean PasswordNotValidate(String password, String username) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(password);

        if (password.contains(username)) {
            return true;
        }
        return !m.matches();
    }

    public boolean saveAdmin(String userName, String password) {
        try {
            FileWriter AdminFileWriter = new FileWriter("daneshjooyar/informations/admins.txt", true);
            String HashPassword = hashPassword(password);
            AdminFileWriter.write(userName + "//" + HashPassword + "\n");
            AdminFileWriter.close();
            return true;
        } catch (Exception e) {
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
    public boolean adminPasswordIsWrong(String username, String password) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/admins.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(username)) {
                    return !checkPassword(password, Info[1]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return true;
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

    public String SignUpStudent(String StudentName, String Username, String Password) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/students.txt", true);
            writer.write(StudentName + "//" + student_code + "//" + Username + "//" + Password + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return student_code;
    }

    public boolean noStudentFound(String studentName) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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

    public void setStudentCourse(String studentId, String courseId) {
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

    public void setStudentScore(String studentId, String courseId, double score) {
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
                    map.replace(studentId, String.valueOf(score));
                    Info[1] = map.toString();
                    String last = Info[0] + "//" + Info[1];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/student_course_score.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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

    public String printAllCourse(String studentId) {
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
            if (allCourseId.isEmpty()) {
                return "null";
            } else {
                StringBuilder out = new StringBuilder();
                out.append("Course name : Course ID\n");
                for (String s : allCourseId) {
                    out.append(courseNameById(s));
                    out.append(" :\t");
                    out.append(s);
                    out.append("\n");
                }
                return out.substring(0, out.length() - 1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "null";
    }

    public int printAllCredit(String studentId) {
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

    public boolean dateHasValidPattern(String date) {
        String pattern = "^(\\d{4})/(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$"; // Pattern for yyyy/mm/dd format
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(date);
        return m.find();
    }

    public boolean hourHasValidPattern(String hour) {
        String pattern = "^(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9])$"; // Pattern for hh:mm format
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(hour);
        return m.find();
    }

    public String addAssignment(String assignmentName, String isActive, String dateOfDeadline, String hourOfDeadline, String maker) {
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
        } catch (Exception e) {
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
            String last = assignmentId + "//" + isActive + "//" + assignmentName + "//" + dateOfDeadline + "," + hourOfDeadline + "//" + maker;
            allOfFile.add(last);
            writer(allOfFile, "daneshjooyar/informations/assignment.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return assignmentId;
    }

    public boolean noAssignmentFoundById(String assignmentId) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public void setAssignmentForCourse(String assignmentId, String courseId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
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
                String last = courseId + "//" + set;
                allOfFile.add(last);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/course_assignment.txt");
    }

    public void setDeadline(String assignmentId, String dateOfDeadline, String hourOfDeadline) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    Info[3] = dateOfDeadline + "," + hourOfDeadline;
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                    assignmentChangedDeadlineIdSetter(assignmentId);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/assignment.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void assignmentChangedDeadlineIdSetter(String assignmentId){
        Set<String> allAssignmentId = new HashSet<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment_change_deadline.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allAssignmentId.add(line);
            }
            bufferedReader.close();
            allAssignmentId.add(assignmentId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<String> allOfFile = new ArrayList<>(allAssignmentId);
        writer(allOfFile, "daneshjooyar/informations/assignment_change_deadline.txt");
    }

    public void setAssignmentActivity(String assignmentId, String obligation) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    if (obligation.equals("deactivate")) {
                        Info[1] = "false";
                    } else if (obligation.equals("activate")) {
                        Info[1] = "true";
                    }
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2] + "//" + Info[3];
                    allOfFile.add(last);
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/assignment.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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

    public void setExamDate(String courseId, String examDate, String examHour) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(courseId)) {
                    Info[1] = examDate;
                    Info[2] = examHour;
                    String last = Info[0] + "//" + Info[1] + "//" + Info[2];
                    allOfFile.add(last);
                    isSet = true;
                } else {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            if (!isSet) {
                allOfFile.add(courseId + "//" + examDate + "//" + examHour);
            }
            writer(allOfFile, "daneshjooyar/informations/exam.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeStudentCourse(String studentId, String courseId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        if (courseId.equals("ALL_COURSE")) {
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
                    map.remove(studentId);
                    if (!map.isEmpty()){
                        Info[1] = map.toString();
                        String last = Info[0] + "//" + Info[1];
                        allOfFile.add(last);
                    }
                }
                bufferedReader.close();
                writer(allOfFile, "daneshjooyar/informations/student_course_score.txt");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
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

    //Remove account of a student
    public void studentAccountRemover(String studentId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/students.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[1].equals(studentId))) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/students.txt");
            removeStudentCourse(studentId, "ALL_COURSE");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    //return true if username is not available
    public boolean teacherUsernameValidation(String teacherUsername) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean teacherSignup(String teacherName, String teacherUsername, String teacherPassword) {
        String teacherId = "";
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teacher_num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int code = Integer.parseInt(line);
            code++;
            teacherId = String.valueOf(code);
            bufferedReader.close();
            try {//writing new code
                FileWriter writer = new FileWriter("daneshjooyar/informations/teacher_num.txt");
                writer.write(teacherId);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter AdminFileWriter = new FileWriter("daneshjooyar/informations/teachers.txt", true);
            AdminFileWriter.write(teacherName + "//" + teacherUsername + "//" + hashPassword(teacherPassword) + "//" + teacherId + "\n");
            AdminFileWriter.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean noTeacherFoundById(String teacherId) {
        ArrayList<String> allTeachersId = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allTeachersId.add(Info[3]);
            }
            bufferedReader.close();
            return !allTeachersId.contains(teacherId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void teacherAccountRemover(String teacherId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[3].equals(teacherId))) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/teachers.txt");
            removeTeacherFromAllCourse(teacherId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeTeacherFromAllCourse(String teacherId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[3].equals(teacherId))) {
                    allOfFile.add(line);
                } else {
                    Info[3] = "null";
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
            writer(allOfFile, "daneshjooyar/informations/course.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String addCourse(String courseName, int credit) {
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
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter writer = new FileWriter("daneshjooyar/informations/course.txt", true);
            writer.write(courseName + "//" + courseId + "//" + credit + "//" + "null" + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return courseId;
    }

    public void setCourseTeacher(String courseId, String teacherId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[1].equals(courseId))) {
                    allOfFile.add(line);
                } else {
                    Info[3] = teacherId;
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean courseIdChecker(String courseId) {
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

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
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

    public void removeCourse(String courseId) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<String> allOfFile = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!(Info[1].equals(courseId))) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/course.txt");
            clearCourseAndItsAssignments(courseId);
            clearCourseAndItsExam(courseId);
            clearCourseAndItsScore(courseId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearCourseAndItsAssignments(String courseId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!Info[0].equals(courseId)) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/course_assignment.txt");
    }

    public void clearCourseAndItsExam(String courseId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/exam.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!Info[0].equals(courseId)) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/exam.txt");
    }

    public void clearCourseAndItsScore(String courseId) {
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_course_score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!Info[0].equals(courseId)) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        writer(allOfFile, "daneshjooyar/informations/student_course_score.txt");
    }

    public void setCaptionForAssignment(String assignmentId, String caption) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            ArrayList<String> newAllOfFile = new ArrayList<>();
            if (allOfFile.contains("//" + assignmentId + "//")) {
                for (int i = 0; i < allOfFile.size(); i++) {
                    if (allOfFile.get(i).equals("//" + assignmentId + "//")) {
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
                newAllOfFile.add("//" + assignmentId + "//");
                newAllOfFile.add(caption);
            }
            writer(newAllOfFile, "daneshjooyar/informations/caption.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean captionAssignmentIdPatternChecker(String input) {

        String pattern = "//\\d{5}//";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return !m.find();

    }

    public String assignmentNameById(String assignmentId) {
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

    public void removeAssignment(String assignmentId) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (!Info[0].equals(assignmentId)) {
                    allOfFile.add(line);
                }
            }
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/assignment.txt");
            removeAssignmentFromCourse(assignmentId, "ALL_COURSE");
            removeAssignmentCaption(assignmentId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeAssignmentCaption(String assignmentId) {
        try {
            ArrayList<String> allOfFile = new ArrayList<>();
            FileReader fileReader = new FileReader("daneshjooyar/informations/caption.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
            ArrayList<String> newAllOfFile = new ArrayList<>();
            if (allOfFile.contains("//" + assignmentId + "//")) {
                for (int i = 0; i < allOfFile.size(); i++) {
                    if (allOfFile.get(i).equals("//" + assignmentId + "//")) {
                        while (i < allOfFile.size() - 1 && captionAssignmentIdPatternChecker(allOfFile.get(i + 1))) {
                            i++;
                        }
                    } else {
                        newAllOfFile.add(allOfFile.get(i));
                    }
                }
            } else {
                newAllOfFile = allOfFile;
            }
            writer(newAllOfFile, "daneshjooyar/informations/caption.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean removeAssignmentFromCourse(String assignmentId, String courseId) {
        boolean hasAssignment = false;
        ArrayList<String> allOfFile = new ArrayList<>();
        if (courseId.equals("ALL_COURSE")) {
            try {
                FileReader fileReader = new FileReader("daneshjooyar/informations/course_assignment.txt");
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] Info = line.split("//");
                    Set<String> set = new HashSet<>();
                    String[] elements = Info[1].substring(1, Info[1].length() - 1).split(", ");
                    Collections.addAll(set, elements);
                    set.remove(assignmentId);
                    String last;
                    if (!set.isEmpty()){
                        last = Info[0] + "//" + set;
                        allOfFile.add(last);
                    }
                }
                bufferedReader.close();
                writer(allOfFile, "daneshjooyar/informations/course_assignment.txt");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return true;
        } else {
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
                        hasAssignment = set.remove(assignmentId);
                        String last;
                        if (!set.isEmpty()){
                            last = Info[0] + "//" + set;
                            allOfFile.add(last);
                        }
                    } else {
                        allOfFile.add(line);
                    }
                }
                bufferedReader.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        writer(allOfFile, "daneshjooyar/informations/course_assignment.txt");
        return hasAssignment;
    }

    public void saveStudentBirthday(String birthday, String studentId){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/student_birthday.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            allOfFile.add(studentId + "//" + birthday);
            bufferedReader.close();
            writer(allOfFile, "daneshjooyar/informations/student_birthday.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean courseDatePatternChecker(String input){
        String pattern = "(sh|y|d|se|c|p|j)=([0-1][0-9]|2[0-3]):[0-5][0-9]";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(input);
        return matcher.matches();
    }

    public void setDateForCourse(String courseId, ArrayList<ArrayList<String>> arrayList){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course_date.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allOfFile.add(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        allOfFile.add(courseId + "//" + arrayList.toString());
        writer(allOfFile, "daneshjooyar/informations/course_date.txt");
    }

    public void estimateTimeSetter(String assignmentId, String id, double estimateTime){
        ArrayList<String> allOfFile = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/estimate_time.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            boolean isSet = false;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId)) {
                    String mapAsString = Info[1].replaceAll("[{}\\s]", "");
                    HashMap<String, String> map = new HashMap<>();
                    String[] keyValuePairs = mapAsString.split(",");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    map.put(id, String.valueOf(estimateTime));
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
                writer(allOfFile, "daneshjooyar/informations/estimate_time.txt");
            } else {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(id, String.valueOf(estimateTime));
                allOfFile.add(assignmentId + "//" + hashMap);
                writer(allOfFile, "daneshjooyar/informations/estimate_time.txt");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
