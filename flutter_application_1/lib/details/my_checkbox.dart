import 'package:flutter/material.dart';

class MyCheckbox extends StatefulWidget {
  const MyCheckbox({super.key});

  @override
  State<MyCheckbox> createState() => _MyCheckboxState();
}

class _MyCheckboxState extends State<MyCheckbox> {
  bool sbu = false;
  bool ssh = false;
  bool qom = false;
  bool kashan = false;

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        createCheckBox('شهید بهشتی', sbu),
        createCheckBox('شریف', ssh),
        createCheckBox('قم', qom),
        createCheckBox('کاشان', kashan),
      ],
    );
  }

  Widget createCheckBox(String title, bool isActive) {
    return Checkbox(
      checkColor: Colors.purple,
      value: isActive,
      onChanged: (bool? value) {
        setState(
              () {
            isActive = !value!;
          },
        );
      },
    );
  }
}
