import 'package:flutter/material.dart';

class BodyOfKlasa extends StatefulWidget {
  const BodyOfKlasa({super.key});

  @override
  State<BodyOfKlasa> createState() => _BodyOfKlasaState();
}

class _BodyOfKlasaState extends State<BodyOfKlasa> {
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
              )),
        ],
      ),
    );
  }
}
