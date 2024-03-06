import 'package:fiya_front/bloc/login_bloc/login_bloc.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:fiya_front/ui/pages/home_page.dart';
import 'package:fiya_front/ui/pages/register_page.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final formLogin = GlobalKey<FormState>();
  final dniTextController = TextEditingController(text: '12345678A');
  final passTextController = TextEditingController(text: '123456789');

  late UserRepository userRepo;
  late LoginBloc loginBloc;

  @override
  void initState() {
    userRepo = UserRepositoryImpl();
    loginBloc = LoginBloc(userRepo);
    super.initState();
  }

  @override
  void dispose() {
    dniTextController.dispose();
    passTextController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
          child: Container(
        decoration: const BoxDecoration(
            gradient: LinearGradient(
          begin: Alignment.bottomLeft,
          end: Alignment.topRight,
          colors: [
            Color.fromRGBO(70, 70, 70, 100),
            Color.fromRGBO(240, 240, 240, 100),
          ],
        )),
        child: BlocProvider.value(
            value: loginBloc,
            child: BlocConsumer<LoginBloc, LoginState>(
              buildWhen: (context, state) {
                return state is LoginInitial ||
                    state is DoLoginSuccess ||
                    state is DoLoginError ||
                    state is DoLoginLoading;
              },
              builder: (context, state) {
                if (state is DoLoginSuccess) {
                  return const Text('Login success');
                } else if (state is DoLoginError) {
                  return const Text('Login error');
                } else if (state is DoLoginLoading) {
                  return const Center(child: CircularProgressIndicator());
                }
                return Center(child: buildLoginForm());
              },
              listener: (BuildContext context, LoginState state) {
                if (state is DoLoginSuccess) {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => const HomePage()),
                  );
                }
              },
            )),
      )),
    );
  }

  buildLoginForm() {
    return Form(
      key: formLogin,
      child: ListView(children: [
        Column(
          mainAxisAlignment: MainAxisAlignment.center,
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Center(child: Image.asset('assets/images/logo.jpeg', height: 150)),
            const Padding(
              padding: EdgeInsets.symmetric(horizontal: 80.0),
              child: Center(
                child: Text(
                  'Login',
                  textAlign: TextAlign.start,
                  style: TextStyle(fontSize: 40),
                ),
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 50.0),
              child: TextFormField(
                controller: dniTextController,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Dni'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter some text';
                  }
                  return null;
                },
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 50.0),
              child: TextFormField(
                controller: passTextController,
                obscureText: true,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(), labelText: 'Password'),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return 'Please enter some text';
                  }
                  return null;
                },
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 50.0),
              child: SizedBox(
                width: double.infinity,
                height: 50,
                child: ElevatedButton(
                  style: const ButtonStyle(
                    backgroundColor:
                        MaterialStatePropertyAll(Color.fromRGBO(33, 33, 33, 1)),
                  ),
                  child: Text(
                    'Get Started'.toUpperCase(),
                    style: const TextStyle(color: Colors.white),
                  ),
                  onPressed: () {
                    if (formLogin.currentState!.validate()) {
                      loginBloc.add(DoLoginEvent(
                          dniTextController.text, passTextController.text));
                    }
                  },
                ),
              ),
            ),
            const SizedBox(
              height: 20,
            ),
            Center(
                child: InkWell(
              child: const Text(
                "You dont have an account?",
                style: TextStyle(color: Color.fromARGB(255, 22, 114, 189)),
              ),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => const RegisterPage()),
                );
              },
            )),
          ],
        ),
      ]),
    );
  }
}
