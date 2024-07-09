import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_application_1/details/information.dart';
import 'package:flutter_application_1/details/user_data.dart';
import 'package:flutter_application_1/services/server_connection_info.dart';
import 'my_app_bar.dart';
import 'my_bottom.dart';

class Signup extends StatefulWidget {
  static const routeName = 'signup';
  const Signup({super.key});

  @override
  State<Signup> createState() => _SignupState();
}

class _SignupState extends State<Signup> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();
  TextEditingController studentCodeController = TextEditingController();

  final _keyform = GlobalKey<FormState>();
  bool visable = true;
  bool visable2 = true;
  static const Color formColor = Color.fromARGB(255, 239, 227, 233);
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
  static const TextStyle formTextStyle = TextStyle(
    fontFamily: 'vazir',
    fontWeight: FontWeight.w900,
    fontSize: 22.0,
  );

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
              height: 60,
            ),
            const Text(
              'ثبت نام در درسا',
              textDirection: TextDirection.rtl,
              style: TextStyle(
                fontFamily: 'dastnevis',
                fontSize: 37.0,
              ),
            ),
            Form(
              key: _keyform,
              child: Padding(
                padding: const EdgeInsets.all(40.0),
                child: Column(
                  textDirection: TextDirection.rtl,
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    // const SizedBox(
                    //   height: 20,
                    // ),
                    TextFormField(
                      controller: usernameController,
                      inputFormatters: [
                        FilteringTextInputFormatter.deny(' '),
                      ],
                      validator: (String? value) {
                        if (value!.isEmpty) {
                          return '!نام کاربری نمیتواند خالی باشد ';
                        } else if (value.length <= 2) {
                          return '.نام کاربری باید بیش از 2 کاراکتر باشد';
                        } else if (!RegExp("^([A-Za-z0-9]){2,22}\$")
                            .hasMatch(value)) {
                          return ".نام کاربری نامعتبر است. فقط از کاراکترهای مجاز استفاده کنید";
                        } else {
                          return null;
                        }
                      },
                      decoration: InputDecoration(
                        //helperText: 'نام کاربری شامل حروف کوچک و بزرگ و اعداد',
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                        filled: true,
                        fillColor: formColor,
                        label: Container(
                          padding: const EdgeInsets.symmetric(horizontal: 12.0),
                          alignment: Alignment.centerRight,
                          child: const Text(
                            'نام کاربری / یوزنیم',
                            textDirection: TextDirection.rtl,
                            style: formTextStyle,
                          ),
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      controller: studentCodeController,
                      validator: (String? value) {
                        if (value!.isEmpty) {
                          return '!شماره دانشجویی نمیتواند خالی باشد';
                        } else {
                          return null;
                        }
                      },
                      inputFormatters: [
                        FilteringTextInputFormatter.digitsOnly,
                        FilteringTextInputFormatter.deny(' '),
                      ],
                      keyboardType: TextInputType.number,
                      decoration: InputDecoration(
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                        filled: true,
                        fillColor: formColor,
                        label: Container(
                          padding: const EdgeInsets.symmetric(horizontal: 12.0),
                          alignment: Alignment.centerRight,
                          child: const Text(
                            'شماره دانشجویی',
                            textDirection: TextDirection.rtl,
                            style: formTextStyle,
                          ),
                        ),
                      ),
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Stack(
                      children: [
                        TextFormField(
                          controller: passwordController,
                          validator: (String? value) {
                            if (!RegExp(
                                    "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                                .hasMatch(value!)) {
                              return "!حداقل 8 حرف شامل حروف کوچک و بزرگ و اعداد";
                            } else if (value
                                .contains(usernameController.text)) {
                              return ".رمز عبور نباید شامل نام کاربری باشد";
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
                            icon: Icon(
                              visable ? Icons.visibility_off : Icons.visibility,
                            ),
                            onPressed: () {
                              setState(() {
                                visable = !visable;
                              });
                            },
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(
                      height: 20,
                    ),
                    Stack(
                      children: [
                        TextFormField(
                          validator: (String? value) {
                            if (value != passwordController.text) {
                              return '.تکرار رمز عبور با رمز عبور باید یکسان باشد';
                            } else if (value!.isEmpty) {
                              return '!تکرار رمز عبور نمیتواند خالی باشد';
                            } else {
                              return null;
                            }
                          },
                          obscureText: visable2,
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
                                'تکرار رمز عبور',
                                style: formTextStyle,
                              ),
                            ),
                          ),
                        ),
                        Container(
                          alignment: Alignment.centerRight,
                          padding: const EdgeInsets.only(top: 7.5),
                          child: IconButton(
                            icon: Icon(
                              visable2
                                  ? Icons.visibility_off
                                  : Icons.visibility,
                            ),
                            onPressed: () {
                              setState(() {
                                visable2 = !visable2;
                              });
                            },
                          ),
                        ),
                      ],
                    ),
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
                            String message = 'null';

                            message = await signUp();
                            print('++++++++++++++++++++++++++++$message');
                            if (message == "400") {
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
                            } else if (message == "403") {
                              ScaffoldMessenger.of(context)
                                  .showSnackBar(SnackBar(
                                elevation: 40,
                                width: widthOfScreen * 0.8,
                                backgroundColor: Colors.red,
                                behavior: SnackBarBehavior.floating,
                                shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(5),
                                ),
                                content: Center(
                                  child: Text(
                                    '.حساب کاربری با مشخصات فوق قبلا ثبت شده است',
                                    textDirection: TextDirection.ltr,
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontSize: heightOfScreen * 0.015,
                                        fontFamily: 'vazir',
                                        fontWeight: FontWeight.w500),
                                  ),
                                ),
                              ));
                            } else if (message == "404") {
                              ScaffoldMessenger.of(context)
                                  .showSnackBar(SnackBar(
                                elevation: 40,
                                width: widthOfScreen * 0.8,
                                backgroundColor: Colors.red,
                                behavior: SnackBarBehavior.floating,
                                shape: RoundedRectangleBorder(
                                  borderRadius: BorderRadius.circular(5),
                                ),
                                content: Center(
                                  child: Text(
                                    '.نام کاربری قبلا ثبت شده است',
                                    textDirection: TextDirection.ltr,
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontSize: heightOfScreen * 0.015,
                                        fontFamily: 'vazir',
                                        fontWeight: FontWeight.w500),
                                  ),
                                ),
                              ));
                            } else if (message == "500") {
                              UserData.studentCode = studentCodeController.text;
                              Navigator.pushReplacementNamed(
                                context,
                                Information.routeName,
                              );
                            }
                          }
                        },
                        child: const Padding(
                          padding: EdgeInsets.symmetric(vertical: 10.0),
                          child: Text(
                            'ثبت نام',
                            textAlign: TextAlign.center,
                            style: TextStyle(
                              fontFamily: 'pinar',
                              color: Colors.white,
                              fontSize: 30,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Future<String> signUp() async {
    final completer = Completer<String>();

    await Socket.connect(ServerConnectionInfo.ipAddress, 3559).then(
      (serverSocket) {
        serverSocket.write(
            'signup//${studentCodeController.text}//${usernameController.text}//${passwordController.text}\u0000');
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
