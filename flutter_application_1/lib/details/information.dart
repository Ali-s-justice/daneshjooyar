import 'package:flutter_application_1/details/classes/student.dart';
import 'package:flutter_application_1/details/edit_informations.dart';
import 'package:flutter_application_1/details/sara/sara.dart';
import 'package:flutter_application_1/details/support.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/signup.dart';

class Information extends StatefulWidget {
  static const String routeName = 'information';
  const Information({super.key});
  static const alertDilogTextStyle = TextStyle(
    fontSize: 13.0,
    fontFamily: 'vazir',
  );
  static const informationTextstyle = TextStyle(
    fontSize: 25.0,
    color: Colors.black,
    fontFamily: 'vazir',
  );
  static const infoStyle = TextStyle(
      fontSize: 15.0,
      fontFamily: 'vazir',
      fontWeight: FontWeight.w400,
      color: Colors.black);

  @override
  State<Information> createState() => _InformationState();
}

class _InformationState extends State<Information> {
  // Reusable gradient decoration
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(212, 255, 255, 255),
            Color.fromARGB(255, 15, 199, 255),
          ],
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
        ),
      );
  static const color = Color.fromRGBO(230, 230, 250, 1.0);

  String userName = 'amirhossein';
  String name = 'سید امیرحسین اشرفیان';
  String id = 'amirhossein';
  String termInfo = 'بهار 1403_1402';
  String creditNum = '16';
  String allAverage = '19.99';

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          actions: <Widget>[
            IconButton(
              icon: const Icon(Icons.home),
              onPressed: () {
                //it isn't complete , must be editted
                Navigator.pushNamed(context, Sara.routeName);
              },
              iconSize: 35,
              color: Colors.black,
            ),
            const SizedBox(
              width: 15,
            ),
          ],
          automaticallyImplyLeading: false,
          backgroundColor: Colors.transparent,
          toolbarHeight: 37,
        ),
        body: SingleChildScrollView(
          child: Center(
            // Center the content horizontally
            child: Column(
              children: [
                const Stack(
                  children: [
                    CircleAvatar(
                      radius: 95.0,
                      backgroundImage: AssetImage('assets/images/mypic.jpg'),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                Text(
                  userName,
                  style: const TextStyle(
                    fontSize: 25.0,
                    color: Colors.black,
                    fontFamily: 'vazir',
                  ),
                ),
                // const SizedBox(
                //   height: 10.0,
                // ),
                const Text(
                  'دانشجو',
                  style: TextStyle(
                      fontSize: 25.0, color: Colors.black, fontFamily: 'vazir'),
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
                      color: color,
                    ),
                    child: Column(
                      children: <Widget>[
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            const Text(
                              'نام و نام خانوادگی',
                              style: Information.infoStyle,
                            ),
                            Text(
                              name,
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
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            const Text(
                              'شماره دانشجویی',
                              style: Information.infoStyle,
                            ),
                            Text(
                              id,
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
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            const Text(
                              'تعداد واحد',
                              style: Information.infoStyle,
                            ),
                            Text(
                              creditNum,
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
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            const Text(
                              'معدل کل',
                              style: Information.infoStyle,
                            ),
                            Text(
                              allAverage,
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
                      color: color,
                    ),
                    child: Column(
                      children: <Widget>[
                        TextButton(
                          onPressed: () {
                            final Student currentStudent = Student();
                            currentStudent.username = userName;
                            currentStudent.name = name;
                            currentStudent.studenCode = id;
                            currentStudent.numberOfCourseUnit =
                                int.tryParse(creditNum);
                            currentStudent.totalAverage =
                                double.tryParse(allAverage);
                            Navigator.pushNamed(
                              context,
                              EditInformation.routeName,
                              arguments: currentStudent,
                            );
                          },
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
                                      color:
                                          const Color.fromARGB(255, 62, 32, 91),
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
                          onPressed: () {
                            setState(() {
                              Navigator.pushNamed(context, Support.routeName);
                            });
                          },
                          style: TextButton.styleFrom(
                            alignment: Alignment.centerRight,
                          ),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.end,
                            children: [
                              const Text('ارتباط با ما',
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
                                      color:
                                          const Color.fromARGB(255, 62, 32, 91),
                                      shape: RoundedRectangleBorder(
                                        borderRadius: BorderRadius.circular(10),
                                      ),
                                    ),
                                  ),
                                  const Icon(
                                    Icons.support_agent_rounded,
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
                  height: 30.0,
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    minimumSize: const Size(250.0, 40),
                    backgroundColor: Colors.red,
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(50.0),
                    ),
                  ),
                  onPressed: () {
                    alertDialog(context);
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

  Future<dynamic> alertDialog(BuildContext context) {
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          content: const Text(
            'آیا از حذف حساب کاربری خود اطمینان دارید ؟',
            textDirection: TextDirection.rtl,
            style: Information.alertDilogTextStyle,
            textAlign: TextAlign.justify,
          ),
          actions: <Widget>[
            Row(
              mainAxisSize: MainAxisSize.max,
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                TextButton(
                  onPressed: () {
                    Navigator.pop(context);
                  },
                  child: const Text('خیر'),
                ),
                TextButton(
                  onPressed: () {
                    Navigator.pushNamed(context, Signup.routeName);
                    //ساز و کار حذف اطلاعات دانشجو در بک اند در این قسمت پیاده سازی شود
                  },
                  child: const Text('بله'),
                ),
              ],
            ),
          ],
        );
      },
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
