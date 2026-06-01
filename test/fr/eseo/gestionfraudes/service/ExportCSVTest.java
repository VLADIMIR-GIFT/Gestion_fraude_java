package fr.eseo.gestionfraudes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.eseo.gestionfraudes.modele.Formulaire;
import fr.eseo.gestionfraudes.modele.fraude.FraudeIAG;

public class ExportCSVTest {

	@Test
	public void testExportReussi() throws IOException {
		List<Formulaire> formulaires = new ArrayList<Formulaire>();
		Formulaire formulaire = new Formulaire();
		formulaire.ajouterFraude(new FraudeIAG(null, "desc", "ChatGPT"));
		formulaires.add(formulaire);
		Statistiques stats = new Statistiques(formulaires);

		Path fichier = Files.createTempFile("stats", ".csv");
		boolean reussi = ExportCSV.exporterStatistiques(stats, fichier.toString());

		assertTrue(reussi, "L'export aurait du reussir.");
		String contenu = Files.readString(fichier);
		assertTrue(contenu.contains("indicateur;valeur"), "Le CSV devrait contenir l'en-tete.");
		assertTrue(contenu.contains("nombre_formulaires;1"), "Le CSV devrait contenir le nombre de formulaires.");
	}

	@Test
	public void testExportCheminInvalide() {
		Statistiques stats = new Statistiques(new ArrayList<Formulaire>());
		boolean reussi = ExportCSV.exporterStatistiques(stats, "/dossier/inexistant/stats.csv");
		assertEquals(false, reussi, "L'export vers un chemin invalide devrait echouer.");
	}
}
