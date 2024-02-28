import 'package:fiya_front/bloc/user_bloc/register_bloc.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:fiya_front/ui/pages/add_field_page.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:flutter/material.dart';
import 'package:intl/intl.dart';

class RegisterPage extends StatefulWidget {
  const RegisterPage({super.key});

  @override
  State<RegisterPage> createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final formRegister = GlobalKey<FormState>();
  final dniTextController = TextEditingController();
  final passTextController = TextEditingController();

  late UserRepository userRepo;
  late RegisterBloc registerBloc;

  @override
  void initState() {
    userRepo = UserRepositoryImpl();
    registerBloc = RegisterBloc(userRepo);
    super.initState();
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
            value: registerBloc,
            child: BlocConsumer<RegisterBloc, RegisterState>(
              buildWhen: (context, state) {
                return state is RegisterInitial ||
                    state is DoRegisterSuccess ||
                    state is DoRegisterError ||
                    state is DoRegisterLoading;
              },
              builder: (context, state) {
                if (state is DoRegisterSuccess) {
                  return const Text('Login success');
                } else if (state is DoRegisterError) {
                  return const Text('Login error');
                } else if (state is DoRegisterLoading) {
                  return const Center(child: CircularProgressIndicator());
                }
                return Center(child: buildRegisterForm());
              },
              listener: (BuildContext context, RegisterState state) {},
            )),
      )),
    );
  }

  buildRegisterForm() {
    return Form(
      key: formRegister,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 80.0),
            child: Text(
              'Login',
              textAlign: TextAlign.start,
              style: TextStyle(fontSize: 40),
            ),
          ),
          const SizedBox(
            height: 20,
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 80.0),
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
            padding: const EdgeInsets.symmetric(horizontal: 80.0),
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
            padding: const EdgeInsets.symmetric(horizontal: 80.0),
            child: SizedBox(
              width: double.infinity,
              child: ElevatedButton(
                style: const ButtonStyle(
                  backgroundColor: MaterialStatePropertyAll(
                      Color.fromRGBO(129, 129, 129, 0.612)),
                ),
                child: Text('Login'.toUpperCase()),
                onPressed: () {
                  if (formRegister.currentState!.validate()) {
                    registerBloc.add(DoRegisterEvent(
                        passTextController.text, dniTextController.text));
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
            child: const Text("You dont have an account?"),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const AddFieldPage()),
              );
            },
          )),
        ],
      ),
    );
  }
}
