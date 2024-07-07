import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/kara/body_of_kara.dart';
import 'package:flutter_application_1/details/kara/kara_appbar.dart';
import '../classes/student.dart';
import 'kara_bottombar.dart';

class Kara extends StatefulWidget {
  static const String routeName = 'kara';
  const Kara({super.key});

  @override
  State<Kara> createState() => _KaraState();
}

class _KaraState extends State<Kara> {
  @override
  Widget build(BuildContext context) {
        // final Student student =
        // ModalRoute.of(context)!.settings.arguments as Student;
    return  const Scaffold(
      appBar: KaraAppBar(),
      body: BodyOfKara(
     
      ),
      bottomNavigationBar: KaraBottomBar(),
      // ignore: prefer_const_constructors
    );
  }
}
