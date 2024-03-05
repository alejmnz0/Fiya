import 'package:fiya_front/ui/pages/login_page.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const Fiya());
}

class Fiya extends StatelessWidget {
  const Fiya({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Fiya App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.purple),
        useMaterial3: true,
      ),
      home: const LoginPage(),
    );
  }
}
