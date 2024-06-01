import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;

public class Student {
//fields :
    private String name;
    private int numberOfCourses;
    private int numberOfCourseUnit;
    private HashMap<Course , Double> pastCourses;
    private HashMap<Course , Double> currentCourses;
    private double totalAverage;
    private double averageOfCurrentTerm;
    private String studentCode; //Id
    private String Username;
    private String Password;
//finish

//constructors :
    public Student (){
        pastCourses = new HashMap<>();
        currentCourses = new HashMap<>();
        numberOfCourses = 0 ;
        numberOfCourseUnit = 0 ;
        try {
            FileReader fileReader = new FileReader("informations/student_num.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            int code = Integer.parseInt(line);
            code++;
            studentCode = String.valueOf(code);
            bufferedReader.close();
            try {//writing new code
                FileWriter writer = new FileWriter("informations/student_num.txt");
                writer.write(studentCode);
                writer.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    public Student(String name, String Username, String Password){
        this();
        this.Username = Username;
        this.Password = Password;
        this.name = name;
        try {
            FileWriter writer = new FileWriter("informations/students.txt",true);
            writer.write(name + "//" + studentCode + "//" + Username + "//" + Model.getModel().hashPassword(Password) + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
//finish


//print base methods :
    public void printStudentCourses(){
    
        System.out.println("->current courses :");
        if(currentCourses.size() == 0){
            System.out.println("No course available");
        }
        for(Course course : currentCourses.keySet()){
            System.out.println(course.getName());
        }
        System.out.println("->past courses :");
        if (pastCourses.size()==0){
            System.out.println("No course available");
        }
        for(Course course : pastCourses.keySet()){
            System.out.println(course.getName());
        }
    
    }
    public void printStudentTotalAverage(){
        
        for(Double x : pastCourses.values()){
            totalAverage += x ; 
        }
        totalAverage /= pastCourses.size() ;
        System.out.println("total average = "+totalAverage);
    
    }
    public void printCurrentAverage(){

        for(Double x : currentCourses.values()){
            averageOfCurrentTerm += x ;
        }
        averageOfCurrentTerm /= currentCourses.size() ; 
        System.out.println("average of current term = "+averageOfCurrentTerm);
    
    }
    public void printNumberOfCredit(){
        System.out.println("number of credit = "+ numberOfCourseUnit);
    }
//finish


//related to Course
public void addCourse(Course newCourse) {
    if (newCourse!=null){
        currentCourses.put(newCourse, -1.0);
        numberOfCourses++ ;
        numberOfCourseUnit += newCourse.getCreditOfCourse();    //The initial grade for a new course is set to 0.0
    }
}
public void removeCourse(Course removedCourse){
    if (removedCourse!=null){
        currentCourses.remove(removedCourse);
        numberOfCourses-- ;
        numberOfCourseUnit -= removedCourse.getCreditOfCourse();
    }
}
//finish

//setters :
    public void setName(String name) {
        this.name = name;
    }
    public void setNumberOfCourses(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;
    }
    public void setNumberOfCourseUnit(int numberOfCourseUnit) {
        this.numberOfCourseUnit = numberOfCourseUnit;
    }
    
    public void setTotalAverage(double totalAverage) {
        this.totalAverage = totalAverage;
    }
    public void setAverageOfCurrentTerm(double averageOfCurrentTerm) {
        this.averageOfCurrentTerm = averageOfCurrentTerm;
    }
    public void setStudentCode(String student_code) {
        this.studentCode = student_code;
    }
//finish


//getters :
    public String getName() {

        return name;
    }
    public HashMap<Course, Double> getCurrentCourses() {
        return currentCourses;
    }
    public HashMap<Course, Double> getPastCourses() {
        return pastCourses;
    }
    public int getNumberOfCourses() {
        return numberOfCourses;
    }
    public int getNumberOfCourseUnit() {
        return numberOfCourseUnit;
    }
    public double getTotalAverage() {       //edited
        for(Double x : pastCourses.values()){
            totalAverage += x ; 
        }
        totalAverage /= pastCourses.size(); 
        return totalAverage;
    }
    public double getAverageOfCurrentTerm() {
        for(Double x : currentCourses.values()){
            averageOfCurrentTerm += x ; 
        }
        averageOfCurrentTerm /= currentCourses.size(); 
        return averageOfCurrentTerm;
        
    }
    public String getStudentCode() {
        return studentCode;
    }
    public Double getScoreOfaCourse(Course target){
        try{
            for(Course x : currentCourses.keySet()){
                if (x.equals(target)){
                    if (currentCourses.get(x) == -1.0) {
                        throw new Exception("No grade has been recorded for this course") ;
                    }
                    return currentCourses.get(x);
                }
            }
        }
        catch(Exception e){
            System.out.println("Exception caught: " + e.getMessage());
        }
        return -1.0 ;   //must be eddited 
        
    }
//finish



@Override 
public String toString(){
    return String.format("<%s>\t<%s>",name,studentCode);
}
}
