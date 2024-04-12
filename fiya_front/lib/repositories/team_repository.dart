import 'package:fiya_front/models/team/add_team_dto.dart';

abstract class TeamRepository {
  Future<AddTeamDto> addTeam(AddTeamDto addTeamDto);
}
