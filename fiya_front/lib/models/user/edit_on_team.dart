class EditOnTeam {
  bool? onTeam;

  EditOnTeam({this.onTeam});

  EditOnTeam.fromJson(Map<String, dynamic> json) {
    onTeam = json['onTeam'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['onTeam'] = this.onTeam;
    return data;
  }
}
