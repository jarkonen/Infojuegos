package Modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Listajuegos implements Serializable{
	
	private ArrayList<Juego> listajuegos;
	
	public Listajuegos() {
		
		listajuegos = new ArrayList<Juego>();
		
	}
	
	public Juego getjuego(int p) {
		return listajuegos.get(p);
	}
	public void setjuego (Juego j) {
		listajuegos.add(j);
	}
	

}
