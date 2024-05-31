import 'package:flutter/material.dart';

class Support extends StatefulWidget {
  const Support({super.key});
  static const String routeName = "support";

  @override
  State<Support> createState() => _SupportState();
}

class _SupportState extends State<Support> {
  
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(212, 255, 255, 255),
            Color.fromARGB(255, 15, 199, 255),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      );

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          centerTitle: true,
          title: const Text(
            'ارتباط با ما',
            style: TextStyle(
              color: Colors.white,
              fontSize: 30,
              fontFamily: 'vazir',
            ),
          ),
          backgroundColor: Colors.transparent,
          toolbarHeight: 40,
        ),
        body: const Padding(
          padding: EdgeInsets.only(
            left: 10,
            right: 10,
            top: 20,
            bottom: 0,
          ),
        ),
      ),
    );
  }
}
