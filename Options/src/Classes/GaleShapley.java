package Classes;

import java.util.*;

public class GaleShapley {
    private Map<String, Option> options;
    private List<Etudiant> etudiants;

    public GaleShapley(Map<String, Option> options, List<Etudiant> etudiants) {
        this.options = options;
        this.etudiants = etudiants;
    }

    public void executer() {
        Queue<Etudiant> etudiantsLibres = new LinkedList<>(etudiants);

        while (!etudiantsLibres.isEmpty()) {
            Etudiant etudiant = etudiantsLibres.poll();
            List<String> preferences = etudiant.getListeVoeux();
            if(preferences != null) {
	            for (String optionNom : preferences) {
	                Option option = options.get(optionNom);
	
	                if (option.rajouterAdmis(etudiant)) {
	                    etudiant.setOptionRetenue(optionNom);
	                    etudiant.setVoeuxConfirmer(true);
	                    break;
	                } else {
	                    Etudiant etudiantMoinsPrefere = trouverEtudiantMoinsPrefere(option);
	
	                    if (etudiant.getMoyenne() > etudiantMoinsPrefere.getMoyenne()) {
	                        etudiantsLibres.add(etudiantMoinsPrefere);
	                        if(option.retirerAdmis(etudiantMoinsPrefere) && option.rajouterAdmis(etudiant)) {
		                        etudiant.setOptionRetenue(optionNom);
		                        etudiant.setVoeuxConfirmer(true);
		                        etudiantMoinsPrefere.setOptionRetenue(null);
		                        etudiantMoinsPrefere.setVoeuxConfirmer(false);
		                        break;
	                        }
	                    }
	                }
	            }
            }
            else {
            	System.out.println("L'étudiant n°"+etudiant.getNumEtudiant()+" n'a pas encore choisi de voeux");
            }
        }
        Etudiant.ecrireEtudiantsDansJson(etudiants, "etudiants.json");
    }

    private Etudiant trouverEtudiantMoinsPrefere(Option option) {
        Etudiant etudiantMoinsPrefere = null;
        double moyenneMin = Double.MAX_VALUE;

        for (Etudiant etudiant : option.getAdmis()) {
            if (etudiant.getMoyenne() < moyenneMin) {
                moyenneMin = etudiant.getMoyenne();
                etudiantMoinsPrefere = etudiant;
            }
        }

        return etudiantMoinsPrefere;
    }
}