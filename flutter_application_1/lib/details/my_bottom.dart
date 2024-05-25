import 'package:flutter/material.dart';

class MyBottom extends StatelessWidget {
  const MyBottom({super.key});

  @override
  Widget build(BuildContext context) {
    return BottomNavigationBar(
      
      backgroundColor: const Color.fromARGB(255, 93, 0, 255),
      items: const <BottomNavigationBarItem>[
        BottomNavigationBarItem(

          label: 'a',
          icon: Icon(Icons.account_box),
        ),
        BottomNavigationBarItem(
          label: 'b',
          icon: Icon(Icons.access_alarm_outlined),
        )
      ],
    );
  }
}