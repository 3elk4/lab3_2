import edu.iis.mto.time.Order;
import edu.iis.mto.time.OrderItem;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
    private Order order;

    @Before
    public void setUp(){
        order = new Order();
        order.addItem(new OrderItem());
    }

    @Test
    public void checkIfOrderIsExpired(){

    }

    @Test
    public void checkIfOrderIsNotExpired(){

    }

    @Test
    public void checkIfOrderIsAfter24Hours(){

    }

    @Test
    public void checkIfOrderIsAfter0Hours(){

    }
}
