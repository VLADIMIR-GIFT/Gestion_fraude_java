package fr.eseo.gestionfraudes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.modele.Cursus;
import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;

public class GestionFraudesTest {

	@Test
	public void testAjouterFormulaire() {
		GestionFraudes gestion = new GestionFraudes();
		Formulaire formulaire = new Formulaire();
		gestion.ajouterFormulaire(formulaire);
		assertEquals(1, gestion.getFormulaires().size(), "Le formulaire n'a pas ete ajoute.");
		assertTrue(gestion.getFormulaires().contains(formulaire), "Le formulaire ajoute n'est pas dans la liste.");
	}

	@Test
	public void testRetirerFormulaireExistant() {
		GestionFraudes gestion = new GestionFraudes();
		Formulaire formulaire = new Formulaire();
		gestion.ajouterFormulaire(formulaire);
		assertTrue(gestion.retirerFormulaire(formulaire.getIdentifiant()), "Le retrait aurait du reussir.");
		assertTrue(gestion.getFormulaires().isEmpty(), "La liste devrait etre vide apres retrait.");
	}

	@Test
	public void testRetirerFormulaireInexistant() {
		GestionFraudes gestion = new GestionFraudes();
		assertFalse(gestion.retirerFormulaire(9999), "Le retrait d'un identifiant inconnu devrait echouer.");
	}

	@Test
	public void testRechercherParEtudiant() {
		GestionFraudes gestion = new GestionFraudes();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);
		gestion.ajouterFormulaire(formulaire);
		assertEquals(1, gestion.rechercherFormulairesParEtudiant(alice).size(), "Le formulaire d'Alice n'a pas ete trouve.");
	}
}
