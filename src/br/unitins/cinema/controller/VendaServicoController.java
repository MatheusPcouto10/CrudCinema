package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.ServicoDAO;
import br.unitins.cinema.model.ItemVenda;
import br.unitins.cinema.model.Servico;

@Named
@ViewScoped
public class VendaServicoController  implements Serializable {

	private static final long serialVersionUID = 8171371944449468627L;

	private String descricao;
	
	private List<Servico> listaServico = null;
	
	public List<Servico> getListaServico(){
		if (listaServico == null) {
			ServicoDAO dao = new ServicoDAO();
			listaServico = dao.findByDescricao(getDescricao());
			if (listaServico == null)
				listaServico = new ArrayList<Servico>();
			dao.closeConnection();
		}
		
		return listaServico;
	}
	
	public void pesquisar() {
		listaServico = null;
	}
	
	public void adicionar(int id) {
		// pesquisa o servico selecionado
		ServicoDAO dao = new ServicoDAO();
		Servico servico = dao.findById(id);
		
		// verifica se existe o carrinho na sessao
		if (Session.getInstance().getAttribute("carrinho") == null) {
			// adiciona o carrinho na sessao
			Session.getInstance().setAttribute("carrinho", new ArrayList<ItemVenda>());
		}
		// busca o carrinho da sessao
		List<ItemVenda> carrinho =  
				(List<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		
		// cria um item de venda
		ItemVenda item = new ItemVenda();
		item.setServico(servico);
		item.setValor(servico.getValor());
		
		// adiciona o item no objeto de referencia do carrinho
		carrinho.add(item);
		
		// atualiza o carrinho
		Session.getInstance().setAttribute("carrinho", carrinho);
		
		Util.addMessageError("Adicionado com Sucesso! Quantidade de itens: "+carrinho.size());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
