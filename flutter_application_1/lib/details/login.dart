import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'my_app_bar.dart';
import 'my_bottom.dart';

class Login extends StatefulWidget {
  static const String routeName = 'login';
  const Login({super.key});

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {
  String? username;
  final _keyform = GlobalKey<FormState>();
  bool visable = true;

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
                      inputFormatters: [
                        FilteringTextInputFormatter.deny(' '),
                        
                      ],
                      validator: (String? value) {
                        username = value;
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
                    const SizedBox(
                      height: 20,
                    ),
                    Stack(children: [
                      TextFormField(
                        validator: (String? value) {
                          if (!RegExp(
                                  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                              .hasMatch(value!)) {
                            return "!رمز عبور نامعتبر است";
                          } else if (value.contains(username!)) {
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
                          if (_keyform.currentState!.validate()) {}
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
}
