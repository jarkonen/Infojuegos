package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class accesousuarios {
	
	//comprueba que el usuario y la contraseña introducidos sean los correctos devuelve true si esta y false si no hay coincidencia.

		public static boolean comprobar_usuarioycontra(String correo, String contraseña) {

			String sentenciaSql = "SELECT email, contraseña FROM logusuarios";
			PreparedStatement sentencia = null;
			ResultSet resultado = null;
			boolean b = false;

			try {
				sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
				resultado = sentencia.executeQuery();
				while (resultado.next()) {
					if (resultado.getString(1).equals(correo)&&resultado.getString(2).equals(contraseña)) {
						b = true;
					}else {
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
			return b;

		}
		
		//Comprueba que el email introducido no este ya registrado
		
		public static Boolean rgu(String email) {
			
			String sentenciaSql = "SELECT email FROM logusuarios";
			PreparedStatement sentencia = null;
			ResultSet resultado = null;
			String s = email;
			boolean bol = false;
			 
			try {
			  sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
			  resultado = sentencia.executeQuery();
			  while (resultado.next()) {
				  System.out.println(resultado.getString(1));
				  System.out.println("email " + email);
				  if (resultado.getString(1).equals(s)) {
					bol = true;
				}
			    ;
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
		
		//Recibe un String correo como parametro y devuelve un objeto usuario.
		public static Usuario sacar_usuario(String correo) throws IOException {

			String sentenciaSql = "SELECT email, contraseña, nombre, descripcion FROM logusuarios where email ='"+correo+"'";
			PreparedStatement sentencia = null;
			ResultSet resultado = null;
			Usuario usu = new Usuario();


			try {
				sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
				resultado = sentencia.executeQuery();
				while (resultado.next()) {
					usu.setUsuario(resultado.getString(3));
					usu.setCorreo(resultado.getString(1));
					usu.setDescripcion(resultado.getString(4));
					//usu.setImagen( Controlador.controlador.ConvertirImagen(Modelo.conectaaBD.Lista_Imagenes(correo)));
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
			return usu;

		}
		
		//inserta los datos de un nuevo usuario a la base de datos

		public static void registra_usuario(String correo, String contraseña, String nombre) {
				String rutaimageninicial ="..\\trabajo\\Iconos\\usuariobase.jpg";
				String sentenciaSql = "INSERT INTO logusuarios (email, contraseña, nombre, imagen) VALUES (?, ?, ?, ?)";
				PreparedStatement sentencia = null;
		        FileInputStream fis = null;
		             File file = new File(rutaimageninicial);
		             try {
						fis = new FileInputStream(file);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				try {
					sentencia = conectaaBD.conexion.prepareStatement(sentenciaSql);
					sentencia.setString(1, correo);
					sentencia.setString(2, contraseña);
					sentencia.setString(3, nombre);
					sentencia.setBinaryStream(4, fis,(int) file.length());
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
		//Recibe los datos de un usuario y lo modifica en la base de datos.
	    public static boolean modificar_usuario(String descripcion, String name, String ruta, String correo) {
	        FileInputStream fis = null;
	        try {
	             File file = new File(ruta);
	             fis = new FileInputStream(file);
	             PreparedStatement pstm = conectaaBD.conexion.prepareStatement("UPDATE logusuarios set nombre=?, descripcion=?, imagen=? where email='"+correo+"'");
	             pstm.setString(1, name);
	             pstm.setString(2, descripcion);
	             pstm.setBinaryStream(3, fis,(int) file.length());
	             pstm.execute();
	             pstm.close();
	             return true;
	        } catch (FileNotFoundException e) {
	             System.out.println(e.getMessage());
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        } finally {
	            try {
	               fis.close();
	             } catch (IOException e) {
	               System.out.println(e.getMessage());
	             }
	        }
	        return false;
	   }
		
}
