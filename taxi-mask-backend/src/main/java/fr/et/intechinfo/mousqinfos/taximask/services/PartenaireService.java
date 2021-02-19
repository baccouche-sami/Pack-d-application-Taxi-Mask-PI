package fr.et.intechinfo.mousqinfos.taximask.services;

import java.io.IOException;
import java.util.List;

import fr.et.intechinfo.mousqinfos.taximask.models.Partenaire;

public interface PartenaireService {
	
	Partenaire createPartenaire(Partenaire partenaire) throws IOException;

	Partenaire updatePartenaire(Partenaire partenaire) throws IOException;

    List < Partenaire > getAllPartenaire();

    Partenaire getPartenaireById(long partenaireId);

    void deletePartenaire(long id);

}
