package es.Studium.Practica2;

import java.awt.Button;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.List;

public class ConsultaArticulos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/tiendecita?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String login = "Enrique";
	static String password = "1234";
	static String sentencia = "SELECT * FROM articulos";
	static Connection connection = null;
	static Statement statement = null;
	static ResultSet rs = null;
	static PdfPTable tabla = new PdfPTable(1);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaArticulos frame = new ConsultaArticulos();
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
	public ConsultaArticulos() {

		setTitle("CONSULTA_ARTICULO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Button aceptar = new Button("EXPORTAR");
		aceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GuardarArchivo();
				Registro(LoginView.Usuario);
				JOptionPane.showMessageDialog(null, "Exportado Correctamente", "Exportar",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aceptar.setBounds(280, 208, 70, 22);
		contentPane.add(aceptar);

		Button Atras = new Button("ATRAS");
		Atras.setActionCommand("Atras");
		Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		Atras.setBounds(34, 208, 70, 22);
		contentPane.add(Atras);

		JLabel lblArticulo = new JLabel("ARTICULOS");
		lblArticulo.setBounds(72, 30, 105, 14);
		contentPane.add(lblArticulo);

		List ListaArticulos = new List();
		ListaArticulos.setMultipleMode(false);
		ListaArticulos.setBounds(72, 50, 284, 136);
		contentPane.add(ListaArticulos);
		setVisible(true);

		ConectorSql.ConectorConsultaList(ListaArticulos);
	}

	private void GuardarArchivo() {
		//Se crea el documento
				Document documento = new Document();
				try {
					FileDialog fd = new FileDialog(this, "Indicar nombre y ubicación del archivo",FileDialog.SAVE);
					fd.setVisible(true);
					if(fd.getFile().contains(".pdf")) {
						try {
						FileOutputStream ficheroPdf = new FileOutputStream(fd.getDirectory()+fd.getFile());
						PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
						
						// Se abre el documento.
						documento.open();
						documento.addTitle("Tabla de Consulta de Articulos");
						Paragraph titulo = new Paragraph();
				        titulo.setAlignment(Paragraph.ALIGN_CENTER);
				        titulo.setFont(FontFactory.getFont("Times New Roman", 24, Font.BOLD, BaseColor.BLACK));
				        titulo.add("LISTA DE ARTICULOS");
				        documento.add(titulo);
				        Paragraph vacio1 = new Paragraph();
				        vacio1.add("\n\n");
				        documento.add(vacio1);	
					documento.add(tabla);
					documento.close();
					}catch ( Exception e ){
						e.printStackTrace();
					}	
				
				}
				}catch ( Exception e ){
					e.printStackTrace();
				}	
	}

	private void Registro(String usuario) {
		Calendar fechaRegistro = Calendar.getInstance();
		Date fecha = fechaRegistro.getTime();
		try {
			FileWriter fw = new FileWriter("movimientos.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("[" + fecha + "] " + "[" + usuario + "]" + "[CONSULTA EN Articulos]");
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Se produjo un error");
		}
	}
}
