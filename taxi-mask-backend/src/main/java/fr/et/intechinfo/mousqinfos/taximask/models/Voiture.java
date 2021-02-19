package fr.et.intechinfo.mousqinfos.taximask.models;

import java.util.Date;

import javax.persistence.*;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "voitures")
@NoArgsConstructor
public class Voiture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "marque")
	private String marque;
	@Column(name = "modele", nullable = true)
	private String modele;
	@JoinColumn(unique = true, name = "immatriculation")
	private String immatriculation;
	@Column(name = "DatePreImma")
	@Temporal(TemporalType.DATE)
	private Date DatePreimma;
	@Column(name = "photoCarteGrise")
	private String photoCarteGrise;
	@Column(name = "photoVoiture")
	private String photoVoiture;
	
	
	
	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public long getId() {
		return id;
	}


	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Date getDatePreimma() {
		return DatePreimma;
	}

	public void setDatePreimma(Date datePreimma) {
		DatePreimma = datePreimma;
	}

	public String getPhotoCarteGrise() {
		return photoCarteGrise;
	}

	public void setPhotoCarteGrise(String photoCarteGrise) {
		this.photoCarteGrise = photoCarteGrise;
	}

	public String getPhotoVoiture() {
		return photoVoiture;
	}

	public void setPhotoVoiture(String photoVoiture) {
		this.photoVoiture = photoVoiture;
	}


	@Override
	public String toString() {
		return "Voiture{" +
				"id=" + id +
				", marque='" + marque + '\'' +
				", modele='" + modele + '\'' +
				", immatriculation='" + immatriculation + '\'' +
				", DatePreimma=" + DatePreimma +
				", photoCarteGrise='" + photoCarteGrise + '\'' +
				", photoVoiture='" + photoVoiture + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				'}';
	}
}
