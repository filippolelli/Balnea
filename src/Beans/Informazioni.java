package Beans;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Informazioni {
	private String descrizione;
	private String telefono;
	private String email;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private List<Foto> foto;
	
	public Informazioni(String descrizione, String telefono, String email, LocalDate dataInizio, LocalDate dataFine,
			List<Foto> foto) {
		super();
		this.descrizione = descrizione;
		this.telefono = telefono;
		this.email = email;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.foto = foto;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}
	public LocalDate getDataFine() {
		return dataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	public List<Foto> getFoto() {
		return foto;
	}
	public void setFoto(List<Foto> foto) {
		this.foto = foto;
	}
	@Override
	public int hashCode() {
		return Objects.hash(dataFine, dataInizio, descrizione, email, foto, telefono);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Informazioni other = (Informazioni) obj;
		return Objects.equals(dataFine, other.dataFine) && Objects.equals(dataInizio, other.dataInizio)
				&& Objects.equals(descrizione, other.descrizione) && Objects.equals(email, other.email)
				&& Objects.equals(foto, other.foto) && Objects.equals(telefono, other.telefono);
	}
	
	
}
