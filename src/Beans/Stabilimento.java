package Beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class Stabilimento {
	private String id;
	private String nome;
	private boolean registrato;
	private Informazioni informazioni;
	private Locazione locazione;
	private Inventario inventario;
	private List<Prenotazione> prenotazioni;
	private Proprietario proprietario;
	private Stato stato;
	
	public Stabilimento(String nome, Informazioni informazioni, Locazione locazione,
			Inventario inventario,Proprietario proprietario) {
		this.id = nome+"@"+locazione.getIndirizzo()+"@"+locazione.getCittà()+"@"+locazione.getStato();
		this.proprietario=proprietario;
		this.nome = nome;
		this.registrato =false;
		this.informazioni = informazioni;
		this.locazione = locazione; 
		this.inventario = inventario;
		this.prenotazioni = new ArrayList<Prenotazione>();
		this.stato = Stato.inAttesa;
	}
	public String getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isRegistrato() {
		return registrato;
	}
	public void setRegistrato(boolean registrato) {
		this.registrato = registrato;
	}
	public Informazioni getInformazioni() {
		return informazioni;
	}
	public void setInformazioni(Informazioni informazioni) {
		this.informazioni = informazioni;
	}
	public Locazione getLocazione() {
		return locazione;
	}
	public void setLocazione(Locazione locazione) {
		this.locazione = locazione;
	}
	public Inventario getInventario() {
		return inventario;
	}
	public void setInventario(Inventario inventario) {
		this.inventario = inventario;
	}
	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	public boolean addPrenotazione(Prenotazione p) {
		if(this.verificaPrenotazione(p))
		{
			this.prenotazioni.add(p);
			return true;
		}
		else return false;
		
	}
	public Proprietario getProprietario() {
		return proprietario;
	}
	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	@Override
	public int hashCode() {
		return Objects.hash(locazione, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stabilimento other = (Stabilimento) obj;
		return Objects.equals(locazione, other.locazione) && Objects.equals(nome, other.nome);
	}
	
	private boolean verificaPrenotazione(Prenotazione p)
	{
		boolean valida = true;
		for(Prenotazione pren: this.prenotazioni)
		{
			if(p.sovrapposte(pren))
			{
				valida = false;
				System.out.println("Non valida:"+p.getId());
				return valida;
			}
		}
		return valida;
	}
	
	public boolean[][] getOmbrelloniDisponibiliByDate(LocalDate date)
	{
		int righe = this.getInventario().getOmbrelloni().getRighe();
		int colonne = this.getInventario().getOmbrelloni().getColonne();
		
		boolean ombrelloni[][] = new boolean[righe][colonne];
		
		for (int x = 0; x < righe; x++) {
			for (int y = 0; y < colonne; y++) {
				ombrelloni[x][y] = true;
			}
		}
		
		for(Prenotazione p: this.prenotazioni)
		{
			if((p.getDataInizio().isBefore(date) || p.getDataInizio().equals(date)) && (p.getDataFine().isAfter(date)||p.getDataFine().equals(date)))//data nell'intervallo
			{
				for(int[]i : p.getPosizioniOmbrelloni())
				{
					ombrelloni[i[0]][i[1]] = false;
				}
			}
		}
		
		return ombrelloni;
	}
	
	public boolean [][] getOmbrelloniDisponibiliByPeriod(LocalDate init, LocalDate fin)
	{
		int righe = this.getInventario().getOmbrelloni().getRighe();
		int colonne = this.getInventario().getOmbrelloni().getColonne();
		
		boolean ombrelloniIniziali[][] = null;
		boolean ombrelloniCompare[][] = null;
		
		ombrelloniIniziali = this.getOmbrelloniDisponibiliByDate(init);
		for (LocalDate date = init; !date.isAfter(fin); date = date.plusDays(1))
		{
			ombrelloniCompare = this.getOmbrelloniDisponibiliByDate(date);
			for (int x = 0; x < righe; x++) {
				for (int y = 0; y < colonne; y++) {
					ombrelloniIniziali[x][y] = ombrelloniIniziali[x][y] && ombrelloniCompare[x][y];
				}
			}
		}
		return ombrelloniIniziali;
	}
}
