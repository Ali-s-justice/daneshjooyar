import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class MyBody extends StatefulWidget {
  const MyBody({super.key});

  @override
  State<MyBody> createState() => _MyBodyState();
}

class _MyBodyState extends State<MyBody> {
  final _keyform = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.start,
      children: [
        const SizedBox(
          height: 120,
        ),
        const Text(
          'به درسا خوش آمدید!',
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
                    textDirection: TextDirection.rtl,
                    validator: (String? value) {
                      if (value!.isEmpty) {
                        return 'user name must be grater than 2 character';
                      } else {
                        return "";
                      }
                    },
                    decoration: InputDecoration(
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(25),
                      ),
                      filled: true,
                      fillColor: const Color.fromARGB(255, 239, 227, 233),
                      // hintText : 'سید امیرحسین اشرفیان',
                      label: const Text(
                        'نام و نام خانوادگی',
                        textDirection: TextDirection.rtl,
                        style: TextStyle(),
                      ),
                      // labelText: 'نام و نام خانوادگی',
                    ),
                  ),
                  const SizedBox(
                    height: 11,
                  ),
                  TextFormField(
                    obscureText: true,
                    textDirection: TextDirection.rtl,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(25),
                      ),
                      filled: true,
                      fillColor: const Color.fromARGB(255, 239, 227, 233),
                      labelText: 'رمز عبور',
                    ),
                  ),
                  const SizedBox(
                    height: 50.0,
                  ),
                  ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        minimumSize: const Size(250, 50),
                        backgroundColor: const Color.fromARGB(255, 93, 0, 255),
                      ),
                      onPressed: () {},
                      child: const Text(
                        'ورود',
                        style: TextStyle(
                          // fontFamily: 'pinar',
                          color: Colors.white,
                          fontSize: 50,
                        ),
                      ))
                ],
              ),
            )),
      ],
    );
  }
}
