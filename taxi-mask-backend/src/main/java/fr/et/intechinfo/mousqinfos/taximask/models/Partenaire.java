package fr.et.intechinfo.mousqinfos.taximask.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "partenaires")
public class Partenaire {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "url")
	private String url;
	
	
	@Column(name = "nom")
	private String nom;
	
	@Transient
	private MultipartFile logo;
	@JoinColumn(nullable = true)
	private String logoFileName;
	
	
	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public MultipartFile getLogo() {
		return logo;
	}


	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}


	public String getLogoFileName() {
		return logoFileName;
	}


	public void setLogoFileName(String logoFileName) {
		this.logoFileName = logoFileName;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	
	
	

}
