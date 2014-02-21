package be.hetcultuurhuis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetcultuurhuis.dao.GenreDAO;
import be.hetcultuurhuis.dao.VoorstellingDAO;

/**
 * Servlet implementation class WelkomServlet
 */
//@WebServlet("/welkom.htm")
public class WelkomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/welkom.jsp";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
	private final GenreDAO genreDAO = new GenreDAO();
	
	

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (request.getParameter("genreNr") != null) {
			try {
			int genreNr = Integer.parseInt(request.getParameter("genreNr"));
			request.setAttribute("voorstellingen", voorstellingDAO
					.findAllVoorstellingenInGenre(genreNr));
			request.setAttribute("genreNaam", genreDAO.getGenreNaam(genreNr));
			} catch (NumberFormatException nfex) {
				String fout = "Ongeldig genrenummer";
				request.setAttribute("fout", fout);
			}
		}
		request.setAttribute("session", session);
		request.setAttribute("genres", genreDAO.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);

	}
}

