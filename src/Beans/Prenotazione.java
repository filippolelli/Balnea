package Beans;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Set;



public class Prenotazione {
	private String id;
	private int numLettini;
	private boolean cabina;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private Stabilimento stabilimento;
	private double prezzoFinale;
	private Bagnante bagnante;
	private Set<int[]> posizioniOmbrelloni;
	
	
	public Prenotazione(Set<int[]> posizioniOmbrelloni, int numLettini, boolean cabina, LocalDate dataInizio,
			LocalDate dataFine, Stabilimento stabilimento,Bagnante bagnante) {
		super();
		this.id = bagnante.getUsername()+"?"+dataInizio+"?"+dataFine+"?"+stabilimento.getId(); 
		this.posizioniOmbrelloni = posizioniOmbrelloni;
		this.numLettini = numLettini;
		this.cabina = cabina;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.stabilimento = stabilimento;
		this.prezzoFinale = 0;
		this.bagnante = bagnante;
		
	}


	public String getId() {
		return id;
	}

	public int getNumLettini() {
		return numLettini;
	}


	public boolean isCabina() {
		return cabina;
	}


	public LocalDate getDataInizio() {
		return dataInizio;
	}


	public LocalDate getDataFine() {
		return dataFine;
	}
	
	public void setNumLettini(int numLettini) {
		this.numLettini = numLettini;
	}


	public void setCabina(boolean cabina) {
		this.cabina = cabina;
	}


	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}


	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	

	public Stabilimento getStabilimento() {
		return stabilimento;
	}


	public void setStabilimento(Stabilimento stabilimento) {
		this.stabilimento = stabilimento;
	}
	
	private long durataVacanza() {
		 long daysDiff = ChronoUnit.DAYS.between(this.dataInizio, this.dataFine);
		 return daysDiff;
	}
	
	public double calcolaPreventivo()
	{
		double preventivo = 0;
		float tariffaCabina = 0;
		float tariffaOmbrelloneStabilimento = this.stabilimento.getInventario().getOmbrelloni().getTariffa(); //15.50 *1
		float tariffaOmbrelloni = tariffaOmbrelloneStabilimento * this.posizioniOmbrelloni.size() * this.durataVacanza(); 
		float tariffaLettini = this.stabilimento.getInventario().getLettini().getTariffa() * this.numLettini * this.durataVacanza(); //5.50 *2
		if(this.isCabina())
		{
			tariffaCabina = this.stabilimento.getInventario().getCabine().getTariffa() * this.durataVacanza();
		}
		
		preventivo = tariffaOmbrelloni + tariffaLettini + tariffaCabina;
		this.prezzoFinale = preventivo;
		return preventivo;
	}


	public double getPrezzoFinale() {
		return prezzoFinale;
	}


	public Bagnante getBagnante() {
		return bagnante;
	}
	
	public Set<int[]> getPosizioniOmbrelloni() {
		return posizioniOmbrelloni;
	}


	public void setPosizioniOmbrelloni(Set<int[]> posizioniOmbrelloni) {
		this.posizioniOmbrelloni = posizioniOmbrelloni;
	}


	public boolean sovrapposte(Prenotazione p)
	{
		//boolean DateSovrapposte = false;
		boolean result = false;
		LocalDate dataInizio1 = this.dataInizio;
		LocalDate dataFine1 = this.dataFine;
		LocalDate dataInizio2 = p.getDataInizio();
		LocalDate dataFine2 = p.getDataFine();
		
		if(dataInizio1.isBefore(dataFine2) && dataInizio2.isBefore(dataFine1))
		{
			 //Le prenotazioni sono sovrapposte 
		}
		else
		{
			//DateSovrapposte = false;
			return result;
		}
		
		for(int[] i: this.posizioniOmbrelloni)
		{
			for(int [] j : p.getPosizioniOmbrelloni())
			{
				if(Arrays.equals(i, j))
				{
					result = true;
					break;
				}
			}
		}
		
		return result;
	}
	
	
}
