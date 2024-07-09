import 'dart:async';
import 'dart:convert';
import 'dart:io';

import 'package:file_picker/file_picker.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';
import 'package:flutter_application_1/details/tamrina/tamrina.dart';
import 'package:flutter_application_1/details/user_data.dart';

import '../../services/server_connection_info.dart';
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
                  bottomLeft: Radius.circular(15),
                  bottomRight: Radius.circular(15),
                ),
                border: Border.all(),
              ),
              constraints: BoxConstraints(minHeight: heightOfScreen),
              padding: EdgeInsets.only(top: heightOfScreen * 0.09),
              child: Align(alignment: Alignment.topCenter, child: selectedItem),
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
  final List<String> sortOptions = ['0', '1', '2'];
  String selectedSortOption = '0';
  int selected2 = 1;
  String responseActivePr = '-';
  String responseUpload = '-';
  //*** */
  List<List<String>> practices = [];
  List<bool> isExpanded = [];
  bool empty = false;
  @override
  void initState() {
    UserData.selectedDate = HelperFunctions.getFormattedDate();
    super.initState();
    fetchData();
  }

  void fetchData() async {
    print(
        '---------------------------------------------------${UserData.selectedDate}');
    String message = await fetchActivePrBySortOption(
        selectedSortOption, UserData.selectedDate!);
    setState(() {
      practices = HelperFunctions.stringToListOfList(message.trim());
      isExpanded = List<bool>.generate(practices.length, (_) => false);
      if (practices[0][0] == '404') {
        empty = true;
      } else {
        empty = false;
      }
    });
  }

  static const styleOfdescription = TextStyle(
    fontFamily: 'vazir',
    fontSize: 15,
    color: Colors.white,
    fontWeight: FontWeight.w900,
  );
  static const styleOfTiltle = TextStyle(
    color: Color.fromARGB(255, 255, 228, 74),
    fontSize: 17,
    fontFamily: 'vazir',
    fontWeight: FontWeight.w900,
  );

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return Column(
      children: [
        Stack(
          children: [
            Positioned(
              top: heightOfScreen * 0.025,
              left: widthOfScreen * 0.1,
              right: widthOfScreen * 0.1,
              child: Container(
                height: 1,
                color: Colors.black,
                width: widthOfScreen * 0.75,
              ),
            ),
            Row(
              textDirection: TextDirection.rtl,
              children: <Widget>[
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.03,
                    width: widthOfScreen * 0.15,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: (selected2 == 1)
                          ? const Color(0xFF2DD634)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'امروز',
                      style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.017,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ),
                  onTap: () {
                    if (selected2 != 1) {
                      setState(() {
                        selected2 = 1;

                        UserData.selectedDate =
                            HelperFunctions.getFormattedDate();
                        Navigator.pushReplacementNamed(
                            context, Tamrina.routeName);
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
                    height: heightOfScreen * 0.03,
                    width: widthOfScreen * 0.25,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      color: (selected2 == 2)
                          ? const Color(0xFF2DD634)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'انتخاب تاریخ',
                      style: TextStyle(
                        fontFamily: 'vazir',
                        fontSize: heightOfScreen * 0.017,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                      ),
                    ),
                  ),
                  onTap: () {
                    if (selected2 != 2) {
                      setState(() {
                        selected2 = 2;
                        UserData.selectedDate = null;
                        _selectDate(context, widthOfScreen, heightOfScreen);
                      });
                    }
                  },
                ),
                SizedBox(
                  width: widthOfScreen * 0.2,
                ),
                Container(
                  constraints: BoxConstraints(
                    minWidth: widthOfScreen * 0.2,
                  ),
                  decoration: BoxDecoration(
                    color: const Color.fromARGB(255, 73, 2, 113),
                    borderRadius: BorderRadius.circular(20),
                    border: Border.all(
                      color: Colors.black,
                      width: 1.0,
                    ),
                  ),
                  child: DropdownButton<String>(
                    alignment: Alignment.center,
                    dropdownColor: const Color.fromARGB(255, 73, 2, 113),
                    style: const TextStyle(
                      color: Color.fromARGB(255, 255, 255, 255),
                      fontFamily: 'vazir',
                    ),
                    borderRadius: BorderRadius.circular(20),
                    underline: const SizedBox(),
                    icon: const Icon(
                      Icons.sort,
                      color: Colors.white,
                    ),
                    value: selectedSortOption,
                    items: sortOptions.map((String dropdownButton) {
                      return DropdownMenuItem<String>(
                        value: dropdownButton,
                        child: (dropdownButton == '0')
                            ? const Text('ددلاین')
                            : (dropdownButton == '1')
                                ? const Text('زمان تخمینی')
                                : const Text('زمان انتشار'),
                      );
                    }).toList(),
                    onChanged: (String? value) {
                      setState(() {
                        selectedSortOption = value!;
                        fetchData(); // اضافه کردن این خط برای فراخوانی دوباره fetchData
                      });
                    },
                  ),
                ),
              ],
            ),
          ],
        ),
        SizedBox(
          height: heightOfScreen * 2,
          child: ListView.builder(
            itemCount: practices.length,
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
                  padding: EdgeInsets.all(widthOfScreen * 0.05),
                  decoration: BoxDecoration(
                      gradient: const LinearGradient(
                          begin: Alignment(1.00, 0.07),
                          end: Alignment(-1, -0.07),
                          colors: [Color(0xFF00A1D3), Color(0xFF1523AF)]),
                      borderRadius: BorderRadius.circular(23),
                      border: Border.all(color: Colors.black, width: 2.0)),
                  height: isExpanded[index]
                      ? heightOfScreen * 0.5
                      : heightOfScreen * 0.2,
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
                                : practices[index][0],
                            textDirection: TextDirection.rtl,
                            textAlign: TextAlign.justify,
                            style: styleOfTiltle,
                          ),
                        ),
                        // const SizedBox(height: 10),
                        if (isExpanded[index] && !empty)
                          Column(
                            children: [
                              SizedBox(
                                height: heightOfScreen * 0.01,
                              ),
                              Align(
                                alignment: Alignment.centerRight,
                                child: Text(
                                  'توضیحات : ${practices[index][2]}',
                                  textDirection: TextDirection.rtl,
                                  style: styleOfdescription,
                                  textAlign: TextAlign.start,
                                  maxLines: 4,
                                  overflow: TextOverflow.ellipsis,
                                ),
                              ),
                              SizedBox(
                                height: heightOfScreen * 0.02,
                              ),
                              Align(
                                alignment: Alignment.centerRight,
                                child: Text(
                                  'توضیحات دانشجو : ${practices[index][3]}',
                                  textDirection: TextDirection.rtl,
                                  style: styleOfdescription,
                                  textAlign: TextAlign.start,
                                  maxLines: 4,
                                  overflow: TextOverflow.ellipsis,
                                ),
                              ),
                            ],
                          ),
                        SizedBox(
                          height: heightOfScreen * 0.02,
                        ),
                        if (!empty)
                          Column(
                            children: [
                              Row(
                                textDirection: TextDirection.rtl,
                                children: [
                                  const Align(
                                    child: Text(
                                      'تا ددلاین :',
                                      textDirection: TextDirection.rtl,
                                      style: styleOfdescription,
                                      textAlign: TextAlign.justify,
                                    ),
                                  ),
                                  Align(
                                    child: Text(
                                      '  ${practices[index][4]} روز و ${practices[index][5]} ساعت و ${practices[index][6]} دقیقه',
                                      textDirection: TextDirection.rtl,
                                      style: styleOfdescription,
                                      textAlign: TextAlign.justify,
                                    ),
                                  ),
                                ],
                              ),
                              Row(
                                children: [
                                  Align(
                                    alignment: Alignment.centerLeft,
                                    child: IconButton(
                                      icon: Icon(
                                        Icons.cloud_upload,
                                        size: heightOfScreen * 0.035,
                                        color: const Color.fromARGB(
                                            255, 30, 255, 0),
                                      ),
                                      onPressed: () async {
                                        FilePickerResult? result =
                                            await FilePicker.platform
                                                .pickFiles();

                                        if (result != null) {
                                          File file =
                                              File(result.files.single.path!);
                                        } else {
                                          // User canceled the picker
                                        }
                                      },
                                    ),
                                  ),
                                  Align(
                                    alignment: Alignment.centerLeft,
                                    child: IconButton(
                                      icon: Icon(
                                        Icons.edit_document,
                                        size: heightOfScreen * 0.035,
                                        color: const Color.fromARGB(
                                            255, 188, 188, 188),
                                      ),
                                      onPressed: () {
                                        _showDialog(
                                            context,
                                            widthOfScreen,
                                            heightOfScreen,
                                            practices[index][7]);
                                      },
                                    ),
                                  ),
                                ],
                              ),
                              Row(
                                textDirection: TextDirection.rtl,
                                children: [
                                  const Text('زمان تخمینی :',
                                      style: styleOfdescription,
                                      textDirection: TextDirection.rtl),
                                  SizedBox(
                                    width: widthOfScreen * 0.02,
                                  ),
                                  Text(practices[index][1],
                                      style: styleOfTiltle,
                                      textDirection: TextDirection.rtl),
                                  SizedBox(
                                    width: widthOfScreen * 0.015,
                                  ),
                                  const Text('ساعت',
                                      style: styleOfdescription,
                                      textDirection: TextDirection.rtl),
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
          ),
        ),
      ],
    );
  }

  void _showDialog(BuildContext context, double widthOfScreen,
      double heightOfScreen, String assignmentId) {
    final TextEditingController descriptionController = TextEditingController();
    final TextEditingController hourController = TextEditingController();
    final TextEditingController minController = TextEditingController();

    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(25),
            side: const BorderSide(color: Colors.black, width: 3),
          ),
          backgroundColor: const Color.fromRGBO(25, 0, 126, 1),
          content: SingleChildScrollView(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
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
                  height: heightOfScreen * 0.3,
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
                    minLines: 6,
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
                      ' :مدت زمان تخمینی ',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.020),
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

                String description = descriptionController.text;

                // String day =
                //     HelperFunctions.padLeftWithZero(dayController.text);
                // String month =
                //     HelperFunctions.padLeftWithZero(monthController.text);
                // String year =
                //     HelperFunctions.padLeftWithZero(yearController.text);
                String hour =
                    HelperFunctions.padLeftWithZero(hourController.text);
                String minute =
                    HelperFunctions.padLeftWithZero(minController.text);
                setDescription(description, hour, minute, assignmentId);
                // Add your save logic here
                //print('Title: $title, Description: $description');
                Navigator.pushReplacementNamed(context, Tamrina.routeName);
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

  Future<String> setDescription(String description, String hour, String minute,
      String assignmentId) async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write(
            'setAssignment//${UserData.studentCode}//$assignmentId//$description//$hour:$minute\u0000');
        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseUpload = utf8.decode(socketResponse);
            });
            completer.complete(responseUpload);
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

  void _selectDate(
      BuildContext context, double widthOfScreen, double heightOfScreen) {
    final TextEditingController dayController = TextEditingController();
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
          content: SingleChildScrollView(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
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
                setState(() {
                  String day =
                      HelperFunctions.padLeftWithZero(dayController.text);
                  String month =
                      HelperFunctions.padLeftWithZero(monthController.text);
                  String year =
                      HelperFunctions.padLeftWithZero(yearController.text);
                  UserData.selectedDate =
                      HelperFunctions.formatDate(day, month, year);
                  print('+***+**********${UserData.selectedDate}');
                  fetchData();
                });
                // fetchActivePrBySortOption(
                //     selectedSortOption, UserData.selectedDate!);
                // Add your save logic here
                //print('Title: $title, Description: $description');
                Navigator.of(context).pop();
              },
              child: const Text(
                'ثبت',
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

  Future<String> fetchActivePrBySortOption(
      String sortOption, String date) async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write(
            'assignmentToday//${UserData.studentCode}//$date//$selectedSortOption\u0000');

        serverSocket.flush();
        serverSocket.listen(
          (socketResponse) {
            setState(() {
              responseActivePr = utf8.decode(socketResponse);
            });
            completer.complete(responseActivePr);
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
  double heightOfSizedBox = 400;
  late double heightOfContainer;
  bool empty = false;
  @override
  void initState() {
    super.initState();
    loadData();
  }

  void loadData() async {
    String message = await fetchInActivePractice();
    print('*************************************$message');

    setState(() {
      expiredPractices = HelperFunctions.stringToListOfList(message.trim());
      isExpanded = List<bool>.generate(expiredPractices.length, (_) => false);
      heightOfSizedBox = HelperFunctions.calculateSizeOfSizedBox(
          expiredPractices.length, heightOfContainer);
      if (expiredPractices[0][0] == '404') {
        empty = true;
      } else {
        empty = false;
      }
    });
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
    heightOfContainer = heightOfScreen * 0.5;
    return SizedBox(
      height: heightOfScreen * 1.5,
      child: ListView.builder(
        itemCount: expiredPractices.length,
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
              padding: EdgeInsets.all(widthOfScreen * 0.05),
              decoration: BoxDecoration(
                gradient: const LinearGradient(
                  begin: Alignment(1.00, 0.07),
                  end: Alignment(-1, -0.07),
                  colors: [Color(0xFF00A1D3), Color(0xFF1523AF)],
                ),
                borderRadius: BorderRadius.circular(23),
                border: Border.all(color: Colors.black, width: 2.0),
              ),
              height: isExpanded[index]
                  ? heightOfScreen * 0.5
                  : heightOfScreen * 0.2,
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
                            : expiredPractices[index][0],
                        textDirection: TextDirection.rtl,
                        textAlign: TextAlign.justify,
                        style: styleOfTiltle,
                      ),
                    ),
                    if (isExpanded[index] && !empty)
                      Column(
                        children: [
                          SizedBox(
                            height: heightOfScreen * 0.01,
                          ),
                          Align(
                            alignment: Alignment.centerRight,
                            child: Text(
                              'توضیحات : ${expiredPractices[index][2]}',
                              textDirection: TextDirection.rtl,
                              style: styleOfdescription,
                              textAlign: TextAlign.start,
                              maxLines: 4,
                              overflow: TextOverflow.ellipsis,
                            ),
                          ),
                          SizedBox(
                            height: heightOfScreen * 0.02,
                          ),
                          Align(
                            alignment: Alignment.centerRight,
                            child: Text(
                              'توضیحات دانشجو : ${expiredPractices[index][3]}',
                              textDirection: TextDirection.rtl,
                              style: styleOfdescription,
                              textAlign: TextAlign.start,
                              maxLines: 4,
                              overflow: TextOverflow.ellipsis,
                            ),
                          ),
                        ],
                      ),
                    SizedBox(
                      height: heightOfScreen * 0.02,
                    ),
                    // Column(
                    //   children: [
                    //     Row(
                    //       textDirection: TextDirection.rtl,
                    //       children: [
                    //         const Align(
                    //           child: Text(
                    //             'تا ددلاین :',
                    //             textDirection: TextDirection.rtl,
                    //             style: styleOfdescription,
                    //             textAlign: TextAlign.justify,
                    //           ),
                    //         ),
                    //         Align(
                    //           child: Text(
                    //             '  ${expiredPractices[index][4]} روز و ${expiredPractices[index][5]} ساعت و ${expiredPractices[index][6]} دقیقه',
                    //             textDirection: TextDirection.rtl,
                    //             style: styleOfdescription,
                    //             textAlign: TextAlign.justify,
                    //           ),
                    //         ),
                    //       ],
                    //     ),
                    //   ],
                    // ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
    );
  }

  Future<String> fetchInActivePractice() async {
    final completer = Completer<String>();

    await Socket.connect(
            ServerConnectionInfo.ipAddress, ServerConnectionInfo.port)
        .then(
      (serverSocket) {
        serverSocket.write('doneAssignment//${UserData.studentCode}\u0000');
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
    print(
        '*************************************${completer.future.toString()}');

    return completer.future;
  }
}
