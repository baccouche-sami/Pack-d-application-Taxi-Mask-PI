package fr.et.intechinfo.mousqinfos.taximask.models;

import java.util.Date;

import javax.persistence.*;


import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "commandes")
@NoArgsConstructor
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "trappe")
	private String trappe;
	@Column(name = "typeProtection")
	private String typeProtection;
	@Column(name = "prixProtection")
	private double prixProtection;
	@Column(name = "toit")
	private String toit;
	@Column(name = "etiquette")
	private String etiquette;
	@CreationTimestamp
	private Date createdAt;
	@CreationTimestamp
	private Date updatedAt;
	@OneToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "voiture_id")
	private Voiture voiture;
	@OneToOne
	@JoinColumn(name = "facture_id", referencedColumnName = "id", nullable = true)
	private Facture facture;
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "user_id")
	private Utilisateur utilisateur;
	@Temporal(TemporalType.TIMESTAMP)
	@JoinColumn(nullable = true)
	private Date datePassage;
	@Transient
	private Date dateImmatriculation;
	@Transient
	private MultipartFile carteGrise;
	@JoinColumn(nullable = true)
	private String carteGriseFileName;
	@Transient
	private MultipartFile photoVoiture;

	@JoinColumn(nullable = true)
	private String photoVoitureFileName;
	@Transient
	private String immatriculation;
	@Transient
	private String modele;
	@Transient
	private String marque;
	private Boolean complete=Boolean.FALSE;
	@Temporal(TemporalType.TIMESTAMP)
	@JoinColumn(nullable = true)
	private Date dateComplete;
	@Column(name = "num_transaction", nullable = true)
	private String numTransaction;
	private Boolean statut =Boolean.FALSE;

	public long getId() {
		return id;
	}


	public String getTrappe() {
		return trappe;
	}

	public void setTrappe(String trappe) {
		this.trappe = trappe;
	}

	public String getTypeProtection() {
		return typeProtection;
	}

	public void setTypeProtection(String typeProtection) {
		this.typeProtection = typeProtection;
	}

	public double getPrixProtection() {
		return prixProtection;
	}

	public void setPrixProtection(double prixProtection) {
		this.prixProtection = prixProtection;
	}

	public String getToit() {
		return toit;
	}

	public void setToit(String toit) {
		this.toit = toit;
	}

	public String getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(String etiquette) {
		this.etiquette = etiquette;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}


	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Facture getFacture() {
		return facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Commande{" +
				"id=" + id +
				", trappe='" + trappe + '\'' +
				", typeProtection='" + typeProtection + '\'' +
				", prixProtection=" + prixProtection +
				", toit='" + toit + '\'' +
				", etiquette='" + etiquette + '\'' +
				", createdAt=" + createdAt +
				", updatedAt=" + updatedAt +
				", voiture=" + voiture +
				", facture=" + facture +
				", client=" + utilisateur +
				'}';
	}

	public Date getDatePassage() {
		return datePassage;
	}

	public void setDatePassage(Date datePassage) {
		this.datePassage = datePassage;
	}

	public MultipartFile getCarteGrise() {
		return carteGrise;
	}

	public void setCarteGrise(MultipartFile carteGrise) {
		this.carteGrise = carteGrise;
	}

	public MultipartFile getPhotoVoiture() {
		return photoVoiture;
	}

	public void setPhotoVoiture(MultipartFile photoVoiture) {
		this.photoVoiture = photoVoiture;
	}

	public String getCarteGriseFileName() {
		return carteGriseFileName;
	}

	public void setCarteGriseFileName(String carteGriseFileName) {
		this.carteGriseFileName = carteGriseFileName;
	}

	public String getPhotoVoitureFileName() {
		return photoVoitureFileName;
	}

	public void setPhotoVoitureFileName(String photoVoitureFileName) {
		this.photoVoitureFileName = photoVoitureFileName;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public Date getDateImmatriculation() {
		return dateImmatriculation;
	}

	public void setDateImmatriculation(Date dateImmatriculation) {
		this.dateImmatriculation = dateImmatriculation;
	}

	public Boolean getComplete() {
		return complete;
	}

	public void setComplete(Boolean complete) {
		this.complete = complete;
	}

	public Date getDateComplete() {
		return dateComplete;
	}

	public void setDateComplete(Date dateComplete) {
		this.dateComplete = dateComplete;
	}

	public String getNumTransaction() {
		return numTransaction;
	}

	public void setNumTransaction(String numTransaction) {
		this.numTransaction = numTransaction;
	}

	public Boolean getStatut() {
		return statut;
	}

	public void setStatut(Boolean statut) {
		this.statut = statut;
	}
}
