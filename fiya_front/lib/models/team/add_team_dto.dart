class AddTeamDto {
  String? name;
  String? urlImage;

  AddTeamDto({this.name, this.urlImage});

  AddTeamDto.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    urlImage = json['urlImage'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['urlImage'] = this.urlImage;
    return data;
  }
}
