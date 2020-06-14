package Modelo;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class accesovaloraciones {
	
	//Recibe como parametro dos string con el corero y el nombre y un booleano
	//y actualiza la base de datos.
	public static void update_fav(String correo, String nombre, boolean fav) {
		
		String sentenciaSql = "Update puntuacion set favoritos=? where email='"+correo+"' and nombre ='"+nombre+"'";
		PreparedStatement sentencia = null;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			sentencia.setBoolean(1, fav);
			sentencia.executeUpdate();
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
	
	//Recibe dos string nombre (del juego) y correo (del usuario) y devuelve un booleano de favorito o no.
	public static boolean sacar_fav(String nombre, String correo) throws IOException {

		String sentenciaSql = "SELECT favoritos FROM puntuacion where nombre ='"+nombre+"' and email ='"+correo+"'";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		boolean bol = false;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				if (resultado.getBoolean(1)==true) {
					bol=true;
				}else {
					bol=false;
				}
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
		return bol;

	}
	
	//Recibe como parámetro dos string con el correo y el nombre y un booleano
	//y lo inserta en la base de datos.
	public static void introduce_fav(String correo, String nombre, boolean fav) {
		
		String sentenciaSql = "INSERT INTO puntuacion (email, nombre, favoritos) VALUES (?, ?, ?)";
		PreparedStatement sentencia = null;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, correo);
			sentencia.setString(2, nombre);
			sentencia.setBoolean(3, fav);
			sentencia.executeUpdate();
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
	
	public static void update_valoracion(String correo, String nombre, int puntuacion) {
		
		String sentenciaSql = "Update puntuacion set puntuacion=? where email='"+correo+"' and nombre ='"+nombre+"'";
		PreparedStatement sentencia = null;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			sentencia.setInt(1, puntuacion);
			sentencia.executeUpdate();
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
	
	public static void introduce_valoracion_inicial(String correo, String nombre) {
		
		int puntuacion = 0;
		boolean f = false;
		String sentenciaSql = "INSERT INTO puntuacion (email, nombre, puntuacion, favoritos) VALUES (?, ?, ?, ?)";
		PreparedStatement sentencia = null;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, correo);
			sentencia.setString(2, nombre);
			sentencia.setInt(3, puntuacion);
			sentencia.setBoolean(4, f);
			sentencia.executeUpdate();
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
	
	public static boolean sacar_valoracion(String correo, String nombre) throws IOException {

		String sentenciaSql = "SELECT * FROM puntuacion where email ='"+correo+"' and nombre ='"+nombre+"'";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		boolean val = false;


		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				val = true;
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
		return val;

	}
	
	//Te dice si has valorado o no un juego y devuelve el valor.
	public static int tuvaloracionsiono(String nombre, String email) throws IOException {

		String sentenciaSql = "SELECT puntuacion FROM puntuacion where nombre ='"+nombre+"' and email ='"+email+"'";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		int contador = 0;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				contador = resultado.getInt(1);
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
		return contador;

	}
	
	//Recibe un String con el nombre de el juego y te devuelve el número de valoraciones que tiene.
	public static int numvaloraciones(String nombre) throws IOException {

		String sentenciaSql = "SELECT puntuacion FROM puntuacion where nombre ='"+nombre+"'";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		int contador = 0;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				contador++;
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
		return contador;

	}
	
	//Recibe un nombre de juego y devuelve un double con la valoración media.
	public static double sacar_valoracionjuegomedia(String nombre) throws IOException {

		String sentenciaSql = "SELECT puntuacion FROM puntuacion where nombre ='"+nombre+"'";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		int contador = 0;
		double media = 0;

		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				contador++;
				media = media+resultado.getInt(1);
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
		media = media/contador;
		return media;

	}
	


}
