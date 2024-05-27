import 'package:flutter/material.dart';
import 'package:flutter_application_1/routes/routes.dart';
import 'details/my_app_bar.dart';
import 'details/login.dart';
import 'details/my_bottom.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      initialRoute: Login.routeName,
      routes: routes,
    );
  }
}
