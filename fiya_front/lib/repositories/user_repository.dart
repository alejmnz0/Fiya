import 'package:fiya_front/models/user/login_dto.dart';
import 'package:fiya_front/models/user/login_response.dart';
import 'package:fiya_front/models/user/register_dto%20copy.dart';
import 'package:fiya_front/models/user/register_response.dart';
import 'package:fiya_front/models/user/user_response.dart';

abstract class UserRepository {
  Future<RegisterResponse> registerUser(RegisterDto registerDto);
  Future<LoginResponse> loginUser(LoginDto loginDto);
  Future<UserResponse> getDetailUser();
}
