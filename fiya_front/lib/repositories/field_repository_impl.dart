import 'dart:convert';

import 'package:fiya_front/models/field/add_field_dto.dart';
import 'package:fiya_front/models/field/field_response.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:http/http.dart';

class FieldRepositoryImpl extends FieldRepository {
  final Client _httpClient = Client();

  @override
  Future<FieldResponseDto> addField(AddFieldDto addFieldDto) async {
    final response = await _httpClient.post(
        Uri.parse('localhost:8080/field/add'),
        body: addFieldDto.toJson());

    if (response.statusCode == 201) {
      final data = json.decode(response.body);
      return data;
    } else {
      throw Exception('Add Field failed');
    }
  }
}
