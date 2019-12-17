package es.Studium.Practica2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Confirmar_Modificar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Confirmar_Modificar dialog = new Confirmar_Modificar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			JOptionPane.showMessageDialog(null, "Modificado Correctamente","Hecho",
                    JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Confirmar_Modificar() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEstaSeguroQue = new JLabel("ESTA SEGURO QUE QUIERE MODIFICAR EL ARTICULO");
			lblEstaSeguroQue.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstaSeguroQue.setBounds(0, 75, 434, 63);
			contentPanel.add(lblEstaSeguroQue);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ConectorSql.Conectorupdate(Modificar_Articulos.TextDescripcion, Modificar_Articulos.TextPrecio,
								Modificar_Articulos.TextCantidad, Modificar_Articulos.ListaArticulos);
						ConectorSql.ConectorConsultaList(Modificar_Articulos.ListaArticulos);
						setVisible(false);
						Modificar_Articulos.Registro(LoginView.Usuario);


					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);

	}

}
