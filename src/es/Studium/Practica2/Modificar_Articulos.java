package es.Studium.Practica2;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Modificar_Articulos extends JFrame {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JTextField TextPrecio;
	public static JTextField TextDescripcion;
	public static JTextField TextCantidad;
	public static List ListaArticulos = new List();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modificar_Articulos frame = new Modificar_Articulos();
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
	public Modificar_Articulos() {
		setTitle("MODIFICAR_ARTICULO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TextDescripcion = new JTextField();
		TextDescripcion.setBounds(31, 158, 143, 30);
		contentPane.add(TextDescripcion);
		TextDescripcion.setColumns(10);

		TextCantidad = new JTextField();
		TextCantidad.setColumns(10);
		TextCantidad.setBounds(197, 158, 42, 30);
		contentPane.add(TextCantidad);

		TextPrecio = new JTextField();
		TextPrecio.setColumns(10);
		TextPrecio.setBounds(279, 158, 42, 30);
		contentPane.add(TextPrecio);

		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setBounds(31, 131, 105, 14);
		contentPane.add(lblDescripcion);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(197, 131, 70, 14);
		contentPane.add(lblCantidad);

		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setBounds(279, 131, 56, 14);
		contentPane.add(lblPrecio);

		Button Aceptar = new Button("ACEPTAR");
		Aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Confirmar_Modificar();

			}
		});
		Aceptar.setBounds(338, 208, 70, 22);
		contentPane.add(Aceptar);

		Button Cancelar = new Button("CANCELAR");
		Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);
			}
		});
		Cancelar.setBounds(31, 208, 70, 22);
		contentPane.add(Cancelar);
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(31, 10, 105, 14);
		contentPane.add(lblBuscar);

		ListaArticulos.setMultipleMode(false);
		ListaArticulos.setBounds(31, 30, 377, 77);
		contentPane.add(ListaArticulos);
		setVisible(true);

		ConectorSql.ConectorConsultaList(ListaArticulos);

		Button Seleccionar = new Button("Seleccionar");
		Seleccionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String espacio = ListaArticulos.getSelectedItem();
				String[] partes = espacio.split(" ");
				// String id = partes[1];
				String Pre_Descripcion = partes[4];
				String Pre_Cantidad = partes[7];
				String Pre_Precio = partes[10];

				TextDescripcion.setText(Pre_Descripcion);
				TextCantidad.setText(Pre_Cantidad);
				TextPrecio.setText(Pre_Precio);
			}
		});
		Seleccionar.setBounds(329, 113, 79, 24);
		contentPane.add(Seleccionar);
	}
	
	public static void Registro(String usuario) {
		Calendar fechaRegistro = Calendar.getInstance();
		Date fecha = fechaRegistro.getTime();
		try {
			FileWriter fw = new FileWriter("movimientos.log",true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("["+fecha+"] "+"["+usuario+"]"+"[UPDATE FROM Articulos]");
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Se produjo un error");
		}						
	}
}
