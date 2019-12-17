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

public class Menu_Principal2 extends JFrame {

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
	public Menu_Principal2() {
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

		JMenu mnTikects = new JMenu("TICKET");
		menuBar.add(mnTikects);

		JMenuItem mntmAlta = new JMenuItem("ALTA");
		mntmAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Alta_tickets();
			}
		});
		mnTikects.add(mntmAlta);
		
		JMenuItem mntmAyuda = new JMenuItem("Ayuda");
		mntmAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		menuBar.add(mntmAyuda);
		
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
