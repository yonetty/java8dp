package example.java8dp.behavior.cor.advanced;

import static org.junit.Assert.*;

import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

import example.java8dp.behavior.cor.Approval;

public class ChainOfResponsibilityTest {

    @Test
    public void test() {
        Function<Integer, Approval> kacho = approver("課長", "鈴木", amount -> amount <= 10000);
        Function<Integer, Approval> bucho = approver("部長", "田中", amount -> amount <= 100000);
        Function<Integer, Approval> jigyoBucho = approver("事業部長", "山田", amount -> amount <= 1000000);

        Function<Integer, Approval> head = new ChainOfResponsibility<Integer, Approval>()
                .add(kacho)
                .add(bucho)
                .add(jigyoBucho)
                .tail(amount -> System.out.println(String.format("決裁できません。 %,3d円", amount)))
                .build();

        Approval approval = head.apply(10000);
        assertEquals("鈴木", approval.getApprover());
        approval = head.apply(100000);
        assertEquals("田中", approval.getApprover());
        approval = head.apply(1000000);
        assertEquals("山田", approval.getApprover());
        approval = head.apply(1000001);
        assertNull(approval);

    }

    private Function<Integer, Approval> approver(String title, String name, Predicate<Integer> predicate) {
        return amount -> {
            if (predicate.test(amount)) {
                System.out.println(String.format("%s決裁(%s) %,3d円", title, name, amount));
                return new Approval(name, amount);
            } else {
                return null;
            }
        };
    }

}
