package be.hetcultuurhuis.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetcultuurhuis.dao.VoorstellingDAO;
import be.hetcultuurhuis.entities.Reservatie;
import be.hetcultuurhuis.entities.Voorstelling;

/**
 * Servlet implementation class ReservatieMandjeServlet
 */
//@WebServlet("/reservatiemandje.htm")
public class ReservatieMandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "WEB-INF/JSP/reservatiemandje.jsp";
	private static final String REDIRECT_URL = "/reservatiemandje.htm";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();

	@SuppressWarnings({ "unchecked"})
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			Map<Integer, Integer> mandje = (Map<Integer, Integer>) session.getAttribute("mandje");
			if (mandje != null) {
				List <Reservatie> teReserveren = new ArrayList<>();
				Set<Integer> reservatieNummers = mandje.keySet();
				BigDecimal totaal = new BigDecimal(BigInteger.ZERO);
				Voorstelling voorstelling;
				for (Integer nr : reservatieNummers) {
					voorstelling = voorstellingDAO.getVoorstelling(nr);
					teReserveren.add(new Reservatie(voorstelling,
							mandje.get(nr)));
					totaal = totaal.add(voorstelling.
							getPrijs().multiply(new BigDecimal(mandje.get(nr))));
				}
				request.setAttribute("teReserveren", teReserveren);
				request.setAttribute("totaal", totaal);
				
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			Map<Integer, Integer> mandje = null;
			mandje = (Map<Integer, Integer>) session.getAttribute("mandje");
			if (request.getParameter("geselecteerdeNrs") != null) {
				try {
					for (String nummerAlsString : request.getParameterValues("geselecteerdeNrs")) {
						int teVerwijderenReservatie = Integer.parseInt(nummerAlsString);
						mandje.remove(teVerwijderenReservatie);
					}
					session.setAttribute("mandje", mandje);
					response.sendRedirect(response.encodeRedirectURL(request
							.getContextPath() + REDIRECT_URL));
				} catch (NumberFormatException nfex) {
					throw new NumberFormatException("parameter in mandje is geen getal");
				}
			} else {
				doGet(request, response);
			}
		}
	}
}

