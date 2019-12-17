package es.Studium.Practica2;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

public class LoginView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String Usuario;
	JLabel usuario, clave;
	JTextField t_usuario;
	JPasswordField t_clave;
	JButton button;
	Container c;

	handler handle;

	database tiendecita;

	LoginView() {
		super("Login form");
		setTitle("Login");

		c = getContentPane();

		tiendecita = new database();
		handle = new handler();

		usuario = new JLabel("Usuario");
		usuario.setBounds(43, 33, 68, 16);
		clave = new JLabel("Clave");
		clave.setBounds(43, 62, 68, 16);
		t_usuario = new JTextField(10);
		t_usuario.setBounds(111, 30, 155, 22);
		t_clave = new JPasswordField(10);
		t_clave.setBounds(111, 61, 157, 22);
		button = new JButton("Login");
		button.setBounds(97, 96, 109, 25);

		button.addActionListener(handle);
		getContentPane().setLayout(null);

		c.add(usuario);
		c.add(t_usuario);
		c.add(clave);
		c.add(t_clave);
		c.add(button);
		
		JLabel lblDesarrolladoPorZerb = new JLabel("Desarrollado: Zerb18. Versi\u00F3n 1.0");
		lblDesarrolladoPorZerb.setBounds(43, 126, 298, 16);
		getContentPane().add(lblDesarrolladoPorZerb);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(340, 200);
		setLocationRelativeTo(null);

	}

	public static void main(String args[]) {
		@SuppressWarnings("unused")
		LoginView Login = new LoginView();
	}

	class handler implements ActionListener {
		public void actionPerformed(ActionEvent ae) {

			char[] temp_pwd = t_clave.getPassword();
			String pwd = null;
			pwd = String.copyValueOf(temp_pwd);
			System.out.println("Username,Pwd:" + t_usuario.getText() + "," + pwd);

			if (tiendecita.checkLogin(t_usuario.getText(), pwd)) {

				JOptionPane.showMessageDialog(null, "Logado Correctamente", "Tiene Acceso",
						JOptionPane.INFORMATION_MESSAGE);
				Usuario = t_usuario.getText();
				if (Usuario.equals("admin")) {
					new Menu_Principal();
				} else if (Usuario.equals("basico")) {
					new Menu_Principal2();
				}
				setVisible(false);
				Registro(LoginView.Usuario);

			} else {
				JOptionPane.showMessageDialog(null, "Login Fallido!", "ERROR!!", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	private void Registro(String usuario) {
		Calendar fechaRegistro = Calendar.getInstance();
		Date fecha = fechaRegistro.getTime();
		try {
			FileWriter fw = new FileWriter("movimientos.log", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			salida.println("[" + fecha + "] " + "[" + usuario + "]" + "[LOGADO]");
			salida.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println("Se produjo un error");
		}

	}
}