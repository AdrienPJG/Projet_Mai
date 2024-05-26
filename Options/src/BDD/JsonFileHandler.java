package BDD;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

public class JsonFileHandler {
    
    private static final Gson gson = new Gson();

    // Classe pour repr�senter le nom et le pr�nom
    public static class Person {
        String nom;
        String prenom;

        public Person(String nom, String prenom) {
            this.nom = nom;
            this.prenom = prenom;
        }
    }

    // Lire le fichier JSON
    public List<Person> readJsonFile(String filePath) throws IOException {
        try (FileReader reader = new FileReader(filePath)) {
            Type personListType = new TypeToken<List<Person>>() {}.getType();
            return gson.fromJson(reader, personListType);
        }
    }

    // �crire dans un fichier JSON
    public void writeJsonFile(String filePath, List<Person> personList) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(personList, writer);
        }
    }

    // S�lectionner al�atoirement un nom et un pr�nom
    public Person getRandomPerson(List<Person> personList) {
        Random random = new Random();
        int randomIndex = random.nextInt(personList.size());
        return personList.get(randomIndex);
    }
}