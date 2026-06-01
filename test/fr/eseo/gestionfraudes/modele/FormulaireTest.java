package fr.eseo.gestionfraudes.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.modele.fraude.FraudeIAG;

public class FormulaireTest {

	@Test
	public void testConstructeur() {
		Formulaire formulaire = new Formulaire();
		assertNotNull(formulaire.getEtudiants(), "La liste des etudiants est nulle.");
		assertTrue(formulaire.getEtudiants().isEmpty(), "La liste des etudiants n'est pas vide.");
		assertTrue(formulaire.getFraudes().isEmpty(), "La liste des fraudes n'est pas vide.");
		assertNotNull(formulaire.getDateCreation(), "La date de creation est nulle.");
	}

	@Test
	public void testAjouterEtudiant() {
		Formulaire formulaire = new Formulaire();
		Etudiant etudiant = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		formulaire.ajouterEtudiant(etudiant);
		assertEquals(1, formulaire.getEtudiants().size(), "Le nombre d'etudiants n'est pas le bon.");
		assertTrue(formulaire.getEtudiants().contains(etudiant), "L'etudiant ajoute n'est pas dans la liste.");
	}

	@Test
	public void testAjouterFraude() {
		Formulaire formulaire = new Formulaire();
		FraudeIAG fraude = new FraudeIAG(null, "desc", "ChatGPT");
		formulaire.ajouterFraude(fraude);
		assertEquals(1, formulaire.getFraudes().size(), "Le nombre de fraudes n'est pas le bon.");
		assertTrue(formulaire.getFraudes().contains(fraude), "La fraude ajoutee n'est pas dans la liste.");
	}

	@Test
	public void testSetEpreuveEtToString() {
		Formulaire formulaire = new Formulaire();
		Epreuve epreuve = new Epreuve("INF-01", null, null, Modalite.QCM);
		formulaire.setEpreuve(epreuve);
		assertEquals(epreuve, formulaire.getEpreuve(), "L'epreuve n'a pas ete enregistree.");
		assertTrue(formulaire.toString().contains("INF-01"), "Le toString devrait contenir le code de l'epreuve.");
	}
}
