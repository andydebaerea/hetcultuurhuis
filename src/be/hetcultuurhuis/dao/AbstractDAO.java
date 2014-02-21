package be.hetcultuurhuis.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
	
abstract class AbstractDAO {
	private static final String JNDI_NAME = "java:/comp/env/jdbc/hetcultuurhuis";
	public Connection getConnection(){
		Context context = null;
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup(JNDI_NAME);
			return dataSource.getConnection();
		} catch (NamingException nex) {
			throw new DAOException(JNDI_NAME + " niet gevonden", nex);
		} catch (SQLException sqlex) {
			throw new DAOException("kan geen connectie krijgen van " + JNDI_NAME, sqlex);
		} finally {
			try {
				if(context != null) {
					context.close();
				}
			} catch (NamingException nex) {
				throw new DAOException("kan " + JNDI_NAME + " niet sluiten", nex);
			}
		}
	}
}
