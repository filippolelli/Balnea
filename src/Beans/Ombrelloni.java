package Beans;


public class Ombrelloni {
	protected float tariffa;
	private int righe = 0;
	private int colonne = 0;

	private int quantità;

	public Ombrelloni(float tariffa, int righe, int colonne, int quantità) {
		this.tariffa = tariffa;
		this.quantità = quantità;
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

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	
	
}