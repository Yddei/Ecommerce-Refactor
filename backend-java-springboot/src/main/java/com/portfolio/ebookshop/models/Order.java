package com.portfolio.ebookshop.models;

import jakarta.persistence.*;

/*
CREATE TABLE IF NOT EXISTS orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    total_price DECIMAL(10,2),
    order_date DATETIME,
    status VARCHAR(20)
);
*/

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String username;
    @Column(nullable = false, length = 255)
    private String address;
    @Column(nullable = false)
    private float total_price;
    @Column(nullable = false)
    private String order_date;
    @Column(nullable = false, length = 20)
    private String status;

    public Order(){}

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getAddress(){return address;}
    public void setAddress(String address){this.address = address;}

    public float getTotalPrice(){return total_price;}
    public void setTotalPrice(float total_price){this.total_price = total_price;}

    public String getOrderDate(){return order_date;}
    public void setOrderDate(String order_date){this.order_date = order_date;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
}
