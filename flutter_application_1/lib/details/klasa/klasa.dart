import 'package:flutter/material.dart';
import 'klasa_appbar.dart';
import 'klasa_bottombar.dart';
import 'body_of_klasa.dart';

class Klasa extends StatefulWidget {
  static const String routeName = 'klasa';
  const Klasa({super.key});

  @override
  State<Klasa> createState() => _KlasaState();
}

class _KlasaState extends State<Klasa> {
  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      appBar: KlasaAppBar(),
      body: BodyOfKlasa(),
      bottomNavigationBar: KlasaBottomBar(),
    );
  }
}
