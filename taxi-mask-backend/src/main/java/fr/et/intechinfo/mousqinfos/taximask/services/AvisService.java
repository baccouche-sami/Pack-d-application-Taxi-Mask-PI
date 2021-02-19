package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;

import fr.et.intechinfo.mousqinfos.taximask.models.Avis;

public interface AvisService {
	
	Avis createAvis(Avis avis);
	
	Avis validerAvis(Avis avis);

    List < Avis > getAllAvis();

    Avis getAvisById(long avisId);

    void deleteAvis(long id);

}
