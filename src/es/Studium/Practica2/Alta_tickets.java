package es.Studium.Practica2;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.List;

public class Alta_tickets extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JTextField TextTotalPrecio = new JTextField();
	public static JTextField TextCantidad = new JTextField();
	public static JTextField TextFecha = new JTextField();
	public static List ListaArticulos = new List(10,false);
	public static List ListaArticulosAñadidos = new List();
	public static int Artañadidos = 0;
	public static double Preañadidos = 0;
	public static int idticket;
	public static double precioxcantidad = 0;
	public static double preprecio = 0;
	public static int CantidadArticulos = 0;
	public static String Preañadido = "";
	public static double volveratras = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alta_tickets frame = new Alta_tickets();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Alta_tickets() {
		setTitle("ALTA_TICKETS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TextFecha.setColumns(10);
		TextFecha.setBounds(80, 283, 91, 22);
		contentPane.add(TextFecha);

		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(34, 287, 39, 14);
		contentPane.add(lblFecha);

		Button aceptar = new Button("ACEPTAR");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ConectorSql.ConectorTicketinsert(TextFecha, TextTotalPrecio);
				ConectorSql.Conectorarticuloticketinsert();

				ConectorSql.Conectorticketupdate();

				TextFecha.setText("");
				TextTotalPrecio.setText("");
				ListaArticulosAñadidos.removeAll();
				ListaArticulosAñadidos.add("   TICKET CREADO   ");
				Conectorarticuloticket();
				ConectorSql.ConectorConsultaList(Alta_tickets.ListaArticulos);
				ConectorSql.array_ListasDeupdate.clear();
				ConectorSql.array_ListasDeinsert.clear();
				Registro(LoginView.Usuario);
				JOptionPane.showMessageDialog(null, "Creado Correctamente", "TICKET CREADO",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aceptar.setBounds(320, 311, 70, 22);
		contentPane.add(aceptar);

		Button cancelar = new Button("CANCELAR");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);
			}
		});
		cancelar.setBounds(42, 311, 70, 22);
		contentPane.add(cancelar);

		JLabel lblArticulos = new JLabel("ARTICULOS A\u00D1ADIDOS");
		lblArticulos.setBounds(42, 140, 155, 14);
		contentPane.add(lblArticulos);
		

		ListaArticulos.setMultipleMode(false);
		ListaArticulos.setBounds(42, 37, 348, 97);
		contentPane.add(ListaArticulos);
		ConectorSql.ConectorConsultaList(ListaArticulos);

		ListaArticulosAñadidos.setMultipleMode(false);
		ListaArticulosAñadidos.setBounds(42, 160, 348, 96);
		contentPane.add(ListaArticulosAñadidos);
		setVisible(true);

		TextCantidad.setColumns(10);
		TextCantidad.setBounds(265, 9, 74, 22);
		contentPane.add(TextCantidad);

		JLabel lblCantidad_1 = new JLabel("CANTIDAD");
		lblCantidad_1.setBounds(196, 13, 80, 14);
		contentPane.add(lblCantidad_1);

		Button añadir = new Button("+");
		añadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Conectorarticuloticket();

				ConectorSql.Conectorticket(Alta_tickets.ListaArticulos);
				TextCantidad.setText("");

			}
		});
		añadir.setActionCommand("+");
		añadir.setBounds(345, 7, 22, 24);
		contentPane.add(añadir);

		JLabel labelArticulos = new JLabel("ARTICULOS");
		labelArticulos.setBounds(42, 12, 105, 14);
		contentPane.add(labelArticulos);

		JLabel lblTotalPrecio = new JLabel("TOTAL PRECIO");
		lblTotalPrecio.setBounds(183, 286, 117, 14);
		contentPane.add(lblTotalPrecio);

		TextTotalPrecio.setColumns(10);
		TextTotalPrecio.setBounds(299, 283, 39, 20);
		contentPane.add(TextTotalPrecio);

		JLabel lblFormatoAaaammdd = new JLabel("FORMATO aaaa-mm-dd");
		lblFormatoAaaammdd.setBounds(34, 262, 137, 14);
		contentPane.add(lblFormatoAaaammdd);

		Button eliminar = new Button("ELIMINAR");
		eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ListaArticulosAñadidos.remove(ListaArticulosAñadidos.getItemCount() - 1);
				volveratras = ((Alta_tickets.Preañadidos) - (Alta_tickets.precioxcantidad));
				Alta_tickets.Preañadidos = volveratras;
				TextTotalPrecio.setText(volveratras + "");
			}
		});
		eliminar.setActionCommand("eliminar");
		eliminar.setBounds(299, 256, 79, 24);
		contentPane.add(eliminar);

		Button eliminartodo = new Button("ELIMINAR TODO");
		eliminartodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListaArticulosAñadidos.removeAll();
				;
				TextTotalPrecio.setText("");
				Preañadidos = 0;
				precioxcantidad = 0;
				preprecio = 0;
				CantidadArticulos = 0;
				Preañadido = "";
			}

		});
		eliminartodo.setActionCommand("eliminar");
		eliminartodo.setBounds(183, 256, 110, 24);
		contentPane.add(eliminartodo);
		setVisible(true);
	}

	public static void Conectorarticuloticket() {

		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/tiendecita?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String login = "Enrique";
		String password = "1234";
		String sentencia = "SELECT id_Tickets FROM Tickets";

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
			rs.last();
			idticket = rs.getInt("id_Tickets");

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
	
	private void Registro(String usuario) {
		Calendar fechaRegistro = Calendar.getInstance();
		Date fecha = fechaRegistro.getTime();
		try {
			FileWriter fw = new FileWriter("movimientos.log",true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("["+fecha+"] "+"["+usuario+"]"+"[INSERT INTO Ticket]");
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Se produjo un error");
		}
		
	}
}
