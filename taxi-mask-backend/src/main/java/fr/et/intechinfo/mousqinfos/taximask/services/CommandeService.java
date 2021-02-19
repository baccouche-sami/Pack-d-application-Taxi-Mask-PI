package fr.et.intechinfo.mousqinfos.taximask.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import fr.et.intechinfo.mousqinfos.taximask.models.Commande;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.models.Voiture;
import fr.et.intechinfo.mousqinfos.taximask.repository.CommandeRepository;

import fr.et.intechinfo.mousqinfos.taximask.security.services.UserDetailsServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
public class CommandeService {

    private static Logger logger = LoggerFactory.getLogger(CommandeService.class);
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private VoitureService voitureService;
   
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Value("${upload-dir}")
    private String FILE_DIRECTORY;


    /**
     * Traitement d'un enregistrement de commande
     * @param commande
     * @return
     */
    public Commande traitementCommande(Commande commande) throws IOException {
        Utilisateur user =  userDetailsService.getCurrentUser();
        System.out.println("uuu==="+user);
        commande.setUtilisateur(user);
        String filename = storageFile(commande.getCarteGrise());
        commande.setCarteGriseFileName(filename);
        filename = storageFile(commande.getPhotoVoiture());
        commande.setPhotoVoitureFileName(filename);
        save(commande);
        return commande;
    }

    /**
     * Gestion de fichiers
     * @param file
     * @return
     * @throws IOException
     */
    public String storageFile(MultipartFile file) throws IOException {
        if(!new File(FILE_DIRECTORY).exists())
        {
            System.out.println("create");
            // Créer le dossier avec tous ses parents
            new File(FILE_DIRECTORY).mkdirs();
        }
        String codeUID= UUID.randomUUID().toString();
        String filename= codeUID+"."+ com.google.common.io.Files.getFileExtension(file.getOriginalFilename());
        Path fileNameAndPath = Paths.get(FILE_DIRECTORY, filename);
        Files.write(fileNameAndPath, file.getBytes());
        return filename;
    }

    /**
     * Enregistrement d'une commande
     * @param commande
     * @return
     * @throws Exception
     */
    public Commande save(Commande commande) {
    //logger.info(commande.toString());
        Voiture v = voitureService.getVoitureByImmatricule(commande.getImmatriculation());
        if(!(v instanceof Voiture)){
            //create a new Voiture
            v = new Voiture();
            v.setImmatriculation(commande.getImmatriculation());
            v.setMarque(commande.getMarque());
            v.setModele(commande.getModele());
            v.setDatePreimma(commande.getDateImmatriculation());
            voitureService.save(v);
        }
        //enregistrement commande
        commande.setVoiture(v);
         commandeRepository.save(commande);
         return commande;
    }

    /**
     * Liste des commandes par utilisateur
     * @return
     */
    public List<Commande> getCommandesByUserNoPaye() throws Exception{
        Utilisateur user =  userDetailsService.getCurrentUser();
        if(user!=null){
          List commandes=   commandeRepository.findByUtilisateurAndComplete(user,Boolean.FALSE);
            return commandes;
        }
        return  null;
    }


    /**
     * Suppression d'une commande
     * @param idCommande
     * @return
     */
    public Commande deleteCommande(Long idCommande){
        Utilisateur user =  userDetailsService.getCurrentUser();
        if(user!=null){
            Commande commande =  commandeRepository.findById(idCommande).get();
            if(commande!=null && commande.getUtilisateur().getId()==user.getId()){
                commandeRepository.delete(commande);
                return commande;
            }
        }
        return null;
    }

    /**
     * Mise à jour de la commande by user
     * @param commandeId
     * @return
     * @throws Exception
     */
    public List<Commande> updateCommandeWithUserId(String commandeId) {
        Utilisateur user =  userDetailsService.getCurrentUser();
        List commandes = new ArrayList();
        if(user!=null){
            String [] temp = commandeId.split(" ");
            for(String courant: temp){
                if(!courant.trim().isEmpty()){
                    Long id = Long.parseLong(courant.trim());
                    logger.info(id.toString());
                    Commande c = commandeRepository.findById(id).get();
                    if(c instanceof Commande && c.getUtilisateur()==null){
                        c.setUtilisateur(user);
                        commandeRepository.save(c);
                        commandes.add(c);
                    }
                }
            }
            return commandes;
        }
        return  null;
    }

    /**
     * List des commandes payes
     * @return
     */
    public List<Commande> getCommandesByUserComplete(){
        Utilisateur user =  userDetailsService.getCurrentUser();
        if(user!=null){
            List commandes=   commandeRepository.findByUtilisateurAndComplete(user,Boolean.TRUE);
            return commandes;
        }
        return null;
    }
}
