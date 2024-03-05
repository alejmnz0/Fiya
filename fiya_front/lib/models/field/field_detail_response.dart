class FieldDetailResponse {
  int? id;
  String? name;
  String? latitude;
  String? longitude;
  double? price;
  int? teamCapacity;
  String? ground;
  String? description;

  FieldDetailResponse(
      {this.id,
      this.name,
      this.latitude,
      this.longitude,
      this.price,
      this.teamCapacity,
      this.ground,
      this.description});

  FieldDetailResponse.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    latitude = json['latitude'];
    longitude = json['longitude'];
    price = json['price'];
    teamCapacity = json['teamCapacity'];
    ground = json['ground'];
    description = json['description'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['id'] = this.id;
    data['name'] = this.name;
    data['latitude'] = this.latitude;
    data['longitude'] = this.longitude;
    data['price'] = this.price;
    data['teamCapacity'] = this.teamCapacity;
    data['ground'] = this.ground;
    data['description'] = this.description;
    return data;
  }
}
