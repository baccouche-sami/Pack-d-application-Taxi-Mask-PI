package fr.et.intechinfo.mousqinfos.taximask.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "roles")
public class Role implements Serializable {
 
	
	
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
 
	@Enumerated(EnumType.STRING)
	@Column(name = "name", length = 65)
	private ERole name;
 
	public Role() {
 
	}
 
	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}
 
	public ERole getName() {
		return name;
	}
 
	public void setName(ERole roleName) {
		this.name = roleName;
	}
 
}