import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class BodyOfKlasa extends StatefulWidget {
  const BodyOfKlasa({super.key});

  @override
  State<BodyOfKlasa> createState() => _BodyOfKlasaState();
}

class _BodyOfKlasaState extends State<BodyOfKlasa> {
  bool showTable = false;
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
              padding: EdgeInsets.only(top: heightOfScreen * 0.05),
            ),
          ),
          Positioned(
            top: heightOfScreen * 0.06,
            right: widthOfScreen * 0.04,
            child: InkWell(
              child: Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(100),
                  border: Border.all(),
                  color: Colors.green,
                ),
                width: widthOfScreen * 0.18,
                height: heightOfScreen * 0.08,
                alignment: Alignment.center,
                child: Center(
                  child: Icon(
                    Icons.add,
                    size: heightOfScreen * 0.05,
                    color: Colors.white,
                    shadows: const <Shadow>[
                      Shadow(
                        color: Colors.black,
                        offset: Offset(1.5, 1.5),
                      ),
                      Shadow(
                        color: Colors.black,
                        offset: Offset(-1.5, 1.5),
                      ),
                      Shadow(
                        color: Colors.black,
                        offset: Offset(-1.5, -1.5),
                      ),
                      Shadow(
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
          ),
          Positioned(
            top: heightOfScreen * 0.06,
            left: widthOfScreen * 0.04,
            child: InkWell(
              child: Container(
                decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(100),
                  border: Border.all(),
                  color: Colors.green,
                ),
                width: widthOfScreen * 0.18,
                height: heightOfScreen * 0.08,
                alignment: Alignment.center,
                child: Center(
                  child: Icon(
                    Icons.paste_outlined,
                    size: heightOfScreen * 0.05,
                    color: Colors.white,
                    shadows: const <Shadow>[
                      Shadow(
                        color: Colors.black,
                        offset: Offset(1.5, 1.5),
                      ),
                      Shadow(
                        color: Colors.black,
                        offset: Offset(-1.5, 1.5),
                      ),
                      Shadow(
                        color: Colors.black,
                        offset: Offset(-1.5, -1.5),
                      ),
                      Shadow(
                        color: Colors.black,
                        offset: Offset(1.5, -1.5),
                      ),
                    ],
                  ),
                ),
              ),
              onTap: () {
                setState(() {
                  showTable = !showTable;
                });
              },
            ),
          ),
          Positioned(
            top: heightOfScreen * 0.16,
            right: widthOfScreen * 0.05,
            child: (showTable) ? const Table() : Container(),
          )
        ],
      ),
    );
  }

  void _showDialog(
      BuildContext context, double widthOfScreen, double heightOfScreen) {
    final TextEditingController titleController = TextEditingController();
    final TextEditingController descriptionController = TextEditingController();
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
          // title: Align(
          //   alignment: Alignment.centerRight,
          //   child: Text(
          //     'کد کلاس مورد نظر خودرا وارد کنید',
          //     style: TextStyle(
          //       color: Colors.white,
          //       fontSize: heightOfScreen * 0.020,
          //     ),
          //     textDirection: TextDirection.rtl,
          //   ),
          // ),
          content: SingleChildScrollView(
            child: Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Row(
                  textDirection: TextDirection.rtl,
                  children: [
                    SizedBox(
                      width: widthOfScreen * 0.015,
                    ),
                    Text(
                      'کد کلاس مورد نظر خودرا وارد کنید',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.020),
                    ),
                  ],
                ),
                SizedBox(
                  height: heightOfScreen * 0.025,
                ),
                Align(
                  alignment: Alignment.center,
                  child: SizedBox(
                    height: heightOfScreen * 0.05,
                    width: widthOfScreen * 0.5,
                    child: TextField(
                      textAlign: TextAlign.center,
                      keyboardType: TextInputType.number,
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
                  height: heightOfScreen * 0.02,
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
                // Add your save logic here
                //print('Title: $title, Description: $description');
                Navigator.of(context).pop();
              },
              child: const Text(
                'ثبت نام',
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
}

class Table extends StatelessWidget {
  const Table({super.key});

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return Container(
      width: widthOfScreen * 0.90,
      height: heightOfScreen * 0.5,
      decoration: ShapeDecoration(
        gradient: const RadialGradient(
          center: Alignment(1, 0.20),
          radius: 0,
          colors: [Color(0xFF0078D9), Color(0xFF0050C1), Color(0xFF311B92)],
        ),
        shape: RoundedRectangleBorder(
          side: const BorderSide(width: 1),
          borderRadius: BorderRadius.circular(29),
        ),
        shadows: const [
          BoxShadow(
            color: Color(0x3F000000),
            blurRadius: 4,
            offset: Offset(0, 4),
            spreadRadius: 0,
          )
        ],
      ),
      child: Stack(children: [
        Positioned(
          left: widthOfScreen * 0.2,
          top: heightOfScreen * 0.015,
          child: Text(
            'برنامه هفتگی شما',
            style: TextStyle(
                fontSize: heightOfScreen * 0.035, color: Colors.white),
          ),
        ),
        Positioned(
          top: heightOfScreen * 0.08,
          left: widthOfScreen * 0.05,
          child: Container(
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(25),
              color: Colors.white,
            ),
            width: widthOfScreen * 0.8,
            height: heightOfScreen * 0.4,
            child: Center(
              child: DataTable(
                columns: const [
                  DataColumn(label: Text('روز هفته')),
                  DataColumn(label: Text('درس')),
                  DataColumn(label: Text('زمان')),
                ],
                rows: const [
                  DataRow(
                    cells: [
                      DataCell(Text('شنبه')),
                      DataCell(Text('ریاضی')),
                      DataCell(Text('08:00 - 10:00')),
                    ],
                  ),
                  DataRow(
                    cells: [
                      DataCell(Text('یکشنبه')),
                      DataCell(Text('فیزیک')),
                      DataCell(Text('10:00 - 12:00')),
                    ],
                  ),
                  DataRow(
                    cells: [
                      DataCell(Text('شنبه')),
                      DataCell(Text('ریاضی')),
                      DataCell(Text('08:00 - 10:00')),
                    ],
                  ),
                  DataRow(
                    cells: [
                      DataCell(Text('یکشنبه')),
                      DataCell(Text('فیزیک')),
                      DataCell(Text('10:00 - 12:00')),
                    ],
                  ),
                  DataRow(
                    cells: [
                      DataCell(Text('شنبه')),
                      DataCell(Text('ریاضی')),
                      DataCell(Text('08:00 - 10:00')),
                    ],
                  ),

                  // ردیف‌های بیشتری اضافه کنید
                ],
              ),
            ),
          ),
        ),
      ]),
    );
  }
}
