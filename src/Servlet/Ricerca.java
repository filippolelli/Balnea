package Servlet;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Beans.Stabilimento;

public class Ricerca extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
		rd.forward(req, res);
		return;
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// init();
		String nomeStab = req.getParameter("nomeStab");
		String indirizzo = req.getParameter("indirizzo");
		String città = req.getParameter("città");
		String stato = req.getParameter("stato");
		Boolean locInserita = false;

		String dataInizio = req.getParameter("dataInizio");
		String dataFine = req.getParameter("dataFine");

		Date dateInit = null;
		Date dateFin = null;

		try {

			dateInit = new SimpleDateFormat("yyyy-MM-dd").parse(dataInizio);
			dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(dataFine);
		} catch (ParseException p) {
			p.printStackTrace();
		}

		HttpSession session = req.getSession();

		LocalDate dataInizioToLocal = dateInit.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataFineToLocal = dateFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		session.setAttribute("dataInizio", dataInizioToLocal);
		session.setAttribute("dataFine", dataFineToLocal);

		if (nomeStab == null || dataInizio == null || dataFine == null) {
			session.setAttribute("errRicerca", "Perfavore immetti almeno i campi Nome e Date");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeBagnante.jsp"); // o admin.html
			rd.forward(req, res);
		}

		if (nomeStab.isEmpty() || dataInizio.isEmpty() || dataFine.isEmpty()) {
			session.setAttribute("errRicerca", "Perfavore immetti almeno i campi Nome e Date");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeBagnante.jsp"); // o admin.html
			rd.forward(req, res);
		}

		if (dateInit.after(dateFin)) {
			session.setAttribute("errRicerca",
					"I viaggi nel tempo non sono ancora consentiti, controlla le date inserite!");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeBagnante.jsp"); // o admin.html
			rd.forward(req, res);
		}

		if (indirizzo != null && !indirizzo.isEmpty()) {
			if (stato == null || città == null) {
				session.setAttribute("errRicerca",
						"Se inserisci un indirizzo, sono necessari anche la città e lo stato");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeBagnante.jsp"); // o
																											// admin.html
				rd.forward(req, res);
			} else {
				locInserita = true;
			}
		}
		// Bagnante attivo = (Bagnante) session.getAttribute("bagnanteAttivo");

		List<Stabilimento> esitoRicerca = new ArrayList<Stabilimento>();

		Set<Stabilimento> stabSalvati = (Set<Stabilimento>) this.getServletContext().getAttribute("stabilimenti");
		for (Stabilimento s : stabSalvati) {
			if (locInserita) {
				if (s.getNome().equals(nomeStab) && s.getLocazione().getIndirizzo().equals(indirizzo)
						&& s.getLocazione().getCittà().equals(città) && s.getLocazione().getStato().equals(stato)
						&& s.getInformazioni().getDataInizio().isBefore(dataInizioToLocal)
						&& s.getInformazioni().getDataFine().isAfter(dataFineToLocal)) {
					esitoRicerca.add(s);
				}
			} else {
				if (s.getNome().equals(nomeStab) && s.getInformazioni().getDataInizio().isBefore(dataInizioToLocal)
						&& s.getInformazioni().getDataFine().isAfter(dataFineToLocal)) {
					esitoRicerca.add(s);
				}
			}
		}

		session.setAttribute("errRicerca", "");
		session.setAttribute("esitoRicercaStab", esitoRicerca);
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeBagnante.jsp"); // o admin.html
		rd.forward(req, res);

	}
}
