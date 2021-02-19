package fr.et.intechinfo.mousqinfos.taximask.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.et.intechinfo.mousqinfos.taximask.exceptions.ResourceNotFoundException;
import fr.et.intechinfo.mousqinfos.taximask.models.Marque;
import fr.et.intechinfo.mousqinfos.taximask.repository.MarqueRepository;


@Service
@Transactional
public class MarqueServiceImpl implements MarqueService {

	
	@Autowired
	MarqueRepository marqueRepositiory; 
	
	
	@Override
	public Marque createMarque(Marque marque) {
		// TODO Auto-generated method stub
		return marqueRepositiory.save(marque);
	}

	@Override
	public Marque updateMarque(Marque marque) {
		// TODO Auto-generated method stub
		Optional < Marque > MarqueDb = this.marqueRepositiory.findById(marque.getId());
		if (MarqueDb.isPresent()) {
			Marque marqueupdate = MarqueDb.get();
			marqueupdate.setNom(marque.getNom());
			marqueRepositiory.save(marqueupdate);
			return marqueupdate;
		}
		else {
            throw new ResourceNotFoundException("Record not found with id : " + marque.getId());
        }
	}

	@Override
	public List<Marque> getAllMarque() {
		// TODO Auto-generated method stub
		return marqueRepositiory.findAllByOrderByNomAsc();
	}

	@Override
	public Marque getMarqueById(long MarqueId) {
		// TODO Auto-generated method stub
		Optional < Marque > MarqueDb = this.marqueRepositiory.findById(MarqueId);
		if (MarqueDb.isPresent()) {
			return MarqueDb.get();
		} else {
			 throw new ResourceNotFoundException("Record not found with id : " + MarqueId);
		}
	}

	@Override
	public void deleteMarque(long id) {
		// TODO Auto-generated method stub
		Optional < Marque > MarqueDb = this.marqueRepositiory.findById(id);
		if (MarqueDb.isPresent()) {
			marqueRepositiory.delete(MarqueDb.get());
		} else {
			 throw new ResourceNotFoundException("Record not found with id : " + id);
		}
		
	}

}
