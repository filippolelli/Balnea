package Beans;

import java.util.Objects;

public class Locazione { 
	private String indirizzo;
	private String città;
	private String stato;
	public Locazione(String indirizzo, String città, String stato) {
		super();
		this.indirizzo = indirizzo;
		this.città = città;
		this.stato = stato;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCittà() {
		return città;
	}
	public void setCittà(String città) {
		this.città = città;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	@Override
	public int hashCode() {
		return Objects.hash(città, indirizzo, stato);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locazione other = (Locazione) obj;
		return Objects.equals(città, other.città) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(stato, other.stato);
	}
	
	
}
