part of 'add_team_bloc.dart';

@immutable
sealed class AddTeamEvent {}

class DoAddTeamEvent extends AddTeamEvent {
  final String name;
  final String urlimage;
  DoAddTeamEvent(this.name, this.urlimage);
}
