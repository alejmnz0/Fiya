class AddTeamDto {
  String? name;
  String? urlImage;
  String? primaryColor;
  String? secondaryColor;

  AddTeamDto(
      {this.name, this.urlImage, this.primaryColor, this.secondaryColor});

  AddTeamDto.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    urlImage = json['urlImage'];
    primaryColor = json['primaryColor'];
    secondaryColor = json['secondaryColor'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['urlImage'] = this.urlImage;
    data['primaryColor'] = this.primaryColor;
    data['secondaryColor'] = this.secondaryColor;
    return data;
  }
}
