import 'package:flutter/material.dart';
import 'sara_appbar.dart';
import 'sara_bottombar.dart';
import 'body_of_sara.dart';

class Sara extends StatefulWidget {
  static const String routeName = 'sara';
  const Sara({super.key});

  @override
  State<Sara> createState() => _SaraState();
}

class _SaraState extends State<Sara> {
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: SaraAppBar(),
      body: BodyOfSara(),
      bottomNavigationBar: SaraBottomBar(),
    );
  }
}
