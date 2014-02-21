package be.hetcultuurhuis.entities;


public class Reservatie {
	private Voorstelling voorstelling=null;
	
	private int plaatsen;
	private int nummer;
	
	public Reservatie(int nummer, int plaatsen){
		setPlaatsen(plaatsen);
		setNummer(nummer);
	}
	
	public Reservatie(Voorstelling voorstelling, int plaatsen){
		setVoorstelling(voorstelling);
		setPlaatsen(plaatsen);
		setNummer(voorstelling.getVoorstellingsNr());
	}
	
	public int getNummer() {
		return nummer;
	}

	public final void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	public Voorstelling getVoorstelling() {
		return voorstelling;
	}

	public final void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}

	public int getPlaatsen() {
		return plaatsen;
	}

	public final void setPlaatsen(int plaatsen) {
		this.plaatsen = plaatsen;
	}
}

