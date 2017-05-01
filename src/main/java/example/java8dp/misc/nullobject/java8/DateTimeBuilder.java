package example.java8dp.misc.nullobject.java8;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Function;

public class DateTimeBuilder {
    private LocalDateTime base;
    private Function<Temporal, Temporal> func;

    private DateTimeBuilder(LocalDateTime base) {
        this.base = base;
        //func = t -> t;
        func = Function.identity();
    }

    private void compose(TemporalAdjuster next) {
        func = func.andThen(t -> next.adjustInto(t));
    }

    public DateTimeBuilder nextMonth() {
        compose(t -> t.plus(1, ChronoUnit.MONTHS));
        return this;
    }

    public DateTimeBuilder lastDay() {
        compose(TemporalAdjusters.lastDayOfMonth());
        return this;
    }

    public LocalDateTime get() {
        return (LocalDateTime) func.apply(base);
    }

    public static DateTimeBuilder of(LocalDateTime base) {
        DateTimeBuilder builder = new DateTimeBuilder(base);
        return builder;
    }
}
