package Servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Cabine;
import Beans.Informazioni;
import Beans.Inventario;
import Beans.Lettini;
import Beans.Ombrelloni;
import Beans.Proprietario;
import Beans.Stabilimento;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 4, // 4 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class Gestisci extends HttpServlet {

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
		HttpSession session = req.getSession();

		Stabilimento current = (Stabilimento) session.getAttribute("stabilimentoGestito");
		Proprietario p = (Proprietario) session.getAttribute("proprietarioAttivo");
		if (req.getParameter("submitButton").equals("ModificaInformazioni")) {
			String email = req.getParameter("email");
			String telefono = req.getParameter("telefono");

			String descrizione = req.getParameter("descrizione");
			if (descrizione == null || descrizione.isEmpty()) {
				descrizione = "Nessuna descrizione per lo stabilimento";
			}

			if (email == null || telefono == null) {
				session.setAttribute("errGestione", "Alcuni campi sono vuoti");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/gestioneStabilimento.jsp"); // o
																													// admin.html
				rd.forward(req, res);
				return;
			}

			if (email.isEmpty() || telefono.isEmpty()) {
				session.setAttribute("errGestione", "Alcuni campi sono vuoti");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/gestioneStabilimento.jsp"); // o
																													// admin.html
				rd.forward(req, res);
				return;
			}

			Date dateInit = null;
			Date dateFin = null;

			try {
				String dataInizio = req.getParameter("dataInizio");
				String dataFine = req.getParameter("dataFine");
				dateInit = new SimpleDateFormat("yyyy-MM-dd").parse(dataInizio);
				dateFin = new SimpleDateFormat("yyyy-MM-dd").parse(dataFine);
			} catch (ParseException pe) {
				pe.printStackTrace();
				return;
			}

			if (dateInit.after(dateFin)) {
				session.setAttribute("errGestione",
						"I viaggi nel tempo non sono ancora consentiti, controlla le date inserite!");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o
																													// admin.html
				rd.forward(req, res);
				return;
			}

			LocalDate localDataInit = Instant.ofEpochMilli(dateInit.getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();
			LocalDate localDataFin = Instant.ofEpochMilli(dateFin.getTime()).atZone(ZoneId.systemDefault())
					.toLocalDate();

			Informazioni info = current.getInformazioni();
			info.setEmail(email);
			info.setTelefono(telefono);
			info.setDataInizio(localDataInit);
			info.setDataFine(localDataFin);
			info.setDescrizione(descrizione);

			current.setInformazioni(info);

			Set<Stabilimento> stabProprietario = p.getStabilimenti();
			for (Stabilimento stb : stabProprietario) {
				if (stb.getId().equals(current.getId())) {
					p.removeStabilimento(stb);
					p.addStabilimento(current);
				}
			}

			session.setAttribute("proprietarioAttivo", p);
			Set<Proprietario> proprietari = (Set<Proprietario>) this.getServletContext().getAttribute("proprietari");
			for (Proprietario pr : proprietari) {
				if (pr.getUsername().equals(p.getUsername())) {
					proprietari.remove(pr);
					proprietari.add(p);
					break;
				}
			}

			this.getServletContext().setAttribute("proprietari", proprietari);

			Set<Stabilimento> stabilimenti = (Set<Stabilimento>) this.getServletContext().getAttribute("stabilimenti");
			for (Stabilimento st : stabilimenti) {
				if (st.getId().equals(current.getId())) {
					stabilimenti.remove(st);
					stabilimenti.add(current);
					break;
				}
			}
			this.getServletContext().setAttribute("stabilimenti", stabilimenti);

			session.setAttribute("errGestione", "");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/gestioneStabilimento.jsp");
			rd.forward(req, res);
			return;
		} else {

			int qtaOmbrelloni = 0;
			int qtaLettini = 0;
			int qtaCabine = 0;
			int dispRighe = 0;
			int dispColonne = 0;

			double tariffaOmbrelloni = 0;
			double tariffaLettini = 0;
			double tariffaCabine = 0;

			try {
				String qtaOmb = req.getParameter("qtaOmbrelloni");
				String qtaLet = req.getParameter("qtaLettini");
				String qtaCab = req.getParameter("qtaCabine");
				String dispR = req.getParameter("dispRighe");
				String dispC = req.getParameter("dispCol");

				String tarOmb = req.getParameter("tarOmbrelloni");
				String tarLet = req.getParameter("tarLettini");
				String tarCab = req.getParameter("tarCabine");

				qtaOmbrelloni = Integer.parseInt(qtaOmb);
				qtaLettini = Integer.parseInt(qtaLet);
				qtaCabine = Integer.parseInt(qtaCab);
				dispRighe = Integer.parseInt(dispR);
				dispColonne = Integer.parseInt(dispC);

				tariffaOmbrelloni = Double.parseDouble(tarOmb);
				tariffaLettini = Double.parseDouble(tarLet);
				tariffaCabine = Double.parseDouble(tarCab);

			} catch (NumberFormatException ne) {
				ne.printStackTrace();
				session.setAttribute("errGestione", "Alcuni campi numerici sono errati, inserire solo numeri positivi");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o
																													// admin.html
				rd.forward(req, res);
				return;
			}

			Ombrelloni omb = new Ombrelloni((float) tariffaOmbrelloni, dispRighe, dispColonne, qtaOmbrelloni);
			Lettini let = new Lettini((float) tariffaLettini, qtaLettini);
			Cabine cab = new Cabine((float) tariffaCabine, qtaCabine);

			Inventario inv = current.getInventario();
			inv.setOmbrelloni(omb);
			inv.setLettini(let);
			inv.setCabine(cab);

			current.setInventario(inv);

			Set<Stabilimento> stabProprietario = p.getStabilimenti();
			for (Stabilimento stb : stabProprietario) {
				if (stb.getId().equals(current.getId())) {
					p.removeStabilimento(stb);
					p.addStabilimento(current);
				}
			}

			session.setAttribute("proprietarioAttivo", p);
			Set<Proprietario> proprietari = (Set<Proprietario>) this.getServletContext().getAttribute("proprietari");
			for (Proprietario pr : proprietari) {
				if (pr.getUsername().equals(p.getUsername())) {
					proprietari.remove(pr);
					proprietari.add(p);
					break;
				}
			}

			this.getServletContext().setAttribute("proprietari", proprietari);

			Set<Stabilimento> stabilimenti = (Set<Stabilimento>) this.getServletContext().getAttribute("stabilimenti");
			for (Stabilimento st : stabilimenti) {
				if (st.getId().equals(current.getId())) {
					stabilimenti.remove(st);
					stabilimenti.add(current);
					break;
				}
			}
			this.getServletContext().setAttribute("stabilimenti", stabilimenti);

			session.setAttribute("errGestione", "");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/gestioneStabilimento.jsp");
			rd.forward(req, res);
			return;
		}
	}
}
