package fr.et.intechinfo.mousqinfos.taximask.models;



import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;



@Entity
@Table(	name = "users", uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") 
	})
public class Utilisateur   {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "nom")
	private String nom;
	
	@NotBlank
	@Column(name = "username", length = 65)
	private String username;
	
	@NotBlank
	@Column(name = "email", unique = true)
	private String email;
	
	@NotBlank
	@Column(name = "password")
	private String password;
	
	@Column(name = "nomEntreprise")
	@Nullable
	private String nomEntreprise;
	
	@Column(name = "pays")
	private String pays;
	
	@Column(name = "adresse")
	private String adresse ;
	
	@Column(name = "codePostale")
	private int codePostale;
	
	@Column(name = "ville")
	private String ville;
	
	@Column(name = "mobile")
	private int mobile;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	
	@OneToMany(mappedBy = "utilisateur", cascade = {
	        CascadeType.ALL
	    })
	private List < Avis > avis;


	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;
	public Utilisateur() {
		
	}
	
	

	public Utilisateur(String prenom, String nom, @NotBlank String username, @NotBlank String email,
			@NotBlank String password, String nomEntreprise, String pays, String adresse, int codePostale, String ville,
			int mobile) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.username = username;
		this.email = email;
		this.password = password;
		this.nomEntreprise = nomEntreprise;
		this.pays = pays;
		this.adresse = adresse;
		this.codePostale = codePostale;
		this.ville = ville;
		this.mobile = mobile;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getNomEntreprise() {
		return nomEntreprise;
	}

	public void setNomEntreprise(String nomEntreprise) {
		this.nomEntreprise = nomEntreprise;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public int getMobile() {
		return mobile;
	}

	public void setMobile(int mobile) {
		this.mobile = mobile;
	}

	
	public List < Avis > getAvis() {
		return avis;
	}

	public void setAvis(List < Avis > avis) {
		this.avis = avis;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	

	
	

	
	

	

	
	

}
