package br.unitins.cinema.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;

//public class UsuarioDAO extends DAO<>  {
//	
//	@Override
//	public boolean create(Usuario obj) {
//		boolean resultado = false;
//		
//		// verificando se tem uma conexao valida
//		if (getConnection() == null) {
//			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
//			return false;
//		}
//		
//		PreparedStatement stat = null;
//		try {
//			
//			LocalDate data5 = obj.getDataNascimento();
//			Date data6 = Date.valueOf(data5);
//			
//			//Date data1 = rs.getDate("data_nascimento");
//			//LocalDate data2 = data1.toLocalDate();
//			
//			//System.out.println(data5);
//			//System.out.println(data6);
//			
//			//
//			
//			String hashedSenha = Util.encrypt(obj.getSenha());
//			
//			stat =	getConnection().prepareStatement("INSERT INTO usuarios ( "
//										+ "  login, "
//										+ "  senha, "
//										+ "  nome, "
//										+ "  perfil, "
//										+ "  data_nascimento ) " 
//										+ "VALUES ( "
//										+ " ?, "
//										+ " ?, "
//										+ " ?, "
//										+ " ?, "
//										+ " ? ) ");
//			stat.setString(1, obj.getLogin());
//			stat.setString(2, hashedSenha);
//			stat.setString(3, obj.getNome());
//			stat.setInt(4, obj.getPerfil().getValue());
//			stat.setDate(5, data6);
//			
//			stat.execute();
//			Util.addMessageError("Cadastro realizado com sucesso!");
//			resultado = true;
//		} catch (SQLException e) {
//			Util.addMessageError("Falha ao incluir.");
//			e.printStackTrace();
//		} finally {
//			try {
//				stat.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return resultado;
//	}
//
//	@Override
//	public boolean update(Usuario obj) {
//		boolean resultado = false;
//		
//		// verificando se tem uma conexao valida
//		if (getConnection() == null) {
//			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
//			return false;
//		}
//		
//		PreparedStatement stat = null;
//		try {
//			
//			LocalDate data5 = obj.getDataNascimento();
//			Date data6 = Date.valueOf(data5);
//			
//			//Date data1 = rs.getDate("data_nascimento");
//			//LocalDate data2 = data1.toLocalDate();
//			
//			//System.out.println(data5);
//			//System.out.println(data6);
//			
//			//
//			
//			String hashedSenha = Util.encrypt(obj.getSenha());
//			
//			stat =	getConnection().prepareStatement("UPDATE usuarios SET "
//												   + "  nome = ?, "
//												   + "  login = ?, "
//												   + "  senha = ?, "
//												   + "  perfil = ?, "
//												   + "  data_nascimento = ?  "
//												   + "WHERE id = ? ");
//			stat.setString(1, obj.getNome());
//			stat.setString(2, obj.getLogin());
//			stat.setString(3, hashedSenha);
//			stat.setInt(4, obj.getPerfil().getValue());
//			stat.setDate(5, data6 );
//			stat.setInt(6, obj.getId() );
//			
//			stat.execute();
//			Util.addMessageError("Alteração realizada com sucesso!");
//			resultado = true;
//		} catch (SQLException e) {
//			Util.addMessageError("Falha ao Alterar.");
//			e.printStackTrace();
//		} finally {
//			try {
//				stat.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return resultado;
//		
//	}
//
//	@Override
//	public boolean delete(int id) {
//		boolean resultado = false;
//		
//		// verificando se tem uma conexao valida
//		if (getConnection() == null) {
//			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
//			return false;
//		}
//		
//		PreparedStatement stat = null;
//		try {
//			stat =	getConnection().prepareStatement("DELETE FROM usuarios WHERE id = ? ");
//			stat.setInt(1, id);
//			
//			stat.execute();
//			Util.addMessageError("Exclusão realizada com sucesso!");
//			resultado = true;
//		} catch (SQLException e) {
//			Util.addMessageError("Falha ao Excluir.");
//			e.printStackTrace();
//		} finally {
//			try {
//				stat.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return resultado;
//	}
//
//	@Override
//	public Usuario findById(int id) {
//		// verificando se tem uma conexao valida
//		if (getConnection() == null) {
//			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
//			return null;
//		}
//		Usuario user = null;
//		
//		PreparedStatement stat = null;
//		
//		try {
//			stat = getConnection().prepareStatement("SELECT * FROM usuarios WHERE id = ?");
//			stat.setInt(1, id);
//			
//			ResultSet rs = stat.executeQuery();
//			if(rs.next()) {
//				user = new Usuario();
//				
//				Date data1 = rs.getDate("data_nascimento");
//				LocalDate data2 = data1.toLocalDate();
//				
//				//System.out.println(data1);
//				//System.out.println(data2);
//				
//				user.setId(rs.getInt("id"));
//				user.setNome(rs.getString("nome"));
//				user.setLogin(rs.getString("login"));
//				user.setSenha(rs.getString("senha"));
//				user.setPerfil(Perfil.valueOf(rs.getInt("perfil")) );
//				user.setDataNascimento(data2);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Util.addMessageError("Falha ao consultar o Banco de Dados.");
//		} finally {
//			try {
//				stat.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return user;
//	}
//
//	@Override
//	public List<Usuario> findAll() {
//		// verificando se tem uma conexao valida
//		if (getConnection() == null) {
//			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
//			return null;
//		}
//		
//		List<Usuario> listaUsuario = new ArrayList<Usuario>();
//		
//		PreparedStatement stat = null;
//	
//		try {
//			stat = getConnection().prepareStatement("SELECT * FROM usuarios");
//			ResultSet rs = stat.executeQuery();
//			while(rs.next()) {
//				Usuario c = new Usuario();
//				
//				Date data1 = rs.getDate("data_nascimento");
//				LocalDate data2 = data1.toLocalDate();
//				
//				//System.out.println(data1);
//				//System.out.println(data2);
//				
//				c.setId(rs.getInt("id"));
//				c.setNome(rs.getString("nome"));
//				c.setLogin(rs.getString("login"));
//				c.setSenha(rs.getString("senha"));
//				c.setPerfil(Perfil.valueOf(rs.getInt("perfil")) );
//				//c.setDataNascimento(rs.getDate("data_nascimento") );
//				c.setDataNascimento(data2);
//
//				listaUsuario.add(c);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			Util.addMessageError("Falha ao consultar o Banco de Dados.");
//			listaUsuario = null;
//		} finally {
//			try {
//				stat.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return listaUsuario;
//	}
//	
//
//}
