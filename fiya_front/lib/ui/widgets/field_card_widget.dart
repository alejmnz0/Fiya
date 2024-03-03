import 'package:fiya_front/models/field/feild_page_response.dart';
import 'package:fiya_front/ui/pages/field_detail_page.dart';
import 'package:flutter/material.dart';

class FieldCardWidget extends StatelessWidget {
  final Field field;
  final int index;

  const FieldCardWidget({super.key, required this.field, required this.index});

  @override
  Widget build(BuildContext context) {
    int delay = 100 * index;

    return SizedBox(
      child: GestureDetector(
        onTap: () {
          Navigator.push(
              context,
              MaterialPageRoute(
                  builder: (context) => FieldDetailPage(fieldId: field.id!)));
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
                            field.name!,
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
                                  field.price.toString(),
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
                                  field.teamCapacity.toString(),
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
                                  field.ground!,
                                  style: const TextStyle(
                                      color: Colors.black,
                                      fontSize: 20,
                                      fontWeight: FontWeight.bold),
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