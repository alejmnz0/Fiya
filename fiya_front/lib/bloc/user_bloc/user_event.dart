part of 'user_bloc.dart';

@immutable
sealed class UserEvent {}

class UserViewDetail extends UserEvent {}

class UserEdit extends UserEvent {
  final String name;
  final String email;
  final String birthdate;
  UserEdit(this.name, this.email, this.birthdate);
}
