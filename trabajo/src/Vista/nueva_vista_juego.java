package Vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import Modelo.Juego;
import Modelo.Usuario;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class nueva_vista_juego {

	private JFrame frame;
	private JTextField txtValorEntre;
	Usuario usu = new Usuario();
	Juego juego = new Juego();
	private JTable table_comentarios;
	DefaultTableModel dtm = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void v_juego(String nombre) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nueva_vista_juego window = new nueva_vista_juego(nombre);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public nueva_vista_juego(String nombre) throws IOException {
		initialize(nombre);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize(String nombre) throws IOException {

		usu = Controlador.main.usu;
		//carga el objeto juego
		juego = Modelo.accesoajuegos.sacajuego(nombre);
		
		//comprueba que el usuario a valorado ese juego y si es asi pone la valoracion media
		if (Modelo.accesovaloraciones.sacar_valoracion(usu.getCorreo(), juego.getNombre())==false) {
			Modelo.accesovaloraciones.introduce_valoracion_inicial(usu.getCorreo(), juego.getNombre());
		}
		frame = new JFrame("Infojuegos");
		frame.setBounds(100, 100, 905, 560);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(10, 11, 869, 499);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btncomentario = new JButton("Nuevo comentario");
		btncomentario.setBounds(503, 465, 163, 23);
		panel.add(btncomentario);
		btncomentario.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vista.dialog_nuevocomentario.dialog_nuevocomentario(juego.getNombre(), usu.getCorreo());
			}
		});
		
		JLabel lblfotojuego = new JLabel("foto");
		lblfotojuego.setBounds(10, 11, 206, 242);
		panel.add(lblfotojuego);
		Image imagen = new ImageIcon(Modelo.accesoajuegos.sacaimagen(nombre)).getImage();
		//esta linea ajusta la imagen al tamaño del label
		ImageIcon imagen2 = new ImageIcon(imagen.getScaledInstance(lblfotojuego.getWidth(), lblfotojuego.getHeight(), Image.SCALE_DEFAULT));
		lblfotojuego.setIcon(imagen2);
		
		
		JLabel lblnombreponer = new JLabel("Nombre");
		lblnombreponer.setBounds(10, 276, 219, 27);
		panel.add(lblnombreponer);
		lblnombreponer.setText(juego.getNombre());
		
		JLabel lblgenero = new JLabel("Genero :");
		lblgenero.setBounds(10, 314, 86, 27);
		panel.add(lblgenero);

		
		JLabel lblgeneroponer = new JLabel("New label");
		lblgeneroponer.setBounds(90, 314, 219, 27);
		panel.add(lblgeneroponer);
		lblgeneroponer.setText(juego.getGenero());
		
		JLabel lblplataforma = new JLabel("Plataforma/s :");
		lblplataforma.setBounds(10, 352, 86, 27);
		panel.add(lblplataforma);
		
		JLabel lblplataformaponer = new JLabel("New label");
		lblplataformaponer.setBounds(90, 352, 261, 27);
		panel.add(lblplataformaponer);
		lblplataformaponer.setText(juego.getPlataforma());
		
		JLabel lblaño = new JLabel("A\u00F1o :");
		lblaño.setBounds(10, 390, 57, 27);
		panel.add(lblaño);
		
		JLabel labelañoponer = new JLabel("New label");
		labelañoponer.setBounds(90, 390, 86, 27);
		panel.add(labelañoponer);
		labelañoponer.setText(Integer.toString(juego.getAño()));
		
		JLabel lblNewLabel_2_1_3 = new JLabel("New label");
		lblNewLabel_2_1_3.setBounds(90, 428, 251, 27);
		panel.add(lblNewLabel_2_1_3);
		lblNewLabel_2_1_3.setText(juego.getCompañia());
		
		JLabel lblcompañia = new JLabel("Compa\u00F1ia :");
		lblcompañia.setBounds(10, 428, 86, 27);
		panel.add(lblcompañia);


		lblnombreponer.setText(juego.getNombre());
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(362, 11, 441, 242);
		textArea.setBackground(Color.WHITE);
		textArea.setLineWrap(true);
		panel.add(textArea);
		textArea.setText(juego.getDescripcion());
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		
		JCheckBox chckbxfav = new JCheckBox("Favorito");
		chckbxfav.setBounds(244, 37, 97, 23);
		panel.add(chckbxfav);
		if(Modelo.accesovaloraciones.sacar_fav(juego.getNombre(), usu.getCorreo())==true) {
			chckbxfav.setSelected(true);
		};

		JButton btnpuntuar = new JButton("Puntuar");
		btnpuntuar.setBounds(241, 161, 89, 23);
		txtValorEntre = new JTextField();
		txtValorEntre.setBounds(244, 130, 86, 23);
		txtValorEntre.setText("1 - 5");
		panel.add(txtValorEntre);
		txtValorEntre.setColumns(10);

		if (Modelo.accesovaloraciones.tuvaloracionsiono(juego.getNombre(), usu.getCorreo())>0 && Modelo.accesovaloraciones.tuvaloracionsiono(juego.getNombre(), usu.getCorreo())<=5) {
			txtValorEntre.setText(String.format("%.2f",Modelo.accesovaloraciones.sacar_valoracionjuegomedia(juego.getNombre())));
			txtValorEntre.setEditable(false);
			btnpuntuar.setEnabled(false);
		}
		panel.add(btnpuntuar);
		
		//Este boton comprueba que el valor introducido este dentro de el rango correcto si es asi hace un insert en la base de datos y luego un select para cargar
		// todos las puntuaciones de ese juegos sacar la media y mostrarla.
		btnpuntuar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Controlador.controlador.numeroono(txtValorEntre.getText())==false) {
					JOptionPane.showMessageDialog(null, "Valor no valido introduce un numero del 1 al 5");
				}else if(Integer.parseInt(txtValorEntre.getText())>=0 && Integer.parseInt(txtValorEntre.getText())<=5) {
					Modelo.accesovaloraciones.update_valoracion(usu.getCorreo(), nombre, Integer.parseInt(txtValorEntre.getText()));
					try {

						txtValorEntre.setText(String.format("%.2f",Modelo.accesovaloraciones.sacar_valoracionjuegomedia(juego.getNombre())));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					txtValorEntre.setEditable(false);
					btnpuntuar.setEnabled(false);
				}
			}
		});
		
		String[] columnNames = {"Nombre", "Comentario"};

		JPanel panel_comentarios = new JPanel();
		panel_comentarios.setLayout(null);
		panel_comentarios.setBorder(new TitledBorder(null, "Comentarios.", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_comentarios.setBounds(362, 278, 441, 192);
		panel.add(panel_comentarios);

		dtm = Modelo.accesoacomentarios.sacarcomentarios2(usu.getCorreo(), juego.getNombre(), columnNames);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 26, 421, 155);
		panel_comentarios.add(scrollPane);
		
		table_comentarios = new JTable(dtm);
		scrollPane.setViewportView(table_comentarios);
		
		JLabel lblNewLabel = new JLabel("Puntuacion:");
		lblNewLabel.setBounds(244, 104, 86, 14);
		panel.add(lblNewLabel);
		
		JButton btnguardarfav = new JButton("Guardar fav");
		btnguardarfav.setBounds(244, 70, 89, 23);
		panel.add(btnguardarfav);
		
		JButton btnrecargarcoments = new JButton("New button");
		btnrecargarcoments.setBounds(813, 302, 57, 51);
		panel.add(btnrecargarcoments);
		Image image = new ImageIcon("..\\\\trabajo\\\\Iconos\\\\reload2.png").getImage();
		btnrecargarcoments.setIcon(new ImageIcon(image.getScaledInstance(btnrecargarcoments.getWidth(), btnrecargarcoments.getHeight(), Image.SCALE_DEFAULT)));
		btnrecargarcoments.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Vista.nueva_vista_juego.v_juego(juego.getNombre());
			}
		});
		
		JButton btnhelpjuegos = new JButton("New button");
		btnhelpjuegos.setBounds(808, 37, 62, 51);
		panel.add(btnhelpjuegos);
		Image image2 = new ImageIcon("..\\trabajo\\Iconos\\help.png").getImage();
		btnhelpjuegos.setIcon(new ImageIcon(image2.getScaledInstance(btnhelpjuegos.getWidth(), btnhelpjuegos.getHeight(), Image.SCALE_DEFAULT)));
		
		//Compueba el estado del checkbox y lo guarda en la base de datos
		btnguardarfav.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (chckbxfav.isSelected()==true) {
					Modelo.accesovaloraciones.update_fav(usu.getCorreo(), nombre, true);
				}else if (chckbxfav.isSelected() ==false) {
					Modelo.accesovaloraciones.update_fav(usu.getCorreo(), nombre, false);
				}
				
			}
		});
		try {
			File fichero = new File("..\\trabajo\\help\\help_set.hs");
			URL hsURL = fichero.toURI().toURL();
			HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
			HelpBroker hb = helpset.createHelpBroker();
			hb.enableHelpOnButton(btnhelpjuegos, "ventana_principal", helpset);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
