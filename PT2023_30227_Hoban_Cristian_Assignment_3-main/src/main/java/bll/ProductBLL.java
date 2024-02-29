package bll;

import dao.ProductDAO;
import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    private ProductDAO productDAO;
    public ProductBLL(){
        productDAO = new ProductDAO();
    }
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    public boolean checkQuantity(int id, int quantity){
        return ProductDAO.checkQuantity(id, quantity);
    }

    public void updateQuantity(int id, int quantity) throws IllegalAccessException {
        ProductDAO.updateQuantity(id, quantity);
    }

    public List<Product> findAll(){
        List<Product> c = new ArrayList<>();
        c = productDAO.findAll();
        return c;
    }

    public Product insert(Product product) throws IllegalAccessException {
        Product p = productDAO.insert(product);
        return p;
    }

    public Product delete(Product product, int i) throws IllegalAccessException {
        Product p = productDAO.delete(i, product);
        return p;
    }
    public Product update(Product product) throws IllegalAccessException {
        Product p = productDAO.update(product);
        return p;
    }
}
