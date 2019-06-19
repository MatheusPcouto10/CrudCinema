package br.unitins.cinema.model;

public class Servico {

	private Integer id;
	private String descricao;
	private Double valor;
	private String titulo;
	private MovieGenre movieGenre;
	private String duration;
	private String director;
	private String releaseYear;

	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
