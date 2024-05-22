public class TestCase {
    public static void main(String[] args) {
        int a = 402243000;
        System.out.println(a);
    }
}
////I am Hamid
//public class TestCase {
//    public static void main(String[] args) {
////    //ایجاد چند دانش آموز
////        Student student1 = new Student("student1","4021");
////        Student student2 = new Student("student2","4022");
////        Student student3 = new Student("student3","4023");
//
//
//    //ایجاد چند کلاس
//        Course course1 = new Course("course1",1) ;
//        Course course2 = new Course("course2",2) ;
//        Course course3 = new Course("course3",3) ;
//
//
//    //ایجاد چند استاد
//        Teacher teacher1 = new Teacher("teacher1");
//        Teacher teacher2 = new Teacher("teacher2");
//        Teacher teacher3 = new Teacher("teacher3");
//
//
//
//    //تنظیم استاد برای هر کلاس
//        course1.setTeacher(teacher1);
//        course2.setTeacher(teacher2);
//        course3.setTeacher(teacher3);
//
//
//
//    //اضافه کردن دانشجویان به کلاس
//        teacher1.addStudentToCourse(student1 , course1);
//        teacher2.addStudentToCourse(student1 , course2);
//        teacher3.addStudentToCourse(student1 , course3);
//
//
//        teacher1.addStudentToCourse(student2 , course1);
//        teacher2.addStudentToCourse(student2 , course2);
//        teacher3.addStudentToCourse(student2 , course3);
//
//
//        teacher1.addStudentToCourse(student3 , course1);
//        teacher2.addStudentToCourse(student3 , course2);
//        teacher3.addStudentToCourse(student3 , course3);
//
//
//    //مشاهده لیست دانشجویان هر دوره
//        System.out.println("course1 :");
//        course1.printStudentOfCours();
//
//        System.out.println("\ncourse2 :");
//        course2.printStudentOfCours();
//
//        System.out.println("\ncourse3 :");
//        course3.printStudentOfCours();
//        System.out.println();
//
//
//    //مشاهده استاد هر دوره
//        System.out.println("teacher of course1 is : "+course1.getteacher().getName());
//        System.out.println("teacher of course2 is : "+course2.getteacher().getName());
//        System.out.println("teacher of course3 is : "+course3.getteacher().getName());
//        System.out.println();
//
//
//    //مشاهده لیست دروس هر دانشجو
//        System.out.println("Student1 :");
//        student1.printStudentCourses();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("Student2 :");
//        student2.printStudentCourses();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("Student3 :");
//        student3.printStudentCourses();
//        System.out.println();
//        System.out.println();
//
//
//
//    //از هر دانشجو یک درس حذف کرده و مجدد لیست دروس هر دانشجو را مشاهده میکنیم
//        teacher1.removeStudentFromCourse(student1, course1);
//        teacher2.removeStudentFromCourse(student2, course2);
//        teacher3.removeStudentFromCourse(student3, course3);
//        System.out.println();
//        System.out.println("********After removing students********");
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("Student1 :");
//        student1.printStudentCourses();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("Student2 :");
//        student2.printStudentCourses();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("Student3 :");
//        student3.printStudentCourses();
//        System.out.println();
//        System.out.println();
//
//
//    //تعداد واحد هر دانشجو رو تست میکنیم
//        System.out.print("student1  ");
//        student1.printNumberOfCredit();
//        System.out.print("student2  ");
//        student2.printNumberOfCredit();
//        System.out.print("student3  ");
//        student3.printNumberOfCredit();
//
//
//
//    //نمایش لیست دانشجویان هر دوره
//        System.out.println("course1");
//        course1.printStudentOfCours();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("course2");
//        course2.printStudentOfCours();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//        System.out.println("course3");
//        course3.printStudentOfCours();
//        System.out.println();
//        System.out.println();
//        System.out.println();
//
//
//    //ایجاد چند تکلیف
//        Assignment assignment1 = new Assignment(11) ;
//        Assignment assignment2 = new Assignment(12) ;
//        Assignment assignment3 = new Assignment(13) ;
//
//    //  اضافه کردن تکلیف به درس توسط استادو تغییر ددلاین پیشفرض
//        teacher1.addAssignment(assignment1, course1);
//        teacher2.addAssignment(assignment2, course2);
//        teacher3.addAssignment(assignment3, course3);
//        teacher1.changeDeadline(21, assignment1, course1);
//        teacher2.changeDeadline(22, assignment2, course2);
//        teacher1.changeDeadline(23, assignment3, course3);
//    //دو بخش بالا توسط تحویلگیرنده بررسی شود!
//
//
//    //ثبت نمره برای دانش آموزان
//    teacher1.setScore(20, student2, course1);
//    teacher1.setScore(19, student3, course1);
//    teacher2.setScore(18, student1, course2);
//    teacher2.setScore(17, student3, course2);
//    teacher3.setScore(16, student1, course3);
//    teacher3.setScore(15, student2, course3);
//
//
//    //تست پیدا کردن بهترین نمره ی یک درس
//    System.out.println("course1 :");
//    course1.bestScore() ;
//    System.out.println("Course2 :");
//    course2.bestScore() ;
//    System.out.println("Course3 :");
//    course3.bestScore() ;
//
//
//
//    //تست معدل ترم جاری
//    System.out.println("Student1 :");
//    student1.printCurrentAverage();
//    System.out.println("Student2 :");
//    student2.printCurrentAverage();
//    System.out.println("Student3 :");
//    student3.printCurrentAverage();
//
//
//    //تست لیست دروس هر استاد
//    System.out.println("teacher1 :");
//    for(int i = 0 ; i < teacher1.getCourses().size() ;i++){
//        System.out.println(teacher1.getCourses().get(i).getName());
//    }
//    System.out.println("teacher2 :");
//    for(int i = 0 ; i < teacher2.getCourses().size() ;i++){
//        System.out.println(teacher2.getCourses().get(i).getName());
//    }
//    System.out.println("teacher3 :");
//    for(int i = 0 ; i < teacher3.getCourses().size() ;i++){
//        System.out.println(teacher3.getCourses().get(i).getName());
//    }
//
//    System.out.println("+--+++-++--+-++--+-+-+-+-+-+-+-+DAREH TAMOOM MISHE BEKHODA+--+++-++--+-++--+-+-+-+-+-+-+-+");
//
//    // تست متود تواسترینگ برای کلاس استاد
//    System.out.println(teacher1);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(teacher2);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(teacher3);
//    System.out.println();
//    System.out.println();
//    System.out.println("***************************************************************************************");
//    //برای کورس
//    System.out.println(course1);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(course2);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(course3);
//    System.out.println();
//    System.out.println();
//    System.out.println("***************************************************************************************");
//    //برای دانشجو
//    System.out.println(student1);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(student2);
//    System.out.println();
//    System.out.println();
//    System.out.println();
//    System.out.println(student3);
//    System.out.println();
//    System.out.println();
//    System.out.println("***************************************************************************************");
//    }
//}
//
