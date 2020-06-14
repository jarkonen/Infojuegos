package Controlador;

import java.awt.Container;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class controlador {

	static Calendar fecha = new GregorianCalendar();
	static int a�oactual = fecha.get(Calendar.YEAR);
	
	//M�todo  que recibe un array de bytes de una imagen y la convierte en un Image y lo devuelve.
	public static Image ConvertirImagen(byte[] bytes) throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Iterator readers = ImageIO.getImageReadersByFormatName("jpeg");
		ImageReader reader = (ImageReader) readers.next();
		Object source = bis;
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		return reader.read(0, param);
	}

	
	//M�todo  que abre un filechooser para buscar la foto y devolver la ruta
	public static String buscafoto() {
		File nuevaruta = null;
		String ruta = null;
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG Images", "jpg");
		JFileChooser fc = new JFileChooser("./");
		fc.addChoosableFileFilter(new FileNameExtensionFilter("Solo archivos JPG", "jpg"));
		fc.setMultiSelectionEnabled(false); // para poder coger varios archivos a la vez
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); // seleccionar lo que podemos utilizar
		fc.setAcceptAllFileFilterUsed(false);

		fc.setFileFilter(filtro);
		int opcion = fc.showOpenDialog(null);
		if (opcion == JFileChooser.APPROVE_OPTION) {
			ruta = fc.getSelectedFile().getAbsolutePath();
			/* nuevaruta = new File(ruta); */

		}
		return ruta;
	}

	
	//M�todo  que recibe un string y dice si son numero o no
	public static boolean numeroono(String String) {
		boolean x = false;
		try {
			if (String != null) {
				Integer.parseInt(String);
				x = true;
			}
		} catch (NumberFormatException nfe) {
			x = false;
		}
		return x;
	}

	
	//M�todo  que recibe un string correo y valida que el formato sea correcto (con @ y .algo).
	public static boolean valida_correo(String s) {

		boolean b;
		// Patr�n para validar el email
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

		// El email a validar
		String email = s;

		Matcher mather = pattern.matcher(email);

		if (mather.find() == true) {
			b = true;
			System.out.println("El email ingresado es v�lido.");
		} else {
			System.out.println("El email ingresado es inv�lido.");
			b = false;
		}
		return b;
	}

	
	//M�todo  que recibe un int a�o y comprueba que este dentro de los valores deseados
	public static boolean compruebaa�o(int a�o) {

		boolean x;
		System.out.println(a�oactual);
		if (a�o > 1850 && a�o < a�oactual + 1) {
			x = true;
		} else {
			x = false;
		}

		return x;

	}

	//M�todo  que rellena un combobox con una lista de a�os
	public static JComboBox rellenaa�o(JComboBox a�os) {
		for (int i = 1949; i <= a�oactual; i++) {
			if (i > 1949) {
				a�os.addItem(Integer.toString(i));
			} else {
				a�os.addItem("");
			}

		}
		return a�os;
	}

	
	//M�todo  que recibe un string ruta con un archivo lo copia a la ruta de la carpeta del proyecto y devuelve esta ruta
	public static String cambiaruta(String ruta) {
		
		String[] dividir = ruta.split("\\\\");              
		System.out.println(dividir.length);
		String nombre = dividir[dividir.length-1];
		
		String nuevaruta = "..\\trabajo\\Imagenes\\" + nombre;
        Path origenPath = FileSystems.getDefault().getPath(ruta);
        Path destinoPath = FileSystems.getDefault().getPath(nuevaruta);

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println(e);
        }
		
		return nuevaruta;
		
	}
	

	
}
