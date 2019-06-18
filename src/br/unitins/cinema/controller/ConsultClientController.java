package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.UsuarioDAO;
import br.unitins.cinema.model.Usuario;


@Named
@ViewScoped
public class ConsultClientController  implements Serializable {

	private static final long serialVersionUID = -4762693472692597449L;

	private String name;
	
	private List<Usuario> listUsuario = null;
	
	public List<Usuario> getListUsuario(){
		if (listUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			listUsuario = dao.findByNome(getName());
			if (listUsuario == null)
				listUsuario = new ArrayList<Usuario>();
			dao.closeConnection();
		}
		
		return listUsuario;
	}
	
	public void search() {
		listUsuario = null;
	}
	
	public void edit(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("clientFlash", usuario);
		Util.redirect("clientupdate.xhtml");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void redirectClientRegister() {
		Util.redirect("client.xhtml");
	}
	
}

