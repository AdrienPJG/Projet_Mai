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

            // S�lectionner une personne al�atoirement
            JsonFileHandler.Person randomPerson = jsonFileHandler.getRandomPerson(personList);

            // Afficher le nom et le pr�nom s�lectionn�s
            System.out.println("Nom: " + randomPerson.nom);
            System.out.println("Pr�nom: " + randomPerson.prenom);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
