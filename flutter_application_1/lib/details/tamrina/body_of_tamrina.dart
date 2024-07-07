import 'package:flutter/material.dart';

import '../classes/student.dart';

class BodyOfTamrina extends StatefulWidget {
  const BodyOfTamrina({super.key});

  @override
  State<BodyOfTamrina> createState() => _BodyOfTamrinaState();
}

class _BodyOfTamrinaState extends State<BodyOfTamrina> {
  Widget selectedItem = const ActivesPr();
  int selected = 1;
  int selected2 = 1;

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return SingleChildScrollView(
      child: Stack(
        children: [
          Padding(
            padding: EdgeInsets.only(
              top: heightOfScreen * 0.08,
              left: widthOfScreen * 0.05,
              right: widthOfScreen * 0.05,
            ),
            child: Container(
              decoration: BoxDecoration(
                color: const Color.fromARGB(255, 111, 207, 255),
                borderRadius: const BorderRadius.only(
                  topLeft: Radius.circular(25),
                  topRight: Radius.circular(25),
                ),
                border: Border.all(),
              ),
              constraints: BoxConstraints(minHeight: heightOfScreen),
              padding: EdgeInsets.only(top: heightOfScreen * 0.09),
              child: selectedItem,
            ),
          ),
          Positioned(
              top: heightOfScreen * 0.135,
              left: widthOfScreen * 0.1,
              right: widthOfScreen * 0.1,
              child: Container(
                height: 1,
                color: Colors.black,
                width: widthOfScreen * 0.75,
              )),
          Positioned(
            top: heightOfScreen * 0.06,
            right: widthOfScreen * 0.09,
            child: Row(
              textDirection: TextDirection.rtl,
              children: [
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.05,
                    width: widthOfScreen * 0.35,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      // ignore: prefer_const_constructors
                      color: (selected == 1)
                          ? const Color(0xFFE4014E)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'تمرین های فعال',
                      style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.017,
                          fontWeight: FontWeight.bold,
                          color: Colors.white),
                    ),
                  ),
                  onTap: () {
                    if (selected != 1) {
                      setState(() {
                        selected = 1;
                        //selectedItem =
                      });
                    }
                  },
                ),
                SizedBox(
                  width: widthOfScreen * 0.01,
                ),
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.05,
                    width: widthOfScreen * 0.35,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: (selected == 2)
                          ? const Color(0xFFE4014E)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'تمرین های غیرفعال',
                      style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.017,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ),
                  onTap: () {
                    if (selected != 2) {
                      setState(() {
                        selected = 2;
                        //selectedItem =
                      });
                    }
                  },
                ),
              ],
            ),
          ),
          Positioned(
            top: heightOfScreen * 0.120,
            right: widthOfScreen * 0.15,
            child: Row(
              textDirection: TextDirection.rtl,
              children: [
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.03,
                    width: widthOfScreen * 0.15,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      // ignore: prefer_const_constructors
                      color: (selected2 == 1)
                          ? const Color(0xFF2DD634)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'امروز',
                      style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.017,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ),
                  onTap: () {
                    if (selected2 != 1) {
                      setState(() {
                        selected2 = 1;
                        //selectedItem =
                      });
                    }
                  },
                ),
                SizedBox(
                  width: widthOfScreen * 0.01,
                ),
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.03,
                    width: widthOfScreen * 0.25,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: (selected2 == 2)
                          ? const Color(0xFF2DD634)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'انتخاب تاریخ',
                      style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.017,
                          fontWeight: FontWeight.bold,
                          color: Colors.white),
                    ),
                  ),
                  onTap: () {
                    if (selected2 != 2) {
                      setState(() {
                        selected2 = 2;
                        //selectedItem =
                      });
                    }
                  },
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}

class ActivesPr extends StatefulWidget {
  const ActivesPr({super.key});

  @override
  State<ActivesPr> createState() => _ActivesPrState();
}

class _ActivesPrState extends State<ActivesPr> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}

class InActivesPr extends StatefulWidget {
  const InActivesPr({super.key});

  @override
  State<InActivesPr> createState() => _InActivesPrState();
}

class _InActivesPrState extends State<InActivesPr> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
