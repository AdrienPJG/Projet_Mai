package BDD;

import Classes.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenererMoyenne {

    private static final Random random = new Random();
    private static final double MOYENNE = 12.0; // La moyenne des moyennes
    private static final double ECART_TYPE = 3.0; // L'écart-type des moyennes

    // Liste de noms et prénoms aléatoires
    private static String[] noms = {
        "Dupont", "Martin", "Bernard", "Thomas", "Petit", 
        "Robert", "Richard", "Durand", "Dubois", "Moreau",
        "Laurent", "Lefebvre", "Simon", "Lemoine", "Lacroix", 
        "Brun", "Clement", "Girard", "Roux", "Morin"
    };

    private static String[] prenoms = {
        "Jean", "Pierre", "Marie", "Luc", "Alain", 
        "Sophie", "Marc", "Nathalie", "Michel", "Patricia",
        "Thierry", "Isabelle", "Antoine", "Jeanne", "Julien", 
        "Claire", "Paul", "Jacqueline", "Henri", "Emilie"
    };

    public static void main(String[] args) {
        List<String> namesList = generateRandomNames(1000); // Générer 1000 noms et prénoms aléatoires

        List<Etudiant> etudiants = new ArrayList<>();
       
        // Générer une liste d'étudiants avec des moyennes aléatoires
        for (int i = 0; i < namesList.size(); i++) {
            String[] fullName = namesList.get(i).split(" ");
            String nom = fullName[0];
            String motDePasse = String.format("%03d", i + 1);
            String prenom = fullName[1];
            String numEtudiant = String.format("%03d", i + 1);
            String email = "etudiant" + numEtudiant + "@example.com";
            String filiere = (random.nextBoolean()) ? "GM" : "GI";
            List<String> voeux = new ArrayList<>();
            Voeu voeu = new Voeu(numEtudiant,numEtudiant,voeux);
            double moyenne = genererMoyenneAleatoire();
            
            Etudiant etudiant = new Etudiant(nom, prenom, email, numEtudiant, filiere, motDePasse);
            etudiant.setVoeux(voeu);
            etudiant.setMoyenne(moyenne);
            etudiants.add(etudiant); // Ajout à la liste des étudiants
        }

        // Convertir la liste d'étudiants en JSON et l'écrire dans un fichier
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("etudiants.json")) {
            gson.toJson(etudiants, writer);
            System.out.println("Les moyennes des étudiants ont été générées et écrites dans etudiants.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double genererMoyenneAleatoire() {
        return Math.max(0, Math.min(20, MOYENNE + ECART_TYPE * random.nextGaussian()));
    }

    private static List<String> generateRandomNames(int count) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String nom = noms[random.nextInt(noms.length)];
            String prenom = prenoms[random.nextInt(prenoms.length)];
            String fullName = nom + " " + prenom;
            names.add(fullName);
        }
        return names;
    }
}

