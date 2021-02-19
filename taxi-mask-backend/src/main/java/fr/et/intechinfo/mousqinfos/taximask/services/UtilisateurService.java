package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;



import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;

public interface UtilisateurService {
	
	Utilisateur createUser(Utilisateur Utilisateur);

    Utilisateur updateUser(Utilisateur Utilisateur);

    List < Utilisateur > getAllUser();

    Utilisateur getUserById(long ClientId);

    void deleteUser(long id);
    
   

}
