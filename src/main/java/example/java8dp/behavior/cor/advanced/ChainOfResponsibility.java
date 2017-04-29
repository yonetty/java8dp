package example.java8dp.behavior.cor.advanced;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ChainOfResponsibility<Q, R> {
    private List<Delegatable<Q, R>> chain;

    public ChainOfResponsibility() {
        super();
        chain = new ArrayList<>();
    }

    public ChainOfResponsibility<Q, R> add(Function<Q, R> func) {
        chain.add(q -> func.apply(q));
        return this;
    }

    public ChainOfResponsibility<Q, R> tail(Consumer<Q> tail) {
        chain.add(q -> {
            tail.accept(q);
            return null;
        });
        return this;
    }

    public Function<Q, R> build() {
        Optional<Delegatable<Q, R>> head = chain.stream()
                .reduce((delegatable, next) -> delegatable.delegate(next));
        return head.isPresent() ? q -> head.get().call(q) : null;
    }

}
