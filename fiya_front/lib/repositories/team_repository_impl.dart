import 'dart:convert';

import 'package:fiya_front/models/team/add_team_dto.dart';
import 'package:fiya_front/repositories/team_repository.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:http/http.dart';

class TeamRepositoryImpl extends TeamRepository {
  final Client _httpClient = Client();
  final UserRepositoryImpl userRepositoryImpl = UserRepositoryImpl();
  @override
  Future<AddTeamDto> addTeam(AddTeamDto addTeamDto) async {
    final response = await _httpClient.post(
        Uri.parse('10.0.2.2:8080/team/register'),
        body: addTeamDto.toJson());

    if (response.statusCode == 201) {
      final data = json.decode(response.body);
      userRepositoryImpl.editOnTeam();
      return data;
    } else {
      throw Exception('Failed to add Team');
    }
  }
}
