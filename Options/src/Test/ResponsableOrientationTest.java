package Test;
import Classes.*;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ResponsableOrientationTest {
    private List<Etudiant> etudiants;
    private List<Option> options;
    private List<Voeu> voeux;

    @Before
    public void setUp() {
        // Initialisation des étudiants
        etudiants = new ArrayList<>();
        etudiants.add(new Etudiant("Alice", "Dupont", "alice@example.com", "001", "IA","mdp001"));
        etudiants.add(new Etudiant("Bob", "Martin", "bob@example.com", "002", "Cyber-Sécurité","mdp002"));
        etudiants.add(new Etudiant("Claire", "Simon", "claire@example.com", "003", "Data-Science","mdp003"));
        
        // Initialisation des options
        options = new ArrayList<>();
        options.add(new Option("001", "IA", 2,"GI"));
        options.add(new Option("002", "Cyber-Sécurité", 2,"GI"));
        options.add(new Option("003", "Data-Science", 2,"GM"));

        // Initialisation des voeux
        voeux = new ArrayList<>();
        List<String> voeuxAlice = new ArrayList<>();
        voeuxAlice.add("IA");
        voeux.add(new Voeu("001", null, voeuxAlice));

        List<String> voeuxBob = new ArrayList<>();
        voeuxBob.add("Cyber-Sécurité");
        voeux.add(new Voeu("002", null, voeuxBob));
    }

    @Test
    public void testActiverRemplissage() {
        ResponsableOrientation responsable = new ResponsableOrientation("Prof", "Duran", "prof.duran@example.com", "001",null, null,"mdp001");
        Voeu voeu = new Voeu("001","001", new ArrayList<>()); //dezbfbezhfbezhbfzegbvfezbfezhfihzbfhzebvfhezbhfbezhbfhezjbfhzejbfhejzbfhejbfhjezbjh
        assertFalse(voeu.isVoeuConfirmer());
        responsable.activerRemplissage(voeu);
        assertTrue(voeu.isVoeuConfirmer());
    }

    @Test
    public void testLancerOrientation() {
        ResponsableOrientation responsable = new ResponsableOrientation("Prof", "Duran", "prof.duran@example.com", "001",null, null,"mdp001");
        responsable.lancerOrientation(voeux, options);

        // Vérifie que les étudiants sont admis dans les bonnes options
        Option IA = options.get(0);
        Option cyber = options.get(1);

        assertEquals(1, IA.getAdmis().size());
        assertEquals("Alice", IA.getAdmis().get(0).getNom());

        assertEquals(1, cyber.getAdmis().size());
        assertEquals("Bob", cyber.getAdmis().get(0).getNom());
    }
}