package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.model.ItemVenda;
import br.unitins.cinema.model.Venda;

@Named
@ViewScoped
public class DetalhesVendaController  implements Serializable {
	
	private static final long serialVersionUID = -2120576424069252674L;
	
	private Venda venda;
	
	public DetalhesVendaController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Venda vendaFlash = (Venda) flash.get("vendaFlash");
		if (vendaFlash != null)
			venda = vendaFlash;
		
	}

	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		return venda;
	}

}
