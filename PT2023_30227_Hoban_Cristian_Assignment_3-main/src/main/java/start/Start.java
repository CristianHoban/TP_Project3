package start;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.AbstractDAO;
import dao.ClientDAO;
import dao.OrderDAO;
import model.Client;
import bll.ClientBLL;
import presentation.ClientView;
import presentation.Controller;
import presentation.TableView;
import presentation.View;


public class Start {
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {

		View v = new View();
		Controller c = new Controller(v);
		v.setVisible(true);

		//OrderDAO o = new OrderDAO();
		//System.out.println(o.createFindAllQuery());


		ClientDAO a = new ClientDAO();

		ClientBLL clientBll = new ClientBLL();

		Client client1 = null;

		try {
			client1 = clientBll.findClientById(1);

		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}


		// obtain field-value pairs for object through reflection
		ReflectionExample.retrieveProperties(client1);

		System.out.println(a.findAll());


	}

}
