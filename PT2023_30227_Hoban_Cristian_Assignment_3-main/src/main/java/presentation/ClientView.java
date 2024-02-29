package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientView extends JFrame {

    private JLabel clientId = new JLabel("Client's id:");
    private JLabel clientName = new JLabel("Client's name:");
    private JLabel clientAge = new JLabel("Client's age:");
    private JLabel clientEmail = new JLabel("Client's email:");
    private JLabel clientAddress = new JLabel("Client's address:");

    private JTextField cId = new JTextField(30);
    private JTextField cName = new JTextField(30);
    private JTextField cAge = new JTextField(30);
    private JTextField cEmail = new JTextField(30);
    private JTextField cAddress = new JTextField(30);

    private JCheckBox cDelete = new JCheckBox();
    private JCheckBox cAdd = new JCheckBox();
    private JCheckBox cEdit = new JCheckBox();

    private JLabel dText = new JLabel("Delete");
    private JLabel aText = new JLabel("Add");
    private JLabel eText = new JLabel("Edit");

    private JButton cApply = new JButton("Apply");
    private JButton back = new JButton("Back");

    private JButton showClients = new JButton("Show all clients");

    public ClientView() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel checkA = new JPanel();
        checkA.setLayout(new FlowLayout());
        checkA.add(cAdd);
        checkA.add(aText);

        JPanel checkD = new JPanel();
        checkD.setLayout(new FlowLayout());
        checkD.add(cDelete);
        checkD.add(dText);

        JPanel checkE = new JPanel();
        checkE.setLayout(new FlowLayout());
        checkE.add(cEdit);
        checkE.add(eText);

        JPanel leftContent = new JPanel();
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
        leftContent.add(clientId);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(clientName);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(clientAge);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(clientEmail);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(clientAddress);

        JPanel rightContent = new JPanel();
        rightContent.setLayout(new BoxLayout(rightContent, BoxLayout.Y_AXIS));
        rightContent.add(cId);
        rightContent.add(cName);
        rightContent.add(cAge);
        rightContent.add(cEmail);
        rightContent.add(cAddress);

        JPanel bottomContent = new JPanel();
        bottomContent.setLayout(new FlowLayout());
        bottomContent.add(leftContent);
        bottomContent.add(rightContent);

        content.add(checkA);
        content.add(checkE);
        content.add(checkD);
        content.add(bottomContent);

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(back);
        buttons.add(cApply);
        buttons.add(showClients);

        content.add(buttons);
        content.add(Box.createRigidArea(new Dimension(0, 5)));


        this.setTitle("Client");
        this.setContentPane(content);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void addBackListener(ActionListener actionListener) {
        this.back.addActionListener(actionListener);
    }

    public void addShowClientsListener(ActionListener actionListener) {
        this.showClients.addActionListener(actionListener);
    }

    public void addCApplyListener(ActionListener actionListener) {
        this.cApply.addActionListener(actionListener);
    }

    public boolean getCAdd() {
        return cAdd.isSelected();
    }
    public boolean getCDelete(){
        return cDelete.isSelected();
    }
    public boolean getCEdit(){
        return cEdit.isSelected();
    }


    public String getcId() {
        return this.cId.getText();
    }

    public String getcName() {
        return this.cName.getText();
    }

    public String getcAge() {
        return this.cAge.getText();
    }

    public String getcEmail() {
        return this.cEmail.getText();
    }

    public String getcAddress() {
        return this.cAddress.getText();
    }
}
