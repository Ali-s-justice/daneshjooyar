import 'package:flutter/material.dart';
import 'details/my_app_bar.dart';
import 'details/my_body.dart';
import 'details/my_bottom.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: MyAppBar(),
        body: MyBody(),
        // bottomNavigationBar: MyBottom(),
      ),
    );
  }
}
