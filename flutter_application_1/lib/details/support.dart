import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Support extends StatefulWidget {
  const Support({super.key});
  static const String routeName = "support";

  @override
  State<Support> createState() => _SupportState();
}

class _SupportState extends State<Support> {
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(212, 255, 255, 255),
            Color.fromARGB(255, 15, 199, 255),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      );

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          centerTitle: true,
          title: const Text(
            'ارتباط با ما',
            style: TextStyle(
              color: Color.fromARGB(255, 0, 0, 0),
              fontSize: 30,
              fontFamily: 'vazir',
            ),
          ),
          backgroundColor: Colors.transparent,
          toolbarHeight: 40,
        ),
        body: Padding(
          padding: const EdgeInsets.only(
            top: 70,
            bottom: 0,
          ),
          child: Container(
            decoration: const BoxDecoration(
              boxShadow: <BoxShadow>[
                // BoxShadow(
                //   color: Colors.blueGrey,
                //   offset: Offset.infinite,
                // ),
              ],
              color: Color.fromARGB(255, 33, 32, 32),
              borderRadius: BorderRadius.only(
                topRight: Radius.circular(40),
                topLeft: Radius.circular(40),
                bottomLeft: Radius.circular(5),
                bottomRight: Radius.circular(5),
              ),
            ),
            height: 750,
            child: Column(
              children: <Widget>[
                const SizedBox(
                  height: 50,
                ),
                TextButton(
                  onPressed: () {},
                  style: TextButton.styleFrom(
                    alignment: Alignment.centerRight,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Stack(
                        alignment: Alignment.center,
                        children: [
                          Container(
                            margin: const EdgeInsets.only(right: 1, bottom: 1),
                            width: 60,
                            height: 60,
                            decoration: ShapeDecoration(
                              color: Colors.transparent,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(50),
                              ),
                            ),
                          ),
                          const Icon(
                            Icons.phone_in_talk_rounded,
                            size: 50,
                            color: Colors.white,
                          ),
                        ],
                      ),
                      const SizedBox(width: 8),
                      const Text(
                        '+989214398263',
                        style: TextStyle(
                          fontSize: 28,
                          color: Colors.white,
                          fontFamily: 'phone',
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                Container(
                  width: 300,
                  height: 1,
                  color: Colors.black.withOpacity(0.6),
                ),
                const SizedBox(
                  height: 20,
                ),
                TextButton(
                  onPressed: () {},
                  style: TextButton.styleFrom(
                    alignment: Alignment.centerRight,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Stack(
                        alignment: Alignment.center,
                        children: [
                          Container(
                            margin: const EdgeInsets.only(right: 1, bottom: 1),
                            width: 60,
                            height: 60,
                            decoration: ShapeDecoration(
                              color: Colors.transparent,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(50),
                              ),
                            ),
                          ),
                          const Icon(
                            Icons.email,
                            size: 50,
                            color: Colors.white,
                          ),
                        ],
                      ),
                      const SizedBox(width: 8),
                      const Text(
                        'sbu@gmail.com',
                        style: TextStyle(
                          fontSize: 28,
                          color: Colors.white,
                          fontFamily: 'phone',
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                Container(
                  width: 300,
                  height: 1,
                  color: Colors.black.withOpacity(0.6),
                ),
                const SizedBox(
                  height: 20,
                ),
                TextButton(
                  onPressed: () {},
                  style: TextButton.styleFrom(
                    alignment: Alignment.centerRight,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Stack(
                        alignment: Alignment.center,
                        children: [
                          Container(
                            margin: const EdgeInsets.only(right: 1, bottom: 1),
                            width: 60,
                            height: 60,
                            decoration: ShapeDecoration(
                              color: Colors.transparent,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(50),
                              ),
                            ),
                          ),
                          const Icon(
                            Icons.other_houses_outlined,
                            size: 50,
                            color: Colors.white,
                          ),
                        ],
                      ),
                      const SizedBox(width: 8),
                      const Text(
                        '1983969411',
                        style: TextStyle(
                          fontSize: 28,
                          color: Colors.white,
                          fontFamily: 'phone',
                        ),
                      ),
                      const SizedBox(
                        width: 70,
                      ),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                Container(
                  width: 300,
                  height: 1,
                  color: Colors.black.withOpacity(0.6),
                ),
                const SizedBox(
                  height: 20,
                ),
                TextButton(
                  onPressed: () {},
                  style: TextButton.styleFrom(
                    alignment: Alignment.centerRight,
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Stack(
                        alignment: Alignment.center,
                        children: [
                          Container(
                            margin: const EdgeInsets.only(right: 1, bottom: 1),
                            width: 60,
                            height: 60,
                            decoration: ShapeDecoration(
                              color: Colors.transparent,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(50),
                              ),
                            ),
                          ),
                          const Icon(
                            Icons.fax_outlined,
                            size: 50,
                            color: Colors.white,
                          ),
                        ],
                      ),
                      const SizedBox(width: 8),
                      const Text(
                        '+22431607',
                        style: TextStyle(
                          fontSize: 28,
                          color: Colors.white,
                          fontFamily: 'phone',
                        ),
                      ),
                      const SizedBox(
                        width: 75.0,
                      ),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 20,
                ),
                Container(
                  width: 300,
                  height: 1,
                  color: Colors.black.withOpacity(0.6),
                ),
                const SizedBox(
                  height: 100,
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    minimumSize: const Size(220, 50),
                    backgroundColor: Colors.white,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20.0),
                    ),
                  ),
                  onPressed: () {},
                  child: const Padding(
                    padding: EdgeInsets.symmetric(vertical: 10.0),
                    child: Text(
                      'ارسال پیام',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontFamily: 'pinar',
                        color: Colors.black,
                        fontSize: 30,
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
