package edu.iis.mto.time;

import org.joda.time.Duration;
import org.joda.time.Instant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class OrderTest {
    private Order order;

    @Before
    public void setUp(){
        order = new Order();
        order.addItem(new OrderItem());
    }

    private Instant getInstantAfterHours(int numberOfHours){
        return Instant.now().plus(Duration.standardHours(numberOfHours));
    }

    @Test (expected = OrderExpiredException.class)
    public void checkIfOrderIsExpired(){
        order.setInstant(getInstantAfterHours(25));
        order.submit();
        order.confirm();
    }

    @Test
    public void checkIfOrderIsNotExpired(){
        order.setInstant(getInstantAfterHours(5));
        order.submit();
        order.confirm();
        Assert.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }

    @Test
    public void checkIfOrderIsAfter24Hours(){
        order.setInstant(getInstantAfterHours(24));
        order.submit();
        order.confirm();
        Assert.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }

    @Test
    public void checkIfOrderIsAfter0Hours(){
        order.setInstant(getInstantAfterHours(0));
        order.submit();
        order.confirm();
        Assert.assertEquals(Order.State.CONFIRMED, order.getOrderState());
    }
}
