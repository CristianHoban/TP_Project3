package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProductView extends JFrame {
    private JLabel id = new JLabel("Product's id:");
    private JLabel product = new JLabel("Product's type:");
    private JLabel price = new JLabel("Price(per piece):");
    private JLabel stock = new JLabel("Quantity:");


    private JTextField pId = new JTextField(30);
    private JTextField pType = new JTextField(30);
    private JTextField pPrice = new JTextField(30);
    private JTextField pStock = new JTextField(30);

    private JCheckBox pDelete = new JCheckBox();
    private JCheckBox pAdd = new JCheckBox();
    private JCheckBox pEdit = new JCheckBox();

    private JLabel dText = new JLabel("Delete");
    private JLabel aText = new JLabel("Add");
    private JLabel eText = new JLabel("Edit");

    private JButton cApply = new JButton("Apply");
    private JButton back = new JButton("Back");

    private JButton showProducts = new JButton("Show all products");

    public ProductView(){
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel checkA = new JPanel();
        checkA.setLayout(new FlowLayout());
        checkA.add(pAdd);
        checkA.add(aText);

        JPanel checkD = new JPanel();
        checkD.setLayout(new FlowLayout());
        checkD.add(pDelete);
        checkD.add(dText);

        JPanel checkE = new JPanel();
        checkE.setLayout(new FlowLayout());
        checkE.add(pEdit);
        checkE.add(eText);

        JPanel leftContent = new JPanel();
        leftContent.setLayout(new BoxLayout(leftContent, BoxLayout.Y_AXIS));
        leftContent.add(id);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(product);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(price);
        leftContent.add(Box.createRigidArea(new Dimension(0, 5)));
        leftContent.add(stock);



        JPanel rightContent = new JPanel();
        rightContent.setLayout(new BoxLayout(rightContent, BoxLayout.Y_AXIS));
        rightContent.add(pId);
        rightContent.add(pType);
        rightContent.add(pPrice);
        rightContent.add(pStock);

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
        buttons.add(showProducts);
        content.add(buttons);
        content.add(Box.createRigidArea(new Dimension(0, 5)));


        this.setTitle("Product");
        this.setContentPane(content);
        this.pack();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void addBackListener(ActionListener actionListener){
        this.back.addActionListener(actionListener);
    }

    public void addPApplyListener(ActionListener actionListener){
        this.cApply.addActionListener(actionListener);
    }
    public void addShowProductsListener(ActionListener actionListener){
        this.showProducts.addActionListener(actionListener);
    }

    public boolean getPAdd() {
        return pAdd.isSelected();
    }
    public boolean getPDelete(){
        return pDelete.isSelected();
    }
    public boolean getPEdit(){
        return pEdit.isSelected();
    }

    public String getPType() {
        return this.pType.getText();
    }

    public String getPId() {
        return this.pId.getText();
    }
    public String getPPrice() {
        return this.pPrice.getText();
    }
    public String getPStock() {
        return this.pStock.getText();
    }
}


