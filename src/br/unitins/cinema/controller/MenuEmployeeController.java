package br.unitins.cinema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Usuario;

@Named
@ViewScoped
public class MenuEmployeeController implements Serializable {

	private static final long serialVersionUID = -6966392563687527941L;
	
	Usuario employeeLog = null;

	
	public MenuEmployeeController() {
		employeeLog = (Usuario) Session.getInstance().getAttribute("EmployeeLog");
	}
	
	public Usuario getClientLog() {
		return employeeLog;
	}
	public void setClientLog(Usuario employeeLog) {
		this.employeeLog = employeeLog;
	}
	public void clientSurvey() {
		Util.redirect("consultclient.xhtml");
	}
	public void filmSurvey() {
		Util.redirect("consultfilm.xhtml");
	}
}
