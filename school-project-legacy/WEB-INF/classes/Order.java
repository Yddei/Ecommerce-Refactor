//to create order objects
public class Order {
    private int id;
    private double totalPrice;
    private String orderDate;
    private String status;
    private String address;

    public Order(int id, double totalPrice, String orderDate, String status, String address) {
        this.id = id;
        this.totalPrice = totalPrice;
        // remove .0 in mysql
        this.orderDate = (orderDate != null && orderDate.endsWith(".0")) ? orderDate.substring(0, orderDate.length() - 2) : orderDate;
        this.status = status;
        this.address = address;
    }

    //for jstl
    public int getId() { return id; }
    public double getTotalPrice() { return totalPrice; }
    public String getOrderDate() { return orderDate; }
    public String getStatus() { return status; }
    public String getAddress() { return address; }
}