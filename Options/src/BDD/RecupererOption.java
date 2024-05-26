package BDD;

import com.google.gson.Gson;
import Classes.Option;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecupererOption {

    public static List<Option> recupererOptionsDepuisJSON(String nomFichier) {
        List<Option> options = new ArrayList<>();

        try (FileReader fileReader = new FileReader(nomFichier)) {
            Gson gson = new Gson();
            Option[] optionsArray = gson.fromJson(fileReader, Option[].class);
            
            for (Option option : optionsArray) {
                options.add(option);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return options;
    }

}