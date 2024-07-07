import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/tamrina/tamrina_appbar.dart';
import 'tamrina_bottombar.dart';
import 'body_of_tamrina.dart';

class Tamrina extends StatefulWidget {
  static const String routeName = 'tamrina';
  const Tamrina({super.key});

  @override
  State<Tamrina> createState() => _TamrinaState();
}

class _TamrinaState extends State<Tamrina> {
  @override
  Widget build(BuildContext context) {

    return const Scaffold(
      appBar: TamrinaAppBar(),
      body: BodyOfTamrina(
       
      ),
      bottomNavigationBar: TamrinaBottomBar(),
    );
  }
}
