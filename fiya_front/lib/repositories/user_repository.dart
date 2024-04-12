import 'package:fiya_front/models/user/edit_response.dart';
import 'package:fiya_front/models/user/login_dto.dart';
import 'package:fiya_front/models/user/login_response.dart';
import 'package:fiya_front/models/user/register_dto.dart';
import 'package:fiya_front/models/user/register_response.dart';
import 'package:fiya_front/models/user/user_response.dart';

abstract class UserRepository {
  Future<RegisterResponse> registerUser(RegisterDto registerDto);
  Future<LoginResponse> loginUser(LoginDto loginDto);
  Future<EditResponse> editUser(EditResponse loginDto);
  void editOnTeam();
  Future<UserResponse> getDetailUser();
  void setFavourite(int fieldId);
}
