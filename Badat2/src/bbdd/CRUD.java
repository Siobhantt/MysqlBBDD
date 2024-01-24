package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CRUD {
	public static Connection conectar() {
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

	public static void insertarAll() {
		int opcInsert;
		int opcS;
		// Insertar datos MOCKAROO
		System.out.println("Desea insertar un registro? o Datos de Makaroo?");
		System.out.println("1) Un registro Ó 2)Datos de Makaroo");
		Utils.espacio();
		
		opcInsert = AdminSql.lee.nextInt();
		if (opcInsert == 2) {
			System.out.println("INSERTAR DATOS MAKAROO");
			Utils.espacio();
			Utils.mensajeInsertar();
			opcS = AdminSql.lee.nextInt();
			AdminSql.lee.nextLine();
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
		} else {
			Utils.mensajeInsertar();
			opcS = AdminSql.lee.nextInt();
			AdminSql.lee.nextLine();
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
		
		}
	}


	public static void borrarAll() {
		int opcS;
		Utils.mensajeBorrar();
		opcS=AdminSql.lee.nextInt();
		AdminSql.lee.nextLine();
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
		}
	}


	public static void modificarAll() {
		int opcS;
		Utils.mensajeModificar();
		opcS = AdminSql.lee.nextInt();
		AdminSql.lee.nextLine();
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
		}
	}


	public static void listarAll() {
		int opcS;
		Utils.mensajeListar();
		opcS = AdminSql.lee.nextInt();

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
		}
	}


	public static void creaTablas() {
		int opcS;
		Utils.mensajeCrear();
		opcS = AdminSql.lee.nextInt();
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
		}//Fin del switch
	}

	
}//Fin del crud
