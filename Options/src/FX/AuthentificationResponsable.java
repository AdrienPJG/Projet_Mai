package FX;

import Classes.*;

public class AuthentificationResponsable {

    private ResponsableOrientation responsable;

    public AuthentificationResponsable(ResponsableOrientation responsable) {
        this.responsable = responsable;
    }

    public boolean authentifier(String email, String motDePasse) {
        if (responsable.getEmail().equals(email) && responsable.getMotDePasse().equals(motDePasse)) {
            return true; // Authentification réussie
        } else {
            return false; // Authentification échouée
        }
    }

    public ResponsableOrientation getResponsableOrientation() {
        return responsable;
    }
}