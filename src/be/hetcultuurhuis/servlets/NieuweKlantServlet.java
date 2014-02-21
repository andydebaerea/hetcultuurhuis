package be.hetcultuurhuis.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetcultuurhuis.dao.KlantDAO;
import be.hetcultuurhuis.entities.Klant;

/**
 * Servlet implementation class NieuweKlantServlet
 */
//@WebServlet("/nieuweklant.htm")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/nieuweklant.jsp";
	private static final String REDIRECT_URL = "/bevestiging.htm";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		request.setAttribute("session", session);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<String> fouten = new ArrayList<String>();
		KlantDAO klantenDAO = new KlantDAO();

		String voornaam = request.getParameter("voornaam");
		if (voornaam == null || voornaam.isEmpty()) {
			fouten.add("Voornaam niet ingevuld");
		}

		String familienaam = request.getParameter("familienaam");
		if (familienaam == null || familienaam.isEmpty()) {
			fouten.add("Familienaam niet ingevuld");
		}

		String straat = request.getParameter("straat");
		if (straat == null || straat.isEmpty()) {
			fouten.add("Straat niet ingevuld");
		}

		String huisNr = request.getParameter("huisNr");
		if (huisNr == null || huisNr.isEmpty()) {
			fouten.add("Huisnr. niet ingevuld");
		}

		String postcode = request.getParameter("postcode");
		if (postcode == null || postcode.isEmpty()) {
			fouten.add("Postcode niet ingevuld");
		}

		String gemeente = request.getParameter("gemeente");
		if (gemeente == null || gemeente.isEmpty()) {
			fouten.add("Gemeente niet ingevuld");
		}

		String gebruikersNaam = request.getParameter("gebruikersNaam");
		if (gebruikersNaam == null || gebruikersNaam.isEmpty()) {
			fouten.add("Gebruikersnaam niet ingevuld");
		} else if (klantenDAO.getGebruikersNaam(gebruikersNaam) == 1) {
			fouten.add("Gebruikersnaam is reeds in gebruik");
		}

		String paswoord = request.getParameter("paswoord");
		if (paswoord == null || paswoord.isEmpty()) {
			fouten.add("Paswoord niet ingevuld");
		} else {
			String herhaalPaswoord = request.getParameter("herhaalPaswoord");
			if (herhaalPaswoord == null || herhaalPaswoord.isEmpty()
					|| !paswoord.equals(herhaalPaswoord)) {
				fouten.add("Paswoord en Herhaal paswoord zijn verschillend");
			}
		}

		if (fouten.isEmpty()) {
			Klant klant = new Klant(voornaam, familienaam, straat, huisNr,
					postcode, gemeente, gebruikersNaam, paswoord);
			int klantNr = klantenDAO.voegKlantToe(klant);
			HttpSession session = request.getSession();
			session.setAttribute("klantNr", klantNr);
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath() + REDIRECT_URL));;
		} else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
