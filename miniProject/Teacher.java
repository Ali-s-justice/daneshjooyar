import java.time.LocalDate;
import java.util.ArrayList;

public class Teacher {
//fields
    private String name ; 
    private int numberOfCourse ; 
    private ArrayList<Course> courses ;
//finish
    
    
//constructors :
    public Teacher(){
        numberOfCourse = 0 ;
        courses = new ArrayList<>() ;
    }
    public Teacher(String name){
        this();
        this.name = name; 
    }
//finish
    

//related to Student and Course :
    public void addStudentToCourse(Student student ,Course course){
        if(student!=null && course!=null){
            for(Course x : courses){
                if (x.equals(course)){
                    x.addStudent(student);
                }
            }        
        }
    }
    public void removeStudentFromCourse(Student student , Course course){
        if (student!=null && course!=null){
            for(Course x : courses){
                if (x.equals(course)){
                    x.removeStudent(student);
                }
            }        
        }
    }
    public void addAssignment(Assignment newAssignment,Course course ){
        if (newAssignment!=null && course!=null){
            for(Course x : courses){
                if (x.equals(course)){
                    x.addAssignment(newAssignment);
                }
            }
        }
    }
    public void deActivationAssignment(Assignment assignment ,Course course){
        if (assignment!=null && course!=null){
            for(Course x : courses){
                if (x.equals(course)){
                    x.deActivationAssignment(assignment);
                }
            }
        }
    }
    public void addProject(Assignment newProject , Course course){
        if (newProject!=null && course!=null){
            for(Course x : courses){
                if (x.equals(course)){
                    x.addProject(newProject);
                }
            }
        }
    }
    public void deActivationProject(Assignment project ,Course course){
        if(project!=null && course!=null){   
            for(Course x : courses){
                if (x.equals(course)){
                    x.deActivationProject(project);
                }
            }
        }
    }
    public void setScore(double score , Student student , Course course){
        if(student!=null && course!=null){
            student.getCurrentCourses().put(course, score);
        }
    }
    public void setExamDate(LocalDate examDate , Course course){
        if(course!=null){
            course.setExamdate(examDate);
        }
    }
//finish


//related to Assignment :
    public void changeDeadline(int untilTheDeadline,Assignment target , Course course){
        if (target!=null && course!=null){
            for(Course x : courses){
                if (x.equals(course)){
                    for(Assignment y : x.getcourseAssignments()){
                        if (y.equals(target)){
                            y.setUntilTheDeadline(untilTheDeadline);
                        }
                    }
                }
            }
        }
    }
//finish 


//Outside of existing classes :
    public void addCourse(Course newCourse){
        if (newCourse!=null){
            newCourse.setTeacher(this);
        }
    }
    public void removeCourse(Course removedCourse){
        if(removedCourse!=null){
            removedCourse.removeTeacher();        
        }
    }
//finish


//setters :
    public void setName(String name) {
        this.name = name;
    }
    public void setNumberOfCourse(int numberOfCourse) {
        this.numberOfCourse = numberOfCourse;
    }    
//finish 


//getters :
    public String getName() {
        return name;
    }
    public int getNumberOfCourse() {
        return numberOfCourse;
    }
    public ArrayList<Course> getCourses() {
        return courses;
    }
//finish

@Override
public String toString(){

    return String.format("<%s>\nnumber of cours : %d\n",name,numberOfCourse);
}

}
