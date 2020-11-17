import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class TestApp {
	
	@Test
	public void testAnimaux() {
		Zoo z = new Zoo();
		z.ajouterSecteur(TypeAnimal.CHIEN);
		
		assertTrue(z.nombreAnimaux() == 0);
		
		try {
			z.nouvelAnimal(new Chien("Fido"));
		} catch (Throwable th) {
			
			assertTrue(th instanceof AnimalDansMauvaisSecteurException);
		}
		
		assertTrue(z.nombreAnimaux() == 1);
	}
	
	@Test
	public void testVisiteurs() {
		Zoo z = new Zoo();
		Throwable e = null;
		
		assertTrue(z.getLimiteVisiteur() == 0);
		assertTrue(z.getNbVisiteurs() == 0);
		
		try {
			z.nouveauVisiteur();
		} catch (Throwable th) {
			e = th;
		}
		
		assertTrue(e instanceof LimiteVisiteurException);
		assertTrue(z.getLimiteVisiteur() == 0);
		assertTrue(z.getNbVisiteurs() == 0);
		
		z.ajouterSecteur(TypeAnimal.CHIEN);
		
		assertTrue(z.getLimiteVisiteur() == 15);
		assertTrue(z.getNbVisiteurs() == 0);
		
		e = null;
		for (int i=0; i<15; i++) {
			try {
				z.nouveauVisiteur();
			} catch (Throwable th) {
				e = th;
			}
		}
		
		assertNull(e);
		assertTrue(z.getNbVisiteurs() <= z.getLimiteVisiteur());
		
	}
	
	@Test
	public void testTypeAnimaux() {
		Zoo z = new Zoo();
		Throwable t = null;
		z.ajouterSecteur(TypeAnimal.CHAT);
		try {
			z.nouvelAnimal(new Chat("Chat1"));
		}
		catch (AnimalDansMauvaisSecteurException e) {
			t = e;
		}
		assertTrue(t == null);
		
		t = null;
		try {
			z.nouvelAnimal(new Chien("Chien1"));
		}
		catch (AnimalDansMauvaisSecteurException e) {
			t = e;
		}
		assertNotEquals(null, t);		
	}
	
	public void testComparable() {
		Zoo z = new Zoo();
		z.ajouterSecteur(TypeAnimal.CHAT);
		z.ajouterSecteur(TypeAnimal.CHIEN);
		try {
			z.nouvelAnimal(new Chien("Chien1"));
		} catch (AnimalDansMauvaisSecteurException e) {
			assertFalse(true);
		}
		List<Secteur> secteurs = z.getSecteursAnimaux();
		assertTrue(secteurs.size() == 2);
		assertTrue(secteurs.get(0).compareTo(secteurs.get(1)) < 0);
		
	}
}
