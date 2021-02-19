package fr.et.intechinfo.mousqinfos.taximask.utils;


import fr.et.intechinfo.mousqinfos.taximask.models.Commande;
import fr.et.intechinfo.mousqinfos.taximask.models.Voiture;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
public class CommandeRequestBody {

    private List<Commande> listCommande;
    private Double price;
    private Integer quantity;

    public List<Commande> getListCommande() {
        return listCommande;
    }

    public void setListCommande(List<Commande> listCommande) {
        this.listCommande = listCommande;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
