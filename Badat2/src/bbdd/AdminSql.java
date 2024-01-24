package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminSql {
	public static Scanner lee = new Scanner(System.in);
	public static Connection conn = CRUD.conectar();

	public static void main(String[] args) {
		// mensaje
		Utils.mensaje();
		int opcionUser = 0;
		System.out.println("Introduzca el numero de la accion a ejecutar:");
		
		opcionUser = lee.nextInt();
		lee.nextLine();

		if (!Character.isDigit(opcionUser)) {
			while (opcionUser != 0) {
				switch (opcionUser) {
				case 1:
					CRUD.creaTablas();
					break;

				case 2:
					CRUD.insertarAll();
					break;
					
				case 3:
					CRUD.listarAll();
					break;
					
				case 4:
					CRUD.modificarAll();
					break;
					
				case 5:
					CRUD.borrarAll();
					break;

				default:
					System.out.println("La opcion introducida no esta valorada entre las opciones disponibles.");
				}
				Utils.mensaje();
				System.out.println("Introduzca el numero de la accion a ejecutar:");
				System.out.println("");
				opcionUser = lee.nextInt();
				lee.nextLine();
			} // Fin del while
		} else {
			System.out.println("La opcion que ha introducido no es correcta, debe ser un valor numerico.");
			opcionUser = lee.nextInt();
			lee.nextLine();
		}// Fin del if
	}//Fin del main

}
