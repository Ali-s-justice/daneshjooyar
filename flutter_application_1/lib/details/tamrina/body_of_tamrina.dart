import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/user_data.dart';

import '../classes/student.dart';
import '../functions.dart';

class BodyOfTamrina extends StatefulWidget {
  const BodyOfTamrina({super.key});

  @override
  State<BodyOfTamrina> createState() => _BodyOfTamrinaState();
}

class _BodyOfTamrinaState extends State<BodyOfTamrina> {
  Widget selectedItem = const ActivesPr();
  int selected = 1;
  int selected2 = 1;

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return SingleChildScrollView(
      child: Stack(
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
              padding: EdgeInsets.only(top: heightOfScreen * 0.09),
              child: selectedItem,
            ),
          ),
          // Positioned(
          //     top: heightOfScreen * 0.135,
          //     left: widthOfScreen * 0.1,
          //     right: widthOfScreen * 0.1,
          //     child: Container(
          //       height: 1,
          //       color: Colors.black,
          //       width: widthOfScreen * 0.75,
          //     )),
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
                    width: widthOfScreen * 0.35,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      // ignore: prefer_const_constructors
                      color: (selected == 1)
                          ? const Color(0xFFE4014E)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'تمرین های فعال',
                      style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.017,
                          fontWeight: FontWeight.bold,
                          color: Colors.white),
                    ),
                  ),
                  onTap: () {
                    if (selected != 1) {
                      setState(() {
                        selected = 1;
                        selectedItem = const ActivesPr();
                      });
                    }
                  },
                ),
                SizedBox(
                  width: widthOfScreen * 0.01,
                ),
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.05,
                    width: widthOfScreen * 0.35,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: (selected == 2)
                          ? const Color(0xFFE4014E)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'تمرین های غیرفعال',
                      style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.017,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ),
                  onTap: () {
                    if (selected != 2) {
                      setState(() {
                        selected = 2;
                        selectedItem = const InActivesPr();
                      });
                    }
                  },
                ),
              ],
            ),
          ),
          // Positioned(
          //   top: heightOfScreen * 0.120,
          //   right: widthOfScreen * 0.15,
          //   child: Row(
          //     textDirection: TextDirection.rtl,
          //     children: [
          //       InkWell(
          //         child: Container(
          //           alignment: Alignment.center,
          //           height: heightOfScreen * 0.03,
          //           width: widthOfScreen * 0.15,
          //           decoration: BoxDecoration(
          //             borderRadius: BorderRadius.circular(15),
          //             // ignore: prefer_const_constructors
          //             color: (selected2 == 1)
          //                 ? const Color(0xFF2DD634)
          //                 : const Color.fromARGB(255, 123, 123, 123),
          //             border: Border.all(),
          //           ),
          //           child: Text(
          //             'امروز',
          //             style: TextStyle(
          //               fontFamily: 'vazir',
          //               fontSize: heightOfScreen * 0.017,
          //               fontWeight: FontWeight.bold,
          //               color: Colors.white,
          //             ),
          //           ),
          //         ),
          //         onTap: () {
          //           if (selected2 != 1) {
          //             setState(() {
          //               selected2 = 1;
          //               //selectedItem =
          //             });
          //           }
          //         },
          //       ),
          //       SizedBox(
          //         width: widthOfScreen * 0.01,
          //       ),
          //       InkWell(
          //         child: Container(
          //           alignment: Alignment.center,
          //           height: heightOfScreen * 0.03,
          //           width: widthOfScreen * 0.25,
          //           decoration: BoxDecoration(
          //             borderRadius: BorderRadius.circular(15),
          //             color: (selected2 == 2)
          //                 ? const Color(0xFF2DD634)
          //                 : const Color.fromARGB(255, 123, 123, 123),
          //             border: Border.all(),
          //           ),
          //           child: Text(
          //             'انتخاب تاریخ',
          //             style: TextStyle(
          //                 fontFamily: 'vazir',
          //                 fontSize: heightOfScreen * 0.017,
          //                 fontWeight: FontWeight.bold,
          //                 color: Colors.white),
          //           ),
          //         ),
          //         onTap: () {
          //           if (selected2 != 2) {
          //             setState(() {
          //               selected2 = 2;
          //               //selectedItem =
          //             });
          //           }
          //         },
          //       ),
          //     ],
          //   ),
          // ),
        ],
      ),
    );
  }
}

class ActivesPr extends StatefulWidget {
  const ActivesPr({super.key});

  @override
  State<ActivesPr> createState() => _ActivesPrState();
}

class _ActivesPrState extends State<ActivesPr> {
  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}

//**************************************************************** */
class InActivesPr extends StatefulWidget {
  const InActivesPr({super.key});

  @override
  State<InActivesPr> createState() => _InActivesPrState();
}

class _InActivesPrState extends State<InActivesPr> {
  List<List<String>> expiredPractices = [];
  List<bool> isExpanded = [];
  String responseOfInActivePractice = '-';
  @override
  void initState() {
    super.initState();
    loadData();
  }

  void loadData() async {
    String message;
    message = await fetchInActivePractice();
    expiredPractices = HelperFunctions.stringToListOfList(message.trim());
    isExpanded = List<bool>.generate(expiredPractices.length, (_) => false);
  }

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
                            const Color(0xFF6A6A6A),
                            const Color(0xFF292A38)
                          ]
                        : [const Color(0xFF292A38), const Color(0xFF6A6A6A)]),
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
                      expiredPractices[index][0],
                      textDirection: TextDirection.rtl,
                      textAlign: TextAlign.justify,
                      style: styleOfTiltle,
                    ),
                  ),
                  const SizedBox(height: 10),
                  if (isExpanded[index])
                    Text(
                      expiredPractices[index][1],
                      textDirection: TextDirection.rtl,
                      style: styleOfdescription,
                      textAlign: TextAlign.justify,
                      maxLines: 4,
                      overflow: TextOverflow.ellipsis,
                    ),
                ],
              ),
            ),
          ),
        );
      },
    );
  }

  Future<String> fetchInActivePractice() async {
    final completer = Completer<String>();

    await Socket.connect("192.168.69.234", 3559).then(
      (serverSocket) {
        serverSocket.write('userInfo//${UserData.studentCode}\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseOfInActivePractice = utf8.decode(socketResponse);
            });
            completer.complete(responseOfInActivePractice);
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
