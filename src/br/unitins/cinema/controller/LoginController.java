package br.unitins.cinema.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.ClientDAO;
import br.unitins.cinema.model.Client;


@Named
@RequestScoped
public class LoginController {

//	private Employee employee;
	private Client client;
	
	public void login() {
		ClientDAO cdao = new ClientDAO();
		//EmployeeDAO dao = new EmployeeDAO();
		// gerando o hash da senha informada na tela de login
		
		String encryptPassword = Util.encrypt(getClient().getPassword());
		
		//Employee employeeLog = dao.findEmployee(getEmployee().getEmail(), getEmployee().getPassword());
		Client clientLog = cdao.findClient(getClient().getEmail(), encryptPassword);
		
		// comparando os dados da tela de login com o banco de dados
		if (clientLog != null) {
			Session.getInstance().setAttribute("ClientLog", clientLog);
			// login valido
			Util.redirect("menu.xhtml");
		} else 
			Util.addMessageError("Usuário ou senha inválido.");
		
	}
	
	public void clean() {
		//setEmployee(null);
		setClient(null);
	}

//	public Employee getEmployee() {
//		if (employee == null) {
//			employee = new Employee();
//		}
//		return employee;
//	}
	public Client getClient() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}

//	public void setEmployee(Employee employee) {
//		this.employee = employee;
//	}
	public void setClient(Client client) {
		this.client = client;
	}

}
