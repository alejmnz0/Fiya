import 'package:fiya_front/bloc/user_bloc/register_bloc.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
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
  final nameTextController = TextEditingController();
  final emailTextController = TextEditingController();
  final dniTextController = TextEditingController();
  final rpassTextController = TextEditingController();
  final passTextController = TextEditingController();
  final lastDate = DateTime.now();
  final firstDate = DateTime(1900, 1, 1);
  DateTime birthDate = DateTime(2000, 1, 1);
  final TextEditingController birthdateTextController = TextEditingController();

  late UserRepository userRepo;
  late RegisterBloc registerBloc;

  @override
  void initState() {
    birthdateTextController.text = birthDate.toString();
    userRepo = UserRepositoryImpl();
    registerBloc = RegisterBloc(userRepo);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Flutter Gradient Example'),
      ),
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
                  return const Text('Register success');
                } else if (state is DoRegisterError) {
                  return const Text('Register error');
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
              'Create an account',
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
              controller: nameTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Name'),
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
              controller: emailTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Email'),
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
            child: TextFormField(
              controller: rpassTextController,
              obscureText: true,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Repeat password'),
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
              controller: birthdateTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Birthday'),
              validator: (value) {
                if (value == null ||
                    value.compareTo(birthDate.toString()) == 0) {
                  return 'Please enter some text';
                }
                return null;
              },
              onTap: () {
                showDatePicker(
                        context: context,
                        firstDate: firstDate,
                        lastDate: lastDate)
                    .then((selectedDate) {
                  if (selectedDate != null) {
                    setState(() {
                      birthDate = selectedDate;
                      birthdateTextController.text =
                          DateFormat.yMd().format(birthDate).toString();
                    });
                  }
                });
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
                  backgroundColor:
                      MaterialStatePropertyAll(Color.fromRGBO(33, 33, 33, 100)),
                ),
                child: Text('Get Started'.toUpperCase()),
                onPressed: () {
                  if (formRegister.currentState!.validate()) {
                    registerBloc.add(DoRegisterEvent(
                        nameTextController.text,
                        passTextController.text,
                        rpassTextController.text,
                        emailTextController.text,
                        dniTextController.text,
                        birthdateTextController.text));
                  }
                },
              ),
            ),
          ),
        ],
      ),
    );
  }
}
