package Modelo;

import java.awt.TextField;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class busquedadejuegos {
	
	public static String buscar(String busqueda,JTextField txtfnombre, JTextField txtfplataforma, JComboBox jbgenero, JTextField compa�ia, JComboBox a�o) {
		
		String sentenciasql = "Select "+busqueda+" from juegos where ";
		String concatena, seleccion="",jcb="";
		int contador=0, contador2=0;


				seleccion = "nombre";
				if (txtfnombre.getText().length() == 0) {
					
				}if (txtfnombre.getText().length() > 0 && contador==0) {
					concatena = seleccion +" ilike '%"+txtfnombre.getText()+"%'";
					sentenciasql = sentenciasql + concatena;
					contador = 1;
				}else if (txtfnombre.getText().length() > 0 && contador!=0) {
					concatena = " and "+seleccion+" ilike '%"+txtfnombre.getText()+"%'";
					sentenciasql = sentenciasql + concatena;
				}
				seleccion = "plataforma";
				if (txtfplataforma.getText().length() == 0) {

				}if (txtfplataforma.getText().length() > 0 && contador==0) {
					concatena = seleccion +" ilike '%"+txtfplataforma.getText()+"%'";
					sentenciasql = sentenciasql + concatena;
					contador = 1;
				}else if (txtfplataforma.getText().length() > 0 && contador!=0) {
					concatena = " and "+seleccion+" ilike '%"+txtfplataforma.getText()+"%'";
					sentenciasql = sentenciasql + concatena;
				}
				seleccion = "genero";
				jcb = (String)jbgenero.getSelectedItem();
				if (jcb.length() == 0) {

				}if (jcb.length() > 0 && contador==0) {
					concatena = seleccion +" = '"+jcb+"'";
					sentenciasql = sentenciasql + concatena;
					contador = 1;
				}else if (jcb.length() > 0 && contador!=0) {
					concatena = " and "+seleccion+" = '"+jcb+"' ";
					sentenciasql = sentenciasql + concatena;
				}
				seleccion = "compa�ia";
				if (compa�ia.getText().length() == 0) {

				}if (compa�ia.getText().length() > 0 && contador==0) {
					concatena = seleccion +" ilike '%"+compa�ia.getText()+"%'";
					sentenciasql = sentenciasql + concatena;
					contador = 1;
				}else if (compa�ia.getText().length() > 0 && contador!=0) {
					concatena = " and "+seleccion+" ilike '%"+compa�ia.getText()+"%'";
				}
				seleccion = "a�o";
				jcb = (String)a�o.getSelectedItem();
				System.out.println(jcb);
				if (jcb.length() == 0) {

				}if (jcb.length() > 0 && contador==0) {
					concatena = seleccion +" = '"+jcb+"'";
					sentenciasql = sentenciasql + concatena;
					contador = 1;
				}else if (jcb.length() > 0 && contador!=0) {
					concatena = " and "+seleccion+" = '"+jcb+"'";
					sentenciasql = sentenciasql + concatena;
				}

				return sentenciasql;
	
		
	}


}
