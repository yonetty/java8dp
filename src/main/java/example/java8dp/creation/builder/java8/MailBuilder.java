package example.java8dp.creation.builder.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class MailBuilder {

    private String fromAddress = "";
    private String toAddress = "";
    private List<String> ccAddresses = new ArrayList<>();
    private String subject = "";
    private String body = "";

    private MailBuilder() {
    }

    public MailBuilder from(String address) {
        this.fromAddress = address;
        return this;
    }

    public MailBuilder to(String address) {
        this.toAddress = address;
        return this;
    }

    public MailBuilder cc(String address) {
        this.ccAddresses.add(address);
        return this;
    }

    public MailBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public MailBuilder body(String body) {
        this.body = body;
        return this;
    }

    private void doSend() {
        StringBuilder sb = new StringBuilder();
        sb.append("TO:").append(toAddress).append("\r\n");
        if (!ccAddresses.isEmpty()) {
            sb.append("CC:").append(String.join(",", ccAddresses)).append("\r\n");
        }
        sb.append("FROM:").append(fromAddress).append("\r\n");
        sb.append("SUBJECT:").append(subject).append("\r\n");
        sb.append("BODY:").append(body).append("\r\n");
        System.out.println(sb.toString());
    }

    public static void send(final Consumer<MailBuilder> consumer) {
        final MailBuilder mailer = new MailBuilder();
        consumer.accept(mailer);
        mailer.doSend();
    }

    public MailBuilder doIf(boolean condition, final Consumer<MailBuilder> consumer) {
        if (condition) {
            consumer.accept(this);
        }
        return this;
    }

    public <T> MailBuilder foreach(Iterable<T> iterable, final BiConsumer<MailBuilder, T> consumer) {
        iterable.forEach(t -> consumer.accept(this, t));
        return this;
    }

}
