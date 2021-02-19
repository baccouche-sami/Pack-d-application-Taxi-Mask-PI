package fr.et.intechinfo.mousqinfos.taximask.controllers;

import com.stripe.model.Charge;
import fr.et.intechinfo.mousqinfos.taximask.models.Utilisateur;
import fr.et.intechinfo.mousqinfos.taximask.payload.response.StripeResponse;
import fr.et.intechinfo.mousqinfos.taximask.services.MailSenderService;
import fr.et.intechinfo.mousqinfos.taximask.services.StripeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private StripeService stripeService;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
    @Autowired
    private MailSenderService mailSenderService;

    @GetMapping("/stripe-public-key")
    public ResponseEntity <?> getPublicKey() {
        return ResponseEntity.ok().body(stripePublicKey);
    }



    @PostMapping("/charge-card")
    public ResponseEntity<?> chargeCard(HttpServletRequest request) throws Exception {
        String canceUrl = request.getParameter("cancel_url");
        String successUrl = request.getParameter("success_url");
        try {
            logger.info(request.getParameter("customer"));
            logger.info(request.getParameter("metadataId"));
            logger.info(request.getParameter("customer_session"));

            String tempComIds = request.getParameter("metadataId");
            String sessionToken = request.getParameter("customer_session");
            Double amount = Double.parseDouble(request.getParameter("amount"));

            logger.info(canceUrl);
            logger.info(successUrl);

            Utilisateur user= stripeService.traitementPaymentData(sessionToken, amount);

            if(user==null){
                return ResponseEntity.ok().body("<script  type=\"application/javascript\">\n" +
                        " window.location.href=\""+canceUrl+"\"\n" +
                        "</script>");
            }
            //
            String token = request.getParameter("stripeToken");
            Charge charge= stripeService.chargeNewCard(token, amount);
            System.out.println(request);
            logger.info(charge.getReceiptUrl());
            logger.info(charge.getStatus());
            if(charge.getStatus().equals("succeeded")){
                stripeService.updateCommande(user, charge.getBalanceTransaction(), amount);

                StripeResponse res = new StripeResponse();
                res.setId(charge.getId());
                res.setChargeId(charge.getId());
                res.setStatus(charge.getStatus());
                res.setBalanceTransaction(charge.getBalanceTransaction());
                mailSenderService.sendEmail(user.getEmail(), "REGLEMENT COMMANDE", "Payement effectué avec succès, n° transaction"+charge.getBalanceTransaction());
                return ResponseEntity.ok().body("<script  type=\"application/javascript\">\n" +
                        " window.location.href=\""+successUrl+"\"\n" +
                        "</script>");
            }else{
                return ResponseEntity.ok().body("<script  type=\"application/javascript\">\n" +
                                               " window.location.href=\""+canceUrl+"\"\n" +
                        "</script>");
            }

        }catch (Exception ex){
            logger.error(ex.getMessage());
                return ResponseEntity.ok().body("<script  type=\"application/javascript\">\n" +
                                 " window.location.href=\""+canceUrl+"\"\n" +
                                "</script>");
         }
    }



}
