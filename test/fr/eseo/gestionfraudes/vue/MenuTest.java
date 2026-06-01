package fr.eseo.gestionfraudes.vue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.service.DonneesTest;
import fr.eseo.gestionfraudes.service.GestionFraudes;

public class MenuTest {

	@Test
	public void testListerPuisQuitter() {
		GestionFraudes gestion = new GestionFraudes();
		DonneesTest.initialiser(gestion);

		InputStream entreeInitiale = System.in;
		PrintStream sortieInitiale = System.out;
		System.setIn(new ByteArrayInputStream("1\n0\n".getBytes()));
		ByteArrayOutputStream sortie = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sortie));

		new Menu(gestion).demarrer();

		System.setIn(entreeInitiale);
		System.setOut(sortieInitiale);

		assertTrue(sortie.toString().contains("Formulaire #1"), "La liste devrait afficher le premier formulaire.");
		assertTrue(sortie.toString().contains("Au revoir"), "Le programme devrait afficher le message de fin.");
	}

	@Test
	public void testChoixInvalide() {
		GestionFraudes gestion = new GestionFraudes();

		InputStream entreeInitiale = System.in;
		PrintStream sortieInitiale = System.out;
		System.setIn(new ByteArrayInputStream("99\n0\n".getBytes()));
		ByteArrayOutputStream sortie = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sortie));

		new Menu(gestion).demarrer();

		System.setIn(entreeInitiale);
		System.setOut(sortieInitiale);

		assertTrue(sortie.toString().contains("Choix invalide"), "Un choix invalide devrait afficher un message d'erreur.");
	}

	@Test
	public void testRetirerFormulaire() {
		GestionFraudes gestion = new GestionFraudes();
		DonneesTest.initialiser(gestion);
		int id = gestion.getFormulaires().get(0).getIdentifiant();

		InputStream entreeInitiale = System.in;
		PrintStream sortieInitiale = System.out;
		System.setIn(new ByteArrayInputStream(("3\n" + id + "\n0\n").getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		new Menu(gestion).demarrer();

		System.setIn(entreeInitiale);
		System.setOut(sortieInitiale);

		assertEquals(2, gestion.getFormulaires().size(), "Un formulaire aurait du etre retire.");
	}

	@Test
	public void testRecherchesEtAffichages() {
		GestionFraudes gestion = new GestionFraudes();
		DonneesTest.initialiser(gestion);

		InputStream entreeInitiale = System.in;
		PrintStream sortieInitiale = System.out;
		String saisies = "4\nA2024001\n5\nINF-JAVA-01\n6\nDupont\n7\n8\n0\n";
		System.setIn(new ByteArrayInputStream(saisies.getBytes()));
		ByteArrayOutputStream sortie = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sortie));

		new Menu(gestion).demarrer();

		System.setIn(entreeInitiale);
		System.setOut(sortieInitiale);

		assertTrue(sortie.toString().contains("Formulaire #"), "La recherche aurait du retourner des formulaires.");
		assertTrue(sortie.toString().contains("Dupont"), "La recherche par nom aurait du trouver Dupont.");
		assertTrue(sortie.toString().contains("Statistiques"), "Les statistiques auraient du etre affichees.");
		assertTrue(sortie.toString().contains("Graphe"), "Le graphe aurait du etre affiche.");
	}

	@Test
	public void testExportCsv() {
		GestionFraudes gestion = new GestionFraudes();
		DonneesTest.initialiser(gestion);

		InputStream entreeInitiale = System.in;
		PrintStream sortieInitiale = System.out;
		System.setIn(new ByteArrayInputStream("9\n0\n".getBytes()));
		ByteArrayOutputStream sortie = new ByteArrayOutputStream();
		System.setOut(new PrintStream(sortie));

		new Menu(gestion).demarrer();

		System.setIn(entreeInitiale);
		System.setOut(sortieInitiale);

		assertTrue(sortie.toString().contains("Statistiques exportees"), "L'export CSV aurait du reussir.");
	}

	@Test
	public void testAjouterFormulaire() {
		GestionFraudes gestion = new GestionFraudes();
		int nombreInitial = gestion.getFormulaires().size();

		InputStream entreeInitiale = System.in;
		PrintStream sortieInitiale = System.out;
		String saisies = "2\nINF-TEST\nA999\nDoe\nJohn\nE3E\nn\n3\nDescription\nChatGPT\nn\n0\n";
		System.setIn(new ByteArrayInputStream(saisies.getBytes()));
		System.setOut(new PrintStream(new ByteArrayOutputStream()));

		new Menu(gestion).demarrer();

		System.setIn(entreeInitiale);
		System.setOut(sortieInitiale);

		assertEquals(nombreInitial + 1, gestion.getFormulaires().size(), "Un formulaire aurait du etre ajoute.");
	}
}
