package bbdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClsPosts {
	
	public static void crearTablaPosts() {
		Statement stmt; 

		String tPosts = "CREATE TABLE Posts (\r\n" 
				+ "    idPosts INT PRIMARY KEY,\r\n" 
						+ "    idUsuarios INT,\r\n"
						+ "    created_at DATE,\r\n" 
						+ "    updated_at DATE,\r\n"
						+ " FOREIGN KEY (idUsuarios) REFERENCES Usuarios(idUsuarios)\r\n" 
						+ " ON DELETE CASCADE\r\n"
						+ " ON UPDATE CASCADE);";
		
		try {
			stmt = AdminSql.conn.createStatement();
			if (stmt.executeUpdate(tPosts) == 0) {
				System.out.println("Tabla Posts creada con exito!");
			}
		}catch (SQLException e) {

			System.out.println("La tabla que desea crear ya existe.");
			Utils.espacio();
		}
	}

	public static void insertMakarooPosts() {
		// C:\Users\lcardozo\git\BBDD\Badat\src\bbdd\Usuarios.sql
		String rutaArchivo = "src\\bbdd\\Posts.sql";
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
	
	public static void listarPosts() {
		Statement stmt;
		ResultSet rs;
		String sentencia = "SELECT * FROM Posts";
		try {
			stmt = AdminSql.conn.createStatement();
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
			System.out.println(e.getMessage()); 
		}
	}
	
	public static void modificarPosts() {
		String idPost = "";
		String idUsuario = "";
		String fcreacion = "";
		String fupdate = "";
		String sentencia = "";
		String nuevoid = "";

		System.out.println("Introduzca el id del Post:");
		idPost = AdminSql.lee.nextLine();

		System.out.println("Introduzca el NUEVO id del Post:");
		nuevoid = AdminSql.lee.nextLine();

		System.out.println("Introduzca el id del Usuario:");
		idUsuario = AdminSql.lee.nextLine();

		System.out.println("Introduzca la fecha de creacion del post:");
		fcreacion = Utils.fecha();

		System.out.println("Introduzca la fecha de actualizacion del post:");
		fupdate = Utils.fecha();

		sentencia = "UPDATE Posts SET idPosts='" + nuevoid + "'";
		sentencia += ",idUsuario='" + idUsuario + "'";
		sentencia += ",fcreacion='" + fcreacion + "'";
		sentencia += ",fupdate='" + fupdate + "'";
		sentencia += " WHERE idPosts=" + idPost;
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
	
	public static void insertarPosts() {
		System.out.println("Introduzca el id del Post:");
		String idPost = AdminSql.lee.nextLine();

		System.out.println("Introduzca el id del Usuario:");
		String idUsuario = AdminSql.lee.nextLine();

		System.out.println("Introduzca la fecha de creacion del post:");
		String fcreacion = Utils.fecha();

		System.out.println("Introduzca la fecha de actualizacion del post:");
		String fupdate = Utils.fecha();

		String sentencia = "INSERT INTO Posts (idPosts, idUsuarios, created_at, updated_at) VALUES ('" + idPost + "', '"
				+ idUsuario + "', " + fcreacion + ", " + fupdate + ")";
		System.out.println(sentencia);
		try {
			Statement stmt = AdminSql.conn.createStatement();
			stmt.execute(sentencia);
			System.out.println("Sentencia ejecutada correctamente.");
		} catch (SQLException e) {
			System.out.println("Se ha producido un error:" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void borrarPosts() {
		Statement stmt;
		int idPosts=0;
		System.out.println("Introduzca el id del post que quiere eliminar:");
		idPosts = AdminSql.lee.nextInt();
		
		String sentencia = "DELETE FROM Posts WHERE idPosts="+idPosts;
		try {
			stmt = AdminSql.conn.createStatement();
			if(stmt.executeUpdate(sentencia)==0) {
				System.out.println("No se ha podido eliminar el post");
			}else {
				System.out.println("Post eliminado");
			}
		}catch(SQLException e){
			System.out.println("Se ha producido un error:");
			System.out.println(e.getMessage());
		}
		
		
	}

}//Fin de la clase POSTS
