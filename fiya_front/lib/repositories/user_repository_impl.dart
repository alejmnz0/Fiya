import 'dart:convert';

import 'package:fiya_front/models/user/register_dto.dart';
import 'package:fiya_front/models/user/register_response.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:http/http.dart';

class UserRepositoryImpl extends UserRepository {
  final Client _httpClient = Client();

  @override
  Future<RegisterResponse> registerUser(RegisterDto registerDto) async {
    final response = await _httpClient
        .post(Uri.parse('localhost:8080/register'), body: registerDto.toJson());

    if (response.statusCode == 201) {
      final data = json.decode(response.body);
      return data;
    } else {
      throw Exception('Register failed');
    }
  }
}
