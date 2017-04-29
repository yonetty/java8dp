package example.java8dp.behavior.cor.advanced;

@FunctionalInterface
public interface Delegatable<Q, R> {

    R call(Q q);

    default Delegatable<Q, R> delegate(Delegatable<Q, R> next) {
        return q -> {
            final R result = call(q);
            return result != null ? result : next.call(q);
        };
    }
}
