package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.application.Util;
import br.unitins.cinema.dao.MovieDAO;
import br.unitins.cinema.model.Movie;


@Named
@ViewScoped
public class ConsultMovieController  implements Serializable {

	private static final long serialVersionUID = -4902023210704305794L;

	private String name;
	
	private List<Movie> listMovie = null;
	
	public List<Movie> getListMovie(){
		if (listMovie == null) {
			MovieDAO dao = new MovieDAO();
			listMovie = dao.findByName(getName());
			if (listMovie == null)
				listMovie = new ArrayList<Movie>();
			dao.closeConnection();
		}
		
		return listMovie;
	}
	
	public void search() {
		listMovie = null;
	}
	
	public void edit(int id) {
		MovieDAO dao = new MovieDAO();
		Movie film = dao.findById(id);
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("filmFlash", film);
		Util.redirect("filmupdate.xhtml");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void redirectMovie() {
		Util.redirect("film.xhtml");
	}
}
