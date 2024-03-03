import 'package:fiya_front/bloc/add-field-bloc/add_field_bloc.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:fiya_front/repositories/field_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class AddFieldPage extends StatefulWidget {
  const AddFieldPage({super.key});

  @override
  State<AddFieldPage> createState() => _AddFieldPageState();
}

class _AddFieldPageState extends State<AddFieldPage> {
  final formRegister = GlobalKey<FormState>();
  final nameTextController = TextEditingController();
  final latTextController = TextEditingController();
  final longTextController = TextEditingController();

  late FieldRepository fieldRepo;
  late AddFieldBloc addFieldBloc;

  @override
  void initState() {
    fieldRepo = FieldRepositoryImpl();
    addFieldBloc = AddFieldBloc(fieldRepo);
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
            value: addFieldBloc,
            child: BlocConsumer<AddFieldBloc, AddFieldState>(
              buildWhen: (context, state) {
                return state is AddFieldInitial ||
                    state is DoAddFieldSuccess ||
                    state is DoAddFieldError ||
                    state is DoAddFieldLoading;
              },
              builder: (context, state) {
                if (state is DoAddFieldSuccess) {
                  return const Text('Add Field success');
                } else if (state is DoAddFieldError) {
                  return const Text('Add Field error');
                } else if (state is DoAddFieldLoading) {
                  return const Center(child: CircularProgressIndicator());
                }
                return Center(child: buildAddFieldForm());
              },
              listener: (BuildContext context, AddFieldState state) {},
            )),
      )),
    );
  }

  buildAddFieldForm() {
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
              'Add Field',
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
                  border: OutlineInputBorder(), labelText: 'Field name'),
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
              controller: latTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Latitude'),
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
              controller: longTextController,
              decoration: const InputDecoration(
                  border: OutlineInputBorder(), labelText: 'Longitude'),
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
                  backgroundColor:
                      MaterialStatePropertyAll(Color.fromRGBO(33, 33, 33, 100)),
                ),
                child: Text('Confirm'.toUpperCase()),
                onPressed: () {
                  if (formRegister.currentState!.validate()) {
                    addFieldBloc.add(DoAddFieldEvent(nameTextController.text,
                        latTextController.text, longTextController.text));
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
