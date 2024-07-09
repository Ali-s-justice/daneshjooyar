import 'package:flutter/material.dart';

import '../information.dart';
import '../sara/sara.dart';

class TamrinaAppBar extends StatelessWidget implements PreferredSizeWidget {
  const TamrinaAppBar({super.key});

  static const gradientOfAppBar = LinearGradient(
    colors: [
      Color.fromRGBO(0, 113, 212, 1),
      Color.fromRGBO(25, 0, 126, 1),
    ],
    begin: Alignment.topLeft,
    end: Alignment.bottomRight,
  );
  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.width;
    return Container(
      decoration: const BoxDecoration(
        gradient: gradientOfAppBar,
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
        title: Container(
          padding: EdgeInsets.only(
              top: heightOfScreen * 0.05, left: widthOfScreen * 0.08),
          alignment: Alignment.bottomCenter,
          child: const Text(
            'Tamrina',
            style: TextStyle(
              color: Color(0xFFFCFFFF),
              fontSize: 53.75,
              fontFamily: 'vapetla',
              fontWeight: FontWeight.w400,
              height: 0,
            ),
          ),
        ),
        actions: [
          Container(
            padding: EdgeInsets.only(right: widthOfScreen * 0.085),
            child: IconButton(
              onPressed: () {
                Navigator.pushNamed(context, Information.routeName);
              },
              icon: Icon(Icons.settings, size: widthOfScreen * 0.11),
              color: Colors.white,
            ),
          ),
        ],
        leading: Container(
          padding: EdgeInsets.only(left: widthOfScreen * 0.085),
          child: IconButton(
            icon: const Icon(Icons.home),
            onPressed: () {
              Navigator.pushNamed(context, Sara.routeName);
            },
            style: ButtonStyle(
              iconSize: MaterialStateProperty.all<double>(widthOfScreen * 0.11),
              iconColor: MaterialStateProperty.all<Color?>(Colors.white),
            ),
          ),
        ),
      ),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(100);
}
