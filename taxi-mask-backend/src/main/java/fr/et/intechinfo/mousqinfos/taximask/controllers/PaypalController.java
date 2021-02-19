package fr.et.intechinfo.mousqinfos.taximask.controllers;


import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.et.intechinfo.mousqinfos.taximask.services.PaypalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/test")
public class PaypalController {

    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "/success";
    public static final String CANCEL_URL = "/cancel";

    @PostMapping("/pay")
    public HashMap<String, String> payment() {
        HashMap<String, String> result = new HashMap<>();
        try {
            Payment payment = service.createPayment(15.0, "EUR", "paypal",
                    "sale", "description", "http://localhost:8080" + "/api/test" + CANCEL_URL,
                    "http://localhost:8080"+ "/api/test" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    result.put("url", link.getHref());
                    return result;
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "Paiement échoué !";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "Paiement réussi !";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}