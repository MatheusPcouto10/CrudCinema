package br.unitins.cinema.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cinema.dao.FilmDAO;
import br.unitins.cinema.model.Film;
import br.unitins.cinema.model.MovieGenre;

@Named
@ViewScoped
public class FilmController implements Serializable{
	
	private static final long serialVersionUID = 1404713075505026358L;

	private Film film;
	
	private List<Film> listFilm = null;
	
	public FilmController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		film = (Film) flash.get("filmFlash");
	}
	
	public List<Film> getListFilm(){
		if (listFilm == null) {
			FilmDAO dao = new FilmDAO();
			listFilm = dao.findAll();
			if (listFilm == null)
				listFilm = new ArrayList<Film>();
			dao.closeConnection();
		}
		
		return listFilm;
	}
	
	public void edit(int id) {
		FilmDAO dao = new FilmDAO();
		setFilm(dao.findById(id));
	}
	
	
	public void create() {
		FilmDAO dao = new FilmDAO();
		if (dao.create(getFilm())) {
			clean();
			// para atualizar o data table
			listFilm = null;
		}
		dao.closeConnection();
	}
	
	public void update() {
		FilmDAO dao = new FilmDAO();
		if (dao.update(getFilm())) {
			clean();
			// para atualizar o data table
			listFilm = null;
		}
		dao.closeConnection();
	}
	
	public void delete() {
		FilmDAO dao = new FilmDAO();
		if (dao.delete(getFilm().getId())) {
			clean();
			// para atualizar o data table
			listFilm = null;
		}
		dao.closeConnection();
	}
	
	public MovieGenre[] getListMovieGenre() {
		return MovieGenre.values();
	}
	
	public void clean() {
		film = null;
	}
	
	public Film getFilm() {
		if (film == null)
			film = new Film();
		return film;
	}

	public void setFilm(Film Film) {
		this.film = Film;
	}
}