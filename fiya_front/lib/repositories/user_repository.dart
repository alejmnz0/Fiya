import 'package:fiya_front/models/user/login_dto.dart';
import 'package:fiya_front/models/user/register_response.dart';

abstract class UserRepository {
  Future<RegisterResponse> registerUser(RegisterDto registerDto);
  Future<RegisterResponse> loginUser(RegisterDto registerDto);
}
