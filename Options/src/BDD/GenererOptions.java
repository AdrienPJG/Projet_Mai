package BDD;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import Classes.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenererOptions {

    public static void main(String[] args) {
        List<Option> options = new ArrayList<>();
        options.add(new Option("001", "MMF", 125,"GM"));
        options.add(new Option("002", "FineTech", 125,"GM"));
        options.add(new Option("003", "DataScience", 125,"GM"));
        options.add(new Option("004", "IA", 125,"GI"));
        options.add(new Option("005", "Cyber-Sécurité", 125,"GI"));
        options.add(new Option("006", "BI", 125,"GI"));
        options.add(new Option("007", "Visual Computing", 125,"GI"));
        options.add(new Option("008", "Acturiat", 125,"GM"));

        // Convertir la liste d'options en JSON et l'écrire dans un fichier
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("options.json")) {
            gson.toJson(options, writer);
            System.out.println("Les options ont été générées et écrites dans options.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}