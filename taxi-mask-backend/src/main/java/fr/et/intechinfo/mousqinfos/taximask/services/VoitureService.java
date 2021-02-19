package fr.et.intechinfo.mousqinfos.taximask.services;

import fr.et.intechinfo.mousqinfos.taximask.models.Voiture;
import fr.et.intechinfo.mousqinfos.taximask.repository.VoitureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoitureService {
    @Autowired
    private VoitureRepository voitureRepository;

    /**
     *
     * @param id
     * @return
     */
    public Voiture getVoitureById(Long id){
        return voitureRepository.getOne(id);
    }

    /**
     *
     * @param immatricule
     * @return
     */
    public Voiture getVoitureByImmatricule(String immatricule){
        return voitureRepository.findByImmatriculation(immatricule);
    }

    /**
     * Enregistrement de voiture
     * @param v
     * @return
     */
    public Voiture save(Voiture v){
        return  voitureRepository.save(v);
    }
}
