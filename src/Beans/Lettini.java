package Beans;

import java.util.Objects;

public class Lettini {
	private float tariffa;
	private int quantità;
	public Lettini(float tariffa, int quantità) {
		super();
		this.tariffa = tariffa;
		this.quantità = quantità;
	}
	public float getTariffa() {
		return tariffa;
	}
	public void setTariffa(float tariffa) {
		this.tariffa = tariffa;
	}
	public int getQuantità() {
		return quantità;
	}
	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	@Override
	public int hashCode() {
		return Objects.hash(quantità, tariffa);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lettini other = (Lettini) obj;
		return quantità == other.quantità && Float.floatToIntBits(tariffa) == Float.floatToIntBits(other.tariffa);
	}
	

}
