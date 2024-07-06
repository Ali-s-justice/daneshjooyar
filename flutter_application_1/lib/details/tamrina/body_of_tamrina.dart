import 'package:flutter/material.dart';

class BodyOfTamrina extends StatefulWidget {
  const BodyOfTamrina({super.key});

  @override
  State<BodyOfTamrina> createState() => _BodyOfTamrinaState();
}

class _BodyOfTamrinaState extends State<BodyOfTamrina> {
  Widget selectedItem = Birthdays();
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
          Positioned(
              top: heightOfScreen * 0.135,
              left: widthOfScreen * 0.1,
              right: widthOfScreen * 0.1,
              child: Container(
                height: 1,
                color: Colors.black,
                width: widthOfScreen * 0.75,
              )),
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
                  onTap: () {},
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
                      color: (selected == 3)
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
                  onTap: () {},
                ),
              ],
            ),
          ),
          Positioned(
            top: heightOfScreen * 0.120,
            right: widthOfScreen * 0.15,
            child: Row(
              textDirection: TextDirection.rtl,
              children: [
                InkWell(
                  child: Container(
                    alignment: Alignment.center,
                    height: heightOfScreen * 0.03,
                    width: widthOfScreen * 0.15,
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15),
                      // ignore: prefer_const_constructors
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
                  onTap: () {},
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
                      color: (selected2 == 3)
                          ? const Color(0xFFE4014E)
                          : const Color.fromARGB(255, 123, 123, 123),
                      border: Border.all(),
                    ),
                    child: Text(
                      'انتخاب تاریخ',
                      style: TextStyle(
                          fontFamily: 'vazir',
                          fontSize: heightOfScreen * 0.017,
                          fontWeight: FontWeight.bold,
                          color: Colors.white),
                    ),
                  ),
                  onTap: () {},
                ),
              ],
            ),
          ),
        ],
      ),
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
      height: heightOfScreen, // برای جلوگیری از تداخل با SingleChildScrollView
      child: ListView.builder(
        itemCount: birthdayNames.length,
        itemBuilder: (BuildContext context, int index) {
          return Column(
            children: <Widget>[
              SizedBox(
                height: heightOfScreen * 0.01,
              ),
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
              SizedBox(
                height: heightOfScreen * 0.01,
              ),
            ],
          );
        },
      ),
    );
  }
}