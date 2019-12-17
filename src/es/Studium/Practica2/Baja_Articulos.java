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
import javax.swing.border.EmptyBorder;

public class Baja_Articulos extends JFrame {
	public static final long serialVersionUID = 1L;
	public static JPanel contentPane;
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
	public Baja_Articulos() {
		setTitle("BAJA_ARTICULO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Button Aceptar = new Button("ACEPTAR");
		Aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Confirmar_Baja();
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
		lblBuscar.setBounds(31, 13, 105, 14);
		contentPane.add(lblBuscar);

		ListaArticulos.setMultipleMode(true);
		ListaArticulos.setBounds(31, 44, 377, 133);
		contentPane.add(ListaArticulos);
		setVisible(true);

		ConectorSql.ConectorConsultaList(ListaArticulos);
	}
	public static void Registro(String usuario) {
		Calendar fechaRegistro = Calendar.getInstance();
		Date fecha = fechaRegistro.getTime();
		try {
			FileWriter fw = new FileWriter("movimientos.log",true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("["+fecha+"] "+"["+usuario+"]"+"[DELETE FROM ARTICULOS]");
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Se produjo un error");
		}				
	}
	
}
