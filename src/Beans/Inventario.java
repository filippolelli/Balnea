package Beans;


import java.util.Objects;

public class Inventario {
	private Ombrelloni ombrelloni;
	private Lettini lettini;
	private Cabine cabine;
	
	public Inventario(Ombrelloni ombrelloni, Lettini lettini, Cabine cabine) {
		super();
		this.ombrelloni = ombrelloni;
		this.lettini = lettini;
		this.cabine = cabine;
	}
	public Ombrelloni getOmbrelloni() {
		return ombrelloni;
	}
	public void setOmbrelloni(Ombrelloni ombrelloni) {
		this.ombrelloni = ombrelloni;
	}
	public Lettini getLettini() {
		return lettini;
	}
	public void setLettini(Lettini lettini) {
		this.lettini = lettini;
	}
	public Cabine getCabine() {
		return cabine;
	}
	public void setCabine(Cabine cabine) {
		this.cabine = cabine;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cabine, lettini, ombrelloni);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventario other = (Inventario) obj;
		return Objects.equals(cabine, other.cabine) && Objects.equals(lettini, other.lettini)
				&& Objects.equals(ombrelloni, other.ombrelloni);
	}
	
	
}
