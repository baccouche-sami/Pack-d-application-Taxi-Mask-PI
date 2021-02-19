package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.et.intechinfo.mousqinfos.taximask.exceptions.ResourceNotFoundException;
import fr.et.intechinfo.mousqinfos.taximask.models.Avis;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.repository.AvisRepository;
import fr.et.intechinfo.mousqinfos.taximask.security.services.UserDetailsServiceImpl;


@Service


@Transactional
public class AvisServiceImpl implements AvisService {
	
	@Autowired
	AvisRepository avisRepository;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Override
	public Avis createAvis(Avis avis) {
		// TODO Auto-generated method stub
		 Utilisateur user = (Utilisateur) userDetailsService.getCurrentUser();
		 avis.setUtilisateur(user);
		return avisRepository.save(avis);
	}

	@Override
	public Avis validerAvis(Avis avis) {
		// TODO Auto-generated method stub
		Optional < Avis > AvisDb = this.avisRepository.findById(avis.getId());
		if (AvisDb.isPresent()) {
			Avis Avisupdate = AvisDb.get();
			Avisupdate.setEtat(true);
			avisRepository.save(Avisupdate);
			return Avisupdate;
		}
		else {
            throw new ResourceNotFoundException("Record not found with id : " + avis.getId());
        }
	}

	@Override
	public List<Avis> getAllAvis() {
		// TODO Auto-generated method stub
		return avisRepository.findAll();
	}

	@Override
	public Avis getAvisById(long avisId) {
		// TODO Auto-generated method stub
		Optional < Avis > AvisDb = this.avisRepository.findById(avisId);
		if (AvisDb.isPresent()) {
			return AvisDb.get();
		}
		else {
            throw new ResourceNotFoundException("Record not found with id : " + avisId);
        }
	}

	@Override
	public void deleteAvis(long id) {
		// TODO Auto-generated method stub
		Optional < Avis > AvisDb = this.avisRepository.findById(id);
		if (AvisDb.isPresent()) {
			 avisRepository.delete(AvisDb.get());;
		}
		else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
	}

}
