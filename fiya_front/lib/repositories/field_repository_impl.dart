import 'dart:convert';

import 'package:fiya_front/models/field/add_field_dto.dart';
import 'package:fiya_front/models/field/field_page_response.dart';
import 'package:fiya_front/models/field/field_detail_response.dart';
import 'package:fiya_front/models/field/field_response.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:http/http.dart';

class FieldRepositoryImpl extends FieldRepository {
  final Client _httpClient = Client();

  @override
  Future<FieldResponseDto> addField(AddFieldDto addFieldDto) async {
    final response = await _httpClient
        .post(Uri.parse('10.0.2.2:8080/field/add'), body: addFieldDto.toJson());

    if (response.statusCode == 201) {
      final data = json.decode(response.body);
      return data;
    } else {
      throw Exception('Failed to add Field');
    }
  }

  @override
  Future<List<Field>> fetchList() async {
    final response =
        await _httpClient.get(Uri.parse('http://10.0.2.2:8080/field/'));
    if (response.statusCode == 200) {
      return FieldPageResponse.fromJson(json.decode(response.body)).content!;
    } else {
      throw Exception('Failed to load Fields');
    }
  }

  @override
  Future<FieldDetailResponse> getDetails(int id) async {
    final response =
        await _httpClient.get(Uri.parse('http://10.0.2.2:8080/field/$id'));
    if (response.statusCode == 200) {
      final data = FieldDetailResponse.fromJson(json.decode(response.body));
      return data;
    } else {
      throw Exception('Failed to load Field');
    }
  }
}
