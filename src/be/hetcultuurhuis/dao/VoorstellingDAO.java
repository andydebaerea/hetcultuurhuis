package be.hetcultuurhuis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.hetcultuurhuis.entities.Voorstelling;

public class VoorstellingDAO extends AbstractDAO{

	private static final String FIND_VOORSTELLING = 
			"Select VoorstellingsNr, Titel, Uitvoerders, Datum, GenreNr, "
	        		+ "Prijs, VrijePlaatsen from voorstellingen "
	        		+ "where voorstellingsNr = ?";
	private static final String FIND_BY_GENRE = 
			"Select voorstellingsNR, titel, uitvoerders, datum, genreNr, prijs, "
			+ "vrijePlaatsen from voorstellingen where genrenr = ? and datum >= {fn curdate()} order by datum";
	public List<Voorstelling> findAllVoorstellingenInGenre(int genreNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						FIND_BY_GENRE)){
			List<Voorstelling> voorstellingen = new ArrayList<Voorstelling>();
			statement.setInt(1, genreNr);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					voorstellingen.add(resultSetRijNaarVoorstelling(resultSet));
			}
				return voorstellingen;
			}
		} catch (SQLException ex) {
			throw new DAOException("kan voorstellingen niet lezen uit de database" + ex);
		}
	}

	public Voorstelling getVoorstelling(int voorstellingsNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(
						FIND_VOORSTELLING)){
			Voorstelling voorstelling = null;
			statement.setInt(1, voorstellingsNr);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					voorstelling = resultSetRijNaarVoorstelling(resultSet);
			}
				return voorstelling;
			}
		} catch (SQLException ex) {
			throw new DAOException("kan voorstelling niet lezen uit de database" + ex);
		}
	}


	private Voorstelling resultSetRijNaarVoorstelling(ResultSet resultSet)
			throws SQLException {
		return new Voorstelling(resultSet.getInt("VoorstellingsNr"),
				resultSet.getString("titel"),
				resultSet.getString("Uitvoerders"), resultSet.getTimestamp("datum"),
				resultSet.getInt("Genrenr"), resultSet.getBigDecimal("prijs"),
				resultSet.getInt("VrijePlaatsen"));
	}
}

