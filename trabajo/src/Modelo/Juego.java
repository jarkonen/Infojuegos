package Modelo;

import java.awt.Image;

public class Juego {
	
	private String nombre;
	private String genero;
	private int a�o;
	private String descripcion;
	private String plataforma;
	private String compa�ia;
	private String videoURL;
	private Image imagen;
	
	public Juego() {
		super();
		this.nombre = "";
		this.genero = "";
		this.a�o = 0;
		this.descripcion = "";
		this.plataforma = "";
		this.compa�ia = "";
		this.videoURL = "";
		this.imagen = null;
	}
	
	public Juego(String nombre, String genero, int a�o, String descripcion, String plataforma, String compa�ia,
			String videoURL, Image imagen) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.a�o = a�o;
		this.descripcion = descripcion;
		this.plataforma = plataforma;
		this.compa�ia = compa�ia;
		this.videoURL = videoURL;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getCompa�ia() {
		return compa�ia;
	}

	public void setCompa�ia(String compa�ia) {
		this.compa�ia = compa�ia;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image image) {
		this.imagen = image;
	}

	

}
