package example.java8dp.behavior.template.old;

public abstract class AbstractCounter {

    public final void count(final int from, final int to) {
        for (int i = from; i <= to; i++) {
            final String expression;
            if (condition1(i)) {
                expression = condition2(i) ? onBoth(i) : onCondition1(i);
            } else {
                expression = condition2(i) ? onCondition2(i) : String.valueOf(i);
            }
            System.out.print(expression + ",");
        }
    }

    abstract boolean condition1(int num);

    abstract boolean condition2(int num);

    abstract String onCondition1(int num);

    abstract String onCondition2(int num);

    abstract String onBoth(int num);
}
