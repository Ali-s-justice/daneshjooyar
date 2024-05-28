import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

class Information extends StatelessWidget {
  static const String routeName = 'information';
  const Information({super.key});

  static const infoStyle = TextStyle(
    fontSize: 15.0,
    fontFamily: 'vazir',
    fontWeight: FontWeight.w400,
  );
  // Reusable gradient decoration
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
    return Container(
      decoration: gradientBackground,
      child: Scaffold(
        backgroundColor: Colors.transparent, // Transparent to show gradient
        body: SingleChildScrollView(
          child: Column(
            children: [
              const SizedBox(
                height: 50.0,
              ),
              Padding(
                padding: const EdgeInsets.symmetric(),
                child: Container(
                  width: 100,
                  height: 100,
                  decoration: const BoxDecoration(
                    color: Colors.yellow,
                    shape: BoxShape.circle,
                  ),
                ),
              ),
              const Text(
                'سید امیرحسین اشرفیان',
                style: TextStyle(
                  fontSize: 25.0,
                  color: Colors.white,
                  fontFamily: 'vazir',
                ),
              ),
              const SizedBox(
                height: 10.0,
              ),
              const Text(
                'دانشجو',
                style: TextStyle(
                    fontSize: 25.0, color: Colors.white, fontFamily: 'vazir'),
              ),
              const SizedBox(
                height: 20.0,
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 40.0,
                  right: 40.0,
                  top: 5.0,
                ),
                child: Container(
                  padding: const EdgeInsets.all(20.0),
                  width: 320,
                  height: 187,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(50),
                    color: Colors.white,
                  ),
                  child: Column(
                    children: <Widget>[
                      const Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        textDirection: TextDirection.rtl,
                        children: [
                          Text(
                            'شماره دانشجویی',
                            style: infoStyle,
                          ),
                          Text(
                            '402243035',
                            style: infoStyle,
                          ),
                        ],
                      ),
                      const faseleBeinInfo(),
                      Container(
                        width: 278.95,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      const faseleBeinInfo(),
                      const Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        textDirection: TextDirection.rtl,
                        children: [
                          Text(
                            'ترم جاری',
                            style: infoStyle,
                          ),
                          Text(
                            'بهار 1402-1403',
                            style: infoStyle,
                          ),
                        ],
                      ),
                      const faseleBeinInfo(),
                      Container(
                        width: 278.95,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      const faseleBeinInfo(),
                      const Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        textDirection: TextDirection.rtl,
                        children: [
                          Text(
                            'شماره دانشجویی',
                            style: infoStyle,
                          ),
                          Text(
                            '402243035',
                            style: infoStyle,
                          ),
                        ],
                      ),
                      const faseleBeinInfo(),
                      Container(
                        width: 278.95,
                        decoration: ShapeDecoration(
                          shape: RoundedRectangleBorder(
                            side: BorderSide(
                              width: 1,
                              strokeAlign: BorderSide.strokeAlignCenter,
                              color:
                                  Colors.black.withOpacity(0.10000000149011612),
                            ),
                          ),
                        ),
                      ),
                      const faseleBeinInfo(),
                      const Row(
                        mainAxisAlignment: MainAxisAlignment.spaceAround,
                        textDirection: TextDirection.rtl,
                        children: [
                          Text(
                            'شماره دانشجویی',
                            style: infoStyle,
                          ),
                          Text(
                            '402243035',
                            style: infoStyle,
                          ),
                        ],
                      ),
                      const faseleBeinInfo(),
                    ],
                  ),
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(
                  left: 40.0,
                  right: 40.0,
                  top: 30.0,
                ),
                child: Container(
                  width: double.infinity,
                  height: 150.0,
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(50),
                    color: Colors.white,
                  ),
                  child: Column(
                    children: <Widget>[
                      OutlinedButton(
                          style: const ButtonStyle(),
                          onPressed: () {},
                          child: const Text(
                            'ویرایش مشخصات',
                            style: TextStyle(),
                          ))
                    ],
                  ),
                ),
              ),
              const SizedBox(
                height: 100.0,
              ),
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  minimumSize: const Size(340.0, 50),
                  backgroundColor: Colors.red,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20.0),
                  ),
                ),
                onPressed: () {},
                child: const Padding(
                  padding: EdgeInsets.symmetric(vertical: 10.0),
                  child: Text(
                    'حذف حساب کاربری',
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
    );
  }
}

class faseleBeinInfo extends StatelessWidget {
  const faseleBeinInfo({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return const SizedBox(
      height: 7.5,
    );
  }
}
