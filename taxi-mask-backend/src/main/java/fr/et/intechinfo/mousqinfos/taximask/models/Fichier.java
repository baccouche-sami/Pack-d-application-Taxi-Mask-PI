package fr.et.intechinfo.mousqinfos.taximask.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ms_fichier")
@Data
@NoArgsConstructor
public class Fichier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "extension")
	private String extension;

	@CreationTimestamp
	private Date createdAt;

	@CreationTimestamp
	private Date updatedAt;
	@Transient
	private MultipartFile file;


	

}
