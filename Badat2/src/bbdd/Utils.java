package bbdd;

public class Utils {

	public static void espacio() {
		System.out.println("");
	}

	public static String fecha() {
		String dia;
		String mes;
		String anio;
		String fecha;
		System.out.println("Introduzca el dia:");
		dia = AdminSql.lee.nextLine();
		System.out.println("Introduzca el mes:");
		mes = AdminSql.lee.nextLine();
		System.out.println("Introduzca el anio:");
		anio = AdminSql.lee.nextLine();
		fecha = "'" + anio + "-" + mes + "-" + dia + "'";
		return fecha;
	}

	public static void verificarEmail() {
		// TODO
	}

	public static void mensajeCrear() {
		System.out.println("Que tabla desea crear?");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");
	}
	
	public static void mensajeBorrar() {
		System.out.println("Introduzca la tabla de la cual quiere eliminar los datos:");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");
	}

	public static void mensajeModificar() {
		System.out.println("Seleccione la tabla a la que quiere modificar datos:");
		System.out.println("1)Usuarios");
		System.out.println("2)Posts");
		System.out.println("3)Likes");

	}

	public static void mensajeInsertar() {
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
				+ "3) Listar.\n" + "4) Modificar datos.\n" + "5) Borrar datos.\n" + "0) Salir.");
	}

}// Fin de clase utils
