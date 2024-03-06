import 'package:fiya_front/bloc/register_bloc/register_bloc.dart';
import 'package:fiya_front/bloc/user_bloc/user_bloc.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:fiya_front/ui/pages/login_page.dart';
import 'package:fiya_front/ui/widgets/field_card_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:intl/intl.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({super.key});

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
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
  late UserRepository userRepository;
  late UserBloc userBloc;
  late RegisterBloc registerBloc;

  @override
  void initState() {
    userRepository = UserRepositoryImpl();
    userBloc = UserBloc(userRepository)..add(UserViewDetail());
    registerBloc = RegisterBloc(userRepository);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider.value(value: userBloc, child: detailPage());
  }

  Widget detailPage() {
    return BlocBuilder<UserBloc, UserState>(
      builder: (context, state) {
        if (state is UserDetailSuccess) {
          nameTextController.text = state.user.name!;
          emailTextController.text = state.user.email!;
          birthdateTextController.text = state.user.birthdate!;
          return Scaffold(
            body: Column(
              children: [
                Expanded(
                    flex: 1,
                    child: Stack(
                      fit: StackFit.expand,
                      children: [
                        Container(
                          margin: const EdgeInsets.only(bottom: 50),
                          decoration: const BoxDecoration(
                              gradient: LinearGradient(
                                  begin: Alignment.bottomCenter,
                                  end: Alignment.topCenter,
                                  colors: [
                                    Colors.purple,
                                    Color.fromRGBO(156, 49, 186, 1)
                                  ]),
                              borderRadius: BorderRadius.only(
                                bottomLeft: Radius.circular(35),
                                bottomRight: Radius.circular(35),
                              )),
                        ),
                        Align(
                          alignment: Alignment.bottomCenter,
                          child: SizedBox(
                            width: 150,
                            height: 150,
                            child: Stack(
                              fit: StackFit.expand,
                              children: [
                                Container(
                                  decoration: BoxDecoration(
                                    color: Colors.black,
                                    shape: BoxShape.circle,
                                    image: DecorationImage(
                                        fit: BoxFit.cover,
                                        image: state.user.image != null
                                            ? NetworkImage(state.user.image!)
                                            : const NetworkImage(
                                                'https://t3.ftcdn.net/jpg/05/16/27/58/360_F_516275801_f3Fsp17x6HQK0xQgDQEELoTuERO4SsWV.jpg')),
                                  ),
                                ),
                              ],
                            ),
                          ),
                        )
                      ],
                    )),
                Expanded(
                  flex: 3,
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Column(
                      children: [
                        Text(
                          state.user.name!,
                          style: Theme.of(context)
                              .textTheme
                              .titleLarge
                              ?.copyWith(fontWeight: FontWeight.bold),
                        ),
                        const SizedBox(height: 10),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            const Icon(Icons.email),
                            Padding(
                              padding: const EdgeInsets.only(left: 5),
                              child: Text(
                                state.user.email!,
                                style: Theme.of(context)
                                    .textTheme
                                    .titleMedium
                                    ?.copyWith(fontWeight: FontWeight.normal),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 10),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            const Icon(Icons.badge),
                            Padding(
                              padding: const EdgeInsets.only(left: 5),
                              child: Text(
                                state.user.dni!,
                                style: Theme.of(context)
                                    .textTheme
                                    .titleMedium
                                    ?.copyWith(fontWeight: FontWeight.normal),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 10),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            const Icon(Icons.cake),
                            Padding(
                              padding: const EdgeInsets.only(left: 5),
                              child: Text(
                                state.user.birthdate!,
                                style: Theme.of(context)
                                    .textTheme
                                    .titleMedium
                                    ?.copyWith(fontWeight: FontWeight.normal),
                              ),
                            ),
                          ],
                        ),
                        const SizedBox(height: 16),
                        Container(
                            height: 150,
                            child: GridView.builder(
                                scrollDirection: Axis.horizontal,
                                gridDelegate:
                                    const SliverGridDelegateWithFixedCrossAxisCount(
                                        crossAxisCount: 1,
                                        childAspectRatio: 0.4,
                                        mainAxisSpacing: 10),
                                itemCount: state.user.favourites!.length,
                                itemBuilder: (context, index) {
                                  final field = state.user.favourites![index];
                                  return FieldCardWidget(
                                    field: field,
                                    index: index,
                                    favourite: true,
                                  );
                                })),
                        const SizedBox(height: 16),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            FloatingActionButton.extended(
                              onPressed: () {
                                showModalBottomSheet(
                                    context: context,
                                    builder: (context) {
                                      return buildRegisterForm(state);
                                    });
                              },
                              heroTag: 'edit',
                              backgroundColor: Colors.lightBlueAccent,
                              elevation: 0,
                              label: const Text("Edit"),
                              icon: const Icon(Icons.edit),
                            ),
                            const SizedBox(width: 16.0),
                            FloatingActionButton.extended(
                              onPressed: () {
                                Navigator.push(
                                  context,
                                  MaterialPageRoute(
                                      builder: (context) => const LoginPage()),
                                );
                              },
                              heroTag: 'logout',
                              elevation: 0,
                              backgroundColor: Colors.red,
                              label: const Text("Logout"),
                              icon: const Icon(Icons.logout),
                            ),
                          ],
                        ),
                        const SizedBox(height: 16)
                      ],
                    ),
                  ),
                ),
              ],
            ),
          );
        } else if (state is UserDetailError) {
          return Text(state.errorMessage);
        } else {
          return const Center(child: CircularProgressIndicator());
        }
      },
    );
  }

  buildRegisterForm(UserDetailSuccess state) {
    return Form(
      key: formRegister,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const SizedBox(
            height: 30,
          ),
          const Padding(
            padding: EdgeInsets.symmetric(horizontal: 50.0),
            child: Center(
              child: Text(
                'Edit account',
                textAlign: TextAlign.center,
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
            padding: const EdgeInsets.symmetric(horizontal: 50.0),
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
            padding: const EdgeInsets.symmetric(horizontal: 50.0),
            child: TextFormField(
              controller: birthdateTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Birthday'),
              validator: (value) {
                if (value == null ||
                    value.compareTo(birthDate.toString()) == 0) {
                  return 'Please select a date';
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
                  'Confirm'.toUpperCase(),
                  style: const TextStyle(color: Colors.white),
                ),
                onPressed: () {
                  userBloc.add(UserEdit(nameTextController.text,
                      emailTextController.text, birthdateTextController.text));
                },
              ),
            ),
          ),
          const SizedBox(
            height: 20,
          ),
        ],
      ),
    );
  }
}
