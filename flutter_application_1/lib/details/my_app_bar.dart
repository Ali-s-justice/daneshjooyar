import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class MyAppBar extends StatelessWidget implements PreferredSizeWidget {
  const MyAppBar({super.key});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: const Color.fromARGB(255, 93, 0, 255),
      title: Center(
          child: RichText(
        text: const TextSpan(children: [
          TextSpan(
              text: 'D',
              style: TextStyle(
                fontFamily: 'fondy-script',
                fontSize: 50,
                fontWeight: FontWeight.w900,
              )),
          TextSpan(
              text: 'arsa',
              style: TextStyle(
                fontFamily: 'fondy-script',
                fontSize: 35,
                fontWeight: FontWeight.w900,
              ))
        ]),
      )),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(100);
}
