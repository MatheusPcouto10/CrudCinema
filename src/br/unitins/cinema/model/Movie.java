package br.unitins.cinema.model;

public class Movie {
	private int id;
	private String name;
	private MovieGenre movieGenre;
	private String duration;
	private String director;
	private String releaseYear;
	
	public Movie() {
		
	}
	public Movie(int id, String name, MovieGenre movieGenre, String duration, String director, String releaseYear) {
		super();
		this.id = id;
		this.name = name;
		this.movieGenre = movieGenre;
		this.duration = duration;
		this.director = director;
		this.releaseYear = releaseYear;
	}

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
	public MovieGenre getMovieGenre() {
		return movieGenre;
	}
	public void setMovieGenre(MovieGenre movieGenre) {
		this.movieGenre = movieGenre;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}
}
