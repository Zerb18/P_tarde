package es.Studium.Practica2;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;

public class ConectorSql {
	public static ArrayList<String> array_ListasDeupdate = new ArrayList<String>();
	public static ArrayList<String> array_ListasDeinsert = new ArrayList<String>();

	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/tiendecita?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String login = "Enrique";
	static String password = "1234";
	static String sentencia = "SELECT * FROM articulos";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet rs = null;

	public static void main(String[] args) {

	}

	/**
	 * @return
	 * 
	 */

	public static void ConectorConsultaList(List ListaArticulos) {
		ListaArticulos.removeAll();
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia
			// SQL
			rs = statement.executeQuery(sentencia);
			while (rs.next()) {
				String Lista = "ID-> " + rs.getInt("id_Articulos") + " , Descripcion-> "
						+ rs.getString("DescripcionArticulos") + " , Cantidad-> " + rs.getDouble("CantidadArticulos")
						+ " , Precio-> " + rs.getDouble("PrecioArticulos")+"€";
				ListaArticulos.add(Lista);
				ConsultaArticulos.tabla.addCell(Lista);
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error 3: " + e.getMessage());
			}
		}
	}

	public static void Conectorinsert(JTextField TextDescripcion, JTextField TextPrecio, JTextField TextCantidad) {

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			String descripcion = TextDescripcion.getText();
			double precio = Double.parseDouble(TextPrecio.getText());
			double cantidad = Double.parseDouble(TextCantidad.getText());

			try {
				String insert = "INSERT INTO articulos VALUES(null,'" + descripcion + "'," + precio + "," + cantidad
						+ ")";
				System.out.println(insert);
				statement.executeUpdate(insert);
				TextDescripcion.setText("");
				TextPrecio.setText("");
				TextCantidad.setText("");
			} catch (SQLException se) {
				System.out.println("Error en la sentencia SQL" + se.toString());
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}

	}

	public static void Conectorupdate(JTextField TextDescripcion, JTextField TextPrecio, JTextField TextCantidad,
			List ListaArticulos) {

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			String espacio = ListaArticulos.getSelectedItem();
			String[] partes = espacio.split(" ");
			String id = partes[1];

			String descripcion = TextDescripcion.getText();
			double precio = Double.parseDouble(TextPrecio.getText());
			double cantidad = Double.parseDouble(TextCantidad.getText());

			try {
				// String insert = "INSERT INTO articulos VALUES(null,'" + descripcion + "'," +
				// precio + "," + cantidad
				// + ")";
				String update = ("UPDATE articulos SET DescripcionArticulos ='" + descripcion + "',PrecioArticulos="
						+ precio + ",CantidadArticulos=" + cantidad + " WHERE id_Articulos=" + id + ";");

				System.out.println(espacio);

				System.out.println(update);
				statement.executeUpdate(update);
				TextDescripcion.setText("");
				TextPrecio.setText("");
				TextCantidad.setText("");
			} catch (SQLException se) {
				System.out.println("Error en la sentencia SQL" + se.toString());
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}

	}

	public static void Conectordelete(List ListaArticulos) {

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			String espacio = ListaArticulos.getSelectedItem();
			String[] partes = espacio.split(" ");
			String id = partes[1];

			try {
				// String insert = "INSERT INTO articulos VALUES(null,'" + descripcion + "'," +
				// precio + "," + cantidad
				// + ")";
				String delete = ("delete from articulos" + " WHERE id_Articulos=" + id + ";");

				System.out.println(espacio);

				System.out.println(delete);
				statement.executeUpdate(delete);

			} catch (SQLException se) {
				System.out.println("Error en la sentencia SQL" + se.toString());
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}

	}

	public static void Conectorticket(List ListaArticulos) {
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();

			String espacio = ListaArticulos.getSelectedItem();
			String[] partes = espacio.split(" ");
			String id = partes[1];
			String Pre_Descripcion = partes[4];
			String Pre_Cantidad = partes[7];
			String Pre_Precio = partes[10];

			double int_Pre_Cantidad = Double.parseDouble(Pre_Cantidad);
			double cantidad_restar = Double.parseDouble(Alta_tickets.TextCantidad.getText());

			double stock_actualizado = int_Pre_Cantidad - cantidad_restar;

			// String insert = "INSERT INTO articulos VALUES(null,'" + descripcion + "'," +
			// precio + "," + cantidad
			// + ")";
			int idT = Alta_tickets.idticket;
			idT = idT + 1;
			String insert = "INSERT INTO articulo_tickets VALUES(null," + idT + "," + id + "," + cantidad_restar
					+ " );";
			System.out.println(insert);

			String update = ("UPDATE articulos SET DescripcionArticulos ='" + Pre_Descripcion + "',PrecioArticulos="
					+ Pre_Precio + ",CantidadArticulos=" + stock_actualizado + " WHERE id_Articulos=" + id + ";");

			Alta_tickets.ListaArticulosAñadidos.add("ID-> " + id + " , Descripcion-> " + Pre_Descripcion
					+ " , Cantidad-> " + cantidad_restar + " , Precio-> " + Pre_Precio);
			System.out.println(espacio);

			System.out.println(update);
			array_ListasDeupdate.add(update);
			array_ListasDeinsert.add(insert);

			Alta_tickets.CantidadArticulos = Integer.parseInt(Alta_tickets.TextCantidad.getText());
			Alta_tickets.preprecio = Double.parseDouble(Pre_Precio);
			Alta_tickets.precioxcantidad = Alta_tickets.CantidadArticulos * Alta_tickets.preprecio;
			Alta_tickets.Preañadidos = Alta_tickets.Preañadidos + Alta_tickets.precioxcantidad;
			Alta_tickets.Preañadido = Alta_tickets.Preañadidos + "";

			Alta_tickets.TextTotalPrecio.setText(Alta_tickets.Preañadido);

		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}

	}

	public static void ConectorTicketinsert(JTextField TextFecha, JTextField TextTotalPrecio) {

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			String fechaTicket = TextFecha.getText();
			String totalticket = TextTotalPrecio.getText();

			try {

				String insert = "INSERT INTO tickets VALUES(null,'" + fechaTicket + "'," + totalticket + ");";
				System.out.println(insert);
				statement.executeUpdate(insert);

			} catch (SQLException se) {
				System.out.println("Error en la sentencia SQL" + se.toString());
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}

	}

	public static void Conectorticketupdate() {
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();

			for (int i = 0; i < ConectorSql.array_ListasDeupdate.size(); i++) {
				System.out.println(array_ListasDeupdate.get(i));
				ConectorSql.statement.executeUpdate(array_ListasDeupdate.get(i));
			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}

	}

	public static void Conectorarticuloticketinsert() {
		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();

			for (int i = 0; i < ConectorSql.array_ListasDeinsert.size(); i++) {
				System.out.println(array_ListasDeinsert.get(i));
				ConectorSql.statement.executeUpdate(array_ListasDeinsert.get(i));
			}

		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e1) {
				System.out.println("Error 3: " + e1.getMessage());
			}
		}
	}

	public static void conectorListaticket(List ListaTickets) {

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/tiendecita?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login = "Enrique";
		String password = "1234";
		String sentencia = "SELECT * FROM tickets";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia
			// SQL
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				ListaTickets.add("IDTicket-> " + rs.getInt("id_Tickets") + " FechaTicket-> " + rs.getDate("FechaTicket")
				+ " TotalTicket-> " + rs.getDouble("totalTicket"));
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error 3: " + e.getMessage());
			}
		}

	}

	public static void conectorArticuloticket(List ListaTickets, List ListaArticulosAñadidos) {
		ListaArticulosAñadidos.removeAll();


		String espacio = ListaTickets.getSelectedItem();
		String[] partes = espacio.split(" ");
		String id = partes[1];

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/tiendecita?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login = "Enrique";
		String password = "1234";
		String sentencia = "SELECT tickets.id_Tickets, articulos.id_Articulos ,articulos.DescripcionArticulos, articulos.PrecioArticulos,articulo_tickets.cantidad FROM articulos INNER JOIN articulo_tickets ON articulos.id_Articulos = articulo_tickets.id_ArticuloFK inner join tickets on tickets.id_Tickets = articulo_tickets.id_TicketFK where tickets.id_Tickets="
				+ id + ";";
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		try {
			// Cargar los controladores para el acceso a la BD
			Class.forName(driver);
			// Establecer la conexión con la BD Empresa
			connection = DriverManager.getConnection(url, login, password);
			// Crear una sentencia
			statement = connection.createStatement();
			// Crear un objeto ResultSet para guardar lo obtenido y ejecutar la sentencia
			// SQL
			rs = statement.executeQuery(sentencia);

			while (rs.next()) {
				String ListaAñadidos = "IDTicket-> " + rs.getInt("id_Tickets") + " id_Articulos-> " + rs.getInt("id_Articulos")
				+ " Descripcion-> " + rs.getString("DescripcionArticulos") + " PrecioArticulo-> "
				+ rs.getDouble("PrecioArticulos") + " Cantidad-> " + rs.getDouble("cantidad")+"€";
				ListaArticulosAñadidos.add(ListaAñadidos);
				
				ConsultaTicket.tablaTicket.addCell(ListaAñadidos);
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Error 1: " + cnfe.getMessage());
		} catch (SQLException sqle) {
			System.out.println("Error 2: " + sqle.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.out.println("Error 3: " + e.getMessage());
			}
		}

	}

}
