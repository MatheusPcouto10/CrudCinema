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
import br.unitins.cinema.model.MovieGenre;

@Named
@ViewScoped
public class MovieController implements Serializable{
	
	private static final long serialVersionUID = -6437899273883339954L;

	private Movie movie;
	
	private List<Movie> listFilm = null;
	
	public MovieController() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		movie = (Movie) flash.get("movieFlash");
	}
	
	public List<Movie> getListFilm(){
		if (listFilm == null) {
			MovieDAO dao = new MovieDAO();
			listFilm = dao.findAll();
			if (listFilm == null)
				listFilm = new ArrayList<Movie>();
			dao.closeConnection();
		}
		
		return listFilm;
	}
	
	public void edit(int id) {
		MovieDAO dao = new MovieDAO();
		setMovie(dao.findById(id));
	}
	
	
	public void create() {
		MovieDAO dao = new MovieDAO();
		if (dao.create(getMovie())) {
			clean();
			// para atualizar o data table
			listFilm = null;
		}
		dao.closeConnection();
	}
	
	public void update() {
		MovieDAO dao = new MovieDAO();
		if (dao.update(getMovie())) {
			clean();
			// para atualizar o data table
			listFilm = null;
		}
		dao.closeConnection();
	}
	
	public void delete(int id) {
		MovieDAO dao = new MovieDAO();
		if (dao.delete(id)){
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
		movie = null;
	}
	
	public Movie getMovie() {
		if (movie == null)
			movie = new Movie();
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public void goback() {
		Util.redirect("consultfilm.xhtml");
	}
}