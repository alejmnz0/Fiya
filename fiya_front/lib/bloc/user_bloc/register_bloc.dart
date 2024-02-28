import 'package:bloc/bloc.dart';
import 'package:fiya_front/models/user/login_dto.dart';
import 'package:fiya_front/models/user/register_response.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:meta/meta.dart';

part 'register_event.dart';
part 'register_state.dart';

class RegisterBloc extends Bloc<RegisterEvent, RegisterState> {
  final UserRepository userRepo;

  RegisterBloc(this.userRepo) : super(RegisterInitial()) {
    on<DoRegisterEvent>(doRegister);
  }

  void doRegister(DoRegisterEvent event, Emitter<RegisterState> emit) async {
    emit(DoRegisterLoading());

    try {
      final RegisterDto loginDto = RegisterDto(
          name: event.name,
          password: event.password,
          dni: event.dni,
          email: event.email,
          repeatPassword: event.repeatPassword,
          birthdate: event.birthdate);
      final response = await userRepo.registerUser(loginDto);
      emit(DoRegisterSuccess(response));
      return;
    } on Exception catch (e) {
      emit(DoRegisterError(e.toString()));
    }
  }
}
