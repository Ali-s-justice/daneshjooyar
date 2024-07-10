import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:flutter_application_1/details/sara/sara.dart';
import 'package:flutter_application_1/details/user_data.dart';
import '../services/server_connection_info.dart';
import 'my_app_bar.dart';
import 'my_bottom.dart';

class Login extends StatefulWidget {
  static const String routeName = 'login';
  const Login({super.key});

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  TextEditingController usernameOrStudentCodeController =
      TextEditingController();
  TextEditingController passwordController = TextEditingController();

  static const Color formColor = Color.fromARGB(255, 239, 227, 233);
  static const TextStyle formTextStyle = TextStyle(
    fontFamily: 'vazir',
    fontWeight: FontWeight.w900,
    fontSize: 22.0,
  );

  static const bottomnDecoration = BoxDecoration(
    gradient: LinearGradient(
      colors: [
        Color.fromARGB(255, 93, 0, 255),
        Color.fromARGB(255, 15, 199, 255),
      ],
      begin: Alignment.topLeft,
      end: Alignment.bottomRight,
    ),
    borderRadius: BorderRadius.all(Radius.circular(30.0)),
  );
  final _keyform = GlobalKey<FormState>();

  bool visable = true;
  String? response;
  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return Scaffold(
      appBar: const SignUpLoginAppBar(),
      bottomNavigationBar: const SignUpLoginBottomBar(),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            const SizedBox(
              height: 120,
            ),
            const Text(
              'به دَرسا خوش آمدید!',
              textDirection: TextDirection.rtl,
              style: TextStyle(
                fontFamily: 'dastnevis',
                fontSize: 37.0,
              ),
            ),
            const SizedBox(
              height: 10,
            ),
            Form(
              key: _keyform,
              child: Padding(
                padding: const EdgeInsets.all(40.0),
                child: Column(
                  textDirection: TextDirection.rtl,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    TextFormField(
                      controller: usernameOrStudentCodeController,
                      inputFormatters: [
                        FilteringTextInputFormatter.deny(' '),
                      ],
                      validator: (String? value) {
                        if (value!.isEmpty) {
                          return '!نام کاربری نمیتواند خالی باشد ';
                        } else if (!RegExp("^([A-Za-z0-9]){4,20}\$")
                            .hasMatch(value)) {
                          return ".نام کاربری نامعتبر است. فقط از کاراکترهای مجاز استفاده کنید";
                        } else {
                          return null;
                        }
                      },
                      decoration: InputDecoration(
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(25),
                          ),
                          filled: true,
                          fillColor: formColor,
                          // hintText : 'سید امیرحسین اشرفیان',

                          label: Container(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 12.0),
                            alignment: Alignment.centerRight,
                            child: const Text(
                              'نام کاربری / شماره دانشجویی',
                              textDirection: TextDirection.rtl,
                              style: formTextStyle,
                            ),
                          )

                          // labelText: 'نام و نام خانوادگی',
                          ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Stack(children: [
                      TextFormField(
                        controller: passwordController,
                        validator: (String? value) {
                          if (!RegExp(
                                  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                              .hasMatch(value!)) {
                            return "!رمز عبور نامعتبر است";
                          } else if (value
                              .contains(usernameOrStudentCodeController.text)) {
                            return ".رمز عبور نمیتواند شامل نام کاربری باشد";
                          } else {
                            return null;
                          }
                        },
                        obscureText: visable,
                        textDirection: TextDirection.ltr,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(25),
                          ),
                          filled: true,
                          fillColor: formColor,
                          label: Container(
                            padding: const EdgeInsets.only(right: 35.0),
                            alignment: Alignment.centerRight,
                            child: const Text(
                              'رمز عبور',
                              style: formTextStyle,
                            ),
                          ),
                        ),
                      ),
                      Container(
                        alignment: Alignment.centerRight,
                        padding: const EdgeInsets.only(top: 7.5),
                        child: IconButton(
                          icon: Icon(visable
                              ? Icons.visibility_off
                              : Icons.visibility),
                          onPressed: () {
                            setState(() {
                              visable = !visable;
                            });
                          },
                        ),
                      ),
                    ]),
                    const SizedBox(
                      height: 50.0,
                    ),
                    Container(
                      decoration: bottomnDecoration,
                      child: ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          minimumSize: const Size(220, 50),
                          backgroundColor: Colors.transparent,
                          shadowColor: Colors.transparent,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(20.0),
                          ),
                        ),
                        onPressed: () async {
                          if (_keyform.currentState!.validate()) {
                            UserData.studentCode =
                                usernameOrStudentCodeController.text;
                            String message = '-';

                            message = await login();
                            if (message.length > 5) {
                              UserData.studentCode =
                                  message.split("//").elementAt(1);
                            }
                            print(
                                '-+++++++++++++++++++++++++++++++++++++++++++++++++++++++ $message');

                            if (message.split("//").elementAt(0) == '500') {
                              // ignore: use_build_context_synchronously
                              Navigator.pushNamed(context, Sara.routeName);
                            } else if (message == '401') {
                              // ignore: use_build_context_synchronously
                              ScaffoldMessenger.of(context).showSnackBar(
                                SnackBar(
                                  elevation: 20,
                                  width: widthOfScreen * 0.8,
                                  backgroundColor: Colors.red,
                                  behavior: SnackBarBehavior.floating,
                                  shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(5),
                                  ),
                                  content: Center(
                                    child: Text(
                                      'شما هنوز ثبت نام نکرده اید. لطفا در سامانه ثبت نام کنید',
                                      textDirection: TextDirection.ltr,
                                      style: TextStyle(
                                          color: Colors.white,
                                          fontSize: heightOfScreen * 0.014,
                                          fontFamily: 'vazir',
                                          fontWeight: FontWeight.w500),
                                    ),
                                  ),
                                ),
                              );
                            } else if (message == '402') {
                              // ignore: use_build_context_synchronously
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
                                      '.رمزعبور وارد شده اشتباه است',
                                      textDirection: TextDirection.ltr,
                                      style: TextStyle(
                                          color: Colors.white,
                                          fontSize: heightOfScreen * 0.018,
                                          fontFamily: 'vazir',
                                          fontWeight: FontWeight.w500),
                                    ),
                                  ),
                                ),
                              );
                            } else if (message == '400') {
                              // ignore: use_build_context_synchronously
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
                                      '.دانشجویی با این مشخصات در سامانه ثبت نیست',
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
                          }
                        },
                        child: const Padding(
                          padding: EdgeInsets.symmetric(vertical: 10.0),
                          child: Text(
                            'ورود',
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              fontFamily: 'pinar',
                              color: Colors.white,
                              fontSize: 30,
                            ),
                          ),
                        ),
                      ),
                    )
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  //connect to server

  Future<String> login() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port).then(
      (serverSocket) {
        serverSocket.write(
            'login//${usernameOrStudentCodeController.text}//${passwordController.text}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              response = utf8.decode(socketResponse);
            });
            completer.complete(response);
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
}
