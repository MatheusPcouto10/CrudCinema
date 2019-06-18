package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Session;
import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.ServicoDAO;
import br.unitins.cinema.dao.VendaDAO;
import br.unitins.cinema.model.ItemVenda;
import br.unitins.cinema.model.Servico;
import br.unitins.cinema.model.Usuario;
import br.unitins.cinema.model.Venda;

@Named
@RequestScoped
public class CarrinhoController  implements Serializable {

	private static final long serialVersionUID = -4131331164022656670L;
	
	private Venda venda;
	
	public void remover(String nomeDoFilme) {
		//System.out.println(nomeDoFilme);

		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");

		for (ItemVenda itemVenda : carrinho) {
			//System.out.println(itemVenda.getServico().getDescricao());

			if (itemVenda.getServico().getDescricao().equals(nomeDoFilme)) {
				carrinho.remove(itemVenda);
				return;
			}

		}

	}
	
	public void finalizar() {
		
		Usuario user = (Usuario) Session.getInstance().getAttribute("usuarioLogado");
		getVenda().setCliente(user.getNome());
		

		getVenda().setUsuario((Usuario) Session.getInstance().getAttribute("usuarioLogado"));
		VendaDAO dao = new VendaDAO();
		dao.create(getVenda());

		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().getAttribute("carrinho");
		carrinho.clear();

	}
		
	
	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
		}
		// obtendo o carrinho da sessao
		List<ItemVenda> carrinho = 
				(List<ItemVenda>)Session.getInstance().getAttribute("carrinho");
		
		if (carrinho != null)
			venda.setListaItemVenda(carrinho);
		else
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
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
		
		Util.addMessageError("Adicionado com Sucesso! ");
	}

	
}
