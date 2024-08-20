package Servlet;

import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import Beans.Bagnante;
import Beans.Cabine;
import Beans.Foto;
import Beans.Informazioni;
import Beans.Inventario;
import Beans.Lettini;
import Beans.Locazione;
import Beans.Ombrelloni;
import Beans.Prenotazione;
import Beans.Proprietario;
import Beans.Stabilimento;
import Beans.Utente;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.*;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		Set<Utente> utenti = new HashSet<Utente>();

		Utente u1 = new Utente("pippo12", "sicura1234", "pippo", "plutarconi", "pippo@gmail.com", true);
		utenti.add(u1);
		u1 = new Utente("pluto45", "sicura123", "pluto", "mickey", "plutomickey@gmail.com", false);
		utenti.add(u1);

		this.getServletContext().setAttribute("utenti", utenti);

		Set<Bagnante> bagnanti = new HashSet<Bagnante>();
		Set<Proprietario> proprietari = new HashSet<Proprietario>();
		Set<Stabilimento> stabilimenti = new HashSet<Stabilimento>();
		
		Bagnante b = new Bagnante("pippo12", "sicura1234", "pippo", "plutarconi", "pippo@gmail.com", true);
		Proprietario p = new Proprietario("pluto45", "sicura123", "pluto", "mickey", "plutomickey@gmail.com", false);
		
		Ombrelloni omb = new Ombrelloni((float) 20.50, 10, 10,120);
		Cabine cab = new Cabine((float)10.50, 20);
		Lettini let = new Lettini((float)7.50, 250);
		
		Inventario inv = new Inventario(omb, let, cab);
		
		Foto f1 = new Foto("Ingresso", new File("images/spiaggia1.jpg"));
		Foto f2 = new Foto("Lol", new File("images/spiaggia2.jpg"));
		List<Foto> photos = new ArrayList<Foto>();
		photos.add(f1);photos.add(f2);
		
		Locazione loco = new Locazione("Via Albertini 52","Bari", "Italia");
		Informazioni contatti = new Informazioni("Bagno meraviglioso in riva al mare","+39 3467896543", 
				"bagniPalude@gmail.com", LocalDate.of(2022, 5, 20), LocalDate.of(2022, 9, 10), photos);
		
		Stabilimento stab = new Stabilimento("Bagni Palude" ,contatti, loco, inv, p);
		
		Set<int[]> ombrelloniPrenotati = new HashSet<int[]>();
		int[] omb1 = {0,1};
		int[] omb2 = {0,2};
		
		ombrelloniPrenotati.add(omb1);ombrelloniPrenotati.add(omb2);
		
		List<Prenotazione> pren = new ArrayList<Prenotazione>();

		Prenotazione pp = new Prenotazione(ombrelloniPrenotati, 2, false,
				LocalDate.of(2022, 7, 8), LocalDate.of(2022, 7, 10), stab,b);
		pren.add(pp);
		stab.addPrenotazione(pp);
		b.addPrenotazione(pp);
		pp = new Prenotazione(ombrelloniPrenotati, 1, true,
				LocalDate.of(2022, 8, 10), LocalDate.of(2022, 8, 19), stab,b);
		pren.add(pp);
		stab.addPrenotazione(pp);
		b.addPrenotazione(pp);
		
		pp = new Prenotazione(ombrelloniPrenotati, 4, false,
				LocalDate.of(2022, 8, 10), LocalDate.of(2022, 8, 20), stab,b);
		pren.add(pp);
		stab.addPrenotazione(pp);
		
		
		stabilimenti.add(stab);
		p.setStabilimenti(stabilimenti);
		proprietari.add(p);
		
		bagnanti.add(b);
		
		
		this.getServletContext().setAttribute("proprietari", proprietari);
		this.getServletContext().setAttribute("bagnanti", bagnanti);
		this.getServletContext().setAttribute("stabilimenti", stabilimenti);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String op = req.getParameter("operation");
		HttpSession session = req.getSession(false);
		if ("logout".equalsIgnoreCase(op)) {
			session.invalidate();
			System.out.println("Invalidated");
		}
		RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
		rd.forward(req, res);
	}


	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String username = req.getParameter("username");
		String pwd = req.getParameter("password");

		Set<Utente> utenti = (Set<Utente>) this.getServletContext().getAttribute("utenti");

		Set<Bagnante> bagnanti = new HashSet<Bagnante>();
		Set<Proprietario> proprietari = new HashSet<Proprietario>();
		bagnanti = (Set<Bagnante>) this.getServletContext().getAttribute("bagnanti");
		proprietari = (Set<Proprietario>) this.getServletContext().getAttribute("proprietari");

		boolean usernameCorretto = false;
		boolean pwCorretta = false;
		HttpSession session = req.getSession();

		if (username.compareTo("admin") == 0 && pwd.compareTo("admin") == 0) {
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/admin.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}

		for (Utente u : utenti) {
			if (u.getUsername().equals(username)) {
				usernameCorretto = true;
				if (u.getPasswd().equals(pwd)) {
					pwCorretta = true;
					session.setAttribute("utenteAttivo", u);
					break;
				} else {
					u.pwErrata();
					break;
				}
			}
		}

		if (usernameCorretto && pwCorretta) {
			Utente u2 = (Utente) session.getAttribute("utenteAttivo");

			if (u2.isBloccato()) {
				session.setAttribute("errMessaggio", "Il tuo account è bloccato");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
				rd.forward(req, res);
			} else {
				session.setAttribute("errMessaggio", "");
				if (u2.isBagnante()) {

					for (Bagnante b : bagnanti) {
						if (b.getUsername().equals(u2.getUsername())) {
							session.setAttribute("bagnanteAttivo", b);
							//System.out.print(b.getUsername());
							break;
						}
					}

					RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeBagnante.jsp");
					rd.forward(req, res);
				} else {

					for (Proprietario p : proprietari) {
						if (p.getUsername().equals(u2.getUsername())) {
							session.setAttribute("proprietarioAttivo", p);
							break;
						}
					}
					RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeProprietario.jsp");
					rd.forward(req, res);
				}
			}

		}

		if (usernameCorretto && !pwCorretta) {
			session.setAttribute("errMessaggio", "Password non corretta, riprova (max 3 tentativi)");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}

		if (!usernameCorretto) {
			session.setAttribute("errMessaggio", "Utente non esistente nel dataBase");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}
	}
}
