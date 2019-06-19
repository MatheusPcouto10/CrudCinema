package br.unitins.cinema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.MovieGenre;
import br.unitins.cinema.model.Servico;

public class ServicoDAO extends DAO<Servico>  {
	
	@Override
	public boolean create(Servico obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO servico ( "
					                    + "  titulo, "
					                    + "  movie_genre, "
					                    + "  duration, "
					                    + "  director, "
					                    + "  release_year, "
										+ "  descricao, "
										+ "  valor ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ? ) ");
			
			stat.setString(1, obj.getTitulo());
			stat.setInt(2, obj.getMovieGenre().getValue());
			stat.setString(3, obj.getDuration());
			stat.setString(4, obj.getDirector());
			stat.setString(5, obj.getReleaseYear());
			stat.setString(6, obj.getDescricao());
			stat.setDouble(7, obj.getValor());
			
			stat.execute();
			Util.addMessageError("Cadastro realizado com sucesso!");
			resultado = true;
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
		return resultado;
	}

	@Override
	public boolean update(Servico obj) {
boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE servico SET "
                                                   + "  titulo = ?, "
                                                   + "  movie_genre = ?, "
                                                   + "  duration = ?, "
                                                   + "  director = ?, "
                                                   + "  release_year = ?, "
                                                   + "  descricao = ?, "
                                                   + "  valor = ? "
                                                   + "WHERE id = ? ");
			
            stat.setString(1, obj.getTitulo());
            stat.setInt(2, obj.getMovieGenre().getValue());
            stat.setString(3, obj.getDuration());
            stat.setString(4, obj.getDirector());
            stat.setString(5, obj.getReleaseYear());
            stat.setString(6, obj.getDescricao());
            stat.setDouble(7, obj.getValor());

			stat.execute();
			Util.addMessageError("Alteração realizada com sucesso!");
			resultado = true;
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
		return resultado;
		
	}

	@Override
	public boolean delete(int id) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("DELETE FROM servico WHERE id = ? ");
			stat.setInt(1, id);
			
			stat.execute();
			Util.addMessageError("Exclusï¿½o realizada com sucesso!");
			resultado = true;
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
		return resultado;
	}

	@Override
	public Servico findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Servico servico = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM servico WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setTitulo(rs.getString("titulo"));
				servico.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				servico.setDuration(rs.getString("duration"));
				servico.setDirector(rs.getString("director"));
				servico.setReleaseYear(rs.getString("release_year"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getDouble("valor"));
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
		return servico;
	}

	@Override
	public List<Servico> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Servico> listaServico = new ArrayList<Servico>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM servico");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Servico servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setTitulo(rs.getString("titulo"));
				servico.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				servico.setDuration(rs.getString("duration"));
				servico.setDirector(rs.getString("director"));
				servico.setReleaseYear(rs.getString("release_year"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getDouble("valor"));

				listaServico.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaServico = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaServico;
	}
	
	public List<Servico> findByTitulo(String titulo) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Servico> listaServico = new ArrayList<Servico>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM servico WHERE titulo ILIKE ?");
			stat.setString(1, (titulo == null? "%" : "%"+titulo+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Servico servico = new Servico();
				servico.setId(rs.getInt("id"));
				servico.setTitulo(rs.getString("titulo"));
				servico.setMovieGenre(MovieGenre.valueOf(rs.getInt("movie_genre")));
				servico.setDuration(rs.getString("duration"));
				servico.setDirector(rs.getString("director"));
				servico.setReleaseYear(rs.getString("release_year"));
				servico.setDescricao(rs.getString("descricao"));
				servico.setValor(rs.getDouble("valor"));
				listaServico.add(servico);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listaServico = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listaServico;

	}

}

