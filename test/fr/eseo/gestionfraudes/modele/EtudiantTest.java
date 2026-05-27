package fr.eseo.gestionfraudes.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EtudiantTest {

	@Test
	public void testConstructeur() {
		Etudiant etudiant = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		assertEquals("A001", etudiant.getNumApprenant(), "Le numero d'apprenant n'est pas le bon.");
		assertEquals("Dupont", etudiant.getNom(), "Le nom n'est pas le bon.");
		assertEquals("Alice", etudiant.getPrenom(), "Le prenom n'est pas le bon.");
		assertEquals(Cursus.E1, etudiant.getCursus(), "Le cursus n'est pas le bon.");
	}

	@Test
	public void testEgaliteMemeNumero() {
		Etudiant etudiant1 = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Etudiant etudiant2 = new Etudiant("A001", "Autre", "Autre", Cursus.E5);
		assertTrue(etudiant1.equals(etudiant2), "Deux etudiants de meme numero devraient etre egaux.");
		assertEquals(etudiant1.hashCode(), etudiant2.hashCode(), "Les hashCode devraient etre egaux.");
	}

	@Test
	public void testEgaliteNumerosDifferents() {
		Etudiant etudiant1 = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Etudiant etudiant2 = new Etudiant("A002", "Dupont", "Alice", Cursus.E1);
		assertFalse(etudiant1.equals(etudiant2), "Deux etudiants de numeros differents ne devraient pas etre egaux.");
	}

	@Test
	public void testToString() {
		Etudiant etudiant = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		String texte = etudiant.toString();
		assertTrue(texte.contains("Alice"), "Le toString devrait contenir le prenom.");
		assertTrue(texte.contains("A001"), "Le toString devrait contenir le numero.");
	}
}
