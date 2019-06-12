package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.ClientDAO;
import br.unitins.cinema.model.Client;

@Named
@ViewScoped
public class ClientController implements Serializable {

	private static final long serialVersionUID = -7679631577173747478L;
	private Client client;
	private List<Client> listClient = null;
	
	public ClientController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		client = (Client) flash.get("clientFlash");
	}
	
	public List<Client> getListClient(){
		if (listClient == null) {
			ClientDAO dao = new ClientDAO();
			listClient = dao.findAll();
			if (listClient == null)
				listClient = new ArrayList<Client>();
			dao.closeConnection();
		}
		return listClient;
	}
	
	public void edit(int id) {
		ClientDAO dao = new ClientDAO();
		setClient(dao.findById(id));
	}
	public void create() {
		// encriptando a senha do usuario
		getClient().setPassword(Util.encrypt(getClient().getPassword()));
		
		ClientDAO dao = new ClientDAO();
		if (dao.create(getClient())) {
			clean();
			// para atualizar o data table
			listClient = null;
		}
		dao.closeConnection();
	}
	public void update() {
		// encriptando a senha do usuario
		getClient().setPassword(Util.encrypt(getClient().getPassword()));
		
		ClientDAO dao = new ClientDAO();
		if (dao.update(getClient())) {
			clean();
			// para atualizar o data table
			listClient = null;
		}
		dao.closeConnection();
	}
	public void delete(int id) {
		ClientDAO dao = new ClientDAO();
		if (dao.delete(id)) {
			clean();
			// para atualizar o data table
			listClient = null;
		}
		dao.closeConnection();
	}
	public void clean() {
		client = null;
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
	
	public void goback() {
		Util.redirect("consultclient.xhtml");
	}
}
