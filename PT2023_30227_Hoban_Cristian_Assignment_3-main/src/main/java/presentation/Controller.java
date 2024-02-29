package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Order;
import model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Controller {
    private View view;
    ClientView clientView = new ClientView();
    ProductView productView = new ProductView();

    OrderView orderView = new OrderView();
    public Controller(View view){
        this.view = view;
        this.view.addClientListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.setVisible(true);
                view.dispose();
            }
        });

        this.view.addProductListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                productView.setVisible(true);
                view.dispose();
            }
        });

        this.view.addOrderListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                orderView.setVisible(true);
                view.dispose();
            }
        });

        clientView.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientView.dispose();
                view.setVisible(true);
            }
        });

        clientView.addShowClientsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Client> list = new ArrayList<>();
                ClientBLL cBll= new ClientBLL();
                TableView t = new TableView();
                list = cBll.findAll();
                JTable table = t.createTable(list);
                t.p.setViewportView(table);
                t.setVisible(true);

            }
        });

        productView.addShowProductsListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> list = new ArrayList<>();
                ProductBLL pBll= new ProductBLL();
                TableView t = new TableView();
                list = pBll.findAll();
                JTable table = t.createTable(list);
                t.p.setViewportView(table);
                t.setVisible(true);

            }
        });

        orderView.addShowOrdersListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Order> list = new ArrayList<>();
                OrderBLL oBll= new OrderBLL();
                TableView t = new TableView();
                list = oBll.findAll();
                if(list == null) {
                    view.showError("The table is empty!");
                }
                else {
                    JTable table = t.createTable(list);
                    t.p.setViewportView(table);
                    t.setVisible(true);
                }

            }
        });

        clientView.addCApplyListener(new ActionListener() {

            ClientDAO clientDAO = new ClientDAO();
            ClientBLL clientBLL = new ClientBLL();
            public void actionPerformed(ActionEvent e) {
                if((!clientView.getCAdd() && !clientView.getCEdit() && !clientView.getCDelete())   ||   (clientView.getCAdd() && clientView.getCEdit())   ||   (clientView.getCAdd() && clientView.getCDelete())  ||   (clientView.getCDelete() && clientView.getCEdit())){
                    view.showError("You have to select exactly one option!");
                    return;
                }
                if(clientView.getCAdd()){
                   // int id = parseInt(clientView.getcId());
                    String name = clientView.getcName();
                    String address = clientView.getcAddress();
                    String email = clientView.getcEmail();
                    String a = clientView.getcAge();
                    if(name.equals("") || address.equals("") || email.equals("")||a.equals("")){
                        view.showError("Please complete every field!");
                        return;
                    }

                    int age = parseInt(a);
                    Client c = new Client(name, address, email, age);
                    try {
                        Client cl = clientBLL.insert(c);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    view.showError("New client INSERTED!");
                }
                else if(clientView.getCDelete()){
                    int id = parseInt(clientView.getcId());
                    Client c = new Client();
                    try {
                        Client cl = clientBLL.delete(c, id);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    view.showError("Client with id = " + id + " deleted!");
                }
                else if(clientView.getCEdit()){
                    int id = parseInt(clientView.getcId());
                    String name = clientView.getcName();
                    String address = clientView.getcAddress();
                    String email = clientView.getcEmail();
                    String a = clientView.getcAge();
                    if(name.equals("") || address.equals("") || email.equals("")||a.equals("")){
                        view.showError("Please complete every field!");
                        return;
                    }

                    int age = parseInt(a);
                    Client c = new Client(id, name, address, email, age);

                    try {
                        Client cl = clientBLL.update(c);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    view.showError("Client UPDATED!");
                }

            }
        });


        orderView.addApplyListener(new ActionListener() {
            ProductDAO productDAO = new ProductDAO();

            ProductBLL productBLL = new ProductBLL();
            ClientBLL clientBLL = new ClientBLL();
            OrderBLL orderBLL = new OrderBLL();
            @Override
            public void actionPerformed(ActionEvent e) {
                String p = orderView.getProduct();
                String q = orderView.getQuantity();
                String c = orderView.getCClient();
                String o = orderView.getOrder();

                if(p.equals("") || q.equals("") || c.equals("") || o.equals("")){
                    view.showError("Please complete every field!");
                    return;
                }

                int oId = Integer.parseInt(o);
                int quantity = Integer.parseInt(q);
                int cId =  Integer.parseInt(c);
                int pId = Integer.parseInt(p);

                try {
                    Product product = productBLL.findProductById(pId);
                }catch(IndexOutOfBoundsException ex){view.showError("There's no product with id = " + pId+"!");
                    return;
                }

               // System.out.println(cId);
                try{
                Client client = clientBLL.findClientById(cId);}
                catch(IndexOutOfBoundsException ex){view.showError("There's no client with id = " + cId+"!");
                    return;
                }

                //System.out.println(client.toString());

                if(!productBLL.checkQuantity(pId, quantity)){
                    view.showError("Not enough products of this kind available!");
                    return;
                }
                Order order = new Order(cId, pId, quantity);

                try {
                    Order ord = orderBLL.insert(order);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    productBLL.updateQuantity(pId, quantity);
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                view.showError("A new Order has been made!");
            }


        });
        productView.addPApplyListener(new ActionListener() {

            ProductDAO productDAO = new ProductDAO();
            ProductBLL productBLL = new ProductBLL();
            public void actionPerformed(ActionEvent e) {
                if((!productView.getPAdd() && !productView.getPEdit() && !productView.getPDelete())   ||   (productView.getPAdd() && productView.getPEdit())   ||   (productView.getPAdd() && productView.getPDelete())  ||   (productView.getPDelete() && productView.getPEdit())){
                    view.showError("You have to select exactly one option!");
                    return;
                }
                if(productView.getPAdd()){
                    //int id = parseInt(productView.getPId());
                    String type = productView.getPType();
                    String p = productView.getPPrice();
                    String s = productView.getPStock();
                    if(type.equals("") || p.equals("") || s.equals("")){
                        view.showError("Please complete every field!");
                        return;
                    }

                    double price = Double.parseDouble(p);
                    int stock = Integer.parseInt(s);
                    Product product = new Product(type, price, stock);
                    try {
                        Product cl = productBLL.insert(product);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    view.showError("New product INSERTED!");
                }
                else if(productView.getPDelete()){
                    int id = parseInt(productView.getPId());
                    Product p = new Product();
                    try {
                        Product pr = productBLL.delete(p, id);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    view.showError("Product with id = " + id + " deleted!");
                }
                else if(productView.getPEdit()){
                    int id = parseInt(productView.getPId());
                    String type = productView.getPType();
                    String p = productView.getPPrice();
                    String s = productView.getPStock();
                    if(type.equals("") || p.equals("") || s.equals("")){
                        view.showError("Please complete every field!");
                        return;
                    }

                    double price = Double.parseDouble(p);
                    int stock = Integer.parseInt(s);
                    Product product = new Product(id, type, price, stock);

                    try {
                        Product pr = productBLL.update(product);
                    } catch (IllegalAccessException ex) {
                        throw new RuntimeException(ex);
                    }
                    view.showError("Product UPDATED!");
                }

            }
        });

        productView.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productView.dispose();
                view.setVisible(true);
            }
        });

        orderView.addBackListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderView.dispose();
                view.setVisible(true);
            }
        });


    }
}
