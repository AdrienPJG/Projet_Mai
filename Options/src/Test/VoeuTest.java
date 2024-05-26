package Test;
import Classes.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;

public class VoeuTest {
    
    @Test
    public void testConstructorAndGetters() {
        List<String> voeux = Arrays.asList("IA", "Cyber-Sécurité", "Data Science");
        Voeu voeu = new Voeu("001", "123456", voeux);
        
        assertEquals("001", voeu.getIdVoeu());
        assertEquals("123456", voeu.getNumEtudiant());
        assertEquals(voeux, voeu.getListeVoeu());
        assertFalse(voeu.isVoeuConfirmer());
    }

    @Test
    public void testSetters() {
        List<String> voeux = Arrays.asList("IA", "Cyber-Sécurité", "Data Science");
        Voeu voeu = new Voeu("002", "123456", voeux);
        
        voeu.setIdVoeu("002");
        voeu.setNumEtudiant("654321");
        voeu.setVoeuConfirmer(true);

        List<String> newVoeux = Arrays.asList("FineTech", "MMF");
        voeu.setListeVoeu(newVoeux);
        
        assertEquals("002", voeu.getIdVoeu());
        assertEquals("654321", voeu.getNumEtudiant());
        assertEquals(newVoeux, voeu.getListeVoeu());
        assertTrue(voeu.isVoeuConfirmer());
    }

    @Test
    public void testVoeuConfirm() {
        List<String> voeux = Arrays.asList("IA", "Cyber-Sécurité", "Data Science");
        Voeu voeu = new Voeu("003", "789101", voeux);
        
        assertFalse(voeu.isVoeuConfirmer());
        voeu.setVoeuConfirmer(true);
        assertTrue(voeu.isVoeuConfirmer());
    }
}