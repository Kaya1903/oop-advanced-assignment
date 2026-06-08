package PGO9;

public class Main3 {
    public static void main(String[] args) {
        Order order = new Order("ORD-100", "Anna Kowalska");

        order.addItem(new Order.OrderItem("Keyboard", 249.99, 1));
        order.addItem(new Order.OrderItem("Mouse", 99.99, 2));

        OrderSummary summary = new OrderSummary(
                order.getOrderNumber(),
                order.getCustomerName(),
                order.total()
        );

        System.out.println(summary);
    }
}