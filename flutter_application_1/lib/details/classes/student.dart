import 'dart:ffi';

import 'course.dart';

class Student {
  String? name = 'Amirhossein Ashrafian';
  String? username;
  String? password;
  String? studenCode;
  int? numberOfCourses;
  int? numberOfCourseUnit = 18;
  Map<Course, Double>? pastCourses;
  Map<Course, Double>? currentCourses;
  double? totalAverage = 19.99;
  double? averageOfCurrentTerm;
}
