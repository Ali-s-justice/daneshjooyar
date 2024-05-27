import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/login.dart';
import 'package:flutter_application_1/details/signup.dart';

// ignore: must_be_immutable
class MyBottom extends StatefulWidget {
  const MyBottom({super.key});

  @override
  State<MyBottom> createState() => _MyBottomState();
}

class _MyBottomState extends State<MyBottom> {
  int _selected = 0;
  final screens = [const Login(), const Signup()];

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 100.0,
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(255, 93, 0, 255),
            Color.fromARGB(255, 131, 58, 180),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.only(
          topLeft: Radius.circular(50),
          topRight: Radius.circular(50),
        ),
      ),
      child: ClipRRect(
        borderRadius: const BorderRadius.only(
          topLeft: Radius.circular(50),
          topRight: Radius.circular(50),
        ),
        child: BottomNavigationBar(
          selectedFontSize: 20.0,
          unselectedFontSize: 15.0,
          unselectedIconTheme: const IconThemeData(
            size: 35.0,
          ),
          currentIndex: _selected,
          iconSize: 50,
          selectedItemColor: Colors.black,
          type: BottomNavigationBarType.fixed,
          backgroundColor: Colors.transparent,
          elevation: 0,
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              label: 'signin',
              icon: Icon(Icons.person),
            ),
            BottomNavigationBarItem(
              label: 'signup',
              icon: Icon(Icons.person_add),
            ),
          ],
          onTap: (int value) {
            setState(
              () {
                _selected = value;
              },
            );
          },
        ),
      ),
    );
  }
}
