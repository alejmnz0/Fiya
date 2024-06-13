import 'package:fiya_front/bloc/field-bloc/field_bloc.dart';
import 'package:fiya_front/repositories/field_repository.dart';
import 'package:fiya_front/repositories/field_repository_impl.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:table_calendar/table_calendar.dart';

class FieldDetailPage extends StatefulWidget {
  final int fieldId;
  const FieldDetailPage({super.key, required this.fieldId});

  @override
  State<FieldDetailPage> createState() => _FieldDetailPageState();
}

class Event {
  final DateTime dateTime;

  Event({required this.dateTime});

  factory Event.fromJson(Map<String, dynamic> json) {
    return Event(
      dateTime: DateTime.parse(json['fecha']),
    );
  }

  @override
  String toString() => dateTime.toString();
}

ValueNotifier<List<Event>>? _selectedEvents;

final kToday = DateTime.now();
final kFirstDay = DateTime(kToday.year, kToday.month - 3, kToday.day);
final kLastDay = DateTime(kToday.year, kToday.month + 3, kToday.day);

int getHashCode(DateTime key) {
  return key.day * 1000000 + key.month * 10000 + key.year;
}

List<DateTime> daysInRange(DateTime first, DateTime last) {
  final dayCount = last.difference(first).inDays + 1;
  return List.generate(
    dayCount,
    (index) => DateTime.utc(first.year, first.month, first.day + index),
  );
}

class _FieldDetailPageState extends State<FieldDetailPage> {
  CalendarFormat _calendarFormat = CalendarFormat.month;
  DateTime _focusedDay = DateTime.now();
  DateTime? _selectedDay;
  DateTime? _rangeStart;
  DateTime? _rangeEnd;
  RangeSelectionMode _rangeSelectionMode = RangeSelectionMode.toggledOff;
  late FieldRepository fieldRepository;
  late FieldBloc fieldBloc;
  ValueNotifier<List<Event>>? _selectedEvents;
  Map<DateTime, List<Event>> _events = {};

  @override
  void initState() {
    super.initState();
    fieldRepository = FieldRepositoryImpl();
    fieldBloc = FieldBloc(fieldRepository)
      ..add(FieldViewDetail(widget.fieldId));
    _selectedDay = _focusedDay;
    _selectedEvents = ValueNotifier([]);
    _fetchEvents();
  }

  Future<void> _fetchEvents() async {
    FieldRepository fieldRepository = FieldRepositoryImpl();
    List<Event> events = await fieldRepository.fetchEvents(widget.fieldId);
    setState(() {
      _events = _groupEventsByDate(events);
      _selectedEvents!.value = _getEventsForDay(_selectedDay!);
    });
    print(events); // Verifica que se están obteniendo los eventos
    print(_events); // Verifica que los eventos están agrupados correctamente
  }

  Map<DateTime, List<Event>> _groupEventsByDate(List<Event> events) {
    Map<DateTime, List<Event>> data = {};
    for (var event in events) {
      DateTime date = DateTime(
          event.dateTime.year, event.dateTime.month, event.dateTime.day);
      if (data[date] == null) data[date] = [];
      data[date]!.add(event);
    }
    return data;
  }

  List<Event> _getEventsForDay(DateTime day) {
    return _events[day] ?? [];
  }

  List<Event> _getEventsForRange(DateTime start, DateTime end) {
    final days = daysInRange(start, end);
    return [
      for (final day in days) ..._getEventsForDay(day),
    ];
  }

  void _onDaySelected(DateTime selectedDay, DateTime focusedDay) {
    if (!isSameDay(_selectedDay, selectedDay)) {
      setState(() {
        _selectedDay = selectedDay;
        _focusedDay = focusedDay;
        _rangeStart = null;
        _rangeEnd = null;
        _rangeSelectionMode = RangeSelectionMode.toggledOff;
      });
      _selectedEvents!.value = _getEventsForDay(selectedDay);
    }
  }

  void _onRangeSelected(DateTime? start, DateTime? end, DateTime focusedDay) {
    setState(() {
      _selectedDay = null;
      _focusedDay = focusedDay;
      _rangeStart = start;
      _rangeEnd = end;
      _rangeSelectionMode = RangeSelectionMode.toggledOn;
    });
    if (start != null && end != null) {
      _selectedEvents!.value = _getEventsForRange(start, end);
    } else if (start != null) {
      _selectedEvents!.value = _getEventsForDay(start);
    } else if (end != null) {
      _selectedEvents!.value = _getEventsForDay(end);
    }
  }

  @override
  Widget build(BuildContext context) {
    return BlocProvider.value(
      value: fieldBloc,
      child: Scaffold(
        body: fieldDetail(context),
      ),
    );
  }

  Widget fieldDetail(BuildContext context) {
    return BlocBuilder<FieldBloc, FieldState>(builder: (context, state) {
      if (state is FieldDetailSuccess) {
        return Scaffold(
          appBar: AppBar(
            title: Text(state.field.name!),
          ),
          body: SizedBox(
            child: Padding(
              padding: const EdgeInsets.all(5),
              child: Column(
                children: [
                  Column(
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(left: 25),
                        child: Row(
                          children: [
                            const Icon(Icons.monetization_on),
                            Padding(
                              padding: const EdgeInsets.only(left: 5),
                              child: Text(
                                "Precio total alquiler: ${state.field.price}€",
                                style: const TextStyle(
                                    color: Colors.black,
                                    fontSize: 20,
                                    fontWeight: FontWeight.bold),
                              ),
                            ),
                          ],
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left: 25),
                        child: Row(
                          children: [
                            const Icon(Icons.groups),
                            Padding(
                              padding: const EdgeInsets.only(left: 5),
                              child: Text(
                                "Cada equipo: ${state.field.teamCapacity} jugadores",
                                style: const TextStyle(
                                    color: Colors.black,
                                    fontSize: 20,
                                    fontWeight: FontWeight.bold),
                              ),
                            ),
                          ],
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(left: 25),
                        child: Row(
                          children: [
                            const Icon(Icons.grass_rounded),
                            Padding(
                              padding: const EdgeInsets.only(left: 5),
                              child: Text(
                                "Terreno de juego: ${state.field.ground!}",
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
                  TableCalendar(
                    focusedDay: _focusedDay,
                    firstDay: DateTime.utc(2024, 6, 11),
                    lastDay: DateTime.utc(2100, 1, 1),
                    selectedDayPredicate: (day) => isSameDay(_selectedDay, day),
                    rangeStartDay: _rangeStart,
                    rangeEndDay: _rangeEnd,
                    calendarFormat: _calendarFormat,
                    rangeSelectionMode: _rangeSelectionMode,
                    eventLoader: _getEventsForDay,
                    startingDayOfWeek: StartingDayOfWeek.monday,
                    calendarStyle: const CalendarStyle(
                      outsideDaysVisible: false,
                    ),
                    onDaySelected: _onDaySelected,
                    onRangeSelected: _onRangeSelected,
                    onFormatChanged: (format) {
                      if (_calendarFormat != format) {
                        setState(() {
                          _calendarFormat = format;
                        });
                      }
                    },
                    onPageChanged: (focusedDay) {
                      _focusedDay = focusedDay;
                    },
                  ),
                  const SizedBox(height: 8),
                  Expanded(
                    child: ValueListenableBuilder<List<Event>>(
                      valueListenable: _selectedEvents!,
                      builder: (context, value, _) {
                        return ListView.builder(
                          itemCount: value.length,
                          itemBuilder: (context, index) {
                            return Container(
                              margin: const EdgeInsets.symmetric(
                                  horizontal: 12, vertical: 4),
                              decoration: BoxDecoration(
                                border: Border.all(),
                                borderRadius: BorderRadius.circular(12),
                              ),
                              child: ListTile(
                                onTap: () => print('${value[index]}'),
                                title: Text('${value[index]}'),
                              ),
                            );
                          },
                        );
                      },
                    ),
                  ),
                ],
              ),
            ),
          ),
        );
      }
      return const Center(child: CircularProgressIndicator());
    });
  }
}
