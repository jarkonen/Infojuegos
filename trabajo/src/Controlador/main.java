package Controlador;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import Modelo.Listanombresjuego;
import Modelo.Usuario;
import Vista.*;


public class main {
	
	public static Usuario usu = new Usuario();
	public static Listanombresjuego ln = new Listanombresjuego();
	static int[] arrayint = null;

	public static void main(String[] args) throws IOException {
		
		Modelo.conectaaBD.conectarBD();
		ln = Modelo.accesoajuegos.sacanombresjuego();
		Vista.vista_loggin.inicia_loggin();

		try{
			 
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception e)
		 {
		  e.printStackTrace();
		 }
		}
}
