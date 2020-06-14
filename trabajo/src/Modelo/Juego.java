package Modelo;

import java.awt.Image;

public class Juego {
	
	private String nombre;
	private String genero;
	private int año;
	private String descripcion;
	private String plataforma;
	private String compañia;
	private String videoURL;
	private Image imagen;
	
	public Juego() {
		super();
		this.nombre = "";
		this.genero = "";
		this.año = 0;
		this.descripcion = "";
		this.plataforma = "";
		this.compañia = "";
		this.videoURL = "";
		this.imagen = null;
	}
	
	public Juego(String nombre, String genero, int año, String descripcion, String plataforma, String compañia,
			String videoURL, Image imagen) {
		super();
		this.nombre = nombre;
		this.genero = genero;
		this.año = año;
		this.descripcion = descripcion;
		this.plataforma = plataforma;
		this.compañia = compañia;
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

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
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

	public String getCompañia() {
		return compañia;
	}

	public void setCompañia(String compañia) {
		this.compañia = compañia;
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
