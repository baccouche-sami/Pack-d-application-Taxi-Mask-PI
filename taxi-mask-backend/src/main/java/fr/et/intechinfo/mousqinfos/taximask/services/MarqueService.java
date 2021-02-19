package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;

import fr.et.intechinfo.mousqinfos.taximask.models.Marque;

public interface MarqueService {
	
	Marque createMarque(Marque marque);

	Marque updateMarque(Marque marque);

    List < Marque > getAllMarque();

    Marque getMarqueById(long MarqueId);

    void deleteMarque(long id);

}
