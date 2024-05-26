package BDD;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonGenerator {
    // Classe repr�sentant une personne avec un nom et un pr�nom
    static class Person {
        String nom;
        String prenom;

        Person(String nom, String prenom) {
            this.nom = nom;
            this.prenom = prenom;
        }
    }

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();

        // Listes de noms et pr�noms courants
        String[] noms = {"Dupont", "Martin", "Bernard", "Thomas", "Petit", "Robert", "Richard", "Durand", "Dubois", "Moreau",
                         "Laurent", "Lefebvre", "Simon", "Lemoine", "Lacroix", "Brun", "Clement", "Girard", "Roux", "Morin"};
        String[] prenoms = {"Jean", "Pierre", "Marie", "Luc", "Alain", "Sophie", "Marc", "Nathalie", "Michel", "Patricia",
                            "Thierry", "Isabelle", "Antoine", "Jeanne", "Julien", "Claire", "Paul", "Jacqueline", "Henri", "Emilie"};

        // G�n�rer 1 000 personnes avec des noms et pr�noms al�atoires
        for (int i = 0; i < 1000; i++) {
            String nom = noms[(int) (Math.random() * noms.length)];
            String prenom = prenoms[(int) (Math.random() * prenoms.length)];
            personList.add(new Person(nom, prenom));
        }

        // �crire cette liste dans un fichier JSON
        try (FileWriter writer = new FileWriter("names.json")) {
            new Gson().toJson(personList, writer);
            System.out.println("Fichier JSON g�n�r� avec succ�s.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}