package FX;

import Classes.*;

public class AuthentificationResponsable {

    private ResponsableOrientation responsable;

    public AuthentificationResponsable(ResponsableOrientation responsable) {
        this.responsable = responsable;
    }

    public boolean authentifier(String email, String motDePasse) {
        if (responsable.getEmail().equals(email) && responsable.getMotDePasse().equals(motDePasse)) {
            return true; // Authentification r�ussie
        } else {
            return false; // Authentification �chou�e
        }
    }

    public ResponsableOrientation getResponsableOrientation() {
        return responsable;
    }
}