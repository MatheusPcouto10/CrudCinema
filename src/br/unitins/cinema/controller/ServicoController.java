package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.dao.ServicoDAO;
import br.unitins.cinema.model.MovieGenre;
import br.unitins.cinema.model.Servico;

@Named
@ViewScoped
public class ServicoController implements Serializable {

	private static final long serialVersionUID = -3565680573694589088L;

	private Servico servico;
	
	private List<Servico> listaServico = null;
	

	public List<Servico> getListaServico(){
		if (listaServico == null) {
			ServicoDAO dao = new ServicoDAO();
			listaServico = dao.findAll();
			if (listaServico == null)
				listaServico = new ArrayList<Servico>();
			dao.closeConnection();
		}
		
		return listaServico;
	}
	
	public void editar(int id) {
		ServicoDAO dao = new ServicoDAO();
		setServico(dao.findById(id));
	}
	
	
	public void incluir() {
		ServicoDAO dao = new ServicoDAO();

		if (dao.create(getServico())) {
			limpar();
			// para atualizar o data table
			listaServico = null;
		}
		dao.closeConnection();
	}
	
	public void alterar() {
		ServicoDAO dao = new ServicoDAO();
		if (dao.update(getServico())) {
			limpar();
			// para atualizar o data table
			listaServico = null;
		}
		dao.closeConnection();
	}
	
	public void excluir() {
		ServicoDAO dao = new ServicoDAO();
		if (dao.delete(getServico().getId())) {
			limpar();
			// para atualizar o data table
			listaServico = null;
		}
		dao.closeConnection();
	}
	
	public MovieGenre[] getListaMovieGenre() {
		return MovieGenre.values();
	}
	
	public void limpar() {
		servico = null;
	}
	

	public Servico getServico() {
		if (servico == null) {
			servico = new Servico();
		}
		
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
}

