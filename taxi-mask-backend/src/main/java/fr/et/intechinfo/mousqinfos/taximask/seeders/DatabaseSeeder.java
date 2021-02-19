package fr.et.intechinfo.mousqinfos.taximask.seeders;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.et.intechinfo.mousqinfos.taximask.models.ERole;
import fr.et.intechinfo.mousqinfos.taximask.models.Prix;
import fr.et.intechinfo.mousqinfos.taximask.models.Role;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.repository.PrixRepository;
import fr.et.intechinfo.mousqinfos.taximask.repository.RoleRepository;
import fr.et.intechinfo.mousqinfos.taximask.repository.UtilisateurRepository;





@Component
public class DatabaseSeeder {
	
    private Logger logger = Logger.getLogger(DatabaseSeeder.class.getName());
	private UtilisateurRepository utilisateurRepository;
	private RoleRepository roleRepository;
	private PrixRepository prixRepository;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public DatabaseSeeder(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository,PrixRepository prixRepository,
			JdbcTemplate jdbcTemplate) {
		
		this.utilisateurRepository = utilisateurRepository;
		this.roleRepository = roleRepository;
		this.jdbcTemplate = jdbcTemplate;
		this.prixRepository = prixRepository;
	}

	
	
	@EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoleTable();
        seedUsersTable();
        seedPrixTable();
	}
	private void seedPrixTable() {
	    String sql = "SELECT * FROM prix P ";
	    List<Prix> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
	    if(u == null || u.size() <= 0) {
	         Prix prix = new Prix();
	        prix.setPrixEtiquetteM(12);
	        prix.setPrixEtiquetteXL(12);
	        prix.setPrixProtectionPartielle(180);
	        prix.setPrixProtectionTotale(360);
	         prixRepository.save(prix);
	         logger.info("Prix Seeded");
	    } else {
	        logger.info("Prix Seeding Not Required");
	    }
	}
	private void seedRoleTable() {
	    String sql = "SELECT * FROM roles R ";
	    List<Role> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
	    if(u == null || u.size() <= 0) {
	         Role role1 = new Role();
	         Role role2 = new Role();
	         Role role3 = new Role();
	         role1.setName(ERole.ROLE_USER);
	         role2.setName(ERole.ROLE_ADMIN);
	         role3.setName(ERole.ROLE_SUPERADMIN);
	         roleRepository.save(role1);
	         roleRepository.save(role2);
	         roleRepository.save(role3);
	         logger.info("Roles Seeded");
	    } else {
	        logger.info("Roles Seeding Not Required");
	    }
	}
	private void seedUsersTable() {
	    String sql = "SELECT username, email FROM users U WHERE U.username = 'superadmin' OR U.email = 'superadmin@test.com' LIMIT 1";
	    List<Utilisateur> u = jdbcTemplate.query(sql, (resultSet, rowNum) -> null);
	    if(u == null || u.size() <= 0) {
	    	Utilisateur user = new Utilisateur();
	         user.setNom("Super");
	         user.setPrenom("Admin");
	         user.setUsername("superadmin");
	         user.setEmail("superadmin@test.com");
	         user.setPassword(new BCryptPasswordEncoder().encode("superadmin"));
	         Set<Role> roles = new HashSet<>();
	         Role userRole = roleRepository.findByName(ERole.ROLE_SUPERADMIN).orElseThrow(() -> new RuntimeException("Error: Le rôle n'est pas trouvé."));
			 roles.add(userRole);
			 user.setRoles(roles);
	       
	         utilisateurRepository.save(user);
	         logger.info("Users Seeded");
	    } else {
	        logger.info("Users Seeding Not Required");
	    }
	}
}
	
