package be.hetcultuurhuis.entities;

public class Genre {
	private int genreNr;
	private String naam;

	public Genre(int genreNr, String naam) {
		setGenreNr(genreNr);
		setNaam(naam);
	}

	private void setNaam(String naam) {
		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public void setGenreNr(int genreNr) {
		this.genreNr = genreNr;
	}

	public int getGenreNr() {
		return genreNr;
	}
}

