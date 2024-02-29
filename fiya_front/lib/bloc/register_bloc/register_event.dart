part of 'register_bloc.dart';

@immutable
sealed class RegisterEvent {}

class DoRegisterEvent extends RegisterEvent {
  final String dni;
  final String name;
  final String email;
  final String password;
  final String repeatPassword;
  final String birthdate;
  DoRegisterEvent(this.dni, this.name, this.email, this.password,
      this.repeatPassword, this.birthdate);
}
