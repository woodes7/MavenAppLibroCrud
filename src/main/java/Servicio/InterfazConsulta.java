package Servicio;

import java.util.List;

import Entidad.LibroDto;
/**
 * Interfaz para llamar los metodos de Consulta
 * @author PabloRG
 */

public interface InterfazConsulta {

	/**
	 * Metodo que seleciona todos los libros
	 * @return
	 */
	public List<LibroDto> SelecionarTodosLosLibros();
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public LibroDto SelecionarUnLibro(String isbn);
	/**
	 * 
	 * @param libro
	 * @return
	 */
	public boolean AgregarLibro(LibroDto libro);
	/**
	 * 
	 * @param isbn
	 * @return
	 */
	public boolean DeleteLibro(String isbn);
	/**
	 * 
	 * @param isbn
	 * @param libro
	 * @return
	 */
	public boolean ModificarLibro(String isbn, LibroDto libro);
	
}
