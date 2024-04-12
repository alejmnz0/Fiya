part of 'add_team_bloc.dart';

@immutable
sealed class AddTeamState {}

final class AddTeamInitial extends AddTeamState {}

final class DoAddTeamLoading extends AddTeamState {}

final class DoAddTeamSuccess extends AddTeamState {
  final AddTeamDto addTeam;
  DoAddTeamSuccess(this.addTeam);
}

final class DoAddTeamError extends AddTeamState {
  final String errorMessage;
  DoAddTeamError(this.errorMessage);
}
