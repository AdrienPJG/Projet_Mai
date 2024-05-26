package Classes;

import com.google.gson.annotations.SerializedName;

public abstract class Personne {
	@SerializedName("nom")
	private String nom;
	 @SerializedName("prenom")
    private String prenom;
	 @SerializedName("email")
    private String email;
	 @SerializedName("motDePasse")
    private String motDePasse;

    public Personne(String nom, String prenom, String email,String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse=motDePasse;
    }
	
	public String getEmail() {
		return this.email;
	}
	public String getPrenom() {
		return this.prenom;
	}
	public String getNom() {
		return this.nom;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMotDePasse() {
		return this.motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse=motDePasse;
	}


}
