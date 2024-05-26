package Classes;

import java.util.List;

public class Voeu {
	private String idVoeu;
    private String numEtudiant;
    private List<String> listeVoeu;
    private boolean voeuConfirmer;

    public Voeu(String idVoeu, String numEtudiant, List<String> listeVoeu) {
        this.idVoeu = idVoeu;
        this.numEtudiant = numEtudiant;
        this.listeVoeu = listeVoeu;
        this.voeuConfirmer = false;
    }
	public String getIdVoeu() {
        return idVoeu;
    }

    public void setIdVoeu(String idVoeu) {
        this.idVoeu = idVoeu;
    }

    public String getNumEtudiant() {
        return numEtudiant;
    }

    public void setNumEtudiant(String numEtudiant) {
        this.numEtudiant = numEtudiant;
    }

    public List<String> getListeVoeu() {
        return listeVoeu;
    }

    public void setListeVoeu(List<String> listeVoeu) {
        this.listeVoeu = listeVoeu;
    }
    
    public boolean isVoeuConfirmer() {
        return voeuConfirmer;
    }

    public void setVoeuConfirmer(boolean voeuConfirmer) {
        this.voeuConfirmer = voeuConfirmer;
    }


}
