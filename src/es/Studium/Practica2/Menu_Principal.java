package es.Studium.Practica2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;

public class Menu_Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_Principal frame = new Menu_Principal();
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
	public Menu_Principal() {
		setTitle("MENU PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 424, 35);
		contentPane.add(menuBar);

		JMenu mnArticulos = new JMenu("ARTICULOS");
		menuBar.add(mnArticulos);

		JMenuItem mntmNewMenuItem = new JMenuItem("ALTA");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Alta_Articulos();
			}
		});
		mnArticulos.add(mntmNewMenuItem);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("BAJA");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Baja_Articulos();
			}
		});
		mnArticulos.add(mntmNewMenuItem_1);

		JMenuItem mntmModificacion = new JMenuItem("MODIFICAR");
		mntmModificacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Modificar_Articulos();
			}
		});
		mnArticulos.add(mntmModificacion);

		JMenuItem mntmConsulta = new JMenuItem("CONSULTA");
		mntmConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ConsultaArticulos();
			}
		});
		mnArticulos.add(mntmConsulta);

		JMenu mnTikects = new JMenu("TICKET");
		menuBar.add(mnTikects);

		JMenuItem mntmAlta = new JMenuItem("ALTA");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Alta_tickets();
			}
		});
		mnTikects.add(mntmAlta);

		JMenuItem mntmConsulta_1 = new JMenuItem("CONSULTA");
		mntmConsulta_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConsultaTicket();

			}
		});
		mnTikects.add(mntmConsulta_1);
		
		JMenu mnAyuda = new JMenu("AYUDA");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mnAyuda.add(mntmAyuda);
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		Button button = new Button("Cerrar sesion");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView();
                setVisible(false);
			}
		});
		button.setBounds(345, 221, 79, 24);
		contentPane.add(button);

		Button ATRAS = new Button("CONSULTA");
		ATRAS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
        setVisible(true);

	}
}
