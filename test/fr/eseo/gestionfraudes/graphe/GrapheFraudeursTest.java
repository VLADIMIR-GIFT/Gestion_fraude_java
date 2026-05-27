package fr.eseo.gestionfraudes.graphe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.modele.Cursus;
import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;

public class GrapheFraudeursTest {

	@Test
	public void testGrapheVide() {
		GrapheFraudeurs graphe = new GrapheFraudeurs();
		graphe.construire(new ArrayList<Formulaire>());
		assertEquals(0, graphe.nombreSommets(), "Un graphe sans formulaire ne devrait pas avoir de sommet.");
		assertEquals(0, graphe.nombreAretes(), "Un graphe sans formulaire ne devrait pas avoir d'arete.");
	}

	@Test
	public void testUnEtudiantSeul() {
		GrapheFraudeurs graphe = new GrapheFraudeurs();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);

		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		formulaires.add(formulaire);
		graphe.construire(formulaires);

		assertEquals(1, graphe.nombreSommets(), "Il devrait y avoir un seul sommet.");
		assertEquals(0, graphe.nombreAretes(), "Un etudiant seul ne devrait creer aucune arete.");
	}

	@Test
	public void testDeuxEtudiantsUneArete() {
		GrapheFraudeurs graphe = new GrapheFraudeurs();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Etudiant bob = new Etudiant("A002", "Martin", "Bob", Cursus.E3E);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);
		formulaire.ajouterEtudiant(bob);

		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		formulaires.add(formulaire);
		graphe.construire(formulaires);

		assertEquals(2, graphe.nombreSommets(), "Il devrait y avoir deux sommets.");
		assertEquals(1, graphe.nombreAretes(), "Deux etudiants ensemble devraient creer une arete.");
	}

	@Test
	public void testAffichageTextuel() {
		GrapheFraudeurs graphe = new GrapheFraudeurs();
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterEtudiant(alice);

		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		formulaires.add(formulaire);
		graphe.construire(formulaires);

		String texte = graphe.afficherTextuel();
		assertTrue(texte.contains("A001"), "L'affichage devrait contenir le numero de l'etudiant.");
		assertTrue(texte.contains("(isole)"), "Un etudiant seul devrait etre marque comme isole.");
	}
}
