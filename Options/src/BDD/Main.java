package BDD;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "names.json";  // Chemin vers votre fichier JSON

        JsonFileHandler jsonFileHandler = new JsonFileHandler();

        try {
            // Lire le fichier JSON
            List<JsonFileHandler.Person> personList = jsonFileHandler.readJsonFile(filePath);

            // Sélectionner une personne aléatoirement
            JsonFileHandler.Person randomPerson = jsonFileHandler.getRandomPerson(personList);

            // Afficher le nom et le prénom sélectionnés
            System.out.println("Nom: " + randomPerson.nom);
            System.out.println("Prénom: " + randomPerson.prenom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
