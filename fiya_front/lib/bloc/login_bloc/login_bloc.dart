import 'package:bloc/bloc.dart';
import 'package:fiya_front/models/user/login_dto.dart';
import 'package:fiya_front/models/user/login_response.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:meta/meta.dart';

part 'login_event.dart';
part 'login_state.dart';

class LoginBloc extends Bloc<LoginEvent, LoginState> {
  final UserRepository userRepo;

  LoginBloc(this.userRepo) : super(LoginInitial()) {
    on<DoLoginEvent>(_doLogin);
  }

  void _doLogin(DoLoginEvent event, Emitter<LoginState> emit) async {
    emit(DoLoginLoading());

    try {
      final LoginDto loginDto =
          LoginDto(dni: event.dni, password: event.password);
      final response = await userRepo.loginUser(loginDto);
      emit(DoLoginSuccess(response));
      return;
    } on Exception catch (e) {
      emit(DoLoginError(e.toString()));
    }
  }
}
