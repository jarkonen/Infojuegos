package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Modelo.accesousuarios;
import Modelo.conectaaBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class dialog_registrarse extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txfcorreo;
	private JTextField txfnombre;
	private JTextField txfcontraseña;

	/**
	 * Launch the application.
	 */
	public static void dialog_registrarse() {
		try {
			dialog_registrarse dialog = new dialog_registrarse();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
			dialog.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_registrarse() {
		setBackground(Color.DARK_GRAY);
		setForeground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Correo :");
			lblNewLabel.setBounds(54, 32, 80, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblnombre = new JLabel("Nombre :");
			lblnombre.setBounds(54, 101, 80, 14);
			contentPanel.add(lblnombre);
		}
		{
			JLabel lblcontraseña = new JLabel("Contrase\u00F1a :");
			lblcontraseña.setBounds(54, 171, 80, 14);
			contentPanel.add(lblcontraseña);
		}
		
		txfcorreo = new JTextField();
		txfcorreo.setBounds(155, 29, 212, 23);
		contentPanel.add(txfcorreo);
		txfcorreo.setColumns(10);
		
		txfnombre = new JTextField();
		txfnombre.setColumns(10);
		txfnombre.setBounds(155, 98, 212, 23);
		contentPanel.add(txfnombre);
		
		txfcontraseña = new JPasswordField();
		txfcontraseña.setColumns(10);
		txfcontraseña.setBounds(155, 165, 212, 23);
		contentPanel.add(txfcontraseña);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
				//comprueba que el usuario no este registrado y si es asi y todos los campos son correctos lo registra
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (accesousuarios.rgu(txfcorreo.getText())==false) {
							Modelo.accesousuarios.registra_usuario(txfcorreo.getText(), Controlador.encriptacionSHA_256.encriptacionSHA_256(txfcontraseña.getText()), txfnombre.getText());
							Vista.vista_loggin.inicia_loggin();;
						}else {
							JOptionPane.showMessageDialog(null, "El correo introducido no es valido o ya esta registrado");
							//dispose();
							//nueva_vista_principal.nueva_vista_principal();
						};
						dispose();
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Vista.vista_loggin.inicia_loggin();
						dispose();
						
					}
				});
			}
		}
	}
}
