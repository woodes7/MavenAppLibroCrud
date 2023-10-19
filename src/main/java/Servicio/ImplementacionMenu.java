package Servicio;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Entidad.LibroDto;
import Servicio.ImplementacionConexion;
import Servicio.ImplementacionConsulta;
import Servicio.InterfazConexion;
import Servicio.InterfazConsulta;
import Util.MaxLenghtStringException;

public class ImplementacionMenu implements InterfazMenu {

	List<LibroDto> Libros = new ArrayList<LibroDto>();
	Scanner entrada = new Scanner(System.in);
	InterfazConexion conexion = new ImplementacionConexion();
	InterfazConsulta consulta = new ImplementacionConsulta();
	@Override
	public void MostrarMenu() {
		// TODO Auto-generated method stub

		System.out.println("Elige un opción de Menu");
		System.out.println("---------Menu--------------");
		System.out.println("1. Selecionar libro");
		System.out.println("2. Selecionar libros");
		System.out.println("3. Inertar libro");
		System.out.println("4. Modificar libro");
		System.out.println("5. Borrar libro");
	}

	String titulo, autor, isbn;
	int edicion;

	
	public void MenuSeleccionarTodosLirbos() {
		List<LibroDto> libros = consulta.SelecionarTodosLosLibros();
		for (int i = 0; i < libros.size(); i++) {
			System.out.println("ID : " + libros.get(i).getId_Libro());
			System.out.println("Titulo : " + libros.get(i).getTitulo());
			System.out.println("Autor : " + libros.get(i).getAutor());
			System.out.println("ISBN : " + libros.get(i).getIsbn());
			System.out.println("Edicion : " + libros.get(i).getEdicion());
		}
	}

	public void MenuSeleccionarUnLirbo() {
		try {
			entrada.nextLine();
			System.out.println("------------------------------------");
			System.out.println("Introduzca isbn:");
			isbn = entrada.nextLine();
			LibroDto libro = consulta.SelecionarUnLibro(isbn);
			System.out.println("ID : " + libro.getId_Libro());
			System.out.println("Titulo : " + libro.getTitulo());
			System.out.println("Autor : " + libro.getAutor());
			System.out.println("ISBN : " + libro.getIsbn());
			System.out.println("Edicion : " + libro.getEdicion());
			System.out.println("------------------------------------");
		} catch (InputMismatchException e) {
			System.out.println("Monstruo, te has equivocado y el tipo escrito no coincide, maquina");
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	
	public void MenuInsertarLibro() {
	    try {
	        entrada.nextLine(); // Consumir carácter de nueva línea
	        System.out.println("Introduzca título:");
	        String titulo = entrada.nextLine();
	        if (titulo.isEmpty()) {
	            throw new IllegalArgumentException("El título no puede estar vacío.");
	        }
	        if (titulo.length() > 70) {
	            throw new MaxLenghtStringException("El título no puede superar los 70 caracteres.");
	        }

	        System.out.println("Introduzca autor:");
	        String autor = entrada.nextLine();
	        if (autor.isEmpty()) {
	            throw new IllegalArgumentException("El autor no puede estar vacío.");
	        }
	        if (autor.length() > 50) {
	            throw new MaxLenghtStringException("El autor no puede superar los 50 caracteres.");
	        }

	        System.out.println("Introduzca ISBN:");
	        String isbn = entrada.nextLine();
	        if (isbn.isEmpty()) {
	            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
	        }
	        if (isbn.length() > 13) {
	            throw new MaxLenghtStringException("El ISBN no puede superar los 13 caracteres.");
	        }

	        System.out.println("Introduzca edición:");
	        int edicion = entrada.nextInt();

	        LibroDto libroInsertar = new LibroDto(titulo, autor, isbn, edicion);
	        Libros.add(libroInsertar);

	        System.out.println("Libro insertado correctamente.");
	    } catch (InputMismatchException e) {
	        System.out.println("Error: el tipo de dato ingresado no es válido.");
	    } catch (MaxLenghtStringException e) {
	        System.out.println(e.getMessage());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error: " + e.getMessage());
	    } catch (Exception e) {
	        System.out.println("Se produjo un error: " + e.getMessage());
	    }
	}
	
	public void MenuModificarLibro() {
		String titulo = "";
		String autor = "";
		String isbn = "";
		int edicion = 0;
		try {
			entrada.nextLine();
			System.out.println("Introduzca isbn del libro a modificar:");
			String isbn_busqueda = entrada.nextLine();
			LibroDto libro_modificable = consulta.SelecionarUnLibro(isbn_busqueda);
			if (libro_modificable != null) {
				System.out.println("Quieres modificarlo? 's' o 'n'");
				String opc = entrada.nextLine();
				if (opc.equals("s")) {
					
					System.out.println("Quieres modificar el titulo? 's' o 'n'");
					String opcCampo = entrada.nextLine();
					if(opcCampo.equals("s")) {
						System.out.println("Introduzca titulo");
						titulo = entrada.nextLine();
					}else
						titulo = libro_modificable.getTitulo();
					
					System.out.println("Quieres modificar el autor? 's' o 'n'");
					opcCampo = entrada.nextLine();
					if(opcCampo.equals("s")) {
						System.out.println("Introduzca autor");
						autor = entrada.nextLine();
					}else
						autor = libro_modificable.getAutor();
					
					System.out.println("Quieres modificar el isbn? 's' o 'n'");
					opcCampo = entrada.nextLine();
					if(opcCampo.equals("s")) {
						System.out.println("Introduzca isbn:");
						isbn = entrada.nextLine();
					}else
						isbn = libro_modificable.getIsbn();
					
					System.out.println("Quieres modificar el edicion? 's' o 'n'");
					opcCampo = entrada.nextLine();
					if(opcCampo.equals("s")) {
						System.out.println("Introduzca edicion:");
						edicion = entrada.nextInt();
					}else
						edicion = libro_modificable.getEdicion();
					
				}
				LibroDto libro = new LibroDto(libro_modificable.getId_Libro(), titulo, autor, isbn, edicion);
				boolean modifica = consulta.ModificarLibro(isbn_busqueda, libro);
				if (modifica)
					System.out.println("El libro con ISBN " + isbn_busqueda + " ha sido modificado correctamente");

			} else
				System.out.println("El libro con ISBN " + isbn_busqueda + " no existe");
		} catch (InputMismatchException e) {
			System.out.println("Monstruo, te has equivocado y el tipo escrito no coincide, maquina");
		} catch (NullPointerException eN) {
			System.out.println(eN.getMessage());
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	public void MenuBorrarLibro() {
		try {
			entrada.nextLine();
			System.out.println("Introduzca isbn del libro a eliminar:");
			String isbn_busqueda_elimina = entrada.nextLine();
			LibroDto libro_eliminar = consulta.SelecionarUnLibro(isbn);
			if (libro_eliminar != null) {
				boolean elimina = consulta.DeleteLibro(isbn_busqueda_elimina);
				if (elimina)
					System.out
							.println("El libro con ISBN " + isbn_busqueda_elimina + " ha sido eliminado correctamente");
			} else
				System.out.println("El libro con ISBN " + isbn_busqueda_elimina + " no existe");
		} catch (InputMismatchException e) {
			System.out.println("Monstruo, te has equivocado y el tipo escrito no coincide, maquina");
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

}
