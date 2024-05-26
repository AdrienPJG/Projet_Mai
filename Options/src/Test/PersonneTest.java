package Test;
import Classes.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PersonneTest {
    
    // Classe concrète interne pour pouvoir instancier Personne et faire les tests
    private class ConcretePersonne extends Personne {
        public ConcretePersonne(String nom, String prenom, String email, String mdp) {
            super(nom, prenom, email, mdp);
        }
    }
    
    @Test
    public void testConstructorAndGetters() {
        ConcretePersonne personne = new ConcretePersonne("Dupont", "Alice", "alice@example.com","mdp001");
        assertEquals("Dupont", personne.getNom());
        assertEquals("Alice", personne.getPrenom());
        assertEquals("alice@example.com", personne.getEmail());
    }
    
    @Test
    public void testSetters() {
        ConcretePersonne personne = new ConcretePersonne("Dupont", "Alice", "alice@example.com","mdp001");
        
        personne.setNom("Martin");
        personne.setPrenom("Bob");
        personne.setEmail("bob@example.com");
        
        assertEquals("Martin", personne.getNom());
        assertEquals("Bob", personne.getPrenom());
        assertEquals("bob@example.com", personne.getEmail());
    }
}