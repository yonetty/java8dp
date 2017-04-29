package example.java8dp.behavior.cor.java8;

import java.util.function.Predicate;
import java.util.stream.Stream;

import example.java8dp.behavior.cor.Approval;

@FunctionalInterface
public interface Approvable {

    Approval approve(int amount);

    default Approvable delegate(Approvable next) {
        return (amount) -> {
            final Approval result = approve(amount);
            return result != null ? result : next.approve(amount);
        };
    }

    static Approvable chain(Approvable... approvables) {
        return Stream.concat(Stream.of(approvables), Stream.of(tail()))
                .reduce((approvable, next) -> approvable.delegate(next))
                .orElse(null);
    }

    static Approvable approver(String title, String name, Predicate<Integer> predicate) {
        return amount -> {
            if (predicate.test(amount)) {
                System.out.println(String.format("%s決裁(%s) %,3d円", title, name, amount));
                return new Approval(name, amount);
            } else {
                return null;
            }
        };
    }

    static Approvable tail() {
        return amount -> {
            System.out.println(String.format("決裁できません。 %,3d円", amount));
            return null;
        };
    }

}
