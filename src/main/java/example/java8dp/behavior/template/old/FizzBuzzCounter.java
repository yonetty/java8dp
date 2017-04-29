package example.java8dp.behavior.template.old;

public class FizzBuzzCounter extends AbstractCounter {

    @Override
    boolean condition1(int num) {
        return num % 3 == 0;
    }

    @Override
    boolean condition2(int num) {
        return num % 5 == 0;
    }

    @Override
    String onCondition1(int num) {
        return "Fizz";
    }

    @Override
    String onCondition2(int num) {
        return "Buzz";
    }

    @Override
    String onBoth(int num) {
        return "FizzBuzz";
    }
}
