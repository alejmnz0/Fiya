import 'package:bloc/bloc.dart';
import 'package:fiya_front/models/field/add_field_dto.dart';
import 'package:fiya_front/models/field/field_response.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:meta/meta.dart';

part 'add_field_event.dart';
part 'add_field_state.dart';

class AddFieldBloc extends Bloc<AddFieldEvent, AddFieldState> {
  final FieldRepository fieldRepo;

  AddFieldBloc(this.fieldRepo) : super(AddFieldInitial()) {
    on<DoAddFieldEvent>(doAddField);
  }

  void doAddField(DoAddFieldEvent event, Emitter<AddFieldState> emit) async {
    emit(DoAddFieldLoading());

    try {
      final AddFieldDto addFieldDto = AddFieldDto(
        name: event.name,
        latitude: event.latitude,
        longitude: event.longitude,
      );
      final response = await fieldRepo.addField(addFieldDto);
      emit(DoAddFieldSuccess(response));
      return;
    } on Exception catch (e) {
      emit(DoAddFieldError(e.toString()));
    }
  }
}
