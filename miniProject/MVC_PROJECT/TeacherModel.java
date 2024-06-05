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

public class TeacherModel {

    //return true if teacher name is not available
    public boolean teacherNameValidation(String teacherName){
        ArrayList<String> allTeachersName = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
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

    public boolean teacherPasswordNotValidation(String password, String username){
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(password);

        if (password.contains(username)) {
            return true;
        }
        return !m.matches();
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
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
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

    public boolean PasswordNotValidate(String password, String username) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(password);

        if (password.contains(username)) {
            return true;
        }
        return !m.matches();
    }

    public boolean teacherUsernameNotAvailable(String username){
        ArrayList<String> allTeacherUsername = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                allTeacherUsername.add(Info[1]);
            }
            bufferedReader.close();
            return allTeacherUsername.contains(username);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean teacherPasswordIsWrong(String username, String password) {
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(username)) {
                    return !checkPassword(password, Info[2]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
        return true;
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

    public String thisTeacherIdGetter(String username){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/teachers.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(username)) {
                    return Info[3];
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
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

    public boolean courseIsNotForTeacher(String courseId, String teacherId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/course.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[1].equals(courseId) && Info[3].equals(teacherId)){
                    return false;
                }
            }
            bufferedReader.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
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

    public boolean dateHasValidPattern(String date) {
        String pattern = "^(\\d{4})/(0?[1-9]|1[0-2])/(0?[1-9]|[12][0-9]|3[01])$"; // Pattern for yyyy/mm/dd format
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(date);
        return m.find();
    }

    public boolean hourHasValidPattern(String hour) {
        String pattern = "^(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"; // Pattern for hh:mm format
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(hour);
        return m.find();
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

    public void addAssignment(String assignmentName, String isActive, String dateOfDeadline, String hourOfDeadline, String maker) {
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
    }

    public boolean assignmentIsNotForTeacher(String assignmentId, String teacherId){
        try {
            FileReader fileReader = new FileReader("daneshjooyar/informations/assignment.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] Info = line.split("//");
                if (Info[0].equals(assignmentId) && Info[4].equals(teacherId)){
                    return false;
                }
            }
            bufferedReader.close();
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

    public boolean captionAssignmentIdPatternChecker(String input) {
        String pattern = "//\\d{5}//";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return !m.find();
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

}
