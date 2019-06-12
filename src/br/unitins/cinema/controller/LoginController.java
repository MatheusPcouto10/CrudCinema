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
	private Client client;
	
	public void signup() {
		Util.redirect("client.xhtml");
	}
	public void login() {
		ClientDAO dao = new ClientDAO();
		
		// gerando o hash da senha informada na tela de login
		//String encryptPassword = Util.encrypt(getClient().getPassword());
		
		Client clientLog = dao.findClient(getClient().getEmail(), Util.encrypt(getClient().getPassword()));
		
		// comparando os dados da tela de login com o banco de dados
		if (clientLog != null) {
			Session.getInstance().setAttribute("ClientLog", clientLog);
			// login valido
			Util.redirect("menu.xhtml");
		} else 
			Util.addMessageError("Cliente ou senha invalida.");
		
	}
	
	public void clean() {
		setClient(null);
	}
	public Client getClient() {
		if (client == null) {
			client = new Client();
		}
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}
