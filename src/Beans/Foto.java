package Beans;

import java.io.File;
import java.util.Objects;

public class Foto {
	private String descrizione;
	private File file;
	
	public Foto(String descrizione, File file) {
		super();
		this.descrizione = descrizione;
		this.file = file;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	@Override
	public int hashCode() {
		return Objects.hash(descrizione, file);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		return Objects.equals(descrizione, other.descrizione) && Objects.equals(file, other.file);
	}
	
	
}
