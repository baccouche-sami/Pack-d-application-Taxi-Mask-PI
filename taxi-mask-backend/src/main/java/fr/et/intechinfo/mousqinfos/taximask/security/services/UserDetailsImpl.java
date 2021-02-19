package fr.et.intechinfo.mousqinfos.taximask.security.services;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;

public class UserDetailsImpl implements UserDetails {
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Long id;

	private String username;

	private String email;
	
	private String prenom;
	
	private String nom;
	
	private String nomEntreprise;
	
	private String pays;
	
	private String adresse;
	
	private int codePostale;
	
	private String ville;
	
	private int mobile;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	

	public UserDetailsImpl(Long id, String username, String email, String prenom, String nom,
						   String nomEntreprise,
			String pays, String adresse, int codePostale, String ville, int mobile, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.prenom = prenom;
		this.nom = nom;
		this.nomEntreprise = nomEntreprise;
		this.pays = pays;
		this.adresse = adresse;
		this.codePostale = codePostale;
		this.ville = ville;
		this.mobile = mobile;
		this.password = password;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Utilisateur user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(),
				user.getEmail(),
				user.getNom(),
				user.getPrenom(),
				user.getNomEntreprise(),
				user.getPays(),
				user.getAdresse(),
				user.getCodePostale(),
				user.getVille(),
				user.getMobile(),
				user.getPassword(), 
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getEmail() {
		return email;
	}
	
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

}
