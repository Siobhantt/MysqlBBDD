package bbdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClsLikes {
	
	public static void crearTablaLikes() {
		Statement stmt; 

		String tLikes = "CREATE TABLE Likes (\r\n" 
				+ "    idLikes INT PRIMARY KEY,\r\n" 
				+ "    idUsuarios INT,\r\n"
				+ "    idPosts INT,\r\n" 
				+ " FOREIGN KEY (idUsuarios) REFERENCES Usuarios(idUsuarios)\r\n"
				+ " ON DELETE CASCADE\r\n"
				+ "	ON UPDATE CASCADE,"
				+ " FOREIGN KEY (idPosts) REFERENCES Posts(idPosts)\r\n" 
				+ " ON DELETE CASCADE\r\n"
				+ " ON UPDATE CASCADE);";
		
		try {
			stmt = AdminSql.conn.createStatement();
			if (stmt.executeUpdate(tLikes) == 0) {
				System.out.println("Tabla Likes creada con exito!");
			}
		}catch (SQLException e) {
			System.out.println("La tabla que desea crear ya existe.");
			System.out.println(e.getMessage());
			Utils.espacio();
		}
	}

	public static void insertMakarooLikes() {
		// C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo = "src\\bbdd\\Likes.sql";
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
			System.out.println(e.getMessage()); 
		} finally {

		}

	}
	
	public static void listarLikes() {
		Statement stmt;
		ResultSet rs;
		String sentencia = "SELECT * FROM Likes";
		try {
			stmt = AdminSql.conn.createStatement();
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
			System.out.println(e.getMessage()); 
		}
	}
	
	public static void modificarLikes() {
		String idLikes = "";
		String idUsuarios = "";
		String idPost = "";
		String nuevoid = "";

		String sentencia = "";

		System.out.println("Introduzca el id del Like:");
		idLikes = AdminSql.lee.nextLine();

		System.out.println("Introduzca el NUEVO id del Like:");
		nuevoid = AdminSql.lee.nextLine();

		System.out.println("Introduzca el id del usuario:");
		idUsuarios = AdminSql.lee.nextLine();

		System.out.println("Introduzca el id del Post:");
		idPost = AdminSql.lee.nextLine();

		sentencia = "UPDATE Likes SET idLikes='" + nuevoid + "'";
		sentencia += ",idUsuarios='" + idUsuarios + "'";
		sentencia += ",idPosts='" + idPost + "'";
		sentencia += " WHERE idLikes=" + idLikes;

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
	
	public static void insertarLikes() {
		String idLikes = "";
		String idUsuarios = "";
		String idPost = "";

		String sentencia = "";

		System.out.println("Introduzca el id del Like:");
		idLikes = AdminSql.lee.nextLine();

		System.out.println("Introduzca el id del usuario:");
		idUsuarios = AdminSql.lee.nextLine();

		System.out.println("Introduzca el id del Post:");
		idPost = AdminSql.lee.nextLine();

		sentencia = "INSERT INTO Likes (idLikes, idUsuarios, idPosts) VALUES ('" + idLikes + "', '" + idUsuarios + "','"
				+ idPost + "')";
		try {
			Statement stmt = AdminSql.conn.createStatement();
			stmt.executeUpdate(sentencia);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			System.out.println("Se ha producido un error:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void borrarLike() {
		Statement stmt;
		int idLike=0;
		System.out.println("Introduzca el id del like que quiere eliminar:");
		idLike = AdminSql.lee.nextInt();
		
		String sentencia = "DELETE FROM Likes WHERE idLikes="+idLike;
		try {
			stmt = AdminSql.conn.createStatement();
			if(stmt.executeUpdate(sentencia)==0) {
				System.out.println("No se ha podido eliminar el like");
			}else {
				System.out.println("Like eliminado");
			}
		}catch(SQLException e){
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}
		
		
	}

}//Fin de clase LIKES
