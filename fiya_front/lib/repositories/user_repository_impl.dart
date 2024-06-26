import 'dart:convert';
import 'package:fiya_front/global_data.dart';
import 'package:fiya_front/models/user/edit_response.dart';
import 'package:fiya_front/models/user/login_dto.dart';
import 'package:fiya_front/models/user/login_response.dart';
import 'package:fiya_front/models/user/register_dto.dart';
import 'package:fiya_front/models/user/register_response.dart';
import 'package:fiya_front/models/user/user_response.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:http/http.dart';

class UserRepositoryImpl extends UserRepository {
  final Client _httpClient = Client();

  @override
  Future<RegisterResponse> registerUser(RegisterDto registerDto) async {
    final response =
        await _httpClient.post(Uri.parse('http://10.0.2.2:8080/user/register'),
            headers: <String, String>{
              'Content-Type': 'application/json',
            },
            body: json.encode(registerDto.toJson()));

    if (response.statusCode == 201) {
      final data = RegisterResponse.fromJson(json.decode(response.body));
      GlobalData.token = data.token!;
      return data;
    } else {
      throw Exception('Register failed');
    }
  }

  @override
  Future<LoginResponse> loginUser(LoginDto loginDto) async {
    final response =
        await _httpClient.post(Uri.parse('http://10.0.2.2:8080/user/login'),
            headers: <String, String>{
              'Content-Type': 'application/json',
            },
            body: json.encode(loginDto.toJson()));
    if (response.statusCode == 200) {
      final data = LoginResponse.fromJson(json.decode(response.body));
      GlobalData.token = data.token!;
      return data;
    } else {
      throw Exception('Login failed');
    }
  }

  @override
  Future<UserResponse> getDetailUser() async {
    final response = await _httpClient.get(
      Uri.parse('http://10.0.2.2:8080/user/profile'),
      headers: <String, String>{
        'Authorization': 'Bearer ${GlobalData.token}',
      },
    );
    if (response.statusCode == 200) {
      final data = UserResponse.fromJson(json.decode(response.body));
      return data;
    } else {
      throw Exception('Failed to load User');
    }
  }

  @override
  void setFavourite(int fieldId) async {
    final response = await _httpClient.post(
      Uri.parse('http://10.0.2.2:8080/user/$fieldId/fav'),
      headers: <String, String>{
        'Authorization': 'Bearer ${GlobalData.token}',
      },
    );
    if (response.statusCode != 200) {
      throw Exception('Failed to load User');
    }
  }

  @override
  Future<EditResponse> editUser(EditResponse editResponse) async {
    final response =
        await _httpClient.post(Uri.parse('http://10.0.2.2:8080/user/edit'),
            headers: <String, String>{
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ${GlobalData.token}',
            },
            body: json.encode(editResponse.toJson()));
    if (response.statusCode != 200) {
      final data = EditResponse.fromJson(json.decode(response.body));
      return data;
    } else {
      throw Exception('Failed to edit User');
    }
  }

  @override
  void editOnTeam() async {
    await _httpClient.post(Uri.parse('http://10.0.2.2:8080/user/edit'),
        headers: <String, String>{
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ${GlobalData.token}',
        },
        body: <String, bool>{
          'onTeam': true,
        });
  }
}
