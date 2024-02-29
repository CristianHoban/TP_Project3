package model;

public record Bill(int id, String productType, String quantity, String clientName ) {
    public String toString(){
        return "Client : " + clientName + "\nProduct type : " + productType + "\nQuantity: " + quantity;
    }
}
