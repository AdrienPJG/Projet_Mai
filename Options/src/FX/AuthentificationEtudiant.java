package FX;

import Classes.Etudiant;
import java.util.List;

public class AuthentificationEtudiant {

    private List<Etudiant> etudiants;

    public AuthentificationEtudiant(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public Etudiant authentifier(String email, String motDePasse) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getEmail().equals(email) && etudiant.getMotDePasse().equals(motDePasse)) {
                return etudiant; // Authentification réussie
            }
        }
        return null; // Authentification échouée
    }
}
