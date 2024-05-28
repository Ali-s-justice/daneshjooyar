import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_application_1/details/information.dart';
import 'my_app_bar.dart';
import 'my_bottom.dart';

class Signup extends StatefulWidget {
  static const routeName = 'signup';
  const Signup({super.key});

  @override
  State<Signup> createState() => _SignupState();
}

class _SignupState extends State<Signup> {
  String? pass;
  String? username;
  final _keyform = GlobalKey<FormState>();
  bool visable = true;
  bool visable2 = true;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const MyAppBar(),
      bottomNavigationBar: const MyBottom(),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,
          children: [
            const SizedBox(
              height: 50,
            ),
            const Text(
              'ثبت نام در درسا',
              textDirection: TextDirection.rtl,
              style: TextStyle(
                fontFamily: 'vazir',
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
                    const SizedBox(
                      height: 20,
                    ),
                    TextFormField(
                      inputFormatters: [
                        FilteringTextInputFormatter.deny(' '),
                      ],
                      validator: (String? value) {
                        username = value;
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
                          helperText:
                              'نام کاربری شامل حروف کوچک و بزرگ و اعداد',
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(25),
                          ),
                          filled: true,
                          fillColor: const Color.fromARGB(255, 239, 227, 233),
                          // hintText : 'سید امیرحسین اشرفیان',

                          label: Container(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 12.0),
                            alignment: Alignment.centerRight,
                            child: const Text(
                              'نام کاربری / یوزنیم',
                              textDirection: TextDirection.rtl,
                              style: TextStyle(
                                fontFamily: 'vazir',
                                fontWeight: FontWeight.w900,
                                fontSize: 22.0,
                              ),
                            ),
                          )

                          // labelText: 'نام و نام خانوادگی',
                          ),
                    ),
                    // const SizedBox(
                    //   height: 20,
                    // ),
                    TextFormField(
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
                          fillColor: const Color.fromARGB(255, 239, 227, 233),
                          // hintText : 'سید امیرحسین اشرفیان',

                          label: Container(
                            padding:
                                const EdgeInsets.symmetric(horizontal: 12.0),
                            alignment: Alignment.centerRight,
                            child: const Text(
                              'شماره دانشجویی',
                              textDirection: TextDirection.rtl,
                              style: TextStyle(
                                fontFamily: 'vazir',
                                fontWeight: FontWeight.w900,
                                fontSize: 22.0,
                              ),
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
                        validator: (String? value) {
                          pass = value;
                          if (!RegExp(
                                  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                              .hasMatch(value!)) {
                            return "!حداقل 8 حرف شامل حروف و کوچک و بزرگ و اعداد";
                          } else if (value.contains(username!)) {
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
                          fillColor: const Color.fromARGB(255, 239, 227, 233),
                          label: Container(
                            padding: const EdgeInsets.only(right: 35.0),
                            alignment: Alignment.centerRight,
                            child: const Text(
                              'رمز عبور',
                              style: TextStyle(
                                fontWeight: FontWeight.w900,
                                fontFamily: 'vazir',
                                fontSize: 22.0,
                              ),
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
                      height: 20,
                    ),
                    Stack(children: [
                      TextFormField(
                        validator: (String? value) {
                          if (value != pass) {
                            return 'تکرار رمز عبور با رمز عبور باید یکسان باشد.';
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
                          fillColor: const Color.fromARGB(255, 239, 227, 233),
                          label: Container(
                            padding: const EdgeInsets.only(right: 35.0),
                            alignment: Alignment.centerRight,
                            child: const Text(
                              'تکرار رمز عبور',
                              style: TextStyle(
                                fontWeight: FontWeight.w900,
                                fontFamily: 'vazir',
                                fontSize: 22.0,
                              ),
                            ),
                          ),
                        ),
                      ),
                      Container(
                        alignment: Alignment.centerRight,
                        padding: const EdgeInsets.only(top: 7.5),
                        child: IconButton(
                          icon: Icon(visable2
                              ? Icons.visibility_off
                              : Icons.visibility),
                          onPressed: () {
                            setState(() {
                              visable2 = !visable2;
                            });
                          },
                        ),
                      ),
                    ]),
                    const SizedBox(
                      height: 50.0,
                    ),
                    Container(
                      decoration: const BoxDecoration(
                        gradient: LinearGradient(
                          colors: [
                            Color.fromARGB(255, 93, 0, 255),
                            Color.fromARGB(255, 131, 58, 180),
                          ],
                          begin: Alignment.topLeft,
                          end: Alignment.bottomRight,
                        ),
                        borderRadius: BorderRadius.all(Radius.circular(30.0)),
                      ),
                      child: ElevatedButton(
                        style: ElevatedButton.styleFrom(
                          minimumSize: const Size(220, 50),
                          backgroundColor: Colors.transparent,
                          shadowColor: Colors.transparent,
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(20.0),
                          ),
                        ),
                        onPressed: () {
                          if (_keyform.currentState!.validate()) {
                            Navigator.pushNamed(context, Information.routeName);
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
}
