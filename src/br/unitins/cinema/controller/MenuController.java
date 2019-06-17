package br.unitins.cinema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Usuario;

@Named
@ViewScoped
public class MenuController implements Serializable {

	Usuario clientLog = null;
	private static final long serialVersionUID = 5087071012447028790L;
	
	public MenuController() {
		clientLog = (Usuario) Session.getInstance().getAttribute("ClientLog");
	}
	
	public Usuario getUsuarioLog() {
		return clientLog;
	}
	public void setUsuarioLog(Usuario clientLog) {
		this.clientLog = clientLog;
	}
	public void client() {
		Util.redirect("client.xhtml");
	}
	public void film() {
		Util.redirect("consultfilm.xhtml");
	}
	public void login() {
		Util.redirect("login.xhtml");
	}
}
