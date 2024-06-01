import 'package:flutter_application_1/details/classes/teacher.dart';
import 'student.dart';
import 'assignment.dart';
class Course {
  String? name;
  Teacher? teacher;
  int? creditOfCourse ;
  List<Student>? studentsOfCourse ; 
  bool? isActive ; 
  List<Assignment>? courseAssignments ; 
  int? numberOfAssignment ; 
  DateTime? examdate;
  List<Assignment>? courseProject;  
  int? numberOfProject ;
  int? numberOfStudents ; 

}
