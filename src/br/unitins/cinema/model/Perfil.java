package br.unitins.cinema.model;

import java.util.Arrays;
import java.util.List;

public enum Perfil {
	
	CLIENTE(1, "Cliente", Arrays.asList( "login.xhtml", "menu.xhtml", "client.xhtml", "consultfilm.xhtml")),
	FUNCIONARIO(2, "Funcionário", Arrays.asList( "login.xhtml","clientupdate.xhtml", "consultclient.xhtml",
			                                     "consultfilm.xhtml", "film.xhtml", "filmupdate.xhtml", 
			                                     "menuemployee.xhtml", "client.xhtml"));

	private int value;
	private String label;
	private List<String> pages;
	
	Perfil(int value, String label, List<String> pages) {
		this.value = value;
		this.label = label;
		this.pages = pages;
		
	}

	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	public List<String> getPages() {
		return pages;
	}
	
	// retorna um perfil a partir de um valor inteiro
	public static Perfil valueOf(int value) {
		for (Perfil perfil : Perfil.values()) {
			if (perfil.getValue() == value) {
				return perfil;
			}
		}
		return null;
	}
	
}
