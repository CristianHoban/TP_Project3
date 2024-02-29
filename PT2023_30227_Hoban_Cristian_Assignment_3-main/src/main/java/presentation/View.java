package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends  JFrame{
    /*private JLabel client = new JLabel("Client's name:");
    private JLabel clientAge = new JLabel("Client's age:");
    private JLabel clientEmail = new JLabel("Client's email:");
    private JLabel cliendAddress = new JLabel("Client's address:");
    private JLabel product = new JLabel("Product's type:");
    private JLabel price = new JLabel("Price(per piece):");
    private JLabel stock = new JLabel("Stock:");

    private JTextField cName = new JTextField(30);
    private JTextField cAge = new JTextField(30);
    private JTextField cEmail = new JTextField(30);
    private JTextField cAddress = new JTextField(30);
    private JTextField pType = new JTextField(30);
    private JTextField pPrice = new JTextField(30);
    private JTextField pStock = new JTextField(30);*/

/*    private Checkbox c = new Checkbox();
    private JLabel addC = new JLabel("Add a client");
    private Checkbox p = new Checkbox();
    private JLabel addP = new JLabel("Add a product");

    private JButton cButton = new JButton("ADD CLIENT");
    private JButton pButton = new JButton("ADD PRODUCT");*/

    private JButton client = new JButton("CLIENT");
    private JButton product = new JButton("PRODUCT");
    private JButton order = new JButton("ORDER");

    public View(){
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new GridLayout(1,3));
        mainContent.add(client);
        mainContent.add(product);
        mainContent.add(order);

        this.setTitle("Frame");
        this.setContentPane(mainContent);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void addClientListener(ActionListener actionListener){
        this.client.addActionListener(actionListener);
    }

    public void addOrderListener(ActionListener actionListener){
        this.order.addActionListener(actionListener);
    }

    public void addProductListener(ActionListener actionListener){
        this.product.addActionListener(actionListener);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }





}
