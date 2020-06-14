package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class accesoacomentarios {
	
	
	//Método que recibe como parametros dos strings de correo y nombre y un array de string,
	//hace un select a la base de datos y coje todos los comentarios por nombre del juego y los mete en una tabla la cual devuelve
	public static DefaultTableModel sacarcomentarios2(String correo, String nombre, String[] c) {

		String sentenciaSql = "select email, comentario from comentarios where lower(nombre) = lower('"+nombre+"') order by numcoment desc";
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		Object[][] datos = {
	            {"",""}};
        DefaultTableModel dtm = new DefaultTableModel(datos, c);
		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			resultado = sentencia.executeQuery();
			while (resultado.next()) {
				Object[] newRow = {resultado.getString(1), resultado.getString(2)};
		        dtm.addRow(newRow);
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
		return dtm;

	}
	
	//metodo que recibe como parametro 3 strings de correo, nombre y comentario y lo inserta en la tabla comentarios
	public static void registra_comentario(String correo, String nombre, String comentario) {
		String sentenciaSql = "INSERT INTO comentarios (email, nombre, comentario) VALUES (?, ?, ?)";
		PreparedStatement sentencia = null;
		try {
			sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			sentencia.setString(1, correo);
			sentencia.setString(2, nombre);
			sentencia.setString(3, comentario);
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

}
