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
	
    public ResponsableOrientation(String nom, String pr�nom, String email, String idResponsable,List<Etudiant> etudiants,List<Option> options,String motDePasse) {
        super(nom, pr�nom, email,motDePasse);
        this.idResponsable = idResponsable;
        this.etudiants= etudiants;
        this.options=options;
    }

    public void activerRemplissage(Voeu voeu) {
        voeu.setVoeuConfirmer(true);
    }
    public void lancerOrientation(List<Voeu> voeux, List<Option> options) {
        // Trier les voeux bas� sur la moyenne des �tudiants (non pr�sent� ici, mais suppose une m�thode getMoyenne)
        
        for (Voeu voeu : voeux) {
            Etudiant etudiant = Classes.Etudiant.findEtudiantByNum(voeu.getNumEtudiant(),etudiants);
            
            for (String optionNom : voeu.getListeVoeu()) {
                Option option = Classes.Option.findOptionByName(optionNom, options);
                if (option != null && option.rajouterAdmis(etudiant)) {
                    System.out.println("�tudiant " + etudiant.getNom() + " admis dans l'option " + option.getNom());
                    break; // Sortir d�s qu'une option est attribu�e
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
    			System.out.println("L'�tudiant n�"+etudiant.getNumEtudiant()+" a obtenu l'option "+etudiant.getOptionRetenue());
    		}
    	}
    }

    public void RemplissageAleatoireVoeu() {
        Random random = new Random();
        
        for (Etudiant etudiant : etudiants) {
            // Liste pour stocker les noms des options al�atoires
            List<String> voeuxAleatoires = new ArrayList<>();
            
            // Filtrer les options de la m�me fili�re que l'�tudiant
            List<Option> optionsCompatibles = options.stream()
                    .filter(option -> option.getFili�re().equals(etudiant.getFili�re()))
                    .collect(Collectors.toList());
            
            if (!optionsCompatibles.isEmpty()) {
                // Ajouter toutes les options compatibles al�atoires � la liste des voeux
                for (Option option : optionsCompatibles) {
                    voeuxAleatoires.add(option.getNom());
                }

                // M�langer les voeux al�atoires
                List<String> voeuxMelanges = voeuxAleatoires.stream()
                        .sorted((o1, o2) -> random.nextInt(3) - 1)
                        .collect(Collectors.toList());

                // Assigner les voeux m�lang�s � l'�tudiant
                etudiant.setListeVoeux(voeuxMelanges);
                System.out.println("�tudiant " + etudiant.getNom() + " a �t� al�atoirement assign� aux options : " + voeuxMelanges);
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
                    System.out.println("�tudiant " + etudiant.getNom() +" "+ etudiant.getPrenom() + " a l'option retenue " + optionRetenue + " � la position " + position + " dans la liste de v�ux.");
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
                    System.out.println("L'option retenue " + optionRetenue + " de l'�tudiant " + etudiant.getNom() +" "+ etudiant.getPrenom() + " n'est pas dans la liste de v�ux.");
                }
            } else {
                System.out.println("L'�tudiant " + etudiant.getNom()+" "+ etudiant.getPrenom() + " n'a pas d'option retenue ou de liste de v�ux.");
            }
        }
        System.out.println(cpt1+" �tudiant(s) ont eu leur premier voeu, "+cpt2+" leur deuxi�me et "+cpt3+" ont eu leur troisi�me ou plus");
    }
    
    public List<Option> getOption(){
    	return options;
    }
}