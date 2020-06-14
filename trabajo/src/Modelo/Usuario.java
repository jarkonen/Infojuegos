package Modelo;

import java.awt.Image;

public class Usuario {

	private String Usuario;
	private String correo;
	private String descripcion;
	private String[] amigos;
	private String[] guias;
	private Image imagen;

	public Usuario() {
		this.Usuario = "";
		this.correo = "";
		this.descripcion = "";
		this.amigos = null;
		this.guias = null;
	}

	public Usuario(String usuario, String correo, String descripcion, String[] amigos, String[] guias) {
		super();
		this.Usuario = usuario;
		this.correo = correo;
		this.descripcion = descripcion;
		this.amigos = amigos;
		this.guias = guias;
	}
	
	//Getters y setters

	public Image getImagen() {
		return imagen;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	public String getUsuario() {
		return Usuario;
	}

	public void setUsuario(String usuario) {
		Usuario = usuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String[] getAmigos() {
		return amigos;
	}

	public void setAmigos(String[] amigos) {
		this.amigos = amigos;
	}

	public String[] getGuias() {
		return guias;
	}

	public void setGuias(String[] guias) {
		this.guias = guias;
	}
}
