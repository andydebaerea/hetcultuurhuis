package be.hetcultuurhuis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import be.hetcultuurhuis.entities.Reservatie;

public class ReservatieDAO extends AbstractDAO {
	private static final String FIND_BESCHIKBARE_PLAATSEN = 
			"Select vrijePlaatsen from voorstellingen where voorstellingsNr = ?";
	private static final String UPDATE_PLAATSEN = 
			"update voorstellingen set vrijePlaatsen = vrijePlaatsen - ? "
			+ "where voorstellingsNr = ?";
	private static final String CREATE_RESERVATIE = 
			"Insert into reservaties (klantnr, voorstellingsnr, plaatsen)"
			+ " values (?,?,?)";

	public int voegReservatieToe(Reservatie reservatie, int klantNr) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection
						.prepareStatement(FIND_BESCHIKBARE_PLAATSEN);
				PreparedStatement statement1 = connection
						.prepareStatement(UPDATE_PLAATSEN);
				PreparedStatement statement2 = connection
						.prepareStatement(CREATE_RESERVATIE)) {
			int aantal = 0;
			statement.setInt(1, reservatie.getVoorstelling().getVoorstellingsNr());
			int vrijePlaatsen = 0;
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			connection.setAutoCommit(false);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					vrijePlaatsen = resultSet.getInt("vrijePlaatsen");
				}
				if (vrijePlaatsen >= reservatie.getPlaatsen()) {
					try {
						
						statement1.setInt(1, reservatie.getPlaatsen());
						statement1.setInt(2, reservatie.getVoorstelling()
								.getVoorstellingsNr());
						statement1.executeUpdate();
						statement2.setInt(1, klantNr);
						statement2.setInt(2, reservatie.getVoorstelling()
								.getVoorstellingsNr());
						statement2.setInt(3, reservatie.getPlaatsen());
						aantal = statement2.executeUpdate();
						connection.commit();
						return aantal;
					} catch (SQLException sqlex) {
						try {
							connection.rollback();
						} catch (SQLException sqlex1) {
							throw new DAOException(
									"kan reservatie niet wegschrijven" + sqlex1);
						}
					}
				}

			}
			return aantal;
		} catch (SQLException sqlex) {
			throw new DAOException("kan reservatie niet wegschrijven" + sqlex);
		}
	}
}
