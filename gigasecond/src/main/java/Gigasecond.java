import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

class Gigasecond {

    private static final int GIGASECOND = 1000000000;

    private LocalDateTime localDateTime;

    Gigasecond(LocalDate moment) {
        this.localDateTime = LocalDateTime.of(moment, LocalTime.MIN);
    }

    Gigasecond(LocalDateTime moment) {
        this.localDateTime = moment;
    }

    LocalDateTime getDateTime() {
        return this.localDateTime.plusSeconds(GIGASECOND);
    }

}
