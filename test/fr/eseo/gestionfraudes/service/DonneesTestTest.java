package fr.eseo.gestionfraudes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DonneesTestTest {

	@Test
	public void testInitialiser() {
		GestionFraudes gestion = new GestionFraudes();
		DonneesTest.initialiser(gestion);
		assertEquals(3, gestion.getFormulaires().size(), "Il devrait y avoir 3 formulaires charges.");
		assertTrue(gestion.getFormulaires().get(0).getEtudiants().size() >= 2, "Le premier formulaire devrait avoir au moins 2 etudiants.");
	}
}
