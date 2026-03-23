package com.portfolio.ebookshop.models;

import jakarta.persistence.*;

/*
books table:
id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50),
    price FLOAT,
    qty INT,
    img VARCHAR(255)
*/

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 50)
    private String title;
    private String author;
    private double price;
    private int qty;
    @Column(name="img", length = 255)
    private String img;

    public Book(){}

    //Getters and Setters
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}

    public Double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}

    public int getQty() {return qty;}
    public void setQty(int qty) {this.qty = qty;}

    public String getImg() {return img;}
    public void setImg(String img) {this.img = img;}

    /* for test
    public Book(int id, String title, String author, double price, int qty, String img) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.qty = qty;
        this.img = img;
    }
    */
}