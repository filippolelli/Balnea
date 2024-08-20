package Beans;

import java.io.File;

import java.util.HashSet;
import java.util.Set;


public class Proprietario extends Utente {
	
	private Stato stato;
	private File f;
	private Set<Stabilimento> stabilimenti;
	public Proprietario(String username, String passwd, String nome, String cognome, String email, boolean bagnante) {
		super(username, passwd, nome, cognome, email, bagnante);
		this.stato = Stato.inAttesa;
		this.stabilimenti = new HashSet<Stabilimento>();
	}
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	public File getFile() {
		return f;
	}
	public void setFile(File f) {
		this.f = f;
	}
	public Set<Stabilimento> getStabilimenti() {
		return stabilimenti;
	}
	public void setStabilimenti(Set<Stabilimento> stabilimenti) {
		this.stabilimenti = stabilimenti;
	}
	
	public void addStabilimento(Stabilimento s) {
		this.stabilimenti.add(s);
	}
	
	public void removeStabilimento(Stabilimento s)
	{
		for(Stabilimento stab: this.stabilimenti)
		{
			if(stab.getId().equals(s.getId()))
			{
				this.stabilimenti.remove(stab);
			}
		}
	}
	
}
