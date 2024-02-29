class LoginDto {
  String? dni;
  String? password;

  LoginDto({this.dni, this.password});

  LoginDto.fromJson(Map<String, dynamic> json) {
    dni = json['dni'];
    password = json['password'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['dni'] = this.dni;
    data['password'] = this.password;
    return data;
  }
}
