package be.hetcultuurhuis.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.hetcultuurhuis.entities.Klant;

public class KlantDAO extends AbstractDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final String FIND_BY_NAAM_PASWOORD_SQL = 
			"select klantnr, voornaam, familienaam, straat, huisnr, "
			+ "postcode, gemeente from klanten "
			+ "where gebruikersNaam = ? and paswoord = ?";
	private static final String FIND_BY_KLANTNR = 
			"select klantnr, voornaam, familienaam, straat, huisnr, "
			+ "postcode, gemeente from klanten where klantNr = ?";
	private static final String CREATE_KLANT = 
			"Insert into klanten(klantnr, voornaam, familienaam, "
			+ "straat, huisnr, postcode, gemeente, gebruikersNaam, "
			+ "paswoord) values (?,?,?,?,?,?,?,?,?)";
	private static final String FIND_GEBRUIKERSNAAM = 
			"select count(gebruikersnaam) as bestaat "
			+ "from klanten where gebruikersnaam = ?";
	
	/*
	 * methode om klantgegevens op te halen uit database
	 * via paswoord en gebruikersnaam
	 */
	public Klant zoekGebruiker(String gebruikersNaam, String paswoord) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						FIND_BY_NAAM_PASWOORD_SQL)){
			Klant klant = null;
			statement.setString(1, gebruikersNaam);
			statement.setString(2, paswoord);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					klant = resultSetRijNaarKlanten(resultSet);
			}
				return klant;
			}
		} catch (SQLException sqlex) {
			throw new DAOException("kan klant niet lezen uit de database" + sqlex);
		}
	}
	
	/*
	 * methode om klantgegevens op te vragen
	 * via klantnummer
	 */
	public Klant zoekGebruiker(int nummer) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						FIND_BY_KLANTNR)){
			Klant klant = null;
			statement.setInt(1, nummer);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					klant = resultSetRijNaarKlanten(resultSet);
			}
				return klant;
			}
		} catch (SQLException sqlex) {
			throw new DAOException("kan klant niet lezen uit de database" + sqlex);
		}
	}

	public int voegKlantToe(Klant klant) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						CREATE_KLANT, Statement.RETURN_GENERATED_KEYS)){
			statement.setLong(1, klant.getKlantNr());
			statement.setString(2, klant.getVoornaam());
			statement.setString(3, klant.getFamilienaam());
			statement.setString(4, klant.getStraat());
			statement.setString(5, klant.getHuisNr());
			statement.setString(6, klant.getPostcode());
			statement.setString(7, klant.getGemeente());
			statement.setString(8, klant.getGebruikersNaam());
			statement.setString(9, klant.getPaswoord());
			statement.executeUpdate();
			try (ResultSet resultSet = statement.getGeneratedKeys()) {
				if (!resultSet.next()) {
					throw new DAOException("Kan nummer toegevoegde klant niet lezen");
				}
				return resultSet.getInt(1);
			} 
		} catch (SQLException sqlex) {
			throw new DAOException("kan nieuwe klant niet invoeren" + sqlex);
		}
	}

	public int getGebruikersNaam(String gebruikersNaam) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						FIND_GEBRUIKERSNAAM)){
			int bestaatGebruiker = 1;
			statement.setString(1, gebruikersNaam);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					bestaatGebruiker = resultSet.getInt("bestaat");
			}
			return bestaatGebruiker;
			}
		} catch (SQLException sqlex) {
			throw new DAOException("Kan niet controleren of Gebruikersnaam reeds bestaat" + sqlex);
		}
	}

	private Klant resultSetRijNaarKlanten(ResultSet resultSet)
			throws SQLException {
		return new Klant(resultSet.getInt("KlantNr"),
				resultSet.getString("voornaam"),
				resultSet.getString("familienaam"),
				resultSet.getString("straat"), resultSet.getString("huisNr"),
				resultSet.getString("postcode"), resultSet.getString("gemeente"));
	}

}

