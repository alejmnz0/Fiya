import 'package:fiya_front/models/field/add_field_dto.dart';
import 'package:fiya_front/models/field/feild_page_response.dart';
import 'package:fiya_front/models/field/field_response.dart';

abstract class FieldRepository {
  Future<FieldResponseDto> addField(AddFieldDto addFieldDto);
  Future<List<Field>> fetchList();
}
