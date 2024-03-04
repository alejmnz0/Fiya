class RegisterResponse {
  String? dni;
  String? name;
  String? password;
  String? token;

  RegisterResponse({this.dni, this.name, this.password, this.token});

  RegisterResponse.fromJson(Map<String, dynamic> json) {
    dni = json['dni'];
    name = json['name'];
    password = json['password'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['dni'] = this.dni;
    data['name'] = this.name;
    data['password'] = this.password;
    data['token'] = this.token;
    return data;
  }
}
