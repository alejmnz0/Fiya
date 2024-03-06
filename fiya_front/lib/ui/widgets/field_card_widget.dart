/*
// ignore: must_be_immutable
class FieldCardWidget extends StatelessWidget {
  final Field field;
  final int index;
  late bool favourite = false;
  late UserRepository userRepository = UserRepositoryImpl();

  FieldCardWidget(
      {super.key,
      required this.field,
      required this.index,
      required this.favourite});

  
}
*/
import 'package:fiya_front/models/field/field_page_response.dart';
import 'package:fiya_front/repositories/user_repository.dart';
import 'package:fiya_front/repositories/user_repository_impl.dart';
import 'package:fiya_front/ui/pages/field_detail_page.dart';
import 'package:flutter/material.dart';

// ignore: must_be_immutable
class FieldCardWidget extends StatefulWidget {
  final Field field;
  final int index;
  late bool favourite;
  FieldCardWidget(
      {super.key,
      required this.field,
      required this.index,
      required this.favourite});

  @override
  State<FieldCardWidget> createState() => _FieldCardWidgetState();
}

class _FieldCardWidgetState extends State<FieldCardWidget> {
  late UserRepository userRepository = UserRepositoryImpl();

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      child: GestureDetector(
        onTap: () {
          Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) =>
                      FieldDetailPage(fieldId: widget.field.id!)));
        },
        onDoubleTap: () {},
        child: Card(
          color: Colors.white,
          child: ClipRRect(
            borderRadius: BorderRadius.circular(5),
            child: Stack(
              children: [
                const Center(
                  child: CircularProgressIndicator(),
                ),
                Center(
                  child: Container(
                    width: 485,
                    height: 155,
                    decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                        color: Colors.white),
                    child: Column(
                      children: [
                        Padding(
                          padding: const EdgeInsets.fromLTRB(0, 20, 0, 0),
                          child: Text(
                            widget.field.name!,
                            style: const TextStyle(
                                color: Colors.black,
                                fontSize: 30,
                                fontWeight: FontWeight.bold),
                          ),
                        ),
                        Padding(
                          padding: const EdgeInsets.fromLTRB(10, 20, 0, 0),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              const Icon(Icons.monetization_on),
                              Padding(
                                padding: const EdgeInsets.only(left: 5),
                                child: Text(
                                  widget.field.price.toString(),
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold),
                                ),
                              ),
                              const Padding(
                                padding: EdgeInsets.only(left: 25),
                                child: Icon(Icons.groups),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5),
                                child: Text(
                                  widget.field.teamCapacity.toString(),
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold),
                                ),
                              ),
                              const Padding(
                                padding: EdgeInsets.only(left: 25),
                                child: Icon(Icons.grass_rounded),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5),
                                child: Text(
                                  widget.field.ground!,
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold),
                                ),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 25),
                                child: GestureDetector(
                                  onTap: () {
                                    userRepository
                                        .setFavourite(widget.field.id!);
                                  },
                                  child: Icon(
                                    Icons.star,
                                    color: widget.favourite
                                        ? Colors.yellow
                                        : Colors.grey,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      ],
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
