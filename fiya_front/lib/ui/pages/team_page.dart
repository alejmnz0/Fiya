import 'package:fiya_front/bloc/add-team-bloc/add_team_bloc.dart';
import 'package:fiya_front/global_data.dart';
import 'package:fiya_front/models/team/add_team_dto.dart';
import 'package:fiya_front/repositories/team_repository.dart';
import 'package:fiya_front/repositories/team_repository_impl.dart';
import 'package:fiya_front/ui/pages/create_team_page.dart';
import 'package:flutter/material.dart';

class TeamPage extends StatefulWidget {
  const TeamPage({super.key});

  @override
  State<TeamPage> createState() => _TeamPageState();
}

class _TeamPageState extends State<TeamPage> {
  final formRegister = GlobalKey<FormState>();
  final nameTextController = TextEditingController();
  final urlImageTextController = TextEditingController();
  late TeamRepository teamRepository;
  late AddTeamBloc addTeamBloc;

  @override
  void initState() {
    teamRepository = TeamRepositoryImpl();
    addTeamBloc = AddTeamBloc(teamRepository);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    if (GlobalData.token!.isNotEmpty) {
      return Scaffold(body: buildNoTeamPage());
    } else {
      return const Scaffold(body: Center(child: Text("Work in progress")));
    }
  }

  buildNoTeamPage() {
    return Center(
      child: Column(
        children: [
          const Padding(
            padding: EdgeInsets.only(top: 20),
            child: Card(
              child: Column(
                mainAxisSize: MainAxisSize.min,
                children: <Widget>[
                  ListTile(
                      leading: Icon(Icons.shield),
                      title: Text(
                        'You are not in a team yet',
                        textAlign: TextAlign.center,
                        style: TextStyle(fontSize: 20),
                      ))
                ],
              ),
            ),
          ),
          ElevatedButton(
            style: ElevatedButton.styleFrom(
              backgroundColor: Colors.purple,
              elevation: 0,
            ),
            onPressed: () {
              showModalBottomSheet(
                  context: context,
                  builder: (context) {
                    return buildAddTeamForm();
                  });
            },
            child: const Text("create your own!",
                style: TextStyle(color: Colors.white)),
          ),
        ],
      ),
    );
  }

  buildAddTeamForm() {
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
                'Create team',
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
              controller: urlImageTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'URL Image'),
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
                  'Confirm'.toUpperCase(),
                  style: const TextStyle(color: Colors.white),
                ),
                onPressed: () {
                  addTeamBloc.add(DoAddTeamEvent(
                      nameTextController.text, urlImageTextController.text));
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
