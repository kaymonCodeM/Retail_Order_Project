package retail.orders.MakeMyOrder.Entity;

import java.util.Date;

public class Order {
    private long orderId;
    private Date orderDate;
    private Date deliveryDate;
    private double deliveryCharge;
    private boolean shipped;
    private boolean complete;
}
