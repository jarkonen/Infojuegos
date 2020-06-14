package Modelo;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Vista.nueva_vista_principal;

public class Listanombresjuego {
	
	private static ArrayList<String> nombrejuegos;
	
	public Listanombresjuego() {
		
		nombrejuegos = new ArrayList<String>();
		
	}
	
	public String getnombre(int p) {
		return nombrejuegos.get(p);
	}
	public void setnombre (String j) {
		nombrejuegos.add(j);
	}
	public static int numerojuegos() {
		int x;
		x = nombrejuegos.size();
		System.out.println(x);
		return x;
	}
	
	public static int rellenapaneles(int contador, Listanombresjuego ln, JLabel label, JLabel label2, JButton b1, JButton b2) throws IOException {
		String path, path2;
		
		path = Modelo.accesoajuegos.sacaimagen(ln.getnombre(contador));
		b1.setText(ln.getnombre(contador));
		System.out.println(path);
		Image imagen = new ImageIcon(path).getImage();
		ImageIcon icon = new ImageIcon(imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icon);
		System.out.println(ln.numerojuegos());
		if (contador>=ln.numerojuegos()+1) {
			contador=0;
		}else {
			contador++;
		}
		
		
		path2 = Modelo.accesoajuegos.sacaimagen(ln.getnombre(contador));
		b2.setText(ln.getnombre(contador));
		System.out.println(path2);
		Image imagen2 = new ImageIcon(path2).getImage();
		ImageIcon icon2 = new ImageIcon(imagen2.getScaledInstance(label2.getWidth(), label2.getHeight(), Image.SCALE_DEFAULT));
		label2.setIcon(icon2);
		if (contador>=ln.numerojuegos()+1) {
			contador=0;
		}else {
			contador++;
		}
		
		return contador;
	}
	
	public static int rellenapaneles2(int contador, Listanombresjuego ln, JLabel label, JButton b1) throws IOException {
		String path, path2;
		
		path = Modelo.accesoajuegos.sacaimagen(ln.getnombre(contador));
		b1.setText(ln.getnombre(contador));
		System.out.println(path);
		Image imagen = new ImageIcon(path).getImage();
		ImageIcon icon = new ImageIcon(imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
		label.setIcon(icon);
		contador++;
		return contador;
	}

}
