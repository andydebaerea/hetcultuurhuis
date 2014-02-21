package be.hetcultuurhuis.entities;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Voorstelling {
	private int voorstellingsNr;
	private String titel;
	private String uitvoerders;
	private Date datum;
	private int genrenr;
	private BigDecimal prijs;
	private int vrijePlaatsen;

	public Voorstelling(int voorstellingsNr, String titel, String uitvoerders,
			Timestamp timestamp, int genrenr, BigDecimal prijs, int vrijePlaatsen) {
		setVoorstellingsNr(voorstellingsNr);
		setTitel(titel);
		setUitvoerders(uitvoerders);
		setDatum(timestamp);
		setGenrenr(genrenr);
		setPrijs(prijs);
		setVrijePlaatsen(vrijePlaatsen);
	}

	public int getVoorstellingsNr() {
		return voorstellingsNr;
	}

	public void setVoorstellingsNr(int nr) {
		this.voorstellingsNr = nr;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getUitvoerders() {
		return uitvoerders;
	}

	public void setUitvoerders(String uitvoerders) {
		this.uitvoerders = uitvoerders;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Timestamp timestamp) {
		datum = new Date(timestamp.getTime());
	}

	public int getGenrenr() {
		return genrenr;
	}

	public void setGenrenr(int genrenr) {
		this.genrenr = genrenr;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public int getVrijePlaatsen() {
		return vrijePlaatsen;
	}

	public void setVrijePlaatsen(int vrijePlaatsen) {
		this.vrijePlaatsen = vrijePlaatsen;
	}
}

