class FieldPageResponse {
  List<Field>? content;
  int? size;
  int? elements;
  int? page;
  bool? firs;
  bool? last;

  FieldPageResponse(
      {this.content,
      this.size,
      this.elements,
      this.page,
      this.firs,
      this.last});

  FieldPageResponse.fromJson(Map<String, dynamic> json) {
    if (json['content'] != null) {
      content = <Field>[];
      json['content'].forEach((v) {
        content!.add(new Field.fromJson(v));
      });
    }
    size = json['size'];
    elements = json['elements'];
    page = json['page'];
    firs = json['firs'];
    last = json['last'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    if (this.content != null) {
      data['content'] = this.content!.map((v) => v.toJson()).toList();
    }
    data['size'] = this.size;
    data['elements'] = this.elements;
    data['page'] = this.page;
    data['firs'] = this.firs;
    data['last'] = this.last;
    return data;
  }
}

class Field {
  int? id;
  String? name;
  String? latitude;
  String? longitude;
  int? price;
  int? teamCapacity;
  String? ground;

  Field(
      {this.id,
      this.name,
      this.latitude,
      this.longitude,
      this.price,
      this.teamCapacity,
      this.ground});

  Field.fromJson(Map<String, dynamic> json) {
    id = json['id'];
    name = json['name'];
    latitude = json['latitude'];
    longitude = json['longitude'];
    price = json['price'];
    teamCapacity = json['teamCapacity'];
    ground = json['ground'];
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
    return data;
  }
}
