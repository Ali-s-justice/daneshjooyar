import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_application_1/details/functions.dart';
import 'package:flutter_application_1/details/kara/kara.dart';

import '../../services/server_connection_info.dart';
import '../user_data.dart';

class BodyOfKara extends StatefulWidget {
  const BodyOfKara({super.key});

  @override
  State<BodyOfKara> createState() => _BodyOfKaraState();
}

class _BodyOfKaraState extends State<BodyOfKara> {
  final GlobalKey<_DontState> _childKey = GlobalKey();

  void _callChildMethod() {
    _childKey.currentState?.initState();
  }

  int selected = 1;
  Widget selectedWidget = const Dont();
  String responseAddNewJob = '-';
  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return Stack(
      children: [
        Padding(
          padding: EdgeInsets.only(
            top: heightOfScreen * 0.08,
            left: widthOfScreen * 0.05,
            right: widthOfScreen * 0.05,
          ),
          child: Container(
            decoration: BoxDecoration(
              color: const Color.fromARGB(255, 111, 207, 255),
              borderRadius: const BorderRadius.only(
                topLeft: Radius.circular(25),
                topRight: Radius.circular(25),
              ),
              border: Border.all(),
            ),
            constraints: BoxConstraints(minHeight: heightOfScreen),
            padding: EdgeInsets.only(top: heightOfScreen * 0.05),
            child: selectedWidget,
          ),
        ),
        Positioned(
          top: heightOfScreen * 0.06,
          right: widthOfScreen * 0.09,
          child: Row(
            textDirection: TextDirection.rtl,
            children: [
              InkWell(
                child: Container(
                  alignment: Alignment.center,
                  height: heightOfScreen * 0.05,
                  width: widthOfScreen * 0.25,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(15),
                    color:
                        (selected == 1) ? const Color(0xFFE4014E) : Colors.grey,
                    border: Border.all(),
                  ),
                  child: Text(
                    'انجام نشده',
                    style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.02,
                        fontWeight: FontWeight.bold,
                        color: Colors.white),
                  ),
                ),
                onTap: () {
                  setState(() {
                    if (selected == 2) {
                      selected = 1;
                      selectedWidget = const Dont();
                    }
                  });
                },
              ),
              SizedBox(
                width: widthOfScreen * 0.01,
              ),
              InkWell(
                child: Container(
                  alignment: Alignment.center,
                  height: heightOfScreen * 0.05,
                  width: widthOfScreen * 0.25,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(15),
                    color:
                        (selected == 2) ? const Color(0xFFE4014E) : Colors.grey,
                    border: Border.all(),
                  ),
                  child: Text(
                    'انجام شده',
                    style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.02,
                        fontWeight: FontWeight.bold,
                        color: Colors.white),
                  ),
                ),
                onTap: () {
                  setState(() {
                    if (selected == 1) {
                      selected = 2;
                      selectedWidget = const Done();
                    }
                  });
                },
              ),
              SizedBox(
                width: widthOfScreen * 0.2,
              ),
              InkWell(
                child: Container(
                  decoration: BoxDecoration(
                    color: const Color.fromARGB(255, 9, 255, 0),
                    borderRadius: const BorderRadius.only(
                        topLeft: Radius.circular(35),
                        topRight: Radius.circular(35),
                        bottomLeft: Radius.circular(35),
                        bottomRight: Radius.circular(10)),
                    border: Border.all(),
                  ),
                  width: widthOfScreen * 0.14,
                  height: heightOfScreen * 0.055,
                  child: Center(
                    child: Icon(
                      Icons.add,
                      size: heightOfScreen * 0.05,
                      color: Colors.white,
                      // ignore: prefer_const_literals_to_create_immutables
                      shadows: <Shadow>[
                        const Shadow(
                          color: Colors.black,
                          offset: Offset(1.5, 1.5),
                        ),
                        const Shadow(
                          color: Colors.black,
                          offset: Offset(-1.5, 1.5),
                        ),
                        const Shadow(
                          color: Colors.black,
                          offset: Offset(-1.5, -1.5),
                        ),
                        const Shadow(
                          color: Colors.black,
                          offset: Offset(1.5, -1.5),
                        ),
                      ],
                    ),
                  ),
                ),
                onTap: () {
                  _showDialog(context, widthOfScreen, heightOfScreen);
                },
              ),
            ],
          ),
        ),
      ],
    );
  }

  void _showDialog(
      BuildContext context, double widthOfScreen, double heightOfScreen) {
    final TextEditingController titleController = TextEditingController();
    final TextEditingController descriptionController = TextEditingController();
    final TextEditingController dayController = TextEditingController();
    final TextEditingController hourController = TextEditingController();
    final TextEditingController minController = TextEditingController();
    final TextEditingController monthController = TextEditingController();
    final TextEditingController yearController = TextEditingController();

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(25),
            side: const BorderSide(color: Colors.black, width: 3),
          ),
          backgroundColor: const Color.fromRGBO(25, 0, 126, 1),
          title: const Align(
            alignment: Alignment.centerRight,
            child: Text(
              'افزودن کار جدید',
              style: TextStyle(
                color: Colors.white,
              ),
              textDirection: TextDirection.rtl,
            ),
          ),
          content: SingleChildScrollView(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Row(
                  textDirection: TextDirection.rtl,
                  children: [
                    SizedBox(
                      width: widthOfScreen * 0.020,
                    ),
                    Text(
                      ':عنوان',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.035),
                    ),
                  ],
                ),
                Align(
                  alignment: Alignment.centerRight,
                  child: SizedBox(
                    height: heightOfScreen * 0.05,
                    width: widthOfScreen * 0.5,
                    child: TextField(
                      decoration: InputDecoration(
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(25),
                        ),
                        filled: true,
                        fillColor: Colors.white,
                      ),
                      controller: titleController,
                      textDirection: TextDirection.rtl,
                    ),
                  ),
                ),
                SizedBox(
                  height: heightOfScreen * 0.015,
                ),
                Row(
                  textDirection: TextDirection.rtl,
                  children: [
                    SizedBox(
                      width: widthOfScreen * 0.020,
                    ),
                    Text(
                      ':توضیحات',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.035),
                    ),
                  ],
                ),
                SizedBox(
                  height: heightOfScreen * 0.2,
                  child: TextField(
                    controller: descriptionController,
                    decoration: InputDecoration(
                      border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(25),
                      ),
                      filled: true,
                      fillColor: Colors.white,
                    ),
                    textDirection: TextDirection.rtl,
                    maxLines: null,
                    minLines: 3,
                  ),
                ),
                // SizedBox(
                //   height: heightOfScreen * 0.02,
                // ),
                Row(
                  textDirection: TextDirection.rtl,
                  children: [
                    SizedBox(
                      width: widthOfScreen * 0.020,
                    ),
                    Text(
                      ':ساعت',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.035),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.005,
                    ),
                    Align(
                      alignment: Alignment.centerRight,
                      child: SizedBox(
                        height: heightOfScreen * 0.05,
                        width: widthOfScreen * 0.15,
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: [
                            LengthLimitingTextInputFormatter(2),
                            FilteringTextInputFormatter.digitsOnly,
                          ],
                          decoration: InputDecoration(
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(25),
                            ),
                            filled: true,
                            fillColor: Colors.white,
                            // label: Text(
                            //   'DD',
                            //   style: TextStyle(
                            //     fontSize: heightOfScreen * 0.013,
                            //   ),
                            // ),
                            hintText: 'mm',
                          ),
                          controller: minController,
                          textDirection: TextDirection.rtl,
                        ),
                      ),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.010,
                    ),
                    Align(
                      alignment: Alignment.centerRight,
                      child: SizedBox(
                        height: heightOfScreen * 0.05,
                        width: widthOfScreen * 0.15,
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: [
                            LengthLimitingTextInputFormatter(2),
                            FilteringTextInputFormatter.digitsOnly,
                          ],
                          decoration: InputDecoration(
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(25),
                            ),
                            filled: true,
                            fillColor: Colors.white,
                            hintText: 'hh',
                          ),
                          controller: hourController,
                          textDirection: TextDirection.rtl,
                        ),
                      ),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.010,
                    ),
                  ],
                ),
                Row(
                  textDirection: TextDirection.rtl,
                  children: [
                    SizedBox(
                      width: widthOfScreen * 0.020,
                    ),
                    Text(
                      ': تاریخ',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.035),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.01,
                    ),
                    Align(
                      alignment: Alignment.centerRight,
                      child: SizedBox(
                        height: heightOfScreen * 0.05,
                        width: widthOfScreen * 0.15,
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: [
                            LengthLimitingTextInputFormatter(2),
                            FilteringTextInputFormatter.digitsOnly,
                          ],
                          decoration: InputDecoration(
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(25),
                            ),
                            filled: true,
                            fillColor: Colors.white,
                            // label: Text(
                            //   'DD',
                            //   style: TextStyle(
                            //     fontSize: heightOfScreen * 0.013,
                            //   ),
                            // ),
                            hintText: 'DD',
                          ),
                          controller: dayController,
                          textDirection: TextDirection.rtl,
                        ),
                      ),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.010,
                    ),
                    Align(
                      alignment: Alignment.centerRight,
                      child: SizedBox(
                        height: heightOfScreen * 0.05,
                        width: widthOfScreen * 0.15,
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: [
                            LengthLimitingTextInputFormatter(2),
                            FilteringTextInputFormatter.digitsOnly,
                          ],
                          decoration: InputDecoration(
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(25),
                            ),
                            filled: true,
                            fillColor: Colors.white,
                            hintText: 'MM',
                          ),
                          controller: monthController,
                          textDirection: TextDirection.rtl,
                        ),
                      ),
                    ),
                    SizedBox(
                      width: widthOfScreen * 0.010,
                    ),
                    Align(
                      alignment: Alignment.centerRight,
                      child: SizedBox(
                        height: heightOfScreen * 0.05,
                        width: widthOfScreen * 0.15,
                        child: TextField(
                          keyboardType: TextInputType.number,
                          inputFormatters: [
                            LengthLimitingTextInputFormatter(2),
                            FilteringTextInputFormatter.digitsOnly,
                          ],
                          decoration: InputDecoration(
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(25),
                            ),
                            filled: true,
                            fillColor: Colors.white,
                            hintText: 'YY',
                          ),
                          controller: yearController,
                          textDirection: TextDirection.rtl,
                        ),
                      ),
                    ),
                  ],
                ),
              ],
            ),
          ),
          actions: [
            ElevatedButton(
              style: TextButton.styleFrom(
                backgroundColor: const Color.fromARGB(255, 165, 27, 27),
              ),
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text(
                'لغو',
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontFamily: 'vazir',
                ),
              ),
            ),
            ElevatedButton(
              style: TextButton.styleFrom(
                backgroundColor: const Color.fromARGB(255, 34, 147, 30),
              ),
              onPressed: () {
                // Handle save action
                String title = titleController.text;
                String description = descriptionController.text;

                String day =
                    HelperFunctions.padLeftWithZero(dayController.text);
                String month =
                    HelperFunctions.padLeftWithZero(monthController.text);
                String year =
                    HelperFunctions.padLeftWithZero(yearController.text);
                String hour =
                    HelperFunctions.padLeftWithZero(hourController.text);
                String minute =
                    HelperFunctions.padLeftWithZero(minController.text);
                addNewJob(title, description, day, month, year, hour, minute);
                _callChildMethod();
                Navigator.pushReplacementNamed(context, Kara.routeName);
                // selectedWidget = const Dont();
                // Navigator.pushReplacementNamed(context, Kara.routeName);
                //Navigator.pushReplacementNamed(context, Kara.routeName);
                // Add your save logic here
                //print('Title: $title, Description: $description');
              },
              child: const Text(
                'افزودن',
                style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontFamily: 'vazir',
                ),
              ),
            ),
          ],
        );
      },
    );
  }

  Future<String> addNewJob(String title, String description, String day,
      String month, String year, String hour, String minute) async {
    final completer = Completer<String>();
    print('*****************************************$title');
    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write(
            'addJob//20$year/$month/$day,$hour:$minute//${UserData.studentCode}//$title//$description\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseAddNewJob = utf8.decode(socketResponse);
            });
            completer.complete(responseAddNewJob);
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

class Dont extends StatefulWidget {
  const Dont({super.key});

  @override
  State<Dont> createState() => _DontState();
}

class _DontState extends State<Dont> {
  List<List<String>> listOfList = [];
  List<bool> isExpanded = [];
  String responseDontAssignment = '-';
  String responseSetDone = '-';
  bool empty = false;
  @override
  void initState() {
    super.initState();
    fetchData();
  }

  void fetchData() async {
    String message = await fetchDontAssignment();
    listOfList = HelperFunctions.stringToListOfList(message.trim());
    isExpanded = List<bool>.generate(listOfList.length, (_) => false);
    print(listOfList.toString());
    if (listOfList[0][0] == '404') {
      empty = true;
    } else {
      empty = false;
    }
  }

  // final List<List<String>> listOfList = [
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  // ];

  static const styleOfdescription = TextStyle(
    fontFamily: 'vazir',
    fontSize: 15,
    color: Colors.white,
    fontWeight: FontWeight.w900,
  );
  static const styleOfTiltle = TextStyle(
    color: Color.fromARGB(255, 255, 228, 74),
    fontSize: 16,
    fontFamily: 'vazir',
    fontWeight: FontWeight.w900,
  );
  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return ListView.builder(
      itemCount: listOfList.length,
      itemBuilder: (context, index) {
        return GestureDetector(
          onTap: () {
            setState(() {
              isExpanded[index] = !isExpanded[index];
            });
          },
          child: AnimatedContainer(
            duration: const Duration(milliseconds: 350),
            margin: const EdgeInsets.all(10),
            padding: const EdgeInsets.all(10),
            decoration: BoxDecoration(
                gradient: LinearGradient(
                    begin: const Alignment(1.00, 0.07),
                    end: const Alignment(-1, -0.07),
                    colors: isExpanded[index]
                        ? [
                            // ignore: prefer_const_constructors
                            Color.fromARGB(255, 129, 30, 138),
                            const Color.fromARGB(255, 132, 126, 197),
                          ]
                        : [
                            const Color(0xFF1523AF),
                            const Color(0xFF00A1D3),
                          ]),
                borderRadius: BorderRadius.circular(23),
                border: Border.all(color: Colors.black, width: 2.0)),
            height: isExpanded[index] ? 200 : 50,
            child: SingleChildScrollView(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  AnimatedAlign(
                    duration: const Duration(milliseconds: 350),
                    alignment: isExpanded[index]
                        ? Alignment.center
                        : Alignment.centerRight,
                    child: Text(
                      (empty)
                          ? 'محتوایی جهت نمایش وجود ندار!'
                          : listOfList[index][0],
                      textDirection: TextDirection.rtl,
                      textAlign: TextAlign.justify,
                      style: styleOfTiltle,
                    ),
                  ),
                  const SizedBox(height: 10),
                  if (isExpanded[index] && !empty)
                    Column(
                      children: [
                        Text(
                          listOfList[index][1],
                          textDirection: TextDirection.rtl,
                          style: styleOfdescription,
                          textAlign: TextAlign.justify,
                          maxLines: 4,
                          overflow: TextOverflow.ellipsis,
                        ),
                        SizedBox(
                          height: heightOfScreen * 0.01,
                        ),
                        Row(
                          textDirection: TextDirection.rtl,
                          children: [
                            Align(
                              alignment: Alignment.centerRight,
                              child: Text(
                                '${listOfList[index][2]} روز و ${listOfList[index][3]} ساعت و ${listOfList[index][4]} دقیقه',
                                textDirection: TextDirection.rtl,
                                style: styleOfdescription,
                                textAlign: TextAlign.justify,
                              ),
                            ),
                            SizedBox(
                              width: widthOfScreen * 0.03,
                            ),
                            IconButton(
                              onPressed: () {
                                setDone(listOfList[index][5]);
                                fetchData();
                              },
                              icon: Icon(
                                Icons.done_outline_rounded,
                                size: widthOfScreen * 0.08,
                                color: const Color.fromARGB(255, 0, 255, 8),
                              ),
                            ),
                            IconButton(
                              onPressed: () {
                                deleteJob(listOfList[index][5]);
                                fetchData();
                              },
                              icon: Icon(
                                Icons.delete,
                                size: widthOfScreen * 0.08,
                                // ignore: prefer_const_constructors
                                color:
                                    // ignore: prefer_const_constructors
                                    Color.fromARGB(255, 255, 0, 0),
                              ),
                            ),
                          ],
                        ),
                      ],
                    ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  Future<String> fetchDontAssignment() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('jobNotDone//${UserData.studentCode}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseDontAssignment = utf8.decode(socketResponse);
            });
            completer.complete(responseDontAssignment);
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

  Future<String> setDone(String jobId) async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('setJobDone//$jobId\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseSetDone = utf8.decode(socketResponse);
            });
            completer.complete(responseSetDone);
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

  Future<String> deleteJob(String jobId) async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('deleteJob//${UserData.studentCode}//$jobId\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseSetDone = utf8.decode(socketResponse);
            });
            completer.complete(responseSetDone);
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

class Done extends StatefulWidget {
  const Done({super.key});

  @override
  State<Done> createState() => _DoneState();
}

class _DoneState extends State<Done> {
  List<List<String>> listOfList = [];
  List<bool> isExpanded = [];

  String responseDoneAssignment = '-';
  bool empty = false;
  @override
  void initState() {
    super.initState();
    fetchData();
  }

  void fetchData() async {
    String message = await fetchDoneAssignment();
    listOfList = HelperFunctions.stringToListOfList(message.trim());
    isExpanded = List<bool>.generate(listOfList.length, (_) => false);
    if (listOfList[0][0] == '404') {
      empty = true;
    } else {
      empty = false;
    }
  }

  // final List<List<String>> listOfList = [
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  //   [
  //     'حل تمرین فیزیک',
  //     'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
  //     '2',
  //     '12',
  //     '40',
  //     '1'
  //   ],
  // ];

  static const styleOfdescription = TextStyle(
    fontFamily: 'vazir',
    fontSize: 15,
    color: Colors.white,
    fontWeight: FontWeight.w900,
  );
  static const styleOfTiltle = TextStyle(
    color: Color.fromARGB(255, 255, 255, 255),
    fontSize: 16,
    fontFamily: 'vazir',
    fontWeight: FontWeight.w900,
  );
  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return ListView.builder(
      itemCount: isExpanded.length,
      itemBuilder: (context, index) {
        return GestureDetector(
          onTap: () {
            setState(() {
              isExpanded[index] = !isExpanded[index];
            });
          },
          child: AnimatedContainer(
            duration: const Duration(milliseconds: 350),
            margin: const EdgeInsets.all(10),
            padding: const EdgeInsets.all(10),
            decoration: BoxDecoration(
                gradient: LinearGradient(
                    begin: const Alignment(1.00, 0.07),
                    end: const Alignment(-1, -0.07),
                    colors: isExpanded[index]
                        ? [
                            // ignore: prefer_const_constructors
                            Color.fromARGB(255, 129, 30, 138),
                            const Color.fromARGB(255, 132, 126, 197),
                          ]
                        : [
                            const Color(0xFF008805),
                            const Color(0xFF00BD07),
                          ]),
                borderRadius: BorderRadius.circular(23),
                border: Border.all(color: Colors.black, width: 2.0)),
            height: isExpanded[index] ? 170 : 50,
            child: SingleChildScrollView(
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  AnimatedAlign(
                    duration: const Duration(milliseconds: 350),
                    alignment: isExpanded[index]
                        ? Alignment.center
                        : Alignment.centerRight,
                    child: Text(
                      (empty)
                          ? 'محتوایی جهت نمایش وجود ندارد!'
                          : listOfList[index][0],
                      textDirection: TextDirection.rtl,
                      textAlign: TextAlign.justify,
                      style: styleOfTiltle,
                    ),
                  ),
                  const SizedBox(height: 10),
                  if (isExpanded[index] && !empty)
                    Column(
                      children: [
                        Align(
                          alignment: Alignment.centerRight,
                          child: Text(
                            listOfList[index][1],
                            textDirection: TextDirection.rtl,
                            style: styleOfdescription,
                            textAlign: TextAlign.justify,
                            maxLines: 4,
                            overflow: TextOverflow.ellipsis,
                          ),
                        ),
                        SizedBox(
                          height: heightOfScreen * 0.03,
                        ),
                        // Row(
                        //   textDirection: TextDirection.rtl,
                        //   children: [
                        //     Align(
                        //       alignment: Alignment.centerRight,
                        //       child: Text(
                        //         '${listOfList[index][2]} روز و ${listOfList[index][3]} ساعت و ${listOfList[index][4]} دقیقه',
                        //         textDirection: TextDirection.rtl,
                        //         style: styleOfdescription,
                        //         textAlign: TextAlign.justify,
                        //       ),
                        //     ),
                        //     SizedBox(
                        //       width: widthOfScreen * 0.1,
                        //     ),
                        //     IconButton(
                        //       onPressed: () {},
                        //       icon: Icon(
                        //         Icons.done_outline_rounded,
                        //         size: widthOfScreen * 0.08,
                        //         color: const Color.fromARGB(255, 0, 255, 8),
                        //       ),
                        //     ),
                        //     IconButton(
                        //       onPressed: () {},
                        //       icon: Icon(
                        //         Icons.delete,
                        //         size: widthOfScreen * 0.08,
                        //         // ignore: prefer_const_constructors
                        //         color:
                        //             // ignore: prefer_const_constructors
                        //             Color.fromARGB(255, 255, 0, 0),
                        //       ),
                        //     ),
                        //   ],
                        // ),
                      ],
                    ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  Future<String> fetchDoneAssignment() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('doneJob//${UserData.studentCode}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseDoneAssignment = utf8.decode(socketResponse);
            });
            completer.complete(responseDoneAssignment);
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
