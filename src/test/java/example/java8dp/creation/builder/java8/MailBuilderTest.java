package example.java8dp.creation.builder.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MailBuilderTest {

    @Test
    public void testSend() {
        MailBuilder.send(mailer -> {
            mailer.from("fowler@example.com")
                    .to("trump@example.com")
                    .subject("Greeting")
                    .body("Hello, Mr. President!");
        });
    }

    @Test
    public void testSend2() {
        MailBuilder.send(mailer -> {
            mailer.from("fowler@example.com")
                    .to("trump@example.com")
                    .doIf(someCondition(), m -> m.cc("clinton@example.com"))
                    .subject("Greeting")
                    .body("Hello, Mr. President!");
        });
    }

    private boolean someCondition() {
        return true;
    }

    @Test
    public void testSend3() {
        final List<String> ccAddresses = Arrays.asList("clinton@example.com", "cockburn@example.com");
        MailBuilder.send(mailer -> {
            mailer.from("fowler@example.com")
                    .to("trump@example.com")
                    .foreach(ccAddresses, (m, ccAddress) -> m.cc(ccAddress))
                    .subject("Greeting")
                    .body("Hello, Mr. President!");
        });

        final List<String> ccAddresses2 = new ArrayList<>();
        MailBuilder.send(mailer -> {
            mailer.from("fowler@example.com")
                    .to("trump@example.com")
                    .foreach(ccAddresses2, (m, ccAddress) -> m.cc(ccAddress))
                    .subject("Greeting")
                    .body("Hello, Mr. President!");
        });
    }

}
