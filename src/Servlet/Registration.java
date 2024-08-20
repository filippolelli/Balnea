package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import Beans.Bagnante;
import Beans.Proprietario;
import Beans.Utente;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 4, // 4 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class Registration extends HttpServlet {

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

		//init();
		String nome = req.getParameter("nome");
		String cognome = req.getParameter("cognome");
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String reptPassword = req.getParameter("reptPassword");
		String radio = req.getParameter("inlineRadioOptions");
		boolean alreadyExisting = false;
		
		HttpSession session = req.getSession();

		if(nome == null || cognome == null || username == null || password == null || reptPassword == null)
		{
			session.setAttribute("errRegistration", "Perfavore immetti tutti i campi");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registration.jsp"); // o admin.html
			rd.forward(req, res);
		}
		
		if(nome.isEmpty() || cognome.isEmpty() || username.isEmpty() || password.isEmpty() || reptPassword.isEmpty())
		{
			session.setAttribute("errRegistration", "Perfavore immetti tutti i campi");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registration.jsp"); // o admin.html
			rd.forward(req, res);
		}
		
		

		
		Set<Utente> utenti = new HashSet<Utente>();
		Set<Bagnante> bagnanti = new HashSet<Bagnante>();
		Set<Proprietario> proprietari = new HashSet<Proprietario>();

		utenti = (Set<Utente>) this.getServletContext().getAttribute("utenti");
		bagnanti = (Set<Bagnante>) this.getServletContext().getAttribute("bagnanti");
		proprietari = (Set<Proprietario>) this.getServletContext().getAttribute("proprietari");

		for (Utente u : utenti) {
			if (u.getUsername().equals(username)) {
				alreadyExisting = true;
				break;
			}
		}

		if (alreadyExisting == true) {
			session.setAttribute("errRegistration", "Username già esistente");
			alreadyExisting = false;
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registration.jsp"); // o admin.html
			rd.forward(req, res);
		}

		if (!password.equals(reptPassword)) {
			session.setAttribute("errRegistration", "Password diverse, riprova");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registration.jsp"); // o admin.html
			rd.forward(req, res);
		}

		if (radio != null && alreadyExisting == false && password.equals(reptPassword)) {

			if (radio.equals("bagnante")) {
				Utente u = new Utente(username, password, nome, cognome, email, true);
				Bagnante b = new Bagnante(username, password, nome, cognome, email, true);

				utenti.add(u);
				bagnanti.add(b);

				this.getServletContext().setAttribute("utenti", utenti);
				this.getServletContext().setAttribute("bagnanti", bagnanti);

				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
				rd.forward(req, res);

			} else {
//				
				Part filePart = req.getPart("file");
				InputStream fileContent = filePart.getInputStream();
			    String fileName = filePart.getSubmittedFileName();
			    System.out.println(fileName);
			    File f = new File("C:\\Users\\cingo\\Documents\\eclipse-workspace\\Balnea\\tmp\\"+username + "_" + fileName);
	            OutputStream outputStream = new FileOutputStream(f);
	            outputStream.write(fileContent.readAllBytes());
			    
			    fileContent.close();
			    outputStream.close();
			    
				Utente u = new Utente(username, password, nome, cognome, email, false);
				Proprietario p = new Proprietario(username, password, nome, cognome, email, false);
				
				if(f.exists() && f.isFile() )
					p.setFile(f);

				utenti.add(u);
				proprietari.add(p);

				this.getServletContext().setAttribute("utenti", utenti);
				this.getServletContext().setAttribute("proprietari", proprietari);

				RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp"); // o admin.html
				rd.forward(req, res);

			}
		}

	}
}
