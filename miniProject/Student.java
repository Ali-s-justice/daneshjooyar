import java.util.HashMap;

public class Student {
//fields :
    private String name ; 
    private int numberOfcourse ; 
    private int numberOfCredit ;    //؟
    private HashMap<Course , Double> pastCourses ;
    private HashMap<Course , Double> currentCourses ;
    private double totalAverage ;
    private double averageOfCurrentTerm ;
    private String student_code ; //Id
    private String Username;
    private String Password;
//finish

//constructors :
    public Student (){
        pastCourses = new HashMap<>();
        currentCourses = new HashMap<>();
        numberOfcourse = 0 ;
        numberOfCredit = 0 ;
    }
    public Student(String name , String student_code){
        this();
        this.name = name ; 
        this.student_code = student_code ;    
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
        System.out.println("number of credit = "+numberOfCredit);
    }
//finish


//related to Course
public void addCourse(Course newCourse) {
    if (newCourse!=null){
        currentCourses.put(newCourse, -1.0);
        numberOfcourse++ ; 
        numberOfCredit += newCourse.getCreditOfCourse();    //The initial grade for a new course is set to 0.0
    }
}
public void removeCourse(Course removedCourse){
    if (removedCourse!=null){
        currentCourses.remove(removedCourse);
        numberOfcourse-- ;
        numberOfCredit -= removedCourse.getCreditOfCourse();
    }
}
//finish

//setters :
    public void setName(String name) {
        this.name = name;
    }
    public void setNumberOfcourse(int numberOfcourse) {
        this.numberOfcourse = numberOfcourse;
    }
    public void setNumberOfCredit(int numberOfCredit) {
        this.numberOfCredit = numberOfCredit;
    }
    
    public void setTotalAverage(double totalAverage) {
        this.totalAverage = totalAverage;
    }
    public void setAverageOfCurrentTerm(double averageOfCurrentTerm) {
        this.averageOfCurrentTerm = averageOfCurrentTerm;
    }
    public void setStudent_code(String student_code) {
        this.student_code = student_code;
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
    public int getNumberOfcourse() {
        return numberOfcourse;
    }
    public int getNumberOfCredit() {
        return numberOfCredit;
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
    public String getStudent_code() {
        return student_code;
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
    return String.format("<%s>\t<%s>",name,student_code);
}
}
