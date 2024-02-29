package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class OrderView extends JFrame {


    private JLabel oId = new JLabel("Orders's id:");
    private JLabel cId = new JLabel("Client's id:");
    private JLabel pId = new JLabel("Product's id:");
    private JLabel pQuantity = new JLabel("Quantity");

    private JTextField order = new JTextField(30);
    private JTextField client = new JTextField(30);
    private JTextField product = new JTextField(30);
    private JTextField quantity = new JTextField(30);

    private JButton cApply = new JButton("Make an order");
    private JButton back = new JButton("Back");

    private JButton showOrders = new JButton("Show orders");

    public OrderView() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));


        JPanel leftContent = new JPanel();
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
        leftContent.add(oId);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(cId);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(pId);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(pQuantity);



        JPanel rightContent = new JPanel();
        rightContent.setLayout(new BoxLayout(rightContent, BoxLayout.Y_AXIS));
        rightContent.add(order);
        rightContent.add(client);
        rightContent.add(product);
        rightContent.add(quantity);

        JPanel bottomContent = new JPanel();
        bottomContent.setLayout(new FlowLayout());
        bottomContent.add(leftContent);
        bottomContent.add(rightContent);


        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());

        buttons.add(back);
        buttons.add(cApply);
        buttons.add(showOrders);
        content.add(bottomContent);
        content.add(buttons);
        content.add(Box.createRigidArea(new Dimension(0, 5)));


        this.setTitle("Order");
        this.setContentPane(content);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void addBackListener(ActionListener actionListener) {
        this.back.addActionListener(actionListener);

    }
    public void addShowOrdersListener(ActionListener actionListener) {
        this.showOrders.addActionListener(actionListener);
    }
    public void addApplyListener(ActionListener actionListener) {
        this.cApply.addActionListener(actionListener);
    }

    public String getOrder() {
        return this.order.getText();
    }
    public String getCClient() {
        return this.client.getText();
    }
    public String getProduct() {
        return this.product.getText();
    }
    public String getQuantity() {
        return this.quantity.getText();
    }
}
