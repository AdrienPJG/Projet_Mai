package Test;

import Classes.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class OptionTest {
    private List<Option> options;
    private Etudiant etudiant1;
    private Etudiant etudiant2;

    @Before
    public void setUp() {
        options = new ArrayList<>();
        options.add(new Option("001", "IA", 2, "GI"));
        options.add(new Option("002", "Data Science", 2, "GM"));
        options.add(new Option("003", "Cyber-Sécurité", 2, "GI"));
        
        etudiant1 = new Etudiant("Alice", "Dupont", "alice@example.com", "001", "IA","mdp001");
        etudiant2 = new Etudiant("Bob", "Martin", "bob@example.com", "002", "Data Science","mdp002");
    }

    @Test
    public void testFindOptionByNameFound() {
        Option option = new Option(null, null, 0, null);
        Option foundOption = option.findOptionByName("Data Science", options);
        assertEquals("Data Science", foundOption.getNom());
    }

    @Test
    public void testFindOptionByNameNotFound() {
        Option option = new Option(null, null, 0, null);
        Option foundOption = option.findOptionByName("Cyber-Sécurité", options);
        assertNull(foundOption);
    }

    @Test
    public void testRajouterAdmisSuccess() {
        Option IA = options.get(0);
        boolean result = IA.rajouterAdmis(etudiant1);
        assertTrue(result);
        assertEquals(1, IA.getAdmis().size());
        assertEquals("Alice", IA.getAdmis().get(0).getNom());
    }

    @Test
    public void testRajouterAdmisFailure() {
        Option IA = options.get(0);
        IA.rajouterAdmis(etudiant1);
        IA.rajouterAdmis(etudiant2);
        Etudiant etudiant3 = new Etudiant("Claire", "Simon", "claire@example.com", "003", "IA", "mdp003");
        boolean result =  IA.rajouterAdmis(etudiant3);
        assertFalse(result);
        assertEquals(2, IA.getAdmis().size()); // capacité est de 2, donc Claire ne peut pas être ajoutée
    }
}