import 'dart:io';

// import 'package:file_picker/file_picker.dart';
// import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
// import 'package:flutter/widgets.dart';
import 'package:flutter_application_1/details/signup.dart';

class Information extends StatefulWidget {
  static const String routeName = 'information';
  const Information({super.key});

  static const infoStyle = TextStyle(
      fontSize: 15.0,
      fontFamily: 'vazir',
      fontWeight: FontWeight.w400,
      color: Colors.black);

  @override
  State<Information> createState() => _InformationState();
}

class _InformationState extends State<Information> {
  File? image;
  // Reusable gradient decoration
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(255, 93, 0, 255),
            Color.fromARGB(255, 131, 58, 180),
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
        backgroundColor: Colors.transparent, // Transparent to show gradient
        body: SingleChildScrollView(
          child: Center(
            // Center the content horizontally
            child: Column(
              children: [
                const SizedBox(
                  height: 50.0,
                ),
                Stack(
                  children: [
                    const CircleAvatar(
                      radius: 75.0,
                      backgroundImage: AssetImage('assets/images/mypic.jpg'),
                    ),
                    Positioned(
                      bottom: 0,
                      right: 0,
                      child: Container(
                        alignment: Alignment.center,
                        width: 40,
                        height: 40,
                        decoration: const ShapeDecoration(
                          color: Color(0xFF24201D),
                          shape: OvalBorder(),
                        ),
                        child: Center(
                          child: IconButton(
                            onPressed: () {},
                            icon: const Icon(
                              Icons.camera_alt_rounded,
                              color: Colors.white,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                const Text(
                  'سید امیرحسین اشرفیان',
                  style: TextStyle(
                    fontSize: 25.0,
                    color: Colors.white,
                    fontFamily: 'vazir',
                  ),
                ),
                const SizedBox(
                  height: 10.0,
                ),
                const Text(
                  'دانشجو',
                  style: TextStyle(
                      fontSize: 25.0, color: Colors.white, fontFamily: 'vazir'),
                ),
                const SizedBox(
                  height: 20.0,
                ),
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 20.0),
                  child: Container(
                    padding: const EdgeInsets.all(20.0),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(35),
                      color: Colors.white,
                    ),
                    child: Column(
                      children: <Widget>[
                        const Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            Text(
                              'شماره دانشجویی',
                              style: Information.infoStyle,
                            ),
                            Text(
                              '402243035',
                              style: Information.infoStyle,
                            ),
                          ],
                        ),
                        const Fasel(),
                        Container(
                          width: double.infinity,
                          height: 1,
                          color: Colors.black.withOpacity(0.1),
                        ),
                        const Fasel(),
                        const Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            Text(
                              'ترم جاری',
                              style: Information.infoStyle,
                            ),
                            Text(
                              'بهار 1402-1403',
                              style: Information.infoStyle,
                            ),
                          ],
                        ),
                        const Fasel(),
                        Container(
                          width: double.infinity,
                          height: 1,
                          color: Colors.black.withOpacity(0.1),
                        ),
                        const Fasel(),
                        const Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            Text(
                              'تعداد واحد',
                              style: Information.infoStyle,
                            ),
                            Text(
                              '16',
                              style: Information.infoStyle,
                            ),
                          ],
                        ),
                        const Fasel(),
                        Container(
                          width: double.infinity,
                          height: 1,
                          color: Colors.black.withOpacity(0.1),
                        ),
                        const Fasel(),
                        const Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            Text(
                              'معدل کل',
                              style: Information.infoStyle,
                            ),
                            Text(
                              '19.01',
                              style: Information.infoStyle,
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                ),
                const SizedBox(height: 30),
                Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 20.0),
                  child: Container(
                    padding: const EdgeInsets.all(10),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(35),
                      color: Colors.white,
                    ),
                    child: Column(
                      children: <Widget>[
                        TextButton(
                          onPressed: () {},
                          style: TextButton.styleFrom(
                            alignment: Alignment.centerRight,
                          ),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              const Text('ویرایش مشخصات',
                                  style: Information.infoStyle),
                              const SizedBox(width: 8),
                              Stack(
                                alignment: Alignment.center,
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(
                                        right: 1, bottom: 1),
                                    width: 30,
                                    height: 30,
                                    decoration: ShapeDecoration(
                                      color: const Color(0xFF553772),
                                      shape: RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(10),
                                      ),
                                    ),
                                  ),
                                  const Icon(
                                    Icons.edit,
                                    color: Colors.white,
                                  ),
                                ],
                              ),
                            ],
                          ),
                        ),
                        Container(
                          width: double.infinity,
                          height: 1,
                          color: Colors.black.withOpacity(0.1),
                        ),
                        TextButton(
                          onPressed: () {},
                          style: TextButton.styleFrom(
                            alignment: Alignment.centerRight,
                          ),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              const Text('تغییر رمز عبور',
                                  style: Information.infoStyle),
                              const SizedBox(width: 8),
                              Stack(
                                alignment: Alignment.center,
                                children: [
                                  Container(
                                    margin: const EdgeInsets.only(
                                        right: 1, bottom: 1),
                                    width: 30,
                                    height: 30,
                                    decoration: ShapeDecoration(
                                      color: const Color(0xFF553772),
                                      shape: RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(10),
                                      ),
                                    ),
                                  ),
                                  const Icon(
                                    Icons.lock,
                                    color: Colors.white,
                                  ),
                                ],
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
                const SizedBox(
                  height: 100.0,
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    minimumSize: const Size(340.0, 50),
                    backgroundColor: Colors.red,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(20.0),
                    ),
                  ),
                  onPressed: () {
                    Navigator.pushNamed(context, Signup.routeName);
                  },
                  child: const Padding(
                    padding: EdgeInsets.symmetric(vertical: 10.0),
                    child: Text(
                      'حذف حساب کاربری',
                      textAlign: TextAlign.center,
                      style: TextStyle(
                        fontFamily: 'pinar',
                        color: Colors.white,
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

class Fasel extends StatelessWidget {
  const Fasel({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return const SizedBox(
      height: 7.5,
    );
  }
}