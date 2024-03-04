import 'package:bloc/bloc.dart';
import 'package:fiya_front/models/user/user_response.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:meta/meta.dart';

part 'user_event.dart';
part 'user_state.dart';

class UserBloc extends Bloc<UserEvent, UserState> {
  final UserRepository userRepository;
  UserBloc(this.userRepository) : super(UserInitial()) {
    on<UserViewDetail>(onUserDetail);
  }

  void onUserDetail(UserViewDetail event, Emitter<UserState> emit) async {
    try {
      final user = await userRepository.getDetailUser();
      emit(UserDetailSuccess(user));
      return;
    } on Exception catch (e) {
      emit(UserDetailError(e.toString()));
    }
  }
}
