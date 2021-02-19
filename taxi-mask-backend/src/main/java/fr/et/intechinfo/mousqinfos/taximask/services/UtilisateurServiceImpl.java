package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;
import java.util.Optional;

import fr.et.intechinfo.mousqinfos.taximask.exceptions.ResourceNotFoundException;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	
	@Autowired
    private UtilisateurRepository utilisateurRepository;

	@Override
	public Utilisateur createUser(Utilisateur Utilisateur) {
		// TODO Auto-generated method stub
		 return utilisateurRepository.save(Utilisateur);
	}

	@Override
	public Utilisateur updateUser(Utilisateur Utilisateur) {
		// TODO Auto-generated method stub
		Optional < Utilisateur > ClientDb = this.utilisateurRepository.findById(Utilisateur.getId());

        if (ClientDb.isPresent()) {
            Utilisateur ClientUpdate = ClientDb.get();
            ClientUpdate.setId(Utilisateur.getId());
            ClientUpdate.setNom(Utilisateur.getNom());
            ClientUpdate.setPrenom(Utilisateur.getPrenom());
            ClientUpdate.setNomEntreprise(Utilisateur.getNomEntreprise());
            ClientUpdate.setPays(Utilisateur.getPays());
            ClientUpdate.setAdresse(Utilisateur.getAdresse());
            ClientUpdate.setCodePostale(Utilisateur.getCodePostale());
            ClientUpdate.setVille(Utilisateur.getVille());
            ClientUpdate.setMobile(Utilisateur.getMobile());
            ClientUpdate.setEmail(Utilisateur.getEmail());
            ClientUpdate.setPassword(Utilisateur.getPassword());
            utilisateurRepository.save(ClientUpdate);
            return ClientUpdate;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + Utilisateur.getId());
        }
	}

	@Override
	public List<Utilisateur> getAllUser() {
		// TODO Auto-generated method stub
		return this.utilisateurRepository.findAll();
	}

	@Override
	public Utilisateur getUserById(long ClientId) {
		// TODO Auto-generated method stub
		Optional < Utilisateur > clientDb = this.utilisateurRepository.findById(ClientId);

        if (clientDb.isPresent()) {
            return clientDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + ClientId);
        }
	}

	@Override
	public void deleteUser(long id) {
		// TODO Auto-generated method stub
		Optional < Utilisateur > clientDb = this.utilisateurRepository.findById(id);

        if (clientDb.isPresent()) {
        	this.utilisateurRepository.delete(clientDb.get());
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + id);
        }
	}


	
	
	

}
