import 'package:fiya_front/bloc/field-bloc/field_bloc.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:fiya_front/repositories/field_repository_impl.dart';
import 'package:fiya_front/ui/widgets/field_card_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class FieldPage extends StatefulWidget {
  const FieldPage({super.key});

  @override
  State<FieldPage> createState() => _FieldPageState();
}

class _FieldPageState extends State<FieldPage> {
  late FieldRepository fieldRepository;
  late FieldBloc fieldBloc;
  String title = "Fields";

  @override
  void initState() {
    fieldRepository = FieldRepositoryImpl();
    fieldBloc = FieldBloc(fieldRepository)..add(FieldFetchList());
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider.value(
        value: fieldBloc,
        child: Scaffold(
          body: fieldList(),
        ));
  }

  Widget fieldList() {
    return BlocBuilder<FieldBloc, FieldState>(builder: (context, state) {
      if (state is FieldFetchSuccess) {
        return GridView.builder(
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 1, childAspectRatio: 3.5, mainAxisSpacing: 10),
            itemCount: state.fieldList.length,
            itemBuilder: (context, index) {
              final field = state.fieldList[index];
              return FieldCardWidget(field: field, index: index);
            });
      } else if (state is FieldFetchError) {
        return Text(state.errorMessage);
      } else {
        return const Center(child: CircularProgressIndicator());
      }
    });
  }
}
