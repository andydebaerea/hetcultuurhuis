package be.hetcultuurhuis.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetcultuurhuis.dao.KlantDAO;
import be.hetcultuurhuis.entities.Klant;

/**
 * Servlet implementation class BevestigServlet
 */
//@WebServlet("/bevestiging.htm")
public class BevestigServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestiging.jsp";
	private final KlantDAO klantDAO = new KlantDAO();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("klantNr") != null) {
			Klant klant = null;
			int klantNr = (Integer) session.getAttribute("klantNr");
			if (klantNr > 0) {
				klant = klantDAO.zoekGebruiker(klantNr);
				request.setAttribute("klant", klant);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Klant klant = null;
		HttpSession session = request.getSession();
		String gebruikersnaam = request.getParameter("Gebruikersnaam");
		String paswoord = request.getParameter("Paswoord");
		klant = klantDAO.zoekGebruiker(gebruikersnaam, paswoord);
		if (klant != null) {
			request.setAttribute("klant", klant);
			session.setAttribute("klantNr", klant.getKlantNr());
		} else {
			String fout = "de combinatie gebruikersnaam - paswoord is niet juist";
			request.setAttribute("fout", fout);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
