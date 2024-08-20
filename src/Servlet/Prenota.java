package Servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Bagnante;
import Beans.Prenotazione;
import Beans.Proprietario;
import Beans.Stabilimento;

public class Prenota extends HttpServlet {

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
		int qtaOmbrelloni = 0;
		int qtaLettini = 0;
		boolean cabina = false;
		String radio = req.getParameter("radio");
		HttpSession session = req.getSession();
		Stabilimento stabCorrente = (Stabilimento) session.getAttribute("stabilimentoCorrente");
		Bagnante attivo = (Bagnante) session.getAttribute("bagnanteAttivo");
		
		if(radio != null && radio.equals("Sì"))
		{
			cabina = true;
		}else cabina = false;
		
		try {
			String qtaOmb = req.getParameter("qtaOmbrelloni");
			String qtaLet = req.getParameter("qtaLettini");
			
			qtaOmbrelloni = Integer.parseInt(qtaOmb);
			qtaLettini = Integer.parseInt(qtaLet);
			
		} catch(NumberFormatException ne) {
			ne.printStackTrace();
			session.setAttribute("errPrenotazione", "Alcuni campi numerici sono errati, inserire solo numeri positivi");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/prenotaStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		
		if(qtaOmbrelloni < 0 || qtaLettini < 0)
		{
			session.setAttribute("errPrenotazione", "Alcuni campi numerici sono errati, inserire solo numeri positivi");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/prenotaStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		

		LocalDate dataInizio = (LocalDate)session.getAttribute("dataInizio");
		LocalDate dataFine = (LocalDate) session.getAttribute("dataFine");
		
		
		Set<int[]> disposizioni = new HashSet<int[]>();
		for(int i = 0; i < qtaOmbrelloni; i++)
		{
			int disposizione[] = new int[2];
			disposizione[0] = Integer.parseInt(req.getParameter("posizioneRiga" + i))-1;
			disposizione[1] = Integer.parseInt(req.getParameter("posizioneCol" + i))-1;
			if(disposizione[0] < 0 || disposizione[1] < 0)
			{
				session.setAttribute("errPrenotazione", "Alcuni campi numerici sono errati, inserire solo numeri positivi");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/prenotaStabilimento.jsp"); // o admin.html
				rd.forward(req, res);
				return;
			}
			disposizioni.add(disposizione);
			System.out.println("disposizione"+disposizione[0]+disposizione[1]);
		}
		
		for(int i[]: disposizioni)
		{
			System.out.println("Posizioni: "+i[0]+i[1]);
		}
		
		Prenotazione p = new Prenotazione(disposizioni, qtaLettini, cabina, dataInizio, dataFine, stabCorrente, attivo);
		
		if(req.getParameter("submitButton").equals("Calcola"))
		{
			session.setAttribute("preventivo", p.calcolaPreventivo());
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/prenotaStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		else
		{
			if(!stabCorrente.addPrenotazione(p))
			{
				session.setAttribute("errPrenotazione","Non è stato possibile inserire la prenotazione, controlla i posti scelti o le date");
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/prenotaStabilimento.jsp"); // o admin.html
				rd.forward(req, res);
				return;
			}
			else
			{
				Set<Proprietario> proprietari =(Set<Proprietario>) this.getServletContext().getAttribute("proprietari");
				Set<Bagnante> bagnanti =(Set<Bagnante>) this.getServletContext().getAttribute("bagnanti");
				Set<Stabilimento> stabilimenti = (Set<Stabilimento>) this.getServletContext().getAttribute("stabilimenti");
				
				attivo.addPrenotazione(p);
				session.setAttribute("bagnanteAttivo",attivo);
				session.setAttribute("stabilimentoCorrente", stabCorrente);
				
				Proprietario update = stabCorrente.getProprietario();
				update.removeStabilimento(stabCorrente);
				update.addStabilimento(stabCorrente);
				
				for(Proprietario pr: proprietari)
				{
					if(pr.getUsername().equals(update.getUsername()))
					{
						proprietari.remove(pr);
						proprietari.add(update);
						break;
					}
				}
				
				for(Bagnante bg: bagnanti)
				{
					if(bg.getUsername().equals(attivo.getUsername()))
					{
						bagnanti.remove(bg);
						bagnanti.add(attivo);
						break;
					}
				}
				
				for(Stabilimento st: stabilimenti)
				{
					if(st.getId().equals(stabCorrente.getId()))
					{
						stabilimenti.remove(st);
						stabilimenti.add(stabCorrente);
						break;
					}
				}
				
				
				this.getServletContext().setAttribute("proprietari", proprietari);
				this.getServletContext().setAttribute("bagnanti", bagnanti);
				this.getServletContext().setAttribute("stabilimenti", stabilimenti);
				
				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/prenotazioni.jsp"); // o admin.html
				rd.forward(req, res);
			}
		}

	}
}
