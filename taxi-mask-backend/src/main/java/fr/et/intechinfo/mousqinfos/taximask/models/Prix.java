package fr.et.intechinfo.mousqinfos.taximask.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prix")
public class Prix {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "prixProtectionTotale")
	private double prixProtectionTotale;
	
	@Column(name = "prixProtectionPartielle")
	private double prixProtectionPartielle;
	
	@Column(name = "prixEtiquetteM")
	private double prixEtiquetteM;
	
	@Column(name = "prixEtiquetteXL")
	private double prixEtiquetteXL;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrixProtectionTotale() {
		return prixProtectionTotale;
	}

	public void setPrixProtectionTotale(double prixProtectionTotale) {
		this.prixProtectionTotale = prixProtectionTotale;
	}

	public double getPrixProtectionPartielle() {
		return prixProtectionPartielle;
	}

	public void setPrixProtectionPartielle(double prixProtectionPartielle) {
		this.prixProtectionPartielle = prixProtectionPartielle;
	}

	public double getPrixEtiquetteM() {
		return prixEtiquetteM;
	}

	public void setPrixEtiquetteM(double prixEtiquetteM) {
		this.prixEtiquetteM = prixEtiquetteM;
	}

	public double getPrixEtiquetteXL() {
		return prixEtiquetteXL;
	}

	public void setPrixEtiquetteXL(double prixEtiquetteXL) {
		this.prixEtiquetteXL = prixEtiquetteXL;
	}
	
	

}
