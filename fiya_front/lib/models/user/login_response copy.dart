class RegisterResponse {
  String? dni;
  String? name;
  String? password;

  RegisterResponse({this.dni, this.name, this.password});

  RegisterResponse.fromJson(Map<String, dynamic> json) {
    dni = json['dni'];
    name = json['name'];
    password = json['password'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['dni'] = this.dni;
    data['name'] = this.name;
    data['password'] = this.password;
    return data;
  }
}
