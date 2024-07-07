import 'package:flutter_application_1/details/edit_informations.dart';
import 'package:flutter_application_1/details/kara/kara.dart';
import 'package:flutter_application_1/details/khabara/khabara.dart';
import 'package:flutter_application_1/details/klasa/klasa.dart';
import 'package:flutter_application_1/details/sara/sara.dart';
import 'package:flutter_application_1/details/support.dart';
import 'package:flutter_application_1/details/tamrina/tamrina.dart';
import '../details/information.dart';
import '../details/login.dart';
import '../details/signup.dart';
import 'package:flutter/material.dart';

final Map<String, WidgetBuilder> routes = {
  Login.routeName: (context) => const Login(),
  Signup.routeName: (context) => const Signup(),
  Information.routeName: (context) => const Information(),
  EditInformation.routeName: (context) => const EditInformation(),
  Support.routeName: (context) => const Support(),
  Sara.routeName: (context) => const Sara(),
  Kara.routeName: (context) => const Kara(),
  Khabara.routeName: (context) => const Khabara(),
  Klasa.routeName: (context) => const Klasa(),
  Tamrina.routeName:(context) => const Tamrina(),
};
