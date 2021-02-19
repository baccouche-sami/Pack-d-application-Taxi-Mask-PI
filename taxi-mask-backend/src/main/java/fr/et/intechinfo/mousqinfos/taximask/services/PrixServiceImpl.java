package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.et.intechinfo.mousqinfos.taximask.exceptions.ResourceNotFoundException;
import fr.et.intechinfo.mousqinfos.taximask.models.Prix;
import fr.et.intechinfo.mousqinfos.taximask.repository.PrixRepository;




@Service
@Transactional
public class PrixServiceImpl implements PrixService {
	
	
	@Autowired
	PrixRepository prixRepository;
	

	@Override
	public Prix updatePrix(Prix prix) {
		// TODO Auto-generated method stub
				Optional < Prix > PrixDb = this.prixRepository.findById(prix.getId());
				if (PrixDb.isPresent()) {
					Prix prixupdate = PrixDb.get();
					prixupdate.setPrixEtiquetteM(prix.getPrixEtiquetteM());
					prixupdate.setPrixEtiquetteXL(prix.getPrixEtiquetteXL());
					prixupdate.setPrixProtectionPartielle(prix.getPrixProtectionPartielle());
					prixupdate.setPrixProtectionTotale(prix.getPrixProtectionTotale());
					prixRepository.save(prixupdate);
					return prixupdate;
				}
				else {
		            throw new ResourceNotFoundException("Record not found with id : " + prix.getId());
		        }
	}

	@Override
	public List<Prix> getAllPrix() {
		// TODO Auto-generated method stub
		return prixRepository.findAll();
	}

}
