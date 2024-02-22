import 'package:fiya_front/bloc/user_bloc/register_bloc.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:flutter/material.dart';

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
  DateTime birthDate = DateTime(1900, 1, 1);
  final TextEditingController birthdateTextController = TextEditingController();

  late UserRepository userRepo;
  late RegisterBloc registerBloc;

  void initState() {
    birthdateTextController.text = birthDate.toString();
    userRepo = UserRepositoryImpl();
    registerBloc = RegisterBloc(userRepo);
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
          const Text(
            'Login',
            textAlign: TextAlign.start,
            style: TextStyle(fontSize: 40),
          ),
          const SizedBox(
            height: 20,
          ),
          TextFormField(
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
          const SizedBox(
            height: 20,
          ),
          TextFormField(
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
          const SizedBox(
            height: 20,
          ),
          TextFormField(
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
          const SizedBox(
            height: 20,
          ),
          TextFormField(
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
          const SizedBox(
            height: 20,
          ),
          TextFormField(
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
          const SizedBox(
            height: 20,
          ),
          TextFormField(
            controller: birthdateTextController,
            decoration: const InputDecoration(
                border: OutlineInputBorder(), labelText: 'Birthday'),
            validator: (value) {
              if (value == null || value.isEmpty) {
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
                        DateFormat.yMMMd().format(birthDate).toString();
                  });
                }
              });
            },
          ),
          const SizedBox(
            height: 20,
          ),
          SizedBox(
            width: double.infinity,
            child: ElevatedButton(
              child: Text('Login'.toUpperCase()),
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
        ],
      ),
    );
  }
}
