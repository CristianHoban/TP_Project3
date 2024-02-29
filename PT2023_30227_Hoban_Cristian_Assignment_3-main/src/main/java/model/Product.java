package model;

public class Product {
    private int id;
    private String type;
    private double price;
    private int quantity;

    public Product(){

    }
    public Product(int id, String type, double price, int quantity){
        this.id = id;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(String type, double price, int quantity){
        super();
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String toString() {
        return "Client [id=" + id + ", type=" + type + ", price=" + price + ", quantity=" + quantity + "]";
    }
}
