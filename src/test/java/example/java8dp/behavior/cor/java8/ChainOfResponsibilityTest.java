package example.java8dp.behavior.cor.java8;

import static org.junit.Assert.*;

import org.junit.Test;

import example.java8dp.behavior.cor.Approval;

public class ChainOfResponsibilityTest {

    @Test
    public void test() {
        Approvable kacho = Approvable.approver("課長", "鈴木", amount -> amount <= 10000);
        Approvable bucho = Approvable.approver("部長", "田中", amount -> amount <= 100000);
        Approvable jigyoBucho = Approvable.approver("事業部長", "山田", amount -> amount <= 1000000);

        Approvable cor = Approvable.chain(kacho, bucho, jigyoBucho);
        Approval approval = cor.approve(10000);
        assertEquals("鈴木", approval.getApprover());
        approval = cor.approve(100000);
        assertEquals("田中", approval.getApprover());
        approval = cor.approve(1000000);
        assertEquals("山田", approval.getApprover());
        approval = cor.approve(1000001);
        assertNull(approval);
    }
}
