package Classes;

import java.util.*;
import java.util.stream.Collectors;

public class ResponsableOrientation extends Personne {
	private String idResponsable;
	private List<Etudiant> etudiants;
	private List<Option> options;
	private String motDePasse;
	private static int cpt1 = 0;
	private static int cpt2 = 0;
	private static int cpt3 = 0;
	
    public ResponsableOrientation(String nom, String prénom, String email, String idResponsable,List<Etudiant> etudiants,List<Option> options,String motDePasse) {
        super(nom, prénom, email,motDePasse);
        this.idResponsable = idResponsable;
        this.etudiants= etudiants;
        this.options=options;
    }

    public void activerRemplissage(Voeu voeu) {
        voeu.setVoeuConfirmer(true);
    }
    public void lancerOrientation(List<Voeu> voeux, List<Option> options) {
        // Trier les voeux basé sur la moyenne des étudiants (non présenté ici, mais suppose une méthode getMoyenne)
        
        for (Voeu voeu : voeux) {
            Etudiant etudiant = Classes.Etudiant.findEtudiantByNum(voeu.getNumEtudiant(),etudiants);
            
            for (String optionNom : voeu.getListeVoeu()) {
                Option option = Classes.Option.findOptionByName(optionNom, options);
                if (option != null && option.rajouterAdmis(etudiant)) {
                    System.out.println("Étudiant " + etudiant.getNom() + " admis dans l'option " + option.getNom());
                    break; // Sortir dès qu'une option est attribuée
                }
            }
        }
    }
    
    public List<Etudiant> getEtudiants(){
    	return this.etudiants;
    }
    
    public void afficherOptionAdmis() {
    	for(Etudiant etudiant : etudiants) {
    		if(!(etudiant.getOptionRetenue() == null)) {
    			System.out.println("L'étudiant n°"+etudiant.getNumEtudiant()+" a obtenu l'option "+etudiant.getOptionRetenue());
    		}
    	}
    }

    public void RemplissageAleatoireVoeu() {
        Random random = new Random();
        
        for (Etudiant etudiant : etudiants) {
            // Liste pour stocker les noms des options aléatoires
            List<String> voeuxAleatoires = new ArrayList<>();
            
            // Filtrer les options de la même filière que l'étudiant
            List<Option> optionsCompatibles = options.stream()
                    .filter(option -> option.getFilière().equals(etudiant.getFilière()))
                    .collect(Collectors.toList());
            
            if (!optionsCompatibles.isEmpty()) {
                // Ajouter toutes les options compatibles aléatoires à la liste des voeux
                for (Option option : optionsCompatibles) {
                    voeuxAleatoires.add(option.getNom());
                }

                // Mélanger les voeux aléatoires
                List<String> voeuxMelanges = voeuxAleatoires.stream()
                        .sorted((o1, o2) -> random.nextInt(3) - 1)
                        .collect(Collectors.toList());

                // Assigner les voeux mélangés à l'étudiant
                etudiant.setListeVoeux(voeuxMelanges);
                System.out.println("Étudiant " + etudiant.getNom() + " a été aléatoirement assigné aux options : " + voeuxMelanges);
            }
        }
    }
    
    public void afficherStatistiques() {
    	cpt1=0;
    	cpt2=0;
    	cpt3=0;
        for (Etudiant etudiant : etudiants) {
            String optionRetenue = etudiant.getOptionRetenue();
            List<String> listeVoeux = etudiant.getListeVoeux();
            
            if (optionRetenue != null && listeVoeux != null) {
                int position = listeVoeux.indexOf(optionRetenue);
                if (position >= 0) {
                    System.out.println("Étudiant " + etudiant.getNom() +" "+ etudiant.getPrenom() + " a l'option retenue " + optionRetenue + " à la position " + position + " dans la liste de vœux.");
                    if(position == 0) {
                    	cpt1++;
                    }
                    else if(position == 1) {
                    	cpt2++;
                    }
                    else {
                    	cpt3++;
                    }
                } else {
                    System.out.println("L'option retenue " + optionRetenue + " de l'étudiant " + etudiant.getNom() +" "+ etudiant.getPrenom() + " n'est pas dans la liste de vœux.");
                }
            } else {
                System.out.println("L'étudiant " + etudiant.getNom()+" "+ etudiant.getPrenom() + " n'a pas d'option retenue ou de liste de vœux.");
            }
        }
        System.out.println(cpt1+" étudiant(s) ont eu leur premier voeu, "+cpt2+" leur deuxième et "+cpt3+" ont eu leur troisième ou plus");
    }
    
    public List<Option> getOption(){
    	return options;
    }
}