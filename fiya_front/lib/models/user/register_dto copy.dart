class RegisterDto {
  String? dni;
  String? email;
  String? name;
  String? password;
  String? repeatPassword;
  String? birthdate;

  RegisterDto(
      {this.dni,
      this.email,
      this.name,
      this.password,
      this.repeatPassword,
      this.birthdate});

  RegisterDto.fromJson(Map<String, dynamic> json) {
    dni = json['dni'];
    email = json['email'];
    name = json['name'];
    password = json['password'];
    repeatPassword = json['repeatPassword'];
    birthdate = json['birthdate'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['dni'] = this.dni;
    data['email'] = this.email;
    data['name'] = this.name;
    data['password'] = this.password;
    data['repeatPassword'] = this.repeatPassword;
    data['birthdate'] = this.birthdate;
    return data;
  }
}
