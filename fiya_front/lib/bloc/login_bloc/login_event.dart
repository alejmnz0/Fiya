part of 'login_bloc.dart';

@immutable
sealed class LoginEvent {}

class DoLoginEvent extends LoginEvent {
  final String dni;
  final String password;
  DoLoginEvent(this.dni, this.password);
}

class GetRequestTokenEvent extends LoginEvent {}
