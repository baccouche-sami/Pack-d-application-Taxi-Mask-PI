package fr.et.intechinfo.mousqinfos.taximask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.et.intechinfo.mousqinfos.taximask.models.Marque;

import java.util.List;

public interface MarqueRepository extends JpaRepository<Marque, Long> {

   List findAllByOrderByNomAsc();
	

}
