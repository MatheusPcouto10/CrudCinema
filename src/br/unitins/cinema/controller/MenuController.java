package br.unitins.cinema.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.model.Employee;

@Named
@ViewScoped
public class MenuController implements Serializable {

	private static final long serialVersionUID = 5087071012447028790L;
	
	Employee employeeLog = null;
	
	public MenuController() {
		employeeLog = (Employee) Session.getInstance().getAttribute("employeeLog");
	}

	public Employee getUsuarioLogado() {
		return employeeLog;
	}

	public void setUsuarioLogado(Employee usuarioLogado) {
		this.employeeLog = usuarioLogado;
	}
	
	
}
