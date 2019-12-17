package es.Studium.Practica2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Alta_Articulos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField TextDescripcion;
	private JTextField TextCantidad;
	private JTextField TextPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Alta_Articulos frame = new Alta_Articulos();
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
	public Alta_Articulos() {
		setTitle("ALTA_ARTICULO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		TextDescripcion = new JTextField();
		TextDescripcion.setBounds(31, 28, 281, 30);
		contentPane.add(TextDescripcion);
		TextDescripcion.setColumns(10);

		TextCantidad = new JTextField();
		TextCantidad.setColumns(10);
		TextCantidad.setBounds(31, 89, 42, 30);
		contentPane.add(TextCantidad);

		TextPrecio = new JTextField();
		TextPrecio.setColumns(10);
		TextPrecio.setBounds(111, 89, 42, 30);
		contentPane.add(TextPrecio);

		JLabel lblDescripcion = new JLabel("DESCRIPCION");
		lblDescripcion.setBounds(31, 11, 105, 14);
		contentPane.add(lblDescripcion);

		JLabel lblCantidad = new JLabel("CANTIDAD");
		lblCantidad.setBounds(31, 75, 105, 14);
		contentPane.add(lblCantidad);

		JLabel lblPrecio = new JLabel("PRECIO");
		lblPrecio.setBounds(115, 75, 105, 14);
		contentPane.add(lblPrecio);

		Button button = new Button("ACEPTAR");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConectorSql.Conectorinsert(TextDescripcion, TextPrecio, TextCantidad);
				Registro(LoginView.Usuario);
				JOptionPane.showMessageDialog(null, "Creado Correctamente","Hecho",
                        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		button.setBounds(242, 184, 70, 22);
		contentPane.add(button);

		Button button_2 = new Button("CANCELAR");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);
			}
		});
		button_2.setBounds(31, 184, 70, 22);
		contentPane.add(button_2);
		setVisible(true);
	}
	private void Registro(String usuario) {
		Calendar fechaRegistro = Calendar.getInstance();
		Date fecha = fechaRegistro.getTime();
		try {
			FileWriter fw = new FileWriter("movimientos.log",true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("["+fecha+"] "+"["+usuario+"]"+"[INSERT INTO ARTICULOS]");
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Se produjo un error");
		}
		
	}
}
