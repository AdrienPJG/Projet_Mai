package Classes;

import java.util.ArrayList;
import java.util.List;

public class Option {
    private String idOption;
    private String nom;
    private int capacité;
    private List<Etudiant> admis;
    private String filière;

    public Option(String idOption, String nom, int capacité,String filière) {
        this.idOption = idOption;
        this.nom = nom;
        this.capacité = capacité;
        this.admis = new ArrayList<>();
        this.filière= filière;
    }
    public String getIdOption() {
        return idOption;
    }

    public void setIdOption(String idOption) {
        this.idOption = idOption;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacité() {
        return capacité;
    }

    public void setCapacité(int capacité) {
        this.capacité = capacité;
    }

    public List<Etudiant> getAdmis() {
        return admis;
    }

    public void setAdmis(List<Etudiant> admis) {
        this.admis = admis;
    }

    // Méthode pour ajouter un étudiant à la liste des admis
    public boolean rajouterAdmis(Etudiant etudiant) {
        if (admis.size() < capacité) {
            admis.add(etudiant);
            return true;
        }
        return false;
    }
    
    public boolean retirerAdmis(Etudiant etudiant) {
    	return admis.remove(etudiant);
    }
    public static Option findOptionByName(String name, List<Option> options) {
        for (Option option : options) {
            if (option.getNom().equalsIgnoreCase(name)) {
                return option;
            }
        }
        return null;
    }
    public String getFilière() {
        return filière;
    }

    public void setFilière(String filière) {
        this.filière = filière;
    }
    
    public static List<String> transformerOptionsEnNoms(List<Option> options) {
        List<String> nomsOptions = new ArrayList<>();
        for (Option option : options) {
            nomsOptions.add(option.getNom());
        }
        return nomsOptions;
    }
}