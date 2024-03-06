class EditResponse {
  String? email;
  String? name;
  String? birthdate;

  EditResponse({this.email, this.name, this.birthdate});

  EditResponse.fromJson(Map<String, dynamic> json) {
    email = json['email'];
    name = json['name'];
    birthdate = json['birthdate'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['email'] = this.email;
    data['name'] = this.name;
    data['birthdate'] = this.birthdate;
    return data;
  }
}
