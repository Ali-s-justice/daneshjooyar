import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class BodyOfKara extends StatefulWidget {
  const BodyOfKara({super.key});

  @override
  State<BodyOfKara> createState() => _BodyOfKaraState();
}

class _BodyOfKaraState extends State<BodyOfKara> {
  int selected = 1;
  Widget selectedWidget = const Done();

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
                      selectedWidget = const Done();
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
                      selectedWidget = const Dont();
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
                  height: heightOfScreen * 0.02,
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
                    minLines: 6,
                  ),
                ),
                SizedBox(
                  height: heightOfScreen * 0.02,
                ),
                Row(
                  textDirection: TextDirection.rtl,
                  children: [
                    SizedBox(
                      width: widthOfScreen * 0.020,
                    ),
                    Text(
                      ':تاریخ',
                      style: TextStyle(
                          color: Colors.white,
                          fontSize: heightOfScreen * 0.035),
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
                // Add your save logic here
                //print('Title: $title, Description: $description');
                Navigator.of(context).pop();
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
}

class Done extends StatefulWidget {
  const Done({super.key});

  @override
  State<Done> createState() => _DoneState();
}

class _DoneState extends State<Done> {
  final List<List<String>> listOfList = [
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
  ];
  List<bool> isExpanded = List<bool>.generate(7, (_) => false);

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
            decoration: ShapeDecoration(
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
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(23),
              ),
            ),
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
                      listOfList[index][0],
                      textDirection: TextDirection.rtl,
                      textAlign: TextAlign.justify,
                      style: styleOfTiltle,
                    ),
                  ),
                  const SizedBox(height: 10),
                  if (isExpanded[index])
                    Column(
                      children: [
                        Text(
                          listOfList[index][1],
                          textDirection: TextDirection.rtl,
                          style: styleOfdescription,
                          textAlign: TextAlign.justify,
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
                              width: widthOfScreen * 0.1,
                            ),
                            IconButton(
                              onPressed: () {},
                              icon: Icon(
                                Icons.done_outline_rounded,
                                size: widthOfScreen * 0.08,
                                color: const Color.fromARGB(255, 0, 255, 8),
                              ),
                            ),
                            IconButton(
                              onPressed: () {},
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
}

class Dont extends StatefulWidget {
  const Dont({super.key});

  @override
  State<Dont> createState() => _DontState();
}

class _DontState extends State<Dont> {
  final List<List<String>> listOfList = [
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
    [
      'حل تمرین فیزیک',
      'امروز باید همه تمرین های فیزیک رو حل کنم.\nتعداد سوال 20 تاست که فکر کنم به مدت 2 ساعت طول بکشه .\nفایل نهایی باید در کورس ور آپلود بشه',
      '2',
      '12',
      '40',
      '1'
    ],
  ];
  List<bool> isExpanded = List<bool>.generate(7, (_) => false);

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
            decoration: ShapeDecoration(
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
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(23),
              ),
            ),
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
                      listOfList[index][0],
                      textDirection: TextDirection.rtl,
                      textAlign: TextAlign.justify,
                      style: styleOfTiltle,
                    ),
                  ),
                  const SizedBox(height: 10),
                  if (isExpanded[index])
                    Column(
                      children: [
                        Text(
                          listOfList[index][1],
                          textDirection: TextDirection.rtl,
                          style: styleOfdescription,
                          textAlign: TextAlign.justify,
                        ),
                        SizedBox(
                          height: heightOfScreen * 0.03,
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
                              width: widthOfScreen * 0.1,
                            ),
                            IconButton(
                              onPressed: () {},
                              icon: Icon(
                                Icons.done_outline_rounded,
                                size: widthOfScreen * 0.08,
                                color: const Color.fromARGB(255, 0, 255, 8),
                              ),
                            ),
                            IconButton(
                              onPressed: () {},
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
}
