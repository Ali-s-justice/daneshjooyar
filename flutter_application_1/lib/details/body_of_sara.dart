import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class BodyOfSara extends StatefulWidget {
  const BodyOfSara({super.key});

  @override
  State<BodyOfSara> createState() => _BodyOfSaraState();
}

class _BodyOfSaraState extends State<BodyOfSara> {
  static const styleOfContainerText = TextStyle(
    fontFamily: 'vazir',
    fontSize: 11,
    color: Colors.white,
    fontWeight: FontWeight.w900,
  );
  static const styleOfContainergrade = TextStyle(
    fontFamily: 'vazir',
    fontSize: 15,
    color: Colors.white,
    fontWeight: FontWeight.w900,
  );

  String dar = 'در';
  String bestScore = '20.00';
  String bestCourse = 'فارسی';
  String worthScore = '15.25';
  String worthCourse = 'ریاضی';
  String examNum = "3";
  String assignNum = "12";
  String doneAssignmentNum = "9";
  String dayOfEnd = "23";
  String houreOfEnd = "12";
  String minOfEnd = "20";

  final Map<String, String> topics = {
    'حل تمرین فیزیک':
        ' اگر می‌خواهید خواننده متن فارسی‌تان را کنار نگذارد و آن را تا انتها بخواهند، از ویرایش و بازخوانی متن غافل نشوید. سرویس ویرایش و بازخوانی متون فارسی شبکه مترجمین ایران این‌جا است تا متون فارسی‌تان را خوانش‌پذیر کند.',
    'حل تمرین ریاضی':
        ' اگر می‌خواهید خواننده متن فارسی‌تان را کنار نگذارد و آن را تا انتها بخواهند، از ویرایش و بازخوانی متن غافل نشوید. سرویس ویرایش و بازخوانی متون فارسی شبکه مترجمین ایران این‌جا است تا متون فارسی‌تان را خوانش‌پذیر کند.',
    'حل تمرین گسسته':
        ' اگر می‌خواهید خواننده متن فارسی‌تان را کنار نگذارد و آن را تا انتها بخواهند، از ویرایش و بازخوانی متن غافل نشوید. سرویس ویرایش و بازخوانی متون فارسی شبکه مترجمین ایران این‌جا است تا متون فارسی‌تان را خوانش‌پذیر کند.',
  };

  @override
  Widget build(BuildContext context) {
    final double widthOfScreen = MediaQuery.of(context).size.width;
    final double heightOfScreen = MediaQuery.of(context).size.width;
    final double heightOfContainer = heightOfScreen * 0.27;
    final double widthOfContainer = widthOfScreen * 0.88;
    final double titleContainerFont = widthOfScreen * 0.12;
    final double titleFromeTop3 = heightOfContainer * 0.15;
    final double titleFromeLeft3 = widthOfContainer * 0.69;
    final double contentFromeTop3 = heightOfContainer * 0.45;
    final double contentFromeLeft3 = widthOfContainer * 0.07;
    final double titleFromeTop2 = heightOfContainer * 0.15;
    final double titleFromeLeft2 = widthOfContainer * 0.6;
    final double contentFromeTop2 = heightOfContainer * 0.45;
    final double contentFromeLeft2 = widthOfContainer * 0.12;
    final double titleFromeTop1 = heightOfContainer * 0.15;
    final double titleFromeLeft1 = widthOfContainer * 0.6;
    final double contentFromeTop1 = heightOfContainer * 0.45;
    final double contentFromeLeft1 = widthOfContainer * 0.12;
    final double titleFromeTop4 = heightOfContainer * 0.15;
    final double titleFromeLeft4 = widthOfContainer * 0.69;
    final double contentFromeTop4 = heightOfContainer * 0.45;
    final double contentFromeLeft4 = widthOfContainer * 0.1;
    final double heightOfassignmentContainer = heightOfContainer * 1.8;

    return SingleChildScrollView(
      child: Column(
        children: [
          SizedBox(
            height: heightOfScreen * 0.040,
          ),
          SizedBox(
            height: heightOfContainer,
            child: ListView(
              scrollDirection: Axis.horizontal,
              children: [
                InkWell(
                  child: containerItem(
                      'کارا',
                      '!اینجا میتونی ببینی چه کارایی داری',
                      const Color(0xFF1523AF),
                      const Color(0xFF00A1D3),
                      widthOfContainer,
                      heightOfContainer,
                      titleFromeLeft4,
                      titleFromeTop4,
                      titleContainerFont,
                      contentFromeLeft4,
                      contentFromeTop4),
                  onTap: () {},
                ),
                betweenItem(widthOfScreen),
                InkWell(
                  child: containerItem(
                      'خبرا',
                      '!اینجا میتونی اخبار مهم رو دنبال کنی',
                      const Color(0xFF1523AF),
                      const Color(0xFF6C00D8),
                      widthOfContainer,
                      heightOfContainer,
                      titleFromeLeft3,
                      titleFromeTop3,
                      titleContainerFont,
                      contentFromeLeft3,
                      contentFromeTop3),
                  onTap: () {},
                ),
                betweenItem(widthOfScreen),
                InkWell(
                  child: containerItem(
                      'کلاسا',
                      '!اینجا میتونی کلاساتو ببینی',
                      const Color(0xFF1523AF),
                      const Color(0xFF00A1D3),
                      widthOfContainer,
                      heightOfContainer,
                      titleFromeLeft2,
                      titleFromeTop2,
                      titleContainerFont,
                      contentFromeLeft2,
                      contentFromeTop2),
                  onTap: () {},
                ),
                betweenItem(widthOfScreen),
                InkWell(
                  child: containerItem(
                      'تمرینا',
                      '!اینجا میتونی تمریناتو ببینی',
                      const Color(0xFF1523AF),
                      const Color(0xFF6C00D8),
                      widthOfContainer,
                      heightOfContainer,
                      titleFromeLeft1,
                      titleFromeTop1,
                      titleContainerFont,
                      contentFromeLeft1,
                      contentFromeTop1),
                  onTap: () {},
                ),
                betweenItem(widthOfScreen),
              ],
            ),
          ),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
          faseleyeAmoodi(heightOfScreen, widthOfScreen),
          SizedBox(
            height: heightOfContainer * 0.7,
            child: ListView(
              scrollDirection: Axis.horizontal,
              children: <Widget>[
                Row(
                  children: [
                    distanceEacRowFromRight(widthOfContainer),
                    Container(
                      width: 172,
                      height: 46,
                      decoration: ShapeDecoration(
                        gradient: const RadialGradient(
                          center: Alignment(0.0, 1.0), // مرکز گرادیان
                          radius: 1.5, // شعاع گرادیان
                          colors: [
                            Color(0xFF0078D9),
                            Color(0xFF0050C1),
                            Color(0xFF311B92)
                          ],
                          stops: [0.1, 0.5, 2.0], // توقف‌های گرادیان
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      alignment: Alignment.center,
                      child: Text(
                        '$worthScore $dar $worthCourse',
                        textDirection: TextDirection.rtl,
                        style: styleOfContainergrade,
                      ),
                    ),
                    betweenItem(widthOfScreen),
                    Container(
                      alignment: Alignment.center,
                      width: widthOfScreen * 0.3,
                      height: heightOfScreen * 0.12,
                      decoration: ShapeDecoration(
                        color: const Color(0xFFE4004D),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      child: const Text(
                        '(: بدترین نمره شما',
                        style: styleOfContainerText,
                      ),
                    ),
                    betweenItem(5 * widthOfScreen),
                    Container(
                      width: 172,
                      height: 46,
                      decoration: ShapeDecoration(
                        gradient: const RadialGradient(
                          center: Alignment(0.0, 1.0), // مرکز گرادیان
                          radius: 1.5, // شعاع گرادیان
                          colors: [
                            Color(0xFF0078D9),
                            Color(0xFF0050C1),
                            Color(0xFF311B92)
                          ],
                          stops: [0.1, 0.5, 2.0], // توقف‌های گرادیان
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      alignment: Alignment.center,
                      child: Text(
                        '$bestScore  $dar $bestCourse',
                        textDirection: TextDirection.rtl,
                        style: styleOfContainergrade,
                      ),
                    ),
                    betweenItem(widthOfScreen),
                    Container(
                      alignment: Alignment.center,
                      width: widthOfScreen * 0.3,
                      height: heightOfScreen * 0.12,
                      decoration: ShapeDecoration(
                        gradient: const LinearGradient(
                          begin: Alignment(0.00, -1.00),
                          end: Alignment(0, 1),
                          colors: [Color(0xFF9E00FF), Color(0xFF345099)],
                        ),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      child: const Text(
                        '(: بالاترین نمره شما',
                        style: styleOfContainerText,
                      ),
                    ),
                    distanceEacRowFromRight(widthOfContainer),
                  ],
                )
              ],
            ),
          ),
          faseleyeAmoodi(heightOfScreen, widthOfScreen),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
          SizedBox(
            height: heightOfContainer,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              textDirection: TextDirection.rtl,
              children: [
                distanceEacRowFromRight(widthOfScreen),
                Container(
                  alignment: Alignment.center,
                  width: widthOfScreen * 0.2,
                  height: heightOfScreen * 0.08,
                  decoration: ShapeDecoration(
                    color: const Color(0xFFE4004D),
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(12),
                    ),
                  ),
                  child: const Text(
                    ': پیشِرو',
                    style: TextStyle(
                      fontFamily: 'vazir',
                      fontSize: 20,
                      color: Colors.white,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                SizedBox(width: widthOfScreen * 0.06),
                leadingBoard(
                    examNum, 'امتحان', heightOfScreen, widthOfScreen, 1),
                SizedBox(width: widthOfScreen * 0.03),
                leadingBoard(
                    assignNum, 'تمرین', heightOfScreen, widthOfScreen, 2),
                SizedBox(width: widthOfScreen * 0.03),
                leadingBoard(doneAssignmentNum, 'انجام شده', heightOfScreen,
                    widthOfScreen, 3),
              ],
            ),
          ),
          faseleyeAmoodi(heightOfScreen, widthOfScreen),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
          SizedBox(
            height: heightOfContainer * 0.5,
            child: Row(
              textDirection: TextDirection.rtl,
              children: [
                distanceEacRowFromRight(widthOfScreen),
                Container(
                  width: widthOfContainer * 0.75,
                  height: heightOfScreen * 0.12,
                  alignment: Alignment.center,
                  decoration: ShapeDecoration(
                    color: const Color(0xFF6A02D8),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(9)),
                  ),
                  child: Text(
                    '$dayOfEnd روز و $houreOfEnd ساعت و $minOfEnd دقیقه',
                    textDirection: TextDirection.rtl,

                    // ignore: prefer_const_constructors
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 20,
                      fontFamily: 'vazir',
                      fontWeight: FontWeight.w900,
                      height: 0,
                    ),
                  ),
                ),
                betweenItem(widthOfScreen),
                Text(
                  '!تا پایان',
                  style: TextStyle(
                      fontFamily: 'pinar', fontSize: widthOfScreen * 0.07),
                ),
              ],
            ),
          ),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
          faseleyeAmoodi(heightOfScreen, widthOfScreen),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
          SizedBox(
            height: heightOfassignmentContainer * 1.5,
            child: Column(
              // crossAxisAlignment: CrossAxisAlignment.start,
              // textDirection: TextDirection.rtl,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  textDirection: TextDirection.rtl,
                  children: [
                    distanceEacRowFromRight(widthOfScreen),
                    Container(
                      alignment: Alignment.center,
                      width: widthOfScreen * 0.2,
                      height: heightOfScreen * 0.08,
                      decoration: ShapeDecoration(
                        color: const Color(0xFFE4004D),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                      ),
                      child: const Text(
                        ': امروز',
                        style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: 20,
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                  ],
                ),
                SizedBox(
                  height: widthOfScreen * 0.03,
                ),
                SizedBox(
                  height: heightOfassignmentContainer, // ارتفاع کانتینرها
                  child: ListView.builder(
                    reverse: true,
                    scrollDirection: Axis.horizontal,
                    itemCount: topics.length,
                    itemBuilder: (context, index) {
                      String key = topics.keys.elementAt(index);
                      String value = topics[key]!;
                      return Row(
                        mainAxisAlignment: MainAxisAlignment.end,
                        textDirection: TextDirection.rtl,
                        children: [
                          betweenItem(widthOfScreen),
                          Container(
                            width: widthOfContainer,
                            height: heightOfassignmentContainer,
                            decoration: ShapeDecoration(
                              gradient: LinearGradient(
                                begin: const Alignment(1.00, 0.07),
                                end: const Alignment(-1, -0.07),
                                colors: (index % 2 == 0)
                                    ? [
                                        const Color(0xFF1523AF),
                                        const Color(0xFF00A1D3),
                                      ]
                                    : [
                                        const Color(0xFF1523AF),
                                        const Color(0xFF6C00D8),
                                      ],
                              ),
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(23),
                              ),
                            ),
                            padding: EdgeInsets.only(
                                top: 0,
                                bottom: heightOfassignmentContainer * 0,
                                right: widthOfContainer * 0.08,
                                left: widthOfContainer * 0.08),
                            child: Column(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Align(
                                  alignment: Alignment.topRight,
                                  child: Text(
                                    textAlign: TextAlign.justify,
                                    textDirection: TextDirection.rtl,
                                    key,
                                    style: TextStyle(
                                      color: const Color.fromARGB(
                                          255, 255, 228, 74),
                                      fontSize: titleContainerFont * 0.55,
                                      fontFamily: 'vazir',
                                      fontWeight: FontWeight.w900,
                                    ),
                                  ),
                                ),
                                const SizedBox(height: 8.0),
                                Align(
                                  alignment: Alignment.center,
                                  child: Text(
                                    textAlign: TextAlign.justify,
                                    textDirection: TextDirection.rtl,
                                    value,
                                    maxLines:
                                        3, // تعداد خطوطی که می‌خواهید نمایش داده شود
                                    overflow: TextOverflow
                                        .ellipsis, // اضافه کردن سه نقطه در انتهای متن
                                    style: const TextStyle(
                                      color: Colors.white,
                                      fontSize: 14,
                                      fontFamily: 'vazir',
                                      fontWeight: FontWeight.w900,
                                    ),
                                  ),
                                ),
                                Align(
                                  alignment: Alignment.bottomLeft,
                                  child: Row(
                                    children: [
                                      IconButton(
                                          onPressed: () {},
                                          icon: Icon(
                                            Icons.done_outline_rounded,
                                            size: widthOfContainer * 0.1,
                                            color: const Color.fromARGB(
                                                255, 0, 255, 8),
                                          )),
                                      IconButton(
                                          onPressed: () {},
                                          icon: Icon(
                                            Icons.delete,
                                            size: widthOfContainer * 0.1,
                                            // ignore: prefer_const_constructors
                                            color:
                                                // ignore: prefer_const_constructors
                                                Color.fromARGB(255, 255, 0, 0),
                                          )),
                                    ],
                                  ),
                                ),
                              ],
                            ),
                          ),
                          betweenItem(widthOfScreen),
                        ],
                      );
                    },
                  ),
                ),
              ],
            ),
          ),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
          faseleyeAmoodi(heightOfScreen, widthOfScreen),
          SizedBox(
            height: widthOfScreen * 0.03,
          ),
        ],
      ),
    );
  }

  SizedBox distanceEacRowFromRight(double widthOfScreen) =>
      SizedBox(width: widthOfScreen * 0.075);

  Container leadingBoard(String info, String title, double heightOfScreen,
      double widthOfScreen, int type) {
    return Container(
      width: widthOfScreen * 0.185,
      height: 78,
      padding: const EdgeInsets.only(left: 3, right: 3, bottom: 5),
      decoration: ShapeDecoration(
        color: (type == 1 || type == 2)
            ? const Color(0xFFFFE610)
            : const Color.fromARGB(255, 0, 255, 21),
        shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(9)),
      ),
      child: Stack(
        children: [
          Positioned(
            top: heightOfScreen * 0.000,
            left: widthOfScreen * 0.038,
            child: Text(
              info,
              textAlign: TextAlign.center,
              // ignore: prefer_const_constructors
              style: TextStyle(
                color: Colors.black,
                fontSize: 35,
                fontFamily: 'vazir',
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
          Positioned(
            top: heightOfScreen * 0.12,
            left: (type == 1)
                ? widthOfScreen * 0.022
                : (type == 2)
                    ? widthOfScreen * 0.029
                    : widthOfScreen * 0.015,
            child: Text(
              title,
              textAlign: TextAlign.center,
              // ignore: prefer_const_constructors
              style: TextStyle(
                color: Colors.black,
                fontSize: (type == 3) ? 13 : 17,
                fontFamily: 'vazir',
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
        ],
      ),
    );
  }

  Divider faseleyeAmoodi(double heightOfScreen, double widthOfScreen) {
    return Divider(
      color: Colors.black,
      height: heightOfScreen * 0.01,
      indent: widthOfScreen * 0.06,
      endIndent: widthOfScreen * 0.06,
    );
  }

  Container containerItem(
      String title,
      String content,
      Color color1,
      Color color2,
      double widthOfContainer,
      double heightOfContainer,
      double titleFromeLeft,
      double titleFromeTop,
      double titleContainerFont,
      double contentFromeLeft,
      double contentFromeTop) {
    return Container(
      width: widthOfContainer,
      height: heightOfContainer,
      decoration: ShapeDecoration(
        gradient: LinearGradient(
          begin: const Alignment(1.00, 0.07),
          end: const Alignment(-1, -0.07),
          colors: [color1, color2],
        ),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(23),
        ),
      ),
      child: Stack(
        children: [
          Positioned(
            left: titleFromeLeft,
            top: titleFromeTop,
            child: Text(
              title,
              style: TextStyle(
                color: const Color.fromARGB(255, 255, 228, 74),
                fontSize: titleContainerFont,
                fontFamily: 'vazir',
                fontWeight: FontWeight.w900,
              ),
            ),
          ),
          Positioned(
            left: contentFromeLeft,
            top: contentFromeTop,
            child: Text(
              content,
              textAlign: TextAlign.center,
              style: const TextStyle(
                color: Colors.white,
                fontSize: 14,
                fontFamily: 'vazir',
                fontWeight: FontWeight.w900,
              ),
            ),
          ),
        ],
      ),
    );
  }

  SizedBox betweenItem(double widthOfScreen) {
    return SizedBox(
      width: widthOfScreen * 0.02,
    );
  }
}
