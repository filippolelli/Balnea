package Beans;

import java.util.ArrayList;
import java.util.List;

public class Bagnante extends Utente {
	
	private List<Prenotazione> prenotazioni;

	public Bagnante(String username, String passwd, String nome, String cognome, String email, boolean bagnante) {
		super(username, passwd, nome, cognome, email, bagnante);
		// TODO Auto-generated constructor stub
		this.prenotazioni = new ArrayList<Prenotazione>();
	}

	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void addPrenotazione(Prenotazione p) {
		this.prenotazioni.add(p);
	}
	
	

}
