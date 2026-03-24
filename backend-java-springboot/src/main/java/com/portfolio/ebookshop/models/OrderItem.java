package com.portfolio.ebookshop.models;

import jakarta.persistence.*;
/* 
CREATE TABLE IF NOT EXISTS order_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    book_id INT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE RESTRICT
);
*/

@Entity
@Table(name="order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int order_id;
    private int book_id;
    private int quantity;
    private float price;

    public OrderItem(){}

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    
    public int getOrderId(){return order_id;}
    public void setOrderId(int order_id){this.order_id = order_id;}
    
    public int getBookId(){return book_id;}
    public void setBookId(int book_id){this.book_id = book_id;}
    
    public int getQuantity(){return quantity;}
    public void setQuantityId(int quantity){this.quantity = quantity;}
    
    public float getPrice(){return price;}
    public void setPrice(int price){this.price = price;}
}
