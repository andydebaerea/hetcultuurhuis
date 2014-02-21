package be.hetcultuurhuis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import be.hetcultuurhuis.entities.Genre;

public class GenreDAO extends AbstractDAO {
	private static final String FIND_ALL_GENRES = 
			"Select genreNr, naam from genres order by naam";
	private static final String GET_GENRE_BY_GENRENR = 
			"Select naam from genres where genreNr = ?";
	
	/*
	 * methode om alle genres op te roepen vanuit database
	 */
	public Iterable<Genre> findAll() {
		try (Connection connection = getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(FIND_ALL_GENRES)){
			List<Genre> genres = new ArrayList<>();
			while (resultSet.next()) {
				genres.add(resultSetRijNaarGenre(resultSet));
			}
			return genres;
		} catch (SQLException sqlex) {
			throw new DAOException("Kan genre niet lezen uit de database" + sqlex);
		}
	}
	
	/*
	 * methode om een genre op te roepen via genrenummer
	 */
	public String getGenreNaam(int nummer) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(GET_GENRE_BY_GENRENR)){
			String naam = null;
			statement.setInt(1, nummer);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					naam = resultSet.getString("naam");
				}
				return naam;
			}
		} catch (SQLException ex) {
			throw new DAOException("kan genre niet lezen uit de database" + ex);
		}
	}

	private Genre resultSetRijNaarGenre(ResultSet resultSet) throws SQLException {
		return new Genre(resultSet.getInt("genreNr"),
				resultSet.getString("naam"));
	}

}

