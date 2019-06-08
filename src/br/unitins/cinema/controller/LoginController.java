package br.unitins.cinema.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.EmployeeDAO;
import br.unitins.cinema.model.Employee;

@Named
@RequestScoped
public class LoginController {

	private Employee employee;
	
	public void login() {
		EmployeeDAO dao = new EmployeeDAO();
		// gerando o hash da senha informada na tela de login
		
		//String encryptPassword = Util.encrypt(getEmployee().getPassword());
		
		Employee employeeLog = dao.findEmployee(getEmployee().getEmail(), getEmployee().getPassword());
		
		// comparando os dados da tela de login com o banco de dados
		if (employeeLog != null) {
			Session.getInstance().setAttribute("EmployeeLogado", employeeLog);
			// login valido
			Util.redirect("menu.xhtml");
		} else 
			Util.addMessageError("Usuário ou senha inválido.");
		
	}
	
	public void clean() {
		setEmployee(null);
	}

	public Employee getEmployee() {
		if (employee == null) {
			employee = new Employee();
		}
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
