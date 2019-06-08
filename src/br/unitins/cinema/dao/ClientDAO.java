package br.unitins.cinema.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Client;

public class ClientDAO extends DAO <Client> {
	
	public Client findClient(String email, String password) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Client client = null;
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM client WHERE email = ? AND password = ? ");
			stat.setString(1, email);
			stat.setString(2, password);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				client.setPassword(rs.getString("password"));
				client.setBirthDate(rs.getDate("birth_date") == null ? null : (rs.getDate("birth_date").toLocalDate()));
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
		return client;		
	}

	@Override
	public boolean create(Client obj) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO client ( "
										+ " name, "
										+ " email, "
										+ " password, "
										+ " cpf,"
										+ " birth_date,"
										+ " telephone ) " 
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ?, "
										+ " ?,"
										+ " ?,"
										+ " ? ) ");
			
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getEmail());
			stat.setString(3, obj.getPassword());
			stat.setString(4, obj.getCpf());
			stat.setDate(5, (obj.getBirthDate() == null ? null : java.sql.Date.valueOf(obj.getBirthDate())));
			stat.setString(6, obj.getTelephone());
			
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
	public boolean update(Client obj) {
		boolean result = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE client SET "
												   + "  name = ?, "
												   + "  email = ?, "
												   + "  password = ?, "
												   + "  cpf = ?,  "
												   + "  birth_date = ?,"
												   + "	telephone = ?  "												   
												   + "WHERE id = ? ");
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getEmail());
			stat.setString(3, obj.getPassword());
			stat.setString(4, obj.getCpf());
			stat.setDate(5, (obj.getBirthDate() == null ? null : java.sql.Date.valueOf(obj.getBirthDate())));
			stat.setString(6, obj.getTelephone());
			
			stat.setInt(7, obj.getId());
			
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
			stat =	getConnection().prepareStatement("DELETE FROM client WHERE id = ? ");
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
	public Client findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Client client = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM client WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				client.setPassword(rs.getString("password"));
				client.setCpf(rs.getString("cpf"));
				client.setBirthDate(rs.getDate("birth_date") == null ? null : (rs.getDate("birth_date").toLocalDate()));
				client.setTelephone(rs.getString("telephone"));
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
		return client;
	}

	@Override
	public List<Client> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Client> listClient = new ArrayList<Client>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM client");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				client.setPassword(rs.getString("password"));
				client.setCpf(rs.getString("cpf"));
				client.setBirthDate(rs.getDate("birth_date") == null ? null : (rs.getDate("birth_date").toLocalDate()));
				client.setTelephone(rs.getString("telephone"));
				listClient.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listClient = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listClient;

	}
	
	public List<Client> findByName(String name) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Client> listClient = new ArrayList<Client>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM client WHERE name ILIKE ?");
			stat.setString(1, (name == null? "%" : "%"+name+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Client client = new Client();
				
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setEmail(rs.getString("email"));
				client.setPassword(rs.getString("password"));
				client.setCpf(rs.getString("cpf"));
				client.setBirthDate(rs.getDate("birth_date") == null ? null : (rs.getDate("birth_date").toLocalDate()));
				client.setTelephone(rs.getString("telephone"));
				
				listClient.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listClient = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listClient;

	}
}
