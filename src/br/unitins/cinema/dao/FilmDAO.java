package br.unitins.cinema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Film;
import br.unitins.cinema.model.MovieGenre;

public class FilmDAO extends DAO<Film>  {
	
	@Override
	public boolean create(Film obj) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO film ( "
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
			stat.setInt(3, obj.getDuration());
			stat.setString(4, obj.getDirector());
			stat.setInt(5, obj.getReleaseYear());
			
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
	public boolean update(Film obj) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE film SET "
												   + "  name = ?, "
												   + "  movie_genre = ?, "
												   + "  duration = ?, "
												   + "  director = ?, "
												   + "  release_year = ?  " 
												   + "WHERE id = ? ");
			stat.setString(1, obj.getName());
			stat.setInt(2, obj.getMovieGenre().getValue());
			stat.setInt(3, obj.getDuration());
			stat.setString(4, obj.getDirector());
			stat.setInt(5, obj.getReleaseYear());
			
			stat.setInt(6, obj.getId());
			
			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
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
			stat =	getConnection().prepareStatement("DELETE FROM film WHERE id = ? ");
			stat.setInt(1, id);
			
			stat.execute();
			Util.addMessageError("Exclusão realizada com sucesso!");
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
	public Film findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Film film = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM film WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				film = new Film();
				
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				film.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				film.setDuration(rs.getInt("duration"));
				film.setDirector(rs.getString("director"));
				film.setReleaseYear(rs.getInt("release_year"));
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
		return film;
	}

	@Override
	public List<Film> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Film> listFilm = new ArrayList<Film>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM film");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Film film = new Film();
				
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				film.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				film.setDuration(rs.getInt("duration"));
				film.setDirector(rs.getString("director"));
				film.setReleaseYear(rs.getInt("release_year"));				

				listFilm.add(film);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listFilm = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listFilm;
	}
}