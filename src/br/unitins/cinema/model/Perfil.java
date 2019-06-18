package br.unitins.cinema.model;

import java.util.Arrays;
import java.util.List;

public enum Perfil {
	
	CLIENTE(1, "Cliente", Arrays.asList("login.xhtml", "template.xhtml", "vendaservico.xhtml", 
			"detalhesvenda.xhtml", "carrinho.xhtml", "historico.xhtml", "signup.xhtml")),
	FUNCIONARIO(2, "Funcionario", Arrays.asList("carrinho.xhtml", "carro.xhtml", 
			"consultausuario.xhtml", "detalhesvenda.xhtml", "historico.xhtml", "servico.xhtml", 
			"usuario2.xhtml", "vendaservico.xhtml", "login.xhtml", "signup.xhtml", "template.xhtml"));

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
