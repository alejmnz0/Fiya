import 'package:fiya_front/models/field/field_page_response.dart';

class UserResponse {
  String? id;
  String? image;
  String? email;
  String? name;
  String? birthdate;
  List<Field>? favourites;
  String? dni;
  String? rol;

  UserResponse(
      {this.id,
      this.image,
      this.email,
      this.name,
      this.birthdate,
      this.favourites,
      this.dni,
      this.rol});

  UserResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    image = json['image'];
    email = json['email'];
    name = json['name'];
    birthdate = json['birthdate'];
    if (json['favourites'] != null) {
      favourites = <Field>[];
      json['favourites'].forEach((v) {
        favourites!.add(new Field.fromJson(v));
      });
    }
    dni = json['dni'];
    rol = json['rol'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['image'] = this.image;
    data['email'] = this.email;
    data['name'] = this.name;
    data['birthdate'] = this.birthdate;
    if (this.favourites != null) {
      data['favourites'] = this.favourites!.map((v) => v.toJson()).toList();
    }
    data['dni'] = this.dni;
    data['rol'] = this.rol;
    return data;
  }
}
