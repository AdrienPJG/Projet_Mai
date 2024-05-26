package FX;

import Classes.*;

import java.util.List;

public class ResponsableController {

    private ResponsableOrientation responsable;

    public ResponsableController(ResponsableOrientation responsable) {
        this.responsable = responsable;
    }

    public List<Etudiant> getEtudiants(){
    	return responsable.getEtudiants();
    }
    
    public void afficherOptionAdmis() {
    	responsable.afficherOptionAdmis();
    }
    
    public void RemplissageAleatoire() {
    	responsable.RemplissageAleatoireVoeu();
    }
    
    public void afficherStatistiques() {
    	responsable.afficherStatistiques();
    }
}