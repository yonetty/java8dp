package example.java8dp.behavior.template.old;

public class NabeatsuCounter extends AbstractCounter {

    @Override
    boolean condition1(int num) {
        return num % 3 == 0;
    }

    @Override
    boolean condition2(int num) {
        return String.valueOf(num).contains("3");
    }

    @Override
    String onCondition1(int num) {
        return String.valueOf(num) + "!";
    }

    @Override
    String onCondition2(int num) {
        return String.valueOf(num) + "!";
    }

    @Override
    String onBoth(int num) {
        return String.valueOf(num) + "!!";
    }

}
