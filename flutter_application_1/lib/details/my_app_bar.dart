import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class SignUpLoginAppBar extends StatelessWidget implements PreferredSizeWidget {
  const SignUpLoginAppBar({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(255, 93, 0, 255),
            Color.fromARGB(255, 15, 199, 255),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.only(
          bottomLeft: Radius.circular(50.0),
          bottomRight: Radius.circular(50.0),
        ),
      ),
      child: AppBar(
        automaticallyImplyLeading: false,
        shape: const RoundedRectangleBorder(
          borderRadius: BorderRadius.only(
            bottomLeft: Radius.circular(50.0),
            bottomRight: Radius.circular(50.0),
          ),
        ),
        backgroundColor: Colors.transparent, // Transparent to show gradient
        title: Center(
          child: RichText(
            text: const TextSpan(children: [
              TextSpan(
                text: 'D',
                style: TextStyle(
                  fontFamily: 'fondy-script',
                  fontSize: 50,
                  fontWeight: FontWeight.w900,
                ),
              ),
              TextSpan(
                text: 'arsa',
                style: TextStyle(
                  fontFamily: 'fondy-script',
                  fontSize: 35,
                  fontWeight: FontWeight.w900,
                ),
              ),
            ]),
          ),
        ),
      ),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(100);
}
