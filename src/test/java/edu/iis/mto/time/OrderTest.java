package edu.iis.mto.time;

import org.joda.time.Instant;
import org.joda.time.ReadableDuration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;

public class OrderTest {
    private Order order;
    private final int ONE_HOUR = 60*60*1000;

    @Before
    public void setUp(){
        order = new Order();
        order.addItem(new OrderItem());
    }

    private Instant getInstantAfterHours(int numberOfHours){
        return Instant.now().withDurationAdded(ONE_HOUR, numberOfHours);
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
        Assert.assertNotEquals(Order.State.CANCELLED, order.getOrderState());
    }

    @Test
    public void checkIfOrderIsAfter24Hours(){
        order.setInstant(getInstantAfterHours(24));
        order.submit();
        order.confirm();
        Assert.assertNotEquals(Order.State.CANCELLED, order.getOrderState());
    }

    @Test
    public void checkIfOrderIsAfter0Hours(){
        order.setInstant(getInstantAfterHours(0));
        order.submit();
        order.confirm();
        Assert.assertNotEquals(Order.State.CANCELLED, order.getOrderState());
    }
}
