package fr.et.intechinfo.mousqinfos.taximask.controllers;

import com.stripe.exception.StripeException;
import fr.et.intechinfo.mousqinfos.taximask.models.Commande;
import fr.et.intechinfo.mousqinfos.taximask.payload.response.MessageResponse;
import fr.et.intechinfo.mousqinfos.taximask.services.CommandeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    private static Logger logger = LoggerFactory.getLogger(CommandeController.class);
    @Autowired
    private CommandeService commandeService;

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";


    @PostMapping
    public ResponseEntity <?> createCommande(@ModelAttribute Commande commande) {
        try {
          Commande c = commandeService.traitementCommande(commande);
          return ResponseEntity.ok().body(c.getId());
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ex.getMessage()));
        }
    }

    /**
     * Liste des commandes par utilisateur
     * @return
     */
    @GetMapping
    public ResponseEntity <?> getCommandesByUser(){
        try {
            List commandes = commandeService.getCommandesByUserNoPaye();
            return ResponseEntity.ok().body(commandes);
        }catch (Exception ex){
            logger.error(ex.getMessage());
            return  ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(ex.getMessage()));
        }

    }

    @GetMapping("/update-commande/{cards}")
    public ResponseEntity <?> updateCommandeWithUserId(@PathVariable String cards){
      try {
        List commandes = commandeService.updateCommandeWithUserId(cards);
        return ResponseEntity.ok().body(commandes);
      }catch (Exception ex){
           logger.error(ex.getMessage());
            return  ResponseEntity
                   .badRequest()
                    .body(new MessageResponse(ex.getMessage()));
      }

    }

    @GetMapping("/del/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable Long id){
       Commande commande = commandeService.deleteCommande(id);
       return ResponseEntity.ok().body(commande);
    }

    @GetMapping("/order-complete")
    public ResponseEntity<?> getCommandeComplete(){
        List commandes = commandeService.getCommandesByUserComplete();
        return ResponseEntity.ok().body(commandes);
    }


    @GetMapping("/all")
    public String getCommandesAllByUser(@RequestBody String userId){
        return "cooo";
    }
}
