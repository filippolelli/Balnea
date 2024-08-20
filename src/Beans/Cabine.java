package Beans;

import java.util.Objects;

public class Cabine {
	private float tariffa;
	private int quantit�;
	
	public Cabine(float tariffa, int quantit�) {
		super();
		this.tariffa = tariffa;
		this.quantit� = quantit�;
	}
	public float getTariffa() {
		return tariffa;
	}
	public void setTariffa(float tariffa) {
		this.tariffa = tariffa;
	}
	public int getQuantit�() {
		return quantit�;
	}
	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
	@Override
	public int hashCode() {
		return Objects.hash(quantit�, tariffa);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cabine other = (Cabine) obj;
		return quantit� == other.quantit� && Float.floatToIntBits(tariffa) == Float.floatToIntBits(other.tariffa);
	}
	

}
