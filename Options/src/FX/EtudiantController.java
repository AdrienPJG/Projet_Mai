package FX;
import Classes.*;

import java.util.List;

import Classes.Etudiant;
import Classes.Option;

import java.util.List;

public class EtudiantController {

    private Etudiant etudiant;

    public EtudiantController(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    private List<Etudiant> etudiants;

    public EtudiantController(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    public void visualiserOptions(List<Option> options) {
        System.out.println("Options disponibles pour la filière " + etudiant.getFilière() + ":");
        for (Option option : options) {
            if (option.getFilière().equals(etudiant.getFilière())) {
                System.out.println(option.getNom());
            }
        }
    }

    public void definirVoeux(List<String> voeux) {
        etudiant.setListeVoeux(voeux);
    }

    public double visualiserMoyenne() {
        return etudiant.getMoyenne();
    }
    public String visualiserNom() {
        return etudiant.getNom();
    }
    public String visualiserPrenom() {
        return etudiant.getPrenom();
    }

    public void consulterResultatOrientation() {
        System.out.println("Résultat d'orientation pour l'étudiant: ");
        etudiant.consulterResultatsOrientation();
    }
    public Etudiant rechercherEtudiantParNomPrenom(String nom, String prenom) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNom().equalsIgnoreCase(nom) && etudiant.getPrenom().equalsIgnoreCase(prenom)) {
                return etudiant;
            }
        }
        return null; // Si l'étudiant avec le nom et prénom donnés n'est pas trouvé
    }
    public void afficherOptionsFiliere(String filiere, List<Option> options) {
        System.out.println("Options disponibles pour la filière " + filiere + ":");
        for (Option option : options) {
            if (option.getFilière().equals(filiere)) {
                System.out.println(option.getNom());
            }
        }
    }
    
    public void mettreAJourListeVoeu(List<String> nouvelleListeVoeu) {
    	Etudiant.mettreAJourListeVoeu(nouvelleListeVoeu, etudiant.getNumEtudiant());
    }
}