
import java.time.LocalDate;
import java.util.ArrayList;
public class Course {
//fields :
    private String name ; 
    private Teacher teacher ;
    private int creditOfCourse ;
    private ArrayList<Student> studentsOfCourse ; 
    private boolean isActive ; 
    private ArrayList<Assignment> courseAssignments ; 
    private int numberOfAssignment ; 
    private LocalDate examdate;
    private ArrayList<Assignment> courseProject;  
    private int numberOfProject ;
    private int numberOfStudents ; 
//finish


//constructors :
    public Course(){
        studentsOfCourse = new ArrayList<>() ;
        courseAssignments = new ArrayList<>() ;
        courseProject = new ArrayList<>() ;
        numberOfAssignment = 0 ;
        numberOfProject = 0 ;
        numberOfAssignment = 0 ;
    }
    public Course(String name , int creditOfCourse){
        this() ;
        this.name = name ;
        this.creditOfCourse = creditOfCourse ;
    }
//finish


//print base methods :
    public void printStudentOfCours(){
        System.out.println("Student of Course :");
        for(int  i = 0 ; i < studentsOfCourse.size() ; i++){
            System.out.println(studentsOfCourse.get(i).getName());
        }
    }
//finish


//related to student :
    public void addStudent(Student newStudent){
        if (newStudent!=null){
            studentsOfCourse.add(newStudent);
            numberOfStudents++ ;
            newStudent.addCourse(this);
        }
    }
    public void removeStudent(Student removedStudent){
        if (removedStudent!=null){
            studentsOfCourse.remove(removedStudent);
            numberOfStudents-- ;
            removedStudent.removeCourse(this);
        }
    }
    public double bestScore(){
        
        double bestScor = 0.0 ;     
        int indexOfBestStudent = 0 ;

        for(int i = 0 ; i < studentsOfCourse.size() ; i++){
            if (studentsOfCourse.get(i).getScoreOfaCourse(this) > bestScor){
                bestScor = studentsOfCourse.get(i).getScoreOfaCourse(this) ;
                indexOfBestStudent = i ;
            }
        }
        System.out.println("The best score belongs to "+studentsOfCourse.get(indexOfBestStudent).getName()+" = "+bestScor);
        return bestScor;
    }
//finish


//related to Teacher
    public void addAssignment(Assignment newAssignment){
        if (newAssignment!=null){
            courseAssignments.add(newAssignment);
            numberOfAssignment++ ;
        }
    }
    public void deActivationAssignment(Assignment targetAssignment){ //این متود به این صورت نوشته شده که در صورت غیرفعالسازی یک تکلیف فیلد مربوط به تعداد تکالیف تغییر نکند
        if (targetAssignment!=null){
            for(Assignment x : courseAssignments){
                if (x.equals(targetAssignment)){
                    x.setActive(false);
                }
            }
        }
    }
    public void addProject(Assignment newProject){
        if (newProject!=null){
            courseProject.add(newProject);
            numberOfProject++ ;
        }
    }
    public void deActivationProject(Assignment targetProject){ //این متود به این صورت نوشته شده که در صورت غیرفعالسازی یک پروژه فیلد مربوط به تعداد پروژه ها تغییر نکند
        if (targetProject!=null){
            for(Assignment x : courseProject){
                if (x.equals(targetProject)){
                    x.setActive(false);
                }
            }
        }
    }
    public void setTeacher(Teacher teacher) {
        if (teacher!=null){
            teacher.getCourses().add(this);
            teacher.setNumberOfCourse(teacher.getNumberOfCourse()+1);
            this.teacher = teacher;
        }
    }
    public void removeTeacher(){
        this.teacher.getCourses().remove(this);
        this.teacher.setNumberOfCourse(teacher.getNumberOfCourse()-1);
        this.teacher = null ; 
    }
//finish


//setters :
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCreditOfCourse(int creditOfCourse) {
        this.creditOfCourse = creditOfCourse;
    }
   
    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
    public void setNumberOfAssignment(int numberOfAssignment) {
        this.numberOfAssignment = numberOfAssignment;
    }
    public void setExamdate(LocalDate examdate) {
        this.examdate = examdate;
    }
    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }
//finish


//getters
    public int getNumberOfProject() {
        return numberOfProject;
    }
    public ArrayList<Assignment> getCourseAssignments() {
        return courseAssignments;
    }
    public ArrayList<Assignment> getcourseProject() {
        return courseProject;
    }
    public String getName() {
        return name;
    }
    public Teacher getteacher() {
        return teacher;
    }
    public int getCreditOfCourse() {
        return creditOfCourse;
    }
    public ArrayList<Student> getStudentsOfCourse() {
        return studentsOfCourse;
    }
    public boolean isActive() {
        return isActive;
    }
    public ArrayList<Assignment> getcourseAssignments() {
        return courseAssignments;
    }
    public int getNumberOfAssignment() {
        return numberOfAssignment;
    }
    public LocalDate getExamdate() {
        return examdate;
    }
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
//finish
    



    @Override
    public String toString(){
        return String.format("<%s>\ncredit = <%d>\ntecher : <%s>",name,creditOfCourse,teacher.getName());
    }
}
