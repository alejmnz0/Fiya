import 'package:fiya_front/models/field/add_field_dto.dart';
import 'package:fiya_front/models/field/field_response.dart';

abstract class FieldRepository {
  Future<FieldResponseDto> addField(AddFieldDto addFieldDto);
}
