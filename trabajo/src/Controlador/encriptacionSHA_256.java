package Controlador;
	
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class encriptacionSHA_256 {
		
	//Método que recibe un string password lo encripta y devuelve el string encriptado
	public static String encriptacionSHA_256(String password) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) {		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) {        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}

}
