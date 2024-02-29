package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

import static java.lang.Integer.parseInt;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

	}

	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM schooldb.");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}

	public String createFindAllQuery() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM schooldb.").append(type.getSimpleName());

		return sb.toString();
	}

	public String createInsertQuery(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO schooldb.");
		sb.append(t.getClass().getSimpleName());
		sb.append(" VALUES(");
		return sb.toString();
	}


	public List<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAllQuery();

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return null;
	}

	/**
	 * Metoda update creaza un query de INSERT care prin reflexie se poate folosi pentru orice tip de obiect
	 * @param t
	 * @return
	 * @throws IllegalAccessException
	 */
	public T insert(T t) throws IllegalAccessException {

		Connection connection = null;
		PreparedStatement statement = null;
		String query = "";
		query += "INSERT INTO schooldb.";
		query += t.getClass().getSimpleName();
		query += " VALUES(";
		int i = 0;
		Field[] f2 = t.getClass().getDeclaredFields();
		for (Field field : f2) {
			field.setAccessible(true);
			Object a = new Object();
			a = field.get(t);
			try {
				if (i == 0){
					i++;
					if(a.getClass().getSimpleName().equals("String")){
						query += "'"+String.valueOf(field.get(t))+"'";
					}
					else{
						query += String.valueOf(field.get(t));
					}
				}
				else {
					query += ", ";
					if(a.getClass().getSimpleName().equals("String")){
						query += "'"+String.valueOf(field.get(t))+"'";
					}
					else{
						query += String.valueOf(field.get(t));
					}
				}

			} catch (IllegalAccessException ex) {
				throw new RuntimeException(ex);
			}
		}


		query += ")";

			try {
				connection = ConnectionFactory.getConnection();
				statement = connection.prepareStatement(query);
				System.out.println(query);
				statement.execute(query);

			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
			} finally {
				ConnectionFactory.close(statement);
				ConnectionFactory.close(connection);
			}

			return null;
	}

	/**
	 * Metoda update creaza un query de UPDATE care prin reflexie se poate folosi pentru orice tip de obiect
	 * @param t
	 * @return
	 * @throws IllegalAccessException
	 */
	public T update(T t) throws IllegalAccessException {

		Connection connection = null;
		PreparedStatement statement = null;
		String query = "";
		query += "UPDATE schooldb.";
		query += t.getClass().getSimpleName();
		query += " SET ";
		int id = 0;
		int i = 0;
		Field[] f2 = t.getClass().getDeclaredFields();
		for (Field field : f2) {
			field.setAccessible(true);
			Object a = new Object();
			a = field.get(t);
			if(field.getName().equals("id"))
				id = parseInt(String.valueOf( field.get(t)));
			else {
				try {
					if (i == 0) {
						i++;
						if (a.getClass().getSimpleName().equals("String")) {
							query += field.getName() +  " = '" + String.valueOf(field.get(t)) + "'";
						} else {
							query += field.getName() +  " = " + String.valueOf(field.get(t));
						}
					} else {
						query += ", ";
						if (a.getClass().getSimpleName().equals("String")) {
							query += field.getName() +  " = '" + String.valueOf(field.get(t)) + "'";
						} else {
							query += field.getName() +  " = " + String.valueOf(field.get(t));
						}
					}

				} catch (IllegalAccessException ex) {
					throw new RuntimeException(ex);
				}
			}
		}


		query += " WHERE id = " + id;

		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			System.out.println(query);
			statement.execute(query);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return null;
	}

	public T delete(int i, T t) throws IllegalAccessException {

		Connection connection = null;
		PreparedStatement statement = null;
		String query = "";
		query += "DELETE FROM schooldb.";
		query += t.getClass().getSimpleName();
		query += " WHERE id =" + i;
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			System.out.println(query);
			statement.execute(query);

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return null;
	}


	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}

	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();
		Constructor[] ctors = type.getDeclaredConstructors();
		Constructor ctor = null;
		for (int i = 0; i < ctors.length; i++) {
			ctor = ctors[i];
			if (ctor.getGenericParameterTypes().length == 0)
				break;
		}
		try {
			while (resultSet.next()) {
				ctor.setAccessible(true);
				T instance = (T) ctor.newInstance();
				for (Field field : type.getDeclaredFields()) {
					String fieldName = field.getName();
					Object value = resultSet.getObject(fieldName);
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}

	public String[] header(){
		String[] h = null;
		int i = 0;
		for (Field e : type.getDeclaredFields()) {
			h[i] = String.valueOf(e);
		}
		return h;
	}

	/*public List<Object> table(List<T> t){
		List<Object> d = new ArrayList<>();
		int i = 1;
		d.add(header());
		for(T e: t){
			for(int j = 0;j<noOfFields();j++){
				d.add(e);
			}
		}
		return d;
	}*/



	public int noOfFields(){
		int i = 0;
		for (Field e : type.getDeclaredFields()) {
			i++;
		}
		return i;
	}

}
