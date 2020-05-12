package edu.iis.mto.time;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OrderTest {
    private Order order;
    private MockTimeSource timeSource;

    @Before
    public void setUp(){
        timeSource = new MockTimeSource();
        order = new Order(timeSource);
        order.addItem(new OrderItem());
    }

    @Test (expected = OrderExpiredException.class)
    public void checkIfOrderIsExpired(){
        order.submit();
        timeSource.addHours(25);
        order.confirm();
    }

    @Test
    public void checkIfOrderIsNotExpired(){
        order.submit();
        timeSource.addHours(5);
        order.confirm();
        Assert.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }

    @Test
    public void checkIfOrderIsAfter24Hours(){
        order.submit();
        timeSource.addHours(24);
        order.confirm();
        Assert.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }

    @Test
    public void checkIfOrderIsAfterZeroHours(){
        order.submit();
        timeSource.addHours(0);
        order.confirm();
        Assert.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }
}
