package Test;

import Classes.*;
import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EtudiantTest {
    private List<Etudiant> etudiants;

    @Before
    public void setUp() {
        etudiants = new ArrayList<>();
        etudiants.add(new Etudiant("Alice", "Dupont", "alice@example.com", "001", "IA","mdp001"));
        etudiants.add(new Etudiant("Bob", "Martin", "bob@example.com", "002", "Cyber-Sécurité","mdp002"));
        etudiants.add(new Etudiant("Claire", "Simon", "claire@example.com", "003", "Data Science","mdp003"));
    }

    @Test
    public void testFindEtudiantByNumFound() {
        Etudiant etudiant = new Etudiant(null, null, null, null, null, null);
        Etudiant foundEtudiant = etudiant.findEtudiantByNum("002", etudiants);
        assertEquals("Bob", foundEtudiant.getNom());
    }

    @Test
    public void testFindEtudiantByNumNotFound() {
        Etudiant etudiant = new Etudiant(null, null, null, null, null, null);
        Etudiant foundEtudiant = etudiant.findEtudiantByNum("999", etudiants);
        assertNull(foundEtudiant);
    }
}