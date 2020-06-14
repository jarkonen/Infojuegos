package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuario;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class dialog_modificausuario extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtfnombre;
	private JTextField txtfimagen;
	private JTextField txtfdescrip;

	/**
	 * Launch the application.
	 */
	public static void dialog_modificausuario() {
		try {
			dialog_modificausuario dialog = new dialog_modificausuario();
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
	public dialog_modificausuario() {
		Usuario usu = Vista.nueva_vista_principal.usu;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(45, 58, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblImagen = new JLabel("Imagen");
		lblImagen.setBounds(45, 101, 46, 14);
		contentPanel.add(lblImagen);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(45, 146, 72, 14);
		contentPanel.add(lblDescripcion);
		
		txtfnombre = new JTextField();
		txtfnombre.setColumns(10);
		txtfnombre.setBounds(182, 55, 86, 23);
		contentPanel.add(txtfnombre);
		
		txtfimagen = new JTextField();
		txtfimagen.setColumns(10);
		txtfimagen.setBounds(182, 98, 86, 23);
		contentPanel.add(txtfimagen);
		
		txtfdescrip = new JTextField();
		txtfdescrip.setColumns(10);
		txtfdescrip.setBounds(182, 143, 86, 23);
		contentPanel.add(txtfdescrip);
		
		JButton btnbuscaimagen = new JButton("Buscar");
		btnbuscaimagen.setBounds(306, 97, 89, 23);
		contentPanel.add(btnbuscaimagen);
		btnbuscaimagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			txtfimagen.setText(Controlador.controlador.buscafoto());
				
			}
		});
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String correo = Controlador.main.usu.getCorreo();
						Modelo.accesousuarios.modificar_usuario(txtfdescrip.getText(), txtfnombre.getText(), txtfimagen.getText(), usu.getCorreo());
						dispose();
						Vista.nueva_vista_principal.nueva_vista_principal();
						
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
