import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter_application_1/details/classes/student.dart';
import 'package:flutter_application_1/details/edit_informations.dart';
import 'package:flutter_application_1/details/sara/sara.dart';
import 'package:flutter_application_1/details/support.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/signup.dart';
import 'package:flutter_application_1/details/user_data.dart';

import '../services/server_connection_info.dart';

class Information extends StatefulWidget {
  static const String routeName = 'information';
  const Information({super.key});
  static const alertDilogTextStyle = TextStyle(
    fontSize: 13.0,
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
            Color.fromRGBO(0, 113, 212, 1),
            Color.fromRGBO(25, 0, 126, 1),
          ],
          begin: Alignment.topCenter,
          end: Alignment.bottomCenter,
        ),
      );
  static const informationTextstyle = TextStyle(
    fontSize: 25.0,
    color: Color.fromARGB(255, 255, 255, 255),
    fontFamily: 'phone',
  );
  static const color = Color.fromRGBO(230, 230, 250, 1.0);

  String userName = '-';
  String name = '-';
  String id = '-';
  String termInfo = '-';
  String creditNum = '-';
  String allAverage = '-';
  String responseInfoData = '-';
  String responseDeleteAccount = '-';

  @override
  void initState() {
    super.initState();
    loadData();
  }

  void loadData() async {
    String message;
    message = await fetchInfoData();

    List<String> arrData = message.split("//");
    userName = arrData.elementAt(0);
    name = arrData.elementAt(1);
    id = arrData.elementAt(2);
    termInfo = arrData.elementAt(3);
    creditNum = arrData.elementAt(4);
    allAverage = arrData.elementAt(5);
  }

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
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
                Stack(
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(100),
                        border: Border.all(
                          color: Colors.black,
                          width: 5,
                        ),
                      ),
                      child: const CircleAvatar(
                        radius: 95.0,
                        backgroundImage:
                            AssetImage('assets/images/userinfo.jpg'),
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 20),
                Text(
                  userName,
                  style: informationTextstyle,
                ),
                // const SizedBox(
                //   height: 10.0,
                // ),
                const Text(
                  'دانشجو',
                  style: informationTextstyle,
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
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          textDirection: TextDirection.rtl,
                          children: [
                            const Text(
                              'ترم جاری',
                              style: Information.infoStyle,
                            ),
                            Text(
                              termInfo,
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

  Future<String> fetchInfoData() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('userInfo//${UserData.studentCode}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseInfoData = utf8.decode(socketResponse);
            });
            completer.complete(responseInfoData);
            serverSocket.destroy();
          },
          onError: (error) {
            completer.completeError(error);
            serverSocket.destroy();
          },
          onDone: () {
            if (!completer.isCompleted) {
              completer.complete('null');
            }
          },
        );
      },
    ).catchError((error) {
      completer.completeError(error);
    });

    return completer.future;
  }

  Future<String> deleteAccount() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('deleteAccount//${UserData.studentCode}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseDeleteAccount = utf8.decode(socketResponse);
            });
            completer.complete(responseDeleteAccount);
            serverSocket.destroy();
          },
          onError: (error) {
            completer.completeError(error);
            serverSocket.destroy();
          },
          onDone: () {
            if (!completer.isCompleted) {
              completer.complete('null');
            }
          },
        );
      },
    ).catchError((error) {
      completer.completeError(error);
    });

    return completer.future;
  }

  Future<dynamic> alertDialog(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return showDialog(
      context: context,
      builder: (BuildContext context) {
        return Dialog(
          child: Container(
            height: heightOfScreen * 0.2,
            decoration: ShapeDecoration(
              gradient: const RadialGradient(
                center: Alignment(1, 0.20),
                radius: 0,
                colors: [
                  Color(0xFF0078D9),
                  Color(0xFF0050C1),
                  Color(0xFF311B92)
                ],
              ),
              shape: RoundedRectangleBorder(
                side: const BorderSide(width: 1),
                borderRadius: BorderRadius.circular(29),
              ),
              shadows: const [
                BoxShadow(
                  color: Color(0x3F000000),
                  blurRadius: 4,
                  offset: Offset(0, 4),
                  spreadRadius: 0,
                )
              ],
            ),
            child: Column(
              children: [
                SizedBox(
                  height: heightOfScreen * 0.05,
                ),
                Text(
                  'آیا از حذف حساب کاربری خود اطمینان دارید',
                  style: TextStyle(
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                      fontSize: heightOfScreen * 0.018),
                ),
                SizedBox(
                  height: heightOfScreen * 0.02,
                ),
                Row(
                  textDirection: TextDirection.rtl,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: () async {
                        String message = 'null';
                        message = await deleteAccount();

                        print('++++++++++++++++++++++++++++$message');

                        if (message == "500") {
                          // ignore: use_build_context_synchronously
                          Navigator.pushNamed(context, Signup.routeName);
                        } else {
                          ScaffoldMessenger.of(context).showSnackBar(
                            SnackBar(
                              elevation: 40,
                              width: widthOfScreen * 0.8,
                              backgroundColor: Colors.red,
                              behavior: SnackBarBehavior.floating,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(5),
                              ),
                              content: Center(
                                child: Text(
                                  'عدم ارتباط با سرور',
                                  textDirection: TextDirection.ltr,
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontSize: heightOfScreen * 0.015,
                                      fontFamily: 'vazir',
                                      fontWeight: FontWeight.w500),
                                ),
                              ),
                            ),
                          );
                        }
                      },
                      style: TextButton.styleFrom(
                        backgroundColor: const Color(0xFFE4014E),
                      ),
                      child: Text(
                        'حذف',
                        style: TextStyle(
                          color: Colors.white,
                          fontFamily: 'vazir',
                          fontWeight: FontWeight.bold,
                          fontSize: heightOfScreen * 0.018,
                        ),
                      ),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.1,
                    ),
                    ElevatedButton(
                      onPressed: () {
                        Navigator.of(context).pop();
                      },
                      style: TextButton.styleFrom(
                        backgroundColor: const Color(0xFF2DD634),
                      ),
                      child: Text(
                        'بازگشت',
                        style: TextStyle(
                          color: Colors.white,
                          fontFamily: 'vazir',
                          fontWeight: FontWeight.bold,
                          fontSize: heightOfScreen * 0.018,
                        ),
                      ),
                    ),
                  ],
                )
              ],
            ),
          ),
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
