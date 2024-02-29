package presentation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class TableView<T> extends JFrame {

    JScrollPane p = new JScrollPane();

    public TableView(){
        p.setBounds(360, 100, 380, 150);
        this.add(p);
        this.setBounds(500, 300, 600, 300);
    }


    public JTable createTable(List<Object> c){
        DefaultTableModel m = new DefaultTableModel();
        Field[] f = c.get(0).getClass().getDeclaredFields();
        for(Field e : f){
            m.addColumn(e.getName());
        }

        for(Object e : c){
            List<Object> l = new ArrayList<>();
            Field[] f2 = e.getClass().getDeclaredFields();
            for(Field field : f2){
                field.setAccessible(true);
                try {
                    l.add(field.get(e));
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
            }
            m.addRow(l.toArray());
        }
        JTable j = new JTable(m);
        return j;
    }

}
