package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;


import fr.et.intechinfo.mousqinfos.taximask.models.Prix;

public interface PrixService {
	
	Prix updatePrix(Prix prix);
	
	List < Prix > getAllPrix();

}
