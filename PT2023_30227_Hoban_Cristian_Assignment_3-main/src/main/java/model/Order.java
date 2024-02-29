package model;

public class Order {
    private int id;
    private int idClient;
    private int idProduct;
    private int quantity;

    public Order(int id, int idClient, int idProduct, int quantity){
        this.id  = id;
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity  = quantity;
    }
    public Order(int idClient, int idProduct, int quantity){
        super();
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.quantity  = quantity;
    }

    public Order(){

    }
    public int getId() {
        return id;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
