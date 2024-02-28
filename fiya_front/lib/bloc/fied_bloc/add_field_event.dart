part of 'add_field_bloc.dart';

@immutable
sealed class AddFieldEvent {}

class DoAddFieldEvent extends AddFieldEvent {
  final String name;
  final String longitude;
  final String latitude;
  DoAddFieldEvent(this.name, this.latitude, this.longitude);
}
