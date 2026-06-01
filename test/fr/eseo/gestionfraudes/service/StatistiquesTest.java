package fr.eseo.gestionfraudes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.modele.Cursus;
import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;
import fr.eseo.gestionfraudes.modele.fraude.FraudeIAG;

public class StatistiquesTest {

	@Test
	public void testStatistiquesVide() {
		Statistiques stats = new Statistiques(new ArrayList<Formulaire>());
		assertEquals(0, stats.getNombreFormulaires(), "Le nombre de formulaires devrait etre nul.");
		assertEquals(0, stats.getNombreTotalFraudes(), "Le nombre de fraudes devrait etre nul.");
		assertEquals(0.0, stats.getMoyenneFraudesParFormulaire(), "La moyenne devrait etre nulle.");
		assertEquals(0.0, stats.getEcartType(), "L'ecart-type devrait etre nul.");
	}

	@Test
	public void testStatistiquesCalcul() {
		
		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		formulaires.add(creerFormulaire(1));
		formulaires.add(creerFormulaire(2));
		formulaires.add(creerFormulaire(3));

		Statistiques stats = new Statistiques(formulaires);
		assertEquals(3, stats.getNombreFormulaires(), "Le nombre de formulaires n'est pas le bon.");
		assertEquals(6, stats.getNombreTotalFraudes(), "Le nombre total de fraudes n'est pas le bon.");
		assertEquals(2.0, stats.getMoyenneFraudesParFormulaire(), 0.0001, "La moyenne n'est pas la bonne.");
		assertEquals(Math.sqrt(2.0 / 3.0), stats.getEcartType(), 0.0001, "L'ecart-type n'est pas le bon.");
	}

	@Test
	public void testEtudiantsDistincts() {
		
		Etudiant alice = new Etudiant("A001", "Dupont", "Alice", Cursus.E1);
		Formulaire f1 = new Formulaire();
		f1.ajouterEtudiant(alice);
		Formulaire f2 = new Formulaire();
		f2.ajouterEtudiant(new Etudiant("A001", "Dupont", "Alice", Cursus.E1));

		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		formulaires.add(f1);
		formulaires.add(f2);

		Statistiques stats = new Statistiques(formulaires);
		assertEquals(1, stats.getNombreEtudiantsDistincts(), "Le meme etudiant ne devrait compter qu'une fois.");
	}

	private Formulaire creerFormulaire(int nombreFraudes) {
		Formulaire formulaire = new Formulaire();
		for (int i = 0; i < nombreFraudes; i++) {
			formulaire.ajouterFraude(new FraudeIAG(null, "desc", "ChatGPT"));
		}
		return formulaire;
	}
}
