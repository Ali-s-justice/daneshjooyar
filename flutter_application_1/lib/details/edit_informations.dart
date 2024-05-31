import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_application_1/details/information.dart';
import 'package:flutter_application_1/details/my_app_bar.dart';
import 'classes/student.dart';

class EditInformation extends StatefulWidget {
  static const String routeName = "editinformation";
  const EditInformation({super.key});

  @override
  State<EditInformation> createState() => _MyWidgetState();
}

class _MyWidgetState extends State<EditInformation> {
  late TextEditingController usernameController;
  TextEditingController passwordController = TextEditingController();
  TextEditingController rePasswordController = TextEditingController();
  bool visable = true;
  bool visable2 = true;

  static const textFormFieldColor = Color.fromARGB(142, 255, 255, 255);
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
            Color.fromARGB(212, 255, 255, 255),
            Color.fromARGB(255, 15, 199, 255),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      );
  @override
  Widget build(BuildContext context) {
    final Student currentStudent =
        ModalRoute.of(context)!.settings.arguments as Student;
    usernameController = TextEditingController(text: currentStudent.username);

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
                          decoration: const BoxDecoration(
                            color: Color.fromARGB(176, 255, 255, 255),
                            borderRadius: BorderRadius.only(
                              topLeft: Radius.circular(50),
                              topRight: Radius.circular(50),
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
                                                'نام کاربری ',
                                                style: TextStyle(
                                                  fontWeight: FontWeight.w900,
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
                                              color:
                                                  Colors.black.withOpacity(0.1),
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
                                              height: 20,
                                            ),

                                            TextFormField(
                                              validator: (value) {
                                                if (value!.isEmpty) {
                                                  return ".رمز عبور فعلی را وارد کنید";
                                                } else if (value !=
                                                    currentStudent.password) {
                                                  return ".رمز عبور فعلی نادرست است";
                                                } else {
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
                                              height: 20,
                                            ),
                                            ElevatedButton(
                                              style: ElevatedButton.styleFrom(
                                                minimumSize:
                                                    const Size(200.0, 30),
                                                backgroundColor: Colors.green,
                                                shape: RoundedRectangleBorder(
                                                  borderRadius:
                                                      BorderRadius.circular(50),
                                                ),
                                              ),
                                              onPressed: () {
                                                setState(
                                                  () {
                                                    if (_keyform1.currentState!
                                                        .validate()) {
                                                      currentStudent.username =
                                                          usernameController
                                                              .text;
                                                      currentStudent.password =
                                                          passwordController
                                                              .text;
                                                      Navigator.pushNamed(
                                                          context,
                                                          Information.routeName,
                                                          arguments:
                                                              currentStudent);
                                                    }
                                                  },
                                                );
                                              },
                                              child: const Padding(
                                                padding: EdgeInsets.symmetric(
                                                    vertical: 10.0),
                                                child: Text(
                                                  'ثبت',
                                                  textAlign: TextAlign.center,
                                                  style: TextStyle(
                                                    fontFamily: 'pinar',
                                                    color: Colors.white,
                                                    fontSize: 20,
                                                  ),
                                                ),
                                              ),
                                            ),
                                            // const SizedBox(
                                            //   height: 10,
                                            // ),
                                            //******************************************************************** */
                                            Form(
                                              key: _keyform2,
                                              child: Column(
                                                children: <Widget>[
                                                  const Align(
                                                    alignment:
                                                        Alignment(0.8, 0),
                                                    child: Text(
                                                      'رمز عبور',
                                                      style: TextStyle(
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
                                                    color: Colors.black
                                                        .withOpacity(0.1),
                                                  ),
                                                  const SizedBox(
                                                    height: 20,
                                                  ),
                                                  Stack(
                                                    children: [
                                                      TextFormField(
                                                        controller:
                                                            passwordController,
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
                                                    height: 20,
                                                  ),
                                                  Stack(
                                                    children: [
                                                      TextFormField(
                                                        validator:
                                                            (String? value) {
                                                          if (value !=
                                                              passwordController
                                                                  .text) {
                                                            return '.تکرار رمز عبور با رمز عبور باید یکسان باشد';
                                                          } else if (value!
                                                              .isEmpty) {
                                                            return '!تکرار رمز عبور نمیتواند خالی باشد';
                                                          } else {
                                                            return null;
                                                          }
                                                        },
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
                                                    height: 20,
                                                  ),

                                                  TextFormField(
                                                    validator: (value) {
                                                      if (value!.isEmpty) {
                                                        return ".رمز عبور فعلی را وارد کنید";
                                                      } else if (value !=
                                                          currentStudent
                                                              .password) {
                                                        return ".رمز عبور فعلی نادرست است";
                                                      } else {
                                                        return null;
                                                      }
                                                    },
                                                    decoration: InputDecoration(
                                                        label: const Align(
                                                          alignment:
                                                              Alignment(0.9, 0),
                                                          child: Text(
                                                            ' رمز عبور فعلی',
                                                            style:
                                                                textFormStyle,
                                                          ),
                                                        ),
                                                        border:
                                                            OutlineInputBorder(
                                                          borderRadius:
                                                              BorderRadius
                                                                  .circular(25),
                                                        ),
                                                        filled: true,
                                                        fillColor:
                                                            textFormFieldColor),
                                                  ),

                                                  //*** */
                                                  const SizedBox(
                                                    height: 20,
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
                                                    onPressed: () {
                                                      setState(
                                                        () {
                                                          if (_keyform2
                                                              .currentState!
                                                              .validate()) {
                                                            Navigator.pushNamed(
                                                                context,
                                                                Information
                                                                    .routeName,
                                                                arguments:
                                                                    currentStudent);

                                                            currentStudent
                                                                    .username =
                                                                usernameController
                                                                    .text;
                                                            currentStudent
                                                                    .password =
                                                                passwordController
                                                                    .text;
                                                            Navigator.pushNamed(
                                                                context,
                                                                Information
                                                                    .routeName,
                                                                arguments:
                                                                    currentStudent);
                                                          }
                                                        },
                                                      );
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
                                                          fontFamily: 'pinar',
                                                          color: Colors.white,
                                                          fontSize: 20,
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
                    right: 110,
                    top: 0,
                    child: Stack(
                      children: [
                        const CircleAvatar(
                          backgroundColor: Colors.white,
                          radius: 95,
                          backgroundImage:
                              AssetImage('assets/images/mypic.jpg'),
                        ),
                        Positioned(
                          bottom: 5,
                          right: 5,
                          child: Container(
                            alignment: Alignment.center,
                            width: 50,
                            height: 50,
                            decoration: const ShapeDecoration(
                              color: Color.fromARGB(255, 127, 217, 225),
                              shape: OvalBorder(),
                            ),
                            child: Center(
                              child: IconButton(
                                onPressed: () {},
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
}
