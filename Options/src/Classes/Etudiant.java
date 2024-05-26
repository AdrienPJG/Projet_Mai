package Classes;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import java.io.*;
import BDD.ChargerEtudiant;

public class Etudiant extends Personne {
    @SerializedName("numEtudiant")
    private String numEtudiant;
    
    @SerializedName("filière")
    private String filière;

    @SerializedName("moyenne")
    private double moyenne;

    @SerializedName("voeu")
    private Voeu voeu;
   
    private List<String> listeVoeux;

    @SerializedName("voeuxConfirmer")
    private boolean voeuxConfirmer = false;

    @SerializedName("optionRetenue")
    private String optionRetenue;

    public Etudiant(String nom, String prénom, String email, String numEtudiant, String filière, String motDePasse) {
        super(nom, prénom, email, motDePasse);
        this.numEtudiant = numEtudiant;
        this.filière = filière;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public String getFilière() {
        return filière;
    }

    public void setFilière(String filière) {
        this.filière = filière;
    }

    public static Etudiant findEtudiantByNum(String numEtudiant, List<Etudiant> etudiants) {
        for (Etudiant etudiant : etudiants) {
            if (etudiant.getNumEtudiant().equals(numEtudiant)) {
                return etudiant;
            }
        }
        return null;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public List<String> getListeVoeux() {
        return listeVoeux;
    }

    public void setListeVoeux(List<String> listeVoeux) {
        this.listeVoeux = listeVoeux;
        this.voeu.setListeVoeu(listeVoeux);
    }

    public boolean isVoeuxConfirmer() {
        return voeuxConfirmer;
    }

    public void setVoeuxConfirmer(boolean voeuxConfirmer) {
        this.voeuxConfirmer = voeuxConfirmer;
    }

    public String getOptionRetenue() {
        return optionRetenue;
    }

    public void setOptionRetenue(String optionRetenue) {
        this.optionRetenue = optionRetenue;
    }

    public void consulterOptions(List<Option> options) {
        System.out.println("Options disponibles pour la filière " + filière + ":");
        for (Option option : options) {
            if (option.getFilière().equals(this.filière)) {
                System.out.println(option.getNom());
            }
        }
    }

    public void remplirFicheDeVoeux(List<String> voeux) {
        this.listeVoeux = voeux;
    }

    public void confirmerFicheDeVoeux() {
        this.voeuxConfirmer = true;
    }

    public void consulterResultatsOrientation() {
        System.out.println("Option retenue: " + this.optionRetenue);
        System.out.println("Options non retenues: ");
        if(this.listeVoeux != null) {
	        for (String voeu : this.listeVoeux) {
	            if (!voeu.equals(this.optionRetenue)) {
	                System.out.println(voeu);
	            }
	        }
        }
        else {
        	 System.out.println("null");
        }
    }
    
    public Voeu getVoeux() {
    	return this.voeu;
    }
    
    public void setVoeux(Voeu voeu) {
    	this.voeu = voeu;
    }
    
    
    public static void ecrireEtudiantsDansJson(List<Etudiant> etudiants, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(etudiants, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void mettreAJourListeVoeu(List<String> nouvelleListeVoeu, String numEtudiant) {
        List<Etudiant> etudiants = ChargerEtudiant.chargerEtudiantsDepuisJSON("etudiants.json");

        if (etudiants != null) {
            for (Etudiant etudiant : etudiants) {
            	if(etudiant.getNumEtudiant().equals(numEtudiant)) {
	                Voeu voeu = etudiant.getVoeux();
	                voeu.setListeVoeu(nouvelleListeVoeu);
	                etudiant.listeVoeux = nouvelleListeVoeu;
            	}
            }
            ecrireEtudiantsDansJson(etudiants, "etudiants.json");
        }
    }
}

