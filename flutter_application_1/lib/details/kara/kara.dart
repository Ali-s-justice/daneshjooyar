import 'package:flutter/material.dart';
import 'package:flutter_application_1/details/kara/body_of_kara.dart';
import 'package:flutter_application_1/details/kara/kara_appbar.dart';
import 'kara_bottombar.dart';

class Kara extends StatefulWidget {
  static const String routeName = 'kara';
  const Kara({super.key});

  @override
  State<Kara> createState() => _KaraState();
}

class _KaraState extends State<Kara> {
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: KaraAppBar(),
      body: BodyOfKara(),
      bottomNavigationBar: KaraBottomBar(),
    );
  }
}
