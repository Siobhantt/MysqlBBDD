package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminSql {
	public static Scanner lee = new Scanner(System.in);
	public static Connection conn = conectar();

	public static void main(String[] args) {
		// mensaje
		Utils.mensaje();
		int opcInsert = 0;
		int opcionUser = 0;
		int opcS = 0;
		System.out.println("Introduzca el numero de la accion a ejecutar:");
		opcionUser = lee.nextInt();
		lee.nextLine();

		if (!Character.isDigit(opcionUser)) {
			while (opcionUser != 0) {
				switch (opcionUser) {
				case 1:
					Utils.mensajeCrear();
					opcS = lee.nextInt();
					switch(opcS) {
					case 1:
						ClsUsuarios.crearTablaUsuarios();
						break;
					case 2:
						ClsPosts.crearTablaPosts();
						break;
					case 3:
						ClsLikes.crearTablaLikes();
						break;
					}
					//crearTablas(conn);
					break;
					
				case 2:
					// Insertar datos MOCKAROO
					System.out.println("Desea insertar un registro? o Datos de Makaroo?");
					System.out.println("1) Un registro Ó 2)Datos de Makaroo");
					Utils.espacio();
					
					opcInsert = lee.nextInt();
					if (opcInsert == 2) {
						System.out.println("INSERTAR DATOS MAKAROO");
						Utils.espacio();
						Utils.mensajeInsertar();
						opcS = lee.nextInt();
						lee.nextLine();
						switch (opcS) {
						case 1:
							ClsUsuarios.insertMakarooUsuarios();
							break;
						case 2:
							ClsPosts.insertMakarooPosts();
							break;
						case 3:
							ClsLikes.insertMakarooLikes();
							break;
						}
						break;
					} else {
						Utils.mensajeInsertar();
						opcS = lee.nextInt();
						lee.nextLine();
						switch (opcS) {
						case 1:
							ClsUsuarios.insertarUsuarios();
							break;
						case 2:
							ClsPosts.insertarPosts();
							break;
						case 3:
							ClsLikes.insertarLikes();
							break;
						}
						break;
					}

				case 3:
					// listar
					Utils.mensajeListar();
					opcS = lee.nextInt();

					switch (opcS) {
					case 1:
						ClsUsuarios.listarUsuarios();
						break;
					case 2:
						ClsPosts.listarPosts();
						break;
					case 3:
						ClsLikes.listarLikes();
						break;
					}//Fin del Switch de la opcion 3
					break;
					
				case 4:
					// Modificar datos
					Utils.mensajeModificar();
					opcS = lee.nextInt();
					lee.nextLine();
					switch (opcS) {
					case 1:
						ClsUsuarios.modificarUsuarios();
						break;
					case 2:
						ClsPosts.modificarPosts();
						break;
					case 3:
						ClsLikes.modificarLikes();
						break;
					}//Fin del switch de la opcion 4
					break;
					
				case 5:
					// Borrar Datos
					Utils.mensajeBorrar();
					opcS=lee.nextInt();
					lee.nextLine();
					switch(opcS) {
					case 1:
						ClsUsuarios.borrarUsuarios();
						break;
					case 2:
						ClsPosts.borrarPosts();
						break;
					case 3:
						ClsLikes.borrarLike();
						break;
					}//Fin del switch de la opcion 5
					break;

				default:
					System.out.println("La opcion introducida no esta valorada entre las opciones disponibles.");
				}// fin del switch PRINCIPAL
				
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


	private static Connection conectar() {
		// jdbc:mysql/+ servidor + : + puerto/ + base de datos
		String url = "jdbc:mysql://dns11036.phdns11.es:3306/ad2324_lcardozo";
		String user = "luisa.cardozo";
		String pass = "12345";
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, user, pass);
			// Si la conexion esta abierta...
			if (!conn.isClosed()) {
				System.out.println("Conexion exitosa (:");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
}
