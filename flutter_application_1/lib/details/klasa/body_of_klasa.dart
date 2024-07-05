import 'package:flutter/material.dart';

class BodyOfKlasa extends StatefulWidget {
  const BodyOfKlasa({super.key});

  @override
  State<BodyOfKlasa> createState() => _BodyOfKlasaState();
}

class _BodyOfKlasaState extends State<BodyOfKlasa> {
  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return SingleChildScrollView(
      child: Padding(
        padding: EdgeInsets.only(
          top: heightOfScreen * 0.1,
          left: widthOfScreen * 0.05,
          right: widthOfScreen * 0.05,
        ),
        child: Container(
          //width: widthOfScreen * 0.8,
          height: heightOfScreen * 0.5,
          color: const Color.fromARGB(255, 111, 207, 255),
          child: const Center(
            child: Text('کانتینر داخلی'),
          ),
        ),
      ),
    );
  }
}
