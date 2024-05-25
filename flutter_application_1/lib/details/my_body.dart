import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class MyBody extends StatefulWidget {
  const MyBody({super.key});

  @override
  State<MyBody> createState() => _MyBodyState();
}

class _MyBodyState extends State<MyBody> {
  final _keyform = GlobalKey<FormState>();
  bool sbu = false;
  bool ssh = false;
  bool qom = false;
  bool kashan = false;
  bool visable = true;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
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
                    // validator: (String? value) {
                    //   if (value!.isEmpty) {
                    //     return 'نام کاربری نمیتواند خالی باشد!';
                    //   } else {
                    //     return "";
                    //   }
                    // },
                    decoration: InputDecoration(
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                        filled: true,
                        fillColor: const Color.fromARGB(255, 239, 227, 233),
                        // hintText : 'سید امیرحسین اشرفیان',

                        label: Container(
                          padding: const EdgeInsets.symmetric(horizontal: 12.0),
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
                        icon: Icon(
                            visable ? Icons.visibility_off : Icons.visibility),
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
                      borderRadius: BorderRadius.all(Radius.circular(20.0)),
                    ),
                    child: ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        minimumSize: const Size(300, 50),
                        backgroundColor: Colors.transparent,
                        shadowColor: Colors.transparent,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20.0),
                        ),
                      ),
                      onPressed: () {},
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

                  // ElevatedButton(
                  //   style: ElevatedButton.styleFrom(
                  //     minimumSize: const Size(300, 7),
                  //     backgroundColor: const Color.fromARGB(255, 93, 0, 255),
                  //   ),
                  //   onPressed: () {},
                  //   child: const Padding(
                  //     padding: EdgeInsets.only(bottom: 10.0),
                  //     child: Text(
                  //       'ورود',
                  //       textAlign: TextAlign.center,
                  //       style: TextStyle(
                  //         fontFamily: 'pinar',
                  //         color: Colors.white,
                  //         fontSize: 30,
                  //       ),
                  //     ),
                  //   ),
                  // )
                ],
              ),
            ),
          ),
          // const Text('choose your university'),
          // Row(
          //     mainAxisAlignment: MainAxisAlignment.spaceAround,
          //     textDirection: TextDirection.rtl,
          //     children: [
          //       createCheckbox('شهید بهشتی', sbu),
          //       createCheckbox('شریف', ssh),
          //       createCheckbox('قم', qom),
          //       createCheckbox('کاشان', kashan),
          //     ])
        ],
      ),
    );
  }

  Widget createCheckbox(String title, bool isActive) {
    return Column(
      textDirection: TextDirection.rtl,
      children: [
        Text(title),
        Checkbox(
          checkColor: Colors.purple,
          value: isActive,
          onChanged: (bool? value) {
            switch (title) {
              case 'شهید بهشتی':
                setState(
                  () {
                    sbu = value!;
                  },
                );
              default:
                setState(
                  () {
                    qom = value!;
                  },
                );
            }
          },
        ),
      ],
    );
  }
}
