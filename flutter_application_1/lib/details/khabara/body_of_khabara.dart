import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:url_launcher/url_launcher.dart';
import '../../services/notification.dart';

class BodyOfKhabara extends StatefulWidget {
  const BodyOfKhabara({super.key});

  @override
  State<BodyOfKhabara> createState() => _BodyOfKhabaraState();
}

class _BodyOfKhabaraState extends State<BodyOfKhabara> {
  Widget selectedItem = const ForYou();
  int selected = 1;
  // late NotificationService notificationService;

  // @override
  // void initState() {
  //   super.initState();
  //   notificationService = NotificationService();
  //   notificationService.initNotification();
  // }

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;

    return ListView(
      children: [
        Stack(
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
                padding: EdgeInsets.only(top: heightOfScreen * 0.05),
                child: selectedItem,
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
                      width: widthOfScreen * 0.21,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15),
                        color: (selected == 1)
                            ? const Color(0xFFE4014E)
                            : Colors.grey,
                        border: Border.all(),
                      ),
                      child: Text(
                        'برای شما',
                        style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.02,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                    ),
                    onTap: () {
                      NotificationService().showNotification(
                        title: 'test',
                        body: 'test',
                      );
                      setState(() {
                        if (selected != 1) {
                          selected = 1;
                          selectedItem = const ForYou();
                        }
                      });
                    },
                  ),
                  SizedBox(width: widthOfScreen * 0.01),
                  InkWell(
                    child: Container(
                      alignment: Alignment.center,
                      height: heightOfScreen * 0.05,
                      width: widthOfScreen * 0.21,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15),
                        color: (selected == 2)
                            ? const Color(0xFFE4014E)
                            : Colors.grey,
                        border: Border.all(),
                      ),
                      child: Text(
                        'رویدادها',
                        style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.02,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                    ),
                    onTap: () {
                      setState(() {
                        if (selected != 2) {
                          selected = 2;
                          selectedItem = const Event();
                        }
                      });
                    },
                  ),
                  SizedBox(width: widthOfScreen * 0.01),
                  InkWell(
                    child: Container(
                      alignment: Alignment.center,
                      height: heightOfScreen * 0.05,
                      width: widthOfScreen * 0.21,
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(15),
                        color: (selected == 3)
                            ? const Color(0xFFE4014E)
                            : Colors.grey,
                        border: Border.all(),
                      ),
                      child: Text(
                        'تولدها',
                        style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.02,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                    ),
                    onTap: () {
                      setState(() {
                        if (selected != 3) {
                          selected = 3;
                          selectedItem = Birthdays();
                        }
                      });
                    },
                  ),
                ],
              ),
            ),
          ],
        ),
      ],
    );
  }
}

class Birthdays extends StatelessWidget {
  Birthdays({super.key});

  final List<String> birthdayNames = <String>[
    'سید امیرحسین اشرفیان',
    'سید حمیدرضا میرزاپور',
    'محمد تقی زاده',
    'علی نصرالله پور'
  ];

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return SizedBox(
      height: heightOfScreen,
      child: ListView.builder(
        itemCount: birthdayNames.length,
        itemBuilder: (BuildContext context, int index) {
          return Column(
            children: <Widget>[
              SizedBox(height: heightOfScreen * 0.01),
              Container(
                width: widthOfScreen * 0.7,
                height: heightOfScreen * 0.07,
                decoration: ShapeDecoration(
                  gradient: const LinearGradient(
                    begin: Alignment(1.00, 0.07),
                    end: Alignment(-1, -0.07),
                    colors: [
                      Color(0xFF3415AF),
                      Color(0xFF7A00B3),
                      Color(0xFFD7009B),
                    ],
                  ),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15),
                  ),
                ),
                child: Center(
                  child: Text(
                    birthdayNames[index],
                    textAlign: TextAlign.center,
                    style: const TextStyle(
                      color: Colors.white,
                      fontSize: 24,
                      fontFamily: 'Vazir',
                      fontWeight: FontWeight.w900,
                    ),
                  ),
                ),
              ),
              SizedBox(height: heightOfScreen * 0.01),
            ],
          );
        },
      ),
    );
  }
}

class ForYou extends StatefulWidget {
  const ForYou({super.key});

  @override
  State<ForYou> createState() => _ForYouState();
}

class _ForYouState extends State<ForYou> {
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

  final List<String> warnAssignment = <String>[
    'تمرین شماره یک مدار',
    'تمرین شماره دو آز',
    'پروژه برنامه نویسی پیشرفته',
    'تمرین شماره پنج دیفرانسیل'
  ];
  final List<String> changedDeadline = <String>[
    'تمرین شماره یک مدار',
    'تمرین شماره دو آز',
    'پروژه برنامه نویسی پیشرفته',
    'تمرین شماره پنج دیفرانسیل'
  ];

  List<bool> isExpanded = List<bool>.generate(4, (_) => false);
  List<bool> isExpanded2 = List<bool>.generate(4, (_) => false);
  @override
  Widget build(BuildContext context) {
    // ignore: unused_local_variable
    final double widthOfScreen = MediaQuery.of(context).size.width;
    // ignore: unused_local_variable
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return ListView(
      shrinkWrap: true,
      physics: const NeverScrollableScrollPhysics(),
      children: [
        ...warnAssignment.map((assignment) {
          int index = warnAssignment.indexOf(assignment);
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
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 146, 0, 132),
                          const Color.fromARGB(255, 189, 3, 3),
                        ]
                      : [
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 129, 30, 138),
                        ],
                ),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(15),
                ),
              ),
              child: Column(
                children: [
                  Row(
                    textDirection: TextDirection.rtl,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(
                        alignment: Alignment.center,
                        child: Text(
                          assignment,
                          style: styleOfTiltle,
                        ),
                      ),
                    ],
                  ),
                  if (isExpanded[index])
                    const Column(
                      children: [
                        Text(
                          '.به اطلاع میرساند برای تحویل تمرین شماره یک درس برنامه نویسی پیشرفته ( AP ) استاد وحیدی، تنها چند ساعت دیگر فرصت باقی است',
                          style: styleOfdescription,
                        )
                      ],
                    ),
                ],
              ),
            ),
          );
        }),
        ...changedDeadline.map((assignment) {
          int index = changedDeadline.indexOf(assignment);
          return GestureDetector(
            onTap: () {
              setState(() {
                isExpanded2[index] = !isExpanded2[index];
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
                  colors: isExpanded2[index]
                      ? [
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 146, 0, 132),
                          const Color.fromARGB(255, 189, 3, 3),
                        ]
                      : [
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 129, 30, 138),
                          const Color.fromARGB(255, 129, 30, 138),
                        ],
                ),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(15),
                ),
              ),
              child: Column(
                children: [
                  Row(
                    textDirection: TextDirection.rtl,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(
                        alignment: Alignment.center,
                        child: Text(
                          assignment,
                          style: styleOfTiltle,
                        ),
                      ),
                    ],
                  ),
                  if (isExpanded2[index])
                    const Column(
                      children: [
                        Text(
                          "this is description of this tile for testing for each tile I hope that it will be great to test that out to see what would happen at the end of this test",
                          style: styleOfdescription,
                        )
                      ],
                    ),
                ],
              ),
            ),
          );
        }),
      ],
    );
  }
}

class Event extends StatefulWidget {
  const Event({super.key});

  @override
  State<Event> createState() => _EventState();
}

class _EventState extends State<Event> {
  Map<String, String> events = {
    'دانشجویدانشجوییی  انتخابات انتخابات انتخابات انتخابات انتخابات انتخابات انتخاباتانتخابات انجمن های دانشجویی':
        'https://www.sbu.ac.ir/fa/web/news/w/international-4?redirect=%2F',
    'ایران قدرتمندانه مسیر هسته‌ای را دنبال می‌کند':
        'https://www.sbu.ac.ir/fa/web/news/w/international-4?redirect=%2F',
    'انتخابات انجمن های هیئت علمی':
        'https://www.sbu.ac.ir/fa/web/news/w/international-4?redirect=%2F',
    'انتخابات انجمن های هیئت غیر علمی':
        'https://www.sbu.ac.ir/fa/web/news/w/international-4?redirect=%2F',
  };

  List<bool> expanded = List<bool>.generate(
      4, (_) => false); // for storing the expansion state of each item

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.height;
    return SizedBox(
      height: heightOfScreen,
      child: ListView.builder(
        itemCount: events.length,
        itemBuilder: (BuildContext context, int index) {
          String title = events.keys.elementAt(index);
          String link = events[title]!;
          bool isExpanded = expanded[index];
          return Column(
            children: <Widget>[
              SizedBox(height: heightOfScreen * 0.01),
              GestureDetector(
                onTap: () {
                  setState(() {
                    expanded[index] = !expanded[index];
                  });
                },
                child: AnimatedContainer(
                  duration: const Duration(milliseconds: 300),
                  padding: EdgeInsets.only(
                      left: widthOfScreen * 0.03,
                      right: widthOfScreen * 0.03,
                      top: widthOfScreen * 0.03),
                  width: widthOfScreen * 0.75,
                  height: isExpanded
                      ? heightOfScreen * 0.15
                      : heightOfScreen * 0.12,
                  decoration: BoxDecoration(
                    border: Border.all(
                      color: Colors.black,
                      width: 2.0,
                    ),
                    borderRadius: BorderRadius.circular(25),
                    gradient: LinearGradient(
                      begin: const Alignment(1.00, 0.07),
                      end: const Alignment(-1, -0.07),
                      colors: (expanded[index])
                          ? [
                              const Color(0xFF008805),
                              const Color(0xFF00BD07),
                            ]
                          : [const Color(0xFF00C7D3), const Color(0xFF1523AF)],
                    ),
                  ),
                  child: Column(
                    children: [
                      Align(
                        alignment: Alignment.topCenter,
                        child: SizedBox(
                          child: Text(
                            title,
                            textAlign: TextAlign.justify,
                            maxLines: 2,
                            overflow: TextOverflow.ellipsis,
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: heightOfScreen * 0.015,
                              fontFamily: 'vazir',
                              fontWeight: FontWeight.w900,
                            ),
                            textDirection: TextDirection.rtl,
                          ),
                        ),
                      ),
                      if (isExpanded)
                        Align(
                          alignment: Alignment.centerLeft,
                          child: IconButton(
                            onPressed: () async {
                              final linkToOpen = Uri.parse(link);
                              await launchUrl(linkToOpen);
                            },
                            icon: Icon(
                              Icons.search,
                              size: heightOfScreen * 0.03,
                              color: Colors.white,
                            ),
                          ),
                        ),
                    ],
                  ),
                ),
              ),
              SizedBox(height: heightOfScreen * 0.01),
            ],
          );
        },
      ),
    );
  }
}
