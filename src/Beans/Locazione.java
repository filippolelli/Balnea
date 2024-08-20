package Beans;

import java.util.Objects;

public class Locazione { 
	private String indirizzo;
	private String citt�;
	private String stato;
	public Locazione(String indirizzo, String citt�, String stato) {
		super();
		this.indirizzo = indirizzo;
		this.citt� = citt�;
		this.stato = stato;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitt�() {
		return citt�;
	}
	public void setCitt�(String citt�) {
		this.citt� = citt�;
	}
	public String getStato() {
		return stato;
	}
	public void setStato(String stato) {
		this.stato = stato;
	}
	@Override
	public int hashCode() {
		return Objects.hash(citt�, indirizzo, stato);
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
		return Objects.equals(citt�, other.citt�) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(stato, other.stato);
	}
	
	
}
