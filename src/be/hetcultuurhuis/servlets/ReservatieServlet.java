package be.hetcultuurhuis.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.hetcultuurhuis.dao.VoorstellingDAO;
import be.hetcultuurhuis.entities.Voorstelling;

/**
 * Servlet implementation class ReservatieServlet
 */
//@WebServlet("/reservatie.htm")
public class ReservatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatie.jsp";
	private static final String REDIRECT_URL = "/reservatiemandje.htm";
	private final VoorstellingDAO voorstellingDAO = new VoorstellingDAO();
	private Voorstelling voorstelling = null;
	private Map<Integer, Integer> mandje = null;
	private HttpSession session = null;


	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		int voorstellingsNr = Integer.parseInt(request
				.getParameter("voorstellingsNr"));
		voorstelling = voorstellingDAO.getVoorstelling(voorstellingsNr);
		session = request.getSession();		
		if (session != null) {
			mandje = (Map<Integer, Integer>) session.getAttribute("mandje");
			if (mandje != null) {				
				Set<Integer> reservatieNummers = mandje.keySet();
				for (Integer nr : reservatieNummers) {
					if (nr == voorstellingsNr){
						int plaatsen = mandje.get(nr);
						request.setAttribute("bestaandeReservatie", plaatsen);
					}
				}
			}
		}
		request.setAttribute("voorstelling", voorstelling);
			
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
		int plaatsen = Integer.parseInt(request.getParameter("plaatsen"));
		int vrijePlaatsen = voorstelling.getVrijePlaatsen();
		if (plaatsen <= 0 || plaatsen > vrijePlaatsen) {
			request.setAttribute("fout", "Tik een getal tussen 1 en " + vrijePlaatsen);
			request.setAttribute("voorstelling", voorstelling);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			HttpSession session = request.getSession();
			mandje = (Map<Integer, Integer>) session
					.getAttribute("mandje");
			if (mandje == null) {
				mandje = new HashMap<Integer, Integer>();
			}
			mandje.put(voorstelling.getVoorstellingsNr(), plaatsen);
			
			session.setAttribute("mandje", mandje);
			response.sendRedirect(response.encodeRedirectURL(request
					.getContextPath() + REDIRECT_URL));
		}
		} catch (NumberFormatException nfex) {
			request.setAttribute("fout", "Geef een getal in");
			request.setAttribute("voorstelling", voorstelling);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
		
	}
}

