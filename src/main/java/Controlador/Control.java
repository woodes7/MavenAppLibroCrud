package Controlador;
import Servicio.InterfazConexion;
import Servicio.InterfazConsulta;
import Servicio.InterfazMenu;
import Servicio.ImplementacionConexion;
import Servicio.ImplementacionConsulta;
import Servicio.ImplementacionMenu;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Entidad.LibroDto;

public class Control {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InterfazConexion Implconexion = new ImplementacionConexion();		
		InterfazConsulta Implconsulta = new ImplementacionConsulta();
		InterfazMenu Implmenu = new ImplementacionMenu();
		Implconexion.EstablecerConexionConBD();
		
		List<LibroDto> Libros = new ArrayList<LibroDto>();
		Scanner entrada = new Scanner(System.in);
		boolean opcionValida = false;
			  	      
	        // Menú donde elegimos la opción y mandará mensaje según lo que suceda
	        do {
	        	
	            try {
	                // Mostramos el menú
	            	Implmenu.MostrarMenu();
	            	
	                System.out.println("Elige una opción");
	                int opcion = entrada.nextInt();

	                switch (opcion) {
	                    case 0:
	                        opcionValida = true;
	                        System.out.println("Adiós");
	                        break;
	                    case 1:
	                    	Implmenu.MenuSeleccionarUnLirbo();
	                        break;
	                    case 2:
	                    	Implmenu.MenuSeleccionarTodosLirbos();
	                        break;
	                    case 3:
	                    	Implmenu.MenuInsertarLibro();
	                        break;
	                    case 4:
	                    	Implmenu.MenuModificarLibro();
	                        break;
	                    case 5:
	                    	Implmenu.MenuBorrarLibro();
	                        break;
	                    default:
	                        System.out.println("Error: Opción inválida. Por favor, introduce un número válido.");
	                        break;
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("Error: Debes introducir un número.");
	                entrada.nextLine(); // Limpiar el búfer del teclado
	            } catch (Exception e) {
	                System.out.println("Se produjo un error: " + e.getMessage());
	            }
	        } while (!opcionValida);

	        // Nos aseguramos de cerrar entrada
	        entrada.close();
	    }
	}


