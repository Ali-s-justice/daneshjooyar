import 'package:flutter/material.dart';
import 'classes/student.dart';

class EditPassword extends StatefulWidget {
  static const String routeName = 'editPassword';
  const EditPassword({super.key});

  @override
  State<EditPassword> createState() => _EditPasswordState();
}

class _EditPasswordState extends State<EditPassword> {
  late TextEditingController passwordController;

  final _keyform = GlobalKey<FormState>();
  static const textFormStyle = TextStyle(
      color: Colors.black, fontSize: 25.0, fontWeight: FontWeight.w900);
  BoxDecoration get gradientBackground => const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color.fromARGB(255, 93, 0, 255),
            Color.fromARGB(255, 131, 58, 180),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      );
  @override
  Widget build(BuildContext context) {
    final Student currentStudent =
        ModalRoute.of(context)!.settings.arguments as Student;
    passwordController = TextEditingController(text: currentStudent.password);
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          centerTitle: true,
          elevation: 0.0,
          backgroundColor: Colors.transparent,
          title: const Text(
            'ویرایش رمز عبور',
            style: TextStyle(
              color: Colors.white,
              fontSize: 30,
              fontFamily: 'vazir',
            ),
          ),
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              const SizedBox(
                height: 60,
              ),
              Padding(
                padding: const EdgeInsets.only(
                  top: 0,
                  right: 10,
                  left: 10,
                ),
                child: Container(
                  decoration: BoxDecoration(
                    color: Colors.white.withOpacity(0.8),
                    borderRadius: BorderRadius.circular(25),
                  ),
                  height: 730,
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
                      child: Form(
                        key: _keyform,
                        child: Padding(
                          padding: const EdgeInsets.all(8.0),
                          child: Column(
                            children: <Widget>[
                              const Align(
                                alignment: Alignment(0.8, 0),
                                child: Text(
                                  ': رمز عبور فعلی',
                                  style: textFormStyle,
                                ),
                              ),
                              TextFormField(
                                decoration: InputDecoration(
                                  border: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(25),
                                  ),
                                  filled: true,
                                  fillColor:
                                      const Color.fromARGB(255, 239, 227, 233),
                                ),
                              ),
                              const SizedBox(
                                height: 20,
                              ),
                              const Align(
                                alignment: Alignment(0.8, 0),
                                child: Text(
                                  ': رمز عبور جدید',
                                  style: textFormStyle,
                                ),
                              ),
                              TextFormField(
                                validator: (String? value) {
                                  currentStudent.password = value;
                                  if (!RegExp(
                                          "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
                                      .hasMatch(value!)) {
                                    return "!حداقل 8 حرف شامل حروف و کوچک و بزرگ و اعداد";
                                  } else if (value
                                      .contains(currentStudent.username!)) {
                                    return ".رمز عبور نباید شامل نام کاربری باشد";
                                  } else {
                                    return null;
                                  }
                                },
                                controller: passwordController,
                                decoration: InputDecoration(
                                  border: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(25),
                                  ),
                                  filled: true,
                                  fillColor:
                                      const Color.fromARGB(255, 239, 227, 233),
                                ),
                              ),
                              const SizedBox(
                                height: 20,
                              ),
                              const Align(
                                alignment: Alignment(0.8, 0),
                                child: Text(
                                  ': تکرار رمز عبور جدید',
                                  style: textFormStyle,
                                ),
                              ),
                              TextFormField(
                                controller: passwordController,
                                decoration: InputDecoration(
                                  border: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(25),
                                  ),
                                  filled: true,
                                  fillColor:
                                      const Color.fromARGB(255, 239, 227, 233),
                                ),
                              ),
                              const SizedBox(
                                height: 150,
                              ),
                              ElevatedButton(
                                style: ElevatedButton.styleFrom(
                                  minimumSize: const Size(340.0, 50),
                                  backgroundColor: Colors.orange,
                                  shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20.0),
                                  ),
                                ),
                                onPressed: () {
                                  setState(() {
                                    _keyform.currentState!.reset();
                                  });
                                },
                                child: const Padding(
                                  padding: EdgeInsets.symmetric(vertical: 10.0),
                                  child: Text(
                                    'صرف نظر کردن از تغییرات',
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      fontFamily: 'pinar',
                                      color: Colors.white,
                                      fontSize: 30,
                                    ),
                                  ),
                                ),
                              ),
                              const SizedBox(
                                height: 20,
                              ),
                              ElevatedButton(
                                style: ElevatedButton.styleFrom(
                                  minimumSize: const Size(340.0, 50),
                                  backgroundColor: Colors.green,
                                  shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(20.0),
                                  ),
                                ),
                                onPressed: () {
                                  setState(() {});
                                },
                                child: const Padding(
                                  padding: EdgeInsets.symmetric(vertical: 10.0),
                                  child: Text(
                                    'ثبت تغییرات',
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
                  ),
                ),
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
