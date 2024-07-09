import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:url_launcher/url_launcher.dart';

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
      color: Colors.black,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          iconTheme: const IconThemeData(
            color: Colors.white, // تغییر رنگ دکمه بازگشت
          ),
          centerTitle: true,
          title: const Text(
            'ارتباط با ما',
            style: TextStyle(
              color: Color.fromARGB(255, 255, 255, 255),
              fontSize: 30,
              fontFamily: 'vazirb',
            ),
          ),
          backgroundColor: Colors.transparent,
          toolbarHeight: 40,
        ),
        body: SingleChildScrollView(
          child: Column(
            children: [
              SizedBox(
                height: 220,
                width: 300,
                child: Image.asset(
                  "assets/images/support.jpg",
                  fit: BoxFit.fitHeight,
                ),
              ),
              const SizedBox(
                height: 10,
              ),
              Container(
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
                height: 560,
                child: Column(
                  children: <Widget>[
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
                                margin:
                                    const EdgeInsets.only(right: 1, bottom: 1),
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
                                margin:
                                    const EdgeInsets.only(right: 1, bottom: 1),
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
                                margin:
                                    const EdgeInsets.only(right: 1, bottom: 1),
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
                                margin:
                                    const EdgeInsets.only(right: 1, bottom: 1),
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
                      height: 10,
                    ),
                    ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        minimumSize: const Size(220, 50),
                        backgroundColor: Colors.white,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20.0),
                        ),
                      ),
                      onPressed: () async {
                        final linkToOpen = Uri.parse(
                            'https://news.sbu.ac.ir/%D8%A7%D8%B1%D8%AA%D8%A8%D8%A7%D8%B7-%D8%A8%D8%A7-%D9%85%D8%A7');
                        await launchUrl(linkToOpen);
                      },
                      child: const Padding(
                        padding: EdgeInsets.symmetric(vertical: 10.0),
                        child: Text(
                          'ارتباط مشتقیم با دانشگاه',
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
            ],
          ),
        ),
      ),
    );
  }
}
