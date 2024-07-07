import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/khabara/khabara_appbar.dart';
import '../classes/student.dart';
import 'khabara_bottombar.dart';
import 'body_of_khabara.dart';

class Khabara extends StatefulWidget {
  static const String routeName = 'khabara';
  const Khabara({super.key});

  @override
  State<Khabara> createState() => _KhabaraState();
}

class _KhabaraState extends State<Khabara> {
  @override
  Widget build(BuildContext context) {

    return  const Scaffold(
      appBar: KhabaraAppBar(),
      body: BodyOfKhabara(
  
      ),
      bottomNavigationBar: KhabaraBottomBar(),
    );
  }
}
