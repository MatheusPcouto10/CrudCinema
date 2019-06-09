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
public class ConsultClientController  implements Serializable {

	private static final long serialVersionUID = -1587204616142896335L;

	private String name;
	
	private List<Client> listClient = null;
	
	public List<Client> getListClient(){
		if (listClient == null) {
			ClientDAO dao = new ClientDAO();
			listClient = dao.findByName(getName());
			if (listClient == null)
				listClient = new ArrayList<Client>();
			dao.closeConnection();
		}
		
		return listClient;
	}
	
	public void search() {
		listClient = null;
	}
	
	public void edit(int id) {
		ClientDAO dao = new ClientDAO();
		Client client = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("clientFlash", client);
		Util.redirect("client.xhtml");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

