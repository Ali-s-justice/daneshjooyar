import 'package:flutter/material.dart';

class Support extends StatelessWidget {
  const Support({super.key});
  static const String routeName = "support";
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(255, 93, 0, 255),
            Color.fromARGB(255, 131, 58, 180),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      );

  @override
  Widget build(BuildContext context) {
    return Container(

      decoration: gradientBackground,
    
      child: Padding(
        padding: const EdgeInsets.only(
          left: 20,
          right: 20,
          top: 20,
          bottom: 0,
        ),
        child: Container(
          decoration: BoxDecoration(
            color: Colors.white.withOpacity(0.8),
            border: BorderRad
          ),
        ),
      ),
    );
  }
}
