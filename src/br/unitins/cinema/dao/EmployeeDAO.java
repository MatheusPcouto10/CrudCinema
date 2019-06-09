package br.unitins.cinema.dao;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Employee;


public class EmployeeDAO extends DAO<Employee>{
	
	public Employee findEmployee(String email, String password) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Employee employee = null;
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM employee WHERE email = ? AND password = ? ");
			stat.setString(1, email);
			stat.setString(2, password);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));
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
		return employee;		
	}

	@Override
	public boolean create(Employee obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("INSERT INTO employee ( "
										+ " name, "
										+ " email, "
										+ " password ) "
										+ "VALUES ( "
										+ " ?, "
										+ " ?, "
										+ " ? ) ");
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getEmail());
			stat.setString(3, obj.getPassword());			
			
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
	public boolean update(Employee obj) {
		boolean resultado = false;
		
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return false;
		}
		
		PreparedStatement stat = null;
		try {
			stat =	getConnection().prepareStatement("UPDATE employee SET "
												   + "  name = ?, "
												   + "  email = ?, "
												   + "  password = ? "											   
												   + "WHERE id = ? ");
			stat.setString(1, obj.getName());
			stat.setString(2, obj.getEmail());
			stat.setString(3, obj.getPassword());
			
			stat.setInt(4, obj.getId());
			
			stat.execute();
			Util.addMessageError("Alteracao realizada com sucesso!");
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
			stat =	getConnection().prepareStatement("DELETE FROM employee WHERE id = ? ");
			stat.setInt(1, id);
			
			stat.execute();
			Util.addMessageError("Exclusao realizada com sucesso!");
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
	public Employee findById(int id) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		Employee employee = null;
		
		PreparedStatement stat = null;
		
		try {
			stat = getConnection().prepareStatement("SELECT * FROM employee WHERE id = ?");
			stat.setInt(1, id);
			
			ResultSet rs = stat.executeQuery();
			if(rs.next()) {
				employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("nome"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("senha"));
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
		return employee;
	}

	@Override
	public List<Employee> findAll() {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Employee> listEmployee = new ArrayList<Employee>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM Employee");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("nome"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("senha"));

				listEmployee.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listEmployee = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listEmployee;

	}
	
	public List<Employee> findByName(String name) {
		// verificando se tem uma conexao valida
		if (getConnection() == null) {
			Util.addMessageError("Falha ao conectar ao Banco de Dados.");
			return null;
		}
		
		List<Employee> listEmployee = new ArrayList<Employee>();
		
		PreparedStatement stat = null;
	
		try {
			stat = getConnection().prepareStatement("SELECT * FROM employee WHERE name ILIKE ?");
			stat.setString(1, (name == null? "%" : "%"+name+"%"));
			ResultSet rs = stat.executeQuery();
			
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPassword(rs.getString("password"));

				listEmployee.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Util.addMessageError("Falha ao consultar o Banco de Dados.");
			listEmployee = null;
		} finally {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listEmployee;

	}
	
}
