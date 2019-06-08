package br.unitins.cinema.model;

public class Film {
	private int id;
	private String name;
	private MovieGenre movieGenre;
	private int duration;
	private String director;
	private int releaseYear;
	
	public Film() {
		
	}
	public Film(int id, String name, MovieGenre movieGenre, int duration, String director, int releaseYear) {
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
}
