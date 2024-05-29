
import 'dart:ffi';

import 'course.dart';
class Student {
  String? username ; 
  String? password ;
  String? studenCode ;
  int? numberOfCourses;
  int? numberOfCourseUnit;
  Map<Course , Double>? pastCourses;
  Map<Course , Double>? currentCourses;
  double? totalAverage;
  double? averageOfCurrentTerm;
   
}
