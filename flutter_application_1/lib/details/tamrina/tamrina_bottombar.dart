import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/kara/kara.dart';

import '../khabara/khabara.dart';
import '../klasa/klasa.dart';
import '../sara/sara.dart';

// ignore: must_be_immutable
class TamrinaBottomBar extends StatefulWidget {
  const TamrinaBottomBar({super.key});

  @override
  State<TamrinaBottomBar> createState() => _TamrinaBottomBarState();
}

class _TamrinaBottomBarState extends State<TamrinaBottomBar> {
  static const gradientOfBottomBar = LinearGradient(
    colors: [
      Color.fromRGBO(0, 113, 212, 1),
      Color.fromRGBO(25, 0, 126, 1),
    ],
    begin: Alignment.topLeft,
    end: Alignment.bottomRight,
  );

  static const titlesTextStyle = TextStyle(
    fontSize: 15,
    color: Colors.white,
    fontFamily: 'vazir',
    fontWeight: FontWeight.w600,
  );
  static const otherIconeColor = Color.fromRGBO(227, 212, 212, 1);
  static const selectedColor = Color.fromARGB(255, 231, 194, 58);
  static const shadowOsIcone = [
    Shadow(
      color: Colors.black,
      offset: Offset(2.0, 2.0),
      blurRadius: 4.0,
    ),
  ];

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.width;
    final iconsSize1 = widthOfScreen * 0.15;
    final iconsSize2 = widthOfScreen * 0.12;
    final iconsSize3 = widthOfScreen * 0.10;
    final leftAndRight1 = widthOfScreen * 0.41;
    final leftAndRight2 = widthOfScreen * 0.23;
    final leftAndRight3 = widthOfScreen * 0.07;

    return BottomSheet(
      builder: (context) {
        return Stack(
          children: [
            Container(
              decoration: const BoxDecoration(
                gradient: gradientOfBottomBar,
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(50),
                  topRight: Radius.circular(50),
                ),
              ),
              height: 130,
              width: widthOfScreen,
            ),
            Positioned(
              left: leftAndRight1,
              bottom: heightOfScreen * 0.07,
              child: Column(
                children: [
                  IconButton(
                    onPressed: () {
                      Navigator.pushNamed(context, Sara.routeName);
                    },
                    icon: Icon(
                      Icons.home,
                      size: iconsSize1,
                      color: otherIconeColor,
                      shadows: shadowOsIcone,
                    ),
                  ),
                  Transform.translate(
                    offset: const Offset(0, -10),
                    child: const Text(
                      'سرا',
                      style: titlesTextStyle,
                    ),
                  ),
                ],
              ),
            ),
            Positioned(
              right: leftAndRight3,
              bottom: heightOfScreen * 0.076,
              child: Column(
                children: [
                  IconButton(
                    onPressed: () {},
                    icon: Icon(
                      Icons.assignment,
                      size: iconsSize3,
                      color: selectedColor,
                      shadows: shadowOsIcone,
                    ),
                  ),
                  Transform.translate(
                    offset: const Offset(0, -8),
                    child: const Text(
                      'تمرینا',
                      style: titlesTextStyle,
                    ),
                  ),
                ],
              ),
            ),
            Positioned(
              left: leftAndRight3,
              bottom: heightOfScreen * 0.079,
              child: Column(
                children: [
                  IconButton(
                    onPressed: () {
                      Navigator.pushNamed(context, Khabara.routeName);
                    },
                    icon: Icon(
                      Icons.newspaper_sharp,
                      size: iconsSize3,
                      color: otherIconeColor,
                      shadows: shadowOsIcone,
                    ),
                  ),
                  Transform.translate(
                    offset: const Offset(0, -8.0),
                    child: const Text(
                      'خبرا',
                      style: titlesTextStyle,
                    ),
                  ),
                ],
              ),
            ),
            Positioned(
              left: leftAndRight2,
              bottom: heightOfScreen * 0.07,
              child: Column(
                children: [
                  IconButton(
                    onPressed: () {
                      Navigator.pushNamed(context, Kara.routeName);
                    },
                    icon: Icon(
                      Icons.checklist_outlined,
                      size: iconsSize2,
                      color: otherIconeColor,
                      shadows: shadowOsIcone,
                    ),
                  ),
                  Transform.translate(
                    offset: const Offset(0, -10),
                    child: const Text(
                      'کارا',
                      style: titlesTextStyle,
                    ),
                  ),
                ],
              ),
            ),
            Positioned(
              right: leftAndRight2,
              bottom: heightOfScreen * 0.076,
              child: Column(
                children: [
                  IconButton(
                    onPressed: () {
                      Navigator.pushNamed(context, Klasa.routeName);
                    },
                    icon: Icon(
                      Icons.school,
                      size: iconsSize2,
                      color: otherIconeColor,
                      shadows: shadowOsIcone,
                    ),
                  ),
                  Transform.translate(
                    offset: const Offset(0, -8),
                    child: const Text(
                      'کلاسا',
                      style: titlesTextStyle,
                    ),
                  ),
                ],
              ),
            ),
          ],
        );
      },
      onClosing: () {},
    );
  }
}
