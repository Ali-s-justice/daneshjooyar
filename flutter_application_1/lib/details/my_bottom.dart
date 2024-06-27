import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/login.dart';
import 'package:flutter_application_1/details/signup.dart';

// ignore: must_be_immutable
class SignUpLoginBottomBar extends StatefulWidget {
  const SignUpLoginBottomBar({super.key});

  @override
  State<SignUpLoginBottomBar> createState() => _SignUpLoginBottomBarState();
}

class _SignUpLoginBottomBarState extends State<SignUpLoginBottomBar> {
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
            Color.fromARGB(255, 15, 199, 255),
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
          currentIndex: _selected,
          iconSize: 50,
          type: BottomNavigationBarType.fixed,
          backgroundColor: Colors.transparent,
          elevation: 0,
          items: const <BottomNavigationBarItem>[
            BottomNavigationBarItem(
              label: '',
              icon: Icon(
                Icons.person,
                color: Colors.white,
              ),
            ),
            BottomNavigationBarItem(
              label: '',
              icon: Icon(
                Icons.person_add,
                color: Colors.white,
              ),
            ),
          ],
          onTap: (int value) {
            setState(
              () {
                _selected = value;
                value == 1
                    ? Navigator.pushReplacementNamed(context, Signup.routeName)
                    : Navigator.pushReplacementNamed(context, Login.routeName);
              },
            );
          },
        ),
      ),
    );
  }
}
