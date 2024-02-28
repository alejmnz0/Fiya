class AddFieldDto {
  String? name;
  String? latitude;
  String? longitude;

  AddFieldDto({this.name, this.latitude, this.longitude});

  AddFieldDto.fromJson(Map<String, dynamic> json) {
    name = json['name'];
    latitude = json['latitude'];
    longitude = json['longitude'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['name'] = this.name;
    data['latitude'] = this.latitude;
    data['longitude'] = this.longitude;
    return data;
  }
}
