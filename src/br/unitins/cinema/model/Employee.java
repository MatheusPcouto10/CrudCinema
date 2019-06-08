package br.unitins.cinema.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Employee {
	private int id;
	
	@NotBlank(message="O nome deve ser informado.")
	private String name;
	
	@Email(message="Email inválido.")
	private String email;
	
	@Size(min=6, max=20, message="Tamanho incompativel, valor mínimo: 6 e valor maximo:20.")
	private String password;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
