package BDD;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Classes.Etudiant;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ChargerEtudiant {

    public static List<Etudiant> chargerEtudiantsDepuisJSON(String nomFichier) {
        try (FileReader reader = new FileReader(nomFichier)) {
            Gson gson = new Gson();
            Type etudiantListType = new TypeToken<List<Etudiant>>(){}.getType();
            return gson.fromJson(reader, etudiantListType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}