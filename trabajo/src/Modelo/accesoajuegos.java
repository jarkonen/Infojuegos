package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accesoajuegos {
	
	/*public static void registrajuego(String nombre, String descripcion, String plataforma, String genero, int año, String compañia, String video, String ruta) throws FileNotFoundException {
		
        FileInputStream fis = null;
        File file = new File(ruta);
        fis = new FileInputStream(file);
		String sentenciaSql = "INSERT INTO juegos (nombre, descripcion, plataforma, genero, año, compañia, video, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement sentencia = null;
		 
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nombre);
		  sentencia.setString(2, descripcion);
		  sentencia.setString(3, plataforma);
		  sentencia.setString(4, genero);
		  sentencia.setInt(5, año);
		  sentencia.setString(6, compañia);
		  sentencia.setString(7, video);
		  sentencia.setBinaryStream(8, fis,(int) file.length());
		  sentencia.executeUpdate();
		  System.out.println("Todo bien");
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		
	}*/
	// Método que recibe todos los datos del juego como parámetro y los introduce en la base de datos.
	public static void registrajuegoconpath(String nombre, String descripcion, String plataforma, String genero, int año, String compañia, String video, String ruta) throws FileNotFoundException {
		
		String sentenciaSql = "INSERT INTO juegos (nombre, descripcion, plataforma, genero, año, compañia, video, pathimagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement sentencia = null;
		 
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  sentencia.setString(1, nombre);
		  sentencia.setString(2, descripcion);
		  sentencia.setString(3, plataforma);
		  sentencia.setString(4, genero);
		  sentencia.setInt(5, año);
		  sentencia.setString(6, compañia);
		  sentencia.setString(7, video);
		  sentencia.setString(8,ruta);
		  sentencia.executeUpdate();
		  System.out.println("Todo bien");
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		
	}
	
	//Recibe un String con el nombre y devuelve una imagen.
	public static String sacaimagen(String nombre) throws IOException {
		
		String sentenciaSql = "SELECT pathimagen FROM juegos where nombre ='" + nombre + "'" ;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		String path = null;
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
		    path = resultado.getString(1);
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		      resultado.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		return path;
		
	}
	
	//Método que recibe un String nombre y devuelve un objeto juego.
	public static Juego sacajuego(String nombre) throws IOException {
		
		String sentenciaSql = "SELECT nombre, descripcion, plataforma, genero, año, compañia, video, imagen FROM juegos where nombre ='" + nombre + "'" ;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		Juego juego = new Juego(); 
		
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
		    juego.setNombre(resultado.getString(1)); 
		    juego.setDescripcion(resultado.getString(2));
		    juego.setPlataforma(resultado.getString(3));
		    juego.setGenero(resultado.getString(4));
		    juego.setAño(resultado.getInt(5));
		    juego.setCompañia(resultado.getString(6));
		    juego.setVideoURL(resultado.getString(7));
		    //juego.setImagen(Controlador.controlador.ConvertirImagen(resultado.getBytes(8)));
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		      resultado.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		return juego;
		
	}
	
	//Método que devuelve una lista de todos los juegos.
	public static Listajuegos sacaobjetosjuego(String nombre) throws IOException {
		
		String sentenciaSql = "SELECT * from juegos";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
	
		Listajuegos listajuego = new Listajuegos();
		Juego juego = new Juego(); 
		
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
		    juego.setNombre(resultado.getString(1)); 
		    juego.setDescripcion(resultado.getString(2));
		    juego.setPlataforma(resultado.getString(3));
		    juego.setGenero(resultado.getString(4));
		    juego.setAño(resultado.getInt(5));
		    juego.setCompañia(resultado.getString(6));
		    juego.setVideoURL(resultado.getString(7));
		    juego.setImagen(Controlador.controlador.ConvertirImagen(resultado.getBytes(8)));
		    listajuego.setjuego(juego);
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		      resultado.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		return listajuego;
		
	}
	
	//Método que devuelve una lista con los nombres de todos los juegos.
	public static Listanombresjuego sacanombresjuego() throws IOException {
		
		String sentenciaSql = "SELECT nombre from juegos";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		String nombres;
		Listanombresjuego listanombres = new Listanombresjuego();
 
		
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
		   nombres = resultado.getString(1); 
		   listanombres.setnombre(nombres);
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		      resultado.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		return listanombres;
		
	}
	
	//Método que recibe como parámetro un string que es una sentenciaSQL y devuelve una lista con los nombres de los juegos que coincidan.
	public static Listanombresjuego sacanombresjuego2(String sentencia2) throws IOException {
		
		String sentenciaSql = sentencia2;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		String nombres;
		Listanombresjuego listanombres = new Listanombresjuego();
 
		
		try {
		  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
		  resultado = sentencia.executeQuery();
		  while (resultado.next()) {
		   nombres = resultado.getString(1); 
		   listanombres.setnombre(nombres);
		  }
		} catch (SQLException sqle) {
		  sqle.printStackTrace();
		} finally {
		  if (sentencia != null)
		    try {
		      sentencia.close();
		      resultado.close();
		    } catch (SQLException sqle) {
		      sqle.printStackTrace();
		    }
		}
		return listanombres;
		
	}

}
