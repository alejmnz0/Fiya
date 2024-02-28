part of 'add_field_bloc.dart';

@immutable
sealed class AddFieldState {}

final class AddFieldInitial extends AddFieldState {}

final class DoAddFieldLoading extends AddFieldState {}

final class DoAddFieldSuccess extends AddFieldState {
  final FieldResponseDto addField;
  DoAddFieldSuccess(this.addField);
}

final class DoAddFieldError extends AddFieldState {
  final String errorMessage;
  DoAddFieldError(this.errorMessage);
}
