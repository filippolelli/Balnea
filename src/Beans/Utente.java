package Beans;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Utente {
	private String username;
	private String passwd;
	private String email;
	private String nome;
	private String cognome;
	private boolean bloccato;
	private int contatore;
	private boolean bagnante;

	public Utente(String username, String passwd, String nome, String cognome, String email, boolean bagnante) {
		super();
		this.username = username;
		this.passwd = passwd;
		this.email = email;
		this.bloccato = false;
		this.contatore = 0;
		this.bagnante = bagnante;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isBloccato() {
		return bloccato;
	}

	public void setBloccato(boolean bloccato) {
		this.bloccato = bloccato;
	}

	public void pwErrata() {
		this.contatore++;
		if (this.contatore == 3) {
			this.setBloccato(true);
			sblocca();
		}
	}

	private void sblocca() {
		Timer t = new Timer();
		TimerTask ts = new TimerTask() {

			int i = 60000;

			@Override
			public void run() {
				i--;

				if (i < 0) {
					t.cancel();
					setBloccato(false);
				}
			}
		};

		t.scheduleAtFixedRate(ts, 0, 1000);
	}

	@Override
	public String toString() {
		return "Utente [username=" + username + ", passwd=" + passwd + ", email=" + email + ", bloccato=" + bloccato
				+ "]";
	}

	public boolean isBagnante() {
		return bagnante;
	}

	public void setBagnante(boolean bagnante) {
		this.bagnante = bagnante;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(passwd, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(passwd, other.passwd) && Objects.equals(username, other.username);
	}
	
	
}
