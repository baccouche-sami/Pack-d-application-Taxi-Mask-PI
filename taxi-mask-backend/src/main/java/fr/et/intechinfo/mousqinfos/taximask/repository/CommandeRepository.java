package fr.et.intechinfo.mousqinfos.taximask.repository;

import java.util.List;

import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.projections.NoUtilisateurCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.et.intechinfo.mousqinfos.taximask.models.Commande;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
	List<NoUtilisateurCommande> findByUtilisateur(Utilisateur utilisateur);
	List<NoUtilisateurCommande> findByUtilisateurAndComplete(Utilisateur utilisateur, Boolean complete);
	@Query("select c from Commande c where c.utilisateur=:user and c.complete=:complete")
	List<Commande> getCommandeByUtilisateurAndComplete(@Param("user") Utilisateur utilisateur, @Param("complete") Boolean complete);

	//@Query("select c from Commande c where c.utilisateur=:userId and c.complete=:complete")
	//List<Commande> getCommandeByUserComplete(@Param("complete") Boolean complete, @Param("userId") Long userId);

	//@Query("select sum(c.prixProtection) as price from Commande c where c.utilisateur=:userId and c.complete=:complete group by c.prixProtection ")
	//List getCommandesPrice(@Param("userId") Long userId, @Param("complete") Boolean complete);

	@Query("select coalesce(sum(c.prixProtection),0) as price from Commande c where c.complete=:complete ")
	List getCommandesPrice(@Param("complete") Boolean complete);

	Commande deleteByUtilisateur(Utilisateur utilisateur);

	@Query("select c from Commande c where c.id=:idCommande and c.utilisateur=:user and c.complete=false ")
	Commande getCommandeByIdAndUser(@Param("idCommande") Long id, @Param("user") Utilisateur user);


}
