import 'package:bloc/bloc.dart';
import 'package:fiya_front/models/team/add_team_dto.dart';
import 'package:fiya_front/repositories/team_repository.dart';
import 'package:meta/meta.dart';

part 'add_team_event.dart';
part 'add_team_state.dart';

class AddTeamBloc extends Bloc<AddTeamEvent, AddTeamState> {
  final TeamRepository teamRepository;

  AddTeamBloc(this.teamRepository) : super(AddTeamInitial()) {
    on<AddTeamEvent>((event, emit) {});
  }

  void doAddTeam(DoAddTeamEvent event, Emitter<AddTeamState> emit) async {
    emit(DoAddTeamLoading());

    try {
      final AddTeamDto addTeamDto =
          AddTeamDto(name: event.name, urlImage: event.urlimage);
      final response = await teamRepository.addTeam(addTeamDto);
      emit(DoAddTeamSuccess(response));
      return;
    } on Exception catch (e) {
      emit(DoAddTeamError(e.toString()));
    }
  }
}
