package bll;

import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import model.Order;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {

    private OrderDAO orderDAO;

    public OrderBLL() {

        orderDAO = new OrderDAO();
    }

    public Order findOrderById(int id) {
        Order st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }



    public Order insert(Order order) throws IllegalAccessException {
        Order o = orderDAO.insert(order);
        return o;
    }

    public List<Order> findAll(){
        List<Order> c = new ArrayList<>();
        c = orderDAO.findAll();
        return c;
    }

}
