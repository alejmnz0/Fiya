import 'package:bloc/bloc.dart';
import 'package:fiya_front/models/field/field_detail_response.dart';
import 'package:fiya_front/models/field/field_page_response.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:meta/meta.dart';

part 'field_event.dart';
part 'field_state.dart';

class FieldBloc extends Bloc<FieldEvent, FieldState> {
  final FieldRepository fieldRepository;
  FieldBloc(this.fieldRepository) : super(FieldInitial()) {
    on<FieldFetchList>(onFieldFetchList);
    on<FieldViewDetail>(onFieldDetail);
  }

  void onFieldFetchList(FieldFetchList event, Emitter<FieldState> emit) async {
    try {
      final fieldList = await fieldRepository.fetchList();
      emit(FieldFetchSuccess(fieldList));
      return;
    } on Exception catch (e) {
      emit(FieldFetchError(e.toString()));
    }
  }

  void onFieldDetail(FieldViewDetail event, Emitter<FieldState> emit) async {
    try {
      final field = await fieldRepository.getDetails(event.fieldId);
      emit(FieldDetailSuccess(field));
      return;
    } on Exception catch (e) {
      emit(FieldDetailError(e.toString()));
    }
  }
}
