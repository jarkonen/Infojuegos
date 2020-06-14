package Vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Modelo.Usuario;
import javax.swing.JPanel;

public class vista_loggin {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	Usuario usu2 = new Usuario();

	/**
	 * Launch the application.
	 * @return 
	 */
	public static void inicia_loggin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vista_loggin window = new vista_loggin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vista_loggin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JLabel lbl_usuario = new JLabel("Usuario");
		lbl_usuario.setBounds(71, 194, 72, 14);
		frame.getContentPane().add(lbl_usuario);
		frame.setResizable(false);
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(71, 225, 72, 14);
		frame.getContentPane().add(lblContraseña);
		
		textField = new JTextField();
		textField.setBounds(205, 190, 150, 23);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(205, 222, 150, 23);
		frame.getContentPane().add(passwordField);
		
		JButton btniniciarsesion = new JButton("Iniciar Sesion");
		btniniciarsesion.setBounds(251, 269, 104, 23);
		frame.getContentPane().add(btniniciarsesion);
		
		btniniciarsesion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Controlador.controlador.valida_correo(textField.getText())==true) {	
					//Comprueba que los campos de texto esten llenos
					if(textField.getText().length() != 0 && passwordField.getText().length() != 0) {
						//Comprueba que el correo y la contraseña estan registrados
						if (Modelo.accesousuarios.comprobar_usuarioycontra(textField.getText(), Controlador.encriptacionSHA_256.encriptacionSHA_256(passwordField.getText()))==true) {
							//Carga el usuario
							try {
								usu2 = Modelo.accesousuarios.sacar_usuario(textField.getText());
								Controlador.main.usu = usu2;
								Vista.nueva_vista_principal.nueva_vista_principal();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							frame.dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos, por favor intentelo de nuevo.");
						};
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Formato del correo invalido pofavor comprueba que tenga arroba y .com.");
				}
			}
		});
		
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(71, 269, 104, 23);
		frame.getContentPane().add(btnRegistrarse);
		
		JLabel lblfoto = new JLabel("New label");
		lblfoto.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(lblfoto);
		Image image = new ImageIcon("..\\trabajo\\Iconos\\Infojeugos2.png").getImage();
		lblfoto.setIcon(new ImageIcon(image.getScaledInstance(lblfoto.getWidth(), lblfoto.getHeight(), Image.SCALE_DEFAULT)));
		btnRegistrarse.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog_registrarse.dialog_registrarse();
				frame.dispose();
			}
		});
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //Ajusta el tamaño de la pantalla a tu monitor
		frame.setLocationRelativeTo(null);
	}
}
