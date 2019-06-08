package br.unitins.cinema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Client;

@Named
@ViewScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 5087071012447028790L;
	
	//Employee employeeLog = null;
	Client clientLog = null;
	
	public MenuController() {
		//employeeLog = (Employee) Session.getInstance().getAttribute("employeeLog");
		clientLog = (Client) Session.getInstance().getAttribute("ClientLog");
	}
	
	public Client getClientLog() {
		return clientLog;
	}
	public void setClientLog(Client clientLog) {
		this.clientLog = clientLog;
	}
//	public Employee getEmployeeLog() {
//		return employeeLog;
//	}
//
//	public void setEmployeeLog(Employee employeeLog) {
//		this.employeeLog = employeeLog;
//	}
	public void client() {
		Util.redirect("client.xhtml");
	}
	public void film() {
		Util.redirect("film.xhtml");
	}
	public void login() {
		Util.redirect("login.xhtml");
	}
}
