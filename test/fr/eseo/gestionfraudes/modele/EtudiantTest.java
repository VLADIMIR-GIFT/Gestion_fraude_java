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

	@Test
	public void testSetters() {
		Etudiant etudiant = new Etudiant();
		etudiant.setNumApprenant("A042");
		etudiant.setNom("Martin");
		etudiant.setPrenom("Bob");
		etudiant.setCursus(Cursus.E4);
		assertEquals("A042", etudiant.getNumApprenant(), "Le numero n'a pas ete enregistre.");
		assertEquals("Martin", etudiant.getNom(), "Le nom n'a pas ete enregistre.");
		assertEquals("Bob", etudiant.getPrenom(), "Le prenom n'a pas ete enregistre.");
		assertEquals(Cursus.E4, etudiant.getCursus(), "Le cursus n'a pas ete enregistre.");
	}

	@Test
	public void testEgaliteCasParticuliers() {
		Etudiant etudiant = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		assertTrue(etudiant.equals(etudiant), "Un etudiant devrait etre egal a lui-meme.");
		assertFalse(etudiant.equals(null), "Un etudiant ne devrait pas etre egal a null.");
		assertFalse(etudiant.equals("A001"), "Un etudiant ne devrait pas etre egal a une chaine.");
	}

	@Test
	public void testEgaliteAvecNumeroNull() {
		Etudiant etudiant1 = new Etudiant();
		Etudiant etudiant2 = new Etudiant();
		assertFalse(etudiant1.equals(etudiant2), "Deux etudiants au numero null ne devraient pas etre egaux.");
	}
}
