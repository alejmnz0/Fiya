part of 'field_bloc.dart';

@immutable
sealed class FieldState {}

final class FieldInitial extends FieldState {}

final class FieldFetchSuccess extends FieldState {
  final List<Field> fieldList;
  FieldFetchSuccess(this.fieldList);
}

final class FieldFetchError extends FieldState {
  final String errorMessage;
  FieldFetchError(this.errorMessage);
}

final class FieldDetailSuccess extends FieldState {
  final FieldDetailResponse field;
  FieldDetailSuccess(this.field);
}

final class FieldDetailError extends FieldState {
  final String errorMessage;
  FieldDetailError(this.errorMessage);
}
