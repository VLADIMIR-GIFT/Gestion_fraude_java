package fr.eseo.gestionfraudes.modele;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
