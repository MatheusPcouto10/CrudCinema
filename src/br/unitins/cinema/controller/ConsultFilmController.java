package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.FilmDAO;
import br.unitins.cinema.model.Film;


@Named
@ViewScoped
public class ConsultFilmController  implements Serializable {

	private static final long serialVersionUID = 6806782813108712741L;

	private String name;
	
	private List<Film> listFilm = null;
	
	public List<Film> getListFilm(){
		if (listFilm == null) {
			FilmDAO dao = new FilmDAO();
			listFilm = dao.findByName(getName());
			if (listFilm == null)
				listFilm = new ArrayList<Film>();
			dao.closeConnection();
		}
		
		return listFilm;
	}
	
	public void search() {
		listFilm = null;
	}
	
	public void edit(int id) {
		FilmDAO dao = new FilmDAO();
		Film film = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("filmFlash", film);
		Util.redirect("film.xhtml");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
