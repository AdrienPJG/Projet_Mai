package Classes;

import java.util.ArrayList;
import java.util.List;

public class Option {
    private String idOption;
    private String nom;
    private int capacit�;
    private List<Etudiant> admis;
    private String fili�re;

    public Option(String idOption, String nom, int capacit�,String fili�re) {
        this.idOption = idOption;
        this.nom = nom;
        this.capacit� = capacit�;
        this.admis = new ArrayList<>();
        this.fili�re= fili�re;
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

    public int getCapacit�() {
        return capacit�;
    }

    public void setCapacit�(int capacit�) {
        this.capacit� = capacit�;
    }

    public List<Etudiant> getAdmis() {
        return admis;
    }

    public void setAdmis(List<Etudiant> admis) {
        this.admis = admis;
    }

    // M�thode pour ajouter un �tudiant � la liste des admis
    public boolean rajouterAdmis(Etudiant etudiant) {
        if (admis.size() < capacit�) {
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
    public String getFili�re() {
        return fili�re;
    }

    public void setFili�re(String fili�re) {
        this.fili�re = fili�re;
    }
    
    public static List<String> transformerOptionsEnNoms(List<Option> options) {
        List<String> nomsOptions = new ArrayList<>();
        for (Option option : options) {
            nomsOptions.add(option.getNom());
        }
        return nomsOptions;
    }
}