class UserResponse {
  String? id;
  String? image;
  String? name;
  String? birthdate;
  String? dni;
  String? rol;

  UserResponse(
      {this.id, this.image, this.name, this.birthdate, this.dni, this.rol});

  UserResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    image = json['image'];
    name = json['name'];
    birthdate = json['birthdate'];
    dni = json['dni'];
    rol = json['rol'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['image'] = this.image;
    data['name'] = this.name;
    data['birthdate'] = this.birthdate;
    data['dni'] = this.dni;
    data['rol'] = this.rol;
    return data;
  }
}
