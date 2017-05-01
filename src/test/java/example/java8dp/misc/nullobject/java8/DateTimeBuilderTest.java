package example.java8dp.misc.nullobject.java8;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateTimeBuilderTest {

    @Test
    public void test() {
        LocalDateTime base = LocalDateTime.of(2017, 1, 17, 0, 0);
        LocalDateTime dt = DateTimeBuilder.of(base)
                .nextMonth()
                .lastDay()
                .get();
        assertEquals(LocalDateTime.of(2017, 2, 28, 0, 0), dt);
    }

}
