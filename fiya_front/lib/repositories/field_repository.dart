import 'package:fiya_front/models/field/add_field_dto.dart';
import 'package:fiya_front/models/field/field_detail_response.dart';
import 'package:fiya_front/models/field/field_page_response.dart';
import 'package:fiya_front/models/field/field_response.dart';

import '../ui/pages/field_detail_page.dart';

abstract class FieldRepository {
  Future<FieldResponseDto> addField(AddFieldDto addFieldDto);
  Future<List<Field>> fetchList();
  Future<FieldDetailResponse> getDetails(int id);
  Future<List<Event>> fetchEvents(int fieldId);
  Future<List<Field>> fetchFavLIst();
}
