package be.hetcultuurhuis.entities;


public class Klant {
	private int klantNr;
	private String voornaam;
	private String familienaam;
	private String straat;
	private String huisNr;
	private String postcode;
	private String gemeente;
	private String gebruikersNaam;
	private String paswoord;
	
	public Klant (int klantnummer, String voornaam, String familienaam, String straat, String huisnummer,
			String postcode, String gemeente){
		setKlantNr(klantnummer);
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setStraat(straat);
		setHuisNr(huisnummer);
		setPostcode(postcode);
		setGemeente(gemeente);
	}
	
	public Klant (String voornaam, String familienaam, String straat, String huisnummer, String postcode,
			String gemeente, String gebruikersNaam, String paswoord){
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setStraat(straat);
		setHuisNr(huisnummer);
		setPostcode(postcode);
		setGemeente(gemeente);
		setGebruikersNaam(gebruikersNaam);
		setPaswoord(paswoord);
	}

	public int getKlantNr() {
		return klantNr;
	}
	
	public void setKlantNr(int klantNr) {
		this.klantNr = klantNr;
	}
	public String getHuisNr() {
		return huisNr;
	}
	public void setHuisNr(String huisNr) {
		this.huisNr = huisNr;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public String getStraat() {
		return straat;
	}
	public void setStraat(String straat) {
		this.straat = straat;
	}
	public String getGemeente() {
		return gemeente;
	}
	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	public String getGebruikersNaam() {
		return gebruikersNaam;
	}
	public void setGebruikersNaam(String gebruikersNaam) {
		this.gebruikersNaam = gebruikersNaam;
	}
	public String getPaswoord() {
		return paswoord;
	}
	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}
	
}

