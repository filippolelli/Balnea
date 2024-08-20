package Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import Beans.Cabine;
import Beans.Foto;
import Beans.Informazioni;
import Beans.Inventario;
import Beans.Lettini;
import Beans.Locazione;
import Beans.Ombrelloni;
import Beans.Proprietario;
import Beans.Stabilimento;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 4, // 4 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class RegistraBagno extends HttpServlet {

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
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{

		//init();
		int j = 0;
		System.out.print(j++);
		HttpSession session = req.getSession();
		
		Proprietario p = (Proprietario) session.getAttribute("proprietarioAttivo");
		String nomeStab = req.getParameter("nomeStab");
		String email = req.getParameter("email");
		String telefono = req.getParameter("telefono");
		
		String indirizzo = req.getParameter("indirizzo");
		String città = req.getParameter("città");
		String stato = req.getParameter("stato");
		
		String descrizione = req.getParameter("descrizione");
		if(descrizione == null || descrizione.isEmpty())
		{
			descrizione = "Nessuna descrizione per lo stabilimento"; 
		}
		
		if(nomeStab == null || indirizzo == null || città == null || stato == null || email == null || telefono == null)
		{
			session.setAttribute("errRegistraStab", "Alcuni campi sono vuoti");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		
		if(nomeStab.isEmpty() || indirizzo.isEmpty() || città.isEmpty() || stato.isEmpty() || email.isEmpty() || telefono.isEmpty())
		{
			session.setAttribute("errRegistraStab", "Alcuni campi sono vuoti");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		
		int qtaOmbrelloni = 0;
		int qtaLettini = 0;
		int  qtaCabine = 0;
		int dispRighe = 0;
		int dispColonne = 0;
		
		double tariffaOmbrelloni = 0;
		double tariffaLettini = 0;
		double tariffaCabine = 0;
		
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
		
		if(dateInit.after(dateFin))
		{
			session.setAttribute("errRegistraStab", "I viaggi nel tempo non sono ancora consentiti, controlla le date inserite!");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		
		
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
			
			
		} catch(NumberFormatException ne) {
			ne.printStackTrace();
			session.setAttribute("errRegistraStab", "Alcuni campi numerici sono errati, inserire solo numeri positivi");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		File doc = null;
		File foto = null;
		String fotoName = null;
		
		try {
			Part filePart = req.getPart("file");
			InputStream fileContent = filePart.getInputStream();
		    String fileName = filePart.getSubmittedFileName();
		    doc = new File("C:\\Users\\cingo\\Documents\\eclipse-workspace\\Balnea\\web\\images\\"+p.getUsername() + "_" + fileName);
            OutputStream outputStream = new FileOutputStream(doc);
            outputStream.write(fileContent.readAllBytes());
		    
		    fileContent.close();
		    outputStream.close();
		    
		    Part fotoPart = req.getPart("fotoFile");
			InputStream fotoContent = fotoPart.getInputStream();
		    fotoName = fotoPart.getSubmittedFileName();
		    foto = new File("C:\\Users\\cingo\\Documents\\eclipse-workspace\\Balnea\\web\\images\\"+p.getUsername()+"_"+nomeStab+ "_" + fotoName);
            OutputStream outputStreamFoto = new FileOutputStream(foto);
            outputStreamFoto.write(fotoContent.readAllBytes());
            
            fotoContent.close();
            outputStreamFoto.close();
		    
		} catch (IOException i) {
			i.printStackTrace();
			session.setAttribute("errRegistraStab", "File inseriti non validi");
			RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); // o admin.html
			rd.forward(req, res);
			return;
		}
		
		//File fotoToAdd = new File("images/"+ p.getUsername()+"_"+nomeStab+ "_" + fotoName);
		List<Foto> photos = new ArrayList<Foto>();
		Foto welcome = new Foto("Hello",new File("images/"+fotoName));
		photos.add(welcome);
		
		LocalDate localDataInit = Instant.ofEpochMilli(dateInit.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDataFin = Instant.ofEpochMilli(dateFin.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		
		Informazioni info = new Informazioni(descrizione, telefono, email,localDataInit,localDataFin, photos);
		
		Ombrelloni omb = new Ombrelloni((float)tariffaOmbrelloni, dispRighe, dispColonne, qtaOmbrelloni);
		Lettini let = new Lettini((float) tariffaLettini, qtaLettini);
		Cabine cab = new Cabine((float) tariffaCabine, qtaCabine);
		
		Inventario inv = new Inventario(omb, let, cab);
		
		Locazione loc = new Locazione(indirizzo, città, stato);
		
		
		Stabilimento stab = new Stabilimento(nomeStab, info, loc, inv, p);
		
		for(Stabilimento s : p.getStabilimenti())
		{
			if(s.equals(stab))
			{
				session.setAttribute("errRegistraStab", "Impossibile aggiungere uno stabilimento già esistente");
		        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registraStabilimento.jsp"); 
		        rd.forward(req, res);
		        return;
			}
		}
		
		p.addStabilimento(stab);
		session.setAttribute("proprietarioAttivo",p);
		Set<Proprietario> proprietari =(Set<Proprietario>) this.getServletContext().getAttribute("proprietari");
		for(Proprietario pr: proprietari)
		{
			if(pr.getUsername().equals(p.getUsername()))
			{
				proprietari.remove(pr);
				proprietari.add(p);
				break;
			}
		}
		
		this.getServletContext().setAttribute("proprietari", proprietari);
		
		Set<Stabilimento> stabSalvati = (Set<Stabilimento>) this.getServletContext().getAttribute("stabilimenti");
		stabSalvati.add(stab);
		this.getServletContext().setAttribute("stabilimenti", stabSalvati);
		
		
		session.setAttribute("errRegistraStab", "");
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/homeProprietario.jsp"); 
        rd.forward(req, res);
		
        
        
	}
}
