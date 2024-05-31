import 'package:flutter_application_1/details/edit_informations.dart';
import 'package:flutter_application_1/details/edit_password.dart';
import 'package:flutter_application_1/details/support.dart';

import '../details/information.dart';
import '../details/login.dart';
import '../details/signup.dart';
import 'package:flutter/material.dart';

final Map<String, WidgetBuilder> routes = {
  Login.routeName: (context) => const Login(),
  Signup.routeName: (context) => const Signup(),
  Information.routeName: (context) => const Information(),
  EditInformation.routeName: (context) => const EditInformation(),
  Support.routeName :(context) => const Support(),
};
