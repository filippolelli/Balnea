package Beans;


public class Ombrelloni {
	protected float tariffa;
	private int righe = 0;
	private int colonne = 0;

	private int quantit�;

	public Ombrelloni(float tariffa, int righe, int colonne, int quantit�) {
		this.tariffa = tariffa;
		this.quantit� = quantit�;
		this.righe = righe;
		this.colonne = colonne;
	}

	
	public int getRighe() {
		return righe;
	}

	public int getColonne() {
		return colonne;
	}

	public float getTariffa() {
		return tariffa;
	}

	public void setTariffa(float tariffa) {
		this.tariffa = tariffa;
	}

	public void setDisposizione(int righe, int colonne) {
		this.righe = righe;
		this.colonne = colonne;
	}

	public int getQuantit�() {
		return quantit�;
	}

	public void setQuantit�(int quantit�) {
		this.quantit� = quantit�;
	}
	
	
	
}