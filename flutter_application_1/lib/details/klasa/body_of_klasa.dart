import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

import '../classes/student.dart';

class BodyOfKlasa extends StatefulWidget {
  const BodyOfKlasa({super.key});

  @override
  State<BodyOfKlasa> createState() => _BodyOfKlasaState();
}

class _BodyOfKlasaState extends State<BodyOfKlasa> {
  @override
  void initState() {
    // String response = fetchSaraData();
    super.initState();
  }

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
              child: const Classes(),
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
    final TextEditingController courseIdController = TextEditingController();

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
                      controller: courseIdController,
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
                //String courseIs = courseIdController.text;
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
  static const tableTextStyle = TextStyle(
    color: Colors.black,
    fontSize: 10,
    fontFamily: 'vazir',
    fontWeight: FontWeight.bold,
  );
  static const titleTableTextStyle = TextStyle(
    color: Colors.black,
    fontSize: 12,
    fontFamily: 'vazir',
    fontWeight: FontWeight.bold,
  );

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
                color: const Color.fromARGB(206, 255, 145, 0)),
            width: widthOfScreen * 0.8,
            height: heightOfScreen * 0.4,
            child: Center(
              child: SingleChildScrollView(
                child: DataTable(
                  // border: TableBorder(
                  //     horizontalInside: BorderSide(
                  //   color: Colors.white,
                  //   width: heightOfScreen * 0.001,
                  // )),
                  headingRowHeight: heightOfScreen * 0.08,
                  columns: const [
                    DataColumn(
                      label: Text(
                        'روز هفته',
                        style: titleTableTextStyle,
                      ),
                    ),
                    DataColumn(
                      label: Text(
                        'درس',
                        style: titleTableTextStyle,
                      ),
                    ),
                    DataColumn(
                      label: Text(
                        'زمان',
                        style: titleTableTextStyle,
                      ),
                    ),
                  ],
                  rows: const [
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'شنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'ریاضی',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '08:00 - 10:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'یکشنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'فیزیک',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '10:00 - 12:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'شنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'ریاضی',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '08:00 - 10:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'یکشنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'فیزیک',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '10:00 - 12:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'شنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'ریاضی',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '08:00 - 10:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'شنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'ریاضی',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '08:00 - 10:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),
                    DataRow(
                      cells: [
                        DataCell(Text(
                          'شنبه',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          'ریاضی',
                          style: tableTextStyle,
                        )),
                        DataCell(Text(
                          '08:00 - 10:00',
                          style: tableTextStyle,
                        )),
                      ],
                    ),

                    // ردیف‌های بیشتری اضافه کنید
                  ],
                ),
              ),
            ),
          ),
        ),
      ]),
    );
  }
}

class Classes extends StatefulWidget {
  const Classes({super.key});

  @override
  State<Classes> createState() => _ClassesState();
}

class _ClassesState extends State<Classes> {
  List<List<String>> classes = [
    ['فیزیک', 'زیبایی', '3', '1403/04/14', '19', '5', 'شنبه', 'courseId'],
    ['ریاضی', 'زیبایی', '3', '1403/04/14', '19', '3', 'شنبه', 'courseId'],
    ['مدار', 'زیبایی', '3', '1403/04/14', '19', '8', 'شنبه', 'courseId'],
    ['دیفرانسیل', 'زیبایی', '3', '1403/04/14', '19', '5', 'شنبه', 'courseId'],
    [
      'برنامه نویسی پیشرفته',
      'زیبایی',
      '3',
      '1403/04/14',
      '19.00',
      '9',
      'شنبه',
      'courseId'
    ],
    [
      'برنامه نویسی پسرفته',
      'زیبایی',
      '3',
      '1403/04/14',
      '19',
      '11',
      'شنبه',
      'courseId'
    ],
    ['ورزش', 'زیبایی', '3', '1403/04/14', '19', '16', 'شنبه', 'courseId'],
  ];

  List<bool> isExpanded = List<bool>.generate(7, (_) => false);

  static const descriptionStyle = TextStyle(
      fontSize: 16,
      fontFamily: 'vazir',
      color: Colors.white,
      fontWeight: FontWeight.w600);
  static const titleStyle = TextStyle(
      fontSize: 20,
      fontFamily: 'vazir',
      color: Color.fromARGB(255, 255, 242, 0),
      fontWeight: FontWeight.w600);

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return SizedBox(
      height: heightOfScreen,
      child: ListView.builder(
        itemCount: classes.length,
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
              padding: EdgeInsets.only(
                  right: widthOfScreen * 0.05, top: widthOfScreen * 0.05),
              decoration: ShapeDecoration(
                gradient: LinearGradient(
                  begin: const Alignment(1.00, 0.07),
                  end: const Alignment(-1, -0.07),
                  colors: isExpanded[index]
                      ? [
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 132, 126, 197),
                        ]
                      : [
                          const Color(0xFF1523AF),
                          const Color(0xFF00A1D3),
                        ],
                ),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(23),
                ),
              ),
              height: isExpanded[index]
                  ? heightOfScreen * 0.25
                  : heightOfScreen * 0.22,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  AnimatedAlign(
                    duration: const Duration(milliseconds: 350),
                    alignment: isExpanded[index]
                        ? Alignment.center
                        : Alignment.centerRight,
                    child: Text(
                      classes[index][0],
                      textDirection: TextDirection.rtl,
                      textAlign: TextAlign.justify,
                      style: titleStyle,
                    ),
                  ),
                  Align(
                    alignment:
                        Alignment(widthOfScreen * 0.0024, heightOfScreen * 0.2),
                    child: Text(
                      'نام استاد : ${classes[index][1]}',
                      style: descriptionStyle,
                    ),
                  ),
                  Row(
                    textDirection: TextDirection.rtl,
                    children: [
                      Align(
                        alignment: Alignment(
                            widthOfScreen * 0.0024, heightOfScreen * 0.2),
                        child: Text(
                          'واحد درسی : ${classes[index][2]}',
                          style: descriptionStyle,
                        ),
                      ),
                      SizedBox(
                        width: widthOfScreen * 0.083,
                      ),
                      Align(
                        alignment: Alignment(
                            widthOfScreen * 0.0024, heightOfScreen * 0.2),
                        child: Text(
                          'تاریخ امتحان : ${classes[index][3]}',
                          style: descriptionStyle,
                        ),
                      ),
                    ],
                  ),
                  Row(
                    textDirection: TextDirection.rtl,
                    children: [
                      Align(
                        alignment: Alignment(
                            widthOfScreen * 0.0024, heightOfScreen * 0.2),
                        child: Text(
                          'نمره اخذ شده : ${classes[index][4]}',
                          style: descriptionStyle,
                        ),
                      ),
                      SizedBox(
                        width: widthOfScreen * 0.02,
                      ),
                      Align(
                        alignment: Alignment(
                            widthOfScreen * 0.0024, heightOfScreen * 0.2),
                        child: Text(
                          'تکالیف فعال  : ${classes[index][5]}',
                          style: descriptionStyle,
                        ),
                      ),
                    ],
                  ),
                  Align(
                    alignment:
                        Alignment(widthOfScreen * 0.0024, heightOfScreen * 0.2),
                    child: Text(
                      classes[index][6],
                      style: descriptionStyle,
                    ),
                  ),
                  if (isExpanded[index])
                    IconButton(
                      onPressed: () {
                        showDialog(
                            context: context,
                            builder: (context) {
                              return const DeleteClassDialog();
                            });
                      },
                      icon: Icon(
                        Icons.delete,
                        size: widthOfScreen * 0.08,
                        color: const Color.fromARGB(255, 255, 0, 0),
                      ),
                    ),
                ],
              ),
            ),
          );
        },
      ),
    );
  }
}

class DeleteClassDialog extends StatelessWidget {
  const DeleteClassDialog({super.key});

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return Dialog(
      child: Container(
        height: heightOfScreen * 0.2,
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
        child: Column(
          children: [
            SizedBox(
              height: heightOfScreen * 0.02,
            ),
            Text(
              'آیا از حذف این کلاس اطمینان دارید؟',
              style: TextStyle(
                  color: Colors.white,
                  fontWeight: FontWeight.bold,
                  fontSize: heightOfScreen * 0.022),
            ),
            SizedBox(
              height: heightOfScreen * 0.05,
            ),
            Row(
              textDirection: TextDirection.rtl,
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                ElevatedButton(
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                  style: TextButton.styleFrom(
                    backgroundColor: const Color(0xFFE4014E),
                  ),
                  child: Text(
                    'حذف',
                    style: TextStyle(
                      color: Colors.white,
                      fontFamily: 'vazir',
                      fontWeight: FontWeight.bold,
                      fontSize: heightOfScreen * 0.018,
                    ),
                  ),
                ),
                SizedBox(
                  width: widthOfScreen * 0.1,
                ),
                ElevatedButton(
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                  style: TextButton.styleFrom(
                    backgroundColor: const Color(0xFF2DD634),
                  ),
                  child: Text(
                    'بازگشت',
                    style: TextStyle(
                      color: Colors.white,
                      fontFamily: 'vazir',
                      fontWeight: FontWeight.bold,
                      fontSize: heightOfScreen * 0.018,
                    ),
                  ),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
