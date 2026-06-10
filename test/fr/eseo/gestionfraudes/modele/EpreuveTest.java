package fr.eseo.gestionfraudes.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EpreuveTest {

	@Test
	public void testConstructeur() {
		LocalDateTime date = LocalDateTime.of(2026, 3, 1, 9, 0);
		Epreuve epreuve = new Epreuve("INF-01", date, Duration.ofMinutes(90), Modalite.QCM);
		assertEquals("INF-01", epreuve.getCodeEcue(), "Le code ECUE n'est pas le bon.");
		assertEquals(date, epreuve.getDatePassage(), "La date de passage n'est pas la bonne.");
		assertEquals(Duration.ofMinutes(90), epreuve.getDuree(), "La duree n'est pas la bonne.");
		assertEquals(Modalite.QCM, epreuve.getModalite(), "La modalite n'est pas la bonne.");
	}

	@Test
	public void testConstructeurVide() {
		Epreuve epreuve = new Epreuve();
		assertEquals(null, epreuve.getCodeEcue(), "Le code ECUE devrait etre null.");
		assertEquals(null, epreuve.getModalite(), "La modalite devrait etre null.");
	}

	@Test
	public void testSetters() {
		Epreuve epreuve = new Epreuve();
		LocalDateTime date = LocalDateTime.of(2026, 5, 1, 14, 0);
		epreuve.setCodeEcue("INF-99");
		epreuve.setDatePassage(date);
		epreuve.setDuree(Duration.ofHours(2));
		epreuve.setModalite(Modalite.TP);
		assertEquals("INF-99", epreuve.getCodeEcue(), "Le code n'a pas ete enregistre.");
		assertEquals(date, epreuve.getDatePassage(), "La date n'a pas ete enregistree.");
		assertEquals(Duration.ofHours(2), epreuve.getDuree(), "La duree n'a pas ete enregistree.");
		assertEquals(Modalite.TP, epreuve.getModalite(), "La modalite n'a pas ete enregistree.");
	}

	@Test
	public void testToString() {
		Epreuve epreuve = new Epreuve("INF-01", null, null, Modalite.QCM);
		assertTrue(epreuve.toString().contains("INF-01"), "Le toString devrait contenir le code.");
	}
}
