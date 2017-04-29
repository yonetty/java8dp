package example.java8dp.behavior.cor.old;

import static org.junit.Assert.*;

import org.junit.Test;

import example.java8dp.behavior.cor.Approval;

public class ChainOfResponsibilityTest {

    @Test
    public void test() {
        JigyoBucho jigyoBucho = new JigyoBucho("山田", null);
        Bucho bucho = new Bucho("田中", jigyoBucho);
        Kacho kacho = new Kacho("鈴木", bucho);
        Approval approval = kacho.approve(10000);
        assertEquals("鈴木", approval.getApprover());
        approval = kacho.approve(100000);
        assertEquals("田中", approval.getApprover());
        approval = kacho.approve(1000000);
        assertEquals("山田", approval.getApprover());
        approval = kacho.approve(1000001);
        assertNull(approval);
    }
}
