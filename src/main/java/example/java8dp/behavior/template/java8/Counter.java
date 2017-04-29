package example.java8dp.behavior.template.java8;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Counter {

    private Predicate<Integer> condition1;
    private Predicate<Integer> condition2;
    private Function<Integer, String> converter1;
    private Function<Integer, String> converter2;
    private Function<Integer, String> converterBoth;
    private int from;
    private int to;

    private Counter() {
    }

    public Counter first(Predicate<Integer> condition1, Function<Integer, String> converter1) {
        this.condition1 = condition1;
        this.converter1 = converter1;
        return this;
    }

    public Counter second(Predicate<Integer> condition2, Function<Integer, String> converter2) {
        this.condition2 = condition2;
        this.converter2 = converter2;
        return this;
    }

    public Counter onBoth(Function<Integer, String> converterBoth) {
        this.converterBoth = converterBoth;
        return this;
    }

    public Counter fromTo(int from, int to) {
        this.from = from;
        this.to = to;
        return this;
    }

    public static void count(final Consumer<Counter> consumer) {
        Counter counter = new Counter();
        consumer.accept(counter);
        counter.doCount();
    }

    private void doCount() {
        String result = IntStream.rangeClosed(from, to)
                .mapToObj(this::map)
                .collect(Collectors.joining(","));
        System.out.println(result);
    }

    private String map(int num) {
        if (condition1.test(num)) {
            return condition2.test(num) ? converterBoth.apply(num) : converter1.apply(num);
        } else {
            return condition2.test(num) ? converter2.apply(num) : String.valueOf(num);
        }
    }
}
