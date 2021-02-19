package fr.et.intechinfo.mousqinfos.taximask.repository;

import fr.et.intechinfo.mousqinfos.taximask.models.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
    Voiture findByImmatriculation(String immatricule);
}
