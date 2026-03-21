public class Book {
    private int id;
    private String title;
    private String author;
    private double price;
    private int qty;
    private String img;

    public Book(int id, String title, String author, double price, int qty, String img) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.qty = qty;
        this.img = img;
    }

    //for JSTL
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public int getQty() { return qty; }
    public String getImg() { return img; }
}