import 'package:fiya_front/ui/pages/register_page.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const Fiya());
}

class Fiya extends StatelessWidget {
  const Fiya({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const RegisterPage(),
    );
  }
}
