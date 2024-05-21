import java.util.ArrayList;

public class Amoozesh {
    private static Amoozesh amoozesh ; 
    ArrayList<Course> courses = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>(); 
    private final String adminUser = "admin" ; 
    private final String adminPass = "admin" ;

    private Amoozesh(){
    }
    // public static getAmoozesh(){
    //     if (amoozesh==null){
    //         amoozesh = new Amoozesh(); 
    //         return amoozesh ;
    //     }
    //     else{
    //         return amoozesh ; 
    //     }
    // }



    public boolean removeCourse(Course removedCourse){
        if (removedCourse!=null){
            courses.remove(removedCourse); 
            return true ;
        }
        else{
            return false; 
        }
    }
}
