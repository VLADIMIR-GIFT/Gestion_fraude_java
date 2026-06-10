package fr.eseo.gestionfraudes.modele.fraude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class FraudeSettersTest {

	@Test
	public void testFraudePapierConstructeurVideEtSetters() {
		FraudePapier papier = new FraudePapier();
		assertNull(papier.getDimensions(), "Les dimensions devraient etre nulles.");
		assertFalse(papier.isPlie(), "Le papier ne devrait pas etre plie par defaut.");

		LocalDate date = LocalDate.of(2026, 1, 1);
		papier.setDateReleve(date);
		papier.setDescription("desc");
		papier.setContenu("Formules de trigonometrie");
		papier.setDimensions("A4");
		papier.setPlie(true);
		assertEquals(date, papier.getDateReleve(), "La date n'a pas ete enregistree.");
		assertEquals("desc", papier.getDescription(), "La description n'a pas ete enregistree.");
		assertEquals("Formules de trigonometrie", papier.getContenu(), "Le contenu n'a pas ete enregistre.");
		assertEquals("A4", papier.getDimensions(), "Les dimensions n'ont pas ete enregistrees.");
	}

	@Test
	public void testFraudeCalculatriceConstructeurVideEtSetters() {
		FraudeCalculatrice calc = new FraudeCalculatrice();
		assertNull(calc.getMarque(), "La marque devrait etre nulle.");

		calc.setMarque("Casio");
		calc.setProgramme("MENU_CACHE");
		assertEquals("Casio", calc.getMarque(), "La marque n'a pas ete enregistree.");
		assertEquals("MENU_CACHE", calc.getProgramme(), "Le programme n'a pas ete enregistre.");
	}

	@Test
	public void testFraudeIagConstructeurVideEtSetter() {
		FraudeIAG iag = new FraudeIAG();
		assertNull(iag.getNomService(), "Le nom du service devrait etre nul.");

		iag.setNomService("Mistral");
		assertEquals("Mistral", iag.getNomService(), "Le nom du service n'a pas ete enregistre.");
	}

	@Test
	public void testFraudeIagConnecteeConstructeurVideEtSetter() {
		FraudeIAGConnectee iagc = new FraudeIAGConnectee();
		assertNull(iagc.getAdresseIP(), "L'adresse IP devrait etre nulle.");

		iagc.setAdresseIP("10.0.0.1");
		assertEquals("10.0.0.1", iagc.getAdresseIP(), "L'adresse IP n'a pas ete enregistree.");
	}
}
