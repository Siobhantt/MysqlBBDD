package bbdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClsUsuarios {

	public static void crearTablaUsuarios() {
		Statement stmt; 

		String tUsuarios = "CREATE TABLE Usuarios (\r\n" 
		+ "    idUsuarios INT PRIMARY KEY,\r\n"
				+ "    Nombre VARCHAR(45),\r\n" 
		+ "    Apellidos VARCHAR(45),\r\n" 
				+ "    Username VARCHAR(30),\r\n"
				+ "    Password VARCHAR(128),\r\n" 
				+ "    email VARCHAR(50)\r\n" + ");";
		
		try {
			stmt = AdminSql.conn.createStatement();
			if (stmt.executeUpdate(tUsuarios) == 0) {
				System.out.println("Tabla Usuarios creada con exito!");
			}
		}catch (SQLException e) {

			System.out.println("La tabla que desea crear ya existe.");
			Utils.espacio();
		}
	}
	
	public static void insertMakarooUsuarios() {
		// C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo = "src\\bbdd\\Usuarios.sql";
		String lineaActual = "";
		try {
			BufferedReader archivo = new BufferedReader(new FileReader(rutaArchivo));
			Statement stmt = AdminSql.conn.createStatement();
			while ((lineaActual = archivo.readLine()) != null) {
				if(stmt.executeUpdate(lineaActual) == 0){
					System.out.println("Existen duplicados");
				}
			}
			archivo.close();
		} catch (IOException | SQLException e) {
			System.out.println("Se ha producido un error");
			System.out.println(e.getMessage());
		} finally {

		}
	}

	public static void listarUsuarios() {
	Statement stmt;
	ResultSet rs;
	String sentencia = "SELECT * FROM Usuarios";
	try {
		stmt = AdminSql.conn.createStatement();
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
		System.out.println(e.getMessage()); 
	}
}

	public static void insertarUsuarios() {
	String idUsuarios = "";
	String Nombre = "";
	String Apellidos = "";
	String Username = "";
	String Password = "";
	String email = "";

	String sentencia = "";

	System.out.println("Introduzca el id del usuario:");
	idUsuarios = AdminSql.lee.nextLine();

	System.out.println("Introduzca el Nombre:");
	Nombre = AdminSql.lee.nextLine();

	System.out.println("Introduzca el Apellido:");
	Apellidos = AdminSql.lee.nextLine();

	System.out.println("Introduzca el Username:");
	Username = AdminSql.lee.nextLine();

	System.out.println("Introduzca el Password:");
	Password = AdminSql.lee.nextLine();

	System.out.println("Introduzca el Email:");
	email = AdminSql.lee.nextLine();

	sentencia = "INSERT INTO Usuarios (idUsuarios, Nombre, Apellidos, Username, Password, email) VALUES ('"
			+ idUsuarios + "', '" + Nombre + "', '" + Apellidos + "', '" + Username + "', '" + Password + "', '"
			+ email + "')";

	try {
		Statement stmt = AdminSql.conn.createStatement();
		stmt.executeUpdate(sentencia);
		System.out.println("Sentencia ejecutada correctamente.");
	} catch (SQLException e) {
		// System.out.println("Error en el formato del campo: "+e.toString());
		System.out.println("Se ha producido un error:");
		System.out.println(e.getMessage());
	}
}
	
	public static void modificarUsuarios() {

		String idUsuario = "";
		String Nombre = "";
		String Apellidos = "";
		String Username = "";
		String Password = "";
		String email = "";
		String nuevoid = "";

		String sentencia = "";

		System.out.println("Introduzca el id del usuario a modificar:");
		idUsuario = AdminSql.lee.nextLine();

		System.out.println("Introduzca el nuevo idUsuario:");
		nuevoid = AdminSql.lee.nextLine();

		System.out.println("Introduzca Nombre:");
		Nombre = AdminSql.lee.nextLine();

		System.out.println("Introduzca el Apellido:");
		Apellidos = AdminSql.lee.nextLine();

		System.out.println("Introduzca el Username:");
		Username = AdminSql.lee.nextLine();

		System.out.println("Introduzca el Password:");
		Password = AdminSql.lee.nextLine();

		System.out.println("Introduzca el Email:");
		email = AdminSql.lee.nextLine();

		sentencia = "UPDATE Usuarios SET idUsuarios='" + nuevoid + "'";
		sentencia += ",Nombre='" + Nombre + "'";
		sentencia += ",Apellidos='" + Apellidos + "'";
		sentencia += ",Username='" + Username + "'";
		sentencia += ",Password='" + Password + "'";
		sentencia += ",email='" + email + "'";
		sentencia += " WHERE idUsuarios=" + idUsuario;

		try {
			Statement stmt = AdminSql.conn.createStatement();
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
	
	public static void borrarUsuarios() {
		Statement stmt;
		int idUser=0;
		System.out.println("Introduzca el id del usuario que quiere eliminar:");
		idUser = AdminSql.lee.nextInt();
		
		String sentencia = "DELETE FROM Usuarios WHERE idUsuarios="+idUser;
		try {
			stmt = AdminSql.conn.createStatement();
			if(stmt.executeUpdate(sentencia)==0) {
				System.out.println("No se ha podido eliminar el usuario");
			}else {
				System.out.println("Usuario eliminado");
			}
		}catch(SQLException e){
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}
		
		
	}

}//Fin de la clase