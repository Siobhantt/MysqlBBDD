package bbdd;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConexionMySql {
	static Scanner lee = new Scanner(System.in);
	static Connection conn = conectar();

	public static void main(String[] args) {
		// mensaje
		mensaje();
		int opcInsert = 0;
		int opcionUser = 0;
		String opcS = "";
		System.out.println("Introduzca el numero de la accion a ejecutar:");
		opcionUser = lee.nextInt();
		lee.nextLine();

		if (!Character.isDigit(opcionUser)) {
			while (opcionUser != 0) {
				switch (opcionUser) {
				case 1:
					// crearTablas()
					// TODO Capturar la excepcion
					crearTablas(conn);
					break;

				case 2:
					// Insertar datos
					System.out.println("Desea insertar un registro? o Datos de Makaroo?");
					System.out.println("1) Un registro Ó 2)Datos de Makaroo");
					opcInsert = lee.nextInt();
					if (opcInsert == 2) {
						System.out.println("INSERTAR DATOS MAKAROO");
						mensajeInsertar();
						opcS = lee.next();
						lee.nextLine();
						switch (opcS) {
						case "1":
							insertMakarooUsuarios();
							break;
						case "2":
							insertMakarooPosts();
							break;
						case "3":
							insertMakarooLikes();
							break;
						}
						break;
					} else {
						mensajeInsertar();
						opcS = lee.next();
						lee.nextLine();
						switch (opcS) {
						case "1":
							insertarUsuarios();
							break;
						case "2":
							insertarPosts();
							break;
						case "3":
							insertarLikes();
							break;
						}
						break;
					}

				case 3:
					// listar
					mensajeListar();
					opcS = lee.next();

					switch (opcS) {
					case "1":
						listarUsuarios();
						break;
					case "2":
						listarPosts();
						break;
					case "3":
						listarLikes();
						break;
					}
					break;
				case 4:
					// Modificar datos
					mensajeModificar();
					opcS = lee.next();
					lee.nextLine();
					switch (opcS) {
					case "1":
						modificarUsuarios();
						break;
					case "2":
						modificarPosts();
						break;
					case "3":
						modificarLikes();
						break;
					}
					break;
				case 5:
					// Borrar datos

					break;

				default:
					System.out.println("La opcion introducida no esta valorada entre las opciones disponibles.");
				}// fin del switch
				mensaje();
				System.out.println("Introduzca el numero de la accion a ejecutar:");
				System.out.println("");
				opcionUser = lee.nextInt();
				lee.nextLine();
			} // Fin del while
		} else {
			System.out.println("La opcion que ha introducido no es correcta, debe ser un valor numerico.");
			opcionUser = lee.nextInt();
			lee.nextLine();
		}
		// Fin del if

	}

	// ============================================================================================================================
	// INSERTS MAKAROOOOOO  cntrl +shift +f
	// ============================================================================================================================
	private static void insertMakarooUsuarios() {
		// C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo = "src\\bbdd\\Usuarios.sql";
		String lineaActual = "";
		try {
			BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
			Statement stmt = conn.createStatement();
			while ((lineaActual = archivo.readLine()) != null) {
				if(stmt.executeUpdate(lineaActual) == 0){
					System.out.println("Existen duplicados");
				}
			}
			archivo.close();
		} catch (IOException | SQLException e) {
			e.getMessage();
		} finally {

		}

	}

	private static void insertMakarooPosts() {
		// C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo = "src\\bbdd\\Posts.sql";
		String lineaActual = "";
		try {
			BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
			Statement stmt = conn.createStatement();
			while ((lineaActual = archivo.readLine()) != null) {
				if(stmt.executeUpdate(lineaActual) == 0){
					System.out.println("Existen duplicados");
				}
			}
			archivo.close();
		} catch (IOException | SQLException e) {
			e.getMessage();
		} finally {

		}

	}

	private static void insertMakarooLikes() {
		// C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo = "src\\bbdd\\Likes.sql";
		String lineaActual = "";
		try {
			BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
			Statement stmt = conn.createStatement();
			while ((lineaActual = archivo.readLine()) != null) {
				if(stmt.executeUpdate(lineaActual) == 0){
					System.out.println("Existen duplicados");
				}
			}
			archivo.close();
		} catch (IOException | SQLException e) {
			e.getMessage();
		} finally {

		}

	}

//===========================================================================================================================
	// MENSAJES
	// ===========================================================================================================================
	private static void mensajeBorrar() {
		System.out.println("Introduzca la tabla de la cual quiere eliminar los datos:");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");
	}

	private static void mensajeModificar() {
		System.out.println("Seleccione la tabla a la que quiere modificar datos:");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");

	}

	private static void mensajeInsertar() {
		System.out.println("Seleccione la tabla a la que quiere insertar datos:");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");

	}

	public static void mensajeListar() {
		System.out.println("Seleccione la tabla que quiere visualizar:");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");
	}

	public static void mensaje() {
		System.out.println("Tiene disponibles las siguientes opciones: \n" + "1) Crear tablas.\n" + "2) Insertar.\n"
				+ "3) Listar.\n" + "4) Modificar datos.\n" + "5) Borrar datos.\n");
	}

	// =================================================================================================================
	// METODOS PARA MOSTRAR EL CONTENIDO DE LAS TABLAS
	// =================================================================================================================

	private static void listarUsuarios() {
		Statement stmt;
		ResultSet rs;
		String sentencia = "SELECT * FROM Usuarios";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sentencia);
			// Mientras el resultset contenga elementos
			while (rs.next()) {
				System.out.println("idUsuario: " + rs.getString(1));
				System.out.println("Nombre: " + rs.getString(2));
				System.out.println("Apellido: " + rs.getString(3));
				System.out.println("Username: " + rs.getString(4));
				System.out.println("Password: " + rs.getString(5));
				System.out.println("Email: " + rs.getString(6));
				System.out.println("");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listarPosts() {
		Statement stmt;
		ResultSet rs;
		String sentencia = "SELECT * FROM Posts";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sentencia);
			// Mientras el resultset contenga elementos
			while (rs.next()) {
				System.out.println("idPost: " + rs.getString(1));
				System.out.println("idUsuario: " + rs.getString(2));
				System.out.println("Fecha de creacion: " + rs.getString(3));
				System.out.println("Fecha de actualizacion: " + rs.getString(4));
				System.out.println("");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void listarLikes() {
		Statement stmt;
		ResultSet rs;
		String sentencia = "SELECT * FROM Likes";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sentencia);
			// Mientras el resultset contenga elementos
			while (rs.next()) {
				System.out.println("idLike: " + rs.getString(1));
				System.out.println("idPost: " + rs.getString(2));
				System.out.println("idUsuario: " + rs.getString(3));
				System.out.println("");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =================================================================================================================
	// METODOS PARA BORRAR LAS TABLAS
	// =================================================================================================================
	
	
	
	
	
	// =================================================================================================================
	// METODOS PARA MODIFICAR LAS TABLAS
	// =================================================================================================================
	private static void modificarUsuarios() {
		String idUsuario = "";
		String Nombre = "";
		String Apellidos = "";
		String Username = "";
		String Password = "";
		String email = "";
		String nuevoid = "";

		String sentencia = "";

		System.out.println("Introduzca el id del usuario a modificar:");
		idUsuario = lee.nextLine();

		System.out.println("Introduzca el nuevo idUsuario:");
		nuevoid = lee.nextLine();

		System.out.println("Introduzca Nombre:");
		Nombre = lee.nextLine();

		System.out.println("Introduzca el Apellido:");
		Apellidos = lee.nextLine();

		System.out.println("Introduzca el Username:");
		Username = lee.nextLine();

		System.out.println("Introduzca el Password:");
		Password = lee.nextLine();

		System.out.println("Introduzca el Email:");
		email = lee.nextLine();

		sentencia = "UPDATE Usuarios SET idUsuarios='" + nuevoid + "'";
		sentencia += ",Nombre='" + Nombre + "'";
		sentencia += ",Apellidos='" + Apellidos + "'";
		sentencia += ",Username='" + Username + "'";
		sentencia += ",Password='" + Password + "'";
		sentencia += ",email='" + email + "'";
		sentencia += " WHERE idUsuarios=" + idUsuario;

		try {
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sentencia) == 0) {
				System.out.println("No se ha realizado ninguna actualizacion.");
			} else {
				System.out.println("Tabla actualizada.");
			}

		} catch (SQLException e) {
			// System.out.println("Error en el formato del campo: "+e.toString());
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}

	}

	private static void modificarPosts() {
		String idPost = "";
		String idUsuario = "";
		String fcreacion = "";
		String fupdate = "";
		String sentencia = "";
		String nuevoid = "";

		System.out.println("Introduzca el id del Post:");
		idPost = lee.nextLine();

		System.out.println("Introduzca el NUEVO id del Post:");
		nuevoid = lee.nextLine();

		System.out.println("Introduzca el id del Usuario:");
		idUsuario = lee.nextLine();

		System.out.println("Introduzca la fecha de creacion del post:");
		fcreacion = fecha();

		System.out.println("Introduzca la fecha de actualizacion del post:");
		fupdate = fecha();

		sentencia = "UPDATE Posts SET idPosts='" + nuevoid + "'";
		sentencia += ",idUsuario='" + idUsuario + "'";
		sentencia += ",fcreacion='" + fcreacion + "'";
		sentencia += ",fupdate='" + fupdate + "'";
		sentencia += " WHERE idPosts=" + idPost;
		try {
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sentencia) == 0) {
				System.out.println("No se ha realizado ninguna actualizacion.");
			} else {
				System.out.println("Tabla actualizada.");
			}

		} catch (SQLException e) {
			// System.out.println("Error en el formato del campo: "+e.toString());
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}

	}

	private static void modificarLikes() {
		String idLikes = "";
		String idUsuarios = "";
		String idPost = "";
		String nuevoid = "";

		String sentencia = "";

		System.out.println("Introduzca el id del Like:");
		idLikes = lee.nextLine();

		System.out.println("Introduzca el NUEVO id del Like:");
		nuevoid = lee.nextLine();

		System.out.println("Introduzca el id del usuario:");
		idUsuarios = lee.nextLine();

		System.out.println("Introduzca el id del Post:");
		idPost = lee.nextLine();

		sentencia = "UPDATE Likes SET idLikes='" + nuevoid + "'";
		sentencia += ",idUsuarios='" + idUsuarios + "'";
		sentencia += ",idPosts='" + idPost + "'";
		sentencia += " WHERE idLikes=" + idLikes;

		try {
			Statement stmt = conn.createStatement();
			if (stmt.executeUpdate(sentencia) == 0) {
				System.out.println("No se ha realizado ninguna actualizacion.");
			} else {
				System.out.println("Tabla actualizada.");
			}

		} catch (SQLException e) {
			// System.out.println("Error en el formato del campo: "+e.toString());
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}
	}
	// TODO verificar formato email email

	// =================================================================================================================
	// INSERTS
//=====================================================================================================================================
	private static void insertarUsuarios() {
		String idUsuarios = "";
		String Nombre = "";
		String Apellidos = "";
		String Username = "";
		String Password = "";
		String email = "";

		String sentencia = "";

		System.out.println("Introduzca el id del usuario:");
		idUsuarios = lee.nextLine();

		System.out.println("Introduzca el Nombre:");
		Nombre = lee.nextLine();

		System.out.println("Introduzca el Apellido:");
		Apellidos = lee.nextLine();

		System.out.println("Introduzca el Username:");
		Username = lee.nextLine();

		System.out.println("Introduzca el Password:");
		Password = lee.nextLine();

		System.out.println("Introduzca el Email:");
		email = lee.nextLine();

		sentencia = "INSERT INTO Usuarios (idUsuarios, Nombre, Apellidos, Username, Password, email) VALUES ('"
				+ idUsuarios + "', '" + Nombre + "', '" + Apellidos + "', '" + Username + "', '" + Password + "', '"
				+ email + "')";

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sentencia);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			// System.out.println("Error en el formato del campo: "+e.toString());
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}
	}

	private static void insertarLikes() {
		String idLikes = "";
		String idUsuarios = "";
		String idPost = "";

		String sentencia = "";

		System.out.println("Introduzca el id del Like:");
		idLikes = lee.nextLine();

		System.out.println("Introduzca el id del usuario:");
		idUsuarios = lee.nextLine();

		System.out.println("Introduzca el id del Post:");
		idPost = lee.nextLine();

		sentencia = "INSERT INTO Likes (idLikes, idUsuarios, idPosts) VALUES ('" + idLikes + "', '" + idUsuarios + "','"
				+ idPost + "')";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sentencia);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			System.out.println("Se ha producido un error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void insertarPosts() {
		String idPost = "";
		String idUsuario = "";
		String fcreacion = "";
		String fupdate = "";
		String sentencia = "";
		System.out.println("Introduzca el id del Post:");
		idPost = lee.nextLine();

		System.out.println("Introduzca el id del Usuario:");
		idUsuario = lee.nextLine();

		System.out.println("Introduzca la fecha de creacion del post:");
		fcreacion = fecha();

		System.out.println("Introduzca la fecha de actualizacion del post:");
		fupdate = fecha();

		sentencia = "INSERT INTO Posts (idPosts, idUsuarios, created_at, updated_at) VALUES ('" + idPost + "', '"
				+ idUsuario + "', " + fcreacion + ", " + fupdate + ")";
		System.out.println(sentencia);
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sentencia);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			System.out.println("Se ha producido un error:" + e.getMessage());
			e.printStackTrace();
		}
	}

	// ====================================================================================================================================================================
	private static void crearTablas(Connection conn) {
		Statement stmt = null;

		String tabla1 = "CREATE TABLE Usuarios (\r\n" + "    idUsuarios INT PRIMARY KEY,\r\n"
				+ "    Nombre VARCHAR(45),\r\n" + "    Apellidos VARCHAR(45),\r\n" + "    Username VARCHAR(30),\r\n"
				+ "    Password VARCHAR(128),\r\n" + "    email VARCHAR(50)\r\n" + ");";

		String tabla2 = "CREATE TABLE Posts (\r\n" + "    idPosts INT PRIMARY KEY,\r\n" + "    idUsuarios INT,\r\n"
				+ "    created_at DATE,\r\n" + "    updated_at DATE,\r\n"
				+ "    FOREIGN KEY (idUsuarios) REFERENCES Usuarios(idUsuarios)\r\n" + " ON DELETE CASCADE\r\n"
				+ "        ON UPDATE CASCADE);";

		String tabla3 = "CREATE TABLE Likes (\r\n" + "    idLikes INT PRIMARY KEY,\r\n" + "    idUsuarios INT,\r\n"
				+ "    idPosts INT,\r\n" + "   FOREIGN KEY (idUsuarios) REFERENCES Usuarios(idUsuarios),\r\n"
				+ "   FOREIGN KEY (idPosts) REFERENCES Posts(idPosts)\r\n" + " ON DELETE CASCADE\r\n"
				+ "        ON UPDATE CASCADE);";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(tabla1);
			stmt.executeUpdate(tabla2);
			stmt.executeUpdate(tabla3);

		} catch (SQLException e) {

			System.out.println("La tabla que desea crear ya existe.");
		}

	}

	// ================================================FUNCION DE CONECCION CON LA
	// BASE DE DATOS!!=============================================================

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	// ===================================================UTILIDADES===========================================
	private static String fecha() {
		String dia;
		String mes;
		String anio;
		String fecha;
		System.out.println("Introduzca el dia:");
		dia = lee.nextLine();
		System.out.println("Introduzca el mes:");
		mes = lee.nextLine();
		System.out.println("Introduzca el anio:");
		anio = lee.nextLine();
		fecha = "'" + anio + "-" + mes + "-" + dia + "'";
		return fecha;
	}

	// VERIFICAR EMAIL
	private static void verificarEmail() {
		// TODO
	}
}
