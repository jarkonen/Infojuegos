package Modelo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class conectaaBD {

	public static Connection conexion = null;

	//Conecta y desconecta de la base de datos
	
	public static void conectarBD() {
		try {

			Class.forName("org.postgresql.Driver").newInstance();
			conexion = DriverManager.getConnection("jdbc:postgresql://192.168.1.78:5433/postgres", "postgres", "1234");
			//conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "1234");
			System.out.println("Se ha conectado");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (InstantiationException ie) {
			ie.printStackTrace();
		} catch (IllegalAccessException iae) {
			iae.printStackTrace();
		}

	}

	public static void desconectarBD() {

		try {
			conexion.close();
			conexion = null;
			System.out.println("se a desconectado");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
    public static boolean guardarfoto(String id, String name, String ruta) {
        FileInputStream fis = null;
        try {
             File file = new File(ruta);
             fis = new FileInputStream(file);
             PreparedStatement pstm = conexion.prepareStatement("INSERT into " +
                        " imagen(id, nombre, archivo) " + " VALUES(?,?,?)");
             pstm.setString(1, id);
             pstm.setString(2, name);
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
    

    
    
    private static ResultSet resultSet = null;
    private static Statement statement = null;

    
    public static byte[] Lista_Imagenes(String email) throws IOException
    {
    	byte[] img2 = null;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("SELECT imagen FROM logusuarios where email ='"+email+"'" );
          while (resultSet.next())
          { 
             img2 = resultSet.getBytes("imagen");
          }
       }
       catch (SQLException ex) {
          System.err.println(ex.getMessage());
       }
       return img2;
    }
    
    public static byte[] Imagen_minijuego(String nombre) throws IOException
    {
    	byte[] img2 = null;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("SELECT imagen FROM juegos where nombre ='"+nombre+"'" );
          while (resultSet.next())
          {  //se crea un objeto CITA y se le van agregando los datos ;
             img2 = resultSet.getBytes("imagen");
          }
       }
       catch (SQLException ex) {
          System.err.println(ex.getMessage());
       }
       return img2;
    }


    public static byte[] Lista_Imagenes2(String email) throws IOException
    {
    	byte[] img2 = null;
        try {
            statement = conexion.createStatement();
            resultSet = statement.executeQuery("SELECT imagen FROM logusuarios where email ='"+email+"'");
          while (resultSet.next())
          {  //se crea un objeto CITA y se le van agregando los datos ;
             img2 = resultSet.getBytes("archivo");
          }
       }
       catch (SQLException ex) {
          System.err.println(ex.getMessage());
       }
        System.out.println(img2);
       return img2;
    }
   
}
