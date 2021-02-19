package fr.et.intechinfo.mousqinfos.taximask.projections;

import fr.et.intechinfo.mousqinfos.taximask.models.Voiture;
import java.util.Date;

public interface NoUtilisateurCommande {

     long getId() ;
     String getTrappe()  ;
     String getTypeProtection();
     double getPrixProtection() ;
     String getToit() ;
     String getEtiquette() ;
     Date getCreatedAt() ;
     Date getUpdatedAt() ;
     Voiture getVoiture() ;
     Date getDatePassage() ;
     String getCarteGriseFileName() ;
     String getPhotoVoitureFileName() ;
     Boolean getComplete() ;
     Date getDateComplete() ;
     String getNumTransaction();
     Boolean getStatut();
}
