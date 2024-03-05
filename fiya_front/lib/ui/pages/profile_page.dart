import 'package:fiya_front/bloc/user_bloc/user_bloc.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:fiya_front/ui/pages/login_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class ProfilePage extends StatefulWidget {
  const ProfilePage({super.key});

  @override
  State<ProfilePage> createState() => _ProfilePageState();
}

class _ProfilePageState extends State<ProfilePage> {
  late UserRepository userRepository;
  late UserBloc userBloc;

  @override
  void initState() {
    userRepository = UserRepositoryImpl();
    userBloc = UserBloc(userRepository)..add(UserViewDetail());
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
                        Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            /*
                            FloatingActionButton.extended(
                              onPressed: () {},
                              heroTag: 'edit',
                              elevation: 0,
                              label: const Text("Edit"),
                              icon: const Icon(Icons.edit),
                            ), */
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
}
