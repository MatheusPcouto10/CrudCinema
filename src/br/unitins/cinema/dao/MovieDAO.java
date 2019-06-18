package br.unitins.cinema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Movie;
import br.unitins.cinema.model.MovieGenre;

public class MovieDAO extends DAO<Movie>  {
	
	@Override
	public boolean create(Movie obj) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO movie ( "
										+ "  name, "
										+ "  movie_genre, "
										+ "  duration, "
										+ "  director, "
										+ "  release_year ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ? ) ");
			stat.setString(1, obj.getName());
			stat.setInt(2, obj.getMovieGenre().getValue());
			stat.setString(3, obj.getDuration());
			stat.setString(4, obj.getDirector());
			stat.setString(5, obj.getReleaseYear());
			
			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			result = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao incluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(Movie obj) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE movie SET "
												   + "  name = ?, "
												   + "  movie_genre = ?, "
												   + "  duration = ?, "
												   + "  director = ?, "
												   + "  release_year = ?  " 
												   + "WHERE id = ? ");
			stat.setString(1, obj.getName());
			stat.setInt(2, obj.getMovieGenre().getValue());
			stat.setString(3, obj.getDuration());
			stat.setString(4, obj.getDirector());
			stat.setString(5, obj.getReleaseYear());
			
			stat.setInt(6, obj.getId());
			
			stat.execute();
			Util.addMessageError("Alteracao realizada com sucesso!");
			result = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Alterar.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}

	@Override
	public boolean delete(int id) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("DELETE FROM movie WHERE id = ? ");
			stat.setInt(1, id);
			
			stat.execute();
			Util.addMessageError("Exclusao realizada com sucesso!");
			result = true;
		} catch (SQLException e) {
			Util.addMessageError("Falha ao Excluir.");
			e.printStackTrace();
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Movie findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Movie movie = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM movie WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				movie = new Movie();
				
				movie.setId(rs.getInt("id"));
				movie.setName(rs.getString("name"));
				movie.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				movie.setDuration(rs.getString("duration"));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseYear(rs.getString("release_year"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return movie;
	}

	@Override
	public List<Movie> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Movie> listMovie = new ArrayList<Movie>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM movie");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Movie movie = new Movie();
				
				movie.setId(rs.getInt("id"));
				movie.setName(rs.getString("name"));
				movie.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				movie.setDuration(rs.getString("duration"));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseYear(rs.getString("release_year"));				

				listMovie.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listMovie = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listMovie;
	}
	
	
	public List<Movie> findByName(String name) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Movie> listMovie = new ArrayList<Movie>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM movie WHERE name ILIKE ?");
			stat.setString(1, (name == null? "%" : "%"+name+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Movie movie = new Movie();
				
				movie.setId(rs.getInt("id"));
				movie.setName(rs.getString("name"));
				movie.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				movie.setDuration(rs.getString("duration"));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseYear(rs.getString("release_year"));
				
				listMovie.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listMovie = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listMovie;

	}
	
}