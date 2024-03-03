part of 'field_bloc.dart';

@immutable
sealed class FieldEvent {}

class FieldFetchList extends FieldEvent {}

class FieldViewDetail extends FieldEvent {
  final int fieldId;
  FieldViewDetail(this.fieldId);
}
