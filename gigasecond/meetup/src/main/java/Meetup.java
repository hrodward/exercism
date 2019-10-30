import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

class Meetup {

  private final int month;
  private final int year;

  Meetup(int month, int year) {
    this.month = month;
    this.year = year;
  }

  LocalDate day(DayOfWeek dayOfWeek, MeetupSchedule meetupSchedule) {

    LocalDate res = LocalDate.of(year, month, 1);

    switch (meetupSchedule) {
      case LAST:
        return dayLast(res.plusMonths(1), dayOfWeek);
      case TEENTH:
        return dayTeenth(res, dayOfWeek);
      default:
        return dayOrdinal(res, dayOfWeek, meetupSchedule);
    }
  }

  private LocalDate dayOrdinal(LocalDate res, DayOfWeek dayOfWeek, MeetupSchedule meetupSchedule) {
    while (!res.getDayOfWeek().equals(dayOfWeek)) {
      res = res.plusDays(1);
    }
    return res.plusWeeks(meetupSchedule.ordinal());
  }

  private LocalDate dayTeenth(LocalDate res, DayOfWeek dayOfWeek) {
    while (res.getDayOfMonth() < 13 || !res.getDayOfWeek().equals(dayOfWeek)) {
      res = res.plusDays(1);
    }
    return res;
  }

  private LocalDate dayLast(LocalDate res, DayOfWeek dayOfWeek) {
    while (!res.getMonth().equals(Month.of(month)) || !res.getDayOfWeek().equals(dayOfWeek)) {
      res = res.minusDays(1);
    }
    return res;
  }
}