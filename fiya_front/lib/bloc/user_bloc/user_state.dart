part of 'user_bloc.dart';

@immutable
sealed class UserState {}

final class UserInitial extends UserState {}

final class UserDetailSuccess extends UserState {
  final UserResponse user;
  UserDetailSuccess(this.user);
}

final class UserDetailError extends UserState {
  final String errorMessage;
  UserDetailError(this.errorMessage);
}
