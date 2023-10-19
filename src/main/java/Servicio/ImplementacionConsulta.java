package Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Entidad.LibroDto;

public class ImplementacionConsulta implements InterfazConsulta {
	InterfazConexion context = new ImplementacionConexion();	

	@Override
	public List<LibroDto> SelecionarTodosLosLibros() {
		List<LibroDto> libros = new ArrayList<LibroDto>();
		try {
            Connection connection = context.EstablecerConexionConBD();
            
            Statement statement = connection.createStatement();
            
            String sql = "SELECT * FROM gbp_almacen.gbp_alm_cat_libros";
            
            ResultSet resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id_libro");
                String nombre = resultSet.getString("titulo");
                String autor = resultSet.getString("autor");
                String isbn = resultSet.getString("isbn");
                int edicion = resultSet.getInt("edicion");
                
                LibroDto libro = new LibroDto(id, nombre, autor, isbn, edicion);
                libros.add(libro);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return libros;
	}

	@Override
	public LibroDto SelecionarUnLibro(String isbn) {
	        LibroDto libro = new LibroDto();
	        String sql = "SELECT * FROM gbp_almacen.gbp_alm_cat_libros WHERE isbn = ?";
	        
	        try {
	        	Connection connection = context.EstablecerConexionConBD();
	            PreparedStatement preparedStatement = connection.prepareStatement(sql);
	            preparedStatement.setString(1, isbn);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            if (resultSet.next()) {
	                libro = new LibroDto();
	                libro.setId_Libro(resultSet.getLong("id_Libro"));
	                libro.setTitulo(resultSet.getString("titulo"));
	                libro.setAutor(resultSet.getString("autor"));
	                libro.setIsbn(resultSet.getString("isbn"));
	                libro.setEdicion(resultSet.getInt("edicion"));
	                resultSet.close();
		            return libro;
	            }
	            
	           
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	        return null;
	}

	@Override
	public boolean AgregarLibro(LibroDto libro) {
		try {
            Connection connection = context.EstablecerConexionConBD();
            
            Statement statement = connection.createStatement();
            
            String sql = "INSERT INTO gbp_almacen.gbp_alm_cat_libros(titulo, autor, isbn, edicion) values (?, ?, ?, ?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libro.getTitulo());
            preparedStatement.setString(2, libro.getAutor());
            preparedStatement.setString(3, libro.getIsbn());
            preparedStatement.setInt(4, libro.getEdicion());
         
            int filas = preparedStatement.executeUpdate();
            
            if(filas > 0)
            	return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
		
	}

	@Override
	public boolean DeleteLibro(String isbn) {
		
		try {
            Connection connection = context.EstablecerConexionConBD();
            
            Statement statement = connection.createStatement();
            
            String sql = "DELETE FROM gbp_almacen.gbp_alm_cat_libros where isbn = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, isbn);
         
            int filas = preparedStatement.executeUpdate();
            
            if(filas > 0)
            	return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

	@Override
	public boolean ModificarLibro(String isbn, LibroDto libro) {
		
		try {
            Connection connection = context.EstablecerConexionConBD();
            
            Statement statement = connection.createStatement();
            
            String sql = "UPDATE gbp_almacen.gbp_alm_cat_libros SET titulo = ?, autor = ?, isbn = ?, edicion = ? where isbn = ?";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, libro.getTitulo());
            preparedStatement.setString(2, libro.getAutor());
            preparedStatement.setString(3, libro.getIsbn());
            preparedStatement.setInt(4, libro.getEdicion());
            preparedStatement.setString(5, isbn);
         
            int filas = preparedStatement.executeUpdate();
            
            if(filas > 0)
            	return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
	}

}
