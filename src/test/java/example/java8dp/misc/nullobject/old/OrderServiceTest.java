package example.java8dp.misc.nullobject.old;

import static org.junit.Assert.*;

import org.junit.Test;

public class OrderServiceTest {

    @Test
    public void test() {
        OrderService service = new OrderService();
        assertEquals(new Integer(100), service.getPrice(100));
        assertEquals(new Integer(100), service.getPrice2(100));
        assertEquals(new Integer(100), service.getPrice3(100));
    }

}
