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
  late TextEditingController studentCodeController;
  late TextEditingController nameController;

  static const textFormStyle = TextStyle(
      color: Colors.black, fontSize: 25.0, fontWeight: FontWeight.w900);
  final _keyform = GlobalKey<FormState>();
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
    usernameController = TextEditingController(text: currentStudent.username);
    studentCodeController =
        TextEditingController(text: currentStudent.studenCode);
    nameController = TextEditingController(text: currentStudent.name);
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent,
        appBar: AppBar(
          centerTitle: true,
          elevation: 0.0,
          backgroundColor: Colors.transparent,
          title: const Text(
            'ویرایش مشخصات',
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
                                  ': نام کاربری جدید',
                                  style: textFormStyle,
                                ),
                              ),
                              TextFormField(
                                controller: usernameController,
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
                                  ': شماره دانشجویی جدید',
                                  style: textFormStyle,
                                ),
                              ),
                              TextFormField(
                                controller: studentCodeController,
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
                                  ': نام و نام خانوادگی جدید',
                                  style: textFormStyle,
                                ),
                              ),
                              TextFormField(
                                controller: nameController,
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
                                  setState(() {
                                    currentStudent.username =
                                        usernameController.text;
                                    currentStudent.studenCode =
                                        studentCodeController.text;
                                    currentStudent.name = nameController.text;
                                    Navigator.pushNamed(
                                        context, Information.routeName,
                                        arguments: currentStudent);
                                  });
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
