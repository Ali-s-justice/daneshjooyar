
import '../details/information.dart';
import '../details/login.dart';
import '../details/signup.dart';
import 'package:flutter/material.dart';

final Map<String, WidgetBuilder> routes = {
  Login.routeName :(context) => const Login(),
  Signup.routeName :(context) => const Signup(),
  Information.routeName :(context) => const Information(),
};
