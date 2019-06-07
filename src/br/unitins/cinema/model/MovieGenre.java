package br.unitins.cinema.model;

public enum MovieGenre {
	ACTION(1, "Action"),
	ANIMATION(2, "Animation"), 
	COMEDY(3, "Comedy"),
	DOCUMENTARY(4, "Documentary"),
	DRAMA(5, "Drama"),
	HORROR(6, "Horror"),
	ROMANCE(7, "Romance"), 
	RELIGION(8, "Religion"), 
	SCI_FI(9, "Science Fiction"),
	THRILLER(10, "Thriller");
	
	private int value;
	private String label;
	
	MovieGenre(int value, String label) {
		this.value = value;
		this.label = label;
	}
	
	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}
	
	// retorna uma marca a partir de um valor inteiro
	public static MovieGenre valueOf(int value) {
		for (MovieGenre genre : MovieGenre.values()) {
			if (genre.getValue() == value) {
				return genre;
			}
		}
		return null;
	}
}
