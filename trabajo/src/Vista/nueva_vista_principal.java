package Vista;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.server.UID;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import Controlador.main;
import Modelo.Listanombresjuego;
import Modelo.Usuario;
import javax.swing.JList;
import javax.swing.JComboBox;

public class nueva_vista_principal {

	private JFrame frame;
	static Usuario usu = new Usuario();
	Listanombresjuego ln = Controlador.main.ln;
	public int contador=0;
	private JTextArea lbldescripcionusu;
	public static JButton btnayuda = new JButton("ayuda");
	private JTextField txtfnombre;
	private JTextField txtfplataforma;
	private JTextField txtfcompañia;
	JComboBox<String> listacombo;
	JComboBox cboxaño = new JComboBox();

	
	/**
	 * Launch the application.
	 */
	public static void nueva_vista_principal() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					nueva_vista_principal window = new nueva_vista_principal();
					window.frame.setVisible(true);
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
	public nueva_vista_principal() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		usu = Controlador.main.usu; 
		System.out.println(usu.getUsuario());
		frame = new JFrame("Infojuegos");
		frame.setBounds(100, 100, 905, 560);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		
		JPanel panel_juegos = new JPanel();
		panel_juegos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_juegos.setBounds(195, 11, 651, 499);
		frame.getContentPane().add(panel_juegos);
		panel_juegos.setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel1.setBounds(70, 117, 233, 309);
		panel_juegos.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblNewLabel1 = new JLabel("New label");
		lblNewLabel1.setBounds(10, 11, 213, 258);
		panel1.add(lblNewLabel1);

		
		JButton btnNewButton1 = new JButton("New button");
		btnNewButton1.setBounds(10, 275, 213, 23);
		panel1.add(btnNewButton1);
		btnNewButton1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vista.nueva_vista_juego.v_juego(btnNewButton1.getText());	
			}
		});
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel2.setBounds(355, 117, 233, 309);
		panel_juegos.add(panel2);
		
		JLabel lblNewLabel2 = new JLabel("New label");
		lblNewLabel2.setBounds(10, 11, 213, 258);
		panel2.add(lblNewLabel2);
		
		JButton btnNewButton2 = new JButton("New button");
		btnNewButton2.setBounds(10, 275, 213, 23);
		panel2.add(btnNewButton2);
		btnNewButton2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vista.nueva_vista_juego.v_juego(btnNewButton2.getText());	
			}
		});
		
		JPanel panel_usuario = new JPanel();
		panel_usuario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_usuario.setBounds(10, 11, 175, 499);
		frame.getContentPane().add(panel_usuario);
		panel_usuario.setLayout(null);
		
		contador = Modelo.Listanombresjuego.rellenapaneles(contador,ln,lblNewLabel1, lblNewLabel2, btnNewButton1, btnNewButton2);
		
		JButton btnNewButton_1 = new JButton("Avanza");
		btnNewButton_1.setBounds(376, 448, 89, 23);
		panel_juegos.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					contador = Modelo.Listanombresjuego.rellenapaneles2(contador,ln,lblNewLabel1, btnNewButton1);
					if (contador==ln.numerojuegos()) {
						contador = 0;
					}
					contador = Modelo.Listanombresjuego.rellenapaneles2(contador,ln,lblNewLabel2, btnNewButton2);
					if (contador==ln.numerojuegos()) {
						contador = 0;
					}

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton btnnuevojuego = new JButton("");
		btnnuevojuego.setBounds(304, 437, 51, 51);
		panel_juegos.add(btnnuevojuego);
		btnnuevojuego.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Vista.nuevo_dialog_agregajuego.nuevo_dialog_agregajuego();
			}
		});
				
		
		btnnuevojuego.setIcon(new ImageIcon("..\\trabajo\\Iconos\\iconmasjuego2.png"));
		
		
		btnayuda.setBounds(189, 448, 89, 23);
		panel_juegos.add(btnayuda);

		JLabel lblimagenusu = new JLabel("New label");
		lblimagenusu.setBounds(10, 11, 155, 155);
		panel_usuario.add(lblimagenusu);
		Image imagen = null;
			try {
				imagen = Controlador.controlador.ConvertirImagen(Modelo.conectaaBD.Lista_Imagenes(usu.getCorreo()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ImageIcon icon = new ImageIcon(imagen.getScaledInstance(lblimagenusu.getWidth(), lblimagenusu.getHeight(), Image.SCALE_DEFAULT));
			lblimagenusu.setIcon(icon);
		
		JLabel lblnombreusu = new JLabel("New label");
		lblnombreusu.setBounds(10, 177, 155, 21);
		panel_usuario.add(lblnombreusu);
		lblnombreusu.setText(usu.getUsuario());
		
		JButton btnactualizarperfil = new JButton("Perfil");
		btnactualizarperfil.setBounds(10, 465, 155, 23);
		panel_usuario.add(btnactualizarperfil);
		
		lbldescripcionusu = new JTextArea();
		lbldescripcionusu.setBounds(10, 238, 155, 211);
		panel_usuario.add(lbldescripcionusu);
		lbldescripcionusu.setColumns(10);
		lbldescripcionusu.setText(usu.getDescripcion());
		lbldescripcionusu.setEditable(false);
		lbldescripcionusu.setLineWrap(true);
		lbldescripcionusu.setWrapStyleWord(true);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(25, 32, 60, 14);
		panel_juegos.add(lblNombre);
		
		txtfnombre = new JTextField();
		txtfnombre.setBounds(95, 29, 86, 23);
		panel_juegos.add(txtfnombre);
		txtfnombre.setColumns(10);
		
		JLabel lblPlataforma = new JLabel("Plataforma :");
		lblPlataforma.setBounds(212, 32, 65, 14);
		panel_juegos.add(lblPlataforma);
		
		JLabel lblGenero = new JLabel("Genero :");
		lblGenero.setBounds(430, 32, 67, 14);
		panel_juegos.add(lblGenero);
		String arreglogeneros[]=new String[]{"","rpg","fps","action rpg", "mmorpg", "grafic Adventure", "rts", "survival horror", "carreras", "musical", "simulacion" };
		
		JComboBox comboBox_1 = new JComboBox(arreglogeneros);
		comboBox_1.setBounds(507, 29, 89, 20);
		panel_juegos.add(comboBox_1);

		txtfplataforma = new JTextField();
		txtfplataforma.setBounds(304, 29, 86, 23);
		panel_juegos.add(txtfplataforma);
		txtfplataforma.setColumns(10);
		
		JLabel lblcompañia = new JLabel("Compa\u00F1ia :");
		lblcompañia.setBounds(59, 76, 71, 14);
		panel_juegos.add(lblcompañia);
		
		txtfcompañia = new JTextField();
		txtfcompañia.setBounds(140, 73, 86, 23);
		panel_juegos.add(txtfcompañia);
		txtfcompañia.setColumns(10);
		
		JLabel lblaño = new JLabel("A\u00F1o :");
		lblaño.setBounds(419, 76, 65, 14);
		panel_juegos.add(lblaño);
		


		cboxaño = Controlador.controlador.rellenaaño(cboxaño);
		cboxaño.setBounds(484, 73, 89, 20);
		panel_juegos.add(cboxaño);

		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(288, 72, 89, 23);
		panel_juegos.add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			try {
				ln = Modelo.accesoajuegos.sacanombresjuego2(Modelo.busquedadejuegos.buscar("nombre",txtfnombre, txtfplataforma, comboBox_1, txtfcompañia, cboxaño));
				System.out.println(Modelo.busquedadejuegos.buscar("nombre",txtfnombre, txtfplataforma, comboBox_1, txtfcompañia, cboxaño));
				if (ln.numerojuegos()==0) {
					JOptionPane.showMessageDialog(null, "No se encuentran juegos con la informacion indicada.");
				}else {
					contador = Modelo.Listanombresjuego.rellenapaneles2(0,ln,lblNewLabel1, btnNewButton1);
					if (contador==ln.numerojuegos()) {
						contador = 0;
					}
					contador = Modelo.Listanombresjuego.rellenapaneles2(contador,ln,lblNewLabel2, btnNewButton2);
					if (contador==ln.numerojuegos()) {
						contador = 0;
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			}
		});
		
		btnactualizarperfil.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Vista.dialog_modificausuario.dialog_modificausuario();
				frame.dispose();
			}
		});
		try {
			File fichero = new File("..\\trabajo\\help\\help_set.hs");
			URL hsURL = fichero.toURI().toURL();
			HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
			HelpBroker hb = helpset.createHelpBroker();
			hb.enableHelpOnButton(btnayuda, "aplicacion", helpset);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
