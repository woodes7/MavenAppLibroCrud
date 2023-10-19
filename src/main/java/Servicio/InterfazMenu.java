package Servicio;

public interface InterfazMenu {
	/**
	 * Metodo para motrar menu
	 */
	public void MostrarMenu();	
	/**
	 * Metodo llama y selecionar todos los libros
	 */	
	public void MenuSeleccionarTodosLirbos();	
	/**
	 * Metodo que pide un isbn de un libro para llamar y selecionar un libro pide datos 
	 */	
	public void MenuSeleccionarUnLirbo();
	/**
	 * Metodo del menu que pide datos de libros y llama para insertar libros
	 */
	public void MenuInsertarLibro();
	/**
	 * Metodo del menu que pide el isbn para buscar libros y llama para modificar un libro
	 */
	public void MenuModificarLibro();
	/**
	 * Metodo del menu que pide el isbn para buscar libros y llama borrar un libro
	 */
	public void MenuBorrarLibro();
}
