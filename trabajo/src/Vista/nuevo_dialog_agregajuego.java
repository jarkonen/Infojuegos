package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class nuevo_dialog_agregajuego extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtdescripcion;
	private JTextField txtplataforma;
	private JTextField txtgenero;
	private JTextField txtaño;
	private JTextField txtcompañia;
	private JTextField txtimagen;

	/**
	 * Launch the application.
	 */
	public static void nuevo_dialog_agregajuego() {
		try {
			nuevo_dialog_agregajuego dialog = new nuevo_dialog_agregajuego();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public nuevo_dialog_agregajuego() {
		
		setBounds(100, 100, 450, 530);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setResizable(false);
		
		JLabel lblnombre = new JLabel("Nombre :");
		lblnombre.setBounds(52, 80, 67, 14);
		contentPanel.add(lblnombre);
		
		JLabel lbldescripcion = new JLabel("Descripcion :");
		lbldescripcion.setBounds(52, 315, 84, 14);
		contentPanel.add(lbldescripcion);
		
		JLabel lblplataforma = new JLabel("Plataforma :");
		lblplataforma.setBounds(52, 149, 84, 14);
		contentPanel.add(lblplataforma);
		
		JLabel lblgenero = new JLabel("Genero :");
		lblgenero.setBounds(52, 186, 67, 14);
		contentPanel.add(lblgenero);

		JLabel lblaño = new JLabel("Año :");
		lblaño.setBounds(52, 217, 46, 14);
		contentPanel.add(lblaño);
		
		JLabel lblcompañia = new JLabel("Compañia :");
		lblcompañia.setBounds(52, 257, 67, 14);
		contentPanel.add(lblcompañia);
		
		JLabel lblimagen = new JLabel("Imagen :");
		lblimagen.setBounds(52, 113, 67, 14);
		contentPanel.add(lblimagen);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(146, 77, 86, 22);
		contentPanel.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setColumns(10);
		txtdescripcion.setBounds(146, 312, 257, 123);
		contentPanel.add(txtdescripcion);
		
		txtplataforma = new JTextField();
		txtplataforma.setColumns(10);
		txtplataforma.setBounds(146, 146, 86, 23);
		contentPanel.add(txtplataforma);
		
		String arreglogeneros[]=new String[]{"","rpg","fps","action rpg", "mmorpg", "grafic Adventure", "rts", "survival horror", "carreras", "musical", "simulacion" };
		JComboBox txtgenero = new JComboBox(arreglogeneros);
		txtgenero.setBounds(146, 183, 86, 20);
		contentPanel.add(txtgenero);
		
		txtaño = new JTextField();
		txtaño.setColumns(10);
		txtaño.setBounds(146, 214, 86, 22);
		contentPanel.add(txtaño);
		
		txtcompañia = new JTextField();
		txtcompañia.setColumns(10);
		txtcompañia.setBounds(146, 254, 86, 23);
		contentPanel.add(txtcompañia);
		
		txtimagen = new JTextField();
		txtimagen.setColumns(10);
		txtimagen.setBounds(146, 110, 86, 22);
		contentPanel.add(txtimagen);
		
		JButton btnimagen = new JButton("Buscar");
		btnimagen.setBounds(267, 109, 89, 23);
		contentPanel.add(btnimagen);
		btnimagen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtimagen.setText(Controlador.controlador.buscafoto());
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
						String s = (String)txtgenero.getSelectedItem();
						//Comprueba que año sea un numero
						if (Controlador.controlador.numeroono(txtaño.getText())) {
						int año = Integer.parseInt(txtaño.getText());
						//comprueba que el año esta dentro de los valores especificados
						if (Controlador.controlador.compruebaaño(año)==true) {
							//comprueba que los campos esten llenos
							if (txtnombre.getText().length() != 0 && txtaño.getText().length() != 0 && txtcompañia.getText().length() != 0 && txtdescripcion.getText().length() != 0 && s.length() != 0 && txtimagen.getText().length() != 0 && txtplataforma.getText().length() != 0 ) {
								try {
									//Si todo lo anterior es correcto inserta el juego en la base de datos
									Modelo.accesoajuegos.registrajuegoconpath(txtnombre.getText(), txtdescripcion.getText(), txtplataforma.getText(), s, año, txtcompañia.getText(), "ya no se usa", Controlador.controlador.cambiaruta(txtimagen.getText()));
									Controlador.main.ln = Modelo.accesoajuegos.sacanombresjuego();
								} catch (FileNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							dispose();
							Vista.nueva_vista_principal.nueva_vista_principal();
							
						}else {
							JOptionPane.showMessageDialog(null, "Fecha incorrecta introduzca una fecha entre 1850 y el año actual.");
						}

					}else {
						JOptionPane.showMessageDialog(null, "Fecha incorrecta asegurate que es un valor numerico de 4 digitos.");
					}
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
						Vista.nueva_vista_principal.nueva_vista_principal();
						dispose();
						
					}
				});
			}
		}
	}
}
