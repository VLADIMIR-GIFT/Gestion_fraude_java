package fr.eseo.gestionfraudes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.modele.Cursus;
import fr.eseo.gestionfraudes.modele.Epreuve;
import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;
import fr.eseo.gestionfraudes.modele.Modalite;

public class GestionFraudesRechercheTest {

	@Test
	public void testRechercherParEpreuve() {
		GestionFraudes gestion = new GestionFraudes();
		Formulaire formulaire = new Formulaire();
		formulaire.setEpreuve(new Epreuve("INF-01", null, null, Modalite.QCM));
		gestion.ajouterFormulaire(formulaire);
		assertEquals(1, gestion.rechercherFormulairesParEpreuve("INF-01").size(), "Le formulaire de l'epreuve n'a pas ete trouve.");
	}

	@Test
	public void testRechercherParNom() {
		GestionFraudes gestion = new GestionFraudes();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);
		gestion.ajouterFormulaire(formulaire);
		assertEquals(1, gestion.rechercherEtudiantsParNom("dupont").size(), "La recherche par nom (insensible a la casse) a echoue.");
	}

	@Test
	public void testRechercherParPrenom() {
		GestionFraudes gestion = new GestionFraudes();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);
		gestion.ajouterFormulaire(formulaire);
		assertEquals(1, gestion.rechercherEtudiantsParPrenom("alice").size(), "La recherche par prenom a echoue.");
	}

	@Test
	public void testTrouverParNumeroExistant() {
		GestionFraudes gestion = new GestionFraudes();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);
		gestion.ajouterFormulaire(formulaire);
		assertEquals(alice, gestion.trouverEtudiantParNumero("A001"), "L'etudiant attendu n'a pas ete trouve.");
	}

	@Test
	public void testTrouverParNumeroInexistant() {
		GestionFraudes gestion = new GestionFraudes();
		assertNull(gestion.trouverEtudiantParNumero("X999"), "Aucun etudiant ne devrait etre trouve.");
	}
}
