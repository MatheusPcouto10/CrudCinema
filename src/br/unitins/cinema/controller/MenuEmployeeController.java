package br.unitins.cinema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.model.Employee;

@Named
@ViewScoped
public class MenuEmployeeController implements Serializable {

	private static final long serialVersionUID = -6966392563687527941L;
	
	Employee employeeLog = null;

	
	public MenuEmployeeController() {
		employeeLog = (Employee) Session.getInstance().getAttribute("EmployeeLog");
	}
	
	public Employee getClientLog() {
		return employeeLog;
	}
	public void setClientLog(Employee employeeLog) {
		this.employeeLog = employeeLog;
	}
	public void clientSurvey() {
		Util.redirect("consultclient.xhtml");
	}
	public void filmSurvey() {
		Util.redirect("consultfilm.xhtml");
	}
}
