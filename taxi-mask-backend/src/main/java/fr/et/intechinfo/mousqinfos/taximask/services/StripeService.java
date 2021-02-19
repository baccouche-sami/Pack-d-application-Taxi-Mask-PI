package fr.et.intechinfo.mousqinfos.taximask.services;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import fr.et.intechinfo.mousqinfos.taximask.controllers.CommandeController;
import fr.et.intechinfo.mousqinfos.taximask.models.Commande;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.repository.CommandeRepository;
import fr.et.intechinfo.mousqinfos.taximask.repository.UtilisateurRepository;
import fr.et.intechinfo.mousqinfos.taximask.security.jwt.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class StripeService {
    @Value("${STRIPE_SECRET_KEY}")
    private String API_SECRET_KEY;

    private static Logger logger = LoggerFactory.getLogger(StripeService.class);
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private CommandeRepository commandeRepository;

    public StripeService() {

    }

    @PostConstruct
    public void initStripe(){
        Stripe.apiKey = API_SECRET_KEY;
    }

    public Charge chargeNewCard(String token, double amount) throws Exception {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "eur");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }

    /**
     *
     * @param sessionToken
     * @param amount
     * @return
     * @throws Exception
     */
    public Utilisateur traitementPaymentData(String sessionToken, Double amount) throws Exception {
        String username=  jwtUtils.getUserNameFromJwtToken(sessionToken);
        Utilisateur user = utilisateurRepository.findByUsername(username).get();
        logger.info(user.getUsername());
        if (!(user instanceof Utilisateur)){
            throw new Exception("Error: Unauthorized");
        }
        List<Commande> commandes = commandeRepository.getCommandeByUtilisateurAndComplete(user, Boolean.FALSE);
        logger.info(commandes.size()+"");
        Double totalAmount =0D;
        for (Commande c: commandes){
            if(c instanceof Commande){
                totalAmount+=c.getPrixProtection();
            }
        }
        logger.info(totalAmount.toString());
        if(amount.equals(totalAmount)){

            return user;
        }
        return null;
    }

    /**
     * Update commande apres payement
     * @param user
     * @param numTransaction
     * @param amount
     * @throws Exception
     */
    public void updateCommande(Utilisateur user, String numTransaction, Double amount) throws Exception {
        List<Commande> commandes = commandeRepository.getCommandeByUtilisateurAndComplete(user, Boolean.FALSE);
        for (Commande c: commandes){
            c.setComplete(true);
            c.setDateComplete(new Date());
            c.setNumTransaction(numTransaction);
            commandeRepository.save(c);
        }
    }
}
