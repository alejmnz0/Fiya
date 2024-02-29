class LoginResponse {
  String? id;
  String? dni;
  String? token;

  LoginResponse({this.id, this.dni, this.token});

  LoginResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    dni = json['dni'];
    token = json['token'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['dni'] = this.dni;
    data['token'] = this.token;
    return data;
  }
}
