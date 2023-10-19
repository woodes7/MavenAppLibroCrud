package Servicio;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImplementacionConexion implements InterfazConexion {

	@Override
	public Connection EstablecerConexionConBD() {

		Connection conexion = null;
		String[] parametrosConexion = null;
		// Se controla que los parámetros de conexión se completen
		try {
			parametrosConexion = configuracionConexion(); // url, user, pass

			if (!parametrosConexion[2].isEmpty()) {

				// Instancia un objeto de la clase interfaz que se le pasa
				Class.forName("org.postgresql.Driver");

				// Se establece la conexión
				// Si pgadmin no tiene abierta la bd, no será posible establecer conexion contra
				// ella
				conexion = DriverManager.getConnection(parametrosConexion[0], parametrosConexion[1],
						parametrosConexion[2]);
				// IsValid te da true o false dependiendo si la bd esta conectada o no (te hace
				// un "pitido" de 5s a la bd)
				boolean esValida = conexion.isValid(50000);
				if (esValida == false) {
					conexion = null;
				}
				System.out.println(esValida
						? "[INFORMACIÓN-ImplementacionConexionBBDD-AbrirConexionConBBDD] Conexión a la base de datos válida"
						: "[ERROR-ImplementacionConexionBBDD-AbrirConexionConBBDD] Conexión a la base de datos no válida");
				return conexion ;
			} else {
				System.err.println(
						"[ERROR-ImplementacionConexionBBDD-AbrirConexionConBBDD] Los parametros de conexion no se han establecido correctamente");
				return conexion;
			}
		} catch (ClassNotFoundException driver) {
			System.err.println(
					"[ERROR-ImplementacionConexionBBDD-AbrirConexionConBBDD] Error en registro driver en la base de datos: "
							+ driver);
			return conexion = null;
		} catch (SQLException datos) {
			System.err.println(
					"[ERROR-ImplementacionConexionBBDD-AbrirConexionConBBDD] Error en conexión a la base de datos ("
							+ parametrosConexion[0] + "): " + datos);
			return conexion = null;
		} catch (NullPointerException nullo) {
			System.err
					.println("[ERROR-ImplementacionConexionBBDD-AbrirConexionConBBDD] Error con los datos de conexión");
			return conexion = null;
		}
	}
	 

	@Override
	public void CerrarConexionConBD(Connection conexion) {
		// TODO Auto-generated method stub
		
	}

	private String[] configuracionConexion() {

		String user = "", pass = "", port = "", host = "", db = "", url = "";

		Properties propiedadesConexionPostgresql = new Properties();
		try {
			// propiedadesConexionPostgresql.load(getClass().getResourceAsStream("conexion_postgresql.properties"));
			propiedadesConexionPostgresql.load(new FileInputStream(new File(
					"C:\\Users\\Puesto4\\Desktop\\Pablo\\DWS\\CrudConexion.AppLibroCrud\\src\\main\\java\\Util\\conexion_postgresql.properties.sql")));
			user = propiedadesConexionPostgresql.getProperty("user");
			pass = propiedadesConexionPostgresql.getProperty("pass");
			port = propiedadesConexionPostgresql.getProperty("port");
			host = propiedadesConexionPostgresql.getProperty("host");
			db = propiedadesConexionPostgresql.getProperty("db");
			url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			String[] stringConfiguracion = { url, user, pass };

			return stringConfiguracion;

		} catch (Exception e) {
			System.out.println(
					"[ERROR-ConexionPostgresqlImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			return null;
		}

	}

}
