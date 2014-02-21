package be.hetcultuurhuis.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetcultuurhuis.dao.ReservatieDAO;
import be.hetcultuurhuis.dao.VoorstellingDAO;
import be.hetcultuurhuis.entities.Reservatie;

/**
 * Servlet implementation class GedaneReserveringenServlet
 */
//@WebServlet("/gedanereserveringen.htm")
public class GedaneReserveringenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/gedaneReserveringen.jsp";

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			Map<Integer, Integer> mandje = (Map<Integer, Integer>) session
					.getAttribute("mandje");
			int klantNr = (Integer) session.getAttribute("klantNr");
			if (mandje != null) {
				Map<Integer, Reservatie> gedaneReservaties = new HashMap<>();
				List<Reservatie> reservaties = new ArrayList<Reservatie>();
				List<Reservatie> gelukteReservaties = new ArrayList<Reservatie>();
				List<Reservatie> mislukteReservaties = new ArrayList<Reservatie>();
				Set<Integer> reservatieNummers = mandje.keySet();
				VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
				ReservatieDAO reservatieDAO = new ReservatieDAO();
				for (Integer nr : reservatieNummers) {
					reservaties.add(new Reservatie(voorstellingDAO
							.getVoorstelling(nr), mandje.get(nr)));
					gedaneReservaties.put(klantNr,
							new Reservatie(
									voorstellingDAO.getVoorstelling(nr),
									mandje.get(nr)));
				}
				for (Reservatie reservatie : reservaties) {
					int geslaagdeReservatie = reservatieDAO.voegReservatieToe(
							reservatie, klantNr);
					if (geslaagdeReservatie == 1) {
						gelukteReservaties.add(reservatie);
					} else {
						mislukteReservaties.add(reservatie);
					}
				}

				request.setAttribute("gelukteReservaties", gelukteReservaties);
				request.setAttribute("mislukteReservaties", mislukteReservaties);

			}
			session = request.getSession(false);
			if (session != null)
				session.invalidate();
		}

		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
