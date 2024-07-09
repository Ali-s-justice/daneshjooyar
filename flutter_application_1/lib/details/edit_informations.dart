import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:file_picker/file_picker.dart';
import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/information.dart';
import 'package:flutter_application_1/services/server_connection_info.dart';
import 'classes/student.dart';

class EditInformation extends StatefulWidget {
  static const String routeName = "editinformation";
  const EditInformation({super.key});

  @override
  State<EditInformation> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<EditInformation> {
  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  TextEditingController newPasswordController = TextEditingController();
  TextEditingController passwordController2 = TextEditingController();
  TextEditingController rePasswordController = TextEditingController();

  bool visable = true;
  bool visable2 = true;
  File? image;

  String responseOfChangeUsername = '-';
  String responseOfChangePasswor = '-';

  static const textFormFieldColor = Color.fromARGB(188, 255, 255, 255);
  static const textFormStyle = TextStyle(
    fontWeight: FontWeight.w900,
    fontFamily: 'vazir',
    fontSize: 22.0,
  );
  final _keyform1 = GlobalKey<FormState>();
  final _keyform2 = GlobalKey<FormState>();
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromRGBO(0, 113, 212, 1),
            Color.fromRGBO(25, 0, 126, 1),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      );
  Student currentStudent = Student();
  @override
  Widget build(BuildContext context) {
    currentStudent = ModalRoute.of(context)!.settings.arguments as Student;

    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          elevation: 0.0,
          backgroundColor: Colors.transparent,
          toolbarHeight: 28,
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              Stack(
                children: [
                  Column(
                    children: [
                      const SizedBox(
                        height: 120,
                      ),
                      Padding(
                        padding: const EdgeInsets.only(
                          top: 0,
                        ),
                        child: Container(
                          decoration: BoxDecoration(
                            gradient: const LinearGradient(
                              colors: [
                                Color(0xFF1523AF),
                                Color(0xFF6C00D8),
                              ],
                              begin: Alignment.topLeft,
                              end: Alignment.bottomRight,
                            ),
                            borderRadius: const BorderRadius.only(
                              topLeft: Radius.circular(50),
                              topRight: Radius.circular(50),
                            ),
                            border: Border.all(
                              color: Colors.black,
                              width: 2.0,
                            ),
                          ),
                          height: 690,
                          child: Padding(
                            padding: const EdgeInsets.only(
                              top: 10,
                              left: 10,
                              right: 10,
                            ),
                            child: Container(
                              height: 700,
                              width: 400,
                              decoration: BoxDecoration(
                                borderRadius: BorderRadius.circular(35),
                                color: Colors.transparent,
                              ),
                              child: SingleChildScrollView(
                                child: Column(
                                  children: [
                                    Form(
                                      key: _keyform1,
                                      child: Padding(
                                        padding: const EdgeInsets.all(8.0),
                                        child: Column(
                                          children: <Widget>[
                                            const SizedBox(
                                              height: 40,
                                            ),
                                            const Align(
                                              alignment: Alignment(0.8, 0),
                                              child: Text(
                                                'تغییر نام کاربری',
                                                style: TextStyle(
                                                    fontWeight: FontWeight.w900,
                                                    fontSize: 20.0,
                                                    color: Colors.white),
                                              ),
                                            ),
                                            const SizedBox(
                                              height: 10,
                                            ),
                                            Container(
                                              width: double.infinity,
                                              height: 1,
                                              color: const Color.fromARGB(
                                                      255, 255, 255, 255)
                                                  .withOpacity(0.1),
                                            ),
                                            const SizedBox(
                                              height: 20,
                                            ),
                                            SizedBox(
                                              height: 60.0,
                                              child: TextFormField(
                                                validator: (String? value) {
                                                  if (value!.isEmpty) {
                                                    return '!نام کاربری نمیتواند خالی باشد ';
                                                  } else if (value.length <=
                                                      2) {
                                                    return '.نام کاربری باید بیش از 2 کاراکتر باشد';
                                                  } else if (!RegExp(
                                                          "^([A-Za-z0-9]){2,22}\$")
                                                      .hasMatch(value)) {
                                                    return ".نام کاربری نامعتبر است. فقط از کاراکترهای مجاز استفاده کنید";
                                                  } else {
                                                    return null;
                                                  }
                                                },
                                                controller: usernameController,
                                                decoration: InputDecoration(
                                                  label: const Align(
                                                    alignment:
                                                        Alignment(0.9, 0),
                                                    child: Text(
                                                      'نام کاربری جدید',
                                                      style: textFormStyle,
                                                    ),
                                                  ),
                                                  border: OutlineInputBorder(
                                                    borderRadius:
                                                        BorderRadius.circular(
                                                            25),
                                                  ),
                                                  filled: true,
                                                  fillColor: textFormFieldColor,
                                                ),
                                              ),
                                            ),
                                            const SizedBox(
                                              height: 10,
                                            ),

                                            TextFormField(
                                              controller: passwordController,
                                              validator: (value) {
                                                if (value!.isEmpty) {
                                                  return ".رمز عبور فعلی را وارد کنید";
                                                }
                                                //  else if (value !=
                                                //     currentStudent.password) {
                                                //   return ".رمز عبور فعلی نادرست است";
                                                // }

                                                else {
                                                  return null;
                                                }
                                              },
                                              decoration: InputDecoration(
                                                  label: const Align(
                                                    alignment:
                                                        Alignment(0.9, 0),
                                                    child: Text(
                                                      ' رمز عبور فعلی',
                                                      style: textFormStyle,
                                                    ),
                                                  ),
                                                  border: OutlineInputBorder(
                                                    borderRadius:
                                                        BorderRadius.circular(
                                                            25),
                                                  ),
                                                  filled: true,
                                                  fillColor:
                                                      textFormFieldColor),
                                            ),

                                            const SizedBox(
                                              height: 5,
                                            ),
                                            ElevatedButton(
                                              style: ElevatedButton.styleFrom(
                                                minimumSize:
                                                    const Size(200.0, 20),
                                                backgroundColor: Colors.green,
                                                shape: RoundedRectangleBorder(
                                                  borderRadius:
                                                      BorderRadius.circular(50),
                                                ),
                                              ),
                                              onPressed: () async {
                                                String message = 'null';

                                                if (_keyform1.currentState!
                                                    .validate()) {
                                                  message =
                                                      await changeUsername();

                                                  print(
                                                      '++++++++++++++++++++++++++++$message');
                                                  if (message == "400") {
                                                    ScaffoldMessenger.of(
                                                            context)
                                                        .showSnackBar(
                                                      SnackBar(
                                                        elevation: 40,
                                                        width:
                                                            widthOfScreen * 0.8,
                                                        backgroundColor:
                                                            Colors.red,
                                                        behavior:
                                                            SnackBarBehavior
                                                                .floating,
                                                        shape:
                                                            RoundedRectangleBorder(
                                                          borderRadius:
                                                              BorderRadius
                                                                  .circular(5),
                                                        ),
                                                        content: Center(
                                                          child: Text(
                                                            '.دانشجویی با این مشخصات در سامانه ثبت نیست',
                                                            textDirection:
                                                                TextDirection
                                                                    .ltr,
                                                            style: TextStyle(
                                                                color: Colors
                                                                    .white,
                                                                fontSize:
                                                                    heightOfScreen *
                                                                        0.015,
                                                                fontFamily:
                                                                    'vazir',
                                                                fontWeight:
                                                                    FontWeight
                                                                        .w500),
                                                          ),
                                                        ),
                                                      ),
                                                    );
                                                  } else if (message == '402') {
                                                    // ignore: use_build_context_synchronously
                                                    ScaffoldMessenger.of(
                                                            context)
                                                        .showSnackBar(
                                                      SnackBar(
                                                        elevation: 40,
                                                        width:
                                                            widthOfScreen * 0.8,
                                                        backgroundColor:
                                                            Colors.red,
                                                        behavior:
                                                            SnackBarBehavior
                                                                .floating,
                                                        shape:
                                                            RoundedRectangleBorder(
                                                          borderRadius:
                                                              BorderRadius
                                                                  .circular(5),
                                                        ),
                                                        content: Center(
                                                          child: Text(
                                                            '.رمزعبور وارد شده اشتباه است',
                                                            textDirection:
                                                                TextDirection
                                                                    .ltr,
                                                            style: TextStyle(
                                                                color: Colors
                                                                    .white,
                                                                fontSize:
                                                                    heightOfScreen *
                                                                        0.018,
                                                                fontFamily:
                                                                    'vazir',
                                                                fontWeight:
                                                                    FontWeight
                                                                        .w500),
                                                          ),
                                                        ),
                                                      ),
                                                    );
                                                  } else if (message == "404") {
                                                    ScaffoldMessenger.of(
                                                            context)
                                                        .showSnackBar(SnackBar(
                                                      elevation: 40,
                                                      width:
                                                          widthOfScreen * 0.8,
                                                      backgroundColor:
                                                          Colors.red,
                                                      behavior: SnackBarBehavior
                                                          .floating,
                                                      shape:
                                                          RoundedRectangleBorder(
                                                        borderRadius:
                                                            BorderRadius
                                                                .circular(5),
                                                      ),
                                                      content: Center(
                                                        child: Text(
                                                          '.نام کاربری قبلا ثبت شده است',
                                                          textDirection:
                                                              TextDirection.ltr,
                                                          style: TextStyle(
                                                              color:
                                                                  Colors.white,
                                                              fontSize:
                                                                  heightOfScreen *
                                                                      0.015,
                                                              fontFamily:
                                                                  'vazir',
                                                              fontWeight:
                                                                  FontWeight
                                                                      .w500),
                                                        ),
                                                      ),
                                                    ));
                                                  }
                                                  if (message == "500") {
                                                    currentStudent.username =
                                                        usernameController.text;
                                                    ScaffoldMessenger.of(
                                                            context)
                                                        .showSnackBar(SnackBar(
                                                      elevation: 40,
                                                      width:
                                                          widthOfScreen * 0.8,
                                                      backgroundColor:
                                                          const Color.fromARGB(
                                                              255, 21, 160, 25),
                                                      behavior: SnackBarBehavior
                                                          .floating,
                                                      shape:
                                                          RoundedRectangleBorder(
                                                        borderRadius:
                                                            BorderRadius
                                                                .circular(5),
                                                      ),
                                                      content: Center(
                                                        child: Text(
                                                          '.نام کاربری با موفقیت تغییر پیدا کرد',
                                                          textDirection:
                                                              TextDirection.ltr,
                                                          style: TextStyle(
                                                              color:
                                                                  Colors.white,
                                                              fontSize:
                                                                  heightOfScreen *
                                                                      0.015,
                                                              fontFamily:
                                                                  'vazir',
                                                              fontWeight:
                                                                  FontWeight
                                                                      .w500),
                                                        ),
                                                      ),
                                                    ));
                                                  }
                                                }
                                              },
                                              child: const Padding(
                                                padding: EdgeInsets.symmetric(
                                                    vertical: 10.0),
                                                child: Text(
                                                  'ثبت',
                                                  textAlign: TextAlign.center,
                                                  style: TextStyle(
                                                      fontFamily: 'vazir',
                                                      color: Colors.white,
                                                      fontSize: 20,
                                                      fontWeight:
                                                          FontWeight.bold),
                                                ),
                                              ),
                                            ),
                                            const SizedBox(
                                              height: 15,
                                            ),
                                            //******************************************************************** */
                                            Form(
                                              key: _keyform2,
                                              child: Column(
                                                children: <Widget>[
                                                  const Align(
                                                    alignment:
                                                        Alignment(0.8, 0),
                                                    child: Text(
                                                      'تغییر رمز عبور',
                                                      style: TextStyle(
                                                        color: Colors.white,
                                                        fontWeight:
                                                            FontWeight.w900,
                                                        fontSize: 20.0,
                                                      ),
                                                    ),
                                                  ),
                                                  const SizedBox(
                                                    height: 10,
                                                  ),
                                                  Container(
                                                    width: double.infinity,
                                                    height: 1,
                                                    color: const Color.fromARGB(
                                                            255, 255, 255, 255)
                                                        .withOpacity(0.1),
                                                  ),
                                                  const SizedBox(
                                                    height: 20,
                                                  ),
                                                  Stack(
                                                    children: [
                                                      TextFormField(
                                                        controller:
                                                            newPasswordController,
                                                        validator:
                                                            (String? value) {
                                                          if (!RegExp(
                                                                  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                                                              .hasMatch(
                                                                  value!)) {
                                                            return "!حداقل 8 حرف شامل حروف کوچک و بزرگ و اعداد";
                                                          } else if (value.contains(
                                                              currentStudent
                                                                  .username!)) {
                                                            return ".رمز عبور نباید شامل نام کاربری باشد";
                                                          } else {
                                                            return null;
                                                          }
                                                        },
                                                        obscureText: visable,
                                                        textDirection:
                                                            TextDirection.ltr,
                                                        decoration:
                                                            InputDecoration(
                                                          border:
                                                              OutlineInputBorder(
                                                            borderRadius:
                                                                BorderRadius
                                                                    .circular(
                                                                        25),
                                                          ),
                                                          filled: true,
                                                          fillColor:
                                                              textFormFieldColor,
                                                          label: Container(
                                                            padding:
                                                                const EdgeInsets
                                                                    .only(
                                                                    right:
                                                                        35.0),
                                                            alignment: Alignment
                                                                .centerRight,
                                                            child: const Text(
                                                              'رمز عبور',
                                                              style:
                                                                  textFormStyle,
                                                            ),
                                                          ),
                                                        ),
                                                      ),
                                                      Container(
                                                        alignment: Alignment
                                                            .centerRight,
                                                        padding:
                                                            const EdgeInsets
                                                                .only(top: 7.5),
                                                        child: IconButton(
                                                          icon: Icon(
                                                            visable
                                                                ? Icons
                                                                    .visibility_off
                                                                : Icons
                                                                    .visibility,
                                                          ),
                                                          onPressed: () {
                                                            setState(() {
                                                              visable =
                                                                  !visable;
                                                            });
                                                          },
                                                        ),
                                                      ),
                                                    ],
                                                  ),
                                                  const SizedBox(
                                                    height: 10,
                                                  ),
                                                  Stack(
                                                    children: [
                                                      TextFormField(
                                                        validator:
                                                            (String? value) {
                                                          if (value !=
                                                              newPasswordController
                                                                  .text) {
                                                            return '.تکرار رمز عبور با رمز عبور باید یکسان باشد';
                                                          } else if (value!
                                                              .isEmpty) {
                                                            return '!تکرار رمز عبور نمیتواند خالی باشد';
                                                          } else {
                                                            return null;
                                                          }
                                                        },
                                                        controller:
                                                            rePasswordController,
                                                        obscureText: visable2,
                                                        textDirection:
                                                            TextDirection.ltr,
                                                        decoration:
                                                            InputDecoration(
                                                          border:
                                                              OutlineInputBorder(
                                                            borderRadius:
                                                                BorderRadius
                                                                    .circular(
                                                                        25),
                                                          ),
                                                          filled: true,
                                                          fillColor:
                                                              textFormFieldColor,
                                                          label: Container(
                                                            padding:
                                                                const EdgeInsets
                                                                    .only(
                                                                    right:
                                                                        35.0),
                                                            alignment: Alignment
                                                                .centerRight,
                                                            child: const Text(
                                                              'تکرار رمز عبور',
                                                              style: TextStyle(
                                                                fontWeight:
                                                                    FontWeight
                                                                        .w900,
                                                                fontFamily:
                                                                    'vazir',
                                                                fontSize: 22.0,
                                                              ),
                                                            ),
                                                          ),
                                                        ),
                                                      ),
                                                      Container(
                                                        alignment: Alignment
                                                            .centerRight,
                                                        padding:
                                                            const EdgeInsets
                                                                .only(top: 7.5),
                                                        child: IconButton(
                                                          icon: Icon(
                                                            visable2
                                                                ? Icons
                                                                    .visibility_off
                                                                : Icons
                                                                    .visibility,
                                                          ),
                                                          onPressed: () {
                                                            setState(() {
                                                              visable2 =
                                                                  !visable2;
                                                            });
                                                          },
                                                        ),
                                                      ),
                                                    ],
                                                  ),
                                                  const SizedBox(
                                                    height: 10,
                                                  ),

                                                  TextFormField(
                                                    validator: (value) {
                                                      if (value!.isEmpty) {
                                                        return ".رمز عبور فعلی را وارد کنید";
                                                      }
                                                      // else if (value !=
                                                      //     currentStudent
                                                      //         .password) {
                                                      //   return ".رمز عبور فعلی نادرست است";
                                                      // }

                                                      else {
                                                        return null;
                                                      }
                                                    },
                                                    controller:
                                                        passwordController2,
                                                    decoration: InputDecoration(
                                                      label: const Align(
                                                        alignment:
                                                            Alignment(0.9, 0),
                                                        child: Text(
                                                          ' رمز عبور فعلی',
                                                          style: textFormStyle,
                                                        ),
                                                      ),
                                                      border:
                                                          OutlineInputBorder(
                                                        borderRadius:
                                                            BorderRadius
                                                                .circular(25),
                                                        borderSide:
                                                            const BorderSide(
                                                          color: Colors.blue,
                                                          width: 2.0,
                                                        ),
                                                      ),
                                                      filled: true,
                                                      fillColor:
                                                          textFormFieldColor,
                                                    ),
                                                  ),

                                                  //*** */
                                                  const SizedBox(
                                                    height: 5,
                                                  ),
                                                  ElevatedButton(
                                                    style: ElevatedButton
                                                        .styleFrom(
                                                      minimumSize:
                                                          const Size(200.0, 30),
                                                      backgroundColor:
                                                          Colors.green,
                                                      shape:
                                                          RoundedRectangleBorder(
                                                        borderRadius:
                                                            BorderRadius
                                                                .circular(50),
                                                      ),
                                                    ),
                                                    onPressed: () async {
                                                      String message = 'null';

                                                      if (_keyform2
                                                          .currentState!
                                                          .validate()) {
                                                        message =
                                                            await changePassword();
                                                        print(
                                                            '++++++++++++++++++++++++++++$message');
                                                        if (message == "400") {
                                                          ScaffoldMessenger.of(
                                                                  context)
                                                              .showSnackBar(
                                                            SnackBar(
                                                              elevation: 40,
                                                              width:
                                                                  widthOfScreen *
                                                                      0.8,
                                                              backgroundColor:
                                                                  Colors.red,
                                                              behavior:
                                                                  SnackBarBehavior
                                                                      .floating,
                                                              shape:
                                                                  RoundedRectangleBorder(
                                                                borderRadius:
                                                                    BorderRadius
                                                                        .circular(
                                                                            5),
                                                              ),
                                                              content: Center(
                                                                child: Text(
                                                                  '.دانشجویی با این مشخصات در سامانه ثبت نیست',
                                                                  textDirection:
                                                                      TextDirection
                                                                          .ltr,
                                                                  style: TextStyle(
                                                                      color: Colors
                                                                          .white,
                                                                      fontSize:
                                                                          heightOfScreen *
                                                                              0.015,
                                                                      fontFamily:
                                                                          'vazir',
                                                                      fontWeight:
                                                                          FontWeight
                                                                              .w500),
                                                                ),
                                                              ),
                                                            ),
                                                          );
                                                        } else if (message ==
                                                            '402') {
                                                          // ignore: use_build_context_synchronously
                                                          ScaffoldMessenger.of(
                                                                  context)
                                                              .showSnackBar(
                                                            SnackBar(
                                                              elevation: 40,
                                                              width:
                                                                  widthOfScreen *
                                                                      0.8,
                                                              backgroundColor:
                                                                  Colors.red,
                                                              behavior:
                                                                  SnackBarBehavior
                                                                      .floating,
                                                              shape:
                                                                  RoundedRectangleBorder(
                                                                borderRadius:
                                                                    BorderRadius
                                                                        .circular(
                                                                            5),
                                                              ),
                                                              content: Center(
                                                                child: Text(
                                                                  '.رمزعبور وارد شده اشتباه است',
                                                                  textDirection:
                                                                      TextDirection
                                                                          .ltr,
                                                                  style: TextStyle(
                                                                      color: Colors
                                                                          .white,
                                                                      fontSize:
                                                                          heightOfScreen *
                                                                              0.018,
                                                                      fontFamily:
                                                                          'vazir',
                                                                      fontWeight:
                                                                          FontWeight
                                                                              .w500),
                                                                ),
                                                              ),
                                                            ),
                                                          );
                                                        } else if (message ==
                                                            "500") {
                                                          ScaffoldMessenger.of(
                                                                  context)
                                                              .showSnackBar(
                                                                  SnackBar(
                                                            elevation: 40,
                                                            width:
                                                                widthOfScreen *
                                                                    0.8,
                                                            backgroundColor:
                                                                const Color
                                                                    .fromARGB(
                                                                    255,
                                                                    21,
                                                                    160,
                                                                    25),
                                                            behavior:
                                                                SnackBarBehavior
                                                                    .floating,
                                                            shape:
                                                                RoundedRectangleBorder(
                                                              borderRadius:
                                                                  BorderRadius
                                                                      .circular(
                                                                          5),
                                                            ),
                                                            content: Center(
                                                              child: Text(
                                                                '.رمز عبور با موفقیت تغییر پیدا کرد',
                                                                textDirection:
                                                                    TextDirection
                                                                        .ltr,
                                                                style: TextStyle(
                                                                    color: Colors
                                                                        .white,
                                                                    fontSize:
                                                                        heightOfScreen *
                                                                            0.015,
                                                                    fontFamily:
                                                                        'vazir',
                                                                    fontWeight:
                                                                        FontWeight
                                                                            .w500),
                                                              ),
                                                            ),
                                                          ));
                                                        }
                                                      }
                                                    },
                                                    child: const Padding(
                                                      padding:
                                                          EdgeInsets.symmetric(
                                                              vertical: 10.0),
                                                      child: Text(
                                                        'ثبت',
                                                        textAlign:
                                                            TextAlign.center,
                                                        style: TextStyle(
                                                            fontFamily: 'vazir',
                                                            color: Colors.white,
                                                            fontSize: 20,
                                                            fontWeight:
                                                                FontWeight
                                                                    .bold),
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
                                  ],
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                  Positioned(
                    right: 130,
                    top: 0,
                    child: Stack(
                      children: [
                        Container(
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.circular(95),
                            border: Border.all(
                              color: Colors.black,
                              width: 3.5,
                            ),
                          ),
                          child: CircleAvatar(
                              backgroundColor: Colors.white,
                              radius: 85,
                              backgroundImage: (image == null)
                                  ? const AssetImage(
                                          'assets/images/userinfo.jpg')
                                      as ImageProvider
                                  : FileImage(image!)),
                        ),
                        Positioned(
                          bottom: 5,
                          right: 5,
                          child: Container(
                            alignment: Alignment.center,
                            width: 50,
                            height: 50,
                            decoration: BoxDecoration(
                                border: Border.all(
                                  color: Colors.black,
                                  width: 2.5,
                                ),
                                borderRadius: BorderRadius.circular(85),
                                color: const Color.fromARGB(255, 196, 86, 2)),
                            child: Center(
                              child: IconButton(
                                onPressed: () async {
                                  FilePickerResult? result = await FilePicker
                                      .platform
                                      .pickFiles(type: FileType.image);

                                  if (result != null) {
                                    File file = File(result.files.single.path!);
                                    setState(() {
                                      image = file;
                                    });
                                  } else {
                                    // User canceled the picker
                                  }
                                },
                                icon: const Icon(
                                  Icons.camera_alt_rounded,
                                  color: Color.fromARGB(255, 0, 0, 0),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
              // const SizedBox(
              //   height: 100,
              // ),
            ],
          ),
        ),
      ),
    );
  }

  Future<String> changeUsername() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write(
            'changeUsername//${currentStudent.username}//${usernameController.text}//${passwordController.text}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseOfChangeUsername = utf8.decode(socketResponse);
            });
            completer.complete(responseOfChangeUsername);
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

  Future<String> changePassword() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write(
            'changePassword//${currentStudent.studenCode}//${newPasswordController.text}//${passwordController2.text}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseOfChangePasswor = utf8.decode(socketResponse);
            });
            completer.complete(responseOfChangePasswor);
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
