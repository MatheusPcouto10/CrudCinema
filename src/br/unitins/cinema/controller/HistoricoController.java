package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.VendaDAO;
import br.unitins.cinema.model.Usuario;
import br.unitins.cinema.model.Venda;

@Named
@ViewScoped
public class HistoricoController  implements Serializable {

	private static final long serialVersionUID = -6633939181563359628L;
	
	private List<Venda> listaVenda;
	
	
	/**
	 * Este metodo retorna todas a vendas do usuario logado
	 * @return List<Venda>
	 */
	public List<Venda> getListaVenda(){
		if (listaVenda == null) {
			Usuario usuario = (Usuario)Session.getInstance().getAttribute("usuarioLogado");
			if (usuario == null) {
				listaVenda = new ArrayList<Venda>();
				return listaVenda;
			}
			VendaDAO dao = new VendaDAO();
			// buscando todas as vendas do usuario logado
			listaVenda = dao.findAll(usuario);
			// se o retorno da consulta for nula ... inicializar a lista para evitar erro de nullpointer
			if (listaVenda == null) 
				listaVenda = new ArrayList<Venda>();
		}
		return listaVenda;
	}
	
	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("vendaFlash", venda);
		Util.redirect("/CrudCinema/faces/pages/detalhesvenda.xhtml");
	}
	
	
}

