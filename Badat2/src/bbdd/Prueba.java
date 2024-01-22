package bbdd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Prueba {

	public static void main(String[] args) throws IOException {
		
		
		
		
		
		
		//C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo ="src\\bbdd\\Usuarios.sql";
		String lineaActual="";
		try {
			BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
			while((lineaActual=archivo.readLine())!=null) {
			System.out.println(lineaActual);
			}
			archivo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}

	}

}
